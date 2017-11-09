package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Tileset;

public class SetZoomFactorCommand extends ChangeCommand
{

	// the tileset object
	private final Tileset tileset;
	// the new index of the left selected color
	private final Integer newZoomfactor;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public SetZoomFactorCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		tileset = parameter.getTileset();
		newZoomfactor = parameter.getNewIndex();

		// set label for undo/redo
		setLabel("Change zoom factor to " + newZoomfactor);
	}

	@Override
	protected void doExecute()
	{
		tileset.setZoomFactor(newZoomfactor);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && newZoomfactor != null
				&& newZoomfactor <= 100 && newZoomfactor > 0)
		{
			return true;
		}
		return false;
	}

}
