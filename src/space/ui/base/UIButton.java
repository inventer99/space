package space.ui.base;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class UIButton extends UIComp
{
	private boolean state = false;
	private String text;
	
	public UIButton(String text)
	{
		this.text = text;
	}
	
	public UIButton()
	{
		this("");
	}
	
	@Override
	public void renderComp(Graphics2D g) 
	{
		Image i = new BufferedImage(this.bounds.width, this.bounds.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D ig = (Graphics2D) i.getGraphics();
		{
			ig.setColor(this.background);
			ig.fillRect(
					0, 
					0,
					this.bounds.width, 
					this.bounds.height
			);
			
			if(this.border != null)
				this.border.Render(this, ig);
			
			ig.setColor(foreground);
			ig.drawString(
					this.text, 
					3 + (this.border != null ? this.border.getWidth() : 0), 
					this.bounds.height - (3 + (this.border != null ? this.border.getWidth() : 0))
			);
		}
		g.drawImage(i, this.bounds.x, this.bounds.y, null);
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	public boolean getState() 
	{
		return state;
	}
}
