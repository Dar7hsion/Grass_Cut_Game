package main;

import player.Player;


public class Collision
{
	GamePanel gp;
	
	public Collision(GamePanel gp)
	{
		this.gp = gp;
	}
	
	
	public void checkTile(Player player)
	{
		int entityLeftWorldX = player.onMap_X + player.solidArea.x;
		int entityRightWorldX = player.onMap_X + player.solidArea.x + player.solidArea.width;
		int entityTopWorldY = player.onMap_Y + player.solidArea.y;
		int entityBottomWorldY = player.onMap_Y + player.solidArea.y + player.solidArea.height;
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		int tileNum1, tileNum2;
		
		switch(player.direction)
		{
			case "up":
				entityTopRow = (entityTopWorldY - player.movementSpeed)/gp.tileSize;
				tileNum1 = gp.BE.Tile_Map_Location[entityLeftCol][entityTopRow];
				tileNum2 = gp.BE.Tile_Map_Location[entityRightCol][entityTopRow];
				if(gp.BE.Tile_Obj_Img_Array[tileNum1].collision_On == true || gp.BE.Tile_Obj_Img_Array[tileNum2].collision_On == true)
				{
					player.collisionOn = true;
				}
				break;
				
			case "down":
				entityBottomRow = (entityBottomWorldY - player.movementSpeed)/gp.tileSize;
				tileNum1 = gp.BE.Tile_Map_Location[entityLeftCol][entityBottomRow];
				tileNum2 = gp.BE.Tile_Map_Location[entityRightCol][entityBottomRow];
				if(gp.BE.Tile_Obj_Img_Array[tileNum1].collision_On == true || gp.BE.Tile_Obj_Img_Array[tileNum2].collision_On == true)
				{
					player.collisionOn = true;
				}
				break;
				
			case "left":
				entityLeftCol = (entityLeftWorldX - player.movementSpeed)/gp.tileSize;
				tileNum1 = gp.BE.Tile_Map_Location[entityLeftCol][entityTopRow];
				tileNum2 = gp.BE.Tile_Map_Location[entityLeftCol][entityBottomRow];
				if(gp.BE.Tile_Obj_Img_Array[tileNum1].collision_On == true || gp.BE.Tile_Obj_Img_Array[tileNum2].collision_On == true)
				{
					player.collisionOn = true;
				}
				break;
				
			case "right":
				entityRightCol = (entityRightWorldX - player.movementSpeed)/gp.tileSize;
				tileNum1 = gp.BE.Tile_Map_Location[entityRightCol][entityTopRow];
				tileNum2 = gp.BE.Tile_Map_Location[entityRightCol][entityBottomRow];
				if(gp.BE.Tile_Obj_Img_Array[tileNum1].collision_On == true || gp.BE.Tile_Obj_Img_Array[tileNum2].collision_On == true)
				{
					player.collisionOn = true;
				}
				break;
				
		}
	}	
	public int checkObject(Player player2, boolean player)
		{
			int index = 1999;
			
			for(int i = 0; i < gp.obj.length; i++)
			{
				if(gp.obj[i] != null)
				{
					player2.solidArea.x = player2.onMap_X + player2.solidArea.x;
					player2.solidArea.y = player2.onMap_Y + player2.solidArea.y;
					
					gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
					gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
					
					switch(player2.direction)
					{
					case "up":
						player2.solidArea.y -= player2.movementSpeed;
						if(player2.solidArea.intersects(gp.obj[i].solidArea))
						{
							if(gp.obj[i].collision == true)
							{
								player2.collisionOn = true;
							}
							if(player == true)
							{
								index = i;
							}
						}
						break;
					case "down":
						player2.solidArea.y += player2.movementSpeed;
						if(player2.solidArea.intersects(gp.obj[i].solidArea))
						{
							if(gp.obj[i].collision == true)
							{
								player2.collisionOn = true;
							}
							if(player == true)
							{
								index = i;
							}
						}
						break;
					case "left":
						player2.solidArea.x -= player2.movementSpeed;
						if(player2.solidArea.intersects(gp.obj[i].solidArea))
						{
							if(gp.obj[i].collision == true)
							{
								player2.collisionOn = true;
							}
							if(player == true)
							{
								index = i;
							}
						}
						break;
					case "right":
						player2.solidArea.x += player2.movementSpeed;
						if(player2.solidArea.intersects(gp.obj[i].solidArea))
						{
							if(gp.obj[i].collision == true)
							{
								player2.collisionOn = true;
							}
							if(player == true)
							{
								index = i;
							}
						}
						break;
					}
					
					player2.solidArea.x = player2.solidAreaDefultX;
					player2.solidArea.y = player2.solidAreaDefultY;
					
					gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
					gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				}
			}
			return index;
		}
		
	
	
}
