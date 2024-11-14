package cacti.saguaro.bingus;

import java.io.IOException;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class BingusCanvas extends GameCanvas implements Runnable {
	private int sleepTime = 20; //(1 / 60) * 1000
	private Image bg;
	private Player bingus;
	private Mandarina mandarina;
	public static boolean pobeda;
	
	public BingusCanvas() {
		super(false);
		setFullScreenMode(true);
	}
	
	public void start() {
		try {
			pobeda = false;
			// pozadina
			bg = Image.createImage("/images/bingus240X320.png");
			
			// player
			bingus = new Player(this);
			// mandarina
			mandarina = new Mandarina(this, bingus);
			
		} catch(IOException ignore) {}
		
		Thread runner = new Thread(this);
		runner.start();
	}

	// glavna game petlja
	public void run() {
		while(true) {
			try {
			Thread.sleep(30);
			} catch (Exception e) {}
			
			// update-uje unos
			bingus.update();
			mandarina.update();
			// update-uje ekran
			updateScreen(getGraphics());
		}
	}

	private void updateScreen(Graphics g) {
		if(pobeda) {
			g.drawImage(bg, 0, 0, 0);
		} else {
			createBackground(g);
			mandarina.render(g);
			bingus.render(g);
		}
		// stavlja frame buffer na ekran
		flushGraphics();
	}

	private void createBackground(Graphics g) {
		// setuje boju
		g.setColor(0x000000);
		// puni pravougaonik velicine ekrana sa odabranom bojom
		g.fillRect(0, 0, getWidth(), getHeight());
		// crta pozadinu
		/*g.drawImage(bg, 0, 0, 0);*/
	}
}