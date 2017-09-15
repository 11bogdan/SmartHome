/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthomelib;

import java.io.*;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Богдан
 */
public class SmartHome implements IObserver {

    public SmartHome() throws JAXBException {
        devices = new ArrayList<>();
        context = JAXBContext.newInstance(SmartHomeCreationInfo.class);
        timer = new Timer();
    }

    @Override
    public void notifyObserver(IObservable o) {
        if (Device.class.isInstance(o)) {
            // do somth
        }
    }

    public void display() {
        System.out.println(devices.size() + " devices present");
        if (!devices.isEmpty()) {
            Iterator<Device> iter = devices.iterator();
            while (iter.hasNext()) {
                Device d = iter.next();
                System.out.println("***");
                System.out.println(d.toString());
            }
        }
    }

    public void display(String arg) {
        Device d = getDevice(arg);
        System.out.println(d.toString());
    }

    public void add(String name, String type) {
        Device d = getDevice(name);
        if (d != null) {
            throw new IllegalArgumentException(
                    "This name is already taken");
        }

        Device newDevice = createDevice(name, type);
        devices.add(newDevice);
    }

    public void remove(String id) {
        Device d = getDevice(id);
        if (d == null) {
            throw new IllegalArgumentException(
                    "Device doesn't exist");
        }

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

    public Device getDevice(String id) {
        Device device = null;
        Iterator<Device> iterator = devices.iterator();

        while (iterator.hasNext()) {
            Device d = iterator.next();
            if (d.getName().equals(id)) {
                device = d;
                break;
            }
        }

        return device;
    }

    private Device createDevice(String id, String type) {
        Device device = null;
        switch (type) {
            case "TVSet":
                device = new TVSet(id);
                break;
        }
        return device;
    }
    
    public void deserialize(String srcFile) {
        try {

            Unmarshaller um = context.createUnmarshaller();
            SmartHomeCreationInfo si = 
                    (SmartHomeCreationInfo) um.unmarshal(new File(srcFile));
            
            devices.clear();
            timer.cancel();
            
            for(DeviceCreationInfo di: si.getDevices()) {
                try {
                    add(di.getName(), di.getType());
                } catch (IllegalArgumentException e) {
                    System.out.println("The device named '"+di.getName()+
                            "' has been met again");
                }
            }
            
        } catch (JAXBException ex) {
            Logger.getLogger(SmartHome.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void serialize (String dstFile) throws JAXBException {
        Marshaller m = context.createMarshaller();
        SmartHomeCreationInfo si = new SmartHomeCreationInfo(this);
        m.marshal(si, new File(dstFile));
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    private final ArrayList<Device> devices;
    private final Timer timer;
    private final JAXBContext context;

    public class DeviceTask extends TimerTask {

        public DeviceTask(Operation o) {
            callback = (Runnable) o;
        }

        @Override
        public void run() {
            callback.run();
        }

        Runnable callback;
    }
}
