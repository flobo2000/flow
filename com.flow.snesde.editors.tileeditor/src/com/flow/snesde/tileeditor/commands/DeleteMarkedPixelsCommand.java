package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;
import tileset.Pixel;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

public class DeleteMarkedPixelsCommand extends ChangeCommand
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

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public DeleteMarkedPixelsCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
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
		return "Delete marked pixels";
	}

	@Override
	protected void doExecute()
	{
		// clear the permanent buffer
		GlobalPixelBuffer newPerm = tilesetFactory.createGlobalPixelBuffer();
		tileset.setPermanentBuffer(newPerm);
		tileset.eResource().getContents().add(newPerm);
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
