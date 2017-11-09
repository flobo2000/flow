/**
 * 
 */
package com.flow.snesde.editors.paletteedit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import palette.Palette;
import palette.PalettePackage.Literals;
import palette.impl.PaletteImpl;

import com.flow.snesde.editors.paletteedit.commands.AddColorToPaletteCommand;
import com.flow.snesde.editors.paletteedit.commands.ChangeRGBFromSelectedColorCommand;
import com.flow.snesde.editors.paletteedit.commands.CommandFactory;
import com.flow.snesde.editors.paletteedit.commands.CommandParameter;
import com.flow.snesde.editors.paletteedit.commands.RemoveColorFromPaletteCommand;
import com.flow.snesde.editors.paletteedit.commands.SelectColorWithIndexCommand;
import com.flow.snesde.editors.paletteedit.converters.EmfColorToNonNativeColorConverter;
import com.flow.snesde.editors.paletteedit.converters.EmfColorsToNonNativeColorsConverter;
import com.flow.snesde.editors.paletteedit.converters.EmfColorsToStringConverter;
import com.flow.snesde.editors.paletteedit.converters.IntToStringConverter;
import com.flow.snesde.uilib.colorSpectrumBrowser.ColorSpectrumBrowser;
import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.palettePanel.PalettePanel;
import com.flow.snesde.uilib.singleColorSelectionPanel.SingleColorSelectionPanel;

/**
 * @author flo
 */
public class PaletteEditorFormPage extends FormPage
{
	private DataBindingContext m_bindingContext;

	private FormEditor editor;
	private AdapterFactoryEditingDomain editingDomain;
	private Palette model;
	private Label lblPalNameValue;
	private Label lblNumOfColorsValue;
	private SingleColorSelectionPanel selectedColorPanel;

	private PalettePanel palettePanel;
	private Slider slider_red;

	private Slider slider_green;

	private Slider slider_blue;

	private String title;

	private Button btnNextColor;

	private Button btnPreviousColor;

	private Label lblAddColor1;

	private Label lblAddColor2;

	private Label lblRemoveColor1;

	private Label lblRemoveColor2;

	private ColorSpectrumBrowser colorpicker;
	private Label lblRedvalue;
	private Label lblGreenvalue;
	private Label lblBluevalue;

	/**
	 * Create the form page.
	 * 
	 * @param id
	 * @param title
	 */
	public PaletteEditorFormPage(final String id, final String title)
	{
		super(id, title);
	}

