package background;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BackgroundEngine
{
	GamePanel gp;
	public Tile_Obj_16x16_Unit[] Tile_Obj_Img_Array;
	public int Tile_Map_Location[][];
	
	public BackgroundEngine(GamePanel gp)
	{
		this.gp = gp;
		
		Tile_Obj_Img_Array = new Tile_Obj_16x16_Unit[10];
		
		Tile_Map_Location = new int[gp.world_Col] [gp.world_Row];
		
		getTileImage();
		loadMap("/maps/map_2.txt");
	}
	
	public void getTileImage()
	{
		
		int a,b,c;
		a = 4;
		c = 0;
		
		try
		{
			Tile_Obj_Img_Array[c] = new Tile_Obj_16x16_Unit();
			Tile_Obj_Img_Array[c].img = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass4.png"));

			Tile_Obj_Img_Array[a] = new Tile_Obj_16x16_Unit();
			Tile_Obj_Img_Array[a].img = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree6.png"));
			Tile_Obj_Img_Array[a].collision_On = true;
		
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath)
	{
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0; 
			int row = 0;
			
			while(col < gp.world_Col && row < gp.world_Row)
			{
				String line = br.readLine();
				
				while(col < gp.world_Col)
				{
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					Tile_Map_Location[col][row] = num;
					col++;
					
				}
				if(col == gp.world_Col)
				{
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(Exception e) 
		{
			
		}
	}
	
	public void draw(Graphics2D g2)
	{	
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.world_Col && worldRow < gp.world_Row)
		{
			int tileNum = Tile_Map_Location[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.onMap_X + gp.player.screenX;
			int screenY = worldY - gp.player.onMap_Y + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.onMap_X - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.onMap_X + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.onMap_Y - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.onMap_Y + gp.player.screenY)
			{
			g2.drawImage(Tile_Obj_Img_Array[tileNum].img, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;

			
			if(worldCol == gp.world_Col)
			{
				worldCol = 0;

				worldRow++;

			}
		}
	}
	
}
