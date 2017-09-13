package smarthomelib;

public class TVSet extends Device {
    private final int MIN_CH = 1, MAX_CH = 99;
    private final int MIN_S = 0, MAX_S = 100;
    
    public TVSet() {
        state = false;
    }
    
    public TVSet(String name) {
        this();
        this.name = name;
    }

    public void setChannel(int c) {
        channel = c;
    }

    public int getChannel () {
        return channel;
    }

    public void channelUp() {
        channel = channel == MAX_CH ? MAX_CH : channel+1;
    }

    public void channelDown () {
        channel = channel == MIN_CH ? MIN_CH : channel-1;
    }
    
    public void soundUp() {
        sound = sound == MAX_S ? MAX_S : channel+1;
    }
    
    public void soundDown() {
        sound = sound == MIN_S ? MIN_S : channel-1;
    }

    private int sound;
    private int channel;
}