	/**
	 * Create the form page.
	 * 
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public PaletteEditorFormPage(final FormEditor editor, final String id,
			final String title, PaletteImpl model2,
			AdapterFactoryEditingDomain editingDomain)
	{
		super(editor, id, title);
		this.editor = editor;
		this.title = title;
		this.editingDomain = editingDomain;
		this.model = (Palette) editingDomain.getResourceSet().getResources()
				.get(0).getContents().get(0);
	}

	/**
	 * Create contents of the form.
	 * 
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm)
	{
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText(getTitle());
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new GridLayout(1, false));

		SashForm sashForm_3 = new SashForm(managedForm.getForm().getBody(),
				SWT.NONE);
		sashForm_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		managedForm.getToolkit().adapt(sashForm_3);
		managedForm.getToolkit().paintBordersFor(sashForm_3);

		SashForm sashForm = new SashForm(sashForm_3, SWT.VERTICAL);
		managedForm.getToolkit().adapt(sashForm);
		managedForm.getToolkit().paintBordersFor(sashForm);

		Section sctnColorPalette = managedForm.getToolkit().createSection(
				sashForm, Section.TITLE_BAR);
		managedForm.getToolkit().paintBordersFor(sctnColorPalette);
		sctnColorPalette.setText("Color Palette");
		sctnColorPalette.setExpanded(true);

		SashForm sashForm_1 = new SashForm(sctnColorPalette, SWT.NONE);
		managedForm.getToolkit().adapt(sashForm_1);
		managedForm.getToolkit().paintBordersFor(sashForm_1);
		sctnColorPalette.setClient(sashForm_1);

		Composite composite = managedForm.getToolkit().createComposite(
				sashForm_1, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite);
		composite.setLayout(new GridLayout(2, false));

		palettePanel = new PalettePanel(composite, SWT.NONE);
		palettePanel.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_CROSS));
		palettePanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		managedForm.getToolkit().paintBordersFor(palettePanel);

		Group grpPaletteMetadata = new Group(composite, SWT.NONE);
		grpPaletteMetadata.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				false, true, 1, 1));
		grpPaletteMetadata.setLayout(new GridLayout(2, false));
		grpPaletteMetadata.setText("Palette Metadata");
		managedForm.getToolkit().adapt(grpPaletteMetadata);
		managedForm.getToolkit().paintBordersFor(grpPaletteMetadata);

		Label lblPaletteName = managedForm.getToolkit().createLabel(
				grpPaletteMetadata, "Palette name:", SWT.NONE);

		Label lblPalettename = managedForm.getToolkit().createLabel(
				grpPaletteMetadata, "", SWT.NONE);
		lblPalettename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		lblPalNameValue = managedForm.getToolkit().createLabel(
				grpPaletteMetadata, title, SWT.NONE);
		new Label(grpPaletteMetadata, SWT.NONE);

		Label lblNumberOfColors = managedForm.getToolkit().createLabel(
				grpPaletteMetadata, "Number of colors:", SWT.NONE);

		Label lblNumberofcolors = managedForm.getToolkit().createLabel(
				grpPaletteMetadata, "", SWT.NONE);
		lblNumberofcolors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		lblNumOfColorsValue = managedForm.getToolkit().createLabel(
				grpPaletteMetadata, "256", SWT.NONE);
		new Label(grpPaletteMetadata, SWT.NONE);

		Composite composite_7 = managedForm.getToolkit().createComposite(
				composite, SWT.NONE);
		GridLayout gl_composite_7 = new GridLayout(5, false);
		gl_composite_7.marginWidth = 0;
		gl_composite_7.marginHeight = 0;
		composite_7.setLayout(gl_composite_7);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 2, 1));
		managedForm.getToolkit().paintBordersFor(composite_7);
		lblAddColor1 = managedForm.getToolkit().createLabel(composite_7,
				"New Label", SWT.NONE);
		lblAddColor1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblAddColor1.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.paletteedit",
				"graphics/icons/16/plus.png"));

		lblAddColor2 = managedForm.getToolkit().createLabel(composite_7,
				"add new color", SWT.NONE);
		lblAddColor2.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		new Label(composite_7, SWT.NONE);

		lblRemoveColor1 = managedForm.getToolkit().createLabel(composite_7,
				"New Label", SWT.NONE);
		lblRemoveColor1
				.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblRemoveColor1.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.paletteedit",
				"graphics/icons/16/delete.png"));

		lblRemoveColor2 = managedForm.getToolkit().createLabel(composite_7,
				"remove selected color", SWT.NONE);
		lblRemoveColor2
				.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		Section sctnSelectedColor = managedForm.getToolkit().createSection(
				composite, Section.TITLE_BAR);
		sctnSelectedColor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 2, 1));
		managedForm.getToolkit().paintBordersFor(sctnSelectedColor);
		sctnSelectedColor.setText("Selected Color");

		Composite composite_1 = managedForm.getToolkit().createComposite(
				sctnSelectedColor, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite_1);
		sctnSelectedColor.setClient(composite_1);
		composite_1.setLayout(new GridLayout(5, false));

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(3, false);
		gl_composite_2.verticalSpacing = 0;
		gl_composite_2.horizontalSpacing = 0;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 3));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		managedForm.getToolkit().adapt(composite_2);
		managedForm.getToolkit().paintBordersFor(composite_2);

		btnPreviousColor = new Button(composite_2, SWT.NONE);
		btnPreviousColor.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
				false, 1, 1));
		btnPreviousColor.setSize(20, 73);
		managedForm.getToolkit().adapt(btnPreviousColor, true, true);
		btnPreviousColor.setText("<");

		selectedColorPanel = new SingleColorSelectionPanel(composite_2,
				SWT.BORDER);
		selectedColorPanel.setBounds(0, 0, 64, 64);
		managedForm.getToolkit().adapt(selectedColorPanel);
		managedForm.getToolkit().paintBordersFor(selectedColorPanel);

		btnNextColor = new Button(composite_2, SWT.NONE);
		btnNextColor.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
				false, 1, 1));
		managedForm.getToolkit().adapt(btnNextColor, true, true);
		btnNextColor.setText(">");
		new Label(composite_1, SWT.NONE);

		Label lblRed = new Label(composite_1, SWT.NONE);
		managedForm.getToolkit().adapt(lblRed, true, true);
		lblRed.setText("Red");

		slider_red = new Slider(composite_1, SWT.NONE);
		slider_red.setPageIncrement(1);
		slider_red.setThumb(1);
		slider_red.setMaximum(32);
		managedForm.getToolkit().adapt(slider_red, true, true);

		lblRedvalue = new Label(composite_1, SWT.NONE);
		GridData gd_lblRedvalue = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_lblRedvalue.widthHint = 30;
		lblRedvalue.setLayoutData(gd_lblRedvalue);
		managedForm.getToolkit().adapt(lblRedvalue, true, true);
		lblRedvalue.setText("248");
		new Label(composite_1, SWT.NONE);

		Label lblGreen = new Label(composite_1, SWT.NONE);
		managedForm.getToolkit().adapt(lblGreen, true, true);
		lblGreen.setText("Green");

		slider_green = new Slider(composite_1, SWT.NONE);
		slider_green.setPageIncrement(1);
		slider_green.setThumb(1);
		slider_green.setMaximum(32);
		managedForm.getToolkit().adapt(slider_green, true, true);

		lblGreenvalue = new Label(composite_1, SWT.NONE);
		GridData gd_lblGreenvalue = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_lblGreenvalue.widthHint = 30;
		lblGreenvalue.setLayoutData(gd_lblGreenvalue);
		managedForm.getToolkit().adapt(lblGreenvalue, true, true);
		lblGreenvalue.setText("248");
		new Label(composite_1, SWT.NONE);

		Label lblBlue = new Label(composite_1, SWT.NONE);
		managedForm.getToolkit().adapt(lblBlue, true, true);
		lblBlue.setText("Blue");

		slider_blue = new Slider(composite_1, SWT.NONE);
		slider_blue.setThumb(1);
		slider_blue.setPageIncrement(1);
		slider_blue.setMaximum(32);
		managedForm.getToolkit().adapt(slider_blue, true, true);

		lblBluevalue = new Label(composite_1, SWT.NONE);
		GridData gd_lblBluevalue = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_lblBluevalue.widthHint = 30;
		lblBluevalue.setLayoutData(gd_lblBluevalue);
		managedForm.getToolkit().adapt(lblBluevalue, true, true);
		lblBluevalue.setText("248");
		sashForm_1.setWeights(new int[] { 1 });

		Section sctnColorConfiguration = managedForm.getToolkit()
				.createSection(sashForm, Section.TITLE_BAR);
		managedForm.getToolkit().paintBordersFor(sctnColorConfiguration);
		sctnColorConfiguration.setText("Color Picker");

		Composite composite_6 = managedForm.getToolkit().createComposite(
				sctnColorConfiguration, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite_6);
		sctnColorConfiguration.setClient(composite_6);
		composite_6.setLayout(new GridLayout(1, false));

		colorpicker = new ColorSpectrumBrowser(composite_6, SWT.NONE);
		colorpicker.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_CROSS));
		colorpicker.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		managedForm.getToolkit().paintBordersFor(colorpicker);
		sashForm.setWeights(new int[] { 339, 245 });

		Section sctnNewSection = managedForm.getToolkit().createSection(
				sashForm_3, Section.TWISTIE | Section.TITLE_BAR);
		managedForm.getToolkit().paintBordersFor(sctnNewSection);
		sctnNewSection.setText("Associated Tiles");
		sctnNewSection.setExpanded(true);

		Composite composite_4 = managedForm.getToolkit().createComposite(
				sctnNewSection, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite_4);
		sctnNewSection.setClient(composite_4);
		composite_4.setLayout(new GridLayout(1, false));

		Label lblTilesPreview = managedForm.getToolkit().createLabel(
				composite_4, "Tiles Preview:", SWT.NONE);

		Composite composite_5 = managedForm.getToolkit().createComposite(
				composite_4, SWT.BORDER);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		managedForm.getToolkit().paintBordersFor(composite_5);

		Label lblTilesSelection = managedForm.getToolkit().createLabel(
				composite_4, "Tiles Selection:", SWT.NONE);

		TreeViewer treeViewer = new TreeViewer(composite_4, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().paintBordersFor(tree);
		sashForm_3.setWeights(new int[] { 586, 495 });
		addListeners();
		m_bindingContext = initDataBindings();
	}

	/**
	 * adds listeners to widgets etc.
	 */
	private void addListeners()
	{
		// react on clicks on the palette panel
		palettePanel.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// select the new color index
				int clickedColorIndex = palettePanel.getClickedColorIndex(e.x,
						e.y);
				SelectColorWithIndexCommand cmd = (SelectColorWithIndexCommand) CommandFactory
						.create(SelectColorWithIndexCommand.class,
								new CommandParameter(model, clickedColorIndex));
				editingDomain.getCommandStack().execute(cmd);
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
			}

			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
			}
		});
		palettePanel.addMouseMoveListener(new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		});

		// react on clicks on button "previous color"
		btnPreviousColor.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				if (model != null)
				{
					// select the new color index
					int clickedColorIndex = model.getLeftSelectedIndex();
					if (clickedColorIndex > 0)
					{
						clickedColorIndex--;
					}
					else
					{
						clickedColorIndex = model.getColors().size() - 1;
					}
					SelectColorWithIndexCommand cmd = (SelectColorWithIndexCommand) CommandFactory
							.create(SelectColorWithIndexCommand.class,
									new CommandParameter(model,
											clickedColorIndex));
					editingDomain.getCommandStack().execute(cmd);
				}
			}
		});

		// react on clicks on button "next color"
		btnNextColor.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				if (model != null)
				{
					int clickedColorIndex = model.getLeftSelectedIndex();
					if (model.getLeftSelectedIndex() < model.getColors().size() - 1)
					{
						clickedColorIndex++;
					}
					else
					{
						clickedColorIndex = 0;
					}
					SelectColorWithIndexCommand cmd = (SelectColorWithIndexCommand) CommandFactory
							.create(SelectColorWithIndexCommand.class,
									new CommandParameter(model,
											clickedColorIndex));
					editingDomain.getCommandStack().execute(cmd);
				}
			}
		});

		// add new color functionality
		MouseListener addColorListener = new MouseListener()
		{

			@Override
			public void mouseUp(MouseEvent e)
			{
				AddColorToPaletteCommand cmd = (AddColorToPaletteCommand) CommandFactory
						.create(AddColorToPaletteCommand.class,
								new CommandParameter(model));
				editingDomain.getCommandStack().execute(cmd);
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
		lblAddColor1.addMouseListener(addColorListener);
		lblAddColor2.addMouseListener(addColorListener);

		// remove color functionality
		MouseListener removeColorListener = new MouseListener()
		{

			@Override
			public void mouseUp(MouseEvent e)
			{
				RemoveColorFromPaletteCommand cmd = (RemoveColorFromPaletteCommand) CommandFactory
						.create(RemoveColorFromPaletteCommand.class,
								new CommandParameter(model));
				editingDomain.getCommandStack().execute(cmd);
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
		lblRemoveColor1.addMouseListener(removeColorListener);
		lblRemoveColor2.addMouseListener(removeColorListener);

		// react on color picker selection event
		MouseListener colorPickMouseListener = new MouseListener()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				int[] clickedColorIndex = colorpicker.getClickedColorIndex(e.x,
						e.y);
				colorpicker.setSelectedColor(clickedColorIndex[0],
						clickedColorIndex[1]);
				NonNativeColor selectedColor = colorpicker.getSelectedColor();
				Integer r = selectedColor.getR();
				Integer g = selectedColor.getG();
				Integer b = selectedColor.getB();
				ChangeCommand command = CommandFactory.create(
						ChangeRGBFromSelectedColorCommand.class,
						new CommandParameter(model, model
								.getLeftSelectedColor(), r, g, b));
				editingDomain.getCommandStack().execute(command);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
		MouseMoveListener colorPickMoveListener = new MouseMoveListener()
		{

			@Override
			public void mouseMove(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
		colorpicker.addMouseListener(colorPickMouseListener);
		colorpicker.addMouseMoveListener(colorPickMoveListener);

		// RGB slider change events
		slider_red.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Integer r = slider_red.getSelection();
				Integer g = model.getLeftSelectedColor().getG();
				Integer b = model.getLeftSelectedColor().getB();
				ChangeCommand command = CommandFactory.create(
						ChangeRGBFromSelectedColorCommand.class,
						new CommandParameter(model, model
								.getLeftSelectedColor(), r, g, b));
				editingDomain.getCommandStack().execute(command);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub

			}
		});
		slider_green.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Integer r = model.getLeftSelectedColor().getR();
				Integer g = slider_green.getSelection();
				Integer b = model.getLeftSelectedColor().getB();
				ChangeCommand command = CommandFactory.create(
						ChangeRGBFromSelectedColorCommand.class,
						new CommandParameter(model, model
								.getLeftSelectedColor(), r, g, b));
				editingDomain.getCommandStack().execute(command);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub

			}
		});
		slider_blue.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Integer r = model.getLeftSelectedColor().getR();
				Integer g = model.getLeftSelectedColor().getG();
				Integer b = slider_blue.getSelection();
				ChangeCommand command = CommandFactory.create(
						ChangeRGBFromSelectedColorCommand.class,
						new CommandParameter(model, model
								.getLeftSelectedColor(), r, g, b));
				editingDomain.getCommandStack().execute(command);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	protected DataBindingContext initDataBindings()
	{
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue colorsPalettePanelObserveValue = PojoProperties.value(
				"colors").observe(palettePanel);
		IObservableValue modelColorsObserveValue = EMFEditObservables
				.observeValue(editingDomain, model, Literals.PALETTE__COLORS);
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setConverter(new EmfColorsToNonNativeColorsConverter());
		bindingContext.bindValue(colorsPalettePanelObserveValue,
				modelColorsObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy);
		//
		IObservableValue leftSelectionIndexPalettePanelObserveValue = PojoProperties
				.value("leftSelectionIndex").observe(palettePanel);
		IObservableValue modelLeftSelectedIndexObserveValue = EMFEditObservables
				.observeValue(editingDomain, model,
						Literals.PALETTE__LEFT_SELECTED_INDEX);
		UpdateValueStrategy strategy_2 = new UpdateValueStrategy(
				UpdateValueStrategy.POLICY_NEVER);
		UpdateValueStrategy strategy_3 = new UpdateValueStrategy();
		bindingContext.bindValue(leftSelectionIndexPalettePanelObserveValue,
				modelLeftSelectedIndexObserveValue, strategy_2, strategy_3);
		//
		IObservableValue colorSelectedColorPanelObserveValue = PojoProperties
				.value("color").observe(selectedColorPanel);
		IObservableValue modelLeftSelectedColorObserveValue = EMFEditObservables
				.observeValue(editingDomain, model,
						Literals.PALETTE__LEFT_SELECTED_COLOR);
		UpdateValueStrategy strategy_1 = new UpdateValueStrategy();
		strategy_1.setConverter(new EmfColorToNonNativeColorConverter());
		bindingContext.bindValue(colorSelectedColorPanelObserveValue,
				modelLeftSelectedColorObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy_1);
		//
		IObservableValue iSelectedColorPanelObserveValue = PojoProperties
				.value("i").observe(selectedColorPanel);
		UpdateValueStrategy strategy_6 = new UpdateValueStrategy();
		bindingContext.bindValue(iSelectedColorPanelObserveValue,
				modelLeftSelectedIndexObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy_6);
		//
		IObservableValue selectionSlider_redObserveValue = PojoProperties
				.value("selection").observe(slider_red);
		IObservableValue modelRObserveValue = EMFEditProperties.value(
				editingDomain,
				FeaturePath.fromList(Literals.PALETTE__LEFT_SELECTED_COLOR,
						Literals.COLOR__R)).observe(model);
		bindingContext.bindValue(selectionSlider_redObserveValue,
				modelRObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue selectionSlider_greenObserveValue = PojoProperties
				.value("selection").observe(slider_green);
		IObservableValue modelGObserveValue = EMFEditProperties.value(
				editingDomain,
				FeaturePath.fromList(Literals.PALETTE__LEFT_SELECTED_COLOR,
						Literals.COLOR__G)).observe(model);
		bindingContext.bindValue(selectionSlider_greenObserveValue,
				modelGObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue selectionSlider_blueObserveValue = PojoProperties
				.value("selection").observe(slider_blue);
		IObservableValue modelBObserveValue = EMFEditProperties.value(
				editingDomain,
				FeaturePath.fromList(Literals.PALETTE__LEFT_SELECTED_COLOR,
						Literals.COLOR__B)).observe(model);
		bindingContext.bindValue(selectionSlider_blueObserveValue,
				modelBObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue rightSelectionIndexPalettePanelObserveValue = PojoProperties
				.value("rightSelectionIndex").observe(palettePanel);
		bindingContext.bindValue(rightSelectionIndexPalettePanelObserveValue,
				modelLeftSelectedIndexObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue observeTextLblNumOfColorsValueObserveWidget = WidgetProperties
				.text().observe(lblNumOfColorsValue);
		UpdateValueStrategy strategy_4 = new UpdateValueStrategy();
		strategy_4.setConverter(new EmfColorsToStringConverter());
		bindingContext.bindValue(observeTextLblNumOfColorsValueObserveWidget,
				modelColorsObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy_4);
		//
		IObservableValue observeTextLblRedvalueObserveWidget = WidgetProperties
				.text().observe(lblRedvalue);
		UpdateValueStrategy strategy_5 = new UpdateValueStrategy();
		strategy_5.setConverter(new IntToStringConverter());
		bindingContext.bindValue(observeTextLblRedvalueObserveWidget,
				modelRObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy_5);
		//
		IObservableValue observeTextLblGreenvalueObserveWidget = WidgetProperties
				.text().observe(lblGreenvalue);
		UpdateValueStrategy strategy_7 = new UpdateValueStrategy();
		strategy_7.setConverter(new IntToStringConverter());
		bindingContext.bindValue(observeTextLblGreenvalueObserveWidget,
				modelGObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy_7);
		//
		IObservableValue observeTextLblBluevalueObserveWidget = WidgetProperties
				.text().observe(lblBluevalue);
		UpdateValueStrategy strategy_8 = new UpdateValueStrategy();
		strategy_8.setConverter(new IntToStringConverter());
		bindingContext.bindValue(observeTextLblBluevalueObserveWidget,
				modelBObserveValue, new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), strategy_8);
		//
		return bindingContext;
	}
}
