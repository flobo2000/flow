/**
 * 
 */
package com.flow.snesde.core.model.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.flow.snesde.core.model.Activator;

/**
 * @author flo
 * 
 */
public class ScriptObject extends AbstractModelObject
{

	/**
	 * The hashmap mapping all occurring label names to the line numbers they
	 * appeared in
	 */
	private final HashMap<String, Integer> labels = new HashMap<String, Integer>();

	/**
	 * the list of included scripts (for calculating inclusion cycles for
	 * instance)
	 */
	private final HashMap<String, Integer> includedScripts = new HashMap<String, Integer>();

	/**
	 * lists of other resources... can be used to validate duplicate inclusion
	 * for instance
	 */
	private final HashMap<String, Integer> includedPalettes = new HashMap<String, Integer>();
	private final HashMap<String, Integer> includedTilesets = new HashMap<String, Integer>();

	/**
	 * @param res
	 *            the Resource the paletteObject is being created by
	 */
	public ScriptObject(final IFile file)
	{
		super(file);
		// handle resource to fill relevant metadata
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(getFileForIFile(file)), "UTF8"));
			String line = null;
			int lineNumber = 0;
			while ((line = in.readLine()) != null)
			{
				lineNumber++;
				line = line.split(";")[0];

				// handle snesde include directives
				if (line.contains("snesde.include("))
				{
					// remove all whitespace
					line = line.replaceAll("\\s+", "");
					if (line.contains("snesde.include(script,"))
					{
						String scriptName = ((line
								.split("snesde.include\\(script,"))[1])
								.split("\\)")[0];
						includedScripts.put(scriptName, lineNumber);
					}
					else if (line.contains("snesde.include(palette,"))
					{
						// get palette file
						String paletteName = ((line
								.split("snesde.include\\(palette,"))[1])
								.split("\\)")[0];
						includedPalettes.put(paletteName, lineNumber);
					}
					else if (line.contains("snesde.include(tileset,"))
					{
						// get tileset file
						String tilesetName = ((line
								.split("snesde.include\\(tileset,"))[1])
								.split("\\)")[0];
						includedTilesets.put(tilesetName, lineNumber);
					}
					// TODO: add more include directives as the corresponding
					// objects are there
				}
				else
				{
					// look for labels
					// remove whitespace
					line = line.replaceAll("\\s+", "");
					if (isLabel(line))
					{
						String labelName = line.substring(0, line.length() - 1);
						labels.put(labelName, lineNumber);
					}
				}
			}
			in.close();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ResourceException e)
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

	/**
	 * checks if the string passed is of format Anycharacters + :
	 * 
	 * @param name
	 *            the string to check
	 * @return true if matches, false otherwise
	 */
	private boolean isLabel(String name)
	{
		if (name.endsWith(":"))
		{
			return isAlpha(name.substring(0, name.length() - 1));
		}
		return false;
	}

	/**
	 * checks if the string passed only consists of lettes
	 * 
	 * @param name
	 *            the string to check
	 * @return true if so, false otherwise
	 */
	private boolean isAlpha(String name)
	{
		char[] chars = name.toCharArray();

		for (char c : chars)
		{
			if (!Character.isLetter(c))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * returns all labels occurring within this file
	 * 
	 * @return the labels
	 */
	public String[] getLabels()
	{
		Set<String> keySet = labels.keySet();
		String[] ret = new String[keySet.size()];
		int i = 0;
		for (String s : keySet)
		{
			ret[i] = s;
			i++;
		}
		return ret;
	}

	/**
	 * @param f
	 *            the IFile to get the java.io.file for
	 * @return the java file corresponding to the IFile
	 * @throws ResourceException
	 *             may occur if the rousrce couldn't be opened
	 */
	private File getFileForIFile(final IFile f) throws ResourceException
	{
		IPath location = f.getLocation();
		if (location == null)
		{
			throw new ResourceException(new Status(IStatus.ERROR, Activator.id,
					"No location for IFile " + f.getName() + " found."));
		}
		File file = location.toFile();
		if (file == null)
		{
			throw new ResourceException(new Status(IStatus.ERROR, Activator.id,
					"File for IFile " + f.getName() + " was null."));
		}
		return file;
	}
}
