package com.flow.snesde.tileeditor;

import java.util.Vector;

import org.eclipse.emf.common.util.EList;

import com.flow.snesde.uilib.generic.InternalPixel;
import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.tilesetPanel.Tilemask;
import com.flow.snesde.uilib.tilesetPanel.TilemaskFactory;

import palette.Color;
import palette.Palette;
import tileset.Tileset;

public class DrawingLogic
{
	/**
	 * Totally unoptimized nearest neighbor implementation with exponential
	 * complexity. Should be improved at one point to use a tree structure to
	 * find the best color more efficiently
	 * 
	 * @param c
	 *            the color to search the nearest match for
	 * @param pal
	 *            the palette to search in
	 * @return the color index of the nearest match
	 */
	public static int getNearestNeighbor(NonNativeColor c, Palette pal)
	{
		double minDistance = 1000000000000.0;
		int bestIndex = -1;
		int index = 0;
		EList<Color> colors = pal.getColors();
		for (Color col : colors)
		{
			NonNativeColor c2 = new NonNativeColor(col.getR(), col.getG(), col.getB());
			double d = getColorDistance(c, c2);
			if (d < minDistance)
			{
				minDistance = d;
				bestIndex = index;
			}
			index++;
		}
		return bestIndex;
	}

	/**
	 * Returns the euklidean distance between two color values
	 * 
	 * @param c1
	 *            first color
	 * @param c2
	 *            second color
	 * @return distance (double)
	 */
	private static double getColorDistance(NonNativeColor c1, NonNativeColor c2)
	{
		int r1 = c1.getR();
		int r2 = c2.getR();
		int g1 = c1.getG();
		int g2 = c2.getG();
		int b1 = c1.getB();
		int b2 = c2.getB();

		return Math.sqrt(Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
	}

	/**
	 * draws a line of variable thickness and stuff
	 * 
	 * @param startCol
	 *            start point of the line
	 * @param startRow
	 *            start point of the line
	 * @param endCol
	 *            end point of the line
	 * @param endRow
	 *            end point of the line
	 * @param leftColorIndex
	 *            left color
	 * @param rightColorIndex
	 *            right color
	 * @param round
	 *            true if a round shape should be drawn, false for square
	 * @param size
	 *            integer 1..8
	 * @param twoColors
	 *            true if two color more should be used, false otherwise
	 * @return the buffer of pixels
	 */
	public static Vector<InternalPixel> drawLine(int startCol, int startRow, int endCol, int endRow, int leftColorIndex,
	        int rightColorIndex, boolean round, int size, boolean twoColors, boolean leftClick)
	{
		// get the proper mask for the given settings
		Tilemask mask = TilemaskFactory.getMaskForRound(1, twoColors);
		if (round)
		{
			mask = TilemaskFactory.getMaskForRound(size, twoColors);
		}
		else
		{
			mask = TilemaskFactory.getMaskForSquare(size, twoColors);
		}

		// get proper starting points as the mask is 8x8 pixels in size and we
		// always start in the middle
		startCol -= 4;
		endCol -= 4;
		startRow -= 3;
		endRow -= 3;

		return drawLineForMask(mask, startCol, startRow, endCol, endRow, leftColorIndex, rightColorIndex, leftClick,
		        twoColors);
	}

	/**
	 * draws a line with the given mask from start to end
	 * 
	 * @param mask
	 *            the mask to be used
	 * @param startCol
	 *            the start col
	 * @param startRow
	 *            the start row
	 * @param endCol
	 *            the end col
	 * @param endRow
	 *            the end row
	 * @param leftColorIndex
	 *            the left color
	 * @param rightColorIndex
	 *            the right color
	 * @param leftClick
	 *            true if the click was left
	 * @param twoColorMode
	 *            true if two colors should be drawn
	 * @return the pixels
	 */
	private static Vector<InternalPixel> drawLineForMask(Tilemask mask, int startCol, int startRow, int endCol,
	        int endRow, int leftColorIndex, int rightColorIndex, boolean leftClick, boolean twoColorMode)
	{
		Vector<InternalPixel> ret = new Vector<InternalPixel>();
		if (twoColorMode)
		{
			// go through mask line by line and draw lines for left color
			for (int col = 0; col < 8; col++)
			{
				for (int row = 0; row < 8; row++)
				{
					int color = -1;
					if (mask.pixelSetAt(row * 8 + col))
					{
						color = leftColorIndex;
					}
					if (mask.pixelSpecialAt(row * 8 + col))
					{
						color = rightColorIndex;
					}
					if (color == leftColorIndex)
					{
						if (!leftClick)
						{
							color = rightColorIndex;
						}
						ret = drawLine(startCol + col, startRow + row, endCol + col, endRow + row, color, ret);
					}
				}
			}
			// go through mask line by line and draw lines with right color
			for (int col = 0; col < 8; col++)
			{
				for (int row = 0; row < 8; row++)
				{
					int color = -1;
					if (mask.pixelSetAt(row * 8 + col))
					{
						color = leftColorIndex;
					}
					if (mask.pixelSpecialAt(row * 8 + col))
					{
						color = rightColorIndex;
					}
					if (color == rightColorIndex)
					{
						if (!leftClick)
						{
							color = leftColorIndex;
						}
						ret = drawLine(startCol + col, startRow + row, endCol + col, endRow + row, color, ret);
					}
				}
			}
		}
		else
		{
			// go through mask line by line and draw lines with selected color
			for (int col = 0; col < 8; col++)
			{
				for (int row = 0; row < 8; row++)
				{
					int color = -1;
					if (mask.pixelSetAt(row * 8 + col))
					{
						if (leftClick)
						{
							color = leftColorIndex;
						}
						else
						{
							color = rightColorIndex;
						}
					}
					if (color >= 0)
					{
						ret = drawLine(startCol + col, startRow + row, endCol + col, endRow + row, color, ret);
					}
				}
			}
		}
		return ret;
	}

	/**
	 * bresenhams line algorithm taken from
	 * http://tech-algorithm.com/articles/drawing
	 * -line-using-bresenham-algorithm/
	 * 
	 * @param startCol
	 *            start col
	 * @param startRow
	 *            start row
	 * @param endCol
	 *            end col
	 * @param endRow
	 *            end row
	 * @param colorIndex
	 *            the index of the color to set
	 * @return the set of pixels to set
	 */
	private static Vector<InternalPixel> drawLine(int startCol, int startRow, int endCol, int endRow, int colorIndex,
	        Vector<InternalPixel> ret)
	{
		int w = endCol - startCol;
		int h = endRow - startRow;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (w < 0)
			dx1 = -1;
		else if (w > 0) dx1 = 1;
		if (h < 0)
			dy1 = -1;
		else if (h > 0) dy1 = 1;
		if (w < 0)
			dx2 = -1;
		else if (w > 0) dx2 = 1;
		int longest = Math.abs(w);
		int shortest = Math.abs(h);
		if (!(longest > shortest))
		{
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0) dy2 = 1;
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++)
		{
			ret.add(new InternalPixel(startCol, startRow, colorIndex));
			numerator += shortest;
			if (!(numerator < longest))
			{
				numerator -= longest;
				startCol += dx1;
				startRow += dy1;
			}
			else
			{
				startCol += dx2;
				startRow += dy2;
			}
		}
		return ret;
	}

