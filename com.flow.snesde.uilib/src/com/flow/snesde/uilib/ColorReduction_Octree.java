package com.flow.snesde.uilib;

import java.awt.Image;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.util.ColorReducer;

public class ColorReduction_Octree {

	public static void main(String[] args) throws JimiException {
		String input = "/home/flo/kirby.jpg";
		String output_octree_noDither = "/home/flo/kirby_octree_nodither.png";
		String output_octree_dither = "/home/flo/kirby_octree_dither.png";
		
		Image image = Jimi.getImage(input);
		image = reduceColors(image, 16, false);
		Jimi.putImage(image, output_octree_noDither);
		
		Image image2 = Jimi.getImage(input);
		image2 = reduceColors(image2, 16, true);
		Jimi.putImage(image2, output_octree_dither);
		
		
		System.exit(0);
	}
	
	public static Image reduceColors(Image image, int colors, boolean dither)
	{
		ColorReducer reducer = new ColorReducer(colors, dither);
		Image img = null;
		try {
				img = reducer.getColorReducedImage(image);
		}
		catch (JimiException e) {
			System.out.println("Error color reducing/dithering");
		}
		return img;
	}

}
