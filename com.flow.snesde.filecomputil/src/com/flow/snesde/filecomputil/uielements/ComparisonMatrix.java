package com.flow.snesde.filecomputil.uielements;

import java.util.Vector;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

import com.flow.snesde.filecomputil.pojos.ColorBytePojo;
import com.flow.snesde.uilib.generic.UilibComposite;

/**
 * @author flo
 */
public class ComparisonMatrix extends UilibComposite
{

	/**
	 * Temporarily used to display colors even though no input was set
	 */
	private final Vector<Color>	colorss							= new Vector<Color>();

	/**
	 * The internal selection number for the left mouse button
	 */
	private int									leftSelectionIndex	= 0;
	/**
	 * The internal selection number for the right mouse button
	 */
	private int									rightSelectionIndex	= 1;

	/**
	 * The container for the model. May be of type Palette or PaletteObject
	 */
	Object											pal									= null;

	/**
	 * The width of one color blob
	 */
	private int									blobwidth;

	/**
	 * the height of one color blob
	 */
	private int									blobheight;

	private ColorBytePojo				byteCounter					= null;

	/**
	 * @param parent
	 *          the parent composite to add this one to
	 * @param style
	 *          the style constant to use
	 */
	public ComparisonMatrix(final Composite parent, final int style)
	{
		super(parent, style);

		byteCounter = new ColorBytePojo(1, 1);

		// Random randGen = new Random(System.currentTimeMillis());
		// for (int i = 0; i < 256; i++)
		// {
		// int r = Math.abs(randGen.nextInt()) % 255;
		// int g = Math.abs(randGen.nextInt()) % 255;
		// int b = Math.abs(randGen.nextInt()) % 255;
		//
		// this.colorss.add(createColor(r, g, b));
		// }

		this.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseDoubleClick(final MouseEvent e)
			{
			}

			@Override
			public void mouseDown(final MouseEvent e)
			{
			}

			@Override
			public void mouseUp(final MouseEvent e)
			{
				int i = getClickedColorIndex(e.x, e.y);
				if (e.button == 1)
				{
					setLeftSelectionIndex(i);
				}
				else if (e.button == 3)
				{
					setRightSelectionIndex(i);
				}
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.flow.snesde.digui.DigUiComposite#drawContent(org.eclipse
	 * .swt.events .PaintEvent)
	 */
	@Override
	public void drawWidget(final PaintEvent e, final GC gc, final int width,
			final int height)
	{

		// get number of pixels available to draw on --> decide how big every byte
		// should be in the display --> should it be a box with height and width or
		// should it simply be a line of a given number of pixels?
		int maxHeight = getBounds().height - 1;
		int maxWidth = getBounds().width - 1;

		long numberOfBytesToDisplay = byteCounter.getNumberOfBytesToDisplay();
		long numberOfPixelsAvailable = maxHeight * maxWidth;

		// number of pixels available exceeds number of bytes to be displayed?
		if (Math.floor(numberOfPixelsAvailable / numberOfBytesToDisplay) >= 1)
		{
			// yes--> calculate size of pixels to draw
			int numberOfPixelsForEachByte = (int) Math.floor(numberOfPixelsAvailable
					/ numberOfBytesToDisplay);

			long[] primeFactors = primeFactors(numberOfPixelsForEachByte);

			int rectWidth = 0;
			int rectHeight = 0;
			if (primeFactors.length == 1)
			{
				// number of pixels is a prime number --> draw number a line for each
				// byte
				rectWidth = numberOfPixelsForEachByte;
				rectHeight = 1;
			}
			else if (primeFactors.length > 1)
			{
				// number of pixels can be broken down to multiple prime numbers -->
				// calculate rectangle sizes
				for (int i = 0; i < primeFactors.length; i++)
				{
					if (i % 2 == 0)
					{
						// even number
						rectWidth += primeFactors[i];
					}
					else
					{
						// odd number
						rectHeight += primeFactors[i];
					}
				}
			}

			if (rectHeight == 0) rectHeight = 1;
			if (rectWidth == 0) rectWidth = 1;
			// System.out.println("Number of Pixels for each byte: "
			// + numberOfPixelsForEachByte);
			// System.out.println("Height/Width: " + maxHeight + "/" + maxWidth);
			// System.out.println("Blog h/w: " + rectHeight + "/" + rectWidth);

			// calculate number of columns to draw with given height/width
			int maxCols = maxWidth / rectWidth;
			int maxRows = maxHeight / rectHeight;

			// System.out.println("Maximum number of columns: " + maxCols);
			// System.out.println("Maximum number of rows: " + maxRows);

			gc.setBackground(createColor(0, 0, 0));
			gc.fillRectangle(0, 0, maxWidth, maxHeight);

			for (int row = 0; row < maxRows; row++)
			{
				for (int col = 0; col < maxCols; col++)
				{
					int[] color = byteCounter.getColorForByteAtIndex(row * maxCols + col);
					if (color == null) break;
					gc.setBackground(createColor(color[0], color[1], color[2]));

					// if (row == 0 && col == 37)
					// {
					// System.out.println("drawing rectangle at row/col: " + row + "/"
					// + col);
					// System.out.println("drawing with color r|g|b: " + color[0] + "|"
					// + color[1] + "|" + color[2]);
					// gc.setBackground(createColor(0, 0, 255));
					// }

					gc.fillRectangle(col * rectWidth, row * rectHeight, rectWidth,
							rectHeight);
				}
			}
		}
		else
		{
			// TODO: draw image offscreen and scale with appropriate algorithm so it
			// fits into the smaller size of the area available for display
		}

		// if (this.colorss.size() == 256)
		// {
		// this.blobwidth = (int) Math.floor(((width / 26) - 1));
		// this.blobheight = (int) Math.floor(((height / 10) - 1));
		//
		// // current index of the color arrays
		// int i = 0;
		//
		// // draw all palette colors
		// for (int row = 0; row < 10; row++)
		// {
		// for (int col = 0; col < 26; col++)
		// {
		// // handle last four color places in last row --> leave empty
		// if (i >= 256)
		// {
		// // do nothing... don't paint these squares
		// }
		// else
		// {
		// // draw color
		// gc.setBackground(this.colorss.get(i));
		// gc.fillRectangle(1 + (col * (this.blobwidth + 1)),
		// 1 + (row * (this.blobheight + 1)), this.blobwidth,
		// this.blobheight);
		// // draw selection frame if necessary
		// if (i == this.leftSelectionIndex || i == this.rightSelectionIndex)
		// {
		// gc.setForeground(createColor(0, 0, 0));
		// gc.drawRectangle(1 + (col * (this.blobwidth + 1)),
		// 1 + (row * (this.blobheight + 1)), this.blobwidth,
		// this.blobheight);
		// }
		// }
		// i++;
		// }
		// }
		// }
		// else if (this.colorss.size() == 16)
		// {
		// // draw the 16 color palette
		// // calculate width and height of each color blob
		// this.blobwidth = (int) Math.floor(((width / 8) - 3));
		// this.blobheight = (int) Math.floor(((height / 2) - 3));
		//
		// // current index of the color arrays
		// int i = 0;
		//
		// // draw all palette colors
		// for (int row = 0; row < 2; row++)
		// {
		// for (int col = 0; col < 8; col++)
		// {
		// // handle last four color places in last row --> leave empty
		// gc.setBackground(this.colorss.get(i));
		// gc.fillRectangle(1 + (col * (this.blobwidth + 3)),
		// 1 + (row * (this.blobheight + 3)), this.blobwidth,
		// this.blobheight);
		// // draw selection border if necessary
		// if (i == this.leftSelectionIndex || i == this.rightSelectionIndex)
		// {
		// gc.setForeground(createColor(0, 0, 0));
		// gc.drawRectangle(1 + (col * (this.blobwidth + 3)),
		// 1 + (row * (this.blobheight + 3)), this.blobwidth,
		// this.blobheight);
		// }
		// i++;
		// }
		//
		// }
		// }
		// else if (this.colorss.size() == 8)
		// {
		// // draw the 8 color palette
		// // calculate width and height of each color blob
		// this.blobwidth = (int) Math.floor(((width / 8) - 3));
		// this.blobheight = (int) Math.floor(((height) - 3));
		//
		// // current index of the color arrays
		// int i = 0;
		//
		// // draw all palette colors
		// for (int col = 0; col < 8; col++)
		// {
		// // handle last four color places in last row --> leave empty
		// gc.setBackground(this.colorss.get(i));
		// gc.fillRectangle(1 + (col * (this.blobwidth + 3)), 1, this.blobwidth,
		// this.blobheight);
		// // draw selection highlighter if necessary
		// if (i == this.leftSelectionIndex || i == this.rightSelectionIndex)
		// {
		// gc.setForeground(createColor(0, 0, 0));
		// gc.drawRectangle(1 + (col * (this.blobwidth + 3)), 1, this.blobwidth,
		// this.blobheight);
		// }
		// i++;
		// }
		// }
		// else if (this.colorss.size() == 4)
		// {
		// // draw the 4 color palette
		// // calculate width and height of each color blob
		// this.blobwidth = (int) Math.floor(((width / 4) - 3));
		// this.blobheight = (int) Math.floor(((height) - 3));
		//
		// // current index of the color arrays
		// int i = 0;
		//
		// // draw all palette colors
		// for (int col = 0; col < 4; col++)
		// {
		// // handle last four color places in last row --> leave empty
		// gc.setBackground(this.colorss.get(i));
		// gc.fillRectangle(1 + (col * (this.blobwidth + 3)), 1, this.blobwidth,
		// this.blobheight);
		// // draw selection highlighter if necessary
		// if (i == this.leftSelectionIndex || i == this.rightSelectionIndex)
		// {
		// gc.setForeground(createColor(0, 0, 0));
		// gc.drawRectangle(1 + (col * (this.blobwidth + 3)), 1, this.blobwidth,
		// this.blobheight);
		// }
		// i++;
		// }
		// }
		// else if (this.colorss.size() == 2)
		// {
		// // draw the 2 color palette
		// // calculate width and height of each color blob
		// this.blobwidth = (int) Math.floor(((width / 2) - 3));
		// this.blobheight = (int) Math.floor(((height) - 3));
		//
		// // current index of the color arrays
		// int i = 0;
		//
		// // draw all palette colors
		// for (int col = 0; col < 2; col++)
		// {
		// // handle last four color places in last row --> leave empty
		// gc.setBackground(this.colorss.get(i));
		// gc.fillRectangle(1 + (col * (this.blobwidth + 3)), 1, this.blobwidth,
		// this.blobheight);
		// // draw selection highlighter if necessary
		// if (i == this.leftSelectionIndex || i == this.rightSelectionIndex)
		// {
		// gc.setForeground(createColor(0, 0, 0));
		// gc.drawRectangle(1 + (col * (this.blobwidth + 3)), 1, this.blobwidth,
		// this.blobheight);
		// }
		// i++;
		// }
		// }
		// else
		// {
		// Color red = createColor(255, 0, 0);
		// gc.setForeground(red);
		// gc.drawString("Test", 1, 1);
		//
		// gc.drawRectangle(10, 10, 20, 20);
		// }
	}

	/**
	 * returns the index of the color that is at pixel x/y
	 * 
	 * @param x
	 *          (x coordinate of the clicked event)
	 * @param y
	 *          (y coordinate of the clicked event)
	 * @return i (the index number of the clicked color
	 */
	private int getClickedColorIndex(final int x, final int y)
	{
		// depending on the number of colors, the colors have different sensitive
		// areass
		if (this.colorss.size() == 256)
		{
			// scale factor one: 7x7 blobs
			int col = ((Double) (Math.ceil(((double) x / (double) this.blobwidth))))
					.intValue() - 1;
			int row = ((Double) (Math.ceil(((double) y / (double) this.blobheight))))
					.intValue();
			if (col < 0)
			{
				col = 0;
			}
			if (col >= 26)
			{
				col = 25;
			}
			if (row < 0)
			{
				row = 0;
			}
			if (row >= 11)
			{
				row = 10;
			}
			return ((26 * (row - 1)) + col);
		}
		else if (this.colorss.size() == 16)
		{
			// 23x36
			int col = ((Double) (Math.ceil(((double) x / (double) 23)))).intValue() - 1;
			int row = ((Double) (Math.ceil(((double) y / (double) 36)))).intValue();
			if (col < 0)
			{
				col = 0;
			}
			if (col >= 8)
			{
				col = 7;
			}
			if (row < 0)
			{
				row = 0;
			}
			if (row >= 3)
			{
				row = 2;
			}
			return ((8 * (row - 1)) + col);
		}
		else if (this.colorss.size() == 8)
		{
			// 23
			int col = ((Double) (Math.ceil(((double) x / (double) 23)))).intValue() - 1;
			if (col < 0)
			{
				col = 0;
			}
			if (col >= 8)
			{
				col = 7;
			}
			return col;
		}
		else if (this.colorss.size() == 4)
		{
			// 46
			int col = ((Double) (Math.ceil(((double) x / (double) 46)))).intValue() - 1;
			if (col < 0)
			{
				col = 0;
			}
			if (col >= 4)
			{
				col = 3;
			}
			return col;
		}
		else
		// if (numberOfColors == 2)
		{
			// 92
			int col = ((Double) (Math.ceil(((double) x / (double) 92)))).intValue() - 1;
			if (col < 0)
			{
				col = 0;
			}
			if (col >= 2)
			{
				col = 1;
			}
			return col;
		}
	}

	/**
	 * @return the rightSelectionIndex
	 */
	public int getRightSelectionIndex()
	{
		return this.rightSelectionIndex;
	}

	/**
	 * @param rightSelectionIndex
	 *          the rightSelectionIndex to set
	 */
	public void setRightSelectionIndex(final int rightSelectionIndex)
	{
		this.rightSelectionIndex = rightSelectionIndex;
		redraw();
	}

	/**
	 * @return the leftSelectionIndex
	 */
	public int getLeftSelectionIndex()
	{
		return this.leftSelectionIndex;
	}

	/**
	 * @param leftSelectionIndex
	 *          the leftSelectionIndex to set
	 */
	public void setLeftSelectionIndex(final int leftSelectionIndex)
	{
		this.leftSelectionIndex = leftSelectionIndex;
		redraw();
	}

	// public void setNumberOfRectanglesToDraw(long maxFileSize)
	// {
	// this.numberOfPixelsToDraw = maxFileSize;
	// }

	public void setBytesToDisplay(ColorBytePojo byteCounter)
	{
		this.byteCounter = byteCounter;
	}

	/**
	 * Berechnet die Primfaktoren, aus denen sich die Zahl n zusammensetzt.
	 * Multipliziert man diese, ergibt sich die Zahl. Zurückgegeben werden die
	 * Zahlen in einem Array, das soviele Elemente hat wie n Primfaktoren. Sie
	 * sind aufsteigend sortiert.
	 * 
	 * @param n
	 *          Die zu zerlegende Zahl
	 * @return Die Primfaktoren in einem Array
	 */
	private long[] primeFactors(long n)
	{

		/*
		 * Die Vorgehensweise ist folgende: Aufgrund der Vorgabe, dass das
		 * zurückgegebene Array maximal soviele Elemente haben darf wie die Zahl n
		 * Primfaktoren hat, erzeugen wir zunächst ein "temporäres" Array tmp,
		 * dessen Länge durch maxFactors angegeben wird. Dies geschieht aus einer
		 * Überlegung zum Speicherverbrauch: Man könnte tmp auch mit der Länge n
		 * initialisieren, allerdings ist dies aus Effizienzgesichtspunkten eher
		 * suboptimal, da jede Zahl maximal eine gewisse Anzahl an Primfaktoren
		 * haben kann. Da 2 der kleinstmögliche Primfaktor ist, ist die Anzahl der
		 * Primfaktoren immer kleiner gleich dem Exponenten der nächst- höheren
		 * Zweierpotenz. Daraus folgt: n <= 2^x log(n) <= log (2^x) x >= log (n) /
		 * log(2) Mit x als maximaler Anzahl der Primfaktoren der Zahl n.
		 */

		// Maximale Faktoranzahl ermitteln
		int maxFactors = (int) Math.ceil(Math.log10(n) / Math.log10(2));

		// Temporäres Array erzeugen
		long[] tmp = new long[maxFactors];

		// Zähler der tatsächlichen Faktoranzahl initialisieren
		int anzahlFaktoren = 0;

		/*
		 * Jetzt kommt der Trick der Zerlegung: In einer Zählschleife wird
		 * wiederholt von 2 (kleinster Primfaktor) bis n (Zahl) gezählt, wobei bei
		 * jedem Durchlauf überprüft wird, ob die Zählvariable ganzzahliger Teiler
		 * der Zahl ist. Ist dies der Fall, ist ein neuer Primfaktor gefunden.
		 * Dieser wird in tmp gesichert, und die ganze Schleife wird
		 * "zurückgesetzt", indem der Zähler erneut bei 2 (1++) beginnt und n durch
		 * n/Primfaktor ersetzt wird.
		 */
		for (long j = 2; j <= n; j++)
		{
			// Ist j Primfaktor?
			if (n % j == 0)
			{
				// Primfaktor sichern und Anzahl der Primfaktoren erhöhen
				tmp[anzahlFaktoren++] = j;
				// n ändern
				n = n / j;
				// j erneut auf Startwert 2 (1++) setzen
				j = 1;
			}
		}
		// Rückgabearray erzeugen, mit Länge der tatsächlichen Anzahl
		// von Primfaktoren
		long[] prf = new long[anzahlFaktoren];
		// Überführen der Werte des temporären Arrays in das
		// Rückgabearray
		for (int i = 0; i < anzahlFaktoren; i++)
		{
			prf[i] = tmp[i];
		}
		// Rückgabe
		return prf;
	}

	// @Override
	// /**
	// * This method will calculate the default size of the control
	// */
	// public Point computeSize(final int wHint, final int hHint,
	// final boolean changed)
	// {
	// // TODO: implement
	// // get number of colors displayed
	// // calculate what the minimum size has to be in this case
	// // compare with wHint and hHint and compute preferred size
	//
	// // TODO: replace this dummy
	// return new Point(wHint, hHint);
	// }

}
