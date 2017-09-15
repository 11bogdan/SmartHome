package smarthomelib;
import java.util.function.*;
import java.io.*;

public abstract class Device 
        implements IObservable, Serializable{
	
        @Override
	public void signUp (Function notify) {
		callback = notify;
	}
        
        public void writeObject(ObjectOutputStream out){
            
        }
        
        public void readObject(ObjectInputStream in) {
        
        }
        
        public void readObjectNoData() {
        
        }
	
	public void on(boolean s) {
		state = s;
	}
	
	public boolean getState() {
		return state;
	}
        
        private void setName(String n) {
            name = n;
        }
        
        public String getName() {
            return name;
        }
        
        
	
        protected String name;
	protected boolean state;
	protected Function callback;
}