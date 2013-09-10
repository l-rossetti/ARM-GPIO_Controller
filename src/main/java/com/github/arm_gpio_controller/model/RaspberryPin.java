package com.github.arm_gpio_controller.model;

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
public class RaspberryPin extends Pin {

    private static final String defaultPath = "/sys/class/gpio/";
    private int pinNumber;
    private PrintWriter writer;
    private BufferedReader reader;

    public RaspberryPin(String name, String type) throws FileNotFoundException, IOException {
       super(name, type);
        //export pin
        pinNumber = Integer.parseInt(name);
        if (!name.startsWith("gpio")) {
            //must export the pin
            writer = new PrintWriter(new File(defaultPath + "export"));
            writer.print(name);
            writer.flush();
            writer.close();
            name = "gpio" + name + "/";
        }
        //set direction, default contructor write the general type, but Raspberry need some init function
        this.setType(type);
        //open final PrintWriter and BufferedReader
        writer = new PrintWriter(new File(defaultPath + name + "value"));
        reader = new BufferedReader(new FileReader(new File(defaultPath + name + "value")));
        //and read initial value
        this.getValue();
    }

    @Override
    public int getValue() throws FileNotFoundException, IOException {
        return Integer.parseInt(reader.readLine());
    }

    @Override
    public void setValue(int value) throws FileNotFoundException {
        writer.print(value);
        writer.flush();
    }

    @Override
    public void setType(String type) throws FileNotFoundException {
        writer = new PrintWriter(new File(defaultPath + name + "direction"));
        if (Pin.OUTPUT.equals(type)) {
            writer.print("out");
        } else if (Pin.INPUT.equals(type)) {
            writer.print("in");
        }
        writer.flush();
        writer.close();
        this.type = type;
    }

    @Override
    public int ON() {
        return 0x1;
    }

    @Override
    public int OFF() {
        return 0x0;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            writer.close();//to close read/setValue
            writer = new PrintWriter(new File(defaultPath + "unexport"));
            writer.print(pinNumber);
            writer.flush();
            writer.close();
            reader.close();
        } finally {
            super.finalize();
        }
    }
}