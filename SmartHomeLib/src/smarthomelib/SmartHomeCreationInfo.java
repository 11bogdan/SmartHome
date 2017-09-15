package smarthomelib;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Богдан
 */
@XmlRootElement(name="SmartHomeCreationInfo")
public class SmartHomeCreationInfo {
    
    public SmartHomeCreationInfo() {
        
    } 
    
    public SmartHomeCreationInfo(SmartHome sh) {
        devInfo = new ArrayList<>();
        for (Device d: sh.getDevices()) {
            devInfo.add(new DeviceCreationInfo(
                    d.getName(), d.getClass().getName().split(".")[1]));
        }
    }
    
    @XmlElementWrapper(name="devices")
    @XmlElement(name="DeviceCreationInfo")
    private ArrayList<DeviceCreationInfo> devInfo;

    public ArrayList<DeviceCreationInfo> getDevices() {
        return devInfo;
    }

    public void setDevices(ArrayList<DeviceCreationInfo> devices) {
        this.devInfo = devices;
    }
}
