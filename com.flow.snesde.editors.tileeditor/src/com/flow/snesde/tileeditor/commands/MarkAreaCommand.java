package com.flow.snesde.tileeditor.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.ChangeCommand;

import tileset.GlobalPixel;
import tileset.GlobalPixelBuffer;
import tileset.Pixel;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;

public class MarkAreaCommand extends ChangeCommand
{

	/**
	 * This caches an instance of the model package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TilesetPackage tilesetPackage = TilesetPackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TilesetFactory tilesetFactory = tilesetPackage
			.getTilesetFactory();
	// the tileset object
	private final Tileset tileset;
	private final Integer newBgColor;
	private final Integer startGlobalCol;
	private final Integer endGlobalCol;
	private final Integer startGlobalRow;
	private final Integer endGlobalRow;
	private final Boolean copyBG;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public MarkAreaCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// prepare any variables needed for execution
		tileset = parameter.getTileset();
		newBgColor = parameter.getSelectedColor();
		startGlobalCol = parameter.getStartGlobalCol();
		endGlobalCol = parameter.getEndGlobalCol();
		startGlobalRow = parameter.getStartGlobalRow();
		endGlobalRow = parameter.getEndGlobalRow();
		copyBG = parameter.isCopyBG();

		// set label for undo/redo
		setLabel(getCommandName());
	}

	/**
	 * should be overwritten by subclasses. returns the name to be displayed in
	 * the undo/redo action section
	 * 
	 * @return the commands name (eg. draw rectangle)
	 */
	public String getCommandName()
	{
		return "mark area";
	}

	@Override
	protected void doExecute()
	{
		// copy all content within the bounds into the pixelbuffer and set
		// this as the new permanent pixel buffer
		int lowerCol = startGlobalCol;
		int lowerRow = startGlobalRow;
		int higherCol = endGlobalCol;
		int higherRow = endGlobalRow;
		if (endGlobalCol < startGlobalCol)
		{
			lowerCol = endGlobalCol;
			higherCol = startGlobalCol;
		}
		if (endGlobalRow < startGlobalRow)
		{
			lowerRow = endGlobalRow;
			higherRow = startGlobalRow;
		}

		// the new model pixel buffer
		GlobalPixelBuffer newBuffer = tilesetFactory.createGlobalPixelBuffer();
		for (int col = lowerCol; col <= higherCol; col++)
		{
			for (int row = lowerRow; row <= higherRow; row++)
			{
				// calculate tile, row and column of the tile
				int tileColumn = (int) Math.ceil((double) col / (double) 8);
				int tileRow = (int) Math.ceil((double) row / (double) 8);
				int tileNumber = (tileRow - 1) * tileset.getColumns()
						+ tileColumn - 1;

				// calculate pixelcolumn in tile
				int pixelColumn = (col % 8) - 1;
				if (pixelColumn == -1) pixelColumn = 7;

				// calculate pixelrow in tile
				int pixelRow = (row % 8) - 1;
				if (pixelRow == -1) pixelRow = 7;

				// if this pixel should be moved into the permanent buffer...
				int colorIndex = tileset.getTiles().get(tileNumber)
						.getPixelColumns().get(pixelColumn).getPixels()
						.get(pixelRow).getColorIndex();
				if (copyBG || (!copyBG && colorIndex != newBgColor))
				{
					// create the new global pixel out of it
					GlobalPixel pix = tilesetFactory.createGlobalPixel();
					pix.setColumn(col);
					pix.setRow(row);
					pix.setColorIndex(colorIndex);
					// put it into the permanent buffer
					newBuffer.getBufferedPixels().add(pix);
					// and remove the original one (that is overwrite with the
					// newBGColor
					Pixel pixel = tilesetFactory.createPixel();
					pixel.setColorIndex(newBgColor);
					tileset.getTiles().get(tileNumber).getPixelColumns()
							.get(pixelColumn).getPixels().set(pixelRow, pixel);
				}
			}
		}
		// assign the new pixel buffer
		tileset.setPermanentBuffer(newBuffer);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.tileset != null
				&& newBgColor != null
				&& startGlobalCol != null
				&& endGlobalCol != null
				&& startGlobalRow != null
				&& endGlobalRow != null
				&& copyBG != null
				&& (startGlobalCol != endGlobalCol && startGlobalRow != endGlobalRow))
		{
			return true;
		}
		return false;
	}
}
