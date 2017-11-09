package com.flow.snesde.editors.paletteedit.converters;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.common.util.EList;

import palette.Color;

import com.flow.snesde.uilib.generic.NonNativeColor;

public class EmfColorsToNonNativeColorsConverter implements IConverter
{

	@Override
	public Object getFromType()
	{
		return Color[].class;
	}

	@Override
	public Object getToType()
	{
		return NonNativeColor[].class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		NonNativeColor[] output = null;
		if (fromObject instanceof EList)
		{
			EList list = ((EList) fromObject);
			output = new NonNativeColor[list.size()];
			for (int i = 0; i < list.size(); i++)
			{
				Object o = list.get(i);
				if (o instanceof Color)
				{
					Color c = (Color) o;
					output[i] = new NonNativeColor(c.getR(), c.getG(), c.getB());
				}
			}
		}
		return output;
	}

}
