package main;

import java.util.Random;

import object.OBJ_Wheat;


public class ObjectBuilder 
{
	GamePanel gp;
	Random rand = new Random();
	
	ObjectBuilder(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setObjects()
	{
		
		int Wheat = 0;
		int wheatLimitCol = 10;
		int wheatLimitRow = 10;

		for(int i = 0; i < gp.world_Col-wheatLimitCol; i++)
		{
			for(int j =0; j <gp.world_Row-wheatLimitRow; j++)
			{
				gp.obj[Wheat]= new OBJ_Wheat();
				gp.obj[Wheat].worldX = (5+j)*gp.tileSize;
				gp.obj[Wheat].worldY = (5+i)*gp.tileSize;
				Wheat++;
			}
		}
		gp.obj[rand.nextInt(800)+801].potion=true;
	}
}
