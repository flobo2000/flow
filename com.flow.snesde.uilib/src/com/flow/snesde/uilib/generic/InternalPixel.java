package com.flow.snesde.uilib.generic;

public class InternalPixel
{
	int row, col, index;

	/**
	 * creates the pixel
	 * 
	 * @param col
	 *            the x position of the pixel
	 * @param row
	 *            the y position of the pixel
	 * @param index
	 *            the color index
	 */
	public InternalPixel(int col, int row, int index)
	{
		this.col = col;
		this.row = row;
		this.index = index;
	}

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getCol()
	{
		return col;
	}

	public void setCol(int col)
	{
		this.col = col;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}
}
