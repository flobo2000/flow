package com.flow.snesde.tileeditor.converters;

import org.eclipse.core.databinding.conversion.IConverter;

public class ComposedPaletteNameToSimplePaletteNameConverter implements
		IConverter
{
	@Override
	public Object getFromType()
	{
		return String.class;
	}

	@Override
	public Object getToType()
	{
		return String.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof String)
		{
			String from = (String) fromObject;
			if (from.contains(" "))
			{
				return from.split(" ")[0];
			}
			return from;
		}
		return null;
	}
}
