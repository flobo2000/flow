package com.flow.snesde.tileeditor.commands;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import com.flow.snesde.uilib.generic.InternalPixel;

import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

public class ResizeMarkedPixelsCommand extends ChangeCommand
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
	private Vector<InternalPixel> tempBuffer;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public ResizeMarkedPixelsCommand(final Collection<Notifier> notifiers, final CommandParameter parameter)
	{
		super(notifiers);

		// prepare any variables needed for execution
		tileset = parameter.getTileset();
		tempBuffer = parameter.getPixelBuffer();

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
		return "Resize marked pixels";
	}

	@Override
	protected void doExecute()
	{
		GlobalPixelBuffer newBuffer = tilesetFactory.createGlobalPixelBuffer();
		// copy all content of the shared buffer back into the tileset
		for (InternalPixel pix : tempBuffer)
		{
			int globalCol = pix.getCol();
			int globalRow = pix.getRow();
			int color = pix.getIndex();

			// create the new global pixel out of it
			GlobalPixel pixl = tilesetFactory.createGlobalPixel();
			pixl.setColumn(globalCol);
			pixl.setRow(globalRow);
			pixl.setColorIndex(color);
			// put it into the permanent buffer
			newBuffer.getBufferedPixels().add(pixl);
		}
		// assign the new pixel buffer
		tileset.setPermanentBuffer(newBuffer);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && tempBuffer != null && !tempBuffer.isEmpty())
		{
			return true;
		}
		return false;
	}
}
