/**
 * 
 */
package com.flow.snesde.uilib.singleColorSelectionPanel;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.generic.UilibComposite;

/**
 * @author flo
 */
public class SingleColorSelectionPanel extends UilibComposite
{

	/**
	 * the single color to use for this panel
	 */
	NonNativeColor color = new NonNativeColor(0, 0, 0);

	/**
	 * the index of the color
	 */
	int i = 0;

	/**
	 * @param parent
	 * @param style
	 */
	public SingleColorSelectionPanel(final Composite parent, final int style)
	{
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawWidget(final PaintEvent e, final GC gc, final int width,
			final int height)
	{
		Color c = createColor(color);
		gc.setBackground(c);
		gc.setForeground(createColor(0, 0, 0));
		gc.fillRectangle(0, 0, width, height);
		gc.drawRectangle(0, 0, width - 1, height - 1);
		gc.drawString("" + i, 5, 5);
	}

	public NonNativeColor getColor()
	{
		return color;
	}

	public void setColor(NonNativeColor color)
	{
		this.color = color;
		redraw();
	}

	public int getI()
	{
		return i;
	}

	public void setI(int i)
	{
		this.i = i;
		redraw();
	}

}
