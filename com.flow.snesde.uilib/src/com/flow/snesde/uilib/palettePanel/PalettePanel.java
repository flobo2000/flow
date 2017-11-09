/**
 * 
 */
package com.flow.snesde.uilib.palettePanel;

import java.util.Random;

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
import com.flow.snesde.uilib.generic.Uilib;
import com.flow.snesde.uilib.generic.UilibComposite;

/**
 * @author flo
 */
public class PalettePanel extends UilibComposite implements MouseMoveListener
{
	private int drawingMode_horizontal = 0;
	private int drawingMode_vertical = 0;

	private final int DRAWINGMODE_TINY = 0;
	private final int DRAWINGMODE_SMALL = 1;
	private final int DRAWINGMODE_MEDIUM = 2;
	private final int DRAWINGMODE_BIG = 3;

	int sel_col = -1;
	int sel_row = -1;

	/**
	 * the color repository
	 */
	private NonNativeColor[] colors = new NonNativeColor[256];

	/**
	 * The internal selection number for the left mouse button
	 */
	private int leftSelectionIndex = 0;
	/**
	 * The internal selection number for the right mouse button
	 */
	private int rightSelectionIndex = 0;

	/**
	 * The container for the model. May be of type Palette or PaletteObject
	 */
	Object pal = null;

	/**
	 * Will temporarily store if the last click on a color was left or right
	 * click
	 */
	private boolean lastClickWasLeft = true;

	/**
	 * The width of one color blob
	 */
	private int blobwidth;

	/**
	 * the height of one color blob
	 */
	private int blobheight;

	// the number of pixels to keep on the far left and right of all the color
	// blobs
	private int border_vertical = 1;
	// the number of pixels to keep on top and bottom of all color blobs
	private int border_horizontal = 1;
	// the number of pixels dividing two consecutive color blobs vertically
	private int divider_vertical = 1;
	// the number of pixels dividing two consecutive color blobs horizontally
	private int divider_horizontal = 1;
	// the number of pixels between the marking frame and the color blob
	// (vertical direction)
	private int markedSpaceToBlob_vertical = 0;
	// the number of pixels between the marking frame and the color blob
	// (horizontal direction)
	private int markedSpaceToBlob_horizontal = 0;

	private final boolean leftPressed = false;
	private final boolean rightPressed = false;
	private int hover;

	private boolean mouseOver = false;
	private boolean displayOnly;

