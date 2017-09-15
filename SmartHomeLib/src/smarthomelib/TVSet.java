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
        if (c >= MIN_CH && c <= MAX_CH) {
            channel = c;
        }
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TVSet '"+name+"' is "+(state?"on":"off")+"\n");
        if (state) {
            sb.append("Current channel: "+channel+"\n");
            sb.append("Sound: "+sound+"\n");
        }
        return sb.toString();
    }

    private int sound;
    private int channel;
}