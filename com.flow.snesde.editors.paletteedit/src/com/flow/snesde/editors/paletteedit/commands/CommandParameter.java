package com.flow.snesde.editors.paletteedit.commands;

import palette.Color;
import palette.Palette;

public class CommandParameter
{
	private final Palette palette;
	private Integer selectionIndex;
	private Integer newRedValue;
	private Integer newGreenValue;
	private Integer newBlueValue;

	// constructors

	/**
	 * @param palette
	 *            the palette model to work with
	 */
	public CommandParameter(Palette palette, int selectionIndex)
	{
		super();
		this.palette = palette;
		this.selectionIndex = selectionIndex;
	}

	public CommandParameter(Palette palette)
	{
		super();
		this.palette = palette;
	}

	public CommandParameter(Palette palette, Color color, int newRedValue,
			int newGreenValue, int newBlueValue)
	{
		super();
		this.palette = palette;
		this.newRedValue = newRedValue;
		this.newGreenValue = newGreenValue;
		this.newBlueValue = newBlueValue;
	}

	// access methods

	/**
	 * returns the palette if set
	 * 
	 * @return the palette
	 */
	public Palette getPalette()
	{
		return this.palette;
	}

	/**
	 * returns the selectionindex set
	 * 
	 * @return the selectionindex
	 */
	public Integer getSelectionIndex()
	{
		return this.selectionIndex;
	}

	/**
	 * returns the new red value
	 * 
	 * @return the new red value
	 */
	public Integer getNewRedValue()
	{
		return newRedValue;
	}

	/**
	 * returns the new green value
	 * 
	 * @return the new green value
	 */
	public Integer getNewGreenValue()
	{
		return newGreenValue;
	}

	/**
	 * returns the new blue value
	 * 
	 * @return the new blue value
	 */
	public Integer getNewBlueValue()
	{
		return newBlueValue;
	}
}