	/**
	 * @param parent
	 *            the parent composite to add this one to
	 * @param style
	 *            the style constant to use
	 */
	public PalettePanel(final Composite parent, final int style)
	{
		super(parent, style);

		// debug = true;

		Random randGen = new Random(System.currentTimeMillis());
		for (int i = 0; i < 256; i++)
		{
			int r = Math.abs(randGen.nextInt()) % 31;
			int g = Math.abs(randGen.nextInt()) % 31;
			int b = Math.abs(randGen.nextInt()) % 31;

			this.colors[i] = new NonNativeColor(r, g, b);
		}

		// so we can react on mouse hovering
		this.addMouseMoveListener(this);

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

	/**
	 * returns the index of the color that is at pixel x/y
	 * 
	 * @param x
	 *            (x coordinate of the clicked event)
	 * @param y
	 *            (y coordinate of the clicked event)
	 * @return i (the index number of the clicked color
	 */
	public int getClickedColorIndex(final int x, final int y)
	{
		// horizontal: border_horiz blobwidth *26 divider_horiz *25 border_horiz
		// vertical: border_vertic blobheight*10 divider_vertic* 9 border_vertic
		int row = 0;
		int col = 0;

		if (drawingMode_horizontal == DRAWINGMODE_TINY
				|| drawingMode_horizontal == DRAWINGMODE_SMALL)
		{
			col = (int) Math.ceil((double) x / (blobwidth + 1));
		}
		else if (drawingMode_horizontal == DRAWINGMODE_MEDIUM)
		{
			col = (int) Math.ceil((double) x / (blobwidth + 2));
		}
		else
		{
			col = (int) Math.ceil((double) x / (blobwidth + 3));
		}

		if (drawingMode_vertical == DRAWINGMODE_TINY
				|| drawingMode_vertical == DRAWINGMODE_SMALL)
		{
			row = (int) Math.ceil((double) y / (blobheight + 1));
		}
		else if (drawingMode_horizontal == DRAWINGMODE_MEDIUM)
		{
			row = (int) Math.ceil((double) y / (blobheight + 2));
		}
		else
		{
			row = (int) Math.ceil((double) y / (blobheight + 3));
		}

		// get max values for row & col + col in last row
		int maxRow = ((int) Math.ceil(colors.length / 26)) + 1;
		int maxCol = 26;
		if (colors.length < 26)
		{
			maxCol = colors.length;
		}
		int lastCol = colors.length - ((maxRow - 1) * 26);

		// make sure we're in bounds
		if (row > maxRow) row = maxRow;
		if (row < 1) row = 1;
		if (row == maxRow)
		{
			if (col > lastCol)
			{
				col = lastCol;
			}
		}
		else
		{
			if (col > maxCol)
			{
				col = maxCol;
			}
		}
		if (col < 1) col = 1;

		// TODO: remove the following line
		// if last row -> only column 22 is allowed (as this is the last color)
		if (row == 10 && col > 22) col = 22;
		this.sel_col = col - 1;
		this.sel_row = row - 1;

		setDebugString("col: " + sel_col + "\nrow: " + sel_row + "\nleft: "
				+ leftSelectionIndex + "\nright: " + rightSelectionIndex);

		return (this.sel_row) * 26 + this.sel_col;
	}

	/**
	 * @return the rightSelectionIndex
	 */
	public Integer getRightSelectionIndex()
	{
		return this.rightSelectionIndex;
	}

	/**
	 * @param rightSelectionIndex
	 *            the rightSelectionIndex to set
	 */
	public void setRightSelectionIndex(final Integer rightSelectionIndex)
	{
		this.rightSelectionIndex = rightSelectionIndex;
		this.lastClickWasLeft = false;
		redraw();
	}

	/**
	 * @return the leftSelectionIndex
	 */
	public Integer getLeftSelectionIndex()
	{
		return this.leftSelectionIndex;
	}

	/**
	 * @param leftSelectionIndex
	 *            the leftSelectionIndex to set
	 */
	public void setLeftSelectionIndex(final Integer leftSelectionIndex)
	{
		this.leftSelectionIndex = leftSelectionIndex;
		this.lastClickWasLeft = true;
		redraw();
	}

	/**
	 * initializes all variables for the drawing mode
	 * 
	 * @param width
	 *            the width of the canvas
	 * @param height
	 *            the height of the canvas
	 */
	private void setDrawingMode(int width, int height)
	{
		// potentially four different modes of drawing the palette are possible:
		// mode1:
		// XXX...... width: 53 - 78
		// XcXc.c.c. height: 13 - 30
		// XXX......
		//
		// mode2:
		// XXXX...... width: 79 - 103
		// XccXcc.cc. height: 31 - 39
		// XccXcc.cc.
		// XXXX......
		//
		// mode3:
		// XXXX........ width: 104 - 182
		// XccX.cc..cc. height: 40 - 79
		// XccX.cc..cc.
		// XXXX........
		//
		// mode4:
		// XXXXXXXX............... width: 183 - infinite
		// X......X............... height: 80 - infinite
		// X.cccc.X.cccc...cccc...
		// X.cccc.X.cccc...cccc...
		// X.cccc.X.cccc...cccc...
		// X.cccc.X.cccc...cccc...
		// X......X...............
		// XXXXXXXX...............
		// assign drawingmode variables

		// horizontal:
		if (width < 79)
		{
			drawingMode_horizontal = DRAWINGMODE_TINY;
		}
		else if (width >= 79 && width < 104)
		{
			drawingMode_horizontal = DRAWINGMODE_SMALL;
		}
		else if (width >= 104 && width < 183)
		{
			drawingMode_horizontal = DRAWINGMODE_MEDIUM;
		}
		else
		{
			drawingMode_horizontal = DRAWINGMODE_BIG;
		}

		// vertical:
		if (height < 31)
		{
			drawingMode_vertical = DRAWINGMODE_TINY;
		}
		else if (height >= 31 && height < 40)
		{
			drawingMode_vertical = DRAWINGMODE_SMALL;
		}
		else if (height >= 40 && height < 80)
		{
			drawingMode_vertical = DRAWINGMODE_MEDIUM;
		}
		else
		{
			drawingMode_vertical = DRAWINGMODE_BIG;
		}

		// calculate blob height and other height variables
		if (drawingMode_vertical == DRAWINGMODE_TINY)
		{
			blobheight = 1;
			border_vertical = 1;
			divider_vertical = 1;
			markedSpaceToBlob_vertical = 0;
		}
		else if (drawingMode_vertical == DRAWINGMODE_SMALL)
		{
			blobheight = 2;
			border_vertical = 1;
			divider_vertical = 1;
			markedSpaceToBlob_vertical = 0;
		}
		else if (drawingMode_vertical == DRAWINGMODE_MEDIUM)
		{
			blobheight = 2;
			border_vertical = 1;
			divider_vertical = 2;
			markedSpaceToBlob_vertical = 0;
		}
		else
		{
			border_vertical = 2;
			divider_vertical = 3;
			markedSpaceToBlob_vertical = 1;
			int heightToCalculateWith = height
					- ((2 * border_vertical) + (9 * divider_vertical));
			blobheight = (int) Math.floor(((heightToCalculateWith / 10) - 1));
		}

		// calculate blob width and other width variables
		if (drawingMode_horizontal == DRAWINGMODE_TINY)
		{
			blobwidth = 1;
			border_horizontal = 1;
			divider_horizontal = 1;
			markedSpaceToBlob_horizontal = 0;
		}
		else if (drawingMode_horizontal == DRAWINGMODE_SMALL)
		{
			blobwidth = 2;
			border_horizontal = 1;
			divider_horizontal = 1;
			markedSpaceToBlob_horizontal = 0;
		}
		else if (drawingMode_horizontal == DRAWINGMODE_MEDIUM)
		{
			blobwidth = 2;
			border_horizontal = 1;
			divider_horizontal = 2;
			markedSpaceToBlob_horizontal = 0;
		}
		else
		{
			border_horizontal = 2;
			divider_horizontal = 3;
			markedSpaceToBlob_horizontal = 1;
			int widthToCalculateWith = width
					- ((2 * border_horizontal) + (25 * divider_horizontal));
			blobwidth = (int) Math.floor(((widthToCalculateWith / 26) - 1));
		}

		/**
		 * display only mode -> show no spaces between color blobs
		 */
		if (displayOnly)
		{
			divider_horizontal = 0;
			divider_vertical = 0;
			markedSpaceToBlob_horizontal = 0;
			markedSpaceToBlob_vertical = 0;
			blobwidth++;
			blobheight++;
		}
	}

	@Override
	public void drawWidget(PaintEvent e, GC gc, int width, int height)
	{
		setDrawingMode(width, height);

		// current index of the color arrays
		int i = 0;
		boolean plusOneDrawn = false;

		// draw all palette colors
		for (int row = 0; row < 10; row++)
		{
			for (int col = 0; col < 26; col++)
			{
				// handle last four color places in last row --> leave empty
				if (i >= colors.length)
				{
					// do nothing... don't paint these squares
				}
				else
				{
					// regular color blob to be drawn
					if (i < colors.length && colors[i] != null)
					{
						Color c = createColor(colors[i]);
						// set color
						Color lighter = Uilib.createLighterColor(c, 20, this);
						Color darker = Uilib.createDarkerColor(c, 20, this);
						gc.setBackground(darker);
						gc.setForeground(lighter);

						// draw blob
						int start_x = border_horizontal
								+ (col * (blobwidth + divider_horizontal));
						int start_y = border_vertical
								+ (row * (blobheight + divider_vertical));
						gc.fillGradientRectangle(start_x, start_y, blobwidth,
								blobheight, true);
						// draw selected rectangle if needed
						if ((i == leftSelectionIndex || i == rightSelectionIndex)
								&& !displayOnly)
						{
							start_x = start_x - border_horizontal;
							start_y = start_y - border_vertical;
							int rectWidth = 1 + markedSpaceToBlob_horizontal
									+ blobwidth + markedSpaceToBlob_horizontal
									+ 1;
							int rectHeight = 1 + markedSpaceToBlob_vertical
									+ blobheight + markedSpaceToBlob_vertical
									+ 1;
							gc.setForeground(createColor(0, 0, 0));
							gc.drawRectangle(start_x, start_y, rectWidth - 1,
									rectHeight - 1);
							// if drawingmode = BIG -> marker line should be
							// double the size to make it more visible
							if (drawingMode_horizontal == DRAWINGMODE_BIG)
							{
								gc.drawRectangle(start_x + 1, start_y,
										rectWidth - 3, rectHeight - 1);
							}
							if (drawingMode_vertical == DRAWINGMODE_BIG)
							{
								gc.drawRectangle(start_x, start_y + 1,
										rectWidth - 1, rectHeight - 3);
							}
						}
						else if ((i == hover && mouseOver) && !displayOnly)
						{
							start_x = start_x - border_horizontal;
							start_y = start_y - border_vertical;
							int rectWidth = 1 + markedSpaceToBlob_horizontal
									+ blobwidth + markedSpaceToBlob_horizontal
									+ 1;
							int rectHeight = 1 + markedSpaceToBlob_vertical
									+ blobheight + markedSpaceToBlob_vertical
									+ 1;
							gc.setForeground(createColor(0, 0, 0));
							gc.setLineStyle(SWT.LINE_DOT);
							gc.drawRectangle(start_x, start_y, rectWidth - 1,
									rectHeight - 1);
							gc.setLineStyle(SWT.LINE_SOLID);
						}
					}
					else if (!plusOneDrawn)
					{
						Color lighter = Uilib.createLighterColor(
								createColor(0, 255, 0), 20, this);
						Color darker = Uilib.createDarkerColor(
								createColor(0, 255, 0), 20, this);
						gc.setBackground(darker);
						gc.setForeground(lighter);
						// TODO: finalize this code... there should be a PLUS
						// sign drawn here to add new colors
						// gc.fillGradientRectangle(x, y, width, height,
						// vertical);
						plusOneDrawn = true;
					}
				}
				i++;
			}
		}
	}

	@Override
	public void mouseMove(MouseEvent e)
	{
		int i = getClickedColorIndex(e.x, e.y);
		setHover(i);
	}

	private void setHover(int i)
	{
		this.hover = i;
		redraw();
	}

	public NonNativeColor[] getColors()
	{
		return colors;
	}

	public void setColors(NonNativeColor[] colors)
	{
		this.colors = colors;
		redraw();
	}

	/**
	 * this method makes the palette panel a display only panel with no hover
	 * effect, no selected color markers, no space between the color dots etc.
	 */
	public void setDisplayOnly()
	{
		displayOnly = true;
	}

	// @Override
	// public Point computeSize(int wHint, int hHint, boolean changed)
	// {
	// int width = wHint;
	// int height = hHint;
	//
	// width = border_horizontal * 2 + divider_horizontal * 27
	// + (28 * blobwidth);
	// height = border_vertical * 2 + divider_vertical * 9 + (10 * blobheight);
	//
	// return new Point(width, height);
	// }
}
