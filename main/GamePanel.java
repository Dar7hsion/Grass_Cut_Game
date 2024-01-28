package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import background.BackgroundEngine;
import object.ObjectFrame;
import player.Player;
import player.TileScaler;

public class GamePanel extends JPanel implements Runnable
{
	final int originalTileSize = 16; 
	final int scale = 3; 
	public int tileSize = originalTileSize * scale;
	public final int window_Col = 16; 
	public final int window_Row = 12; 
	public final int windowWidth = tileSize * window_Col; 
	public final int windowHeight = tileSize * window_Row; 
	public final int world_Col = 50;
	public final int world_Row = 50;
	public final int worldWidth = tileSize * world_Col;
	public final int worldHeight = tileSize * world_Row;
	int FPS = 60;
	public int mouseIndex, pointVal, pointMod, points, cut;
	public boolean cutIndex;
	public boolean win, lose = false;
	BackgroundEngine BE = new BackgroundEngine(this);
	MouseListener ML = new MouseListener(this);
	KeyHandler KH = new KeyHandler();
	Thread gameThread; 
	public Collision cChecker = new Collision(this);
	public ObjectBuilder aSetter = new ObjectBuilder(this);
	public GUI gui = new GUI(this);
	public Player player = new Player(this, KH, ML);
	public ObjectFrame obj[] = new ObjectFrame[2000];
	public Points p = new Points(this);
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(KH);
		this.setFocusable(true);
	}
	
	public void setUpGame()
	{
		aSetter.setObjects();
	}
	
	public void startGameThread() 
	{
		gameThread = new Thread(this); 
		gameThread.start();
	}

	public void run()
	{
		double drawInterval = 1000000000/FPS; 
		double delta = 0;
		int beta = 0;
		int betaC = 7;
		long lastTime = System.nanoTime();
		long currentTime;
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				if(beta == betaC)
				{
					p.setPointMod();
					cutIndex = false;
					beta = 0;
				}
				else
				{
					beta++;
				}
				repaint();
				delta--;
			}	
		}
	}
	public void update() 
	{	
		player.update();
	}
	
	public void setGameThread()
	{
		gameThread = null;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; 
		BE.draw((Graphics2D) g2);
		
		for(int i = 0; i < obj.length; i++)
		{
			if(obj[i] != null)
			{
				obj[i].draw((Graphics2D)g2, this);
			}
		}
		
		player.draw((Graphics2D) g2);
		
		gui.draw((Graphics2D)g2);
		g2.dispose();
	}
}
