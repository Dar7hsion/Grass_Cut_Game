package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Wheat extends ObjectFrame
{
	public OBJ_Wheat()
	{
		name = "Wheat";
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/wheat.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
