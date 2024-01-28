package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Potion extends ObjectFrame
{
	public OBJ_Potion()
	{
		name = "Potion";
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
