package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Tileset;

public class SetRightSelectedColorCommand extends ChangeCommand
{

	// the tileset object
	private final Tileset tileset;
	// the new index of the left selected color
	private final Integer newRightIndex;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public SetRightSelectedColorCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		tileset = parameter.getTileset();
		newRightIndex = parameter.getNewIndex();

		// set label for undo/redo
		setLabel("Change right selected color to " + newRightIndex);
	}

	@Override
	protected void doExecute()
	{
		tileset.setRightIndex(newRightIndex);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && newRightIndex != null)
		{
			return true;
		}
		return false;
	}

}
