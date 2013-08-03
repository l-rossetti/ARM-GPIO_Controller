package it.cspnet.arm_gpio_controller.service;

import java.io.FileNotFoundException;

/**
 *
 * @author Rossetti Leonardo
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

public interface ConfigurationService {
    
    public abstract String readPath() throws FileNotFoundException;
}
