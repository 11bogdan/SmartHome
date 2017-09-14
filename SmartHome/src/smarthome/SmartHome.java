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
public class SmartHome implements IObserver {
    
    public SmartHome() {
        devices = new ArrayList<Device>();
    }
    
    public void notifyObserver(IObservable o) {
        if (Device.class.isInstance(o)){
            // do somth
        }
    }
    
    public void add(String name, String type) {
        Device d = getDevice(name);
        if (d != null) 
            throw new IllegalArgumentException(
                "This name is already taken");
        
        Device newDevice = CreateDevice(name, type);
        devices.add(newDevice);
    }
    
    public void remove(String id) {
        Device d = getDevice(id);
        if (d == null) 
            throw new IllegalArgumentException(
                "Device doesn't exist");
        
        devices.remove(d);
    }
    
    public void off(String id) {
        Device d = getDevice(id);
        d.on(false);
    }
    
    public void off(String id, Date dt) {
        Device d = getDevice(id);
        Operation callback = () -> d.on(false);
        timer.schedule(new DeviceTask(callback), dt);
    }
    
    public void on(String id) {
        Device d = getDevice(id);
        d.on(true);
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
            on/off/display      id
            ------
            on/off              id time
            add/remove          id type
        */
        
        SmartHome sm = new SmartHome();
        try {
            int len = args.length;
            if (len == 1) {
                throw new Exception("Arguments required");
            }
            
            String com = args[1];
            switch (com) {
                case "add":
                    if (len < 4) {
                        throw new Exception("Not enough args for add");
                    }
                    sm.add(args[2], args[3]);
                    break;
                
                case "remove":
                    if (len < 3) {
                        throw new Exception("Not enough args for remove");
                    }
                    sm.remove(args[2]);
                    break;
                    
                case "on":
                    if (len < 3) {
                        throw new Exception("Not enough args for remove");
                    }
                    sm.remove(args[2]);
                    break;
            }
        } catch (Exception e) {
            
        }
    }
    
    public Device getDevice(String id) {
        Device device = null;
        Iterator<Device> iterator = devices.iterator();
        
        while (iterator.hasNext()) {
            Device d = iterator.next();
            if (d.getName() == id) {
                device = d;
                break;
            }
        }

        return device;
    }
    
    private Device CreateDevice(String id, String type) {
        //TODO: implement the method
        return new TVSet(id);
    }
    
    private ArrayList<Device> devices;
    private Timer timer;
    
    public class DeviceTask extends TimerTask {
        
        public DeviceTask(Operation o) {
            callback = (Runnable)o;
        }
        
        @Override
        public void run() {
            callback.run();
        }
        
        Runnable callback;
    }
}
