/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthomelib;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Богдан
 */
@XmlRootElement(name="DeviceCreationInfo")
public class DeviceCreationInfo {
    
     public DeviceCreationInfo() {
    }
    
    public DeviceCreationInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    @XmlElement(name="type")
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    private String name;
    private String type;
}
