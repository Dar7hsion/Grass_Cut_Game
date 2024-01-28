package main;

public class Points 
{
	
	GamePanel gp;
	int healthC;
	
	public Points(GamePanel gp)
	{
		this.gp = gp;
		healthC = gp.player.Health;
		gp.pointMod = 1;
		gp.pointVal = 1;
		gp.points = 0;
		gp.cut = 0;
	}
	
	public void setPointMod()
	{
		int pointModC = 4;
		
		if(gp.cutIndex == true)
		{
			if(gp.pointMod < pointModC)
			{
				gp.pointMod++;
			}
		}
		else if(gp.cutIndex == false)
		{
			if(gp.pointMod > 1)
			{
				gp.pointMod--;
			}
			
		}
	}
	
	public void setPointValue()
	{
		if(healthC == gp.player.Health)
		{
			gp.pointVal++;
			if(gp.pointVal > 5)
			{
				gp.pointVal = 5;
			}
		}
		else
		{
			gp.pointVal = 1;
			healthC = gp.player.Health;
		}
		
	}
	
	public void setPoint()
	{
		setPointValue();
		gp.points += (gp.pointVal * gp.pointMod);
		
	}
	
	public void setCut()
	{
		gp.cut++;
	}
	
	
}
