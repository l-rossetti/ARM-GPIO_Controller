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
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

/*
 * for info https://sites.google.com/site/semilleroadt/raspberry-pi-tutorials/gpio
 */
public class Raspberry_GPIOServiceImpl implements GPIOService {

    public void writePinValue(Pin pin, int value) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(pin.getPath() + pin.getName() + "value" ));
        writer.print(value);
        writer.flush();
        pin.setValue(value);
        writer.close();
    }

    public int readPinValue(Pin pin) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pin.getPath() + pin.getName() + "value" )));
        return Integer.parseInt(reader.readLine());
    }

    public void configurePin(Pin pin) throws FileNotFoundException {
        //use the pin type: INPUT or OUTPUT
        //configure Pin for input and output
        //before configure Pin you need to be create and allowed the pin into the kernel
        //to configure "echo 11 > /sys/class/gpio/export" and you enable pin named "pin11" or use exportPin() function
        PrintWriter writer = new PrintWriter(new File(pin.getPath() + pin.getName() + "direction" ));
        if (Pin.OUTPUT.equals(pin.getType())) {
            writer.print("out");
        } else if (Pin.INPUT.equals(pin.getType())) {
            writer.print("in");
        }
        writer.flush();
        writer.close();
    }
    
    public String exportPin(int pinNumber, String path) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(path + "export" ));
        writer.print(pinNumber);
        writer.flush();
        writer.close();
        return "gpio"+pinNumber+"/";
    }

    public void unexportPin(int pinNumber, String path) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(path + "unexport" ));
        writer.print(pinNumber);
        writer.flush();
        writer.close();
    }
}
