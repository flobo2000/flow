/**
 * 
 */
package com.flow.snesde.core.wizards.createwizards.file.palette;

import java.util.Random;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import palette.Color;
import palette.Palette;
import palette.PaletteFactory;
import palette.PalettePackage;

import com.flow.snesde.core.wizards.WizardConfiguration;
import com.flow.snesde.core.wizards.createwizards.file.AbstractNewFileWizard;
import com.flow.snesde.core.wizards.createwizards.file.AbstractNewFileWizardPage;

/**
 * @author flo
 */
public class NewPaletteWizard extends AbstractNewFileWizard
{
	/**
	 * This caches an instance of the model package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PalettePackage palettePackage = PalettePackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PaletteFactory paletteFactory = palettePackage
			.getPaletteFactory();

	@Override
	public WizardConfiguration getConfiguration()
	{
		// create new configuration
		WizardConfiguration config = new WizardConfiguration();

		// initialize configuration values
		config.setWizardIcon("graphics/wizards/palette.png");
		config.setWizardTitle("New color palette");
		config.setWizardHint("Create a new color palette with the following presets");
		config.setResourceExtension("pal");

		// return the new configuration
		return config;
	}

	@Override
	public AbstractNewFileWizardPage getWizardPage(String preselection)
	{
		return new NewPaletteWizardPage(preselection);
	}

	@Override
	protected EObject createModel()
	{
		EClass eClass = (EClass) palettePackage.getEClassifier("Palette");
		EObject rootObject = paletteFactory.create(eClass);

		Palette pal = (Palette) rootObject;
		// add stuff to this palette object
		int colors = ((NewPaletteWizardPage) page).getColorDepth();
		int creationMode = ((NewPaletteWizardPage) page).getColorMode();
		int r = 0;
		int b = 0;
		int g = 0;
		if (creationMode == NewPaletteWizardPage.MODE_SINGLE_COLOR)
		{
			r = ((NewPaletteWizardPage) page).getRedValue();
			g = ((NewPaletteWizardPage) page).getGreenValue();
			b = ((NewPaletteWizardPage) page).getBlueValue();
		}
		Random randGen = new Random(System.currentTimeMillis());
		for (int i = 0; i < colors; i++)
		{
			if (creationMode == NewPaletteWizardPage.MODE_RANDOM_COLORS)
			{
				r = Math.abs(randGen.nextInt()) % 31;
				g = Math.abs(randGen.nextInt()) % 31;
				b = Math.abs(randGen.nextInt()) % 31;
			}
			Color color = paletteFactory.createColor();
			color.setR(r);
			color.setG(g);
			color.setB(b);
			pal.getColors().add(color);

			if (i == 0)
			{
				pal.setLeftSelectedColor(color);
				pal.setRightSelectedColor(color);
				pal.setLeftSelectedIndex(0);
				pal.setRightSelectedIndex(0);
			}
		}

		return rootObject;
	}
}
