package smarthomelib;
import java.util.function.*;

public abstract class Device implements IObservable{
	
	public void signUp (Function notify) {
		callback = notify;
	}
	
	public void On(boolean s) {
		state = s;
	}
	
	public boolean getState() {
		return state;
	}
        
        public void setName(String n) {
            name = n;
        }
	
        protected String name;
	protected boolean state;
	protected Function callback;
}