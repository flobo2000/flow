package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;

public class DrawRectangleCommand extends AbstractDrawPixelBufferCommand
{

	public DrawRectangleCommand(Collection<Notifier> notifiers,
			CommandParameter parameter)
	{
		super(notifiers, parameter);
	}

	@Override
	public String getCommandName()
	{
		return "draw rectangle";
	}

}