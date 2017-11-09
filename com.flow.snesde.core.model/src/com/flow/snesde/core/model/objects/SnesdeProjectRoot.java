/**
 * 
 */
package com.flow.snesde.core.model.objects;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.flow.snesde.core.model.util.DuplicateFileException;
import com.flow.snesde.core.model.util.UnknownTypeException;

/**
 * @author flo
 * 
 */
public class SnesdeProjectRoot
{
	public static final int FILETYPE_PROJECTMETA = 0;
	public static final int FILETYPE_MAINSCRIPT = 1;
	public static final int FILETYPE_SCRIPT = 3;
	public static final int FILETYPE_PALETTE = 4;
	public static final int FILETYPE_TILESET = 5;

	// Hashmaps for filename --> HashMap(id -> modelobject mappings)
	private final HashMap<String, AbstractModelObject> metadatas = new HashMap<String, AbstractModelObject>();
	private final HashMap<String, AbstractModelObject> mainscripts = new HashMap<String, AbstractModelObject>();
	private final HashMap<String, HashMap<String, AbstractModelObject>> scripts = new HashMap<String, HashMap<String, AbstractModelObject>>();
	private final HashMap<String, HashMap<String, AbstractModelObject>> palettes = new HashMap<String, HashMap<String, AbstractModelObject>>();
	private final HashMap<String, HashMap<String, AbstractModelObject>> tilesets = new HashMap<String, HashMap<String, AbstractModelObject>>();

	private String projectName = null;

	/**
	 * creates the projectRoot
	 * 
	 * @param project
	 *            the IProject
	 */
	public SnesdeProjectRoot(final IProject project)
	{
		projectName = project.getName();
	}

	/**
	 * adds the model with the given id to the project
	 * 
	 * @param id
	 * @param o
	 */
	public void addModelObject(String id, AbstractModelObject o)
	{
		if (o instanceof MainscriptObject)
		{
			mainscripts.put(id, o);

		}
		else if (o instanceof MetadataObject)
		{
			metadatas.put(id, o);
		}
		else if (o instanceof PaletteObject)
		{
			HashMap<String, AbstractModelObject> nameMap = palettes.get(o
					.getName());
			if (nameMap == null)
			{
				nameMap = new HashMap<String, AbstractModelObject>();
				nameMap.put(id, o);
				palettes.put(o.getName(), nameMap);
			}
			else
			{
				nameMap.put(id, o);
			}
		}
		else if (o instanceof ScriptObject)
		{
			HashMap<String, AbstractModelObject> nameMap = scripts.get(o
					.getName());
			if (nameMap == null)
			{
				nameMap = new HashMap<String, AbstractModelObject>();
				nameMap.put(id, o);
				scripts.put(o.getName(), nameMap);
			}
			else
			{
				nameMap.put(id, o);
			}
		}
		else if (o instanceof TilesetObject)
		{
			HashMap<String, AbstractModelObject> nameMap = tilesets.get(o
					.getName());
			if (nameMap == null)
			{
				nameMap = new HashMap<String, AbstractModelObject>();
				nameMap.put(id, o);
				tilesets.put(o.getName(), nameMap);
			}
			else
			{
				nameMap.put(id, o);
			}
		}
	}

	/**
	 * remove the model from the project
	 * 
	 * @param id
	 */
	public void removeModel(String id)
	{
		mainscripts.remove(id);
		metadatas.remove(id);
		Set<String> keySet = scripts.keySet();
		for (String key : keySet)
		{
			HashMap<String, AbstractModelObject> hashMap = scripts.get(key);
			hashMap.remove(id);
		}
		keySet = palettes.keySet();
		for (String key : keySet)
		{
			HashMap<String, AbstractModelObject> hashMap = palettes.get(key);
			hashMap.remove(id);
		}
		keySet = tilesets.keySet();
		for (String key : keySet)
		{
			HashMap<String, AbstractModelObject> hashMap = tilesets.get(key);
			hashMap.remove(id);
		}
	}

	/**
	 * returns all PaletteObjects
	 * 
	 * @param i
	 * @return
	 */
	public HashSet<PaletteObject> getPalettesForColorDepth(int i)
	{
		HashSet<PaletteObject> returnSet = new HashSet<PaletteObject>();
		Set<String> paletteNames = palettes.keySet();
		for (String name : paletteNames)
		{
			HashMap<String, AbstractModelObject> hashMap = palettes.get(name);
			Set<String> ids = hashMap.keySet();
			for (String id : ids)
			{
				PaletteObject pal = (PaletteObject) hashMap.get(id);
				if (pal.getNumberOfColors() == i)
				{
					returnSet.add(pal);
				}

			}
		}
		return returnSet;
	}

