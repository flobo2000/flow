package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.GlobalPixel;
import tileset.Pixel;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

public class UnmarkAreaCommand extends ChangeCommand
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
	protected TilesetFactory tilesetFactory = tilesetPackage.getTilesetFactory();
	// the tileset object
	private final Tileset tileset;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public UnmarkAreaCommand(final Collection<Notifier> notifiers, final CommandParameter parameter)
	{
		super(notifiers);

		// prepare any variables needed for execution
		tileset = parameter.getTileset();

		// set label for undo/redo
		setLabel(getCommandName());
	}

	/**
	 * should be overwritten by subclasses. returns the name to be displayed in
	 * the undo/redo action section
	 * 
	 * @return the commands name (eg. draw rectangle)
	 */
	public String getCommandName()
	{
		return "unmark area";
	}

	@Override
	protected void doExecute()
	{
		// copy all content of the permanent buffer back into the tileset
		EList<GlobalPixel> buffer = tileset.getPermanentBuffer().getBufferedPixels();
		for (GlobalPixel pix : buffer)
		{
			int globalCol = pix.getColumn();
			int globalRow = pix.getRow();
			int color = pix.getColorIndex();

			// calculate tile, row and column of the tile
			int tileColumn = (int) Math.ceil((double) globalCol / (double) 8);
			int tileRow = (int) Math.ceil((double) globalRow / (double) 8);
			int tileNumber = (tileRow - 1) * tileset.getColumns() + tileColumn - 1;

			if (tileNumber >= 0 && tileNumber < tileset.getTiles().size())
			{
				// calculate pixelcolumn in tile
				int pixelColumn = (globalCol % 8) - 1;
				if (pixelColumn == -1) pixelColumn = 7;

				// calculate pixelrow in tile
				int pixelRow = (globalRow % 8) - 1;
				if (pixelRow == -1) pixelRow = 7;

				// create the new pixel and add to the tileset
				Pixel pixel = tilesetFactory.createPixel();
				pixel.setColorIndex(color);
				tileset.getTiles().get(tileNumber).getPixelColumns().get(pixelColumn).getPixels().set(pixelRow, pixel);
			}
		}
		// clear the permanent buffer
		tileset.setPermanentBuffer(null);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null
		/*
		 * && !this.tileset.getPermanentBuffer().getBufferedPixels() .isEmpty()
		 */)
		{
			return true;
		}
		return false;
	}
}
