package com.flow.snesde.uilib.generic;

import org.eclipse.swt.graphics.Color;

public class Uilib {
	/**
	 * This will take both colors and create a mixture out of them with the blend 
	 * being an int between 0 and 100 deciding the weight. The parent composite is 
	 * needed to generate the new color.
	 * 
	 * @param color1 the first color
	 * @param color2 the second color
	 * @param blend blend integer 0 .. 100
	 * @param parent the parent UilibComposite
	 * @return the new Color
	 */
	public static Color createMixedColor(Color color1, Color color2, int blend, UilibComposite parent)
	{
		// draw color
		int rl, gl, bl, rd, gd, bd;
		
		rl = color1.getRed();
		gl = color1.getGreen();
		bl = color1.getBlue();
		rd = color2.getRed();
		gd = color2.getGreen();
		bd = color2.getBlue();
		
		if (blend < 0)
		{
			return color1;
		}
		else if (blend > 100)
		{
			return color2;
		}
		
		int percent1 = blend;
		int percent2 = 100 - blend;
		
		double r1, g1, b1;
		r1 = ((double)rl * (double)percent1)/100;
		g1 = ((double)gl * (double)percent1)/100;
		b1 = ((double)bl * (double)percent1)/100;
		double r2, g2, b2;
		r2 = ((double)rd * (double)percent2)/100;
		g2 = ((double)gd * (double)percent2)/100;
		b2 = ((double)bd * (double)percent2)/100;
		
		int r,g,b;
		r = ((int)(r1+r2))%255;
		g = ((int)(g1+g2))%255;
		b = ((int)(b1+b2))%255;
		
		return parent.createColor(r, g, b);
	}
	
	/**
	 * Returns a slightly lighter color (by the value i passed than the original
	 * 
	 * @param c the original color
	 * @param i the value to increase by
	 * @param parent the parent composite
	 * @return the new color
	 */
	public static Color createLighterColor(Color c, int i, UilibComposite parent)
	{
		// draw color
		int rl, gl, bl;
		rl = c.getRed() + i;
		if (rl > 255) rl = 255;
		gl = c.getGreen() + i;
		if (gl > 255) gl = 255;
		bl = c.getBlue() + i;
		if (bl > 255) bl = 255;
		return parent.createColor(rl, gl, bl);
	}
	
	/**
	 * Returns a slightly darker color (by the value i passed than the original
	 * 
	 * @param c the original color
	 * @param i the value to decrease by
	 * @param parent the parent composite
	 * @return the new color
	 */
	public static Color createDarkerColor(Color c, int i, UilibComposite parent)
	{
		// draw color
		int rl, gl, bl;
		rl = c.getRed() - i;
		if (rl < 0) rl = 0;
		gl = c.getGreen() - i;
		if (gl < 0) gl = 0;
		bl = c.getBlue() - i;
		if (bl < 0) bl = 0;
		return parent.createColor(rl, gl, bl);
	}
}
