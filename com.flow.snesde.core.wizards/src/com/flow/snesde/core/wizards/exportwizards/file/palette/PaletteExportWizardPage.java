package com.flow.snesde.core.wizards.exportwizards.file.palette;

import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Composite;

import com.flow.snesde.core.model.objects.AbstractModelObject;
import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.core.model.util.UnknownTypeException;
import com.flow.snesde.core.wizards.exportwizards.file.AbstractExportFileWizardPage;

public class PaletteExportWizardPage extends AbstractExportFileWizardPage
{

	public PaletteExportWizardPage(String preselection)
	{
		super(preselection);
		// nothing to be done here
	}

	@Override
	public void addComponents(Composite composite)
	{
		// nothing to be done here
	}

	@Override
	public String getAnyErrors()
	{
		// nothing to be done here
		return null;
	}

	@Override
	public HashSet<AbstractModelObject> getAllRelevantModelObjectsFromProject(
			IProject project)
	{
		try
		{
			SnesdeProjectRoot projectRoot = FlowWorkspace
					.getProjectRoot(project);
			HashSet<AbstractModelObject> paletteObjects = projectRoot
					.getModelObjectsOfClass(SnesdeProjectRoot.FILETYPE_PALETTE);
			return paletteObjects;
		}
		catch (CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnknownTypeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
