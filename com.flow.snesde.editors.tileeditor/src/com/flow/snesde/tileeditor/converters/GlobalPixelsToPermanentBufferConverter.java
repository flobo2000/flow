package com.flow.snesde.tileeditor.converters;

import java.util.Vector;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.common.util.EList;

import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;

import com.flow.snesde.uilib.generic.InternalPixel;

public class GlobalPixelsToPermanentBufferConverter implements IConverter
{

	@Override
	public Object getFromType()
	{
		return GlobalPixelBuffer.class;
	}

	@Override
	public Object getToType()
	{
		return Vector.class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof GlobalPixelBuffer)
		{
			// return object
			Vector<InternalPixel> ret = new Vector<InternalPixel>();

			// get all buffer data
			GlobalPixelBuffer from = (GlobalPixelBuffer) fromObject;
			EList<GlobalPixel> bufferedPixels = from.getBufferedPixels();
			for (GlobalPixel p : bufferedPixels)
			{
				// convert to internal pixels
				ret.add(new InternalPixel(p.getColumn(), p.getRow(), p
						.getColorIndex()));
			}
			// return converted object
			return ret;
		}
		return null;
	}
}
