package com.flow.snesde.tileeditor.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;

/**
 * This class is a wrapper for the data transfer of image objects.
 */
public class ImageTransferable implements Transferable
{
	/**
	 * Constructs the selection.
	 * 
	 * @param img
	 *            an image
	 */
	public ImageTransferable(BufferedImage img)
	{
		theImage = img;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors()
	{
		return new DataFlavor[] { DataFlavor.imageFlavor };
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return flavor.equals(DataFlavor.imageFlavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
	{
		if (flavor.equals(DataFlavor.imageFlavor))
		{
			return theImage;
		}
		else
		{
			throw new UnsupportedFlavorException(flavor);
		}
	}

	private BufferedImage theImage;
}