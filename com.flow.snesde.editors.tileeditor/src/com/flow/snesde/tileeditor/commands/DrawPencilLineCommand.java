package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;

public class DrawPencilLineCommand extends AbstractDrawPixelBufferCommand
{

	public DrawPencilLineCommand(Collection<Notifier> notifiers,
			CommandParameter parameter)
	{
		super(notifiers, parameter);
	}

	@Override
	public String getCommandName()
	{
		return "draw pencil stroke";
	}
}
