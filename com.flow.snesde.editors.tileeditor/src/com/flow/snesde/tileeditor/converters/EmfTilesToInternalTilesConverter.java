package com.flow.snesde.tileeditor.converters;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.common.util.EList;

import tileset.Pixel;
import tileset.PixelColumn;
import tileset.Tile;
import tileset.impl.TileImpl;

import com.flow.snesde.uilib.generic.InternalTile;

public class EmfTilesToInternalTilesConverter implements IConverter
{

	@Override
	public Object getFromType()
	{
		return TileImpl[].class;
	}

	@Override
	public Object getToType()
	{
		return InternalTile[].class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		InternalTile[] output = null;
		if (fromObject instanceof EList)
		{
			EList list = ((EList) fromObject);
			output = new InternalTile[list.size()];
			for (int i = 0; i < list.size(); i++)
			{
				InternalTile newTile = new InternalTile();
				Object o = list.get(i);
				if (o instanceof Tile)
				{
					Tile tile = (Tile) o;
					for (int column = 0; column < 8; column++)
					{
						PixelColumn pixelColumn = tile.getPixelColumns().get(
								column);
						for (int row = 0; row < 8; row++)
						{
							Pixel pixel = pixelColumn.getPixels().get(row);
							newTile.setColorAt(column, row,
									pixel.getColorIndex());
						}
					}
				}
				output[i] = newTile;
			}
		}
		return output;
	}

}
