package com.flow.snesde.core.wizards.createwizards.file.palette;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

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
public class NewPaletteWizardPage extends AbstractNewFileWizardPage
{
	public static final int MODE_SINGLE_COLOR = 0;
	public static final int MODE_RANDOM_COLORS = 1;

	/**
	 * This combo holds the potential color depths (2, 4, 8, 16 and 256) in this
	 * order
	 */
	private Combo cmbNumberOfColors;
	/**
	 * This radiobutton signalizes that the user wants to create a new palette
	 * with a single color initialized in every slot (default behaviour of the
	 * wizard)
	 */
	private Button btnSingleColor;
	private Label singleColorDisplay;
	private Button btnRandomColors;

	/**
	 * Create the wizard.
	 */
	public NewPaletteWizardPage(String preselection)
	{
		super(preselection);
	}

	@Override
	public void addComponents(Composite composite)
	{
		Label lblNumberOfColors = new Label(composite, SWT.NONE);
		lblNumberOfColors.setText("Number of colors:");

		this.cmbNumberOfColors = new Combo(composite, SWT.READ_ONLY);
		String[] colorDepths = { "2", "4", "8", "16", "256" };
		this.cmbNumberOfColors.setItems(colorDepths);
		this.cmbNumberOfColors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 2, 1));
		this.cmbNumberOfColors.select(3);

		Group grpInitialContent = new Group(composite, SWT.NONE);
		grpInitialContent.setLayout(new GridLayout(3, false));
		grpInitialContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 3, 1));
		grpInitialContent.setText("Initial Content");

		btnRandomColors = new Button(grpInitialContent, SWT.RADIO);
		btnRandomColors.setSelection(true);
		btnRandomColors.setText("Random colors");
		new Label(grpInitialContent, SWT.NONE);
		new Label(grpInitialContent, SWT.NONE);

		this.btnSingleColor = new Button(grpInitialContent, SWT.RADIO);
		this.btnSingleColor.setText("Single color");

		singleColorDisplay = new Label(grpInitialContent, SWT.BORDER);
		singleColorDisplay.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND));
		singleColorDisplay.setAlignment(SWT.CENTER);
		singleColorDisplay.setText("                              ");
		singleColorDisplay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1));
		new Label(grpInitialContent, SWT.NONE);
		singleColorDisplay.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent arg0)
			{
				ColorDialog dlg = new ColorDialog(NewPaletteWizardPage.this
						.getShell());
				RGB rgb = dlg.open();
				if (rgb != null)
				{
					singleColorDisplay.setBackground(new Color(null, rgb.red,
							rgb.green, rgb.blue));
				}
			}
		});
	}

	@Override
	public String getAnyErrors()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the number of colors the new palette should have
	 * 
	 * @return the number of colors for the new palette as an int
	 */
	protected int getColorDepth()
	{
		int i = Integer.parseInt(this.cmbNumberOfColors.getText());
		return i;
	}

	/**
	 * returns the selected red value
	 * 
	 * @return the red value (0..31)
	 */
	protected int getRedValue()
	{
		return (this.singleColorDisplay.getBackground().getRed() / 8);
	}

	/**
	 * returns the selected green value
	 * 
	 * @return the green value (0..31)
	 */
	protected int getGreenValue()
	{
		return (this.singleColorDisplay.getBackground().getGreen() / 8);
	}

	/**
	 * returns the selected blue value
	 * 
	 * @return the blue value (0..31)
	 */
	protected int getBlueValue()
	{
		return (this.singleColorDisplay.getBackground().getBlue() / 8);
	}

	/**
	 * returns the color mode to create (random vs single color)
	 * 
	 * @return
	 */
	public int getColorMode()
	{
		if (btnSingleColor.getSelection())
		{
			return MODE_SINGLE_COLOR;
		}
		return MODE_RANDOM_COLORS;
	}
}
