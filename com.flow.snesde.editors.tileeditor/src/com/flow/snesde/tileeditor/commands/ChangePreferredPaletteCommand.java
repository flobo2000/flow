package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Tileset;

public class ChangePreferredPaletteCommand extends ChangeCommand
{

	// the tileset object
	private final Tileset tileset;
	// the new name of the preferred palette
	private final String palette;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public ChangePreferredPaletteCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		tileset = parameter.getTileset();
		palette = parameter.getPaletteName();

		// set label for undo/redo
		setLabel("Change palette to " + palette);
	}

	@Override
	protected void doExecute()
	{
		tileset.setPaletteName(palette);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.palette != null && palette != null)
		{
			return true;
		}
		return false;
	}

}