	/**
	 * heavily modified version of bresenhams ellipse drawing algorithm taken
	 * from https://de.wikipedia.org/wiki/Bresenham-Algorithmus#Ellipsen
	 * 
	 * @param startCol
	 *            starting column of the ellipse
	 * @param startRow
	 *            starting row of the ellipse
	 * @param endCol
	 *            ending column of the ellipse
	 * @param endRow
	 *            ending row of the ellipse
	 * @param colorIndex
	 *            color index
	 * @return
	 */
	public static Vector<InternalPixel> drawEllipse(int startCol, int startRow, int endCol, int endRow, int colorIndex)
	{
		Vector<InternalPixel> ret = new Vector<InternalPixel>();
		// draw only one pixel
		if (startCol == endCol && startRow == endRow)
		{
			ret.add(new InternalPixel(startCol, startRow, colorIndex));
			return ret;
		}
		// draw only one line
		if (startCol == endCol || startRow == endRow)
		{
			ret = drawLine(startCol, startRow, endCol, endRow, colorIndex, ret);
			return ret;
		}
		int lowerCol = startCol;
		int lowerRow = startRow;
		int higherCol = endCol;
		int higherRow = endRow;
		if (endCol < startCol)
		{
			lowerCol = endCol;
			higherCol = startCol;
		}
		if (endRow < startRow)
		{
			lowerRow = endRow;
			higherRow = startRow;
		}
		int xm = lowerCol + (int) (((double) higherCol - (double) lowerCol) / 2);
		int ym = lowerRow + (int) (((double) higherRow - (double) lowerRow) / 2);
		int width = (int) (((double) higherCol - (double) lowerCol) / 2);
		int height = (int) (((double) higherRow - (double) lowerRow) / 2);

		int dx = 0;
		int dy = height; /* im I. Quadranten von links oben nach rechts unten */
		long a2 = width * width;
		long b2 = height * height;
		long err = b2 - (2 * height - 1) * a2, e2; /* Fehler im 1. Schritt */
		if (dy >= 0)
		{
			boolean noChangeInThisCycle = false;
			do
			{
				// 1. Quadrant
				ret.add(new InternalPixel(xm + dx, ym + dy, colorIndex));
				// 2. Quadrant
				ret.add(new InternalPixel(xm - dx, ym + dy, colorIndex));
				// 3. Quadrant
				ret.add(new InternalPixel(xm - dx, ym - dy, colorIndex));
				// 4. Quadrant
				ret.add(new InternalPixel(xm + dx, ym - dy, colorIndex));

				e2 = 2 * err;
				noChangeInThisCycle = true;
				if (e2 < (2 * dx + 1) * b2)
				{
					noChangeInThisCycle = false;
					dx++;
					err += (2 * dx + 1) * b2;
				}
				if (e2 > -(2 * dy - 1) * a2)
				{
					noChangeInThisCycle = false;
					dy--;
					err -= (2 * dy - 1) * a2;
				}
				if (noChangeInThisCycle)
				{
					break;
				}
			}
			while (dy >= 0);
		}

		while (dx++ < width)
		{
			// fehlerhafter Abbruch bei flachen Ellipsen (b=1)
			// -> Spitze der Ellipse vollenden
			ret.add(new InternalPixel(xm + dx, ym, colorIndex));
			ret.add(new InternalPixel(xm - dx, ym, colorIndex));
		}
		return ret;
	}

