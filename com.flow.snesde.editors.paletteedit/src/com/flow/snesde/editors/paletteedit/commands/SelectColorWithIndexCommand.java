package com.flow.snesde.editors.paletteedit.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import palette.Color;
import palette.Palette;

public class SelectColorWithIndexCommand extends ChangeCommand
{
	// the palette object
	private final Palette palette;
	// the index to set
	private final Integer selectionIndex;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public SelectColorWithIndexCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		palette = parameter.getPalette();
		selectionIndex = parameter.getSelectionIndex();

		// set label for undo/redo
		setLabel("Select color " + selectionIndex);
	}

	@Override
	protected void doExecute()
	{
		palette.setLeftSelectedIndex(selectionIndex);
		palette.setRightSelectedIndex(selectionIndex);
		Color color = palette.getColors().get(selectionIndex);
		palette.setLeftSelectedColor(color);
		palette.setRightSelectedColor(color);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.palette != null && selectionIndex != null)
		{
			// selectionindex must be in bounds of color palette size
			if (selectionIndex >= 0
					&& palette.getColors().size() - 1 >= selectionIndex)
			{
				return true;
			}
		}
		return false;
	}

}
