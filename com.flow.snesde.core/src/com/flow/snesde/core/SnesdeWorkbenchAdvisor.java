/**
 * 
 */
package com.flow.snesde.core;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * @author flo
 * 
 */
public class SnesdeWorkbenchAdvisor extends WorkbenchAdvisor
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.application.WorkbenchAdvisor#createWorkbenchWindowAdvisor()
	 */
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			final IWorkbenchWindowConfigurer configurer)
	{
		return new SnesdeWorkbenchWindowAdvisor(configurer);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
	 */
	@Override
	public String getInitialWindowPerspectiveId()
	{
		// sets the initial perspective
		// return "org.eclipse.ui.resourcePerspective";
		return "com.flow.snesde.core.perspectives.snesdeDefaultPerspective";
	}
}
