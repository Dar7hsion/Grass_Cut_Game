package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class ObjectFrame 
{
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public boolean potion = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp)
	{
		int screenX = worldX - gp.player.onMap_X + gp.player.screenX;
		int screenY = worldY - gp.player.onMap_Y + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.onMap_X - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.onMap_X + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.onMap_Y - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.onMap_Y + gp.player.screenY)
		{
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
		
	}
	

	
}
