package com.flow.snesde.editors.paletteedit.commands;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChangeCommand;

import palette.Color;
import palette.Palette;
import palette.PaletteFactory;
import palette.PalettePackage;

public class AddColorToPaletteCommand extends ChangeCommand
{
	// the palette object
	private final Palette palette;
	// the new color object to replace the old with
	private final Color newColor;

	/**
	 * Constructor for this command
	 * 
	 * @param notifiers
	 * @param parameter
	 */
	public AddColorToPaletteCommand(final Collection<Notifier> notifiers,
			final CommandParameter parameter)
	{
		super(notifiers);

		// TODO prepare any variables needed for execution
		palette = parameter.getPalette();

		PalettePackage palettePackage = PalettePackage.eINSTANCE;
		PaletteFactory paletteFactory = palettePackage.getPaletteFactory();
		EClass eClass = (EClass) palettePackage.getEClassifier("Palette");
		EObject rootObject = paletteFactory.create(eClass);
		newColor = paletteFactory.createColor();
		newColor.setR(0);
		newColor.setG(0);
		newColor.setB(0);

		// set label for undo/redo
		setLabel("Add color to palette.");
	}

	@Override
	protected void doExecute()
	{
		palette.getColors().add(palette.getColors().size(), newColor);
	}

	@Override
	protected boolean prepare()
	{
		// parameters have to be passed and exist
		if (this.palette != null && this.palette.getColors().size() < 256)
		{
			return true;
		}
		return false;
	}

}
