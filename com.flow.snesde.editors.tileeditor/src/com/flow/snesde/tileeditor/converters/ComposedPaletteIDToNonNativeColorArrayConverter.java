package com.flow.snesde.tileeditor.converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import palette.impl.PaletteImpl;

import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.uilib.generic.NonNativeColor;

public class ComposedPaletteIDToNonNativeColorArrayConverter implements
		IConverter
{
	@Override
	public Object getFromType()
	{
		return String.class;
	}

	@Override
	public Object getToType()
	{
		return NonNativeColor[].class;
	}

	@Override
	public Object convert(Object fromObject)
	{
		if (fromObject instanceof String)
		{
			// string should be of format "name.pal
			// (/projectName/subfolderpath/name.pal)
			String[] parts = ((String) fromObject).split("/");
			parts = parts[0].split("\\(");
			// get project name
			String projectName = parts[1];
			// get filename
			String fileName = parts[0].substring(0, parts[0].length() - 1);
			parts = ((String) fromObject).split("\\(");
			// get id for obtaining the correct file (in case we have duplicates
			// in different folders)
			String id = parts[1].substring(0, parts[1].length() - 1);

			// get project reference
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);
			try
			{
				// get palette file reference
				SnesdeProjectRoot projectRoot = FlowWorkspace
						.getProjectRoot(project);
				IFile paletteFile = projectRoot.getFileRef(id);

				// load EMF model from file
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
				BasicCommandStack commandStack = new BasicCommandStack();
				AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(
						adapterFactory, commandStack,
						new HashMap<Resource, Boolean>());
				URI inputUri = URI.createPlatformResourceURI(paletteFile
						.getFullPath().toString(), true);
				Resource res = editingDomain.getResourceSet().getResource(
						inputUri, true);
				res.load(null);
				PaletteImpl model = (PaletteImpl) res.getContents().get(0);

				// read color data, create NonNativeColors and return the
				// created array
				EList<palette.Color> colors2 = model.getColors();
				NonNativeColor[] retArray = new NonNativeColor[colors2.size()];
				int i = 0;
				for (palette.Color c : colors2)
				{
					NonNativeColor to = new NonNativeColor(c.getR(), c.getG(),
							c.getB());
					retArray[i] = to;
					i++;
				}
				return retArray;
			}
			catch (CoreException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
