package com.flow.snesde.core.wizards.importwizards.file.graphic;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.wb.swt.ResourceManager;

import com.flow.snesde.core.wizards.WizardConfiguration;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.ScrolledComposite;

public abstract class ImportGraphicWizardAdvancedPage extends WizardPage implements
		Listener
{
	private final String preselection;

	public String resourceExtension = null;
	private String fileExtension;

	/**
	 * Create the wizard.
	 */
	public ImportGraphicWizardAdvancedPage(String preselection)
	{
		super("wizardPage");
		this.preselection = preselection;
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 *            the parent to which all content of this page is added
	 */
	@Override
	public void createControl(final Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(4, false));
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite_1 = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite_1);
		scrolledComposite.setMinSize(composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblZoomin = new Label(composite, SWT.NONE);
		lblZoomin.setImage(ResourceManager.getPluginImage("com.flow.snesde.core.wizards", "graphics/custom/zoomIn.png"));
		
		Label lblZoomout = new Label(composite, SWT.NONE);
		lblZoomout.setImage(ResourceManager.getPluginImage("com.flow.snesde.core.wizards", "graphics/custom/zoomOut.png"));
		lblZoomout.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		
		Label lblPalettes = new Label(container, SWT.NONE);
		lblPalettes.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblPalettes.setText("Palettes:");
		
		Button btnDither = new Button(container, SWT.CHECK);
		btnDither.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnDither.setText("Dither");
		
		Label lblColorDepth = new Label(container, SWT.NONE);
		lblColorDepth.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		lblColorDepth.setText("Color Depth:");
		
		Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setItems(new String[] {"256", "16", "8", "4", "2"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		List list = new List(container, SWT.BORDER);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 3));
		
		Button btnAdd = new Button(container, SWT.NONE);
		btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAdd.setText("Add");
		
		Button btnRename = new Button(container, SWT.NONE);
		btnRename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRename.setText("Rename");
		
		Button btnRemove = new Button(container, SWT.NONE);
		btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRemove.setText("Remove");

		// validate initial values
		setPageComplete(validatePage());
	}

	/**
	 * used to validate the page contents. All controls which should react on it
	 * must add control.addListener(SWT.Modify, this);
	 */
	@Override
	public void handleEvent(Event event)
	{
		setPageComplete(validatePage());
	}

	/**
	 * Validates the page and returns true if everything is ready for takeoff
	 * (so we can proceed to the next page)
	 * 
	 * @return true if the page content is valid for processing
	 */
	private boolean validatePage()
	{
		// TODO: check some stuff
		if (false)
		{
			setErrorMessage("An error occurred bla...");
			return false;
		}

		setErrorMessage(null);
		return true;
	}
	
	/**
	 * will take the configuration from the wizard and set all values for itself
	 * 
	 * @param config
	 *            the config object
	 */
	public void configure(WizardConfiguration config)
	{
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"com.flow.snesde.core.wizards", config.getWizardIcon()));
		setTitle(config.getWizardTitle());
		setDescription(config.getWizardHint());
		resourceExtension = config.getResourceExtension();
		fileExtension = config.getFileExtension();
	}
}
