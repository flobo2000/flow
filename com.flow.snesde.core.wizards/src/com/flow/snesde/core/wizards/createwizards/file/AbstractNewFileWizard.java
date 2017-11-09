/**
 * 
 */
package com.flow.snesde.core.wizards.createwizards.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.wizards.newresource.ResourceMessages;
import org.eclipse.ui.part.ISetSelectionTarget;

import com.flow.snesde.core.wizards.WizardConfiguration;

/**
 * @author flo
 */
public abstract class AbstractNewFileWizard extends Wizard implements
		IWorkbenchWizard
{
	public AbstractNewFileWizardPage page;

	private String preselection;

	private IWorkbench workbench;

	private WizardConfiguration config;

	public AbstractNewFileWizard()
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
		addPage(page);
	}

	@Override
	public boolean performFinish()
	{
		String path = page.getCompleteTargetLocationString();
		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(path));
		file = addFileToProject(file);

		if (file != null)
		{
			selectAndReveal(file, workbench.getActiveWorkbenchWindow());

			// Open editor on new file.
			IWorkbenchWindow dw = workbench.getActiveWorkbenchWindow();
			try
			{
				if (dw != null)
				{
					IWorkbenchPage page = dw.getActivePage();
					if (page != null)
					{
						IDE.openEditor(page, file, true);
					}
				}
			}
			catch (PartInitException e)
			{
				DialogUtil.openError(dw.getShell(),
						ResourceMessages.FileResource_errorMessage,
						e.getMessage(), e);
			}
		}
		return true;
	}

	/**
	 * Add the file to the project
	 * 
	 * @param file
	 *            the file to be added
	 * @return the added file
	 */
	private IFile addFileToProject(IFile file)
	{
		try
		{
			return createFile(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * creaes an IFile
	 * 
	 * @param file
	 *            the IFile to be created
	 * @param filename
	 *            the filename to be used
	 * @throws CoreException
	 *             thrown if no file was created
	 * @return true if successful, false otherwise
	 */
	private IFile createFile(final IFile file) throws CoreException
	{
		try
		{
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
			{
				@Override
				protected void execute(IProgressMonitor progressMonitor)
				{
					try
					{
						// Create a resource set
						ResourceSet resourceSet = new ResourceSetImpl();
						// Get the URI of the metadata file.
						URI fileURI = URI.createPlatformResourceURI(file
								.getFullPath().toString(), true);
						// Create a resource for this file.
						Resource resource = resourceSet.createResource(fileURI);
						// create model object and add to resource for saving
						EObject o = createModel();
						if (o != null)
						{
							resource.getContents().add(o);
						}
						// Save the contents of the resource to the file
						// system.
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING, "UTF-8");
						resource.save(options);
					}
					catch (Exception exception)
					{
						// TODO handle properly
						exception.printStackTrace();
					}
					finally
					{
						progressMonitor.done();
					}
				}
			};
			getContainer().run(false, false, operation);
			return file;
		}
		catch (Exception exception)
		{
			// TODO: handle
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * to be overwritten by subclasses. should return a subclass of type
	 * AbstractNewFileWizardPage
	 * 
	 * @param preselection
	 *            the String to set in the project field of the wizard page
	 * @return the new page
	 */
	public abstract AbstractNewFileWizardPage getWizardPage(String preselection);

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
				this.preselection = "/"
						+ selectedResource.getProject().getName()
						+ "/"
						+ selectedResource.getProjectRelativePath();
			}
		}
	}

	/**
	 * To be overwritten by the subclasses. Creates the new model object
	 * 
	 * @return the EObject to create
	 */
	protected abstract EObject createModel();

	/**
	 * Attempts to select and reveal the specified resource in all parts within
	 * the supplied workbench window's active page.
	 * <p>
	 * Checks all parts in the active page to see if they implement
	 * <code>ISetSelectionTarget</code>, either directly or as an adapter. If
	 * so, tells the part to select and reveal the specified resource.
	 * </p>
	 * 
	 * @param resource
	 *            the resource to be selected and revealed
	 * @param window
	 *            the workbench window to select and reveal the resource
	 * 
	 * @see ISetSelectionTarget
	 */
	public static void selectAndReveal(IResource resource,
			IWorkbenchWindow window)
	{
		// validate the input
		if (window == null || resource == null)
		{
			return;
		}
		IWorkbenchPage page = window.getActivePage();
		if (page == null)
		{
			return;
		}

		// get all the view and editor parts
		List parts = new ArrayList();
		IWorkbenchPartReference refs[] = page.getViewReferences();
		for (int i = 0; i < refs.length; i++)
		{
			IWorkbenchPart part = refs[i].getPart(false);
			if (part != null)
			{
				parts.add(part);
			}
		}
		refs = page.getEditorReferences();
		for (int i = 0; i < refs.length; i++)
		{
			if (refs[i].getPart(false) != null)
			{
				parts.add(refs[i].getPart(false));
			}
		}

		final ISelection selection = new StructuredSelection(resource);
		Iterator itr = parts.iterator();
		while (itr.hasNext())
		{
			IWorkbenchPart part = (IWorkbenchPart) itr.next();

			// get the part's ISetSelectionTarget implementation
			ISetSelectionTarget target = null;
			if (part instanceof ISetSelectionTarget)
			{
				target = (ISetSelectionTarget) part;
			}
			else
			{
				target = (ISetSelectionTarget) part
						.getAdapter(ISetSelectionTarget.class);
			}

			if (target != null)
			{
				// select and reveal resource
				final ISetSelectionTarget finalTarget = target;
				window.getShell().getDisplay().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						finalTarget.selectReveal(selection);
					}
				});
			}
		}
	}
}
