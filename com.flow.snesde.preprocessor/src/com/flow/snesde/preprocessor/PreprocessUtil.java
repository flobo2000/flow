package com.flow.snesde.preprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import palette.Palette;
import palette.impl.PaletteImpl;

import com.flow.snesde.core.model.util.ModelTransformationUtil;

public class PreprocessUtil
{
	/**
	 * Converts the palette with the given name to a WLADX compatible .db
	 * statement
	 * 
	 * @param project
	 *            the project to search the palette in
	 * @param paletteName
	 *            the palette name
	 * @return the .db $XX string
	 * @throws IOException
	 *             if anything went wrong
	 */
	public static String parseEmfModelFromIFile(IFile paletteFile)
			throws IOException
	{
		// create editingDomain
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
				adapterFactory, commandStack, new HashMap<Resource, Boolean>());

		// create palette model
		URI paletteUri = URI.createPlatformResourceURI(paletteFile
				.getFullPath().toString(), true);
		Resource res = editingDomain.getResourceSet().getResource(paletteUri,
				true);
		res.load(null);
		Palette palette = (PaletteImpl) res.getContents().get(0);

		// use ModelTransformationUtil to do the actual conversion of the model
		Vector<String> bytes = ModelTransformationUtil
				.transformModelToByteStrings(palette);

		// add output of the conversion to the output string
		return createDbStatementForWladx(bytes);
	}

	/**
	 * converts the byte string vector to a WLADX compatible .db statement
	 * 
	 * @param bytes
	 *            the vector of bytes in string format
	 * @return the single string .db statement
	 */
	private static String createDbStatementForWladx(Vector<String> bytes)
	{
		String ret = ".db";
		for (int i = 0; i < bytes.size(); i++)
		{
			if (ret.equals(".db"))
			{
				ret = ret + " $" + bytes.get(i);
			}
			else
			{
				ret = ret + ", $" + bytes.get(i);
			}
		}

		return ret;
	}
}
