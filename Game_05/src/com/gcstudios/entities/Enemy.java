package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;

public class Enemy extends Entity{
	
	public BufferedImage[] Enemy1_right;
	public BufferedImage[] Enemy1_left;
	private int frames = 0, maxFrames = 15, index = 0, maxIndex = 3;
	public double vida = 30, maxVida = 30;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		Enemy1_right = new BufferedImage[4];
		
		Enemy1_right[0] = Game.spritesheet.getSprite(32, 0, 16, 16);
		Enemy1_right[1] = Game.spritesheet.getSprite(48, 0, 16, 16);
		Enemy1_right[2] = Game.spritesheet.getSprite(64, 0, 16, 16);
		Enemy1_right[3] = Game.spritesheet.getSprite(80, 0, 16, 16);
		
		Enemy1_left = new BufferedImage[4];
		
		Enemy1_left[0] = Game.spritesheet.getSprite(32, 16, 16, 16);
		Enemy1_left[1] = Game.spritesheet.getSprite(48, 16, 16, 16);
		Enemy1_left[2] = Game.spritesheet.getSprite(64, 16, 16, 16);
		Enemy1_left[3] = Game.spritesheet.getSprite(80, 16, 16, 16);
		
		
	}
	
	public void tick() {
		//Para ele acompanhar a rota dele
		//Para q o inimigo chegue no target q é o alvo final;	
		path = AStar.findPath(Game.world, new Vector2i(this.getX()/16, this.getY()/16), new Vector2i(World.xFinal,World.yFinal));
		//path = AStar.findPath(Game.world, new Vector2i(World.xInicial,World.yInicial), new Vector2i(World.xFinal,World.yFinal));
		followPath(path);
		
		if(x >= Game.WIDTH) {
			//Perdemos vida
			Game.life -=Entity.rand.nextDouble();
			Game.entities.remove(this);
			return;
		}
		
		if(vida<=0) {
			
			Game.entities.remove(this);
			Game.dinheiro+=5;
			return;
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == 0) {
			sprite = Enemy1_right[index];
		}else if (dir == 1) {
			sprite = Enemy1_left[index];
		}
		super.render(g);
		
		//Vida do inimigo
		g.setColor(Color.red);
		g.fillRect((int)x, (int)(y-5), 20, 3);
		g.setColor(Color.green);
		g.fillRect((int)x, (int)(y-5), (int)((vida/maxVida)*20), 3);
		//g.setColor(Color.red);
		//g.fillRect(this.getX(), this.getY(), 16, 16);
	}

}
