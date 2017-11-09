package com.flow.snesde.core.project;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;

/**
 * @author flo
 * 
 *         The SNESDE project builder... will load all resource content into ram
 *         for reference
 * 
 *         Reference: http://wiki.eclipse.org/
 *         FAQ_How_do_I_implement_an_incremental_project_builder%3F
 */
public class SnesdeProjectBuilder extends IncrementalProjectBuilder
{
	/**
	 * the ID for reference in plugin.xml
	 */
	public static final String id = "com.flow.snesde.core.project.SnesdsProjectBuilder";

	@Override
	protected IProject[] build(final int kind,
			@SuppressWarnings("rawtypes") final Map args,
			final IProgressMonitor monitor) throws CoreException
	{
		if (kind == IncrementalProjectBuilder.FULL_BUILD)
		{
			fullBuild(monitor);
		}
		else
		{
			IResourceDelta delta = getDelta(getProject());
			if (delta == null)
			{
				fullBuild(monitor);
			}
			else
			{
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	/**
	 * This will incrementally build everything needed as the delta has been
	 * changed
	 * 
	 * @param delta
	 *            the resource delta to react upon
	 * @param monitor
	 *            the progress monitor to use for job progress indication
	 */
	private void incrementalBuild(final IResourceDelta delta,
			final IProgressMonitor monitor)
	{
		// System.out.println("incremental build on " + delta);
		try
		{
			delta.accept(new IResourceDeltaVisitor()
			{
				@Override
				public boolean visit(final IResourceDelta delta)
						throws CoreException
				{
					// get resource
					IResource res = delta.getResource();

					if (res instanceof IFile)
					{
						// only take care of files for now
						int action = delta.getKind();
						switch (action)
						{
							case IResourceDelta.ADDED:
							{
								// System.out.println("added: "
								// + delta.getResource().getRawLocation());
								FlowWorkspace.addFileInProject((IFile) res,
										FlowWorkspace.getProjectRoot(res
												.getProject()));
								break;
							}
							case IResourceDelta.CHANGED:
							{
								// System.out.println("changed: "
								// + delta.getResource().getRawLocation());
								FlowWorkspace.changeFileInProject((IFile) res,
										FlowWorkspace.getProjectRoot(res
												.getProject()));
								break;
							}
							case IResourceDelta.REMOVED:
							{
								// System.out.println("removed: "
								// + delta.getResource().getRawLocation());
								FlowWorkspace.removeFileInProject((IFile) res,
										FlowWorkspace.getProjectRoot(res
												.getProject()));
								break;
							}
							default:
								break;
						}
						return false;
					}
					else
					{
						// don't take care of folders for now
						return true;
					}
				}
			});
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This will be invoked if all workingdata should be removed and recreated
	 * 
	 * @param monitor
	 *            the job monitor to be used
	 * @throws CoreException
	 *             may occur if there was a problem reading the project
	 *             resources
	 */
	private void fullBuild(final IProgressMonitor monitor) throws CoreException
	{
		// System.out.println("full build");
		SnesdeProjectRoot project = FlowWorkspace
				.recreateProjectRoot(getProject());
	}
}
