package com.github.arm_gpio_controller.demo;

import com.github.arm_gpio_controller.model.Pin;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

//a simple DEMO on how to add a Pin configuration for a new device
public class VirtualPin extends Pin {
    
    //if you have a REAL pin value will be read and write directy, value variable will be removed
    private int value;

    public VirtualPin(String name, String type) {
        super(name, type);        
        //your initialization code
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
        return 0x1; //with ON-HIGH
        //or 0x0 with ON-LOW
    }

    @Override
    public int OFF() {
        return 0x0; //with OFF-HIGH
        //or 0x1 with OFF-LOW
    }
}