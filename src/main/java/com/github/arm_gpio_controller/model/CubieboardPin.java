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

    public CubieboardPin(String name, String type) throws FileNotFoundException, IOException {
        super(name, type);
        //open final PrintWriter and BufferedReader
        writer = new PrintWriter(new File(this.defaultPath + this.name));
        reader = new BufferedReader(new FileReader(new File(this.defaultPath + this.name)));
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
    public int ON() {
        return 0x1;
    }

    @Override
    public int OFF() {
        return 0x0;
    }

    @Override
    public boolean isON(int value) {
        return value == 0x1; //retur true if passed value is equals to 0x1, the true value
    }

    @Override
    public boolean isOFF(int value) {
        return value == 0x0; //retur true if passed value is equals to 0x0, the true value
    }

    //destroy method
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