package com.flow.snesde.core.wizards.importwizards.file.tileset;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;
import tileset.ToolSelection;

import com.flow.snesde.core.model.util.ModelTransformationUtil;
import com.flow.snesde.core.wizards.WizardConfiguration;
import com.flow.snesde.core.wizards.importwizards.file.AbstractImportFileWizard;
import com.flow.snesde.core.wizards.importwizards.file.AbstractImportFileWizardPage;

public class ImportTilesetWizard extends AbstractImportFileWizard
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

	@Override
	public WizardConfiguration getConfiguration()
	{
		// create new configuration
		WizardConfiguration config = new WizardConfiguration();

		// initialize configuration values
		config.setWizardIcon("graphics/wizards/tileset.png");
		config.setWizardTitle("Import Tileset");
		config.setWizardHint("Import a Tileset with the following presets");
		config.setResourceExtension("tile");
		config.setFileExtension("bin");

		// return the new configuration
		return config;
	}

	@Override
	public AbstractImportFileWizardPage getWizardPage(String preselection)
	{
		return new ImportTilesetWizardPage(preselection);
	}

	@Override
	protected EObject addContentToModel(byte[] byteContents2, EObject o)
	{
		ImportTilesetWizardPage p = ((ImportTilesetWizardPage) page);
		// encoding && byte content to create tiles/pixelcolumns/pixels
		int encoding = p.getEncoding();
		Tileset tileset = ModelTransformationUtil.transformBytesToTileset(
				byteContents2, (Tileset) o, encoding);

		// columns
		int columns = p.getColumns();
		tileset.setColumns(columns);

		// palette
		String paletteName = p.getPalette();
		tileset.setPaletteName(paletteName);
		tileset.setLeftIndex(0);
		tileset.setRightIndex(0);
		tileset.setZoomFactor(7);

		// other stuff
		tileset.setTool(ToolSelection.PENCIL);

		return tileset;
	}

	@Override
	protected EObject createModel()
	{
		EClass eClass = (EClass) tilesetPackage.getEClassifier("Tileset");
		EObject rootObject = tilesetFactory.create(eClass);

		return rootObject;
	}

}
