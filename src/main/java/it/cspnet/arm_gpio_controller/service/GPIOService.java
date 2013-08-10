package it.cspnet.arm_gpio_controller.service;

import it.cspnet.arm_gpio_controller.model.Pin;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

 public interface GPIOService {
    
     public abstract void writePinValue( Pin pin, int value ) throws FileNotFoundException;
     
     public abstract int readPinValue( Pin pin ) throws FileNotFoundException, IOException;
     
     public abstract void configurePin( Pin pin ) throws FileNotFoundException;
     
     public abstract String exportPin( int pinNumber, String path ) throws FileNotFoundException;
     
     public abstract void unexportPin( int pinNumber, String path ) throws FileNotFoundException;
}
