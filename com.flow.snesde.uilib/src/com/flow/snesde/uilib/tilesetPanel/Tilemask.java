package com.flow.snesde.uilib.tilesetPanel;

public class Tilemask
{
	// the actual content of the pixel mask
	private String pixelString = null;
	// the number of pixels to move to the right until no more set ones follow
	private int minWidth = -1;

	/**
	 * Constructor for creating a tilemask object. Should be passed a 64
	 * character long string. The binary string data. The string is 64
	 * characters long representing a row by row encoded 8x8 pixel tile. The
	 * character space represents an unset pixel. The character x represents a
	 * set pixel
	 */
	public Tilemask(String binaryString)
	{
		this.pixelString = binaryString;
		// make sure string is 64 characters long
		while (pixelString.length() < 64)
		{
			pixelString += ' ';
		}
		if (pixelString.length() > 64)
		{
			pixelString = pixelString.substring(0, 64);
		}

		// find out how wide the mask is
		int lastSetPixelCol = -1;
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				char c = pixelString.charAt((row * 8) + col);
				if (c != ' ' && col > lastSetPixelCol)
				{
					lastSetPixelCol = col;
				}
			}
		}
		if (lastSetPixelCol == -1)
		{
			minWidth = 5;
		}
		else
		{
			minWidth = lastSetPixelCol + 1;
		}
	}

	/**
	 * returns the binary string data. The string is 64 characters long
	 * representing a row by row encoded 8x8 pixel tile. The character space
	 * represents an unset pixel. The character x represents a set pixel
	 * 
	 * @return the binary string
	 */
	public String getBinaryData()
	{
		return pixelString;
	}

	/**
	 * returns the minimum width of the tilemask (for creating concatenated
	 * vvw-strings)
	 * 
	 * @return the minimum width
	 */
	public int getMinWidth()
	{
		return this.minWidth;
	}

	/**
	 * checks if the pixel at the index is set
	 * 
	 * @param i
	 *            index
	 * @return true if set, false otherwise
	 */
	public boolean pixelSetAt(int i)
	{
		if (pixelString.charAt(i) != ' ')
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if the pixel at the index is special (which means neither blank
	 * nor an x)
	 * 
	 * @param i
	 *            index
	 * @return true if special, false otherwise
	 */
	public boolean pixelSpecialAt(int i)
	{
		if (pixelString.charAt(i) != ' ' && pixelString.charAt(i) != 'x')
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if the pixel above the index is set
	 * 
	 * @param i
	 *            index
	 * @return true if set, false otherwise
	 */
	public boolean pixelSetAbove(int i)
	{
		i = i - 8;
		// pixel is above the first tile row -> always not set
		if (i < 0)
		{
			return false;
		}
		else if (pixelString.charAt(i) != ' ')
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if the pixel below the index is set
	 * 
	 * @param i
	 *            index
	 * @return true if set, false otherwise
	 */
	public boolean pixelSetBelow(int i)
	{
		i = i + 8;
		// pixel is in the 9th row (that is below our tile) -> alyways not set
		if (i > 63)
		{
			return false;
		}
		else if (pixelString.charAt(i) != ' ')
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if the pixel to the left of the index is set
	 * 
	 * @param i
	 *            index
	 * @return true if set, false otherwise
	 */
	public boolean pixelSetOnTheLeft(int i)
	{
		// pixel on the far left of the tile -> always not set
		if (i % 8 == 0)
		{
			return false;
		}
		if (pixelString.charAt(i - 1) != ' ')
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if the pixel to the right of the index is set
	 * 
	 * @param i
	 *            index
	 * @return true if set, false otherwise
	 */
	public boolean pixelSetOnTheRight(int i)
	{
		// pixel is on the far right of the tile -> always not set
		if (i % 8 == 7)
		{
			return false;
		}
		if (pixelString.charAt(i + 1) != ' ')
		{
			return true;
		}
		return false;
	}
}
