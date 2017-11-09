package com.flow.snesde.core.wizards.createwizards.project.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import com.flow.snesde.preprocessor.metadatamapping.CountrycodeMapping;

public class NewSnesdeProjectWizardPage extends WizardPage
{
	private Text txtProjectName;
	private Text txtGameName;
	private Text txtGameShortname;
	private Combo cmbCountrycode;
	private Combo cmbLicensees;
	private Combo cmbCarttype;
	private Combo cmbAdressing;
	private Combo cmbRomsizes;
	private Combo cmbRamsizes;
	private Combo cmbTiming;
	private Combo cmbVideoformat;
	private Button btnUseDefaults;
	private Spinner spnVersion;

	/**
	 * Constructor
	 * 
	 * @param pageName
	 *            the pages name
	 */
	public NewSnesdeProjectWizardPage(String pageName)
	{
		super(pageName);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"com.flow.snesde.core.wizards", "graphics/wizards/project.png"));
		setTitle("Create a SNESDE Project");
		setDescription("Enter all relevant data to create a new SNES game project");
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(2, false));

		Label lblProjectName = new Label(container, SWT.NONE);
		lblProjectName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblProjectName.setText("Project Name:");

		txtProjectName = new Text(container, SWT.BORDER);
		txtProjectName.setMessage("Enter the project name here");
		txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Group grpGameDetails = new Group(container, SWT.NONE);
		grpGameDetails.setLayout(new GridLayout(4, false));
		grpGameDetails.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 2, 1));
		grpGameDetails.setText("Game Details:");

		Label lblGameName = new Label(grpGameDetails, SWT.NONE);
		lblGameName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblGameName.setText("Game Name:");

		txtGameName = new Text(grpGameDetails, SWT.BORDER);
		txtGameName.setMessage("Enter the games name here");
		txtGameName.setTextLimit(20);
		txtGameName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));

		Label lblGameShortname = new Label(grpGameDetails, SWT.NONE);
		lblGameShortname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblGameShortname.setText("Game Shortname:");

		txtGameShortname = new Text(grpGameDetails, SWT.BORDER);
		txtGameShortname.setMessage("Enter the games shortname here");
		txtGameShortname.setTextLimit(4);
		txtGameShortname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));

		Label lblPublishingRegion = new Label(grpGameDetails, SWT.NONE);
		lblPublishingRegion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblPublishingRegion.setText("Publishing Region:");

		cmbCountrycode = new Combo(grpGameDetails, SWT.READ_ONLY);
		cmbCountrycode.setItems(new CountrycodeMapping().getLabelSet());
		cmbCountrycode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));
		cmbCountrycode.select(11);

		Label lblLicensee = new Label(grpGameDetails, SWT.NONE);
		lblLicensee.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblLicensee.setText("Licensee:");

		cmbLicensees = new Combo(grpGameDetails, SWT.READ_ONLY);
		this.cmbLicensees.setItems(new String[] { "Absolute Entertainment",
				"Acclaim", "Accolade Europe Inc.", "Activision", "Ajinomoto",
				"American Sammy", "American Softworks Corp.", "Arcade Zone",
				"ASCII", "Ask Kodansha", "Asmik", "Athena", "Atlus", "Banalex",
				"BanDai America", "Banpresto", "Beam Software", "BEC",
				"Black Pearl", "Bullet-Proof Software", "Capcom",
				"Carrozzeria", "Character Soft", "Chris Gray Enterprises Inc.",
				"Chun Software", "COBRA Team", "Coconuts", "Culture Brain",
				"Data East", "DATAM-Polystar", "Digisalt", "Dynamic",
				"Electro Brain", "Electronic Arts", "Elite", "Empire Software",
				"Enix", "Epoch", "Falcom", "Field", "Game Village", "GameTek",
				"Gremlin Graphics", "Halken", "Hect", "Hi Tech Expressions",
				"Hiro", "Hori", "HOT-B", "Hudson Soft", "Human Entertainment",
				"I'Max", "Imagesoft", "Imagineer", "Infogrames", "INTEC Inc.",
				"Interplay", "Irem Japan", "Jaleco", "JVC", "K. Amusement",
				"Kaneco", "Kemco", "King A Wave", "KOEI", "Konami", "KSS",
				"Laser Beam", "LifeFitness Exertainment", "LJN Toys",
				"Loriciel", "Lozc", "LucasArts", "Magifact", "Masaya", "Maxis",
				"Mebio Software", "Meldac KAZe", "Micro World", "Micronet",
				"Microprose", "Mindscape", "Misawa", "Namco Ltd.", "Natsume",
				"Naxat", "NCS", "NHK", "Nichibutsu", "Nintendo", "Ocean",
				"Open System", "Origin", "Other", "Pack in Video", "Pal Soft",
				"Parker Brothers", "Pony Canyon", "POW", "Psygnosis Ltd.",
				"Quest", "Rage Software", "Renovation Products",
				"Ringler Studios", "Romstar", "S'Pal Inc.",
				"Sculptured Software", "Seika Corp.", "Seta Co. Ltd.", "Sigma",
				"Sofel", "Sony", "Sotsu Agency", "Spectrum Holobyte",
				"Squaresoft", "Starfish", "STORM", "Sunsoft", "System 3",
				"System Sacom Corp.", "T&E Software", "Taito", "Takara",
				"Tec Magik", "Technos", "Tecmo", "Teichio", "THQ Software",
				"Titus", "Toei Animation", "Toho", "Tokai Engineering",
				"Tokuma Shoten Intermedia", "Tomy", "Tonkin House",
				"Toshiba EMI System Vision", "Tradewest",
				"Triffix Entertainment", "Tsukuda Original", "U.S. Gold",
				"UBI Soft", "Varie", "Viacom New Media", "Video System",
				"Virgin Interactive Entertainment", "Visit Co. Ltd.",
				"Wolf Team", "Yanoman", "Zamuse", "Zoom" });
		cmbLicensees.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbLicensees.select(93);

		Label lblVersion = new Label(grpGameDetails, SWT.NONE);
		lblVersion.setText("Version: 1.");

		spnVersion = new Spinner(grpGameDetails, SWT.BORDER);
		spnVersion.setMaximum(255);

		Group grpCartridgeSpecifics = new Group(container, SWT.NONE);
		grpCartridgeSpecifics.setLayout(new GridLayout(4, false));
		grpCartridgeSpecifics.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 2, 1));
		grpCartridgeSpecifics.setText("Cartridge Specification:");

		Label lblCartridgeFeatures = new Label(grpCartridgeSpecifics, SWT.NONE);
		lblCartridgeFeatures.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblCartridgeFeatures.setText("Cartridge Features:");

		cmbCarttype = new Combo(grpCartridgeSpecifics, SWT.READ_ONLY);
		cmbCarttype.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbCarttype.setItems(new String[] { "ROM", "ROM + RAM", "ROM + SRAM",
				"ROM + DSP", "ROM + RAM + DSP", "ROM + SRAM + DSP",
				"ROM + SuperFX", "ROM + RAM + SuperFX", "ROM + SRAM + SuperFX",
				"ROM + SRAM + SA-1" });
		cmbCarttype.select(2);

		Label lblBoardLayout = new Label(grpCartridgeSpecifics, SWT.NONE);
		lblBoardLayout.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblBoardLayout.setText("Board Layout:");

		cmbAdressing = new Combo(grpCartridgeSpecifics, SWT.READ_ONLY);
		cmbAdressing.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbAdressing.setItems(new String[] { "LoROM", "HiROM" });
		cmbAdressing.select(0);

		Label lblRomSize = new Label(grpCartridgeSpecifics, SWT.NONE);
		lblRomSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblRomSize.setText("ROM Size:");

		cmbRomsizes = new Combo(grpCartridgeSpecifics, SWT.READ_ONLY);
		cmbRomsizes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbRomsizes.setItems(new String[] { "256 Kilobytes", "512 Kilobytes",
				"1 Megabyte", "2 Megabytes", "4 Megabytes" });
		cmbRomsizes.select(4);

		Label lblRamSize = new Label(grpCartridgeSpecifics, SWT.NONE);
		lblRamSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblRamSize.setText("RAM Size:");

		cmbRamsizes = new Combo(grpCartridgeSpecifics, SWT.READ_ONLY);
		cmbRamsizes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbRamsizes.setItems(new String[] { "0 Kilobytes", "2 Kilobytes",
				"4 Kilobytes", "8 Kilobytes" });
		cmbRamsizes.select(3);

		Label lblRomSpeed = new Label(grpCartridgeSpecifics, SWT.NONE);
		lblRomSpeed.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblRomSpeed.setText("ROM Speed:");

		cmbTiming = new Combo(grpCartridgeSpecifics, SWT.READ_ONLY);
		cmbTiming.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		cmbTiming.setItems(new String[] { "120 Nanoseconds (FASTROM)",
				"200 Nanoseconds (SLOWROM)" });
		cmbTiming.select(0);

		Label lblVideoFormat = new Label(grpCartridgeSpecifics, SWT.NONE);
		lblVideoFormat.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblVideoFormat.setText("Video Format:");

		cmbVideoformat = new Combo(grpCartridgeSpecifics, SWT.READ_ONLY);
		cmbVideoformat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbVideoformat.setItems(new String[] { "NTSC", "PAL" });
		cmbVideoformat.select(0);
		new Label(grpCartridgeSpecifics, SWT.NONE);
		new Label(grpCartridgeSpecifics, SWT.NONE);
		new Label(grpCartridgeSpecifics, SWT.NONE);
		new Label(grpCartridgeSpecifics, SWT.NONE);
		new Label(grpCartridgeSpecifics, SWT.NONE);
		new Label(grpCartridgeSpecifics, SWT.NONE);
		new Label(grpCartridgeSpecifics, SWT.NONE);

		btnUseDefaults = new Button(grpCartridgeSpecifics, SWT.NONE);
		btnUseDefaults.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		btnUseDefaults.setText("Use Defaults");

		addListeners();
	}

	/**
	 * adds listeners to the widgets
	 */
	private void addListeners()
	{
		// disable ramsize selection when no ram is used
		cmbCarttype.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(final MouseEvent e)
			{
				if (cmbCarttype.getSelectionIndex() == 0
						|| cmbCarttype.getSelectionIndex() == 3
						|| cmbCarttype.getSelectionIndex() == 6)
				{
					cmbRamsizes.select(0);
					cmbRamsizes.setEnabled(false);
				}
				else
				{
					cmbRamsizes.setEnabled(true);
				}
			}
		});

		// don't allow space characters in game shortname
		txtGameShortname.addVerifyListener(new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent e)
			{
				if (e.text.contains(" "))
				{
					e.doit = false;
				}
			}
		});

		// don't allow space characters in game longname
		txtGameName.addVerifyListener(new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent e)
			{
				if (e.text.contains(" "))
				{
					e.doit = false;
				}
			}
		});

		// update game shortname on game modification
		txtProjectName.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(final ModifyEvent e)
			{
				updateEnablement();
				updateGamenameFromProjectNameChange();
			}
		});

		// update game shortname on game modification
		txtGameName.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(final ModifyEvent e)
			{
				updateEnablement();
				updateGamenameFromGamenameChange();
			}
		});

		btnUseDefaults.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(final KeyEvent e)
			{
				if (e.keyCode == SWT.SPACE || e.keyCode == SWT.CR)
				{
					resetDetails();
				}
			}
		});
		btnUseDefaults.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(final MouseEvent e)
			{
				resetDetails();
			}
		});
	}

	/**
	 * resets all details fields to the default values
	 */
	private void resetDetails()
	{
		if (this.txtGameName.getText().length() > 0)
		{
			if (this.txtGameName.getText().length() >= 4)
			{
				this.txtGameShortname.setText(this.txtGameName.getText()
						.substring(0, 4));
			}
			else
			{
				this.txtGameShortname.setText(this.txtGameName.getText());
			}
		}
		else
		{
			if (this.txtProjectName.getText().length() > 0)
			{
				if (this.txtProjectName.getText().length() >= 4)
				{
					this.txtGameShortname.setText(this.txtProjectName.getText()
							.substring(0, 4));
				}
				else
				{
					this.txtGameShortname
							.setText(this.txtProjectName.getText());
				}
			}
			else
			{
				this.txtGameShortname.setText("");
			}
		}
		cmbTiming.select(0);
		cmbAdressing.select(0);
		cmbCarttype.select(2);
		cmbRomsizes.select(4);
		cmbRamsizes.select(3);
		cmbRamsizes.setEnabled(true);
		cmbVideoformat.select(0);
		spnVersion.setDigits(0);
	}

	/**
	 * activates/deactivates the next and finish buttons depending on weather or
	 * not a project has been selected
	 */
	private void updateEnablement()
	{
		if (this.txtProjectName != null && this.txtGameName != null)
		{
			boolean projectNameSet = this.txtProjectName.getText() != "";
			boolean gameNameSet = this.txtGameName.getText() != "";

			if (projectNameSet && gameNameSet)
			{
				setErrorMessage(null);
				setDescription("Click Finish to create the new Project.");
				setPageComplete(true);
			}
			else
			{
				if (!projectNameSet)
				{
					setErrorMessage("You have to enter a project name!");
				}
				else if (!gameNameSet)
				{
					setErrorMessage("You have to enter the games name!");
				}
				setPageComplete(false);
			}
		}
		else
		{
			setPageComplete(false);
		}
	}

	/**
	 * will update the field gameshortname according to the recent changes in
	 * gamename field
	 */
	protected void updateGamenameFromGamenameChange()
	{
		// update game shortname
		if (this.txtGameShortname != null)
		{
			String gameName = this.txtGameName.getText();
			if (gameName.length() > 4)
			{
				this.txtGameShortname.setText(gameName.substring(0, 4)
						.toUpperCase());
			}
			else
			{
				this.txtGameShortname.setText(gameName.toUpperCase());
			}
		}
	}

	/**
	 * will update the fields gamename and gameshortname according to the recent
	 * changes in project field
	 */
	protected void updateGamenameFromProjectNameChange()
	{
		String projectName = this.txtProjectName.getText();
		// update Game name
		if (this.txtGameName != null)
		{
			if (projectName.length() > 21)
			{
				this.txtGameName.setText(projectName.substring(0, 21)
						.toUpperCase());
			}
			else
			{
				this.txtGameName.setText(projectName.toUpperCase());
			}
		}
		// update game shortname
		if (this.txtGameShortname != null)
		{
			if (projectName.length() > 4)
			{
				this.txtGameShortname.setText(projectName.substring(0, 4)
						.toUpperCase());
			}
			else
			{
				this.txtGameShortname.setText(projectName.toUpperCase());
			}
		}
	}

	/**
	 * A method returning the chosen project name
	 * 
	 * @return the project name chosen
	 */
	public String getProjectName()
	{
		return this.txtProjectName.getText();
	}

	/**
	 * A method returning the chosen game name
	 * 
	 * @return the game name chosen
	 */
	public String getGameName()
	{
		return this.txtGameName.getText();
	}

	/**
	 * returns the game shortname
	 * 
	 * @return the game shortname
	 */
	public String getShortname()
	{
		return this.txtGameShortname.getText();
	}

	/**
	 * returns the timing currently set
	 * 
	 * @return the timing set
	 */
	public String getTiming()
	{
		return this.cmbTiming.getText();
	}

	/**
	 * returns the currently set adressing mode
	 * 
	 * @return the adressing mode set
	 */
	public String getAdressing()
	{
		return this.cmbAdressing.getText();
	}

	/**
	 * returns the currently set cartridge type
	 * 
	 * @return the carttype set
	 */
	public String getCarttype()
	{
		return this.cmbCarttype.getText();
	}

	/**
	 * returns the currently set romsize
	 * 
	 * @return the romsize set
	 */
	public String getRomsize()
	{
		return this.cmbRomsizes.getText();
	}

	/**
	 * returns the ramsize set
	 * 
	 * @return the ramsize set
	 */
	public String getRamsize()
	{
		return this.cmbRamsizes.getText();
	}

	/**
	 * returns the country currently selected
	 * 
	 * @return the country selected
	 */
	public String getCountry()
	{
		return this.cmbCountrycode.getText();
	}

	/**
	 * returns the licensee currently selected
	 * 
	 * @return the licensee set
	 */
	public String getLicensee()
	{
		return this.cmbLicensees.getText();
	}

	/**
	 * returns the videoformat to be used
	 * 
	 * @return the videoformat (PAL/NTSC)
	 */
	public String getVideoFormat()
	{
		return this.cmbVideoformat.getText();
	}

	/**
	 * returns the version to be used
	 * 
	 * @return the version
	 */
	public int getVersion()
	{
		return this.spnVersion.getSelection();
	}
}
