package it.cspnet.arm_gpio_controller;

import it.cspnet.arm_gpio_controller.service.GPIOService;
import it.cspnet.arm_gpio_controller.service.impl.Cubieboard_GPIOServiceImpl;
import it.cspnet.arm_gpio_controller.model.Pin;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */

//a simple blinking led application
public class Main {
    
    public static void main( String[] args ) throws InterruptedException {
        
        if( args.length == 0 ){
            printHelp();
            System.exit(1);
        }
        
        GPIOService serviceGPIO = new Cubieboard_GPIOServiceImpl();
        
        Pin pe11 = new Pin( "pe11", "/sys/devices/virtual/misc/sun4i-gpio/pin/" );
        pe11.setType( Pin.OUTPUT );
        
        try {
            pe11.setValue( serviceGPIO.readPinValue(pe11) );
            while(true){
                
                serviceGPIO.writePinValue(pe11, Pin.ON);
                System.out.println("Acceso");
                Thread.sleep(500);
                
                serviceGPIO.writePinValue(pe11, Pin.OFF);
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
