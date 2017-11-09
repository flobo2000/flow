package com.flow.snesde.tileeditor.converters;

import org.eclipse.core.databinding.conversion.IConverter;

import tileset.ToolSelection;

import com.flow.snesde.tileeditor.ui.TwoColumnToolbar;

public class DrawingModeToToolSelectionBarConverter implements IConverter
{
	@Override
	public Object getFromType()
	{
		return ToolSelection.class;
	}

	@Override
	public Object getToType()
	{
		return Integer.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof ToolSelection)
		{
			ToolSelection from = (ToolSelection) fromObject;
			if (from.equals(ToolSelection.ELLIPSE))
			{
				return TwoColumnToolbar.TOOL_ELLIPSE;
			}
			else if (from.equals(ToolSelection.ENUMERATION))
			{
				return TwoColumnToolbar.TOOL_ENUMERATION;
			}
			else if (from.equals(ToolSelection.FILL))
			{
				return TwoColumnToolbar.TOOL_FILL;
			}
			else if (from.equals(ToolSelection.LINE))
			{
				return TwoColumnToolbar.TOOL_LINE;
			}
			else if (from.equals(ToolSelection.MAGNIFIER))
			{
				return TwoColumnToolbar.TOOL_MAGNIFIER;
			}
			else if (from.equals(ToolSelection.MARK))
			{
				return TwoColumnToolbar.TOOL_MARK;
			}
			else if (from.equals(ToolSelection.PALETTE))
			{
				return TwoColumnToolbar.TOOL_PALETTE;
			}
			else if (from.equals(ToolSelection.PENCIL))
			{
				return TwoColumnToolbar.TOOL_PENCIL;
			}
			else if (from.equals(ToolSelection.PIPETTE))
			{
				return TwoColumnToolbar.TOOL_PIPETTE;
			}
			else if (from.equals(ToolSelection.RECTANGLE))
			{
				return TwoColumnToolbar.TOOL_RECTANGLE;
			}
			else if (from.equals(ToolSelection.TEXT))
			{
				return TwoColumnToolbar.TOOL_TEXT;
			}
			else if (from.equals(ToolSelection.WIZARD))
			{
				return TwoColumnToolbar.TOOL_WIZARD;
			}
			else if (from.equals(ToolSelection.SPRAY))
			{
				return TwoColumnToolbar.TOOL_SPRAY;
			}
		}
		return null;
	}
}
