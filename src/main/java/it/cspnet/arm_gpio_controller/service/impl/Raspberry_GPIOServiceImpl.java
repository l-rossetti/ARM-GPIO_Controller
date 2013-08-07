package it.cspnet.arm_gpio_controller.service.impl;

import it.cspnet.arm_gpio_controller.model.Pin;
import it.cspnet.arm_gpio_controller.service.GPIOService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Rossetti Leonardo
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
public class Raspberry_GPIOServiceImpl implements GPIOService {

    public void writePinValue(Pin pin, int value) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(pin.getPath() + pin.getName()));
        writer.print(value);
        writer.flush();
        pin.setValue(value);
        writer.close();
    }

    public int readPinValue(Pin pin) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pin.getPath() + pin.getName())));
        return Integer.parseInt(reader.readLine());
    }
}
