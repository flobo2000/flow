package com.flow.snesde.editors.paletteedit.converters;

import org.eclipse.core.databinding.conversion.IConverter;

public class IntToStringConverter implements IConverter
{

	@Override
	public Object getFromType()
	{
		return Integer.class;
	}

	@Override
	public Object getToType()
	{
		return String.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof Integer)
		{
			Integer i = ((Integer) fromObject);
			return "" + i;
		}
		return "";
	}

}
