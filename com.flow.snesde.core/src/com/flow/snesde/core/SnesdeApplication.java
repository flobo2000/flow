/**
 * 
 */
package com.flow.snesde.core;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author flo
 * 
 */
public class SnesdeApplication implements IApplication
{
	
	public static final String ID = "com.flow.snesde.core.application";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	@Override
	public Object start(final IApplicationContext context) throws Exception
	{
		Display display = PlatformUI.createDisplay();
		try
		{
			int returnCode = PlatformUI.createAndRunWorkbench(display,
					new SnesdeWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
			{
				return IApplication.EXIT_RESTART;
			} else
			{
				return IApplication.EXIT_OK;
			}
		} finally
		{
			display.dispose();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop()
	{
		// TODO Auto-generated method stub
		
	}
	
}
