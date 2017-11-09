package com.flow.snesde.editors.paletteedit.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChangeCommand;

import palette.Color;
import palette.Palette;
import palette.PaletteFactory;
import palette.PalettePackage;

public class ChangeRGBFromSelectedColorCommand extends ChangeCommand
{
	// the palette object
	private final Palette palette;
	// the new red value to set
	private final Integer newRedValue;
	// the new green value to set
	private final Integer newGreenValue;
	// the new blue value to set
	private final Integer newBlueValue;
	// the new color object to replace the old with
	private final Color newColor;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public ChangeRGBFromSelectedColorCommand(
			final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		palette = parameter.getPalette();
		newRedValue = parameter.getNewRedValue();
		newGreenValue = parameter.getNewGreenValue();
		newBlueValue = parameter.getNewBlueValue();

		PalettePackage palettePackage = PalettePackage.eINSTANCE;
		PaletteFactory paletteFactory = palettePackage.getPaletteFactory();
		EClass eClass = (EClass) palettePackage.getEClassifier("Palette");
		EObject rootObject = paletteFactory.create(eClass);
		newColor = paletteFactory.createColor();
		newColor.setR(newRedValue);
		newColor.setG(newGreenValue);
		newColor.setB(newBlueValue);
		palette.eResource().getContents().add(newColor);

		// set label for undo/redo
		setLabel("Change selected color.");
	}

	@Override
	protected void doExecute()
	{
		palette.getColors().add(palette.getLeftSelectedIndex(), newColor);
		palette.getColors().remove(palette.getLeftSelectedIndex() + 1);
		EList<Color> colors = palette.getColors();
		palette.setLeftSelectedColor(newColor);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.palette != null && newRedValue != null
				&& newGreenValue != null && newBlueValue != null)
		{
			// selectionindex must be in bounds of color palette size
			if (newRedValue >= 0 && newRedValue < 32)
			{
				if (newGreenValue >= 0 && newGreenValue < 32)
				{
					if (newBlueValue >= 0 && newBlueValue < 32)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

}
