package com.flow.snesde.core.wizards.importwizards.file.palette;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import palette.Palette;
import palette.PaletteFactory;
import palette.PalettePackage;

import com.flow.snesde.core.model.util.ModelTransformationUtil;
import com.flow.snesde.core.wizards.WizardConfiguration;
import com.flow.snesde.core.wizards.importwizards.file.AbstractImportFileWizard;
import com.flow.snesde.core.wizards.importwizards.file.AbstractImportFileWizardPage;

public class ImportPaletteWizard extends AbstractImportFileWizard
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
		config.setWizardTitle("Import Color Palette");
		config.setWizardHint("Import a Color Palette into your project");
		config.setResourceExtension("pal");
		config.setFileExtension("bin");

		// return the new configuration
		return config;
	}

	@Override
	public AbstractImportFileWizardPage getWizardPage(String preselection)
	{
		return new ImportPaletteWizardPage(preselection);
	}

	@Override
	protected EObject addContentToModel(byte[] byteContents2, EObject o)
	{
		Palette model = (Palette) ModelTransformationUtil
				.transformByteArrayToModel(byteContents2, o);
		model.setLeftSelectedIndex(0);
		model.setLeftSelectedColor(model.getColors().get(0));
		model.setRightSelectedIndex(0);
		model.setRightSelectedColor(model.getColors().get(0));
		return model;
	}

	@Override
	protected EObject createModel()
	{
		EClass eClass = (EClass) palettePackage.getEClassifier("Palette");
		EObject rootObject = paletteFactory.create(eClass);

		return rootObject;
	}

}