	/**
	 * returns the file reference for the project file with the given name
	 * (format filename.extension). This will be used by the preprocessor for
	 * instance
	 * 
	 * @param type
	 *            the type of the file to return (see
	 *            SnesdeProjectRoot.FILETYPE_XYZ for valid values)
	 * @param name
	 *            the name of the file to locate
	 * @return the IFile reference of the file searched
	 * @throws DuplicateFileException
	 *             thrown if two files with the given name and type exist within
	 *             the project
	 * @throws FileNotFoundException
	 *             thrown if the file referenced with the given name and type
	 *             doesn't exist within the project
	 * @throws UnknownTypeException
	 *             thrown if an invalid identifier was passed for filetype
	 */
	public IFile getFileRef(int type, String name)
			throws DuplicateFileException, FileNotFoundException,
			UnknownTypeException
	{
		switch (type)
		{
			case FILETYPE_MAINSCRIPT:
			{
				if (mainscripts.size() > 1)
				{
					throw new DuplicateFileException(
							"The file 'main.dscript' exists multiple times within the project.");
				}
				else if (mainscripts.size() == 0)
				{
					throw new FileNotFoundException(
							"The file 'main.dscript' couldn't be found.");
				}
				else
				{
					return (mainscripts.get(mainscripts.keySet().toArray()[0]))
							.getFile();
				}
			}
			case FILETYPE_PROJECTMETA:
			{
				if (metadatas.size() > 1)
				{
					throw new DuplicateFileException(
							"The file 'project.meta' exists multiple times within the project.");
				}
				else if (metadatas.size() == 0)
				{
					throw new FileNotFoundException(
							"The file 'project.meta' couldn't be found.");
				}
				else
				{
					return (metadatas.get(metadatas.keySet().toArray()[0]))
							.getFile();
				}
			}
			case FILETYPE_PALETTE:
			{
				HashMap<String, AbstractModelObject> modelIdToModelMap = palettes
						.get(name);
				return getIFileForNameFrom(name, modelIdToModelMap);
			}
			case FILETYPE_SCRIPT:
			{
				HashMap<String, AbstractModelObject> modelIdToModelMap = scripts
						.get(name);
				return getIFileForNameFrom(name, modelIdToModelMap);
			}
			case FILETYPE_TILESET:
			{
				HashMap<String, AbstractModelObject> modelIdToModelMap = tilesets
						.get(name);
				return getIFileForNameFrom(name, modelIdToModelMap);
			}
			default:
			{
				throw new UnknownTypeException(
						"The referenced file type with ID " + type
								+ " is not known.");
			}
		}
	}

	/**
	 * returns the single IFile reference for the given name or throws
	 * exceptions if the ref is not in the list or duplicate
	 * 
	 * @param name
	 *            the name (with which this map was created) only passed for
	 *            human readable error messages
	 * @param modelIdToModelMap
	 *            the map containing all entries for the given name. may be zero
	 *            (bad), one (good) or many times (bad as we can't decide which
	 *            to pick)
	 * @return the IFile reference for the single file (if there's only exactly
	 *         one)
	 * @throws DuplicateFileException
	 *             if there's more than one file with the given name
	 * @throws FileNotFoundException
	 *             if there's no file with the given name
	 */
	private IFile getIFileForNameFrom(String name,
			HashMap<String, AbstractModelObject> modelIdToModelMap)
			throws DuplicateFileException, FileNotFoundException
	{
		if (modelIdToModelMap == null || modelIdToModelMap.size() == 0)
		{
			throw new FileNotFoundException("The file '" + name
					+ "' couldn't be found.");
		}
		if (modelIdToModelMap.size() > 1)
		{
			throw new DuplicateFileException("The file " + name
					+ " exists multiple times within the project.");
		}
		else
		{
			String id = (String) (modelIdToModelMap.keySet().toArray())[0];
			AbstractModelObject abstractModelObject = modelIdToModelMap.get(id);
			if (abstractModelObject != null)
			{
				IFile f = abstractModelObject.getFile();
				if (f != null)
				{
					return f;
				}
			}
			throw new FileNotFoundException("The file '" + name
					+ "' couldn't be found.");
		}
	}

	/**
	 * returns a set of all model objects with the given type
	 * 
	 * @param type
	 *            the type integer (see SnesdeProjectRoot.FILETYPE_XXX)
	 * @return the hashset
	 * @throws UnknownTypeException
	 */
	public HashSet<AbstractModelObject> getModelObjectsOfClass(int type)
			throws UnknownTypeException
	{
		HashSet<AbstractModelObject> returnSet = new HashSet<AbstractModelObject>();
		switch (type)
		{
			case FILETYPE_MAINSCRIPT:
			{
				Collection<AbstractModelObject> values = mainscripts.values();
				for (AbstractModelObject m : values)
				{
					returnSet.add(m);
				}
				return returnSet;
			}
			case FILETYPE_PROJECTMETA:
			{
				Collection<AbstractModelObject> values = metadatas.values();
				for (AbstractModelObject m : values)
				{
					returnSet.add(m);
				}
				return returnSet;
			}
			case FILETYPE_PALETTE:
			{
				Collection<HashMap<String, AbstractModelObject>> values = palettes
						.values();
				for (HashMap m : values)
				{
					Collection<AbstractModelObject> values2 = m.values();
					for (AbstractModelObject a : values2)
					{
						returnSet.add(a);
					}
				}
				return returnSet;
			}
			case FILETYPE_SCRIPT:
			{
				Collection<HashMap<String, AbstractModelObject>> values = scripts
						.values();
				for (HashMap m : values)
				{
					Collection<AbstractModelObject> values2 = m.values();
					for (AbstractModelObject a : values2)
					{
						returnSet.add(a);
					}
				}
				return returnSet;
			}
			case FILETYPE_TILESET:
			{
				Collection<HashMap<String, AbstractModelObject>> values = tilesets
						.values();
				for (HashMap m : values)
				{
					Collection<AbstractModelObject> values2 = m.values();
					for (AbstractModelObject a : values2)
					{
						returnSet.add(a);
					}
				}
				return returnSet;
			}
			default:
			{
				throw new UnknownTypeException(
						"The referenced file type with ID " + type
								+ " is not known.");
			}
		}
	}

