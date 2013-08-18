package it.cspnet.arm_gpio_controller;

import it.cspnet.arm_gpio_controller.service.GPIOService;
import it.cspnet.arm_gpio_controller.service.impl.Cubieboard_GPIOServiceImpl;
import it.cspnet.arm_gpio_controller.model.Pin;
import it.cspnet.arm_gpio_controller.service.impl.Raspberry_GPIOServiceImpl;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
//a simple blinking led DEMO application
public class Main {

    private static final String cubieboard_path = "/sys/devices/virtual/misc/sun4i-gpio/pin/";
    //for raspberry: REMEMBER THE / CHAR AT THE END OF THE NAME AND AT THE END OF THE PATH
    private static final String raspberry_path = "/sys/class/gpio/";

    public static void main(String[] args) throws InterruptedException {

        /////////// CUBIEBOARD ///////////
        /*create a Pin
         Pin cubieboard_pe11 = new Pin("pe11", cubieboard_path);
         cubieboard_pe11.setType(Pin.OUTPUT);
         */

        //Create a specific GPIO Service Manager then configure pin
        GPIOService cubieboard_GPIOService = new Cubieboard_GPIOServiceImpl();
        //create a Pin
        Pin cubieboard_pe11 = cubieboard_GPIOService.getNewPin("pe11", Pin.OUTPUT);

        /////////// RASPBERRY ///////////
        /*create a Pin
         Pin raspberry_gpio11 = new Pin();
         */
        //Create a specific GPIO Service Manager then configure pin
        GPIOService raspberry_GPIOService = new Raspberry_GPIOServiceImpl();

        try {
            //configure pins
            /////////// CUBIEBOARD ///////////
            cubieboard_GPIOService.configurePin(cubieboard_pe11);

            /////////// RASPBERRY ///////////
            //return the name: name is "gpio11/"
            String raspberry_pin_name = raspberry_GPIOService.exportPin("11", raspberry_path);
            //create a Raspberry Pin
            Pin raspberry_gpio11 = raspberry_GPIOService.getNewPin(raspberry_pin_name, Pin.OUTPUT);
            //when configure raspberry you MUST configure Pin Type on model and call configurePin method
            //raspberry_gpio11.setType(Pin.OUTPUT);
            raspberry_GPIOService.configurePin(raspberry_gpio11);

            // end of config, now start program

            //read value on init
            cubieboard_pe11.setValue(cubieboard_GPIOService.readPinValue(cubieboard_pe11));

            raspberry_gpio11.setValue(raspberry_GPIOService.readPinValue(raspberry_gpio11));

            while (true) {
                cubieboard_GPIOService.writePinValue(cubieboard_pe11, Pin.ON_HIGH);
                System.out.println("CUBIEBOARD Led ON");

                raspberry_GPIOService.writePinValue(raspberry_gpio11, Pin.ON_HIGH);
                System.out.println("RASPBERRY Led ON");

                Thread.sleep(500);

                cubieboard_GPIOService.writePinValue(cubieboard_pe11, Pin.OFF_HIGH);
                System.out.println("CUBIEBOARD Led OFF");

                raspberry_GPIOService.writePinValue(raspberry_gpio11, Pin.OFF_HIGH);
                System.out.println("RASPBERRY Led OFF");

                Thread.sleep(500);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("### ERROR: ###\n\n" + ex.toString());
            System.exit(2);
        } catch (IOException ex) {
            System.err.println("### ERROR: ###\n\n" + ex.toString());
            System.exit(3);
        }
    }
}