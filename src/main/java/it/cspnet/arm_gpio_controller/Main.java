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
//a simple DEMO blinking led application
public class Main {

    public static void main(String[] args) throws InterruptedException {

        if (args.length == 0) {
            printHelp();
            System.exit(1);
        }

        /////////// CUBIEBOARD ///////////
        //create a Pin
        Pin cubieboard_pe11 = new Pin("pe11", "/sys/devices/virtual/misc/sun4i-gpio/pin/");
        cubieboard_pe11.setType(Pin.OUTPUT);
        //Create a specific GPIO Service Manager and configure pin
        GPIOService cubieboard_GPIOService = new Cubieboard_GPIOServiceImpl();

        /////////// RASPBERRY ///////////
        //create a Pin
        Pin raspberry_gpio11 = new Pin("gpio11/", "/sys/class/gpio/");
        //REMEMBER THE / CHAR AT THE END OF THE NAME AND AT THE END OF THE PATH
        raspberry_gpio11.setType(Pin.OUTPUT);
        //Create a specific GPIO Service Manager and configure pin
        GPIOService raspberry_GPIOService = new Raspberry_GPIOServiceImpl();

        try {
            //configure pins
            cubieboard_GPIOService.configurePin(cubieboard_pe11);
            raspberry_GPIOService.configurePin(raspberry_gpio11);

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

    private static void printHelp() {
        System.out.println("Usage:\njava program {PIN_NAME}");
    }
}
