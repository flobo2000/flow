package com.flow.snesde.uilib.tilesetPanel;

import java.util.Random;
import java.util.Vector;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.flow.snesde.uilib.generic.InternalPixel;
import com.flow.snesde.uilib.generic.InternalTile;
import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.generic.UilibComposite;

public class TilesetPanel extends UilibComposite implements MouseMoveListener, FocusListener, ISelectionListener
{
	// flags for configuration of the tilesetPanel
	private int columns = 16;
	private int zoomFactor = 7;
	private boolean showGrid = true;
	private boolean highlightHoveringTile = false;
	private boolean highlightHoveringPixel = true;

	// the tiles to be displayed
	private InternalTile[] tiles = new InternalTile[256];
	// the palette to work with
	private NonNativeColor[] colors = new NonNativeColor[256];

	// used to indicate which tile is currently focussed
	private int hoveringTile;

	// used to indicate which pixel is currently focussed
	private int hoverPixelColumn;
	// used to indicate which pixel is currently focussed
	private int hoverPixelRow;

	// booleans deciding weather or not to draw temporary stuff
	// draw temp line circle (line tool)
	boolean drawTempLineCircle = false;
	boolean tempLineCircleTwoColors = false;
	int tempLineCircleSize = 8;
	// draw temp line square (line tool)
	boolean drawTempLineSquare = false;
	boolean tempLineSquareTwoColors = false;
	int tempLineSquareSize = 1;
	// draw temp string (text tool)
	boolean drawTempString = false;
	String tempString = "Hallo zusammen. Dies ist der tolle Temp String.";
	boolean lineWrap = true;
	boolean variableWidth = true;
	// enumberation
	boolean drawTempEnum = false;
	int numberOfNumers = 100;
	// hex enumeration
	boolean drawTempHexEnum = false;
	int numberOfHexNumbers = 256;

	// marking rectangle
	boolean drawMarkingRectangle = false;
	int startMarkCol = 7;
	int endMarkCol = 27;
	int startMarkRow = 15;
	int endMarkRow = 75;

	// temporary pixel buffer (no model correlation)
	boolean showBufferedPixels = false;
	Vector<InternalPixel> bufferedPixels = new Vector<InternalPixel>();

	private int globalPixelColumn;
	private int globalPixelRow;

	// permanent pixel buffer (has a correlating model element)
	boolean showPermanentBuffer = false;
	Vector<InternalPixel> permanentBuffer = new Vector<InternalPixel>();
	private boolean temporarilyHidePermanentBuffer;
	private boolean focus;
	private boolean drawRectangleOnTempBuffer;

