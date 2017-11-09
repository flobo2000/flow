package com.flow.snesde.uilib;

import java.awt.Toolkit;
import java.io.IOException;
import java.math.BigInteger;

import javax.activation.DataHandler;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class TestSWTClipboard
{

	public static void main(String[] args)
	{
		getClipboardContent();
		setClipboardContent();


		
	}

	private static void getClipboardContent() 
	{
		final Display display = new Display();
		final Clipboard clipboard = new Clipboard(display);
		
		ImageData imageData = (ImageData)clipboard.getContents(ImageTransfer.getInstance());
		
		if (imageData != null) 
		{
			int height = imageData.height;
			int width = imageData.width;
			for (int row = 0; row < height; row++)
			{
				for (int col = 0; col < width; col++)
				{
					String binString = Integer
							.toBinaryString(imageData.getPixel(col, row));
					while (binString.length() != 32)
					{
						binString = "0" + binString;
					}
					int b = Integer.parseInt(
							(binString.substring(
									binString.length() - 8,
									binString.length())), 2);
					int g = Integer.parseInt((binString.substring(
							binString.length() - 16,
							binString.length() - 8)), 2);
					int r = Integer.parseInt((binString.substring(
							binString.length() - 24,
							binString.length() - 16)), 2);
					int a = imageData.getAlpha(col, row);
					boolean transparent = false;
					if (a < 127)
					{
						transparent = true;
					}
					System.out.println("#" + col + "|" + row + ": rgb "
							+ r + "|" + g + "|" + b + " alpha: " + a);
				}
			}
		}
	}
	
	private static void setClipboardContent()
	{
		//create ImageData
		
				int width = 8;
				int height = 8;
				ImageData img = new ImageData(width, height, 32, new PaletteData(new RGB[] {new RGB(255,0,0), new RGB(255,255,255), new RGB(0,0,255)}));
				for (int row = 0; row < height; row++)
				{
					for (int col = 0; col < width; col++)
					{
						if (row==0 && col==0)
						{
//							img.setRGB(col, row, Integer.parseInt("11111111111111110000000000000000", 2));
							img.setPixel(col, row, 0);
							System.out.println("set first pixel");
						}
						else if (row==0 && col==1)
						{
//							img.setRGB(col, row, Integer.parseInt("01111111111111110000000000000000", 2));
							img.setPixel(col, row, 0);
							img.setAlpha(col, row, 127);
							System.out.println("set second pixel");
						}
						else if (row==7 && col==7)
						{
//							img.setRGB(col, row, Integer.parseInt("11111111000000000000000011111111", 2));
							img.setPixel(col, row, 2);
							System.out.println("set last pixel");
						}
						else
						{
							img.setPixel(col, row, 1);
							System.out.println("set other pixel");
						}
					}
				}
				
				
				//set Image
				ImageTransfer imageTransfer = ImageTransfer.getInstance();
				TextTransfer textTransfer = TextTransfer.getInstance();

			    //set clipboard
				final Display display = new Display();
				final Clipboard clipboard = new Clipboard(display);
				clipboard.setContents(new Object[]{img},
						new Transfer[]{imageTransfer});
	}
}
