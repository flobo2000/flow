/**
 * 
 */
package com.flow.snesde.uilib.generic;

import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * @author flo
 */
public abstract class UilibComposite extends Canvas
{
	// debug flag -> will draw debug stuff if wanted
	protected boolean debug = false;

	/**
	 * a vector containing all SWT colors (they's all be disposed at the end of
	 * the widgets lifecycle automatically
	 */
	private final HashSet<Color> internal_colors = new HashSet<Color>();
	private final HashSet<Font> fonts = new HashSet<Font>();

	private String debugString = "";

	/**
	 * @param parent
	 *            the parent composite to add this one to
	 * @param style
	 *            the SWT style constant
	 */
	public UilibComposite(final Composite parent, final int style)
	{
		super(parent, style | SWT.NO_BACKGROUND);

		addDisposeListener(new DisposeListener()
		{
			/**
			 * Dispose all colors used within this widget
			 */
			@Override
			public void widgetDisposed(final DisposeEvent e)
			{
				for (Color c : UilibComposite.this.internal_colors)
				{
					c.dispose();
				}
				for (Font f : UilibComposite.this.fonts)
				{
					f.dispose();
				}
			}
		});

		addPaintListener(new PaintListener()
		{
			@Override
			public void paintControl(final PaintEvent e)
			{
				Point p = getSize();
				int width = p.x;
				int height = p.y;

				drawContent(e, e.gc, width, height);
			}

		});
	}

	/**
	 * This method is used to create new SWT colors for this widget. They're
	 * disposed automatically once the widget is disposed.
	 * 
	 * @param r
	 *            the red value (0-255)
	 * @param g
	 *            the green value (0-255)
	 * @param b
	 *            the blue value (0-255)
	 * @return the SWT color object created
	 */
	public Color createColor(final int r, final int g, final int b)
	{
		Color c = new Color(null, r, g, b);
		if (!this.internal_colors.contains(c))
		{
			this.internal_colors.add(c);
		}
		return c;
	}

	/**
	 * Creates an SWT color out of a non native color to calculate with
	 * 
	 * @param s
	 *            the non native color
	 * @return the SWT color
	 */
	public Color createColor(NonNativeColor s)
	{
		Color c = new Color(null, s.getR() * 8, s.getG() * 8, s.getB() * 8);
		if (!this.internal_colors.contains(c))
		{
			this.internal_colors.add(c);
		}
		return c;
	}

	/**
	 * creates a nonnative color out of an SWT color
	 * 
	 * @param c
	 *            the source color
	 * @return the nonnative color
	 */
	public NonNativeColor createNonNativeColor(Color c)
	{
		int r = Math.round(c.getRed() / 8);
		int g = Math.round(c.getGreen() / 8);
		int b = Math.round(c.getBlue() / 8);
		if (r > 31)
		{
			r = 31;
		}
		if (g > 31)
		{
			g = 31;
		}
		if (b > 31)
		{
			b = 31;
		}
		return new NonNativeColor(r, g, b);
	}

	/**
	 * create a native font resource which is properly disposed off in the end
	 * 
	 * @param device
	 * @param name
	 * @param height
	 * @param style
	 * @return
	 */
	public Font createFont(final Device device, final String name,
			final int height, final int style)
	{
		Font f = new Font(device, name, height, style);
		if (!this.fonts.contains(f))
		{
			this.fonts.add(f);
		}
		return f;
	}

	/**
	 * draw the content of the widget
	 * 
	 * @param e
	 * @param gc
	 * @param width
	 * @param height
	 */
	public void drawContent(final PaintEvent e, GC gc, int width, int height)
	{
		//implement buffer so we have a double buffered widget (no flickering at fast redraw)
		Image bufferImage = new Image(getDisplay(),width,height); 
		GC bufferGC = new GC(bufferImage); 

		bufferGC.setBackground(getBackground());
		bufferGC.fillRectangle(0, 0, width, height);
		
		//draw the actual widget
		drawWidget(e, bufferGC, width, height);
		if (debug)
		{
			//draw debug stuff if any
			drawDebugContent(e, bufferGC, width, height);
		}
		
		//draw the buffer all at once
		gc.drawImage(bufferImage,0,0); 
		bufferImage.dispose(); 
	}

	/**
	 * draw the actual widget content
	 * 
	 * @param e the draw event
	 * @param gc the graphics context to draw through
	 * @param width the widgets width
	 * @param height the widgets height
	 */
	public abstract void drawWidget(final PaintEvent e, GC gc, int width,
			int height);

	private void drawDebugContent(final PaintEvent e, GC gc, int width,
			int height)
	{
		drawDebug(e, gc, width, height);
		drawDebugStrings(e, gc, width, height);
	}

	/**
	 * this method should be overwritten by the corresponding subclass if anything should
	 * be drawn on top of the widget in case of debugging
	 * 
	 * @param e
	 * @param gc
	 * @param width
	 * @param height
	 */
	public void drawDebug(final PaintEvent e, GC gc, int width, int height)
	{
		// TODO: this method should be overwritten if debug graphics should be
		// drawn
	}

	/**
	 * this method will draw something on top of the widget (eg. variable content)
	 * 
	 * @param e
	 * @param gc
	 * @param width
	 * @param height
	 */
	private void drawDebugStrings(final PaintEvent e, GC gc, int width,
			int height)
	{
		gc.setForeground(createColor(0, 0, 0));
		gc.setBackground(createColor(255, 255, 255));
		gc.drawText(debugString, 0, 0);
	}

	/**
	 * sets the debug string
	 * 
	 * @param s
	 */
	public void setDebugString(String s)
	{
		this.debugString = s;
	}
}
