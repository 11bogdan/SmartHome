/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthomeprogram;

import java.util.Scanner;
import javax.xml.bind.JAXBException;
import smarthomelib.*;

/**
 *
 * @author Богдан
 */
public class Program {
    
    /**
     * @param args the command line arguments
     * @throws javax.xml.bind.JAXBException
     */
    public static void main(String[] args) throws JAXBException {
        /*
            on/off/display      id
            ------
            on/off              id time
            add/remove          id type
         */

        SmartHome sm = new SmartHome();
        Scanner sc = new Scanner(System.in);
        
        do {
            try {
                System.out.print(">>");
                args = sc.nextLine().split(" ");
                int len = args.length;
                if (len == 0) {
                    throw new Exception("Arguments required");
                }

                String com = args[0];
                switch (com) {
                    case "display":
                        if (len != 2) {
                            sm.display();
                        } else {
                            sm.display(args[1]);
                        }
                        break;

                    case "add":
                        if (len < 3) {
                            throw new Exception("Not enough args");
                        }
                        sm.add(args[1], args[2]);
                        break;

                    case "remove":
                        if (len < 2) {
                            throw new Exception("Not enough args");
                        }
                        sm.remove(args[1]);
                        break;

                    case "on":
                        if (len < 2) {
                            throw new Exception("Not enough args");
                        }
                        sm.on(args[1]);
                        break;

                    case "off":
                        if (len < 2) {
                            throw new Exception("Not enough args");
                        }
                        sm.off(args[1]);
                        break;
                        
                    case "save":
                        if (len == 1) {
                            throw new Exception("Not enough args");
                        }
                        sm.serialize(args[1]);
                        break;
                        
                    case "open":
                        if (len == 1) {
                            throw new Exception("Not enough args");
                        }
                        sm.deserialize(args[1]);
                        break;
                        
                    default:
                        System.out.println("No such command found");
                        break;
                }
            } catch (Exception e) {
                String m = e.getMessage();
                System.out.println(m);
            }
        } while (true);
    }
}
