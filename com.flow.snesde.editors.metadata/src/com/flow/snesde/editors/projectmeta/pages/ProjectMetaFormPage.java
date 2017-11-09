package com.flow.snesde.editors.projectmeta.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import projectmeta.Metadata;
import projectmeta.ProjectmetaPackage.Literals;

import com.flow.snesde.preprocessor.metadatamapping.CarttypeMapping;
import com.flow.snesde.preprocessor.metadatamapping.CountrycodeMapping;
import com.flow.snesde.preprocessor.metadatamapping.LicenseeMapping;
import com.flow.snesde.preprocessor.metadatamapping.RamsizeMapping;
import com.flow.snesde.preprocessor.metadatamapping.RomsizeMapping;
import com.flow.snesde.preprocessor.metadatamapping.TimingMapping;

/**
 * @author flo The first (and up to now single) editor page for the project
 *         metadata editor.
 */
public class ProjectMetaFormPage extends FormPage
{
	private DataBindingContext m_bindingContext;
	// the editors EMF model
	Metadata model;

	// the command stack holding EMF thingy
	private final AdapterFactoryEditingDomain editingDomain;

	// UI stuff
	private Text txtShortname;
	private Text txtGamename;
	private Spinner spinnerVersion;
	private Label lblCountrycode;
	private Label lblLicensee;
	private Combo cmbCountrycode;
	private Combo cmbLicensee;
	private Combo cmbTiming;
	private Combo cmbAddressing;
	private Combo cmbCarttype;
	private Combo cmbRomsize;
	private Combo cmbRamsize;
	private Label lblGameTitle;
	private final FormEditor editor;
	private Label lblVideoFormat;
	private Combo cmbVideoformat;

