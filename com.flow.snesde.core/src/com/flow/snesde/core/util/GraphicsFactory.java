package com.flow.snesde.core.util;

import java.io.FileNotFoundException;
import java.net.URL;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.internal.databinding.BindingStatus;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

import com.flow.snesde.core.Activator;

/**
 * A utility class providing static methods to access graphical resources
 * 
 * @author flo
 * 
 */
@SuppressWarnings("restriction")
public class GraphicsFactory
{
	/**
	 * constant for icon size selectable
	 */
	public static final int ICONSIZE_16x16 = 16;

	/**
	 * graphics base directory
	 */
	private static final String graphicsDir = "graphics";

	/**
	 * the icons subdirectory
	 */
	private static final String iconsDir = "icons";

	/**
	 * color registry for managing colors
	 */
	public static final ColorRegistry colorRegistry = new ColorRegistry();

	/**
	 * This utility method searches within the specific path for an icon of the
	 * given name and dimensions. If it finds it, it will return an
	 * ImageDescriptor. Otherwise, it'll throw an Exception.
	 * 
	 * @param filename
	 *            the name of the file icon to be opened (eg. myIcon.png)
	 * @param size
	 *            a valid size. Sizes are constants of this class. For example
	 *            GraphicsFactory.ICONSIZE_16x16 would be a valid identifier.
	 * @return an ImageDescriptor object including the icon. Not null
	 * @throws FileNotFoundException
	 *             thrown if the icon specified couldn't be found
	 */
	public static ImageDescriptor getIconAsImageDescriptor(
			final String filename, final int size) throws FileNotFoundException
	{
		String filepath = graphicsDir + "/" + iconsDir + "/" + size + "/"
				+ filename;
		// can't return null as we're within the bundle we search for
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		URL url = FileLocator.find(bundle, new Path(filepath), null);
		if (url == null)
		{
			throw new FileNotFoundException("The icon \"" + filepath
					+ "\" couln't be found.");
		}
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image;
	}

	/**
	 * This utility method returns an image to be used within SNESDE
	 * 
	 * @param filename
	 *            the filename to look for in the /graphics/ subfolder
	 * @return the swt compatible image object
	 * @throws FileNotFoundException
	 *             may be thrown if the image wasn't present
	 */
	public static Image getImage(final String filename)
			throws FileNotFoundException
	{
		String filepath = graphicsDir + "/" + filename;
		// can't return null as we're within the bundle we search for
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		URL url = FileLocator.find(bundle, new Path(filepath), null);
		if (url == null)
		{
			throw new FileNotFoundException("The image \"" + filepath
					+ "\" couln't be found.");
		}
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();
	}

	/**
	 * This method aggregates all validation results of a databinding into a new
	 * status icon and message for the decorator given.
	 * 
	 * @param decorator
	 *            the decorator to be updated
	 * @param binding
	 *            the binding to get the validation result(s) from
	 */
	public static void updateDecoratorWithBindingValidation(
			final ControlDecoration decorator, final Binding binding)
	{
		// make sure binding evaluation is up to date
		binding.validateTargetToModel();

		WritableValue writableValue = (WritableValue) binding
				.getValidationStatus();
		BindingStatus bindingStatus = ((BindingStatus) writableValue.getValue());
		IStatus[] stati = bindingStatus.getChildren();

		int mostSevereStatus = IStatus.OK;
		// the most severe status decides on decorator icon
		for (IStatus status : stati)
		{
			if (status.getSeverity() > mostSevereStatus)
			{
				mostSevereStatus = status.getSeverity();
			}
		}

		// map the most severe status to the standard decorator icons
		String iconToLoad;
		switch (mostSevereStatus)
		{
			case IStatus.ERROR:
				iconToLoad = FieldDecorationRegistry.DEC_ERROR;
				break;
			case IStatus.WARNING:
				iconToLoad = FieldDecorationRegistry.DEC_WARNING;
				break;
			case IStatus.INFO:
				iconToLoad = FieldDecorationRegistry.DEC_CONTENT_PROPOSAL;
				break;
			default:
				iconToLoad = "noIcon";
				break;
		}

		// if multiple stati were given, aggregate into one single String list
		String msg = "";
		if (stati.length > 1)
		{
			msg = "Multiple markers occurred: ";
		}
		for (IStatus status : stati)
		{
			if (!msg.equals(""))
			{
				msg += "\n";
			}
			msg += status.getMessage();
		}

		// update the decorator
		if (mostSevereStatus == IStatus.OK)
		{
			decorator.hide();
		}
		else
		{
			decorator.setImage((FieldDecorationRegistry.getDefault()
					.getFieldDecoration(iconToLoad)).getImage());
			decorator.setDescriptionText(msg);
			decorator.show();
		}
	}
}
