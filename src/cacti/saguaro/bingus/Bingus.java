package cacti.saguaro.bingus;

import javax.microedition.lcdui.Display;			
import javax.microedition.midlet.MIDlet;

public class Bingus extends MIDlet {
	private static BingusCanvas bingusCanvas;
	
	public Bingus() {							
		bingusCanvas = new BingusCanvas();				
	}	
	
	public void startApp() {
		Display display = Display.getDisplay(this);
		bingusCanvas.start();
		display.setCurrent(bingusCanvas);	
	}
	
	public void pauseApp() {
		
	}
	
	public void destroyApp(boolean unconditional) {
		
	}
}