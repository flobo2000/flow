package com.flow.snesde.core.wizards.exportwizards.file.palette;

import com.flow.snesde.core.wizards.WizardConfiguration;
import com.flow.snesde.core.wizards.exportwizards.file.AbstractExportFileWizard;
import com.flow.snesde.core.wizards.exportwizards.file.AbstractExportFileWizardPage;

public class PaletteExportWizard extends AbstractExportFileWizard
{

	@Override
	public WizardConfiguration getConfiguration()
	{
		// create new configuration
		WizardConfiguration config = new WizardConfiguration();

		// initialize configuration values
		config.setWizardIcon("graphics/wizards/palette.png");
		config.setWizardTitle("Export color palette");
		config.setWizardHint("Export a new color palette from your project");
		config.setResourceExtension("pal");
		config.setFileExtension("bin");

		// return the new configuration
		return config;
	}

	@Override
	public AbstractExportFileWizardPage getWizardPage(String preselection)
	{
		return new PaletteExportWizardPage(preselection);
	}
}