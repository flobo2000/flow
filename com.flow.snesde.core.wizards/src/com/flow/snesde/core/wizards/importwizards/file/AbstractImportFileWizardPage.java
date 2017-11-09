package com.flow.snesde.core.wizards.importwizards.file;

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

public abstract class AbstractImportFileWizardPage extends WizardPage implements
		Listener
{
	protected Text txtLocation;
	private Label lblProject;
	private Button btnBrowseProject;
	private final String preselection;

	public String resourceExtension = null;
	private String fileExtension;
	private Label lblFileToImport;
	protected Text txtFileToImport;
	private Button btnBrowseFileToImport;
	private Composite subclassComposite;
	private Text txtNewResourceName;
	private Label lblNewResourceName;

	/**
	 * Create the wizard.
	 */
	public AbstractImportFileWizardPage(String preselection)
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

		lblFileToImport = new Label(container, SWT.NONE);
		lblFileToImport.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblFileToImport.setText("File to import:");

		txtFileToImport = new Text(container, SWT.BORDER);
		txtFileToImport.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		txtFileToImport.addListener(SWT.Modify, this);
		txtFileToImport.addListener(SWT.Modify, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				String selected = txtFileToImport.getText();
				if (selected.endsWith(".bin"))
				{
					selected = selected.substring(0, selected.length() - 4);
				}
				if (selected.contains("/"))
				{
					String[] parts = selected.split("/");
					selected = parts[parts.length - 1];
				}
				if (selected.contains("\\"))
				{
					String[] parts = selected.split("\\\\");
					selected = parts[parts.length - 1];
				}
				if (selected.equals(""))
				{
					txtNewResourceName.setText("");
				}
				else
				{
					txtNewResourceName.setText(selected + "."
							+ resourceExtension);
				}
			}
		});

		btnBrowseFileToImport = new Button(container, SWT.NONE);
		btnBrowseFileToImport.setText("Browse");
		btnBrowseFileToImport.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				FileDialog fd = new FileDialog(getShell(), SWT.SAVE);
				fd.setText("Import from...");
				String[] filterExt = { "*." + fileExtension };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				txtFileToImport.setText(selected);
				if (selected.endsWith(".bin"))
				{
					selected = selected.substring(0, selected.length() - 4);
				}
				if (selected.contains("/"))
				{
					String[] parts = selected.split("/");
					selected = parts[parts.length - 1];
				}
				if (selected.contains("\\"))
				{
					String[] parts = selected.split("\\\\");
					selected = parts[parts.length - 1];
				}
				txtNewResourceName.setText(selected + "." + resourceExtension);
			}
		});

		lblProject = new Label(container, SWT.NONE);
		lblProject.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblProject.setText("Import Location:");

		txtLocation = new Text(container, SWT.BORDER);
		txtLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		if (preselection != null)
		{
			txtLocation.setText(preselection);
		}
		txtLocation.addListener(SWT.Modify, this);

		btnBrowseProject = new Button(container, SWT.NONE);
		btnBrowseProject.setText("Browse");
		btnBrowseProject.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				ContainerSelectionDialog dialog = new ContainerSelectionDialog(
						AbstractImportFileWizardPage.this.getShell(), root,
						false, "Select the location to add the file to");
				// dialog.setInitialSelections(selectedResources);
				if (dialog.open() == Window.OK)
				{
					Object[] result = dialog.getResult();
					if (result != null)
					{
						if (result[0] instanceof Path)
						{
							txtLocation.setText(result[0].toString());
						}
					}
				}
			}
		});

		lblNewResourceName = new Label(container, SWT.NONE);
		lblNewResourceName.setText("New Resource Name:");

		txtNewResourceName = new Text(container, SWT.BORDER);
		txtNewResourceName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 2, 1));
		txtNewResourceName.addListener(SWT.Modify, this);

		subclassComposite = new Composite(container, SWT.NONE);
		GridLayout gl_subclassComposite = new GridLayout(1, false);
		gl_subclassComposite.marginWidth = 0;
		gl_subclassComposite.marginHeight = 0;
		subclassComposite.setLayout(gl_subclassComposite);
		subclassComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 3, 1));

		// add potential components for subclass
		addComponents(subclassComposite);

		// validate initial values
		setPageComplete(validatePage());
	}

	/**
	 * add custom components underneath the project and filename selection stuff
	 * 
	 * @param composite
	 *            the composite to add stuff to
	 */
	public abstract void addComponents(Composite composite);

	/**
	 * returns the target location selected or null if nonsense value
	 * 
	 * @return the target location name
	 */
	public String getTargetLocation()
	{
		if (txtLocation != null && !txtLocation.getText().equals(""))
		{
			return txtLocation.getText();
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
		if (this.txtLocation == null || txtLocation.getText().equals(""))
		{
			setErrorMessage("Target location must not be empty.");
			return false;
		}
		// test target project/folder existance
		if (!targetLocationExists())
		{
			setErrorMessage("The target location selected doesn't exist.");
			return false;
		}

		// check if file with this name already exists
		String fileName = txtFileToImport.getText();
		File f = new File(fileName);
		if (!f.exists() || f.isDirectory())
		{
			setErrorMessage("The file you want to import is invalid.");
			return false;
		}

		String importedResourceName = txtNewResourceName.getText();
		if (importedResourceName == null || importedResourceName.equals(""))
		{
			setErrorMessage("The imported resource needs to have a name");
			return false;
		}
		else if (fileAlreadyExists(importedResourceName))
		{
			setErrorMessage("A resource with the name selected already exists.");
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
	 * checks if the target location we want to add to already exists
	 * 
	 * @return true if so, false otherwise
	 */
	protected boolean targetLocationExists()
	{
		String[] parts = txtLocation.getText().split("/");
		String all = txtLocation.getText();
		int numberOfParts = 0;
		for (String s : parts)
		{
			if (!s.equals(""))
			{
				numberOfParts++;
			}
		}
		if (numberOfParts > 1)
		{
			Path path = new Path(txtLocation.getText());
			IFolder folder = ResourcesPlugin.getWorkspace().getRoot()
					.getFolder(path);
			if (folder.exists())
			{
				return true;
			}
		}
		else
		{
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(txtLocation.getText().replaceAll("//", ""));
			if (project.exists())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * tests if the file we want to create within the selected project/directory
	 * already exists
	 * 
	 * @param fileName
	 *            the new filename
	 * @return true if so, false otherwise
	 */
	private boolean fileAlreadyExists(String fileName)
	{
		String path = getCompleteTargetLocationString();
		IPath p = new Path(path);
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
		if (f.exists())
		{
			return true;
		}
		return false;
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
	 * Assembles the complete target location
	 * (project+subfolders+filename+extension
	 * 
	 * @return the assembled path
	 */
	public String getCompleteTargetLocationString()
	{
		String path = txtLocation.getText();
		if (path.contains("/"))
		{
			if (!path.endsWith("/"))
			{
				path = path + "/" + txtNewResourceName.getText();
			}
			else
			{
				path = path + txtNewResourceName.getText();
			}
		}
		else if (path.contains("\\"))
		{
			if (!path.endsWith("\\"))
			{
				path = path + "\\" + txtNewResourceName.getText();
			}
			else
			{
				path = path + txtNewResourceName.getText();
			}
		}
		if (!path.endsWith(resourceExtension))
		{
			path = path + "." + resourceExtension;
		}
		return path;
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

	/**
	 * checks if the project we want to add to already exists
	 * 
	 * @return true if so, false otherwise
	 */
	protected boolean projectExists()
	{
		String[] parts = txtLocation.getText().split("/");
		int numberOfParts = 0;
		for (String s : parts)
		{
			// count number of nonempty parts of the path
			if (!s.equals(""))
			{
				numberOfParts++;
			}
		}
		// multiple parts -> folder. We need to get the project from that folder
		// and check if for existance
		if (numberOfParts > 1)
		{
			Path path = new Path(txtLocation.getText());
			IFolder folder = ResourcesPlugin.getWorkspace().getRoot()
					.getFolder(path);
			if (folder.exists())
			{
				if (folder.getProject().exists())
				{
					return true;
				}
			}
		}
		// only one single part -> project. Check for existance and return
		else
		{
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(txtLocation.getText().replaceAll("//", ""));
			if (project.exists())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * returns the file to be imported
	 * 
	 * @return the file to be imported
	 */
	public String getFileToImport()
	{
		if (txtFileToImport != null)
		{
			return txtFileToImport.getText();
		}
		return "";
	}

	/**
	 * returns the resource name selected
	 * 
	 * @return the new resource name
	 */
	public String getResourcenameToBeCreated()
	{
		if (txtNewResourceName != null)
		{
			return txtNewResourceName.getText();
		}
		return "";
	}

	/**
	 * returns the project currently selected
	 * 
	 * @return the project or null if no proper value there
	 */
	public IProject getProject()
	{
		String[] parts = txtLocation.getText().split("/");
		int numberOfParts = 0;
		for (String s : parts)
		{
			// count number of nonempty parts of the path
			if (!s.equals(""))
			{
				numberOfParts++;
			}
		}
		// multiple parts -> folder. We need to get the project from that folder
		// and check if for existance
		if (numberOfParts > 1)
		{
			Path path = new Path(txtLocation.getText());
			IFolder folder = ResourcesPlugin.getWorkspace().getRoot()
					.getFolder(path);
			if (folder.exists())
			{
				return folder.getProject();
			}
		}
		// only one single part -> project. Check for existance and return
		else
		{
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(txtLocation.getText().replaceAll("//", ""));
			return project;
		}
		return null;
	}
}
