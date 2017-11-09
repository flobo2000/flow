package com.flow.snesde.core.wizards.exportwizards.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import palette.Palette;

import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.core.model.util.ModelTransformationUtil;
import com.flow.snesde.core.wizards.WizardConfiguration;

public abstract class AbstractExportFileWizard extends Wizard implements
		IExportWizard
{
	/**
	 * This holds the command stack etc for EMF undoable commands
	 */
	protected AdapterFactoryEditingDomain editingDomain;

	public AbstractExportFileWizardPage page;

	private String preselection;

	private IWorkbench workbench;

	private WizardConfiguration config;

	private String resourceExtension;

	public AbstractExportFileWizard()
	{
		configure();
	}

	/**
	 * configures the wizard
	 */
	private void configure()
	{
		config = getConfiguration();
		doConfigure(config);
	}

	/**
	 * takes all config data and configures the wizard
	 * 
	 * @param config
	 *            the config bean
	 */
	private void doConfigure(WizardConfiguration config)
	{
		// TODO do stuff with the configuration bean
	}

	/**
	 * Method to obtain the wizard configuration. should be overwritten by all
	 * subclasses
	 * 
	 * @return the wizard configuration
	 */
	public abstract WizardConfiguration getConfiguration();

	@Override
	public void addPages()
	{
		page = getWizardPage(preselection);
		page.configure(config);
		resourceExtension = config.getResourceExtension();
		addPage(page);
	}

	/**
	 * to be overwritten by subclasses. should return a subclass of type
	 * AbstractNewFileWizardPage
	 * 
	 * @param preselection
	 *            the String to set in the project field of the wizard page
	 * @return the new page
	 */
	public abstract AbstractExportFileWizardPage getWizardPage(
			String preselection);

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		this.workbench = workbench;
		Iterator it = selection.iterator();
		if (it.hasNext())
		{
			Object object = it.next();
			IResource selectedResource = null;
			if (object instanceof IResource)
			{
				selectedResource = (IResource) object;
			}
			else if (object instanceof IAdaptable)
			{
				selectedResource = (IResource) ((IAdaptable) object)
						.getAdapter(IResource.class);
			}
			if (selectedResource != null)
			{
				if (selectedResource.getProject() != null)
				{
					this.preselection = selectedResource.getProject().getName();
				}
			}
		}
	}

	@Override
	public boolean performFinish()
	{
		// get project
		String projectName = page.getProject();
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);
		// get resource to export
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource res = null;
		if (project.exists())
		{
			String resourceToExport = page.getResourceToExport();

			IFile file = null;
			try
			{
				file = FlowWorkspace.getProjectRoot(project).getFileRef(
						resourceToExport);
			}
			catch (CoreException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (file != null && file.exists())
			{
				URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
						.toString(), true);

				// Don't really know what all this does but it's needed
				// anyways
				List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
				ComposedAdapterFactory adapterFactory1 = new ComposedAdapterFactory(
						ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
				adapterFactory1
						.addAdapterFactory(new ResourceItemProviderAdapterFactory());
				adapterFactory1
						.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
				factories.add(adapterFactory1);
				ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
						factories);

				// Create the command stack that will notify this editor as
				// commands are
				// executed.
				BasicCommandStack commandStack = new BasicCommandStack();

				// Create the editing domain with a special command stack.
				//
				editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
						commandStack, new HashMap<Resource, Boolean>());

				res = editingDomain.getResourceSet().getResource(fileURI, true);
				try
				{
					res.load(null);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// get export location
		String exportLocation = page.getExportLocation();
		File f = new File(exportLocation);
		// load model
		EObject o = res.getContents().get(0);
		// trigger subclass specific export functionality
		return exportModelToFile(o, f);
	}

	/**
	 * To be implemented by subclasses. Should take the EObject and transform it
	 * in some way or another into a serialized form which is then written to
	 * the file reference passed.
	 * 
	 * @param model
	 *            the model to export
	 * @param file
	 *            the file to export into
	 * @return true if this export procedure was successful, false otherwise
	 */
	protected boolean exportModelToFile(EObject model, File file)
	{
		if (model instanceof Palette)
		{
			byte[] output = ModelTransformationUtil
					.transformModelToByteArray(model);

			try
			{
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(output);
				fos.flush();
				fos.close();
				return true;
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
