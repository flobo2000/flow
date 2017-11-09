package com.flow.snesde.editors.paletteedit.converters;

import org.eclipse.core.databinding.conversion.IConverter;

import palette.Color;

import com.flow.snesde.uilib.generic.NonNativeColor;

public class EmfColorToNonNativeColorConverter implements IConverter
{

	@Override
	public Object getFromType()
	{
		return Color.class;
	}

	@Override
	public Object getToType()
	{
		return NonNativeColor.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		Color c = (Color) fromObject;
		return new NonNativeColor(c.getR(), c.getG(), c.getB());
	}

}
