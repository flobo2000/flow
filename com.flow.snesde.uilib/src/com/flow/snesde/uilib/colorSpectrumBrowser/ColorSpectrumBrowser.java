/**
 * 
 */
package com.flow.snesde.uilib.colorSpectrumBrowser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.generic.UilibComposite;

/**
 * @author flo
 */
public class ColorSpectrumBrowser extends UilibComposite implements
		MouseMoveListener
{
	private enum Status
	{
		DEC, STAY, INC
	}

	int border_horizontal = 1;
	int border_vertical = 1;
	int blob_width = 1;
	int blob_height = 1;

	// selected column
	int selectedColumn = -1;
	// selected row
	int selectedRow = -1;

	private final boolean leftPressed = false;
	private final boolean rightPressed = false;

	// boolean indicating that the mouse is over the widget
	private boolean mouseOver = false;

	// column over which the mouse hovered last
	private int hoverColumn = -1;
	// row over which the mouse hovered last
	private int hoverRow = -1;

	/**
	 * @param parent
	 * @param style
	 */
	public ColorSpectrumBrowser(final Composite parent, final int style)
	{
		super(parent, style);
		this.addMouseMoveListener(this);

		debug = true;

		//
		this.addListener(SWT.MouseEnter, new Listener()
		{
			@Override
			public void handleEvent(Event e)
			{
				mouseOver = true;
				redraw();
			}
		});

		this.addListener(SWT.MouseExit, new Listener()
		{
			@Override
			public void handleEvent(Event e)
			{
				mouseOver = false;
				redraw();
			}
		});
	}

	@Override
	public void drawWidget(final PaintEvent e, final GC gc, final int width,
			final int height)
	{
		// TODO: refine this so the unused space on the right & bottom is
		// equally distributed amongst the color columns & rows

		// color spectrum consists of...
		// 186 color blobs per row
		// 63 color blobs per column

		// calculate regular color blob width and height
		this.blob_height = (int) Math
				.floor((height - (2 * border_vertical)) / 63);
		this.blob_width = (int) Math
				.floor((width - (2 * border_horizontal)) / 186);

		// color spectrum chooser
		int cRed = 31;
		int cGreen = 0;
		int cBlue = 0;

		boolean stop = false;

		Status redStatus = Status.STAY;
		Status greenStatus = Status.INC;
		Status blueStatus = Status.STAY;

		int currentCol = 0;
		int currentRow;
		int startx, starty;

		// draw loop
		while (!stop)
		{
			// draw block of 32 columns... this loop is executed six times
			for (int i = 0; i < 32; i++)
			{
				// draw upper half plus middle color rows
				gc.setBackground(getBaseColorPlusX(i, cRed, cGreen, cBlue));
				currentRow = i;
				startx = this.border_horizontal
						+ (currentCol * this.blob_width);
				starty = this.border_vertical + (31 * this.blob_height)
						- (i * this.blob_height);
				gc.fillRectangle(startx, starty, blob_width, blob_height);
				// draw lower half plus middle color rows
				gc.setBackground(getBaseColorPlusX(-i, cRed, cGreen, cBlue));
				currentRow = -i;
				startx = this.border_horizontal
						+ (currentCol * this.blob_width);
				starty = this.border_vertical + (31 * this.blob_height)
						+ (i * this.blob_height);
				gc.fillRectangle(startx, starty, blob_width, blob_height);
			}

			// 31 0 0 --> 31 31 0
			if (redStatus == Status.STAY && greenStatus == Status.INC
					&& blueStatus == Status.STAY)
			{
				if (cGreen != 31)
				{
					currentCol++;
					cGreen++;
				}
				else
				{
					// set status for next part
					redStatus = Status.DEC;
					greenStatus = Status.STAY;
				}
			}
			// 31 31 0 --> 0 31 0
			else if (redStatus == Status.DEC && greenStatus == Status.STAY
					&& blueStatus == Status.STAY)
			{
				if (cRed != 0)
				{
					currentCol++;
					cRed--;
				}
				else
				{
					// set status for next part
					redStatus = Status.STAY;
					blueStatus = Status.INC;
				}
			}
			// 0 31 0 --> 0 31 31
			else if (redStatus == Status.STAY && greenStatus == Status.STAY
					&& blueStatus == Status.INC)
			{
				if (cBlue != 31)
				{
					currentCol++;
					cBlue++;
				}
				else
				{
					// set status for next part
					greenStatus = Status.DEC;
					blueStatus = Status.STAY;
				}
			}
			// 0 31 31 --> 0 0 31
			else if (redStatus == Status.STAY && greenStatus == Status.DEC
					&& blueStatus == Status.STAY)
			{
				if (cGreen != 0)
				{
					currentCol++;
					cGreen--;
				}
				else
				{
					// set status for next part
					redStatus = Status.INC;
					greenStatus = Status.STAY;
				}
			}
			// 0 0 31 --> 31 0 31
			else if (redStatus == Status.INC && greenStatus == Status.STAY
					&& blueStatus == Status.STAY)
			{
				if (cRed != 31)
				{
					currentCol++;
					cRed++;
				}
				else
				{
					// set status for next part
					redStatus = Status.STAY;
					blueStatus = Status.DEC;
				}
			}
			// 31 0 31 --> 31 0 1
			else if (redStatus == Status.STAY && greenStatus == Status.STAY
					&& blueStatus == Status.DEC)
			{
				if (cBlue != 1)
				{
					currentCol++;
					cBlue--;
				}
				else
				{
					stop = true;
				}
			}
		}

		// draw frame
		gc.setForeground(createColor(0, 0, 0));
		startx = 0;
		starty = 0;
		int rectwidth = border_horizontal + (186 * blob_width);
		int rectheight = border_vertical + (63 * blob_height);
		gc.drawRectangle(startx, starty, rectwidth, rectheight);

		// hightlight last selected color
		if (selectedColumn >= 0 && selectedRow >= 0)
		{
			startx = border_horizontal + (selectedColumn * blob_width);
			starty = border_vertical + (selectedRow * blob_height);
			gc.drawRectangle(startx, starty, blob_width - 1, blob_height - 1);
		}
		// draw hover color
		else if (mouseOver)
		{
			gc.setForeground(createColor(0, 0, 0));
			gc.setLineStyle(SWT.LINE_DOT);

			startx = border_horizontal + (hoverColumn * blob_width);
			starty = border_vertical + (hoverRow * blob_height);
			gc.drawRectangle(startx, starty, blob_width - 1, blob_height - 1);

			gc.setLineStyle(SWT.LINE_SOLID);
		}
	}

	/**
	 * This method adds to the passed color the integer i
	 * 
	 * @param 0 .. 31
	 * @param red
	 *            the original red 0..31
	 * @param green
	 *            the original green 0..31
	 * @param blue
	 *            the original blue 0..31
	 * @return the new color
	 */
	private Color getBaseColorPlusX(int i, int red, int green, int blue)
	{
		if (i > 31)
		{
			i = 31;
		}
		if (i < -31)
		{
			i = -31;
		}
		red = (red + i);
		green = (green + i);
		blue = (blue + i);
		if (red < 0)
		{
			red = 0;
		}
		if (green < 0)
		{
			green = 0;
		}
		if (blue < 0)
		{
			blue = 0;
		}
		if (red > 31)
		{
			red = 31;
		}
		if (green > 31)
		{
			green = 31;
		}
		if (blue > 31)
		{
			blue = 31;
		}
		return createColor((red * 8) % 255, (green * 8) % 255, (blue * 8) % 255);
	}

	@Override
	public void mouseMove(MouseEvent e)
	{
		int[] colRow = getClickedColorIndex(e.x, e.y);
		hoverColumn = colRow[0];
		hoverRow = colRow[1];
		redraw();
	}

	/**
	 * takes the mouse click location (parameters x and y from a mouse click
	 * event) and calculates the color blob position. returns it as an array of
	 * integers (column, row)
	 * 
	 * @param x
	 *            the mouse x location
	 * @param y
	 *            the mouse y location
	 * @return [colorBlobColumn, colorBlobRow]
	 */
	public int[] getClickedColorIndex(int x, int y)
	{
		// setDebugString("cols: "+col+"\nrows: "+row+"\nx: "+x+"\ny: "+y);
		// 186 color blobs per row
		// 63 color blobs per column

		int clickedColumn = -1;
		int clickedRow = -1;
		x = x - border_horizontal;
		clickedColumn = (int) Math.floor(x / blob_width);
		if (clickedColumn >= 186) clickedColumn = 185;
		if (clickedColumn < 0) clickedColumn = 0;
		y = y - border_vertical;
		clickedRow = (int) Math.floor(y / blob_height);
		if (clickedRow >= 63) clickedRow = 62;
		if (clickedRow < 0) clickedRow = 0;

		return new int[] { clickedColumn, clickedRow };
	}

	public void setSelectedColor(int column, int row)
	{
		selectedColumn = column;
		selectedRow = row;
		redraw();
	}

	public NonNativeColor getSelectedColor()
	{
		NonNativeColor c = new NonNativeColor(0, 0, 0);

		int cRed = 31;
		int cGreen = 0;
		int cBlue = 0;

		boolean stop = false;

		Status redStatus = Status.STAY;
		Status greenStatus = Status.INC;
		Status blueStatus = Status.STAY;

		int currentCol = 0;
		int currentRow;
		int startx, starty;

		// draw loop
		while (!stop)
		{
			// draw block of 32 columns... this loop is executed six times
			for (int i = 0; i < 32; i++)
			{
				// draw upper half plus middle color rows
				Color baseColorPlusX = getBaseColorPlusX(i, cRed, cGreen, cBlue);
				currentRow = 31 - i;
				if (currentCol == selectedColumn && currentRow == selectedRow)
				{
					c.setR(baseColorPlusX.getRed());
					c.setG(baseColorPlusX.getGreen());
					c.setB(baseColorPlusX.getBlue());
					return c;
				}
				// draw lower half plus middle color rows
				Color baseColorPlusX2 = getBaseColorPlusX(-i, cRed, cGreen,
						cBlue);
				currentRow = 31 + i;
				if (currentCol == selectedColumn && currentRow == selectedRow)
				{
					c.setR(baseColorPlusX2.getRed());
					c.setG(baseColorPlusX2.getGreen());
					c.setB(baseColorPlusX2.getBlue());
					return c;
				}
			}

			// 31 0 0 --> 31 31 0
			if (redStatus == Status.STAY && greenStatus == Status.INC
					&& blueStatus == Status.STAY)
			{
				if (cGreen != 31)
				{
					currentCol++;
					cGreen++;
				}
				else
				{
					// set status for next part
					redStatus = Status.DEC;
					greenStatus = Status.STAY;
				}
			}
			// 31 31 0 --> 0 31 0
			else if (redStatus == Status.DEC && greenStatus == Status.STAY
					&& blueStatus == Status.STAY)
			{
				if (cRed != 0)
				{
					currentCol++;
					cRed--;
				}
				else
				{
					// set status for next part
					redStatus = Status.STAY;
					blueStatus = Status.INC;
				}
			}
			// 0 31 0 --> 0 31 31
			else if (redStatus == Status.STAY && greenStatus == Status.STAY
					&& blueStatus == Status.INC)
			{
				if (cBlue != 31)
				{
					currentCol++;
					cBlue++;
				}
				else
				{
					// set status for next part
					greenStatus = Status.DEC;
					blueStatus = Status.STAY;
				}
			}
			// 0 31 31 --> 0 0 31
			else if (redStatus == Status.STAY && greenStatus == Status.DEC
					&& blueStatus == Status.STAY)
			{
				if (cGreen != 0)
				{
					currentCol++;
					cGreen--;
				}
				else
				{
					// set status for next part
					redStatus = Status.INC;
					greenStatus = Status.STAY;
				}
			}
			// 0 0 31 --> 31 0 31
			else if (redStatus == Status.INC && greenStatus == Status.STAY
					&& blueStatus == Status.STAY)
			{
				if (cRed != 31)
				{
					currentCol++;
					cRed++;
				}
				else
				{
					// set status for next part
					redStatus = Status.STAY;
					blueStatus = Status.DEC;
				}
			}
			// 31 0 31 --> 31 0 1
			else if (redStatus == Status.STAY && greenStatus == Status.STAY
					&& blueStatus == Status.DEC)
			{
				if (cBlue != 1)
				{
					currentCol++;
					cBlue--;
				}
				else
				{
					stop = true;
				}
			}
		}
		return c;
	}

}
