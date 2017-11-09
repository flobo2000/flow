/**
 * 
 */
package com.flow.snesde.core;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * @author flo
 * 
 */
public class SnesdeWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{
	
	/**
	 * @param configurer
	 */
	public SnesdeWorkbenchWindowAdvisor(
			final IWorkbenchWindowConfigurer configurer)
	{
		super(configurer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void preWindowOpen()
	{
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setShowPerspectiveBar(false);
	}
	
}