	/**
	 * draws the rectangle onto the vector
	 * 
	 * @param startCol
	 * @param startRow
	 * @param endCol
	 * @param endRow
	 * @param colorIndex
	 * @return
	 */
	public static Vector<InternalPixel> drawRectangle(int startCol, int startRow, int endCol, int endRow,
	        int colorIndex)
	{
		int lowerCol = startCol;
		int lowerRow = startRow;
		int higherCol = endCol;
		int higherRow = endRow;
		if (endCol < startCol)
		{
			lowerCol = endCol;
			higherCol = startCol;
		}
		if (endRow < startRow)
		{
			lowerRow = endRow;
			higherRow = startRow;
		}

		Vector<InternalPixel> ret = new Vector<InternalPixel>();

		// fill rectangle shape
		for (int col = lowerCol; col <= higherCol; col++)
		{
			ret.add(new InternalPixel(col, lowerRow, colorIndex));
			ret.add(new InternalPixel(col, higherRow, colorIndex));
		}
		for (int row = lowerRow; row <= higherRow; row++)
		{
			ret.add(new InternalPixel(lowerCol, row, colorIndex));
			ret.add(new InternalPixel(higherCol, row, colorIndex));
		}

		return ret;
	}

	/**
	 * draws the rectangle onto the vector
	 * 
	 * @param startCol
	 * @param startRow
	 * @param endCol
	 * @param endRow
	 * @param colorIndex
	 * @return
	 */
	public static Vector<InternalPixel> fillRectangle(int startCol, int startRow, int endCol, int endRow,
	        int colorIndex)
	{
		int lowerCol = startCol;
		int lowerRow = startRow;
		int higherCol = endCol;
		int higherRow = endRow;
		if (endCol < startCol)
		{
			lowerCol = endCol;
			higherCol = startCol;
		}
		if (endRow < startRow)
		{
			lowerRow = endRow;
			higherRow = startRow;
		}

		Vector<InternalPixel> ret = new Vector<InternalPixel>();

		// fill rectangle shape
		for (int col = lowerCol; col <= higherCol; col++)
		{
			for (int row = lowerRow; row <= higherRow; row++)
			{
				ret.add(new InternalPixel(col, row, colorIndex));
			}
		}

		return ret;
	}

