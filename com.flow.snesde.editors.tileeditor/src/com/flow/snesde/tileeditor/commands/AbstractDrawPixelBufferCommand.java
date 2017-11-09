package com.flow.snesde.tileeditor.commands;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Pixel;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

import com.flow.snesde.uilib.generic.InternalPixel;

public abstract class AbstractDrawPixelBufferCommand extends ChangeCommand
{

	/**
	 * This caches an instance of the model package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TilesetPackage tilesetPackage = TilesetPackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TilesetFactory tilesetFactory = tilesetPackage
			.getTilesetFactory();
	// the tileset object
	private final Tileset tileset;
	private final Vector<InternalPixel> pixelBuffer;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public AbstractDrawPixelBufferCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		tileset = parameter.getTileset();
		pixelBuffer = parameter.getPixelBuffer();

		// set label for undo/redo
		setLabel(getCommandName());
	}

	/**
	 * should be overwritten by subclasses. returns the name to be displayed in
	 * the undo/redo action section
	 * 
	 * @return the commands name (eg. draw rectangle)
	 */
	public abstract String getCommandName();

	@Override
	protected void doExecute()
	{
		for (InternalPixel p : pixelBuffer)
		{
			int globalPixelColumn = p.getCol();
			int globalPixelRow = p.getRow();
			int colorToSet = p.getIndex();

			// calculate the tile we want to set the color in
			int columns = tileset.getColumns();
			int tileCol = (int) Math.ceil((double) globalPixelColumn
					/ (double) 8);
			int tileRow = (int) Math.ceil((double) globalPixelRow / (double) 8);
			int tileNumber = (tileRow - 1) * columns + tileCol - 1;

			// calculate pixelcolumn in tile
			int pixelColInTile = (globalPixelColumn % 8) - 1;
			if (pixelColInTile == -1) pixelColInTile = 7;

			// calculate pixelrow in tile
			int pixelRowInTile = (globalPixelRow % 8) - 1;
			if (pixelRowInTile == -1) pixelRowInTile = 7;

			// set the pixel value
			if (globalPixelColumn >= 0 && globalPixelRow >= 0
					&& tileset.getTiles().size() > tileNumber
					&& tileNumber >= 0)
			{
				Pixel pix = tilesetFactory.createPixel();
				pix.setColorIndex(colorToSet);
				tileset.getTiles().get(tileNumber).getPixelColumns()
						.get(pixelColInTile).getPixels()
						.set(pixelRowInTile, pix);
			}
		}
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && pixelBuffer != null
				&& !pixelBuffer.isEmpty())
		{
			return true;
		}
		return false;
	}
}
