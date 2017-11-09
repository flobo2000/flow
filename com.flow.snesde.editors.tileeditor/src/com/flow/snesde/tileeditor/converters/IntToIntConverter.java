package com.flow.snesde.tileeditor.converters;

import org.eclipse.core.databinding.conversion.IConverter;

public class IntToIntConverter implements IConverter
{
	@Override
	public Object getFromType()
	{
		return Integer.class;
	}

	@Override
	public Object getToType()
	{
		return Integer.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof Integer)
		{
			Integer from = (Integer) fromObject;
			return from;
		}
		return null;
	}
}
