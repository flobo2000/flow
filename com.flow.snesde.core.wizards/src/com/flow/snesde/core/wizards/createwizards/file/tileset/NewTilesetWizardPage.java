package com.flow.snesde.core.wizards.createwizards.file.tileset;

import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import com.flow.snesde.core.model.objects.PaletteObject;
import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.core.wizards.createwizards.file.AbstractNewFileWizardPage;

/**
 * package com.flow.snesde.core.wizards.create.file.palette;
 * 
 * import org.eclipse.core.resources.IFile;
 * 
 * /**
 * 
 * @author flo
 */
public class NewTilesetWizardPage extends AbstractNewFileWizardPage
{
	public static final int CONTENT_RANDOM = 0;
	public static final int CONTENT_SINGLECOLOR = 1;

	public static final int ENCODING_2COLORS_1BPP = 2;
	public static final int ENCODING_4COLORS_2BPP = 4;
	public static final int ENCODING_8COLORS_3BPP = 8;
	public static final int ENCODING_16COLORS_4BPP = 16;
	public static final int ENCODING_256COLORS_8BPP = 256;

	private Combo cmbEncoding;
	protected boolean reactOnRowSpinnerChanges;
	protected boolean reactOnColumnSpinnerChanges;
	protected boolean reactOnLastRowSpinnerChanges;
	private Text txtRows;
	private Text txtColumns;
	private Text txtAdditionaltiles;
	private Button btnRandomColors;
	private Button btnSingleColor;
	private Combo cmbPalettes;

	/**
	 * constructor
	 * 
	 * @param preselection
	 */
	public NewTilesetWizardPage(String preselection)
	{
		super(preselection);
	}

