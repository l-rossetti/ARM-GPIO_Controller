package it.cspnet.arm_gpio_controller;

import it.cspnet.arm_gpio_controller.controller.ControllerGPIO;
import it.cspnet.arm_gpio_controller.controller.ControllerGPIOCubieImpl;
import it.cspnet.arm_gpio_controller.model.Pin;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

public class Main {
    
    public static void main( String[] args ) throws InterruptedException {
        
        if( args.length == 0 ){
            printHelp();
            System.exit(1);
        }
        
        ControllerGPIO controllerGPIO = new ControllerGPIOCubieImpl();
        
        Pin pe11 = new Pin( "pe11", "/sys/devices/virtual/misc/sun4i-gpio/pin/" );
        pe11.setType( Pin.OUTPUT );
        
        try {
            pe11.setValue( controllerGPIO.readPinValue(pe11) );
            while(true){
                
                controllerGPIO.writePinValue(pe11, Pin.ON);
                System.out.println("Acceso");
                Thread.sleep(500);
                
                controllerGPIO.writePinValue(pe11, Pin.OFF);
                System.out.println("Spento");
                Thread.sleep(500);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("### ERRORE: ###\n\n" + ex.toString());
            System.exit(2);
        } catch (IOException ex) {
            System.err.println("### ERRORE: ###\n\n" + ex.toString());
            System.exit(3);
        }
    }

    private static void printHelp() {
        System.out.println("Usage:\njava ControllerGPIO {PIN NAME}");
    }

}
