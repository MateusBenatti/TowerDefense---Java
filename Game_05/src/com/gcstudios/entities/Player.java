package com.gcstudios.entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;



public class Player extends Entity{
	
	public int xTarget,yTarget;
	public boolean atacando = false;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}
	
	public void tick(){
		Enemy enemy = null;
		for(int i =0; i <Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				int xEnemy = e.getX(), yEnemy = e.getY();
				//Raio de ataque, se ta perto ou longe da torre para atacar
				if(Entity.calculateDistance(this.getX(), this.getY(), xEnemy, yEnemy) < 40) {
					enemy = (Enemy)e;
					
				}
			}
		}
		//Quer dizer q um inimigo esta no range de ataque
		if(enemy != null) {
			atacando = true;
			xTarget = enemy.getX();
			yTarget = enemy.getY();
			if(Entity.rand.nextInt(100)<20) {
				enemy.vida-=Entity.rand.nextDouble();
			}
		}else{
			atacando = false;
		}
		
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		//Mostrar Ataque das torres
		if(atacando) {
			g.setColor(Color.cyan);
			g.drawLine((int)x+8, (int)y+8, xTarget+8, yTarget+8);
		}
	}
}
