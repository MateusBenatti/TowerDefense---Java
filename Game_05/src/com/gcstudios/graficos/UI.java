package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class UI {
	
	public BufferedImage heart = Game.spritesheet.getSprite(0, 16, 9, 9);

	public void render(Graphics g) {
		for(int i = 0; i< (int)(Game.life); i++) {
			//O parenteses é para o x entre os colaçoes
			g.drawImage(heart, 30+(i*40), 25, 36, 36, null);
			
		}

		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD,30));
		g.drawString("$"+Game.dinheiro, (Game.WIDTH*Game.SCALE)-100, 40);
	}
	
}
