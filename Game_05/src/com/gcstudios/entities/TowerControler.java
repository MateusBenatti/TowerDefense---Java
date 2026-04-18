package com.gcstudios.entities;

import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class TowerControler extends Entity{
	
	public boolean isPressed = false;
	public int xTarget, yTarget;

	public TowerControler(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(isPressed) {
			//Vamos criar uma torre no mapa
			isPressed = false;
			boolean liberado = true;
			int xx = (xTarget/16)*16;
			int yy = (yTarget/16)*16;
			Player player = new Player(xx,yy,16,16,0,Game.spritesheet.getSprite(16, 32, 16, 16));
			for(int i = 0; i < Game.entities.size();i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(e, player)) {
						liberado = false;
						System.out.println("Já existe uma torre nessa posição");
					}
				}
			}
			if(World.isFree(xx, yy)) {
				liberado = false;
				System.out.println("Nao pode colocar Torre ai, é a passagem");
			}
			if(liberado) {
				if(Game.dinheiro>= 10) {
					Game.entities.add(player);
					Game.dinheiro-=10;
					return;
				}else {
					System.out.println("Dinehiro Insuficiente");
				}
				
			}
		}
		if(Game.life <= 0) {
			//Game Over
			System.exit(1);
		}
	}

}
