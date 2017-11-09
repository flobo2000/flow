package com.flow.snesde.core.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

/**
 * @author flo
 * 
 *         The SNESDE project nature marking a project as a SNESDE project
 */
public class SnesdeProjectNature implements IProjectNature
{
	/**
	 * the unique nature id for all associated projects
	 */
	public static final String NATURE_ID = "com.flow.snesde.core.project.SnesdeProjectNature";
	
	@Override
	public void configure() throws CoreException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deconfigure() throws CoreException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IProject getProject()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setProject(final IProject project)
	{
		// TODO Auto-generated method stub
		
	}
	
}
