package it.cspnet.arm_gpio_controller.model;

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
public class Cubieboard_Pin extends Pin {

    private static final String defaultPath = "/sys/devices/virtual/misc/sun4i-gpio/pin/";
    private PrintWriter writer;
    private BufferedReader reader;

    public Cubieboard_Pin(String name, String type) throws FileNotFoundException {
        super(name, defaultPath, type);
        writer = new PrintWriter(new File(this.path + this.name));
        reader = new BufferedReader(new FileReader(new File(this.path + this.name)));
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
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) throws FileNotFoundException {
        this.type = type;
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
