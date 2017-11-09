package com.flow.snesde.editors.paletteedit.commands;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edit.command.ChangeCommand;

import palette.Palette;

public class CommandFactory
{

	public static ChangeCommand create(
			final Class<? extends ChangeCommand> commandClass,
			final CommandParameter parameter)
	{
		if (parameter == null)
		{
			return null;
		}

		// get all potential model references for notifications. This is
		// necessary as we need to have the correct model references available
		// when undoing model changes (undoing recorded model changes in
		// ChangeCommand).
		Palette palette = parameter.getPalette();
		Integer selectionIndex = parameter.getSelectionIndex();
		// TODO: add more if needed

		// the list of notifiers to be filled
		BasicEList<Notifier> notifiers = new BasicEList<Notifier>();

		// add all notifiers available currently
		if (palette != null)
		{
			notifiers.add(palette);
		}
		if (selectionIndex != null)
		{
			// TODO: how to handle integer indices?!
			// notifiers.add(selectionIndex);
		}
		// TODO: add more
		// if (ListVariable != null)
		// {
		// for (Element e : ListVariable)
		// {
		// notifiers.add(e);
		// }
		// }

		// create the actual command classes, add the correct notifiers and
		// return the newly created instance
		if (commandClass == SelectColorWithIndexCommand.class)
		{
			SelectColorWithIndexCommand command = new SelectColorWithIndexCommand(
					notifiers, parameter);
			return command;
		}
		else if (commandClass == ChangeRGBFromSelectedColorCommand.class)
		{
			ChangeRGBFromSelectedColorCommand command = new ChangeRGBFromSelectedColorCommand(
					notifiers, parameter);
			return command;
		}
		else if (commandClass == AddColorToPaletteCommand.class)
		{
			AddColorToPaletteCommand command = new AddColorToPaletteCommand(
					notifiers, parameter);
			return command;
		}
		else if (commandClass == RemoveColorFromPaletteCommand.class)
		{
			RemoveColorFromPaletteCommand command = new RemoveColorFromPaletteCommand(
					notifiers, parameter);
			return command;
		}
		// TODO: add more commands here if needed

		// no matching command identifier --> throw error
		throw new IllegalArgumentException("The class '"
				+ commandClass.getName() + "' is not a valid classifier");
	}
}
