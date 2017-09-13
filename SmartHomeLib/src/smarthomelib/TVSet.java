package smarthomelib;

public class TVSet extends Device {
	
	public TVSet() {
		state = false;
	}
	
	public void setChannel(int c) {
		channel = c;
	}
	
	private int channel;
}