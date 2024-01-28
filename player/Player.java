package player;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.MouseListener;
import object.OBJ_Potion;

public class Player 
{
	GamePanel gp;
	KeyHandler keyH;
	MouseListener ML;
	public final int screenX, screenY;
	public int Health;
	public int onMap_X, onMap_Y;
	public int movementSpeed;
	public int last_X, last_Y;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, 
	right2, Death, upCut, downCut, leftCut, rightCut;
	public String direction;
	public int imageWidth;
	public int imageHeigth;
	public int imageScreenX;
	public int imageScreenY;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int spriteAttackNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefultX, solidAreaDefultY;
	public boolean collisionOn = false;
	
	public Player(GamePanel gp, KeyHandler keyH, MouseListener ML)
	{
		this.gp = gp;
		this.keyH = keyH;
		this.ML = ML;
		Health = 50;
		screenX = gp.windowWidth/2 - (gp.tileSize/2);
		screenY = gp.windowHeight/2 - (gp.tileSize/2);
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		setDefaultValues();
		getPlayerImage();
		getCutImage();
	}
	
	public void setDefaultValues()
	{
		imageScreenX = screenX;
		imageScreenY = screenY;
		imageWidth = gp.tileSize;
		imageHeigth = gp.tileSize;
		onMap_X = gp.tileSize * 3;
		onMap_Y = gp.tileSize * 3;
		movementSpeed = 3;
		direction = "down";
	}
	public void getPlayerImage()
	{
		try
		{
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Bill_right_2.png"));
			Death = ImageIO.read(getClass().getResourceAsStream("/player/RIP.png"));
			
		}catch(IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public void getCutImage()
	{
		try
		{
			upCut = ImageIO.read(getClass().getResourceAsStream("/player/Bill_attackUp.png"));
			downCut = ImageIO.read(getClass().getResourceAsStream("/player/Bill_attackDown.png"));
			leftCut = ImageIO.read(getClass().getResourceAsStream("/player/Bill_attackLeft.png"));
			rightCut = ImageIO.read(getClass().getResourceAsStream("/player/Bill_attackRight.png"));
		
		}catch(IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
			if(Health>0)
			{
				if(keyH.upPressed == true)
				{
					direction = "up";
				}
				else if(keyH.downPressed == true)
				{
					direction = "down";
				}
				else if(keyH.leftPressed ==true)
				{
					direction = "left";
				}
				else if(keyH.rightPressed ==true)
				{
					direction = "right";
				}
			}
			else
			{
				direction = "Death";
			}
			collisionOn = false;
			gp.cChecker.checkTile(this);
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			if(collisionOn == false)
			{
				switch(direction)
				{
				case "up": 
					onMap_Y -= movementSpeed; 
					Health();
					break;
				case "down": 
					onMap_Y += movementSpeed;
					Health();
					break;
				case "left": 
					onMap_X -= movementSpeed;
					Health();
					break;
				case "right": 
					onMap_X += movementSpeed;
					Health();
					break;	
				}
			}
			spriteCounter++;
			if(spriteCounter > 12)
			{
				if(spriteNum == 1)
				{
					spriteNum = 2;
				}
				else if(spriteNum == 2)
				{
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	public void pickUpObject(int i)
	{
		if(i != 1999)
		{
			String objectName = gp.obj[i].name;
			switch(objectName)
			{
			case "Wheat":
				if(gp.obj[i].potion && gp.mouseIndex == 1)
				{
					int tempX = gp.obj[i].worldX;
					int tempY = gp.obj[i].worldY;
					gp.obj[i]= new OBJ_Potion();
					gp.obj[i].worldX = tempX-40;
					gp.obj[i].worldY = tempY-40;
				}
				else if(gp.mouseIndex == 1)
				{
					gp.obj[i] = null;
					gp.p.setPoint();
					gp.cutIndex = true;
				}
				break;
				
			case "Potion":
				gp.obj[i] = null;
				System.out.println("win");
				System.out.println("Cut: " + gp.cut);
				System.out.println("Point: " + gp.points);
				gp.win = true;
				gp.setGameThread();
				break;
				
			}	
		}
	}
	public void Health()
	{
		int mod = 4;
		
		if(onMap_X == gp.tileSize * 23 && onMap_Y == gp.tileSize * 20)
		{
			last_X = onMap_X;
			last_Y = onMap_Y;
		}
		if(onMap_X < last_X - mod*gp.tileSize || onMap_X > last_X + mod*gp.tileSize || onMap_Y < last_Y - mod*gp.tileSize || onMap_Y > last_Y + mod*gp.tileSize)
		{
			last_X = onMap_X;
			last_Y = onMap_Y;
			Health--;
			System.out.println("Health: " + Health);
		}
		if(Health<=0)
		{
			System.out.println("lose");
		}
	}
	
	public void TileScaler()
	{
		if(gp.mouseIndex == 1)
		{
			
			if(direction == "up" || direction == "down")
			{
				if(direction == "up")
				{
					imageScreenX = screenX;
					imageScreenY = screenY- gp.tileSize;
				}
				
				imageWidth = gp.tileSize;
				imageHeigth = gp.tileSize + gp.tileSize;
			}	
			
			if(direction == "right" || direction == "left")
			{
				if(direction == "left")
				{
					imageScreenX = screenX- gp.tileSize;
					imageScreenY = screenY;
				}
				
				imageWidth = gp.tileSize + gp.tileSize;
				imageHeigth = gp.tileSize;
			}
			
			if(spriteAttackNum < 6)
			{
				spriteAttackNum++;
			}
			else
			{
				spriteAttackNum = 1;
				gp.mouseIndex = 0;
			}
			
		}
		else
		{   
			imageScreenX = screenX;
			imageScreenY = screenY;
			imageWidth = gp.tileSize;
			imageHeigth = gp.tileSize;
		}
	}
	
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		switch(direction)
		{
		case "up":
			System.out.println(gp.cutIndex);
			if(gp.mouseIndex == 1)
			{
				image = upCut;
			}
			else
			{
				if(spriteNum == 1)
				{
					image = up1;
				}
				if(spriteNum == 2)
				{
					image = up2;
				}
			}
			
			break;
		case "down":
			if(gp.mouseIndex == 1)
			{
				image = downCut;
			}
			else
			{
				if(spriteNum == 1 )
				{
					image = down1;
				}
				if(spriteNum == 2 )
				{
					image = down2;
				}
			}
			
			break;
		case "left":
			if(gp.mouseIndex == 1)
			{
				image = leftCut;
			}
			else
			{
				if(spriteNum == 1 )
				{
					image = left1;
				}
				if(spriteNum == 2 )
				{
					image = left2;
				}
			}
			
			break;
		case "right":
			if(gp.mouseIndex == 1)
			{
				image = rightCut;
			}
			else
			{
				if(spriteNum == 1 )
				{
					image = right1;
				}
				if(spriteNum == 2 )
				{
					image = right2;
				}
			}
			
			break;
		case "Death":
			image = Death;
			System.out.println("Cut: " + gp.cut);
			System.out.println("Point: " + gp.points);
			gp.lose = true;
			gp.setGameThread();
			break;
		}
		System.out.println(imageWidth + " " + imageHeigth);
		TileScaler();
		g2.drawImage(image, imageScreenX, imageScreenY, imageWidth, imageHeigth, null);
	}
}
