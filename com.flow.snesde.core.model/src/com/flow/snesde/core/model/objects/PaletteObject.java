/**
 * 
 */
package com.flow.snesde.core.model.objects;

import org.eclipse.core.resources.IFile;

import palette.Palette;

/**
 * @author flo
 * 
 */
public class PaletteObject extends AbstractModelObject
{

	/**
	 * the number of colors supported by this palette
	 */
	private int numOfColors = 16;

	/**
	 * Constructor
	 * 
	 * @param res
	 *            the Resource the paletteObject is being created by
	 */
	public PaletteObject(final IFile file)
	{
		super(file);

		Palette p = (Palette) getEmfModelForFile(file);
		this.numOfColors = p.getColors().size();
	}

	/**
	 * Returns the number of colors used by the palette
	 * 
	 * @return the number of colors
	 */
	public int getNumberOfColors()
	{
		return numOfColors;
	}
}
