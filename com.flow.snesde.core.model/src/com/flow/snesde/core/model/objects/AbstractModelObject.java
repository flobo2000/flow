package com.flow.snesde.core.model.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

public abstract class AbstractModelObject
{
	String identifier = null;

	String name = null;

	private final IFile file;

	public AbstractModelObject(IFile file)
	{
		this.file = file;
		setIdentifier(file);
		setName(file);
	}

	private void setIdentifier(IFile file)
	{
		this.identifier = file.getProject().getName() + "/"
				+ file.getProjectRelativePath().toString();
	}

	private void setName(IFile file)
	{
		this.name = file.getName();
	}

	public String getName()
	{
		return this.name;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * tries to generate an EMF model out of the file passed
	 * 
	 * @param file
	 *            the IFile
	 * @return the model
	 */
	public EObject getEmfModelForFile(IFile file)
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
		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(
				adapterFactory, commandStack, new HashMap<Resource, Boolean>());

		Resource res = editingDomain.getResourceSet()
				.getResource(fileURI, true);
		try
		{
			res.load(null);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res.getContents().get(0);
	}

	/**
	 * returns the file reference for the project
	 * 
	 * @return
	 */
	public IFile getFile()
	{
		return file;
	}
}
