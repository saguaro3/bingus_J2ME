package cacti.saguaro.bingus;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

public class Player {
	
	private GameCanvas gc;
	private Sprite bingus;
	private int keyState;
	private int moveFactor;
	
	private int frameCounter;
	
	public Player(GameCanvas gc) {
		this.gc = gc;
		moveFactor = 3;
		
		try {
			bingus = new Sprite(Image.createImage("/images/player/bingusP.png"), 30, 40);
		} catch (IOException ignore) {}
		bingus.setPosition(gc.getWidth() / 2, gc.getHeight() / 2);
		
		frameCounter = 0;
	}
	
	public void update() {
		updateInput();
	}

	private void updateInput() {
		// refreshuje key state
		keyState = gc.getKeyStates();
		if((keyState & GameCanvas.DOWN_PRESSED) != 0) {
			if(bingus.getY() < gc.getHeight() - bingus.getHeight()) {
				bingus.move(0, 1 * moveFactor);
			}
		}
		if((keyState & GameCanvas.UP_PRESSED) != 0) {
			if(bingus.getY() > 0) {
				bingus.move(0, -1 * moveFactor);
			}
		}
		if((keyState & GameCanvas.RIGHT_PRESSED) != 0) {
			if(bingus.getX() < gc.getWidth() - bingus.getWidth()) {
				bingus.move(1 * moveFactor, 0);
			}
		}
		if((keyState & GameCanvas.LEFT_PRESSED) != 0) {
			if(bingus.getX() > 0) {
				bingus.move(-1 * moveFactor, 0);
			}
		}
		
		// svaki 30-ti frame promeni frame bingusa
		frameCounter++;
		if((((keyState & GameCanvas.DOWN_PRESSED) | (keyState & GameCanvas.UP_PRESSED) | (keyState & GameCanvas.LEFT_PRESSED) | (keyState & GameCanvas.RIGHT_PRESSED)) != 0) 
				&& frameCounter > 5) {
			bingus.nextFrame();
			frameCounter = 0;
		}
	}
	
	public void render(Graphics g) {
		bingus.paint(g);
	}
	
	public Sprite getSprite() {
		return bingus;
	}
}
