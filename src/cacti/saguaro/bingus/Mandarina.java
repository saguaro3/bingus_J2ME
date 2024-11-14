package cacti.saguaro.bingus;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

public class Mandarina {
	private GameCanvas gc;
	private Sprite mandarina;
	private Player player;
	private Random r;
	private int brPoena;
	private int sleepTime;
	private Font f;

	public Mandarina(GameCanvas gc, Player pl) {
		this.gc = gc;
		r = new Random();
		player = pl;
		brPoena = 0;
		sleepTime = 30;
		f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
		
		try {
			mandarina = new Sprite(Image.createImage("/images/mandarina.png"), 40, 40);
		} catch (IOException ignore) {}
		randChange();
	}
	
	public void update() {
		if(mandarina.collidesWith(player.getSprite(), true)) {
			randChange();
			
			brPoena++;
			
			if(brPoena == 69){
				pobeda();
			}
			
			sleepTime = sleepTime - 1;
		}
	}
	
	public void pobeda() {
		mandarina.setVisible(false);
		player.getSprite().setVisible(false);
		BingusCanvas.pobeda = true;
	}

	private void randChange() {
		if(r.nextInt(2) == 0) {
			mandarina.nextFrame();
		}
		mandarina.setPosition(r.nextInt(gc.getWidth() - mandarina.getWidth()), r.nextInt(gc.getHeight() - mandarina.getHeight()));
	}
	
	public void render(Graphics g) {
		mandarina.paint(g);
		g.setColor(0xFFFFFF);
		g.setFont(f);
		g.drawString(String.valueOf(brPoena), gc.getWidth() / 2, 20, Graphics.TOP | Graphics.HCENTER);
	}
	
	public int getSleepTime() {
		return sleepTime;
	}
}
