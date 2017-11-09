package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.Tileset;
import tileset.ToolSelection;

public class ChangeSelectedToolCommand extends ChangeCommand
{

	// the tileset object
	private final Tileset tileset;
	// the new name of the preferred palette
	private final Integer selectedTool;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public ChangeSelectedToolCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		tileset = parameter.getTileset();
		selectedTool = parameter.getSelectedTool();

		// set label for undo/redo
		setLabel("Select tool " + selectedTool);
	}

	@Override
	protected void doExecute()
	{
		ToolSelection toolSelection = ToolSelection.get(selectedTool);
		tileset.setTool(toolSelection);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null && selectedTool != null)
		{
			if (selectedTool == ToolSelection.ELLIPSE_VALUE
					|| selectedTool == ToolSelection.ENUMERATION_VALUE
					|| selectedTool == ToolSelection.FILL_VALUE
					|| selectedTool == ToolSelection.LINE_VALUE
					|| selectedTool == ToolSelection.MAGNIFIER_VALUE
					|| selectedTool == ToolSelection.MARK_VALUE
					|| selectedTool == ToolSelection.PALETTE_VALUE
					|| selectedTool == ToolSelection.PENCIL_VALUE
					|| selectedTool == ToolSelection.PIPETTE_VALUE
					|| selectedTool == ToolSelection.RECTANGLE_VALUE
					|| selectedTool == ToolSelection.TEXT_VALUE
					|| selectedTool == ToolSelection.WIZARD_VALUE
					|| selectedTool == ToolSelection.SPRAY_VALUE)
			{
				return true;
			}
		}
		return false;
	}

}
