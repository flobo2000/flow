/**
 * 
 */
package com.flow.snesde.core.model.util;

import java.io.File;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import com.flow.snesde.core.model.objects.AbstractModelObject;
import com.flow.snesde.core.model.objects.MainscriptObject;
import com.flow.snesde.core.model.objects.MetadataObject;
import com.flow.snesde.core.model.objects.PaletteObject;
import com.flow.snesde.core.model.objects.ScriptObject;
import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.objects.TilesetObject;

/**
 * @author flo
 * 
 */
public class FlowWorkspace
{
	/**
	 * Contains a list of all IProject-SnesdeProjectRoot objects
	 */
	private static HashMap<IProject, SnesdeProjectRoot> allProjects = new HashMap<IProject, SnesdeProjectRoot>();

	/**
	 * returns the SnesdeProjectRoot for the given IProject. If there wasn't a
	 * SnesdeProjectRoot for the given IProject before, it is created
	 * 
	 * @param project
	 *            the IProject to get the DSnesdsProjectRoot for
	 * @return the DsnedssProjectRoot
	 * @throws CoreException
	 *             may occur if anything goes wrong reading the project
	 *             resources
	 */
	public static SnesdeProjectRoot getProjectRoot(final IProject project)
			throws CoreException
	{
		// do we already have a SnesdeProjectRoot for the given IProject?
		if (allProjects.get(project) != null)
		{
			// yes --> return it
			return allProjects.get(project);
		}
		// no --> create it, add to HashMap and return it
		SnesdeProjectRoot newRoot = new SnesdeProjectRoot(project);

		IResource[] projectResources = project.members();
		addAllResourceContentToProject(newRoot, projectResources);

		allProjects.put(project, newRoot);
		return newRoot;
	}

	/**
	 * Needs to be invoked by the project builder when a full build is done.
	 * Will remove the complete model reference from the list
	 * 
	 * @param project
	 *            the IProject to remove the SnesdeProjectRoot for
	 * @return the DsnedsProjectRoot created
	 * @throws CoreException
	 *             may occur if anything goes wrong reading the resources of the
	 *             project
	 */
	public static SnesdeProjectRoot recreateProjectRoot(final IProject project)
			throws CoreException
	{
		allProjects.remove(project);
		return getProjectRoot(project);
	}

	/**
	 * will go through all resources recursively and add the corresponding
	 * objects to the projectroot which has been created afresh before
	 * 
	 * @param projectRoot
	 *            the project root to add the resources pojos to
	 * @param resources
	 *            the resource array holding all resources
	 * @throws CoreException
	 */
	private static void addAllResourceContentToProject(
			final SnesdeProjectRoot projectRoot, final IResource[] resources)
			throws CoreException
	{
		for (IResource res : resources)
		{
			if (res instanceof IFolder)
			{
				// recursively try to add all content of the subfolders too
				addAllResourceContentToProject(projectRoot,
						((IFolder) res).members());
			}
			else
			{
				addFileInProject((IFile) res, projectRoot);
			}
		}
	}

	/**
	 * add the file to the project
	 * 
	 * @param res
	 *            the file
	 * @param snesdeProjectRoot
	 *            the project
	 */
	public static void addFileInProject(IFile file, SnesdeProjectRoot project)
	{
		AbstractModelObject m = getModelForFile(file);
		if (m == null)
		{
			// no model object created for the file
			// ignore .project file in project root
			if (file.getName().equals(".project")
					&& file.getParent() instanceof IProject)
			{
				// System.out.println("OK... no model for .project file");
			}
			// ignore files in Output folder
			else if (file.getParent() instanceof IFolder
					&& file.getParent().getName().equals("Output")
					&& file.getParent().getParent() instanceof IProject)
			{
				// System.out.println("OK... no model for file " +
				// file.getName()
				// + " in Output folder.");
			}
			// otherwise print error message
			else
			{
				System.out.println("###Builder Error: adding file "
						+ file.getName() + " returned a null model.");
			}
		}
		else
		{
			String id = m.getIdentifier();

			project.addModelObject(id, m);
		}
	}

	/**
	 * change the file from the project
	 * 
	 * @param res
	 *            the file
	 * @param project
	 *            the project
	 */
	public static void changeFileInProject(IFile file, SnesdeProjectRoot project)
	{
		project.removeModel(getIdForFile(file));

		AbstractModelObject m = getModelForFile(file);
		if (m == null)
		{
			// no model object created for the file
			// ignore .project file in project root
			if (file.getName().equals(".project")
					&& file.getParent() instanceof IProject)
			{
				// System.out.println("OK... no model for .project file");
			}
			// ignore files in Output folder
			else if (file.getParent() instanceof IFolder
					&& file.getParent().getName().equals("Output")
					&& file.getParent().getParent() instanceof IProject)
			{
				// System.out.println("OK... no model for file " +
				// file.getName()
				// + " in Output folder.");
			}
			// otherwise print error message
			else
			{
				System.out.println("###Builder Error: changing file "
						+ file.getName() + " returned a null model.");
			}
		}
		else
		{
			String id = m.getIdentifier();

			project.addModelObject(id, m);
		}
	}

	/**
	 * remove the file from the project
	 * 
	 * @param res
	 *            the file
	 * @param project
	 *            the project
	 */
	public static void removeFileInProject(IFile file, SnesdeProjectRoot project)
	{
		project.removeModel(getIdForFile(file));
	}

	/**
	 * returns the unique identifier for the file associated to the model object
	 * 
	 * @param file
	 *            the file
	 * @return the identifier
	 */
	private static String getIdForFile(IFile file)
	{
		return file.getProject().getName() + "/"
				+ file.getProjectRelativePath().toString();
	}

	/**
	 * creates a model object for the given file reference
	 * 
	 * @param file
	 * @return
	 */
	private static AbstractModelObject getModelForFile(IFile file)
	{
		int type = getModelTypeForFile(file);
		AbstractModelObject o = null;
		switch (type)
		{
			case SnesdeProjectRoot.FILETYPE_PROJECTMETA:
			{
				o = new MetadataObject(file);
				break;
			}
			case SnesdeProjectRoot.FILETYPE_MAINSCRIPT:
			{
				o = new MainscriptObject(file);
				break;
			}
			case SnesdeProjectRoot.FILETYPE_SCRIPT:
			{
				o = new ScriptObject(file);
				break;
			}
			case SnesdeProjectRoot.FILETYPE_PALETTE:
			{
				o = new PaletteObject(file);
				break;
			}
			case SnesdeProjectRoot.FILETYPE_TILESET:
			{
				o = new TilesetObject(file);
				break;
			}
			// TODO: add more as needed
			default:
				break;
		}
		return o;
	}

	/**
	 * Returns the type of the model for the file
	 * 
	 * @param file
	 *            the file reference
	 * @return the model type
	 */
	private static int getModelTypeForFile(IFile file)
	{
		String name = file.getName();
		if (name.equals("project.meta"))
		{
			return SnesdeProjectRoot.FILETYPE_PROJECTMETA;
		}
		else if (name.equals("main.dscript"))
		{
			return SnesdeProjectRoot.FILETYPE_MAINSCRIPT;
		}
		else if (name.endsWith(".dscript"))
		{
			return SnesdeProjectRoot.FILETYPE_SCRIPT;
		}
		else if (name.endsWith(".pal"))
		{
			return SnesdeProjectRoot.FILETYPE_PALETTE;
		}
		else if (name.endsWith(".tile"))
		{
			return SnesdeProjectRoot.FILETYPE_TILESET;
		}
		// TODO: add more as needed
		return -1;
	}
}
