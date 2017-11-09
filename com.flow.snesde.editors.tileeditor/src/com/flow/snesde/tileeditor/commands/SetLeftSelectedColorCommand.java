package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Tileset;

public class SetLeftSelectedColorCommand extends ChangeCommand
{

	// the tileset object
	private final Tileset tileset;
	// the new index of the left selected color
	private final Integer newLeftIndex;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public SetLeftSelectedColorCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		tileset = parameter.getTileset();
		newLeftIndex = parameter.getNewIndex();

		// set label for undo/redo
		setLabel("Change left selected color to " + newLeftIndex);
	}

	@Override
	protected void doExecute()
	{
		tileset.setLeftIndex(newLeftIndex);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && newLeftIndex != null)
		{
			return true;
		}
		return false;
	}

}
