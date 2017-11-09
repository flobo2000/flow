package com.flow.snesde.tileeditor.commands;

import java.util.Vector;

import tileset.Tileset;

import com.flow.snesde.uilib.generic.InternalPixel;

public class CommandParameter
{
	Tileset tileset;
	String paletteName;
	Integer newIndex;
	Integer selectedTool;
	Integer startGlobalCol, endGlobalCol, startGlobalRow, endGlobalRow;
	Integer startTileCol, endTileCol, startTileRow, endTileRow;
	Integer startTileNumber, endTileNumber;
	Integer selectedColor;
	private Vector<InternalPixel> pixelBuffer;
	private Boolean copyBG;
	private Object d;
	private int deltaRow;
	private int deltaCol;

	// constructors

	public CommandParameter(Tileset tileset)
	{
		super();
		this.tileset = tileset;
	}

	public CommandParameter(Tileset tileset, String paletteName)
	{
		super();
		this.tileset = tileset;
		this.paletteName = paletteName;
	}

	public CommandParameter(Tileset tileset, Integer newIndex)
	{
		super();
		this.tileset = tileset;
		this.newIndex = newIndex;
	}

	public CommandParameter(Tileset tileset, Vector<InternalPixel> pixelBuffer)
	{
		super();
		this.pixelBuffer = pixelBuffer;
		this.tileset = tileset;
	}

	public CommandParameter(Tileset tileset, int newBgColor, int startCol,
			int endCol, int startRow, int endRow, boolean copyBG)
	{
		super();
		this.tileset = tileset;
		this.startGlobalCol = startCol;
		this.startGlobalRow = startRow;
		this.endGlobalCol = endCol;
		this.endGlobalRow = endRow;
		this.selectedColor = newBgColor;
		this.copyBG = copyBG;
	}

	public CommandParameter(Tileset tileset, int deltaCol, int deltaRow)
	{
		super();
		this.tileset = tileset;
		this.deltaCol = deltaCol;
		this.deltaRow = deltaRow;
	}

	// access methods

	public Tileset getTileset()
	{
		return tileset;
	}

	public String getPaletteName()
	{
		return paletteName;
	}

	public Integer getNewIndex()
	{
		return newIndex;
	}

	public Integer getSelectedTool()
	{
		return newIndex;
	}

	public Integer getStartGlobalCol()
	{
		return startGlobalCol;
	}

	public Integer getEndGlobalCol()
	{
		return endGlobalCol;
	}

	public Integer getStartGlobalRow()
	{
		return startGlobalRow;
	}

	public Integer getEndGlobalRow()
	{
		return endGlobalRow;
	}

	public Integer getSelectedColor()
	{
		return selectedColor;
	}

	public Integer getStartTileCol()
	{
		return startTileCol;
	}

	public Integer getEndTileCol()
	{
		return endTileCol;
	}

	public Integer getStartTileRow()
	{
		return startTileRow;
	}

	public Integer getEndTileRow()
	{
		return endTileRow;
	}

	public Integer getStartTileNumber()
	{
		return startTileNumber;
	}

	public Integer getEndTileNumber()
	{
		return endTileNumber;
	}

	public Vector<InternalPixel> getPixelBuffer()
	{
		return this.pixelBuffer;
	}

	public Boolean isCopyBG()
	{
		return this.copyBG;
	}

	public int getDeltaRow()
	{
		return this.deltaRow;
	}

	public int getDeltaCol()
	{
		return this.deltaCol;
	}

}