	/**
	 * Create the form page.
	 * 
	 * @param editor
	 *            the editor the page belongs to
	 * @param id
	 *            the id of the page
	 * @param title
	 *            the title to be displayed in the editors tab
	 * @param model
	 *            the editor model to process on
	 * @param editingDomain
	 *            the commandstack holding editing domain
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public ProjectMetaFormPage(final FormEditor editor, final String id,
			final String title, Metadata model,
			AdapterFactoryEditingDomain editingDomain)
	{
		super(editor, id, title);
		this.editor = editor;
		this.editingDomain = editingDomain;
		this.model = (Metadata) editingDomain.getResourceSet().getResources()
				.get(0).getContents().get(0);
	}

	/**
	 * Create contents of the form.
	 * 
	 * @param managedForm
	 *            the base form every widget of this page will be added to
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm)
	{
		managedForm.getForm().setImage(
				ResourceManager.getPluginImage("com.flow.snesde.core",
						"graphics/icons/32/staticData.png"));
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("Project Metadata");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody()
				.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(managedForm.getForm().getBody(),
				SWT.NONE);
		sashForm.setBounds(0, 0, 32, 32);
		managedForm.getToolkit().adapt(sashForm);
		managedForm.getToolkit().paintBordersFor(sashForm);

		Section sctnNewSection = managedForm.getToolkit().createSection(
				sashForm, Section.TITLE_BAR);
		sctnNewSection.marginWidth = 5;
		sctnNewSection.marginHeight = 5;
		managedForm.getToolkit().paintBordersFor(sctnNewSection);
		sctnNewSection.setText("Game Details");
		sctnNewSection.setExpanded(true);

		Composite composite = managedForm.getToolkit().createComposite(
				sctnNewSection, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite);
		sctnNewSection.setClient(composite);
		composite.setLayout(new GridLayout(2, false));

		lblGameTitle = managedForm.getToolkit().createLabel(composite,
				"Game Name:", SWT.NONE);
		lblGameTitle.setFont(SWTResourceManager.getFont("Cantarell", 11,
				SWT.NORMAL));
		lblGameTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		txtGamename = managedForm.getToolkit().createText(composite,
				"New Text", SWT.NONE);
		txtGamename.setText("");
		this.txtGamename.setTextLimit(20);
		txtGamename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblShortname = managedForm.getToolkit().createLabel(composite,
				"Game Shortname:", SWT.NONE);
		lblShortname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		this.txtShortname = managedForm.getToolkit().createText(composite,
				"New Text", SWT.NONE);
		this.txtShortname.setText("");
		this.txtShortname.setTextLimit(4);
		this.txtShortname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		lblCountrycode = managedForm.getToolkit().createLabel(composite,
				"Publishing Region:", SWT.NONE);

		cmbCountrycode = new Combo(composite, SWT.READ_ONLY);
		cmbCountrycode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		cmbCountrycode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbCountrycode.setItems(new CountrycodeMapping().getLabelSet());
		managedForm.getToolkit().adapt(cmbCountrycode);
		managedForm.getToolkit().paintBordersFor(cmbCountrycode);

		lblLicensee = managedForm.getToolkit().createLabel(composite,
				"Licensee:", SWT.NONE);
		lblLicensee.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		cmbLicensee = new Combo(composite, SWT.READ_ONLY);
		cmbLicensee.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		managedForm.getToolkit().adapt(cmbLicensee);
		managedForm.getToolkit().paintBordersFor(cmbLicensee);
		cmbLicensee.setItems(new LicenseeMapping().getLabelSet());
		// cmbLicensee.setItems(new String[] { "Absolute Entertainment",
		// "Acclaim", "Accolade Europe Inc.", "Activision", "Ajinomoto",
		// "American Sammy", "American Softworks Corp.", "Arcade Zone",
		// "ASCII", "Ask Kodansha", "Asmik", "Athena", "Atlus", "Banalex",
		// "BanDai America", "Banpresto", "Beam Software", "BEC",
		// "Black Pearl", "Bullet-Proof Software", "Capcom",
		// "Carrozzeria", "Character Soft", "Chris Gray Enterprises Inc.",
		// "Chun Software", "COBRA Team", "Coconuts", "Culture Brain",
		// "Data East", "DATAM-Polystar", "Flow", "Dynamic",
		// "Electro Brain", "Electronic Arts", "Elite", "Empire Software",
		// "Enix", "Epoch", "Falcom", "Field", "Game Village", "GameTek",
		// "Gremlin Graphics", "Halken", "Hect", "Hi Tech Expressions",
		// "Hiro", "Hori", "HOT-B", "Hudson Soft", "Human Entertainment",
		// "I'Max", "Imagesoft", "Imagineer", "Infogrames", "INTEC Inc.",
		// "Interplay", "Irem Japan", "Jaleco", "JVC", "K. Amusement",
		// "Kaneco", "Kemco", "King A Wave", "KOEI", "Konami", "KSS",
		// "Laser Beam", "LifeFitness Exertainment", "LJN Toys",
		// "Loriciel", "Lozc", "LucasArts", "Magifact", "Masaya", "Maxis",
		// "Mebio Software", "Meldac KAZe", "Micro World", "Micronet",
		// "Microprose", "Mindscape", "Misawa", "Namco Ltd.", "Natsume",
		// "Naxat", "NCS", "NHK", "Nichibutsu", "Nintendo", "Ocean",
		// "Open System", "Origin", "Other", "Pack in Video", "Pal Soft",
		// "Parker Brothers", "Pony Canyon", "POW", "Psygnosis Ltd.",
		// "Quest", "Rage Software", "Renovation Products",
		// "Ringler Studios", "Romstar", "S'Pal Inc.",
		// "Sculptured Software", "Seika Corp.", "Seta Co. Ltd.", "Sigma",
		// "Sofel", "Sony", "Sotsu Agency", "Spectrum Holobyte",
		// "Squaresoft", "Starfish", "STORM", "Sunsoft", "System 3",
		// "System Sacom Corp.", "T&E Software", "Taito", "Takara",
		// "Tec Magik", "Technos", "Tecmo", "Teichio", "THQ Software",
		// "Titus", "Toei Animation", "Toho", "Tokai Engineering",
		// "Tokuma Shoten Intermedia", "Tomy", "Tonkin House",
		// "Toshiba EMI System Vision", "Tradewest",
		// "Triffix Entertainment", "Tsukuda Original", "U.S. Gold",
		// "UBI Soft", "Varie", "Viacom New Media", "Video System",
		// "Virgin Interactive Entertainment", "Visit Co. Ltd.",
		// "Wolf Team", "Yanoman", "Zamuse", "Zoom" });

		Label lblVersion = managedForm.getToolkit().createLabel(composite,
				"Version: 1.", SWT.NONE);
		lblVersion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		this.spinnerVersion = new Spinner(composite, SWT.BORDER);
		this.spinnerVersion.setMaximum(255);
		this.spinnerVersion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1));
		managedForm.getToolkit().adapt(this.spinnerVersion);
		managedForm.getToolkit().paintBordersFor(this.spinnerVersion);

		Section sctnNewSection_1 = managedForm.getToolkit().createSection(
				sashForm, Section.TITLE_BAR);
		sctnNewSection_1.marginWidth = 5;
		sctnNewSection_1.marginHeight = 5;
		managedForm.getToolkit().paintBordersFor(sctnNewSection_1);
		sctnNewSection_1.setText("Cartridge Specification");
		sctnNewSection_1.setExpanded(true);

		Composite composite_1 = managedForm.getToolkit().createComposite(
				sctnNewSection_1, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite_1);
		sctnNewSection_1.setClient(composite_1);
		composite_1.setLayout(new GridLayout(2, false));

		Label lblCartridgeType = managedForm.getToolkit().createLabel(
				composite_1, "Cartridge Features:", SWT.NONE);
		lblCartridgeType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));

		cmbCarttype = new Combo(composite_1, SWT.READ_ONLY);
		cmbCarttype.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbCarttype.setItems(new CarttypeMapping().getLabelSet());
		// cmbCarttype.setItems(new String[] { "ROM", "ROM + RAM", "ROM + SRAM",
		// "ROM + DSP", "ROM + RAM + DSP", "ROM + SRAM + DSP",
		// "ROM + SuperFX", "ROM + RAM + SuperFX", "ROM + SRAM + SuperFX",
		// "ROM + SRAM + SA-1" });
		managedForm.getToolkit().adapt(cmbCarttype);
		managedForm.getToolkit().paintBordersFor(cmbCarttype);

		Label lblAdressing = managedForm.getToolkit().createLabel(composite_1,
				"Board Layout:", SWT.NONE);
		lblAdressing.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		cmbAddressing = new Combo(composite_1, SWT.READ_ONLY);
		cmbAddressing.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbAddressing.setItems(new String[] { "LoROM", "HiROM" });
		managedForm.getToolkit().adapt(cmbAddressing);
		managedForm.getToolkit().paintBordersFor(cmbAddressing);

		Label lblRomSize = managedForm.getToolkit().createLabel(composite_1,
				"ROM Size:", SWT.NONE);
		lblRomSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		cmbRomsize = new Combo(composite_1, SWT.READ_ONLY);
		cmbRomsize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbRomsize.setItems(new RomsizeMapping().getLabelSet());
		// cmbRomsize.setItems(new String[] { "256 Kilobytes", "512 Kilobytes",
		// "1 Megabyte", "2 Megabytes", "4 Megabytes" });
		managedForm.getToolkit().adapt(cmbRomsize);
		managedForm.getToolkit().paintBordersFor(cmbRomsize);

		Label lblRomTiming = managedForm.getToolkit().createLabel(composite_1,
				"ROM Speed:", SWT.NONE);
		lblRomTiming.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		cmbTiming = new Combo(composite_1, SWT.READ_ONLY);
		cmbTiming.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		cmbTiming.setItems(new TimingMapping().getLabelSet());
		// cmbTiming.setItems(new String[] { "120 Nanoseconds (FASTROM)",
		// "200 Nanoseconds (SLOWROM)" });
		managedForm.getToolkit().adapt(cmbTiming);
		managedForm.getToolkit().paintBordersFor(cmbTiming);

		Label lblRamSize = managedForm.getToolkit().createLabel(composite_1,
				"RAM Size:", SWT.NONE);
		lblRamSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		cmbRamsize = new Combo(composite_1, SWT.READ_ONLY);
		cmbRamsize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbRamsize.setItems(new RamsizeMapping().getLabelSet());
		// cmbRamsize.setItems(new String[] { "0 Kilobytes", "2 Kilobytes",
		// "4 Kilobytes", "8 Kilobytes" });
		managedForm.getToolkit().adapt(cmbRamsize);
		managedForm.getToolkit().paintBordersFor(cmbRamsize);

		lblVideoFormat = new Label(composite_1, SWT.NONE);
		lblVideoFormat.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		managedForm.getToolkit().adapt(lblVideoFormat, true, true);
		lblVideoFormat.setText("Video Format:");

		cmbVideoformat = new Combo(composite_1, SWT.READ_ONLY);
		cmbVideoformat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cmbVideoformat.setItems(new String[] { "NTSC", "PAL" });
		managedForm.getToolkit().adapt(cmbVideoformat);
		managedForm.getToolkit().paintBordersFor(cmbVideoformat);
		sashForm.setWeights(new int[] { 1, 1 });
		m_bindingContext = initDataBindings();
	}

	protected DataBindingContext initDataBindings()
	{
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtGamenameObserveWidget = WidgetProperties
				.text(new int[] { SWT.Modify, SWT.FocusOut }).observeDelayed(
						200, txtGamename);
		IObservableValue modelGamenameObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__GAMENAME);
		bindingContext.bindValue(observeTextTxtGamenameObserveWidget,
				modelGamenameObserveValue, null, null);
		//
		IObservableValue observeTextTxtShortnameObserveWidget = WidgetProperties
				.text(new int[] { SWT.Modify, SWT.FocusOut }).observeDelayed(
						200, txtShortname);
		IObservableValue modelShortnameObserveValue = EMFEditObservables
				.observeValue(editingDomain, model,
						Literals.METADATA__SHORTNAME);
		bindingContext.bindValue(observeTextTxtShortnameObserveWidget,
				modelShortnameObserveValue, null, null);
		//
		IObservableValue observeTextCmbCountrycodeObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbCountrycode);
		IObservableValue modelCountryObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__COUNTRY);
		bindingContext.bindValue(observeTextCmbCountrycodeObserveWidget,
				modelCountryObserveValue, null, null);
		//
		IObservableValue observeTextCmbLicenseeObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbLicensee);
		IObservableValue modelLicenseeObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__LICENSEE);
		bindingContext.bindValue(observeTextCmbLicenseeObserveWidget,
				modelLicenseeObserveValue, null, null);
		//
		IObservableValue observeSelectionSpinnerVersionObserveWidget = WidgetProperties
				.selection().observeDelayed(200, spinnerVersion);
		IObservableValue modelVersionObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__VERSION);
		bindingContext.bindValue(observeSelectionSpinnerVersionObserveWidget,
				modelVersionObserveValue, null, null);
		//
		IObservableValue observeTextCmbTimingObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbTiming);
		IObservableValue modelTimingObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__TIMING);
		bindingContext.bindValue(observeTextCmbTimingObserveWidget,
				modelTimingObserveValue, null, null);
		//
		IObservableValue observeTextCmbAddressingObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbAddressing);
		IObservableValue modelAdressingObserveValue = EMFEditObservables
				.observeValue(editingDomain, model,
						Literals.METADATA__ADRESSING);
		bindingContext.bindValue(observeTextCmbAddressingObserveWidget,
				modelAdressingObserveValue, null, null);
		//
		IObservableValue observeTextCmbCarttypeObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbCarttype);
		IObservableValue modelCartridgeTypeObserveValue = EMFEditObservables
				.observeValue(editingDomain, model,
						Literals.METADATA__CARTRIDGE_TYPE);
		bindingContext.bindValue(observeTextCmbCarttypeObserveWidget,
				modelCartridgeTypeObserveValue, null, null);
		//
		IObservableValue observeTextCmbRomsizeObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbRomsize);
		IObservableValue modelRomSizeObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__ROM_SIZE);
		bindingContext.bindValue(observeTextCmbRomsizeObserveWidget,
				modelRomSizeObserveValue, null, null);
		//
		IObservableValue observeTextCmbRamsizeObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbRamsize);
		IObservableValue modelRamSizeObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.METADATA__RAM_SIZE);
		bindingContext.bindValue(observeTextCmbRamsizeObserveWidget,
				modelRamSizeObserveValue, null, null);
		//
		IObservableValue observeTextCmbVideoformatObserveWidget = WidgetProperties
				.text().observeDelayed(200, cmbVideoformat);
		IObservableValue modelVideoformatObserveValue = EMFEditObservables
				.observeValue(editingDomain, model,
						Literals.METADATA__VIDEOFORMAT);
		bindingContext.bindValue(observeTextCmbVideoformatObserveWidget,
				modelVideoformatObserveValue, null, null);
		//
		return bindingContext;
	}
}
