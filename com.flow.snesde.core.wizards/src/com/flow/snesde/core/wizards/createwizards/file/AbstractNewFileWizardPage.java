package com.flow.snesde.core.wizards.createwizards.file;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.wb.swt.ResourceManager;

import com.flow.snesde.core.wizards.WizardConfiguration;

/**
 * @author flo
 */
public abstract class AbstractNewFileWizardPage extends WizardPage implements
		Listener
{
	private Text txtFileName;
	protected Text txtLocation;
	private Label lblLocation;
	private Button btnBrowse;
	private final String preselection;

	public String extension = null;
	public String folderName = null;

	/**
	 * Create the wizard.
	 */
	public AbstractNewFileWizardPage(String preselection)
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

		lblLocation = new Label(container, SWT.NONE);
		lblLocation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblLocation.setText("Location:");

		txtLocation = new Text(container, SWT.BORDER);
		txtLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		if (preselection != null)
		{
			txtLocation.setText(preselection);
		}
		txtLocation.addListener(SWT.Modify, this);

		btnBrowse = new Button(container, SWT.NONE);
		btnBrowse.setText("Browse");
		btnBrowse.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				ContainerSelectionDialog dialog = new ContainerSelectionDialog(
						AbstractNewFileWizardPage.this.getShell(), root, false,
						"Select the location to add the file to");
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

		Label lblFilename = new Label(container, SWT.NONE);
		lblFilename.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFilename.setText("Filename:");

		txtFileName = new Text(container, SWT.BORDER);
		txtFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));
		txtFileName.addListener(SWT.Modify, this);

		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3,
				1));

		// add subclass components if any
		addComponents(composite);

		// validate initial page setup
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

		// filename empty?
		if (this.txtFileName == null || txtFileName.getText().equals(""))
		{
			setErrorMessage("Filename must not be empty.");
			return false;
		}

		// check if file with this name already exists
		String fileName = getFilename();
		if (fileAlreadyExists(fileName))
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
	 * checks if the target location we want to add to already exists
	 * 
	 * @return true if so, false otherwise
	 */
	protected boolean targetLocationExists()
	{
		String[] parts = txtLocation.getText().split("/");
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
			String raw = txtLocation.getText();
			String refined = raw.replaceAll("//", "");
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(refined);
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
				path = path + "/" + txtFileName.getText();
			}
			else
			{
				path = path + txtFileName.getText();
			}
		}
		else if (path.contains("\\"))
		{
			if (!path.endsWith("\\"))
			{
				path = path + "\\" + txtFileName.getText();
			}
			else
			{
				path = path + txtFileName.getText();
			}
		}
		if (!path.endsWith(extension))
		{
			path = path + "." + extension;
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
		extension = config.getResourceExtension();
	}

	/**
	 * returns the filename or null if nonsense value
	 * 
	 * @return the filename
	 */
	public String getFilename()
	{
		if (txtFileName != null && !txtFileName.getText().equals(""))
		{
			String fileName = txtFileName.getText();

			// add extension if not present
			if (!fileName.endsWith("." + extension))
			{
				fileName += "." + extension;
			}
			return fileName;
		}
		return null;
	}
}
