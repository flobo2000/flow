package com.flow.snesde.core.wizards;

public class WizardConfiguration
{
	private String WizardIcon = "graphics/wizards/none.png";
	private String WizardTitle = "New file";
	private String WizardHint = "Create a new file with the following presets";
	// the resourceExtension is the extension of the resource to be
	// created/exported from
	// please not the difference to the fileExtension which is the extension of
	// the non workspace standard java.io.File to export/import to/from
	private String resourceExtension = "bin";
	// the extension for a non-workspace standard java.io.File from which to
	// import/to which to export to. Do not confuse this with resourceExtension
	// which refers to the workspace resource extension to handle
	private String fileExtension = "bin";

	public String getWizardIcon()
	{
		return WizardIcon;
	}

	public void setWizardIcon(String wizardIcon)
	{
		WizardIcon = wizardIcon;
	}

	public String getWizardTitle()
	{
		return WizardTitle;
	}

	public void setWizardTitle(String wizardTitle)
	{
		WizardTitle = wizardTitle;
	}

	public String getWizardHint()
	{
		return WizardHint;
	}

	public void setWizardHint(String wizardHint)
	{
		WizardHint = wizardHint;
	}

	public String getResourceExtension()
	{
		return resourceExtension;
	}

	public void setResourceExtension(String resourceExtension)
	{
		this.resourceExtension = resourceExtension;
	}

	public String getFileExtension()
	{
		return fileExtension;
	}

	public void setFileExtension(String fileExtension)
	{
		this.fileExtension = fileExtension;
	}
}
