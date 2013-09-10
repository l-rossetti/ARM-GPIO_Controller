package com.github.arm_gpio_controller.demo;

import com.github.arm_gpio_controller.model.CubieboardPin;
import com.github.arm_gpio_controller.model.Pin;
import com.github.arm_gpio_controller.model.RaspberryPin;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

//a simple blinking led DEMO application
public class Main {

    public static void main(String[] args) {
        try {
            /////////// CUBIEBOARD ///////////
            //create a Pin
            Pin cubieboard_pe11 = new CubieboardPin("pe11", Pin.OUTPUT);

            /////////// RASPBERRY ///////////
            //create a Pin        
            Pin raspberry_gpio11 = new RaspberryPin("11", Pin.OUTPUT);

            while (true) {
                //ON
                cubieboard_pe11.setValue(cubieboard_pe11.ON());
                System.out.println("CUBIEBOARD Led ON");

                raspberry_gpio11.setValue(raspberry_gpio11.ON());
                System.out.println("RASPBERRY Led ON");

                Thread.sleep(500);
                //OFF
                cubieboard_pe11.setValue(cubieboard_pe11.OFF());
                System.out.println("CUBIEBOARD Led OFF");

                raspberry_gpio11.setValue(raspberry_gpio11.OFF());
                System.out.println("RASPBERRY Led OFF");

                Thread.sleep(500);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("### ERROR: ###\n\n" + ex.toString());
            System.exit(2);
        } catch (IOException ex) {
            System.err.println("### ERROR: ###\n\n" + ex.toString());
            System.exit(3);
        } catch (Exception ex) {
            System.err.println("### ERROR: ###\n\n" + ex.toString());
            System.exit(4);
        }
    }
}