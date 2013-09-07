package com.github.arm_gpio_controller.service;

import java.io.FileNotFoundException;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

public interface ConfigurationService {
    
    public abstract String readPath() throws FileNotFoundException;
}
