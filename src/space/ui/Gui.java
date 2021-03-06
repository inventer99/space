package space.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import pixgen.PixGen;
import pixgen.Settings;
import pixgen.Updateable;
import space.main.Main;

public class Gui implements Updateable
{
	public static final Color colorBack = new Color(89, 89, 89, 100);
	public static final Color colorEdge = new Color(153, 0, 153);
	public static final Color colorHealth = new Color(191, 0, 0);
	public static final Color colorShield = new Color(48, 191, 191);
	public static final Color colorPower = new Color(230, 230, 0);
	public static final Color colorText = new Color(255, 255, 255);
	
	private ProgressBar healthBar = new ProgressBar(2, Settings.HEIGHT - 153, colorHealth);
	private ProgressBar shieldBar = new ProgressBar(25, Settings.HEIGHT - 153, colorShield);
	private ProgressBar powerBar = new ProgressBar(Settings.WIDTH - 23, Settings.HEIGHT - 153, colorPower);
	private AbilityBar abilityBar = new AbilityBar(Settings.WIDTH - 61, Settings.HEIGHT - 153);
	private MiniMap miniMap = new MiniMap(2, 2);
	private OptionButton optionButton = new OptionButton(Settings.WIDTH - 63, 2);
	private OptionMenu optionMenu = new OptionMenu(Settings.WIDTH - 78, 25);
	private OptionWindow optionWindow = new OptionWindow(Math.round((Settings.WIDTH / 2) - 200), Math.round((Settings.HEIGHT / 2) - 150));
	private ChatWindow chatWindow = new ChatWindow(48, Settings.HEIGHT - 202);
	
	public static boolean drawOptionMenu = false;
	public static boolean drawOptionWindow = false;
	public static boolean drawChatWindow = false;
	
	public Gui()
	{
		PixGen.addUpdateableObject(this);
	}
	
	private static boolean mStillDown = false;
	
	@Override
	public void update()
	{
//		EventCreator.updateMouse();
		
		if(PixGen.getInputManager().mouseDown(1) && !mStillDown)
		{
			// Pressed
			optionButton.mousePressed();
			optionMenu.mousePressed();
			optionWindow.mousePressed();
			chatWindow.mousePressed();
			
			mStillDown = true;
		}
		if(!PixGen.getInputManager().mouseDown(1) && mStillDown)
		{
			// Released
			optionWindow.mouseReleased();
			chatWindow.mouseReleased();
			
			mStillDown = false;
		}
		
		if(
				PixGen.getInputManager().mouseX() != PixGen.getInputManager().lastMouseX() &&
				PixGen.getInputManager().mouseY() != PixGen.getInputManager().lastMouseY()	
		)
		{
			if(mStillDown) 
			{
				// Dragged
				optionWindow.mouseDragged();
				chatWindow.mouseReleased();
			}
			else
			{
				// Moved
			}
			
			PixGen.getInputManager().equalizeMouse();
		}
	}
	
	@Override
	public void render(Graphics2D g)
	{	
		healthBar.render(((Main) PixGen.getGame()).getPlayer().getHealth(), g);
		
		shieldBar.render(((Main) PixGen.getGame()).getPlayer().getShield(), g);
		
		powerBar.render(((Main) PixGen.getGame()).getPlayer().getPower(), g);
		
		abilityBar.render(g);
		
		miniMap.render(g);
		
		optionButton.render(g);
		
		optionMenu.render(g);
		
		optionWindow.render(g);
		
		chatWindow.render(g);
	}
}
