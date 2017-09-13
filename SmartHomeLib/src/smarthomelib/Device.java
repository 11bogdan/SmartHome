package smarthomelib;

public abstract class Device implements IObservable{
	
	public void SignUp (Method m) {
		callback = m;
	}
	
	public void On(boolean s) {
		state = s;
	}
	
	public void getState() {
		return state;
	}
	
	private boolean state;
	private Method callback;
}