package com.github.arm_gpio_controller.demo;

import com.github.arm_gpio_controller.model.Pin;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
//a simple DEMO on hot to add a Pin configuration for a new device
public class VirtualPin extends Pin {

    public int ON = Pin.ON_HIGH;
    public int OFF = Pin.OFF_HIGH;
//    or
//    public int ON = Pin.ON_LOW;
//    public int OFF = Pin.OFF_LOW;

    public VirtualPin() {
        this.name = name;
        this.type = type;
//        this.path = path of your pins directory
        
//        initialization code
    }

    @Override
    public int getValue() throws Exception {
        //insert code to access to your Pin
        return this.value;
    }

    @Override
    public void setValue(int value) throws Exception {
        //insert code to access to your Pin
        this.value = value;
    }

    @Override
    public int ON() {
        return this.ON;
    }

    @Override
    public int OFF() {
        return this.OFF;
    }
}
