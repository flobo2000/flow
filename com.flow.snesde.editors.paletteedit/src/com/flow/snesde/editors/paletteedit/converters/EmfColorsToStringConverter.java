package com.flow.snesde.editors.paletteedit.converters;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.common.util.EList;

import palette.Color;

public class EmfColorsToStringConverter implements IConverter
{

	@Override
	public Object getFromType()
	{
		return Color[].class;
	}

	@Override
	public Object getToType()
	{
		return String.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof EList)
		{
			EList list = ((EList) fromObject);
			return "" + list.size();
		}
		return "";
	}

}
