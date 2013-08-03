package it.cspnet.arm_gpio_controller.controller;

import it.cspnet.arm_gpio_controller.model.Pin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ControllerGPIOCubieImpl implements ControllerGPIO {
    
    @Override
    public void writePinValue( Pin pin, int value ) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter( new File( pin.getPath() + pin.getName() ));
        writer.print(value);
        writer.flush();
        pin.setValue(value);
        writer.close();
    }
    
    @Override
    public int readPinValue( Pin pin ) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader( new FileReader( new File( pin.getPath() + pin.getName() )));
        return Integer.parseInt( reader.readLine() );
    }
}
