package com.flow.snesde.core.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author flo
 * 
 *         This class defines the default perspective for SNESDE
 */
public class SnesdeDefaultPerspective implements IPerspectiveFactory
{
	
	@Override
	public void createInitialLayout(final IPageLayout layout)
	{
		defineActions(layout);
		defineLayout(layout);
	}
	
	/**
	 * @param layout
	 *          dont know about this method yet
	 */
	private void defineActions(final IPageLayout layout)
	{
		// TODO: implement?!
	}
	
	/**
	 * @param layout
	 *          dont know abo
	 */
	private void defineLayout(final IPageLayout layout)
	{
		// TODO: implement shomething?!
	}
}
