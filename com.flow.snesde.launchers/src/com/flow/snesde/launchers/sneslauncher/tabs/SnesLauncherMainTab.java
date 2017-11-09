/**
 * 
 */
package com.flow.snesde.launchers.sneslauncher.tabs;

import java.net.URL;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.osgi.framework.Bundle;

import com.flow.snesde.launchers.sneslauncher.tabs.composites.MainTabComposite;

/**
 * @author flo
 * 
 */
public class SnesLauncherMainTab extends AbstractLaunchConfigurationTab
{

	private MainTabComposite composite;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite = new MainTabComposite(parent, SWT.NONE);
		composite.setTab(this);
		setControl(composite);
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		if (composite != null)
		{
			composite.setDefaults(configuration);			
		}
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		composite.initializeFrom(configuration);
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		composite.apply(configuration);
	}

	@Override
	public String getName() {
		return "Main";
	}
	
	@Override
	public Image getImage() {
		String path = "graphics/run.png";
		Bundle bundle = Platform.getBundle("com.flow.snesde.launchers");
		URL url = FileLocator.find(bundle, new Path(path), null);
		ImageDescriptor imageDesc = ImageDescriptor.createFromURL(url);
		Image image = imageDesc.createImage();
		return image;
	}
	
	@Override
	public boolean isValid(ILaunchConfiguration launchConfig)
	{
		setErrorMessage(null);
		if (this.composite.getProjectName().equals(""))
		{
			setErrorMessage("Project name must not be empty.");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canSave() 
	{
		return true;
	}
	
	public void doUpdateLaunchConfigurationDialog() {
		if (getLaunchConfigurationDialog() != null) {
			//order is important here due to the call to 
			//refresh the tab viewer in updateButtons()
			//which ensures that the messages are up to date
			getLaunchConfigurationDialog().updateButtons();
			getLaunchConfigurationDialog().updateMessage();
		}
	}
}
