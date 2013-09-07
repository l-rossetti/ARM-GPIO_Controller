package com.github.arm_gpio_controller.service.impl;

import com.github.arm_gpio_controller.model.Pin;
import com.github.arm_gpio_controller.service.GPIOService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
public class Cubieboard_GPIOServiceImpl implements GPIOService {

    private static final String defaultPath = "/sys/devices/virtual/misc/sun4i-gpio/pin/";

    @Override
    public void writePinValue(Pin pin, int value) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(pin.getPath() + pin.getName()));
        writer.print(value);
        writer.flush();
        pin.setValue(value);
        writer.close();
    }

    @Override
    public int readPinValue(Pin pin) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pin.getPath() + pin.getName())));
        return Integer.parseInt(reader.readLine());
    }

    @Override
    public void configurePin(Pin pin) throws FileNotFoundException {
        //do nothing now
    }

    @Override
    public String exportPin(String pinNumber, String path) throws FileNotFoundException {
        //do nothing, not used
        return null;
    }

    @Override
    public void unexportPin(String pinNumber, String path) throws FileNotFoundException {
        //do nothing, not used
    }

    @Override
    public Pin getNewPin(String name, String type) {
        return new Pin(name, defaultPath, type);
    }
}