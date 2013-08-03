package it.cspnet.arm_gpio_controller.controller;

import java.io.FileNotFoundException;

public interface ControllerConfiguration {
    public abstract String readPath() throws FileNotFoundException;
}
