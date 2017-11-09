package com.flow.snesde.core.wizards.importwizards.file.tileset;

import java.io.File;
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.flow.snesde.core.model.objects.PaletteObject;
import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.core.wizards.importwizards.file.AbstractImportFileWizardPage;

public class ImportTilesetWizardPage extends AbstractImportFileWizardPage
{
	public static final int ENCODING_2colors_1bpp = 2;
	public static final int ENCODING_4colors_2bpp = 4;
	public static final int ENCODING_8colors_3bpp = 8;
	public static final int ENCODING_16colors_4bpp = 16;
	public static final int ENCODING_256colors_8bpp = 256;

	private Text txtNumberOfColumns;
	private Combo cmbPreferredPalette;
	private Combo cmbEncoding;

	public ImportTilesetWizardPage(String preselection)
	{
		super(preselection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addComponents(Composite composite)
	{

		Group grpTilesetConfiguration = new Group(composite, SWT.NONE);
		grpTilesetConfiguration.setLayout(new GridLayout(2, false));
		grpTilesetConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));
		grpTilesetConfiguration.setText("Tileset Configuration");

		Label lblEncoding = new Label(grpTilesetConfiguration, SWT.NONE);
		lblEncoding.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblEncoding.setText("Encoding:");

		cmbEncoding = new Combo(grpTilesetConfiguration, SWT.READ_ONLY);
		cmbEncoding.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbEncoding.addListener(SWT.Modify, this);

		Label lblPreferredPalette = new Label(grpTilesetConfiguration, SWT.NONE);
		lblPreferredPalette.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblPreferredPalette.setText("Preferred Palette:");

		cmbPreferredPalette = new Combo(grpTilesetConfiguration, SWT.READ_ONLY);
		cmbPreferredPalette.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Label lblNumberOfColumns = new Label(grpTilesetConfiguration, SWT.NONE);
		lblNumberOfColumns.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblNumberOfColumns.setText("Number of Columns:");

		txtNumberOfColumns = new Text(grpTilesetConfiguration, SWT.BORDER);
		txtNumberOfColumns.setText("16");
		txtNumberOfColumns.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		txtNumberOfColumns.addListener(SWT.Modify, this);

		// add listeners
		addListeners();

		// if selection for project was predefined -> show available color
		// palettes
		if (txtLocation != null && !txtLocation.getText().equals(""))
		{
			updateAvailablePalettesCombo();
		}
	}

