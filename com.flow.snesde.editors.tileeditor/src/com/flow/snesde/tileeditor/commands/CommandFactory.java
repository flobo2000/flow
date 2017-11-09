package com.flow.snesde.tileeditor.commands;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Tileset;

public class CommandFactory
{

	public static ChangeCommand create(final Class<? extends ChangeCommand> commandClass,
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
		Tileset tileset = parameter.getTileset();
		Integer newIndex = parameter.getNewIndex();
		// TODO: add more if needed

		// the list of notifiers to be filled
		BasicEList<Notifier> notifiers = new BasicEList<Notifier>();

		// add all notifiers available currently
		if (tileset != null)
		{
			notifiers.add(tileset);
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
		if (commandClass == ChangePreferredPaletteCommand.class)
		{
			ChangePreferredPaletteCommand command = new ChangePreferredPaletteCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == SetLeftSelectedColorCommand.class)
		{
			SetLeftSelectedColorCommand command = new SetLeftSelectedColorCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == SetRightSelectedColorCommand.class)
		{
			SetRightSelectedColorCommand command = new SetRightSelectedColorCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == ChangeSelectedToolCommand.class)
		{
			ChangeSelectedToolCommand command = new ChangeSelectedToolCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == DrawPencilLineCommand.class)
		{
			DrawPencilLineCommand command = new DrawPencilLineCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == DrawLineCommand.class)
		{
			DrawLineCommand command = new DrawLineCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == DrawEllipseCommand.class)
		{
			DrawEllipseCommand command = new DrawEllipseCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == DrawRectangleCommand.class)
		{
			DrawRectangleCommand command = new DrawRectangleCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == DrawFillCommand.class)
		{
			DrawFillCommand command = new DrawFillCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == SetZoomFactorCommand.class)
		{
			SetZoomFactorCommand command = new SetZoomFactorCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == MarkAreaCommand.class)
		{
			MarkAreaCommand command = new MarkAreaCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == UnmarkAreaCommand.class)
		{
			UnmarkAreaCommand command = new UnmarkAreaCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == MoveMarkedAreaCommand.class)
		{
			MoveMarkedAreaCommand command = new MoveMarkedAreaCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == PasteCopiedPixelsCommand.class)
		{
			PasteCopiedPixelsCommand command = new PasteCopiedPixelsCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == CutMarkedPixelsCommand.class)
		{
			CutMarkedPixelsCommand command = new CutMarkedPixelsCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == DeleteMarkedPixelsCommand.class)
		{
			DeleteMarkedPixelsCommand command = new DeleteMarkedPixelsCommand(notifiers, parameter);
			return command;
		}
		else if (commandClass == ResizeMarkedPixelsCommand.class)
		{
			ResizeMarkedPixelsCommand command = new ResizeMarkedPixelsCommand(notifiers, parameter);
			return command;
		}
		// TODO: add more commands here if needed

		// no matching command identifier --> throw error
		throw new IllegalArgumentException("The class '" + commandClass.getName() + "' is not a valid classifier");
	}
}
