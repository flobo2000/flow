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

/**
 * @author flo
 * 
 */
public class SnesLauncherAdvancedTab extends AbstractLaunchConfigurationTab
{

	private SnesLauncherMainTabComposite composite;

	@Override
	public void createControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite = new SnesLauncherMainTabComposite(parent, SWT.NONE);
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
		return "Advanced";
	}
	
	@Override
	public Image getImage() {
		String path = "graphics/window.png";
		Bundle bundle = Platform.getBundle("com.flow.snesde.launchers");
		URL url = FileLocator.find(bundle, new Path(path), null);
		ImageDescriptor imageDesc = ImageDescriptor.createFromURL(url);
		Image image = imageDesc.createImage();
		return image;
	}
	
	@Override
	public boolean isValid(ILaunchConfiguration launchConfig)
	{
//		try {
//			if (launchConfig.getAttribute("projectName", "").equals(""))
//			{
//				return false;
//			}
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return true;
	}
	
	@Override
	public boolean canSave() 
	{
		return true;
	}
	
	/**
	 * the actual composite containing the content of the Tab
	 * 
	 * @author flo
	 *
	 */
	private class SnesLauncherMainTabComposite extends Composite {
		
		private Composite myparent;
		

		/**
		 * Standad constructor
		 * 
		 * @param parent
		 * @param style
		 */
		public SnesLauncherMainTabComposite(Composite parent, int style) {
			super(parent, style);
			this.myparent = parent;
			//create UI
			createContent();
			//init with preferences
			initUi();
			//add listeners
			addListeners();
		}

		private void addListeners() {
			//TODO: add content here
		}

		private void initUi() {
			//TODO: init UI
		}

		private void createContent() {
			//TODO: add SWT widgets here
		}

		/**
		 * Stores the dialogs values in the configuration object
		 * 
		 * @param configuration the configuration to store into
		 */
		protected void apply(ILaunchConfigurationWorkingCopy configuration) {
			//TODO: apply changes
		}

		/**
		 * Initializes the UI and the config object (this is called for NEW launch configurations)
		 * 
		 * @param configuration the config
		 */
		protected void setDefaults(ILaunchConfigurationWorkingCopy configuration) 
		{
			//TODO: apply defaults
		}

		/**
		 * Initializes the UI from the config object (this is called for EXISTING launch configs)
		 * 
		 * @param configuration the immutible config object
		 */
		public void initializeFrom(ILaunchConfiguration configuration) {
			//TODO: initialize from configuration
		}
	}
}
