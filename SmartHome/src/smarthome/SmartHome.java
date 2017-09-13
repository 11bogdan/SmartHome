/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthome;
import smarthomelib.*;
import java.util.*;
import java.lang.*;
//import java.lang.reflect.*;
import java.text.*;
import java.util.function.*;

/**
 *
 * @author Богдан
 */
public class SmartHome {
    
    public SmartHome() {
        devices = new ArrayList<Device>();
    }
    
    
    public void off(String id, Date dt) {
        Device d = getDevice(id);
        Operation callback = () -> d.on(false);
        timer.schedule(new DeviceTask(callback), dt);
    }
    
    public void on(String id, Date dt) {
        Device d = getDevice(id);
        Operation callback = () -> d.on(true);
        timer.schedule(new DeviceTask(callback), dt);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        1st place - command:
            add
            remove
            ------
            add/remove on/off display id
            ------
            on/off id time
        
        2nd place - device name
        (on|off|display)
            name
        3rd place - timing (hh:mm)
        */
        
        String com = args[0];
        
    }
    
    private Device getDevice(String id) {
        Device device = null;
        Iterator<Device> it = devices.iterator();
        
        while (it.hasNext()) {
            Device d = it.next();
            if (d.getName() == id) {
                device = d;
                break;
            }
        }
        if (device == null) {
            throw new IllegalArgumentException("No such device exist");
        }
        return device;
    }
    
    private ArrayList<Device> devices;
    private Timer timer;
    
    public class DeviceTask extends TimerTask {
        
        public DeviceTask(Operation o) {
            callback = o;
        }
        
        @Override
        public void run() {
            System.out.println("I'm Mr. MI6, look at me!");
        }
        
        Operation callback;
    }
}
