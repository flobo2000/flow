package com.flow.snesde.editors.projectmeta;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import projectmeta.impl.MetadataImpl;

import com.flow.snesde.editors.projectmeta.pages.ProjectMetaFormPage;

/**
 * @author flo The baseclass for SNESDEs project metadata editor. It is a
 *         Formeditor and could be extended to have multiple pages in the
 *         future.
 */
@SuppressWarnings("restriction")
public class ProjectMetaEditor extends FormEditor implements
		IEditingDomainProvider, IResourceChangeListener
{
	/**
	 * This holds the command stack etc for EMF undoable commands
	 */
	protected AdapterFactoryEditingDomain editingDomain;

	/**
	 * This is the model for the editor
	 */
	private MetadataImpl model;

	/**
	 * The IResource read by the file
	 */
	private IFile inputFile = null;

	/**
	 * Standard constructor
	 */
	public ProjectMetaEditor()
	{
		super();
		initializeEditingDomain();
	}

	/**
	 * This sets up the editing domain for the model editor.
	 */
	protected void initializeEditingDomain()
	{
		// Don't really know what all this does but it's needed anyways
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

		// Create the command stack that will notify this editor as commands are
		// executed.
		BasicCommandStack commandStack = new BasicCommandStack();

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		commandStack.addCommandStackListener(new CommandStackListener()
		{
			@Override
			public void commandStackChanged(final EventObject event)
			{
				getContainer().getDisplay().asyncExec(new Runnable()
				{

					@Override
					public void run()
					{
						firePropertyChange(IEditorPart.PROP_DIRTY);

						// TODO: in case we need to manually update a field, we
						// should trigger it here
						// if (getSelectedPage() instanceof
						// IRefreshableEditorPart)
						// {
						// ((IRefreshableEditorPart)
						// getSelectedPage()).doRefresh();
						// }
					}
				});
			}
		});

		// Create the editing domain with a special command stack.
		//
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				commandStack, new HashMap<Resource, Boolean>());
		// see
		// http://milesparker.blogspot.de/2011/01/supporting-multiple-resource-types-with.html
		// don't forget to add the extension to plugin.properties
		// editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("meta",
		// new ProjectMetaResourceFactoryImpl());
	}

	/**
	 * This is here for the listener to be able to call it.
	 */
	@Override
	protected void firePropertyChange(int action)
	{
		super.firePropertyChange(action);
	}

	@Override
	protected void addPages()
	{
		if (this.model != null)
		{
			try
			{
				FormPage page1 = new ProjectMetaFormPage(this,
						"com.flow.projectmeta.page1", inputFile.getName(),
						this.model, this.editingDomain);
				int index = addPage(page1);
				setPageText(index, "Project Metadata");
			}
			catch (PartInitException e)
			{
				e.printStackTrace();
			}

			this.setPartName("project.meta");
		}
		else
		{
			// for some reason the model is not present -> show the error
			MessageDialog.openError(getSite().getShell(),
					"Opening editor failed.", getEditorInput().getName()
							+ " could not be opened.");
			Label control = new Label(getContainer(), SWT.NONE);
			addPage(control);
			close(false);
		}
	}

	/**
	 * This will be checked to decide weather or not to enable save
	 * functionality
	 */
	@Override
	public boolean isDirty()
	{
		return ((BasicCommandStack) this.editingDomain.getCommandStack())
				.isSaveNeeded();
	}

	@Override
	public void close(final boolean save)
	{
		final Display display = getSite().getShell().getDisplay();
		display.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				getSite().getPage().closeEditor(ProjectMetaEditor.this,
						save);

				if (!ProjectMetaEditor.this.getContainer().isDisposed())
				{
					ProjectMetaEditor.this.dispose();
				}
			}
		});
	}

	@Override
	public void dispose()
	{
		try
		{
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
			// super.dispose();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(final IProgressMonitor monitor)
	{
		// TODO: write save code
		// Do the work within an operation because this is a long running
		// activity
		// that modifies the workbench.
		try
		{
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
			{
				@Override
				public void execute(final IProgressMonitor monitor)
						throws ResourceException
				{
					try
					{
						// Create a resource set
						ResourceSet resourceSet = editingDomain
								.getResourceSet();
						// Get the URI of the metadata file.
						URI fileURI = URI.createPlatformResourceURI(inputFile
								.getFullPath().toString(), true);
						// Create a resource for this file.
						Resource resource = resourceSet.createResource(fileURI);

						// create Metadata object and add to resource for saving
						if (model != null)
						{
							resource.getContents().add(model);
						}

						// Save the contents of the resource to the file system.
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING, "UTF-8");
						resource.save(options);
						inputFile.refreshLocal(IResource.DEPTH_ZERO, monitor);
						((BasicCommandStack) editingDomain.getCommandStack())
								.saveIsDone();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					catch (CoreException e)
					{
						e.printStackTrace();
					}
				}
			};
			operation.run(monitor);
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		catch (Exception e)
		{
			// TODO
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs()
	{
		// don't do anything as the project.meta file needs to be saved at
		// project
		// root with exactly that name
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		// don't allow this saving with another name as this is an integral
		// requirement of SNESDE parser
		return false;
	}

	/**
	 * Executed once the resource has been changed by an external program and
	 * the editor should be notified about it At this point in time, it only
	 * reacts upon the project being closed/deleted -> editor will close too
	 * 
	 * @param event
	 *            the resource change event
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event)
	{
		IResourceDelta resourceDelta = event.getDelta();

		if (resourceDelta != null)
		{
			IResourceDelta[] removedDeltas = resourceDelta
					.getAffectedChildren(IResourceDelta.REMOVED);
			IResourceDelta[] preDeleteDeltas = resourceDelta
					.getAffectedChildren(IResourceChangeEvent.PRE_DELETE);
			ArrayList<IResourceDelta> deltaList = new ArrayList<IResourceDelta>();
			if ((removedDeltas != null) && (removedDeltas.length != 0))
			{
				deltaList.addAll(Arrays.asList(removedDeltas));
			}
			if ((preDeleteDeltas != null) && (preDeleteDeltas.length != 0))
			{
				deltaList.addAll(Arrays.asList(preDeleteDeltas));
			}

			IProject project = null;
			for (IResourceDelta delta : deltaList)
			{
				IResource resource = delta.getResource();
				IFile input = ((FileEditorInput) getEditorInput()).getFile();

				if (input != null)
				{
					project = input.getProject();
					if ((project != null) && (resource != null)
							&& (resource instanceof IProject)
							&& (resource.getName().equals(project.getName())))
					{
						if ((delta.getKind() == IResourceDelta.REMOVED)
								|| (!input.exists()))
						{
							close(false);
							return;
						}
					}
				}
			}
		}
	}

	/**
	 * returns the editingDomain
	 * 
	 * @return the editingDomain for this editor
	 */
	@Override
	public EditingDomain getEditingDomain()
	{
		return this.editingDomain;
	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input)
			throws PartInitException
	{
		setSite(site);
		setInput(input);

		// EditingDomainActionBarContributor cont =
		// (EditingDomainActionBarContributor)site.getActionBarContributor();
		// cont.getActionBars().setGlobalActionHandler(ActionFactory.UNDO.getId(),
		// undoAction);

		if (!(input instanceof IFileEditorInput)
				&& !(input instanceof FileStoreEditorInput))
		{
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput or FileStoreEditorInput");
		}
		ProgressMonitorDialog pmdialog = new ProgressMonitorDialog(
				site.getShell());
		try
		{
			pmdialog.run(false, false, new IRunnableWithProgress()
			{

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException
				{
					monitor.beginTask("Opening editor...",
							IProgressMonitor.UNKNOWN);
					try
					{
						createModel(site, input);
					}
					catch (PartInitException e)
					{
						// TODO: handle properly
						e.printStackTrace();
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally
					{
						monitor.done();
					}
				}
			});
		}
		catch (InvocationTargetException e)
		{
			// TODO
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			// TODO
			e.printStackTrace();
		}
	}

	/**
	 * Creates the model of the file being processed
	 * 
	 * @param site
	 *            the editor site
	 * @param input
	 *            the IFileEditorInput to open
	 * @throws Exception
	 */
	private void createModel(final IEditorSite site, final IEditorInput input)
			throws Exception
	{
		// store it for future reference

		if (input instanceof IFileEditorInput)
		{
			inputFile = ((IFileEditorInput) input).getFile();
		}

		URI inputUri = URI.createPlatformResourceURI(inputFile.getFullPath()
				.toString(), true);
		Resource res = editingDomain.getResourceSet().getResource(inputUri,
				true);
		res.load(null);

		model = (MetadataImpl) res.getContents().get(0);

		// listen for changes on this IFile so we can close the editor if the
		// project has been closed/deleted
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				ProjectMetaEditor.this, IResourceChangeEvent.POST_CHANGE);
	}
}
