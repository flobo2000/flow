package com.flow.snesde.uilib.generic;

import java.util.Random;

public class InternalTile
{
	private final int[][] pixels = new int[8][8];

	/**
	 * initializes the array
	 */
	public InternalTile()
	{
		Random randGen = new Random(System.currentTimeMillis());

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				pixels[i][j] = Math.abs(randGen.nextInt()) % 8;
			}
		}
	}

	/**
	 * get color index at the position specified
	 * 
	 * @param column
	 *            the pixel column
	 * @param row
	 *            the pixel row
	 * @return the color index
	 */
	public int getColorAt(int column, int row)
	{
		return pixels[column][row];
	}

	/**
	 * set color index at the position specified
	 * 
	 * @param column
	 *            the pixel column
	 * @param row
	 *            the pixel row
	 * @return the color index
	 */
	public void setColorAt(int column, int row, int index)
	{
		pixels[column][row] = index;
	}
}
