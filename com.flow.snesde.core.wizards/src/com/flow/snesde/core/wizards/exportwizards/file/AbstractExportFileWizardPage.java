package com.flow.snesde.core.wizards.exportwizards.file;

import java.io.File;
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.wb.swt.ResourceManager;

import com.flow.snesde.core.model.objects.AbstractModelObject;
import com.flow.snesde.core.wizards.WizardConfiguration;

public abstract class AbstractExportFileWizardPage extends WizardPage implements
		Listener
{
	protected Text txtProject;
	private Label lblProject;
	private Button btnBrowseProject;
	private final String preselection;

	public String resourceExtension = null;
	private String fileExtension;
	private Combo cmbAvailableResources;
	private Label lblExportTo;
	private Text txtExportto;
	private Button btnBrowseExportTo;
	private Label label;
	private Composite subclassComposite;

	/**
	 * Create the wizard.
	 */
	public AbstractExportFileWizardPage(String preselection)
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
		container.setLayout(new GridLayout(3, false));

		lblProject = new Label(container, SWT.NONE);
		lblProject.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblProject.setText("Location:");

		txtProject = new Text(container, SWT.BORDER);
		txtProject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		if (preselection != null)
		{
			txtProject.setText(preselection);
		}
		txtProject.addListener(SWT.Modify, this);

		btnBrowseProject = new Button(container, SWT.NONE);
		btnBrowseProject.setText("Browse");
		btnBrowseProject.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

				ContainerSelectionDialog dialog = new ContainerSelectionDialog(
						AbstractExportFileWizardPage.this.getShell(), root,
						false, "Select the project to add the file to");
				// dialog.setInitialSelections(selectedResources);
				if (dialog.open() == Window.OK)
				{
					Object[] result = dialog.getResult();
					if (result != null)
					{
						if (result[0] instanceof Path)
						{
							String projectName = (((Path) result[0]).toString())
									.substring(1);
							if (projectName.contains("/"))
							{
								projectName = projectName.split("/")[0];
							}
							txtProject.setText(projectName);
							updateCombo();
						}
					}
				}
			}
		});

		Label lblFilename = new Label(container, SWT.NONE);
		lblFilename.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFilename.setText("File to export:");

		cmbAvailableResources = new Combo(container, SWT.READ_ONLY);
		cmbAvailableResources.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 2, 1));

		label = new Label(container, SWT.NONE);
		label.setText(" ");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		lblExportTo = new Label(container, SWT.NONE);
		lblExportTo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblExportTo.setText("Export to:");

		txtExportto = new Text(container, SWT.BORDER);
		txtExportto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		txtExportto.addListener(SWT.Modify, this);

		btnBrowseExportTo = new Button(container, SWT.NONE);
		btnBrowseExportTo.setText("Browse");

		subclassComposite = new Composite(container, SWT.NONE);
		GridLayout gl_subclassComposite = new GridLayout(1, false);
		gl_subclassComposite.marginWidth = 0;
		gl_subclassComposite.marginHeight = 0;
		subclassComposite.setLayout(gl_subclassComposite);
		subclassComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 3, 1));

		if (preselection != null)
		{
			updateCombo();
		}
		// add listeners
		addListeners();

		// add potential components for subclass
		addComponents(subclassComposite);

		// validate initial values
		setPageComplete(validatePage());
	}

	/**
	 * adds listeners.. what else
	 */
	private void addListeners()
	{

		// update all wordspace resources in dropdown upon project selection
		// change
		txtProject.addListener(SWT.Modify, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				updateCombo();
			}
		});

		// browse for file export location
		btnBrowseExportTo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				FileDialog fd = new FileDialog(getShell(), SWT.SAVE);
				fd.setText("Export to...");
				// fd.setFilterPath("C:/");
				String[] filterExt = { "*." + fileExtension };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				txtExportto.setText(selected);
			}
		});
	}

	/**
	 * Updates the combo with the resources selectable in the current project
	 */
	protected void updateCombo()
	{
		HashSet<AbstractModelObject> modelObjects = new HashSet<AbstractModelObject>();
		// in case a valid project was selected, update and enable the
		// combo for the file to export
		if (txtProject != null && !txtProject.getText().equals("")
				&& projectExists())
		{
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(txtProject.getText());
			// TODO: collect all relevant model objects from the project
			modelObjects = getAllRelevantModelObjectsFromProject(project);
		}
		if (modelObjects.isEmpty())
		{
			cmbAvailableResources.removeAll();
			cmbAvailableResources.setEnabled(false);
		}
		else
		{
			cmbAvailableResources.removeAll();
			String lastResource = "";
			for (AbstractModelObject m : modelObjects)
			{
				cmbAvailableResources.add(m.getName() + " ("
						+ m.getIdentifier() + ")");
				cmbAvailableResources.setEnabled(true);
				lastResource = m.getName() + " (" + m.getIdentifier() + ")";
			}
			cmbAvailableResources.setText(lastResource);
		}
	}

	/**
	 * This method should be overwritten by any subclasses. The passed project
	 * should be combined with the wizards configuration data to obtain a list
	 * of modelobjects extending AbstractModelObject which is returned. The
	 * result will be that the combo of the wizard will contain all object names
	 * for the set
	 * 
	 * @param project
	 *            the project to look for model objects
	 * @return the HashSet containing the modelobjects
	 */
	public abstract HashSet<AbstractModelObject> getAllRelevantModelObjectsFromProject(
			IProject project);

	/**
	 * add custom components underneath the project and filename selection stuff
	 * 
	 * @param composite
	 *            the composite to add stuff to
	 */
	public abstract void addComponents(Composite composite);

	/**
	 * returns the project selected or null if nonsense value
	 * 
	 * @return the project name
	 */
	public String getProject()
	{
		if (txtProject != null && !txtProject.getText().equals(""))
		{
			return txtProject.getText();
		}
		return null;
	}

	/**
	 * returns the name of the resource to export
	 * 
	 * @return the resources name
	 */
	public String getResourceToExport()
	{
		if (cmbAvailableResources != null)
		{
			String res = cmbAvailableResources.getText();
			String[] parts = res.split("\\(");
			parts = parts[1].split("\\)");
			return parts[0];
		}
		return null;
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
		// project name empty?
		if (this.txtProject == null || txtProject.getText().equals(""))
		{
			setErrorMessage("Project must not be empty.");
			return false;
		}
		// test project existance
		if (!projectExists())
		{
			setErrorMessage("The project selected doesn't exist.");
			return false;
		}

		// filename empty?
		if (this.cmbAvailableResources == null
				|| cmbAvailableResources.getText().equals(""))
		{
			setErrorMessage("File to export must not be empty.");
			return false;
		}

		if (txtExportto == null || txtExportto.getText().equals(""))
		{
			setErrorMessage("Exported file path must not be empty.");
			return false;
		}

		// check if file with this name already exists
		String fileName = txtExportto.getText();
		File f = new File(fileName);
		if (f.exists() && !f.isDirectory())
		{
			setErrorMessage("There's already a file with the given name.");
			return false;
		}

		// to handle subclass error messages
		String error = getAnyErrors();
		if (error != null)
		{
			setErrorMessage(error);
			return false;
		}
		else
		{
			setErrorMessage(null);
			return true;
		}
	}

	/**
	 * should be overwritten by subclasses so the can test their own content for
	 * errors. if there was an error, the method should return the error
	 * message. otherwise null. If a widget should trigger validation, it needs
	 * to be assigned the modifylistener which is this page itself. Should be
	 * done like this: myWidget.addListener(SWT.Modify, this);
	 * 
	 * @return the error message, null if no errors occurred
	 */
	public abstract String getAnyErrors();

	/**
	 * checks if the project we want to add to already exists
	 * 
	 * @return true if so, false otherwise
	 */
	protected boolean projectExists()
	{
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(txtProject.getText());
		return project.exists();
	}

	/**
	 * Returns the export location of the files
	 * 
	 * @return the location to export to as a string
	 */
	public String getExportLocation()
	{
		if (txtExportto != null)
		{
			return this.txtExportto.getText();
		}
		return "";
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