	/**
	 * This method will go to the starting coordinate (if existing) and fill
	 * surrounding pixels with the same color as the origin
	 * 
	 * @param startGlobalCol
	 * @param startGlobalRow
	 * @param model
	 * @param colorToFillWith
	 * @return
	 */
	public static Vector<InternalPixel> fillPixels(int startGlobalCol, int startGlobalRow, Tileset model,
	        Integer colorToOverwrite, Integer colorToFillWith, Vector<InternalPixel> filledPixels, boolean[][] visited)
	{
		filledPixels.add(new InternalPixel(startGlobalCol, startGlobalRow, colorToFillWith));
		visited[startGlobalCol][startGlobalRow] = true;
		if (pixelExistsAt(startGlobalCol + 1, startGlobalRow, model))
		{
			if (getColorAt(startGlobalCol + 1, startGlobalRow, model) == colorToOverwrite)
			{
				if (!visited[startGlobalCol + 1][startGlobalRow])
				{
					filledPixels = fillPixels(startGlobalCol + 1, startGlobalRow, model, colorToOverwrite,
					        colorToFillWith, filledPixels, visited);
				}
			}
		}
		if (pixelExistsAt(startGlobalCol - 1, startGlobalRow, model))
		{
			if (getColorAt(startGlobalCol - 1, startGlobalRow, model) == colorToOverwrite)
			{
				if (!visited[startGlobalCol - 1][startGlobalRow])
				{
					filledPixels = fillPixels(startGlobalCol - 1, startGlobalRow, model, colorToOverwrite,
					        colorToFillWith, filledPixels, visited);
				}
			}
		}
		if (pixelExistsAt(startGlobalCol, startGlobalRow + 1, model))
		{

			if (getColorAt(startGlobalCol, startGlobalRow + 1, model) == colorToOverwrite)
			{
				if (!visited[startGlobalCol][startGlobalRow + 1])
				{
					filledPixels = fillPixels(startGlobalCol, startGlobalRow + 1, model, colorToOverwrite,
					        colorToFillWith, filledPixels, visited);
				}
			}
		}
		if (pixelExistsAt(startGlobalCol, startGlobalRow - 1, model))
		{
			if (getColorAt(startGlobalCol, startGlobalRow - 1, model) == colorToOverwrite)
			{
				if (!visited[startGlobalCol][startGlobalRow - 1])
				{
					filledPixels = fillPixels(startGlobalCol, startGlobalRow - 1, model, colorToOverwrite,
					        colorToFillWith, filledPixels, visited);
				}
			}
		}
		return filledPixels;
	}

	/**
	 * tests if a pixel at the given position exists or not
	 * 
	 * @param globalPixelColumn
	 * @param globalPixelRow
	 * @param model
	 * @return
	 */
	private static boolean pixelExistsAt(int globalPixelColumn, int globalPixelRow, Tileset model)
	{
		if (globalPixelColumn < 1 || globalPixelRow < 1)
		{
			return false;
		}
		if (globalPixelColumn > (8 * model.getColumns()))
		{
			return false;
		}
		// calculate tile number
		int tileColumn = (int) Math.ceil((double) globalPixelColumn / (double) 8);
		int tileRow = (int) Math.ceil((double) globalPixelRow / (double) 8);
		int tileNumber = (tileRow - 1) * model.getColumns() + tileColumn - 1;

		// calculate pixelcolumn in tile
		int pixelColumn = (globalPixelColumn % 8) - 1;
		if (pixelColumn == -1) pixelColumn = 7;

		// calculate pixelrow in tile
		int pixelRow = (globalPixelRow % 8) - 1;
		if (pixelRow == -1) pixelRow = 7;

		if (model.getTiles().size() > tileNumber && tileNumber >= 0)
		{
			return true;
		}
		return false;
	}

	private static int getColorAt(int globalPixelColumn, int globalPixelRow, Tileset model)
	{
		// calculate tile number
		int tileColumn = (int) Math.ceil((double) globalPixelColumn / (double) 8);
		int tileRow = (int) Math.ceil((double) globalPixelRow / (double) 8);
		int tileNumber = (tileRow - 1) * model.getColumns() + tileColumn - 1;

		// calculate pixelcolumn in tile
		int pixelColumn = (globalPixelColumn % 8) - 1;
		if (pixelColumn == -1) pixelColumn = 7;

		// calculate pixelrow in tile
		int pixelRow = (globalPixelRow % 8) - 1;
		if (pixelRow == -1) pixelRow = 7;

		return model.getTiles().get(tileNumber).getPixelColumns().get(pixelColumn).getPixels().get(pixelRow)
		        .getColorIndex();
	}

