package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;

public class DrawFillCommand extends AbstractDrawPixelBufferCommand
{

	public DrawFillCommand(Collection<Notifier> notifiers,
			CommandParameter parameter)
	{
		super(notifiers, parameter);
	}

	@Override
	public String getCommandName()
	{
		return "fill tiles";
	}

}