	@Override
	public void addComponents(Composite composite)
	{
		Label lblEncoding = new Label(composite, SWT.NONE);
		lblEncoding.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblEncoding.setText("Encoding:");

		cmbEncoding = new Combo(composite, SWT.READ_ONLY);
		cmbEncoding.setItems(new String[] { "2 Colors (1bpp)",
				"4 Colors (2bpp)", "8 Colors (3bpp)", "16 Colors (4bpp)",
				"256 Colors (8bpp)" });
		cmbEncoding.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbEncoding.select(3);

		Group grpDimensions = new Group(composite, SWT.NONE);
		grpDimensions.setLayout(new GridLayout(3, false));
		grpDimensions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 3, 1));
		grpDimensions.setText("Dimensions");

		Composite composite_1 = new Composite(grpDimensions, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 3));

		Label lblRows = new Label(composite_1, SWT.NONE);
		lblRows.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true,
				1, 1));
		lblRows.setText("Rows:");

		txtRows = new Text(composite_1, SWT.BORDER);
		txtRows.setText("16");
		GridData gd_txtRows = new GridData(SWT.RIGHT, SWT.BOTTOM, false, false,
				1, 1);
		gd_txtRows.widthHint = 50;
		gd_txtRows.minimumWidth = 50;
		txtRows.setLayoutData(gd_txtRows);
		txtRows.addListener(SWT.Modify, this);
		new Label(grpDimensions, SWT.NONE);

		Composite composite_2 = new Composite(grpDimensions, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 2));
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);

		Label lblColumns = new Label(composite_2, SWT.NONE);
		lblColumns.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblColumns.setText("Columns:");

		txtColumns = new Text(composite_2, SWT.BORDER);
		GridData gd_txtColumns = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_txtColumns.minimumWidth = 50;
		gd_txtColumns.widthHint = 50;
		txtColumns.setLayoutData(gd_txtColumns);
		txtColumns.setText("16");
		txtColumns.addListener(SWT.Modify, this);

		Label lblImage = new Label(grpDimensions, SWT.NONE);
		lblImage.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 4));
		lblImage.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.core.wizards",
				"graphics/custom/tilesetCreation.png"));
		new Label(grpDimensions, SWT.NONE);

		Label lblNix = new Label(grpDimensions, SWT.NONE);

		Label lblAdditionalTiles = new Label(grpDimensions, SWT.NONE);
		lblAdditionalTiles.setText("Additional Tiles:");
		new Label(grpDimensions, SWT.NONE);

		txtAdditionaltiles = new Text(grpDimensions, SWT.BORDER);
		txtAdditionaltiles.setText("0");
		GridData gd_txtAdditionaltiles = new GridData(SWT.LEFT, SWT.BOTTOM,
				true, false, 1, 1);
		gd_txtAdditionaltiles.widthHint = 50;
		gd_txtAdditionaltiles.minimumWidth = 50;
		txtAdditionaltiles.setLayoutData(gd_txtAdditionaltiles);
		txtAdditionaltiles.addListener(SWT.Modify, this);

		Composite composite_3 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(3, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 2, 1));

		Group grpInitialContent = new Group(composite_3, SWT.NONE);
		grpInitialContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1));
		grpInitialContent.setLayout(new GridLayout(1, true));
		grpInitialContent.setText("Initial Content");

		btnSingleColor = new Button(grpInitialContent, SWT.RADIO);
		btnSingleColor.setSelection(true);
		btnSingleColor.setText("Single color");

		btnRandomColors = new Button(grpInitialContent, SWT.RADIO);
		btnRandomColors.setText("Random colors");

		Group grpPreferredColorPalette = new Group(composite_3, SWT.NONE);
		grpPreferredColorPalette.setLayout(new GridLayout(1, false));
		grpPreferredColorPalette.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, false, 1, 1));
		grpPreferredColorPalette.setText("Preferred Color Palette");

		cmbPalettes = new Combo(grpPreferredColorPalette, SWT.READ_ONLY);
		cmbPalettes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		addListeners();
		updatePaletteCombo();
	}

	/**
	 * updates the palette combo with the selectable color palettes
	 */
	private void updatePaletteCombo()
	{
		// remove all entries from the combo
		cmbPalettes.removeAll();
		int index = 0;

		// search for palettes in the new project selected
		if (targetLocationExists())
		{
			String projectName = null;
			String[] pathParts = txtLocation.getText().split("/");
			if (pathParts != null && pathParts.length > 0)
			{
				if (!pathParts[0].equals(""))
				{
					projectName = pathParts[0];
				}
				else if (pathParts.length > 1)
				{
					projectName = pathParts[1];	
				}
			}
			// get project reference
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);

			SnesdeProjectRoot projectRoot = null;
			try
			{
				projectRoot = FlowWorkspace.getProjectRoot(project);
			}
			catch (CoreException e)
			{
				cmbPalettes.removeAll();
				cmbPalettes.setEnabled(false);
			}
			HashSet<PaletteObject> palettesForColorDepth = projectRoot
					.getPalettesForColorDepth(getEncoding());

			if (palettesForColorDepth.size() > 0)
			{
				cmbPalettes.removeAll();
				cmbPalettes.setEnabled(true);
			}
			for (PaletteObject p : palettesForColorDepth)
			{
				String name2 = p.getName();
				if (name2.endsWith(".pal"))
				{
					cmbPalettes.add(name2.substring(0, name2.length() - 4),
							index);
					cmbPalettes.setText(name2.substring(0, name2.length() - 4));
					index++;
				}
			}
		}
	}

	/**
	 * adds listeners
	 */
	private void addListeners()
	{
		txtLocation.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(ModifyEvent e)
			{
				updatePaletteCombo();
			}
		});
	}

	@Override
	public String getAnyErrors()
	{
		if (!isInteger(txtColumns.getText()))
		{
			return "The column field must contain a positive number.";
		}
		if (!isInteger(txtRows.getText()))
		{
			return "The row field must contain a positive number.";
		}
		if (!isInteger(txtAdditionaltiles.getText()))
		{
			return "The additional tiles field must contain a positive number or zero.";
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
	 * returns the encoding selected in form of a constant. see
	 * NewTilesetWizardPage.ENCODING_XXX for possible values
	 * 
	 * @return the encoding
	 */
	protected int getEncoding()
	{
		String enc = cmbEncoding.getText();
		if (enc.equals("2 Colors (1bpp)"))
		{
			return ENCODING_2COLORS_1BPP;
		}
		else if (enc.equals("4 Colors (2bpp)"))
		{
			return ENCODING_4COLORS_2BPP;
		}
		else if (enc.equals("8 Colors (3bpp)"))
		{
			return ENCODING_8COLORS_3BPP;
		}
		else if (enc.equals("16 Colors (4bpp)"))
		{
			return ENCODING_16COLORS_4BPP;
		}
		else
		// if (enc.equals("256 Colors (8bpp)"))
		{
			return ENCODING_256COLORS_8BPP;
		}
	}

	/**
	 * returns the total number of tiles to be added
	 * 
	 * @return the number of tiles
	 */
	protected int getTotalNumberOfTiles()
	{
		String rows = txtRows.getText();
		int r = Integer.valueOf(rows);

		String columns = txtColumns.getText();
		int c = Integer.valueOf(columns);

		String additional = txtAdditionaltiles.getText();
		int a = Integer.valueOf(additional);

		return (r * c) + a;
	}

	/**
	 * returns the number of columns to be displayed can be serialized as well
	 * so the editor will look the same whenever it is reopened.
	 * 
	 * @return the number of columns configured
	 */
	protected int getNumberOfColumns()
	{
		return Integer.valueOf(txtColumns.getText());
	}

	/**
	 * returns CONTENT_RANDOM / CONTENT_SINGLECOLOR depending on user input
	 * 
	 * @return the int
	 */
	protected int getInitialContent()
	{
		if (btnRandomColors.getSelection())
		{
			return CONTENT_RANDOM;
		}
		return CONTENT_SINGLECOLOR;
	}

	/**
	 * returns the Palette selected by the user
	 * 
	 * @return the palette name
	 */
	protected String getPreferredPalette()
	{
		return cmbPalettes.getText();
	}
}
