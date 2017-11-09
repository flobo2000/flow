/**
 * 
 */
package com.flow.snesde.launchers.sneslauncher;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

import com.flow.snesde.launchers.sneslauncher.tabs.SnesLauncherAdvancedTab;
import com.flow.snesde.launchers.sneslauncher.tabs.SnesLauncherMainTab;

/**
 * @author flo
 * 
 */
public class SnesLauncherTabGroup extends AbstractLaunchConfigurationTabGroup
{
	private SnesLauncherMainTab snesLauncherMainTab;
	private SnesLauncherAdvancedTab snesLauncherAdvancedTab;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse
	 * .debug.ui.ILaunchConfigurationDialog, java.lang.String)
	 */
	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog,
			final String mode)
	{
		snesLauncherMainTab = new SnesLauncherMainTab();
		snesLauncherMainTab.setLaunchConfigurationDialog(dialog);
		snesLauncherAdvancedTab = new SnesLauncherAdvancedTab();
		snesLauncherAdvancedTab.setLaunchConfigurationDialog(dialog);
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { snesLauncherMainTab, snesLauncherAdvancedTab };
		setTabs(tabs);
	}	
}
