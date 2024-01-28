package main;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener 
{
	GamePanel gp;
	
	
	public MouseListener(GamePanel gp)
	{
		this.gp = gp;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		gp.mouseIndex = 1;
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
	
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{

	}
}
