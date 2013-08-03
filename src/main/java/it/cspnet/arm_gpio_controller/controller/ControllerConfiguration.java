package it.cspnet.arm_gpio_controller.controller;

import java.io.FileNotFoundException;

/**
 *
 * @author Rossetti Leonardo
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

public interface ControllerConfiguration {
    public abstract String readPath() throws FileNotFoundException;
}
