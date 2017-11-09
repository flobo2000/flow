/**
 * 
 */
package com.flow.snesde.core.model.objects;

import java.util.HashSet;

import org.eclipse.core.resources.IFile;

/**
 * @author flo
 * 
 */
public class TilesetObject extends AbstractModelObject
{

	/**
	 * the number of colors supported by this palette
	 */
	private final int encoding = 16;

	/**
	 * list of possible encodings
	 */
	private final HashSet<Integer> possibleEncodings = new HashSet<Integer>();

	/**
	 * @param res
	 *            the Resource the paletteObject is being created by
	 */
	public TilesetObject(IFile file)
	{
		super(file);
		// TODO: handle resource to fill relevant metadata
		// res.get
	}
}
