package com.flow.snesde.launchers.sneslauncher.tabs.composites;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import com.flow.snesde.launchers.sneslauncher.tabs.SnesLauncherMainTab;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainTabComposite extends Composite {


	private Text txtAsmPath;
	private Text txtLinkPath;
	private Text txtEmuPath;
	private Button btnAsmBrowse;
	private Button btnAsmDefault;
	private Button btnLinkBrowse;
	private Button btnLinkDefault;
	private Button btnEmuBrowse;
	private Button btnEmuDefault;
	private Composite myparent;
	private Label lblProject;
	private Text txtProjectName;
	private Button btnProjectBrowse;
	private SnesLauncherMainTab tab;
	private Composite composite;
	
	/**
	 * Standad constructor
	 * 
	 * @param parent
	 * @param style
	 */
	public MainTabComposite(Composite parent, int style) {
		super(parent, style);
		this.myparent = parent;
		//create UI
		createContent();
		//init with preferences
		initUi();
		//add listeners
		addListeners();
	}

	public void setTab(SnesLauncherMainTab snesLauncherMainTab) {
		this.tab = snesLauncherMainTab;
	}

	private void addListeners() {
		ModifyListener textChangeListener = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				tab.doUpdateLaunchConfigurationDialog();
			}
		};
		txtProjectName.addModifyListener(textChangeListener);
		txtAsmPath.addModifyListener(textChangeListener);
		txtLinkPath.addModifyListener(textChangeListener);
		txtEmuPath.addModifyListener(textChangeListener);
		
		
		btnAsmDefault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (btnAsmDefault.getSelection())
				{
					String asmpath = Platform.getPreferencesService().
							getString("com.flow.snesde.core", "ASMPATH", "", null);
					txtAsmPath.setText(asmpath);
					txtAsmPath.setEnabled(false);
					btnAsmBrowse.setEnabled(false);
				}
				else
				{
					txtAsmPath.setEnabled(true);
					btnAsmBrowse.setEnabled(true);
				}
			}
		});
		btnLinkDefault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (btnLinkDefault.getSelection())
				{
					String linkpath = Platform.getPreferencesService().
							getString("com.flow.snesde.core", "LINKPATH", "", null);
					txtLinkPath.setText(linkpath);
					txtLinkPath.setEnabled(false);
					btnLinkBrowse.setEnabled(false);
				}
				else
				{
					txtLinkPath.setEnabled(true);
					btnLinkBrowse.setEnabled(true);
				}
			}
		});
		btnEmuDefault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (btnEmuDefault.getSelection())
				{
					String emupath = Platform.getPreferencesService().
							getString("com.flow.snesde.core", "EMUPATH", "", null);
					txtEmuPath.setText(emupath);
					txtEmuPath.setEnabled(false);
					btnEmuBrowse.setEnabled(false);
				}
				else
				{
					txtEmuPath.setEnabled(true);
					btnEmuBrowse.setEnabled(true);
				}
			}
		});
		
		btnProjectBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				ContainerSelectionDialog dialog = new ContainerSelectionDialog(myparent.getShell(), root, false, "Select the project to build");
				//dialog.setInitialSelections(selectedResources);
				if (dialog.open() == Window.OK) {
					Object[] result = dialog.getResult();
					if (result[0] != null && result[0] instanceof Path)
					{
						String projectName = (((Path)result[0]).toString()).substring(1);
						if (projectName.contains("/"))
						{
							projectName = projectName.split("/")[0];
						}
						txtProjectName.setText(projectName);						
					}
			    }
			}
		});
		btnAsmBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				FileDialog dialog = new FileDialog(myparent.getShell(), SWT.OPEN);
				txtAsmPath.setText(dialog.open());
			}
		});
		btnLinkBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				FileDialog dialog = new FileDialog(myparent.getShell(), SWT.OPEN);
				txtLinkPath.setText(dialog.open());
			}
		});
		btnEmuBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				FileDialog dialog = new FileDialog(myparent.getShell(), SWT.OPEN);
				txtEmuPath.setText(dialog.open());
			}
		});
	}

	private void initUi() {
		//get the preferences from the preference page
		String asmpath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "ASMPATH", "", null);
		String linkerpath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "LINKPATH", "", null);
		String emupath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "EMUPATH", "", null);
		txtAsmPath.setText(asmpath);
		txtLinkPath.setText(linkerpath);
		txtEmuPath.setText(emupath);
	}
	
	private void createContent() {
		setLayout(new GridLayout(3, false));
		
		lblProject = new Label(this, SWT.NONE);
		lblProject.setText("Project: ");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		txtProjectName = new Text(this, SWT.BORDER);
		txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		btnProjectBrowse = new Button(this, SWT.NONE);
		btnProjectBrowse.setText("Browse");
		
		Label lblAssembler = new Label(this, SWT.NONE);
		lblAssembler.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		lblAssembler.setText("Assembler: ");
		
		btnAsmDefault = new Button(this, SWT.CHECK);
		btnAsmDefault.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnAsmDefault.setSelection(true);
		btnAsmDefault.setText("use default");
		new Label(this, SWT.NONE);
		
		txtAsmPath = new Text(this, SWT.BORDER);
		txtAsmPath.setEnabled(false);
		txtAsmPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		btnAsmBrowse = new Button(this, SWT.NONE);
		btnAsmBrowse.setEnabled(false);
		btnAsmBrowse.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnAsmBrowse.setText("Browse");
		
		Label lblLinker = new Label(this, SWT.NONE);
		lblLinker.setText("Linker: ");
		
		btnLinkDefault = new Button(this, SWT.CHECK);
		btnLinkDefault.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLinkDefault.setSelection(true);
		btnLinkDefault.setText("use default");
		new Label(this, SWT.NONE);
		
		txtLinkPath = new Text(this, SWT.BORDER);
		txtLinkPath.setEnabled(false);
		txtLinkPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		btnLinkBrowse = new Button(this, SWT.NONE);
		btnLinkBrowse.setEnabled(false);
		btnLinkBrowse.setText("Browse");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Emulator: ");
		
		btnEmuDefault = new Button(this, SWT.CHECK);
		btnEmuDefault.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnEmuDefault.setSelection(true);
		btnEmuDefault.setText("use default");
		new Label(this, SWT.NONE);
		
		txtEmuPath = new Text(this, SWT.BORDER);
		txtEmuPath.setEnabled(false);
		txtEmuPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		btnEmuBrowse = new Button(this, SWT.NONE);
		btnEmuBrowse.setEnabled(false);
		btnEmuBrowse.setText("Browse");
		
		composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		Button btnUseInternalAssembler = new Button(composite, SWT.CHECK);
		btnUseInternalAssembler.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnUseInternalAssembler.setText("Use Internal Assembler (beta)");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
	}

	/**
	 * Stores the dialogs values in the configuration object
	 * 
	 * @param configuration the configuration to store into
	 */
	public void apply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute("projectName", txtProjectName.getText());
		configuration.setAttribute("ASMPATH", txtAsmPath.getText());
		configuration.setAttribute("LINKPATH", txtLinkPath.getText());
		configuration.setAttribute("EMUPATH", txtEmuPath.getText());
	}

	/**
	 * Initializes the UI and the config object (this is called for NEW launch configurations)
	 * 
	 * @param configuration the config
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) 
	{
		//get the preferences from the preference page
		String asmpath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "ASMPATH", "", null);
		String linkerpath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "LINKPATH", "", null);
		String emupath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "EMUPATH", "", null);
		txtAsmPath.setText(asmpath);
		txtLinkPath.setText(linkerpath);
		txtEmuPath.setText(emupath);
		txtAsmPath.setEnabled(false);
		txtLinkPath.setEnabled(false);
		txtEmuPath.setEnabled(false);
		btnAsmBrowse.setEnabled(false);
		btnLinkBrowse.setEnabled(false);
		btnEmuBrowse.setEnabled(false);
		//store values to the config object
		apply(configuration);
	}

	/**
	 * Initializes the UI from the config object (this is called for EXISTING launch configs)
	 * 
	 * @param configuration the immutible config object
	 */
	public void initializeFrom(ILaunchConfiguration configuration) {
		//get the preferences from the preference page
		String projectName = "";
		String asmpath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "ASMPATH", "", null);
		String linkerpath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "LINKPATH", "", null);
		String emupath = Platform.getPreferencesService().
				getString("com.flow.snesde.core", "EMUPATH", "", null);
		try {
			projectName = configuration.getAttribute("projectName", projectName);
			asmpath = configuration.getAttribute("ASMPATH", asmpath);
			linkerpath = configuration.getAttribute("LINKPATH", linkerpath);
			emupath = configuration.getAttribute("EMUPATH", emupath);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtProjectName.setText(projectName);
		txtAsmPath.setText(asmpath);
		txtLinkPath.setText(linkerpath);
		txtEmuPath.setText(emupath);
		
		//set default value checkboxes and disable buttons if needed
		if (asmpath.equals(Platform.getPreferencesService().getString("com.flow.snesde.core", "ASMPATH", "", null)))
		{
			btnAsmDefault.setSelection(true);
			btnAsmBrowse.setEnabled(false);
			txtAsmPath.setEnabled(false);
		}
		else
		{
			btnAsmDefault.setSelection(false);
			btnAsmBrowse.setEnabled(true);
			txtAsmPath.setEnabled(true);
		}
		if (linkerpath.equals(Platform.getPreferencesService().getString("com.flow.snesde.core", "LINKPATH", "", null)))
		{
			btnLinkDefault.setSelection(true);
			btnLinkBrowse.setEnabled(false);
			txtLinkPath.setEnabled(false);
		}
		else
		{
			btnLinkDefault.setSelection(false);
			btnLinkBrowse.setEnabled(true);
			txtLinkPath.setEnabled(true);
		}
		if (emupath.equals(Platform.getPreferencesService().getString("com.flow.snesde.core", "EMUPATH", "", null)))
		{
			btnEmuDefault.setSelection(true);
			btnEmuBrowse.setEnabled(false);
			txtEmuPath.setEnabled(false);
		}
		else
		{
			btnEmuDefault.setSelection(false);
			btnEmuBrowse.setEnabled(true);
			txtEmuPath.setEnabled(true);
		}
	}

	public String getProjectName() {
		if (txtProjectName != null)
		{
			return txtProjectName.getText();
		}
		return "";
	}
}