	/**
	 * Adds listeners
	 */
	private void addListeners()
	{
		// update color depth on filename change
		txtFileToImport.addListener(SWT.Modify, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				updateEncodingCombo();
				updateAvailablePalettesCombo();
			}
		});

		// update color palette combo when project selection is changed so we
		// have the list of available color palettes for that project
		txtLocation.addListener(SWT.Modify, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				updateAvailablePalettesCombo();
			}
		});

		cmbEncoding.addListener(SWT.Modify, new Listener()
		{
			@Override
			public void handleEvent(Event event)
			{
				updateAvailablePalettesCombo();
			}
		});

		cmbEncoding.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				updateAvailablePalettesCombo();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * checks the file selected in txtFileToImport and enables/disables options
	 * in the encoding combo
	 */
	protected void updateEncodingCombo()
	{
		if (txtFileToImport != null)
		{
			// temporarily deactivate the modify listener from the encoding
			// combo so we can update it without getting into an infinite loop
			Listener[] listeners = cmbEncoding.getListeners(SWT.Modify);
			for (Listener l : listeners)
			{
				cmbEncoding.removeListener(SWT.Modify, l);
			}
			// get filesize to calculate which encodings would be valid
			File f = new File(txtFileToImport.getText());
			if (f.exists())
			{
				long size = f.length();

				// update encoding combo
				cmbEncoding.removeAll();
				if (size % 64 == 0)
				{
					// TODO: enable once we know how the format works
					// cmbEncoding.add("256 colors / Mode7");
				}
				if (size % 32 == 0)
				{
					cmbEncoding.add("16 colors (4bpp)");
				}
				if (size % 24 == 0)
				{
					cmbEncoding.add("8 colors (3bpp)");
				}
				if (size % 16 == 0)
				{
					cmbEncoding.add("4 colors (2bpp)");
				}
				if (size % 8 == 0)
				{
					cmbEncoding.add("2 colors (1bpp)");
				}
				if (cmbEncoding.getItemCount() == 0)
				{
					cmbEncoding.setEnabled(false);
					return;
				}
				else
				{
					cmbEncoding.setEnabled(true);
					cmbEncoding.setText(cmbEncoding.getItem(0));
					return;
				}
			}
		}
		cmbEncoding.removeAll();
		cmbEncoding.setEnabled(false);
		return;
	}

	/**
	 * Triggered when project selection changed -> so the user sees all
	 * available palettes for that project
	 */
	protected void updateAvailablePalettesCombo()
	{
		int numberOfColors = getEncoding();
		// in case a valid project was selected, update and enable the
		// combo with the palettes selectable
		if (txtLocation != null && !txtLocation.getText().equals("")
				&& projectExists())
		{
			IProject project = getProject();
			try
			{
				SnesdeProjectRoot projectRoot = FlowWorkspace
						.getProjectRoot(project);
				HashSet<PaletteObject> palettesForColorDepth = projectRoot
						.getPalettesForColorDepth(numberOfColors);
				if (palettesForColorDepth.isEmpty())
				{
					cmbPreferredPalette.removeAll();
					cmbPreferredPalette.setEnabled(false);
				}
				else
				{
					cmbPreferredPalette.removeAll();
					String lastResource = "";
					for (PaletteObject m : palettesForColorDepth)
					{
						cmbPreferredPalette.add(m.getName() + " ("
								+ m.getIdentifier() + ")");
						cmbPreferredPalette.setEnabled(true);
						lastResource = m.getName() + " (" + m.getIdentifier()
								+ ")";
					}
					cmbPreferredPalette.setText(lastResource);
					return;
				}
			}
			catch (CoreException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cmbPreferredPalette.removeAll();
		cmbPreferredPalette.setEnabled(false);
	}

	@Override
	public String getAnyErrors()
	{
		// check combo for enablement
		if (cmbEncoding != null && cmbEncoding.getItemCount() == 0)
		{
			return "The file you want to import seems to have a non-standard size. Please choose a file which contains a multiple of 8, 16, 24, 32 or 64 bytes.";
		}

		// make sure that column is either not filled or a numeric positive
		if (txtNumberOfColumns != null
				&& !txtNumberOfColumns.getText().equals(""))
		{
			if (!isInteger(txtNumberOfColumns.getText()))
			{
				return "The value for columns must be a numeric positive.";
			}
		}
		return null;

	}

	/**
	 * Tests if the string passed is a numeric positive
	 * 
	 * @param str
	 *            the string to check
	 * @return true if numeric positive, false otherwise
	 */
	private boolean isInteger(String str)
	{
		if (str == null)
		{
			return false;
		}
		int length = str.length();
		if (length == 0)
		{
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-')
		{
			if (length == 1)
			{
				return false;
			}
			i = 1;
		}
		for (; i < length; i++)
		{
			char c = str.charAt(i);
			if (c < '0' || c > '9')
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * this method will return the selected encoding in form of integer
	 * constants. How to interpret these: see
	 * ImportTilesetWizardPage.ENCODING_xxx
	 * 
	 * @return the encoding selection
	 */
	public int getEncoding()
	{
		String s = cmbEncoding.getText();
		if (cmbEncoding.getText().equals("256 colors / Mode7"))
		{
			return ENCODING_256colors_8bpp;
		}
		if (cmbEncoding.getText().equals("16 colors (4bpp)"))
		{
			return ENCODING_16colors_4bpp;
		}
		if (cmbEncoding.getText().equals("8 colors (3bpp)"))
		{
			return ENCODING_8colors_3bpp;
		}
		if (cmbEncoding.getText().equals("4 colors (2bpp)"))
		{
			return ENCODING_4colors_2bpp;
		}
		if (cmbEncoding.getText().equals("2 colors (1bpp)"))
		{
			return ENCODING_2colors_1bpp;
		}
		return 0;
	}

	public String getPalette()
	{
		return cmbPreferredPalette.getText();
	}

	public int getColumns()
	{
		return Integer.parseInt(txtNumberOfColumns.getText());
	}

}