	/**
	 * marks the given area from start to end and returns the content as a pixel
	 * array
	 * 
	 * @param startGlobalCol
	 *            start column
	 * @param startGlobalRow
	 *            start row
	 * @param endGlobalCol
	 *            end column
	 * @param endGlobalRow
	 *            end row
	 * @param model
	 *            the model to get the pixel data from
	 * @param bgColor
	 *            the bg color to
	 * @param copyBG
	 * @return
	 */
	public static Vector<InternalPixel> markPixels(int startGlobalCol, int startGlobalRow, int endGlobalCol,
	        int endGlobalRow, Tileset model, int bgColor, boolean copyBG)
	{
		Vector<InternalPixel> ret = new Vector<InternalPixel>();
		int lowerCol = startGlobalCol;
		int higherCol = endGlobalCol;
		int lowerRow = startGlobalRow;
		int higherRow = endGlobalRow;
		if (endGlobalCol < startGlobalCol)
		{
			lowerCol = endGlobalCol;
			higherCol = startGlobalCol;
		}
		if (endGlobalRow < startGlobalRow)
		{
			lowerRow = endGlobalRow;
			higherRow = startGlobalRow;
		}

		// copy stuff into buffer
		for (int col = lowerCol; col <= higherCol; col++)
		{
			for (int row = lowerRow; row <= higherRow; row++)
			{
				if (pixelExistsAt(col, row, model))
				{
					int color = getColorAt(col, row, model);
					if (!copyBG && color == bgColor)
					{
						// don't copy this pixel
					}
					else
					{
						InternalPixel pix = new InternalPixel(col, row, color);
						ret.add(pix);
					}
				}
			}
		}
		return ret;
	}

	public static Vector<InternalPixel> scaleBuffer(Vector<InternalPixel> source, int nobNumber, int xDelta, int yDelta)
	{
		if (xDelta == 0 && yDelta == 0)
		{
			return source;
		}

		int minCol = 99999999, minRow = 99999999, maxCol = -99999999, maxRow = -99999999;
		if (source != null)
		{
			for (InternalPixel p : source)
			{
				if (p.getCol() < minCol)
				{
					minCol = p.getCol();
				}
				if (p.getCol() > maxCol)
				{
					maxCol = p.getCol();
				}
				if (p.getRow() < minRow)
				{
					minRow = p.getRow();
				}
				if (p.getRow() > maxRow)
				{
					maxRow = p.getRow();
				}
			}
		}

		int w1 = maxCol - minCol + 1;
		int w2 = w1 + xDelta;
		int h1 = maxRow - minRow + 1;
		int h2 = h1 + yDelta;

		// create source array for scaling
		int[][] src = new int[w1][h1];
		for (int row = 0; row < h1; row++)
		{
			for (int col = 0; col < w1; col++)
			{
				// default color index = -1
				src[col][row] = -1;
			}
		}

		// transfer pixel values from buffer
		for (InternalPixel p : source)
		{
			src[p.getCol() - minCol][p.getRow() - minRow] = p.getIndex();
		}

		// create output array
		int[][] tempRet = new int[w2][h2];

		// calculate step sizes
		float xStep = ((float) 1 / (float) w2) * w1;
		float yStep = ((float) 1 / (float) h2) * h1;

		// create output vector
		Vector<InternalPixel> ret = new Vector<InternalPixel>();
		for (int row = 0; row < h2; row++)
		{
			for (int col = 0; col < w2; col++)
			{
				int srcCol = (int) Math.round((double) col * (double) xStep);
				if (srcCol < 0)
				{
					srcCol = 0;
				}
				if (srcCol >= w1)
				{
					srcCol = w1 - 1;
				}

				int srcRow = (int) Math.round((double) row * (double) yStep);
				if (srcRow < 0)
				{
					srcRow = 0;
				}
				if (srcRow >= h1)
				{
					srcRow = h1 - 1;
				}
				tempRet[col][row] = src[srcCol][srcRow];
			}
		}

		// decide what col/row to add/substract
		int deltaRow = 0;
		int deltaCol = 0;
		if (nobNumber == 0 || nobNumber == 1 || nobNumber == 2)
		{
			deltaRow = -yDelta;
		}
		if (nobNumber == 0 || nobNumber == 3 || nobNumber == 5)
		{
			deltaCol = -xDelta;
		}
		// fill output vector
		for (int row = 0; row < h2; row++)
		{
			for (int col = 0; col < w2; col++)
			{
				if (tempRet[col][row] != -1)
				{
					InternalPixel p = new InternalPixel(col + minCol + deltaCol, row + minRow + deltaRow,
					        tempRet[col][row]);
					ret.add(p);
				}
			}
		}
		// return output vector
		return ret;
	}

}
