package com.github.arm_gpio_controller.service;

import com.github.arm_gpio_controller.model.Pin;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
public interface Services {
    //used to read and write value on a group of pin.
    //For the value the less-significant-bit is Pin[0]
    //long has 64bits, DO NOT PASS MORE THAN 64pin!
    public abstract long getValues(Pin[] pinArray) throws Exception;
    public abstract void setValues(Pin[] pinArray, long value) throws Exception;
}
