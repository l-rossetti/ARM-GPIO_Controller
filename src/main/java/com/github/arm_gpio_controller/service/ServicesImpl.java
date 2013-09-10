package com.github.arm_gpio_controller.service;

import com.github.arm_gpio_controller.model.Pin;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
public class ServicesImpl implements Services {

    public long getValues(Pin[] pinArray) throws Exception {
        //set-up
        long returnValue = 0;
        int pinArrayLength;
        if (pinArray.length >= 64) {
            pinArrayLength = 64;
        } else {
            pinArrayLength = pinArray.length;
        }
        //real code
        for (int i = 0; i < pinArrayLength; i++) {
            returnValue |= (pinArray[i].getValue() << i);
        }
        return returnValue;
    }

    public void setValues(Pin[] pinArray, long value) throws Exception {
        //setup
        int pinArrayLength;
        long toWriteValue;
        long mask = 0x1;
        if (pinArray.length >= 64) {;
            pinArrayLength = 64;
        } else {
            pinArrayLength = pinArray.length;
        }
        //real code
        for (int i = 0; i < pinArrayLength; i++) {
            toWriteValue = (value & (mask << i) ) >> i;
            pinArray[i].setValue( (int)toWriteValue );
        }
    }
}
