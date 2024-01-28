package player;

import main.GamePanel;

public class TileScaler 
{
	Player player;
	GamePanel gp;
	
	public TileScaler(GamePanel gp, Player player)
	{
		this.gp = gp;
	}
	
	public void tileSet()
	{
		if(gp.mouseIndex == 1)
		{
			if(player.direction == "up" || player.direction == "down")
			{
				player.imageWidth = gp.tileSize;
				player.imageHeigth = gp.tileSize + gp.tileSize;
			}
			if(player.direction == "right" || player.direction == "left")
			{
				player.imageWidth = gp.tileSize + gp.tileSize;
				player.imageHeigth = gp.tileSize;
			}
		}
		else
		{
			player.imageWidth = gp.tileSize;
			player.imageHeigth = gp.tileSize;
		}
	}
}
