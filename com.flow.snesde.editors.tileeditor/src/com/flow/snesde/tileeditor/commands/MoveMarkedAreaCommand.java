package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

public class MoveMarkedAreaCommand extends ChangeCommand
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
	private final Integer deltaCol;
	private final Integer deltaRow;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public MoveMarkedAreaCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// prepare any variables needed for execution
		tileset = parameter.getTileset();
		deltaCol = parameter.getDeltaCol();
		deltaRow = parameter.getDeltaRow();

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
		return "move marked area";
	}

	@Override
	protected void doExecute()
	{
		EList<GlobalPixel> bufferedPixels = tileset.getPermanentBuffer()
				.getBufferedPixels();

		GlobalPixelBuffer newBuffer = tilesetFactory.createGlobalPixelBuffer();
		for (GlobalPixel pix : bufferedPixels)
		{
			GlobalPixel newPix = tilesetFactory.createGlobalPixel();
			newPix.setColumn(pix.getColumn() + deltaCol);
			newPix.setRow(pix.getRow() + deltaRow);
			newPix.setColorIndex(pix.getColorIndex());
			newBuffer.getBufferedPixels().add(newPix);
		}
		tileset.setPermanentBuffer(newBuffer);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && this.deltaCol != null
				&& this.deltaRow != null)
		{
			return true;
		}
		return false;
	}
}