	/**
	 * Searches for model objects within the project with the ID passed. Returns
	 * an IFile reference to them if found, null otherwise.
	 * 
	 * @param ID
	 *            the ID of the resource to export
	 * @return the IFile reference if found, null otherwise
	 */
	public IFile getFileRef(String ID)
	{
		AbstractModelObject object = metadatas.get(ID);
		if (object != null)
		{
			return object.getFile();
		}
		object = mainscripts.get(ID);
		if (object != null)
		{
			return object.getFile();
		}
		Collection<HashMap<String, AbstractModelObject>> values = scripts
				.values();
		for (HashMap<String, AbstractModelObject> map : values)
		{
			object = map.get(ID);
			if (object != null)
			{
				return object.getFile();
			}
		}
		values = palettes.values();
		for (HashMap<String, AbstractModelObject> map : values)
		{
			object = map.get(ID);
			if (object != null)
			{
				return object.getFile();
			}
		}
		values = tilesets.values();
		for (HashMap<String, AbstractModelObject> map : values)
		{
			object = map.get(ID);
			if (object != null)
			{
				return object.getFile();
			}
		}
		return null;
	}

	/**
	 * returns all resource names (no duplicates if existing) for the given
	 * resource class
	 * 
	 * @param filetype
	 *            the class parameter. See SnesdeProjectRoot.FILETYPE_XYZ
	 * @return the string array with all names
	 * @throws IllegalArgumentException
	 *             if an illegal filetype was requested
	 */
	public String[] getAllUniqueResourceNames(int filetype)
			throws IllegalArgumentException
	{
		Vector<String> names = new Vector<String>();

		HashMap<String, HashMap<String, AbstractModelObject>> source = null;
		String extensionToChopOff = "";

		if (filetype == FILETYPE_SCRIPT)
		{
			source = scripts;
			extensionToChopOff = ".dscript";
		}
		else if (filetype == FILETYPE_PALETTE)
		{
			source = palettes;
			extensionToChopOff = ".pal";
		}
		else if (filetype == FILETYPE_TILESET)
		{
			source = tilesets;
			extensionToChopOff = ".tile";
		}
		else
		{
			throw new IllegalArgumentException(
					"Tried to obtain all unique resource names for an unknown identifier "
							+ filetype);
		}

		// transform source into unique names vector
		// source is of the following structure:
		// HashMap(filename --> HashMap(id -> modelobject mappings))
		Set<String> keySet = source.keySet();
		for (String s : keySet)
		{
			s = s.substring(0, s.length() - extensionToChopOff.length());
			if (!names.contains(s))
			{
				names.add(s);
			}
		}

		String[] ret = new String[names.size()];
		for (int i = 0; i < names.size(); i++)
		{
			ret[i] = names.get(i);
		}
		return ret;
	}

	/**
	 * Returns a list of all possible jump labels accross all scripts in the
	 * project
	 * 
	 * @return the array of jump labels
	 */
	public String[] getAllJumpLabels()
	{
		Vector<String> labels = new Vector<String>();

		// add jump labels for every regular script
		Set<String> keySet = scripts.keySet();
		for (String s : keySet)
		{
			HashMap<String, AbstractModelObject> hashMap = scripts.get(s);
			Set<String> keySet2 = hashMap.keySet();
			for (String t : keySet2)
			{
				ScriptObject script = (ScriptObject) hashMap.get(t);
				String[] jumpLabels = script.getLabels();

				for (String u : jumpLabels)
				{
					if (!labels.contains(u))
					{
						labels.add(u);
					}
				}
			}
		}

		// add jump labels for main script
		keySet = mainscripts.keySet();
		for (String s : keySet)
		{
			ScriptObject mainScript = (ScriptObject) mainscripts.get(s);
			String[] jumpLabels = mainScript.getLabels();

			for (String u : jumpLabels)
			{
				if (!labels.contains(u))
				{
					labels.add(u);
				}
			}
		}

		// return array
		String[] ret = new String[labels.size()];
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = labels.get(i);
		}
		return ret;
	}
}
