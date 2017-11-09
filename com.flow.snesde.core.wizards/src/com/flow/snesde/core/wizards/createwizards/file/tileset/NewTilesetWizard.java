/**
 * 
 */
package com.flow.snesde.core.wizards.createwizards.file.tileset;

import java.util.Random;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import tileset.Encoding;
import tileset.Pixel;
import tileset.PixelColumn;
import tileset.Tile;
import tileset.Tileset;
import tileset.TilesetFactory;
import tileset.TilesetPackage;
import tileset.ToolSelection;

import com.flow.snesde.core.wizards.WizardConfiguration;
import com.flow.snesde.core.wizards.createwizards.file.AbstractNewFileWizard;
import com.flow.snesde.core.wizards.createwizards.file.AbstractNewFileWizardPage;

/**
 * @author flo
 */
public class NewTilesetWizard extends AbstractNewFileWizard
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

	private NewTilesetWizardPage newTilesetWizardPage;

	@Override
	public WizardConfiguration getConfiguration()
	{
		// create new configuration
		WizardConfiguration config = new WizardConfiguration();

		// initialize configuration values
		config.setWizardIcon("graphics/wizards/tileset.png");
		config.setWizardTitle("New Tileset");
		config.setWizardHint("Create a new Tileset with the following presets");
		config.setResourceExtension("tile");

		// return the new configuration
		return config;
	}

	@Override
	public AbstractNewFileWizardPage getWizardPage(String preselection)
	{
		newTilesetWizardPage = new NewTilesetWizardPage(preselection);
		return newTilesetWizardPage;
	}

	@Override
	protected EObject createModel()
	{
		EClass eClass = (EClass) tilesetPackage.getEClassifier("Tileset");
		EObject rootObject = tilesetFactory.create(eClass);

		Tileset tileset = (Tileset) rootObject;

		Tile tile = null;
		// TODO: decide if needed
		// String filename = newTilesetWizardPage.getFilename();
		int tileNumber = newTilesetWizardPage.getTotalNumberOfTiles();

		// columns
		int numberOfColumns = newTilesetWizardPage.getNumberOfColumns();
		tileset.setColumns(numberOfColumns);

		// encoding
		int encoding = newTilesetWizardPage.getEncoding();
		switch (encoding)
		{
			case 4:
				tileset.setEncoding(Encoding.ENC2BPP4COLORS);
				break;
			case 8:
				tileset.setEncoding(Encoding.ENC3BPP8COLORS);
				break;
			case 16:
				tileset.setEncoding(Encoding.ENC4BPP16COLORS);
				break;
			case 256:
				tileset.setEncoding(Encoding.ENC8BPP256COLORS);
				break;
			default:
				tileset.setEncoding(Encoding.ENC1BPP2COLORS);
				break;
		}

		// palette
		String paletteName = newTilesetWizardPage.getPreferredPalette();
		tileset.setPaletteName(paletteName);
		tileset.setLeftIndex(0);
		tileset.setRightIndex(0);
		tileset.setTool(ToolSelection.PENCIL);
		tileset.setZoomFactor(7);

		// fill pixels of tiles
		int randomPixelsOrNot = newTilesetWizardPage.getInitialContent();
		Random randGen = new Random(System.currentTimeMillis());
		for (int i = 0; i < tileNumber; i++)
		{
			// create the tile object
			tile = tilesetFactory.createTile();

			// TODO: this should be inside the factory and not here
			for (int column = 0; column < 8; column++)
			{
				PixelColumn col = tilesetFactory.createPixelColumn();
				for (int row = 0; row < 8; row++)
				{
					Pixel pix = tilesetFactory.createPixel();
					if (randomPixelsOrNot == NewTilesetWizardPage.CONTENT_RANDOM)
					{
						pix.setColorIndex(Math.abs(randGen.nextInt())
								% encoding);
					}
					else
					{
						pix.setColorIndex(0);
					}
					col.getPixels().add(row, pix);
				}
				tile.getPixelColumns().add(column, col);
			}

			tileset.getTiles().add(tile);
		}

		return rootObject;
	}
}
