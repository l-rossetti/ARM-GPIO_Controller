package it.cspnet.arm_gpio_controller.service.impl;

import it.cspnet.arm_gpio_controller.model.Pin;
import it.cspnet.arm_gpio_controller.service.GPIOService;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

public class Raspberry_GPIOServiceImpl implements GPIOService {

    public void writePinValue(Pin pin, int value) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int readPinValue(Pin pin) throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
