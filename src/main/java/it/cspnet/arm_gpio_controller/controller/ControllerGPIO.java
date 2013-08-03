package it.cspnet.arm_gpio_controller.controller;

import it.cspnet.arm_gpio_controller.model.Pin;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

 public interface ControllerGPIO {
    
     public abstract void writePinValue( Pin pin, int value ) throws FileNotFoundException;
     
     public abstract int readPinValue( Pin pin ) throws FileNotFoundException, IOException;
}
