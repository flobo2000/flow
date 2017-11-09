package com.flow.snesde.editors.paletteedit.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import palette.Palette;

public class RemoveColorFromPaletteCommand extends ChangeCommand
{
	// the palette object
	private final Palette palette;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public RemoveColorFromPaletteCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		palette = parameter.getPalette();

		// set label for undo/redo
		setLabel("Remove selected color from palette.");
	}

	@Override
	protected void doExecute()
	{
		int i = palette.getLeftSelectedIndex();
		palette.getColors().remove(i);
		palette.setLeftSelectedIndex(0);
		palette.setRightSelectedIndex(0);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.palette != null && this.palette.getColors().size() > 1)
		{
			return true;
		}
		return false;
	}

}
