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
public class CubieboardPin extends Pin {

    private static final String defaultPath = "/sys/devices/virtual/misc/sun4i-gpio/pin/";
    private PrintWriter writer;
    private BufferedReader reader;
    public int ON = Pin.ON_HIGH;
    public int OFF = Pin.OFF_HIGH;

    public CubieboardPin(String name, String type) throws FileNotFoundException, IOException {
        this.name = name;
        this.type = type;
        this.path = defaultPath;
        //open final PrintWriter and BufferedReader
        writer = new PrintWriter(new File(this.path + this.name));
        reader = new BufferedReader(new FileReader(new File(this.path + this.name)));
        //and read initial value
        this.getValue();
    }

    @Override
    public int getValue() throws FileNotFoundException, IOException {
        this.value = Integer.parseInt(reader.readLine());
        return value;
    }

    @Override
    public void setValue(int value) throws FileNotFoundException {
        writer.print(value);
        writer.flush();
        this.value = value;
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
            writer.close();
            reader.close();
        } finally {
            super.finalize();
        }
    }
}