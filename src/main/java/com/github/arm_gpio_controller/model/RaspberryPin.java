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
    public int ON = Pin.ON_HIGH;
    public int OFF = Pin.OFF_HIGH;

    public RaspberryPin(String name, String type) throws FileNotFoundException, IOException {
        this.name = name;
        this.path = defaultPath;

        //export pin
        pinNumber = Integer.parseInt(name);
        if (!name.startsWith("gpio")) {
            //must export the pin
            writer = new PrintWriter(new File(path + "export"));
            writer.print(name);
            writer.flush();
            writer.close();
            name = "gpio" + name + "/";
        }
        //set direction
        this.setType(type);
        
        //open final PrintWriter and BufferedReader
        writer = new PrintWriter(new File(path + name + "value"));
        reader = new BufferedReader(new FileReader(new File(path + name + "value")));
        
        //and setup initial value
        this.getValue();
    }

    public int getValue() throws FileNotFoundException, IOException {
        this.value = Integer.parseInt(reader.readLine());
        return value;
    }

    public void setValue(int value) throws FileNotFoundException {
        writer.print(value);
        writer.flush();
        this.value = value;
    }

    @Override
    public void setType(String type) throws FileNotFoundException {
        writer = new PrintWriter(new File(path + name + "direction"));
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
        return this.ON;
    }

    @Override
    public int OFF() {
        return this.OFF;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            writer.close();//to close read/setValue
            writer = new PrintWriter(new File(path + "unexport"));
            writer.print(pinNumber);
            writer.flush();
            writer.close();
            reader.close();
        } finally {
            super.finalize();
        }
    }
}