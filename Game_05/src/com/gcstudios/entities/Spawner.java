package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Spawner extends Entity{
	
	
	private int timer = 60, curTimer = 0 ;
	
	public Spawner(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		//Criar Inimigos
		curTimer++;
		if(curTimer == timer) {
			//Hora de criar o Inimigo
			curTimer = 0;
			timer = Entity.rand.nextInt(60-30)+30;
			Enemy enemy = new Enemy(x,y,16,16,Entity.rand.nextDouble(),Entity.Enemy1_right);
			//Entity.rand.nextDouble() a velocidade pode ser assim para variar entre 0 e 1
			Game.entities.add(enemy);
		}
		
	}
	
	
}