	/**
	 * constructor
	 * 
	 * @param parent
	 * @param style
	 */
	public TilesetPanel(Composite parent, int style)
	{
		super(parent, style);
		setCursor(SWTResourceManager.getCursor(SWT.CURSOR_CROSS));

		// debug = true;
		Random randGen = new Random(System.currentTimeMillis());
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[i] = new InternalTile();
			if (debug)
			{
				for (int col = 0; col < 8; col++)
				{
					for (int row = 0; row < 8; row++)
					{
						tiles[i].setColorAt(col, row, Math.abs(randGen.nextInt()) % colors.length);
					}
				}
			}
		}
		for (int i = 0; i < colors.length; i++)
		{
			if (debug)
			{
				colors[i] = new NonNativeColor(Math.abs(randGen.nextInt()) % 31, Math.abs(randGen.nextInt()) % 31,
				        Math.abs(randGen.nextInt()) % 31);
			}
			else
			{
				colors[i] = new NonNativeColor(0, 0, 0);
			}
		}
		this.addMouseMoveListener(this);
		// this.addSelectionListener(this);
	}

	@Override
	public void drawWidget(PaintEvent e, GC gc, int width, int height)
	{
		int currentRow = -1;
		// go through all tiles and draw them
		for (int i = 0; i < tiles.length; i++)
		{
			if (i % columns == 0)
			{
				currentRow++;
			}
			int currentColumn = i % columns;

			// get current tile
			InternalTile tile = tiles[i];
			// draw it
			drawTile(gc, tile, currentColumn, currentRow, i);
		}

		// draw buffered pixels which are not yet written into the tiles
		if (showBufferedPixels)
		{
			drawBufferedPixels(gc);
		}
		// setDebugString("showPERM: " + showPermanentBuffer +
		// "\ntempHidePERM: "
		// + temporarilyHidePermanentBuffer);
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			drawPermanentBuffer(gc);
		}
		// draw temporary stuff if switches are on
		if (drawTempLineCircle)
		{
			drawTempLineCircle(gc);
		}
		else if (drawTempLineSquare)
		{
			drawTempLineSquare(gc);
		}
		else if (drawTempString)
		{
			drawTempString(gc, lineWrap, variableWidth);
		}
		else if (drawTempEnum)
		{
			drawTempEnum(gc);
		}
		else if (drawTempHexEnum)
		{
			drawTempHexEnum(gc);
		}
		else if (drawMarkingRectangle)
		{
			drawMarkingRectangle(gc);
		}
	}

	private void drawPermanentBuffer(GC gc)
	{
		if (permanentBuffer != null && !permanentBuffer.isEmpty())
		{
			int mincol = 10000;
			int minrow = 10000;
			int maxcol = -1;
			int maxrow = -1;
			for (InternalPixel pix : permanentBuffer)
			{
				int col = pix.getCol();
				int row = pix.getRow();
				if (col < mincol)
				{
					mincol = col;
				}
				if (row < minrow)
				{
					minrow = row;
				}
				if (col > maxcol)
				{
					maxcol = col;
				}
				if (row > maxrow)
				{
					maxrow = row;
				}
				int colorIndex = pix.getIndex();

				NonNativeColor nnColor = colors[colorIndex];
				Color c = createColor(nnColor);
				gc.setBackground(c);
				gc.fillRectangle(((col - 1) * zoomFactor), ((row - 1) * zoomFactor), zoomFactor, zoomFactor);

				// draw Gridlines if enabled
				// if (showGrid)
				// {
				// gc.setForeground(createColor(0, 0, 0));
				// gc.drawLine((col * zoomFactor) - 1, (row * zoomFactor) - 1,
				// startX
				// + (8 * zoomFactor) - 1, startY + (8 * zoomFactor) - 1);
				// gc.drawLine(startX - 1, startY + (8 * zoomFactor) - 1, startX
				// + (8 * zoomFactor) - 1, startY + (8 * zoomFactor) - 1);
				// }
			}
			// draw marking rectangle around this buffer
			gc.setLineStyle(SWT.LINE_SOLID);

			gc.setForeground(createColor(0, 0, 0));
			int startX = (mincol - 1) * zoomFactor;
			int startY = (minrow - 1) * zoomFactor;
			int endX = (maxcol - 1) * zoomFactor + zoomFactor;
			int endY = (maxrow - 1) * zoomFactor + zoomFactor;

			gc.drawLine(startX - 1, startY - 1, endX - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, endY - 1);
			gc.drawLine(endX - 1, startY - 1, endX - 1, endY - 1);
			gc.drawLine(startX - 1, endY - 1, endX - 1, endY - 1);

			gc.setForeground(createColor(255, 255, 255));
			gc.setLineStyle(SWT.LINE_DOT);

			gc.drawLine(startX - 1, startY - 1, endX - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, endY - 1);
			gc.drawLine(endX - 1, startY - 1, endX - 1, endY - 1);
			gc.drawLine(startX - 1, endY - 1, endX - 1, endY - 1);

			gc.setLineStyle(SWT.LINE_SOLID);

			// draw drag dots at the corners and in the middle of the lines
			gc.setBackground(createColor(48, 104, 192));
			// top left
			gc.fillRectangle(startX - 1, startY - 1, zoomFactor, zoomFactor);
			// sad
			// top middle
			gc.fillRectangle(startX - 1 + (int) (Math.floor((endX - startX) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), startY - 1, zoomFactor, zoomFactor);
			// right middle
			gc.fillRectangle(startX - 1, startY - 1 + (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// top right
			gc.fillRectangle(endX - (1 + zoomFactor), startY - 1, zoomFactor, zoomFactor);
			// bottom left
			gc.fillRectangle(startX - 1, endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom right
			gc.fillRectangle(endX - (1 + zoomFactor), endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom middle
			gc.fillRectangle(
			        endX - (1 + zoomFactor) - (int) (Math.floor((endX - startX) / 2))
			                + ((int) Math.floor((double) zoomFactor / (double) 2)),
			        endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// white outline on them...
			gc.setForeground(createColor(255, 255, 255));
			// top left
			gc.drawRectangle(startX - 1, startY - 1, zoomFactor, zoomFactor);
			// top middle
			gc.drawRectangle(startX - 1 + (int) (Math.floor((endX - startX) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), startY - 1, zoomFactor, zoomFactor);
			// right middle
			gc.fillRectangle(endX - (1 + zoomFactor), endY - 1 - (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// left middle
			gc.drawRectangle(startX - 1, startY - 1 + (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// top right
			gc.drawRectangle(endX - (1 + zoomFactor), startY - 1, zoomFactor, zoomFactor);
			// bottom left
			gc.drawRectangle(startX - 1, endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom right
			gc.drawRectangle(endX - (1 + zoomFactor), endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom middle
			gc.drawRectangle(
			        endX - (1 + zoomFactor) - (int) (Math.floor((endX - startX) / 2))
			                + ((int) Math.floor((double) zoomFactor / (double) 2)),
			        endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// right middle
			gc.drawRectangle(endX - (1 + zoomFactor), endY - 1 - (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// gc.fillRectangle(
			// startX - 1
			// - (int) Math.ceil((double) zoomFactor / (double) 2),
			// startY - 1
			// - (int) Math.ceil((double) zoomFactor / (double) 2),
			// zoomFactor, zoomFactor);
			// draw logo of permanent buffer if debug is on
			if (debug)
			{
				gc.setBackground(createColor(255, 255, 255));
				gc.setForeground(createColor(0, 0, 255));
				gc.drawString("PERM", (mincol - 1) * zoomFactor, (minrow - 1) * zoomFactor);
			}
		}
	}

	/**
	 * draws a dashed rectangle around the marked pixels
	 * 
	 * @param gc
	 */
	private void drawMarkingRectangle(GC gc)
	{
		if (startMarkCol != endMarkCol || startMarkRow != endMarkRow)
		{
			gc.setLineStyle(SWT.LINE_SOLID);

			// setDebugString("start: " + startMarkCol + "|" + startMarkRow +
			// "\n"
			// + "end: " + endMarkCol + "|" + endMarkRow);

			int lowerCol = startMarkCol;
			int lowerRow = startMarkRow;
			int higherCol = endMarkCol;
			int higherRow = endMarkRow;
			if (startMarkCol > endMarkCol)
			{
				lowerCol = endMarkCol;
				higherCol = startMarkCol;
			}
			if (startMarkRow > endMarkRow)
			{
				lowerRow = endMarkRow;
				higherRow = startMarkRow;
			}

			gc.setForeground(createColor(0, 0, 0));
			int startX = lowerCol * zoomFactor;
			int startY = lowerRow * zoomFactor;
			int endX = higherCol * zoomFactor + zoomFactor;
			int endY = higherRow * zoomFactor + zoomFactor;

			gc.drawLine(startX - 1, startY - 1, endX - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, endY - 1);
			gc.drawLine(endX - 1, startY - 1, endX - 1, endY - 1);
			gc.drawLine(startX - 1, endY - 1, endX - 1, endY - 1);

			gc.setForeground(createColor(255, 255, 255));
			gc.setLineStyle(SWT.LINE_DOT);

			gc.drawLine(startX - 1, startY - 1, endX - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, endY - 1);
			gc.drawLine(endX - 1, startY - 1, endX - 1, endY - 1);
			gc.drawLine(startX - 1, endY - 1, endX - 1, endY - 1);

			gc.setLineStyle(SWT.LINE_SOLID);

			// draw logo of permanent buffer if debug is on
			if (debug)
			{
				gc.setBackground(createColor(255, 255, 255));
				gc.setForeground(createColor(0, 0, 255));
				gc.drawString("MARK", lowerCol * zoomFactor, lowerRow * zoomFactor);
			}
		}
	}

	/**
	 * draws the buffered pixels on top of the regular tileset (these buffered
	 * pixels are not part of the tileset yet but an intermediate variable for
	 * tools adding new pixels)
	 * 
	 * @param gc
	 */
	private void drawBufferedPixels(GC gc)
	{
		int mincol = 10000;
		int minrow = 10000;
		int maxcol = -1;
		int maxrow = -1;

		for (InternalPixel pix : bufferedPixels)
		{
			int col = pix.getCol();
			int row = pix.getRow();
			if (col < mincol)
			{
				mincol = col;
			}
			if (row < minrow)
			{
				minrow = row;
			}
			if (col > maxcol)
			{
				maxcol = col;
			}
			if (row > maxrow)
			{
				maxrow = row;
			}
			int colorIndex = pix.getIndex();

			NonNativeColor nnColor = colors[colorIndex];
			Color c = createColor(nnColor);
			gc.setBackground(c);
			gc.fillRectangle(((col - 1) * zoomFactor), ((row - 1) * zoomFactor), zoomFactor, zoomFactor);

			// draw Gridlines if enabled
			// if (showGrid)
			// {
			// gc.setForeground(createColor(0, 0, 0));
			// gc.drawLine((col * zoomFactor) - 1, (row * zoomFactor) - 1,
			// startX
			// + (8 * zoomFactor) - 1, startY + (8 * zoomFactor) - 1);
			// gc.drawLine(startX - 1, startY + (8 * zoomFactor) - 1, startX
			// + (8 * zoomFactor) - 1, startY + (8 * zoomFactor) - 1);
			// }
		}
		if (drawRectangleOnTempBuffer || debug)
		{
			// draw marking rectangle around this buffer
			gc.setLineStyle(SWT.LINE_SOLID);

			gc.setForeground(createColor(0, 0, 0));
			int startX = (mincol - 1) * zoomFactor;
			int startY = (minrow - 1) * zoomFactor;
			int endX = (maxcol - 1) * zoomFactor + zoomFactor;
			int endY = (maxrow - 1) * zoomFactor + zoomFactor;

			gc.drawLine(startX - 1, startY - 1, endX - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, endY - 1);
			gc.drawLine(endX - 1, startY - 1, endX - 1, endY - 1);
			gc.drawLine(startX - 1, endY - 1, endX - 1, endY - 1);

			gc.setForeground(createColor(255, 255, 255));
			gc.setLineStyle(SWT.LINE_DOT);

			gc.drawLine(startX - 1, startY - 1, endX - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, endY - 1);
			gc.drawLine(endX - 1, startY - 1, endX - 1, endY - 1);
			gc.drawLine(startX - 1, endY - 1, endX - 1, endY - 1);

			gc.setLineStyle(SWT.LINE_SOLID);

			// draw drag dots at the corners and in the middle of the lines
			gc.setBackground(createColor(48, 104, 192));
			// top left
			gc.fillRectangle(startX - 1, startY - 1, zoomFactor, zoomFactor);
			// sad
			// top middle
			gc.fillRectangle(startX - 1 + (int) (Math.floor((endX - startX) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), startY - 1, zoomFactor, zoomFactor);
			// right middle
			gc.fillRectangle(startX - 1, startY - 1 + (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// top right
			gc.fillRectangle(endX - (1 + zoomFactor), startY - 1, zoomFactor, zoomFactor);
			// bottom left
			gc.fillRectangle(startX - 1, endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom right
			gc.fillRectangle(endX - (1 + zoomFactor), endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom middle
			gc.fillRectangle(
			        endX - (1 + zoomFactor) - (int) (Math.floor((endX - startX) / 2))
			                + ((int) Math.floor((double) zoomFactor / (double) 2)),
			        endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// white outline on them...
			gc.setForeground(createColor(255, 255, 255));
			// top left
			gc.drawRectangle(startX - 1, startY - 1, zoomFactor, zoomFactor);
			// top middle
			gc.drawRectangle(startX - 1 + (int) (Math.floor((endX - startX) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), startY - 1, zoomFactor, zoomFactor);
			// right middle
			gc.fillRectangle(endX - (1 + zoomFactor), endY - 1 - (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// left middle
			gc.drawRectangle(startX - 1, startY - 1 + (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
			// top right
			gc.drawRectangle(endX - (1 + zoomFactor), startY - 1, zoomFactor, zoomFactor);
			// bottom left
			gc.drawRectangle(startX - 1, endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom right
			gc.drawRectangle(endX - (1 + zoomFactor), endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// bottom middle
			gc.drawRectangle(
			        endX - (1 + zoomFactor) - (int) (Math.floor((endX - startX) / 2))
			                + ((int) Math.floor((double) zoomFactor / (double) 2)),
			        endY - (1 + zoomFactor), zoomFactor, zoomFactor);
			// right middle
			gc.drawRectangle(endX - (1 + zoomFactor), endY - 1 - (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2)), zoomFactor, zoomFactor);
		}
		// draw logo of permanent buffer if debug is on
		if (debug)
		{
			gc.setBackground(createColor(255, 255, 255));
			gc.setForeground(createColor(255, 0, 0));
			gc.drawString("TEMP", (mincol - 1) * zoomFactor, (minrow - 1) * zoomFactor);
		}
	}

	/**
	 * draws the temporary square shape for the drawing/line tool where the
	 * current pointer is
	 */
	private void drawTempLineSquare(GC gc)
	{
		// get binary string representation of this shape
		// center is always character in row 4/8 and column 5/8
		Tilemask mask = TilemaskFactory.getMaskForSquare(tempLineSquareSize, tempLineSquareTwoColors);

		if (mask != null)
		{
			int startX = (globalPixelColumn - 5);
			int startY = (globalPixelRow - 4);

			drawMaskSilhouette(gc, mask, startX, startY);
		}
	}

	/**
	 * draws the temporary circle shape for the drawing/line tool where the
	 * current pointer is
	 * 
	 * @param gc
	 *            the graphics context
	 */
	private void drawTempLineCircle(GC gc)
	{
		// get binary string representation of this shape
		// center is always character in row 4/8 and column 5/8
		Tilemask mask = TilemaskFactory.getMaskForRound(tempLineCircleSize, tempLineCircleTwoColors);
		// mask = TilemaskFactory.getMaskForRound(8);
		// mask = TilemaskFactory.getMaskForCharacter('#');

		if (mask != null)
		{
			int startX = (globalPixelColumn - 5);
			int startY = (globalPixelRow - 4);

			drawMaskSilhouette(gc, mask, startX, startY);
		}
	}

	/**
	 * draws the string temporarily
	 * 
	 * @param gc
	 *            the graphics context
	 */
	private void drawTempString(GC gc, boolean wrap, boolean variableWidth)
	{
		int startX = (globalPixelColumn - 5);
		int startY = (globalPixelRow - 4);

		for (int i = 0; i < tempString.length(); i++)
		{
			Tilemask mask = TilemaskFactory.getMaskForCharacter(tempString.charAt(i));
			if (mask != null)
			{
				if (wrap)
				{
					if (variableWidth)
					{
						if (startX + mask.getMinWidth() > columns * 8)
						{
							startX = 0;
							startY += 8;
						}
					}
					else
					{
						if (startX + 8 > columns * 8)
						{
							startX = 0;
							startY += 8;
						}
					}
				}
				drawMaskSilhouette(gc, mask, startX, startY);
				if (variableWidth)
				{
					startX += mask.getMinWidth();
				}
				else
				{
					startX += 8;
				}
			}
		}
	}

	/**
	 * draws all numbers from 00 .. numberOfNumbers from the tile where the
	 * pointer is
	 * 
	 * @param gc
	 */
	private void drawTempEnum(GC gc)
	{
		int startX = (int) (globalPixelColumn - (Math.floor(globalPixelColumn % 8)));
		int startY = (int) (globalPixelRow - (Math.floor(globalPixelRow % 8)));

		for (int i = 0; i < numberOfNumers; i++)
		{
			Tilemask mask = TilemaskFactory.getMaskForHex(i % 100);
			if (mask != null)
			{
				if (startX + 8 > columns * 8)
				{
					startX = 0;
					startY += 8;
				}
				drawMaskSilhouette(gc, mask, startX, startY);
				startX += 8;
			}
		}
	}

	/**
	 * draws all numbers from 00 .. numberOfHexNumbers from the tile where the
	 * pointer is
	 * 
	 * @param gc
	 */
	private void drawTempHexEnum(GC gc)
	{
		int startX = (int) (globalPixelColumn - (Math.floor(globalPixelColumn % 8)));
		int startY = (int) (globalPixelRow - (Math.floor(globalPixelRow % 8)));

		for (int i = 0; i < numberOfHexNumbers; i++)
		{
			Tilemask mask = TilemaskFactory.getMaskForHex(i % 256);
			if (mask != null)
			{
				if (startX + 8 > columns * 8)
				{
					startX = 0;
					startY += 8;
				}
				drawMaskSilhouette(gc, mask, startX, startY);
				startX += 8;
			}
		}
	}

	/**
	 * draws the tilemask silhouette at the position of the hovering pixel
	 * 
	 * @param gc
	 *            the graphics context
	 * @param mask
	 *            the tilemask to draw
	 * @param pixelColumn
	 *            the global column of the pixels to start at
	 * @param pixelRow
	 *            the global row of the pixels to start at
	 */
	private void drawMaskSilhouette(GC gc, Tilemask mask, int pixelColumn, int pixelRow)
	{
		// white dashed line style
		gc.setForeground(createColor(255, 255, 255));
		gc.setLineWidth(1);
		// gc.setLineStyle(SWT.LINE_DOT);

		// determine start x and y positions to do our calculations from
		int startX = (pixelColumn) * zoomFactor;
		int startY = (pixelRow) * zoomFactor;

		int curX = startX;
		int curY = startY;

		// go through shape pixels row by row
		for (int row = 0; row < 8; row++)
		{
			curY = startY + (zoomFactor * row);
			for (int col = 0; col < 8; col++)
			{
				curX = startX + (zoomFactor * col);
				int index = (8 * row) + col;
				if (mask.pixelSetAt(index))
				{
					// current pixel is set --> check around it to draw
					// borders
					if (!mask.pixelSetAbove(index))
					{
						gc.drawLine(curX - 1, curY - 1, curX + zoomFactor - 1, curY - 1);
					}
					if (!mask.pixelSetBelow(index))
					{
						gc.drawLine(curX - 1, curY + zoomFactor - 1, curX + zoomFactor - 1, curY + zoomFactor - 1);
					}
					if (!mask.pixelSetOnTheLeft(index))
					{
						gc.drawLine(curX - 1, curY - 1, curX - 1, curY + zoomFactor - 1);
					}
					if (!mask.pixelSetOnTheRight(index))
					{
						gc.drawLine(curX + zoomFactor - 1, curY - 1, curX + zoomFactor - 1, curY + zoomFactor - 1);
					}
				}
			}
		}
		gc.setLineStyle(SWT.LINE_SOLID);
	}

	/**
	 * draws the current tile
	 * 
	 * @param tile
	 *            the tile to be drawn
	 * @param currentColumn
	 *            the column in which it should be displayed
	 * @param currentRow
	 *            the row in which to display
	 */
	private void drawTile(GC gc, InternalTile tile, int currentColumn, int currentRow, int tileNumber)
	{
		int startX = 8 * currentColumn * zoomFactor;
		int startY = 8 * currentRow * zoomFactor;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				int colorIndex = tile.getColorAt(i, j);
				NonNativeColor nnColor = colors[colorIndex];
				Color c = createColor(nnColor);
				gc.setBackground(c);
				gc.fillRectangle(startX + (i * zoomFactor), startY + (j * zoomFactor), zoomFactor, zoomFactor);
			}
		}
		// draw Gridlines if enabled
		if (showGrid)
		{
			gc.setForeground(createColor(0, 0, 0));
			gc.drawLine(startX + (8 * zoomFactor) - 1, startY - 1, startX + (8 * zoomFactor) - 1,
			        startY + (8 * zoomFactor) - 1);
			gc.drawLine(startX - 1, startY + (8 * zoomFactor) - 1, startX + (8 * zoomFactor) - 1,
			        startY + (8 * zoomFactor) - 1);
		}

		// highlight current tile
		if (highlightHoveringTile && tileNumber == hoveringTile)
		{
			gc.setForeground(createColor(255, 255, 255));
			gc.setLineStyle(SWT.LINE_DOT);

			gc.drawLine(startX - 1, startY - 1, startX + (8 * zoomFactor) - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, startY + (8 * zoomFactor) - 1);
			gc.drawLine(startX + (8 * zoomFactor) - 1, startY - 1, startX + (8 * zoomFactor) - 1,
			        startY + (8 * zoomFactor) - 1);
			gc.drawLine(startX - 1, startY + (8 * zoomFactor) - 1, startX + (8 * zoomFactor) - 1,
			        startY + (8 * zoomFactor) - 1);

			gc.setLineStyle(SWT.LINE_SOLID);
		}

		// hightlight hovering pixel
		if (highlightHoveringPixel && tileNumber == hoveringTile)
		{
			gc.setLineStyle(SWT.LINE_SOLID);

			gc.setForeground(createColor(0, 0, 0));
			startX += hoverPixelColumn * zoomFactor;
			startY += hoverPixelRow * zoomFactor;
			gc.drawLine(startX - 1, startY - 1, startX + zoomFactor - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, startY + zoomFactor - 1);
			gc.drawLine(startX + zoomFactor - 1, startY - 1, startX + zoomFactor - 1, startY + zoomFactor - 1);
			gc.drawLine(startX - 1, startY + zoomFactor - 1, startX + zoomFactor - 1, startY + zoomFactor - 1);

			gc.setForeground(createColor(255, 255, 255));
			gc.setLineStyle(SWT.LINE_DOT);
			gc.drawLine(startX - 1, startY - 1, startX + zoomFactor - 1, startY - 1);
			gc.drawLine(startX - 1, startY - 1, startX - 1, startY + zoomFactor - 1);
			gc.drawLine(startX + zoomFactor - 1, startY - 1, startX + zoomFactor - 1, startY + zoomFactor - 1);
			gc.drawLine(startX - 1, startY + zoomFactor - 1, startX + zoomFactor - 1, startY + zoomFactor - 1);

			gc.setLineStyle(SWT.LINE_SOLID);
		}

	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed)
	{
		int rows = (int) Math.ceil((double) tiles.length / (double) columns);

		// default values
		int width = columns * zoomFactor * 8;
		int height = rows * zoomFactor * 8;

		if (tiles.length == 0)
		{
			// no tiles -> single tile height & width to add one
			width = zoomFactor * 8;
			height = zoomFactor * 8;
		}
		else if (columns >= tiles.length)
		{
			// all tiles will fit into the first row -> height=1 tile, width =
			// the number of tiles
			width = tiles.length * 8 * zoomFactor;
			height = 8 * zoomFactor;
		}

		// setDebugString("cols: " + columns + "\nrows: " + rows + "\nwidth: "
		// + width + "\nheigth: " + height);

		return new Point(width, height);
	}

	@Override
	public void mouseMove(org.eclipse.swt.events.MouseEvent e)
	{
		int i[] = getClickedColorPosition(e.x, e.y);
		setHover(i);
	}

	/**
	 * The method sets the hover information afresh and redraws
	 * 
	 * @param i
	 *            int array {TileNumber, PixelColumn, PixelRow}
	 */
	private void setHover(int[] i)
	{
		this.hoveringTile = i[0];
		this.hoverPixelColumn = i[1];
		this.hoverPixelRow = i[2];
		// setDebugString("hoveringTile: " + hoveringTile +
		// "\nhoverPixelColumn: "
		// + hoverPixelColumn + "\nhoveringPixelRow: " + hoverPixelRow);
		redraw();
	}

	/**
	 * Takes the mouse events x and y positions and calculates the tile clicked
	 * as well as the pixel columns & pixel rows of the tile/globally where the
	 * event occurred. Returns them as int[] {tileNumber, pixelColumn, pixelRow,
	 * globalPixelColumn, globalPixelRow, originalX, originalY}
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return the int[]
	 */
	public int[] getClickedColorPosition(int x, int y)
	{
		globalPixelColumn = (int) Math.ceil((double) x / (double) zoomFactor);
		globalPixelRow = (int) Math.ceil((double) y / (double) zoomFactor);

		// calculate tile number
		int tileColumn = (int) Math.ceil((double) globalPixelColumn / (double) 8);
		int tileRow = (int) Math.ceil((double) globalPixelRow / (double) 8);
		int tileNumber = (tileRow - 1) * columns + tileColumn - 1;

		// calculate pixelcolumn in tile
		int pixelColumn = (globalPixelColumn % 8) - 1;
		if (pixelColumn == -1) pixelColumn = 7;

		// calculate pixelrow in tile
		int pixelRow = (globalPixelRow % 8) - 1;
		if (pixelRow == -1) pixelRow = 7;

		// pointer on the far right (not on tiles anymore --> don't show
		if (globalPixelColumn > columns * 8)
		{
			return new int[] { -2, 0, 0, globalPixelColumn, globalPixelRow, x, y };
		}
		// pointer after last tile --> don't show
		if (tileNumber > tiles.length - 1)
		{
			return new int[] { -2, 0, 0, globalPixelColumn, globalPixelRow, x, y };
		}

		// setDebugString("TileNumber: " + tileNumber + "\nPixel: " +
		// pixelColumn
		// + "|" + pixelRow + "\nGlobalCol/Row: " + globalPixelColumn
		// + "|" + globalPixelRow);
		int[] ret = new int[] { tileNumber, pixelColumn, pixelRow, globalPixelColumn, globalPixelRow, x, y };
		return ret;
	}

	public InternalTile[] getTiles()
	{
		return tiles;
	}

	public void setTiles(InternalTile[] tiles)
	{
		this.tiles = tiles;
		redraw();
	}

	public int getColumns()
	{
		return columns;
	}

	public void setColumns(int columns)
	{
		this.columns = columns;

		Composite c = getParent();
		if (c instanceof ScrolledComposite)
		{
			// make sure the scrolledcomposite which is (or at least should be)
			// the parent is notified so the scrollbars are altered
			((ScrolledComposite) getParent()).setMinSize(computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
		redraw();
	}

	public int getZoomFactor()
	{
		return zoomFactor;
	}

	public void setZoomFactor(int zoomFactor)
	{
		this.zoomFactor = zoomFactor;

		Composite c = getParent();
		if (c instanceof ScrolledComposite)
		{
			// make sure the scrolledcomposite which is (or at least should be)
			// the parent is notified so the scrollbars are altered
			((ScrolledComposite) getParent()).setMinSize(computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
		redraw();
	}

	public boolean isShowGrid()
	{
		return showGrid;
	}

	public void setShowGrid(boolean showGrid)
	{
		this.showGrid = showGrid;
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

	public boolean isHighlightHoveringTile()
	{
		return highlightHoveringTile;
	}

	public void setHighlightHoveringTile(boolean highlightHoveringTile)
	{
		this.highlightHoveringTile = highlightHoveringTile;
	}

	public boolean isHighlightHoveringPixel()
	{
		return highlightHoveringPixel;
	}

	public void setHighlightHoveringPixel(boolean highlightHoveringPixel)
	{
		this.highlightHoveringPixel = highlightHoveringPixel;
	}

	public boolean isShowPermanentBuffer()
	{
		return showPermanentBuffer;
	}

	public void setShowPermanentBuffer(boolean showBufferedPixels)
	{
		this.showPermanentBuffer = showBufferedPixels;
		redraw();
	}

	public void addPermanentlyBufferedPixels(Vector<InternalPixel> pix)
	{
		for (InternalPixel p : pix)
		{
			permanentBuffer.add(p);
		}
		redraw();
	}

	public void setPermanentBuffer(Vector<InternalPixel> newBuffer)
	{
		this.permanentBuffer = newBuffer;
		if (showPermanentBuffer)
		{
			redraw();
		}
		// setShowPermanentBuffer(true);
	}

	public Vector<InternalPixel> getPermanentBuffer()
	{
		return permanentBuffer;
	}

	/**
	 * returns the rectangle containing the buffer in form of an int[]. 0 =
	 * minCol, 1 = minRow, 2 = maxCol, 3 = maxRow
	 * 
	 * @return the int array
	 */
	public int[] getPermanentBufferRectangle()
	{
		int minCol = 99999999, minRow = 99999999, maxCol = -99999999, maxRow = -99999999;
		if (permanentBuffer != null)
		{
			for (InternalPixel p : permanentBuffer)
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
		return new int[] { minCol, minRow, maxCol, maxRow };
	}

	/**
	 * decides weather or not a click (with the given x and y parameters) was
	 * within the area of the permanent buffer
	 * 
	 * @param x
	 *            mouse click x coordinate
	 * @param y
	 *            mouse click y coordinate
	 * @return true if it was on the marked area, false otherwise
	 */
	public boolean clickedOnPermanentBuffer(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = getPermanentBufferRectangle();
		int minCol = rect[0];
		int minRow = rect[1];
		int maxCol = rect[2];
		int maxRow = rect[3];

		if ((minCol <= col) && (col <= maxCol) && (minRow <= row) && (row <= maxRow))
		{
			return true;
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the top left nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnTopLeftResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minCol = rect[0];
		int minRow = rect[1];

		if (col == minCol && row == minRow)
		{
			return true;
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the top right nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnTopRightResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minRow = rect[1];
		int maxCol = rect[2];

		if (col == maxCol && row == minRow)
		{
			return true;
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the bottom left nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnBottomLeftResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minCol = rect[0];
		int maxRow = rect[3];

		if (col == minCol && row == maxRow)
		{
			return true;
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the bottom right nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnBottomRightResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int maxCol = rect[2];
		int maxRow = rect[3];

		if (col == maxCol && row == maxRow)
		{
			return true;
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the top middle nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnTopMiddleResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minCol = rect[0];
		int minRow = rect[1];
		int maxCol = rect[2];

		if (col >= minCol && col <= maxCol && row == minRow)
		{
			// generally in bounds -> check pixel precision
			int startX = (minCol - 1) * zoomFactor;
			int endX = (maxCol - 1) * zoomFactor + zoomFactor;

			int lowerX = startX - 1 + (int) (Math.floor((endX - startX) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2));
			int higherX = lowerX + zoomFactor;

			if (x >= lowerX && x <= higherX)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the bottom middle nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnBottomMiddleResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minCol = rect[0];
		int maxCol = rect[2];
		int maxRow = rect[3];

		if (col >= minCol && col <= maxCol && row == maxRow)
		{
			// generally in bounds -> check pixel precision
			int startX = (minCol - 1) * zoomFactor;
			int endX = (maxCol - 1) * zoomFactor + zoomFactor;

			int lowerX = endX - (1 + zoomFactor) - (int) (Math.floor((endX - startX) / 2))
			        + ((int) Math.floor((double) zoomFactor / (double) 2));
			int higherX = lowerX + zoomFactor;

			if (x >= lowerX && x <= higherX)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the middle left nob of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnMiddleLeftResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minCol = rect[0];
		int minRow = rect[1];
		int maxRow = rect[3];

		if (row >= minRow && row <= maxRow && col == minCol)
		{
			// generally in bounds -> check pixel precision
			int startY = (minRow - 1) * zoomFactor;
			int endY = (maxRow - 1) * zoomFactor + zoomFactor;

			int lowerY = startY - 1 + (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2));
			int higherY = lowerY + zoomFactor;

			if (y >= lowerY && y <= higherY)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * returns true if the x/y coordinate (pixel precision from a mouse click
	 * event) is located on the top middle right of the permanent buffer
	 * 
	 * @param x
	 *            mouse x position
	 * @param y
	 *            mouse y position
	 * @return true if it is on the nob, false otherwise
	 */
	public boolean clickedOnMiddleRightResizeNob(int x, int y)
	{
		int[] pos = getClickedColorPosition(x, y);
		int col = pos[3];
		int row = pos[4];

		int[] rect = null;
		if (showPermanentBuffer && !temporarilyHidePermanentBuffer)
		{
			rect = getPermanentBufferRectangle();
		}
		else
		{
			rect = getBufferRectangle();
		}
		int minRow = rect[1];
		int maxCol = rect[2];
		int maxRow = rect[3];

		if (row >= minRow && row <= maxRow && col == maxCol)
		{
			// generally in bounds -> check pixel precision
			int startY = (minRow - 1) * zoomFactor;
			int endY = (maxRow - 1) * zoomFactor + zoomFactor;

			int lowerY = endY - 1 - (int) (Math.floor((endY - startY) / 2))
			        - ((int) Math.floor((double) zoomFactor / (double) 2));
			int higherY = lowerY + zoomFactor;

			if (y >= lowerY && y <= higherY)
			{
				return true;
			}
		}
		return false;
	}

	public void clearPermanentBuffer()
	{
		permanentBuffer.clear();
		redraw();
	}

	public boolean isShowBufferedPixels()
	{
		return showBufferedPixels;
	}

	public void setShowBufferedPixels(boolean showBufferedPixels)
	{
		this.showBufferedPixels = showBufferedPixels;
		redraw();
	}

	public void addBufferedPixels(Vector<InternalPixel> pix)
	{
		for (InternalPixel p : pix)
		{
			bufferedPixels.add(p);
		}
		redraw();
	}

	public Vector<InternalPixel> getPixelBuffer()
	{
		return bufferedPixels;
	}

	/**
	 * returns the rectangle containing the buffer in form of an int[]. 0 =
	 * minCol, 1 = minRow, 2 = maxCol, 3 = maxRow
	 * 
	 * @return the int array
	 */
	public int[] getBufferRectangle()
	{
		int minCol = 99999999, minRow = 99999999, maxCol = -99999999, maxRow = -99999999;
		if (bufferedPixels != null)
		{
			for (InternalPixel p : bufferedPixels)
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
		return new int[] { minCol, minRow, maxCol, maxRow };
	}

	public void clearPixelBuffer()
	{
		bufferedPixels.clear();
		redraw();
	}

	public boolean isDrawMarkingRectangle()
	{
		return drawMarkingRectangle;
	}

	public void setDrawMarkingRectangle(boolean drawMarkingRectangle)
	{
		if (drawMarkingRectangle != this.drawMarkingRectangle)
		{
			this.drawMarkingRectangle = drawMarkingRectangle;
			redraw();
		}
	}

	public void setMarkingBounds(int startCol, int startRow, int endCol, int endRow)
	{
		this.startMarkCol = startCol;
		this.endMarkCol = endCol;
		this.startMarkRow = startRow;
		this.endMarkRow = endRow;
		redraw();
	}

	public int getTempLineCircleSize()
	{
		return tempLineCircleSize;
	}

	public void setTempLineCircleSize(int tempLineCircleSize)
	{
		this.tempLineCircleSize = tempLineCircleSize;
	}

	public int getTempLineSquareSize()
	{
		return tempLineSquareSize;
	}

	public void setTempLineSquareSize(int tempLineSquareSize)
	{
		this.tempLineSquareSize = tempLineSquareSize;
	}

	public void setDrawTempLineCircle(boolean drawTempLineCircle)
	{
		this.drawTempLineCircle = drawTempLineCircle;
	}

	public void setDrawTempLineSquare(boolean drawTempLineSquare)
	{
		this.drawTempLineSquare = drawTempLineSquare;
	}

	/**
	 * copies the perm buffer into the UI only temp buffer (for instance for
	 * moving/resizing the buffer without constantly updating the model and
	 * waiting for UI refreshes
	 */
	public void copyPermIntoTemp()
	{
		// copy contents of permanent buffer into non permanent buffer
		bufferedPixels.clear();
		for (InternalPixel p : permanentBuffer)
		{
			bufferedPixels.add(p);
		}
	}

	public void setTemporarilyHidePermanentBuffer(boolean val)
	{
		this.temporarilyHidePermanentBuffer = val;
		redraw();
	}

	public boolean isTemporarilyHidePermanentBuffer()
	{
		return temporarilyHidePermanentBuffer;
	}

	/**
	 * moves the temporary buffer for the delta values passed
	 * 
	 * @param deltaCol
	 * @param deltaRow
	 */
	public void moveTempBuffer(int deltaCol, int deltaRow)
	{
		if (deltaCol != 0 || deltaRow != 0)
		{
			for (InternalPixel pix : bufferedPixels)
			{
				pix.setCol(pix.getCol() + deltaCol);
				pix.setRow(pix.getRow() + deltaRow);
			}
			startMarkCol += deltaCol;
			startMarkRow += deltaRow;
			endMarkCol += deltaCol;
			endMarkRow += deltaRow;
			redraw();
		}
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		this.focus = true;
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		this.focus = false;
	}

	public boolean hasFocus()
	{
		return this.focus;
	}

	public void setRectangleOnTempBuffer(boolean val)
	{
		this.drawRectangleOnTempBuffer = val;
		redraw();
	}

	public void addSelectionListener(SelectionListener listener)
	{
		addListener(SWT.Selection, new TypedListener(listener));
	}

	public void removeSelectionListener(SelectionListener listener)
	{
		removeListener(SWT.Selection, listener);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection)
	{
		System.out.println("tilesetPanel selected");
	}

}
