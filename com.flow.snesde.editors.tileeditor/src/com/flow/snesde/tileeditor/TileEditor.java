/**
 * 
 */
package com.flow.snesde.tileeditor;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.flow.snesde.core.model.objects.PaletteObject;
import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.tileeditor.commands.ChangePreferredPaletteCommand;
import com.flow.snesde.tileeditor.commands.ChangeSelectedToolCommand;
import com.flow.snesde.tileeditor.commands.CommandFactory;
import com.flow.snesde.tileeditor.commands.CommandParameter;
import com.flow.snesde.tileeditor.commands.CutMarkedPixelsCommand;
import com.flow.snesde.tileeditor.commands.DrawEllipseCommand;
import com.flow.snesde.tileeditor.commands.DrawFillCommand;
import com.flow.snesde.tileeditor.commands.DrawLineCommand;
import com.flow.snesde.tileeditor.commands.DrawPencilLineCommand;
import com.flow.snesde.tileeditor.commands.DrawRectangleCommand;
import com.flow.snesde.tileeditor.commands.MarkAreaCommand;
import com.flow.snesde.tileeditor.commands.MoveMarkedAreaCommand;
import com.flow.snesde.tileeditor.commands.PasteCopiedPixelsCommand;
import com.flow.snesde.tileeditor.commands.ResizeMarkedPixelsCommand;
import com.flow.snesde.tileeditor.commands.SetLeftSelectedColorCommand;
import com.flow.snesde.tileeditor.commands.SetRightSelectedColorCommand;
import com.flow.snesde.tileeditor.commands.SetZoomFactorCommand;
import com.flow.snesde.tileeditor.commands.UnmarkAreaCommand;
import com.flow.snesde.tileeditor.converters.ComposedPaletteIDToNonNativeColorArrayConverter;
import com.flow.snesde.tileeditor.converters.ComposedPaletteNameToSimplePaletteNameConverter;
import com.flow.snesde.tileeditor.converters.DrawingModeToToolSelectionBarConverter;
import com.flow.snesde.tileeditor.converters.EmfTilesToInternalTilesConverter;
import com.flow.snesde.tileeditor.converters.GlobalPixelsToPermanentBufferConverter;
import com.flow.snesde.tileeditor.ui.ImageTransferable;
import com.flow.snesde.tileeditor.ui.PaletteSelectionDialog;
import com.flow.snesde.tileeditor.ui.TwoColumnToolbar;
import com.flow.snesde.uilib.doubleColorSelectionPanel.DoubleColorSelectionPanel;
import com.flow.snesde.uilib.generic.InternalPixel;
import com.flow.snesde.uilib.generic.NonNativeColor;
import com.flow.snesde.uilib.palettePanel.PalettePanel;
import com.flow.snesde.uilib.tilesetPanel.TilesetPanel;

import palette.impl.PaletteImpl;
import tileset.Encoding;
import tileset.Tileset;
import tileset.TilesetPackage.Literals;
import tileset.ToolSelection;
import tileset.impl.TilesetImpl;

/**
 * @author flo
 * 
 *         This is the new tileset editor based on eclipse and emf. it uses emf
 *         to enable undo/redo stuff and should be much cleaner than the
 *         previous one in DSNESDS v2.
 */
public class TileEditor extends EditorPart
        implements IResourceChangeListener, IEditingDomainProvider, ISelectionProvider
{
	// private DataBindingContext m_bindingContext;

	public static final String ID = "com.flow.snesde.tileeditor.TileEditor"; //$NON-NLS-1$

	/**
	 * This holds the command stack etc for EMF undoable commands
	 */
	protected AdapterFactoryEditingDomain editingDomain;

	/**
	 * This is the model for the editor
	 */
	private Tileset model;

	/**
	 * The IResource read by the file
	 */
	private IFile inputFile = null;

	// component variables for file access
	private TilesetPanel tilesetPanel;
	private Spinner spnZoom;
	private Label lblZoomin;
	private Label lblZoomout;
	private Label lblZoomorig;
	private Button btnShowGrid;
	private Spinner spnColumns;
	private PalettePanel palettePanel;
	private DoubleColorSelectionPanel selectedColors;
	private Button btnChangePalette;
	private Text txtPaletteName;
	private TwoColumnToolbar tbToolSelection;
	private ScrolledComposite tilesetScroller;
	private StackLayout layoutToolOptions;
	private Composite cmpPencilOptions;
	private Composite cmpLineOptions;
	private Composite cmpMarkOptions;
	private Composite cmpWizardOptions;
	private Composite cmpPipetteOptions;
	private Composite cmpMagnifierOptions;
	private Composite cmpRectangleOptions;
	private Composite cmpEllipseOptions;
	private Composite cmpEnumerationOptions;
	private Composite cmpTextOptions;
	private Composite cmpFillOptions;
	private Composite cmpPalettetoolOptions;
	private Composite cmpToolOptions;
	private Text text;
	private Text txtHereGoesSome;
	private Text txtSomeVeryImportant;
	private ToolItem tltmTranslucentBG_mark;
	private ToolItem tltmSolidBG_mark;
	private ToolItem tltmRound_Pencil;
	private ToolItem tltmRect_Pencil;
	private Spinner spnSize_Pencil;
	private ToolItem tltmSolid_Pencil;
	private ToolItem tltmBorder_Pencil;
	private ToolItem tltmRound_Line;
	private ToolItem tltmSquare_Line;
	private Spinner spnSize_Line;
	private ToolItem tltmSolid_Line;
	private ToolItem tltmBorder_Line;
	private ToolItem tltm_multiTile_wizard;
	private ToolItem tltm_singleTile_wizard;
	private ToolItem tltm_ignoreBorders_fill;
	private ToolItem tltm_singleTile_fill;
	private ToolItem tltm_singleColor_fill;
	private ToolItem tltm_horiz_fill;
	private ToolItem tltm_vertic_fill;
	private ToolItem tltm_diagone_fill;
	private ToolItem tltm_diagtwo_fill;
	private ToolItem tltmOutline_rect;
	private ToolItem tltmFilled_rect;
	private ToolItem tltmTwoColors_rect;
	private ToolItem tltmTransition_rect;
	private ToolItem tltm_horizfade_rect;
	private ToolItem tltm_verticfade_rect;
	private ToolItem tltm_diagfadeone_rect;
	private ToolItem tltm_diagfadetwo_rect;
	private ToolItem tltm_outline_ellipse;
	private ToolItem tltm_filled_ellipse;
	private ToolItem tltm_twoColors_ellipse;
	private ToolItem tltm_transition_ellipse;
	private ToolItem tltm_horizfade_ellipse;
	private ToolItem tltm_verticfade_ellipse;
	private ToolItem tltm_diagfadeone_ellipse;
	private ToolItem tltm_diagfadetwo_ellipse;
	private ToolItem tltm_markModeRectangular_Enum;
	private ToolItem tltm_markModeContinuous_Enum;
	private Combo cmb_base_Enum;
	private ToolItem tltm_variableWidth;
	private ToolItem tltm_fixedWidth;
	private ToolItem tltm_LeftAligned;
	private ToolItem tltm_CenterAlignment;
	private ToolItem tltm_RightAligned;
	private Button btnWrap;

	// internal variables for model and current data stuff
	private String preferredPaletteCombinedId;
	private HashSet<String> availablePalettes;

	// internal status variables mostly to manage paint functionality for the
	// tools
	private boolean leftDown = false;
	private boolean rightDown = false;
	private boolean mouseMovedBetweenDownAndUp = false;
	private int startGlobalCol, startGlobalRow, endGlobalCol, endGlobalRow, tempGlobalCol, tempGlobalRow;
	private int startTileCol, startTileRow, endTileCol, endTileRow, tempTileCol, tempTileRow;
	private int startTileNumber, endTileNumber, tempTileNumber;
	private int startClickX, endClickX, tempClickX, popupClickX;
	private int startClickY, endClickY, tempClickY, popupClickY;
	// indicating that pixels are currently being moved
	private boolean markedRectBeingMoved = false;
	// indicating that pixels are being resized
	private boolean markedRectBeingResized = false;
	// indicating the nob which was grabbed (0=tl, 1=tm, 2=tr, 3=lm, 4=rm, 5=bl,
	// 6=bm, 7=br)
	private boolean tlNobClicked;
	private boolean tmNobClicked;
	private boolean trNobClicked;
	private boolean mlNobClicked;
	private boolean mrNobClicked;
	private boolean blNobClicked;
	private boolean bmNobClicked;
	private boolean brNobClicked;
	private boolean noNobClicked;
	private boolean clickedOnPermBuffer;
	private int clickedNob = -1;
	private boolean horizResize = false;
	private boolean verticResize = false;
	// total delta for drag operations
	private int totalDeltaCol = 0;
	private int totalDeltaRow = 0;

	// named databindings which need to be refreshed when undo was triggered
	private Binding tilesetBinding;
	private Binding permanentBufferBinding;

	// listeners for specific tools
	// listeners for pencil tool
	private MouseAdapter pencilMouseAdapter;
	private MouseMoveListener pencilMouseMoveListener;
	// listeners for line tool
	private MouseAdapter lineMouseAdapter;
	private MouseMoveListener lineMouseMoveListener;
	// listeners for rectangle tool
	private MouseAdapter rectangleMouseAdapter;
	private MouseMoveListener rectangleMouseMoveListener;
	// listeners for ellipse tool
	private MouseAdapter ellipseMouseAdapter;
	private MouseMoveListener ellipseMouseMoveListener;
	// listener for pipette tool
	private MouseAdapter pipetteMouseAdapter;
	// listeners for magnifier tool
	private MouseAdapter magnifierMouseAdapter;
	private MouseMoveListener magnifierMouseMoveListener;
	// listener for fill tool
	private MouseAdapter fillMouseAdapter;
	// listeners for mark tool
	private MouseAdapter markMouseAdapter;
	private MouseMoveListener markMouseMoveListener;

	private DataBindingContext bindingContext;
	private Composite cmpSprayOptions;
	private Label lblDitheringMode;
	private Label lblShape_2;
	private Label lblSize_2;
	private ToolBar toolBar_11;
	private ToolItem tltmCircle_spray;
	private ToolItem tltmRect_spray;
	private Spinner spnSize_spray;

	private ToolItem tltmRandomMode;

	private ToolItem tltmPatternMode;

	private Menu menu;
	private Button btnCharacterData;

	/**
	 * constructor
	 */
	public TileEditor()
	{
		super();
		// init model stuff, commandstack, etc
		initializeEditingDomain();
		// instantiate the tool specific listeners which are only assigned to
		// the tilesetpanel once a given tool is selected
		createToolListeners();
	}

	private void initializeEditingDomain()
	{
		// Don't really know what all this does but it's needed anyways
		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		ComposedAdapterFactory adapterFactory1 = new ComposedAdapterFactory(
		        ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory1.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory1.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		factories.add(adapterFactory1);
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(factories);

		// Create the command stack that will notify this editor as commands are
		// executed.
		final BasicCommandStack commandStack = new BasicCommandStack();

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		commandStack.addCommandStackListener(new CommandStackListener()
		{
			@Override
			public void commandStackChanged(final EventObject event)
			{
				Display.getDefault().asyncExec(new Runnable()
		        {

			        @Override
			        public void run()
			        {
				        firePropertyChange(IEditorPart.PROP_DIRTY);

				        // as the databinding for the tileset data only
		                // automatically refreshes upon complete reset of the
		                // tile content (and not upon single pixel changes which
		                // are usually triggered within the editor),
		                // we'll have to trigger the update manually once a
		                // command was executed or undone. The following line
		                // accomplishes this
				        tilesetBinding.updateModelToTarget();
				        // do the same with the permanent buffer binding
				        permanentBufferBinding.updateModelToTarget();
				        // if last undone command was unmark -> show permanent
		                // buffer again
				        if (commandStack.getRedoCommand() instanceof UnmarkAreaCommand)
				        {
					        tilesetPanel.setShowPermanentBuffer(true);
				        }
				        // tilesetPanel.setShowPermanentBuffer(true);
		                // in case a tool selection was changed we'll want to
		                // make sure the proper listeners are attached to the
		                // tilesetPanel
				        updateToolListenersOnTilesetPanel(model.getTool().getValue());
				        // make sure the tool options panel shows the ones for
		                // the proper tool
				        updateToolOptionsComposite(model.getTool().getValue());
				        // make sure the proper cursor is used
				        updateCursorOnTileset(model.getTool().getValue());
				        // make sure the proper temp drawings are used
				        updateTempDrawingOnTileset(model.getTool().getValue());
			        }
		        });
			}
		});

		// Create the editing domain with a special command stack.
		//
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
		// see
		// http://milesparker.blogspot.de/2011/01/supporting-multiple-resource-types-with.html
		// don't forget to add the extension to plugin.properties
		// editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("meta",
		// new ProjectMetaResourceFactoryImpl());
	}

	/**
	 * creates the listeners which are added to/removed from the tilesetPanel
	 * depending on tool selection
	 */
	private void createToolListeners()
	{
		// mouse listeners for pencil tool
		pencilMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				if ((e.stateMask & SWT.CTRL) != 0)
				{
					storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					int color = model.getTiles().get(startTileNumber).getPixelColumns().get(startTileCol).getPixels()
		                    .get(startTileRow).getColorIndex();
					if (e.button == 1)// left
					{
						ChangeCommand command = CommandFactory.create(SetLeftSelectedColorCommand.class,
		                        new CommandParameter(model, color));
						editingDomain.getCommandStack().execute(command);
					}
					else if (e.button == 3) // right
					{
						ChangeCommand command = CommandFactory.create(SetRightSelectedColorCommand.class,
		                        new CommandParameter(model, color));
						editingDomain.getCommandStack().execute(command);
					}
				}
				else
				{
					Integer leftColor = model.getLeftIndex();
					Integer rightColor = model.getRightIndex();
					boolean oldLeftDown = leftDown;
					if (e.button == 1)// left
					{
						leftDown = false;
					}
					else if (e.button == 3) // right
					{
						rightDown = false;
					}
					// store end clicked values
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// get new pixels to be drawn
					Vector<InternalPixel> newPixels = DrawingLogic.drawLine(startGlobalCol, startGlobalRow,
		                    endGlobalCol, endGlobalRow, leftColor, rightColor, tltmRound_Pencil.getSelection(),
		                    spnSize_Pencil.getSelection(), tltmBorder_Pencil.getSelection(), oldLeftDown);
					// add them to the buffer
					tilesetPanel.addBufferedPixels(newPixels);
					// mouseup event -> obtain buffer and write it to the model
		            // obtain the current buffer
					Vector<InternalPixel> buffer = tilesetPanel.getPixelBuffer();
					// draw the buffer into the model
					ChangeCommand command = CommandFactory.create(DrawPencilLineCommand.class,
		                    new CommandParameter(model, buffer));
					editingDomain.getCommandStack().execute(command);
					// clear the buffer and disable its display
					tilesetPanel.clearPixelBuffer();
					tilesetPanel.setShowBufferedPixels(false);
				}
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				if ((e.stateMask & SWT.CTRL) == 0)
				{
					Integer leftColor = model.getLeftIndex();
					Integer rightColor = model.getRightIndex();
					if (e.button == 1)// left
					{
						leftDown = true;
					}
					else if (e.button == 3) // right
					{
						rightDown = true;
					}
					// store start indices
					storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					storeStartIndicesAsNewEndIndices();
					// display pixel buffer
					tilesetPanel.setShowBufferedPixels(true);
					// add first pixels to buffer
					Vector<InternalPixel> newPixels = DrawingLogic.drawLine(startGlobalCol, startGlobalRow,
		                    endGlobalCol, endGlobalRow, leftColor, rightColor, tltmRound_Pencil.getSelection(),
		                    spnSize_Pencil.getSelection(), tltmBorder_Pencil.getSelection(), leftDown);
					tilesetPanel.addBufferedPixels(newPixels);
				}
			}
		};

		pencilMouseMoveListener = new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				if ((e.stateMask & SWT.CTRL) == 0)
				{
					if (leftDown || rightDown)
					{
						Integer leftColor = model.getLeftIndex();
						Integer rightColor = model.getRightIndex();
						// store end indices
						storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
						// add new pixels to buffer
						Vector<InternalPixel> newPixels = DrawingLogic.drawLine(startGlobalCol, startGlobalRow,
		                        endGlobalCol, endGlobalRow, leftColor, rightColor, tltmRound_Pencil.getSelection(),
		                        spnSize_Pencil.getSelection(), tltmBorder_Pencil.getSelection(), leftDown);
						tilesetPanel.addBufferedPixels(newPixels);
						// old end becomes new start position for next event
						storeEndIndicesAsNewStartIndices();
					}
				}
			}
		};

		// mouse listeners for pencil tool
		lineMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				if ((e.stateMask & SWT.CTRL) != 0)
				{
					storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					int color = model.getTiles().get(startTileNumber).getPixelColumns().get(startTileCol).getPixels()
		                    .get(startTileRow).getColorIndex();
					if (e.button == 1)// left
					{
						ChangeCommand command = CommandFactory.create(SetLeftSelectedColorCommand.class,
		                        new CommandParameter(model, color));
						editingDomain.getCommandStack().execute(command);
					}
					else if (e.button == 3) // right
					{
						ChangeCommand command = CommandFactory.create(SetRightSelectedColorCommand.class,
		                        new CommandParameter(model, color));
						editingDomain.getCommandStack().execute(command);
					}
				}
				else
				{
					Integer leftColor = model.getLeftIndex();
					Integer rightColor = model.getRightIndex();
					boolean oldLeftDown = leftDown;
					if (e.button == 1)// left
					{
						leftDown = false;
					}
					else if (e.button == 3) // right
					{
						rightDown = false;
					}
					// store end clicked values
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// clear pixel buffer
					tilesetPanel.clearPixelBuffer();
					// get new pixels to be drawn
					Vector<InternalPixel> newPixels = DrawingLogic.drawLine(startGlobalCol, startGlobalRow,
		                    endGlobalCol, endGlobalRow, leftColor, rightColor, tltmRound_Line.getSelection(),
		                    spnSize_Line.getSelection(), tltmBorder_Line.getSelection(), oldLeftDown);
					// add them to the buffer
					tilesetPanel.addBufferedPixels(newPixels);
					// mouseup event -> obtain buffer and write it to the model
		            // obtain the current buffer
					Vector<InternalPixel> buffer = tilesetPanel.getPixelBuffer();
					// draw the buffer into the model
					ChangeCommand command = CommandFactory.create(DrawLineCommand.class,
		                    new CommandParameter(model, buffer));
					editingDomain.getCommandStack().execute(command);
					// clear the buffer and disable its display
					tilesetPanel.clearPixelBuffer();
					tilesetPanel.setShowBufferedPixels(false);
				}
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				if ((e.stateMask & SWT.CTRL) == 0)
				{
					Integer leftColor = model.getLeftIndex();
					Integer rightColor = model.getRightIndex();
					if (e.button == 1)// left
					{
						leftDown = true;
					}
					else if (e.button == 3) // right
					{
						rightDown = true;
					}
					// store start indices
					storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					storeStartIndicesAsNewEndIndices();
					// display pixel buffer
					tilesetPanel.setShowBufferedPixels(true);
					// add first pixel to buffer
					Vector<InternalPixel> newPixels = DrawingLogic.drawLine(startGlobalCol, startGlobalRow,
		                    endGlobalCol, endGlobalRow, leftColor, rightColor, tltmRound_Pencil.getSelection(),
		                    spnSize_Pencil.getSelection(), tltmBorder_Pencil.getSelection(), leftDown);
					tilesetPanel.addBufferedPixels(newPixels);
				}
			}
		};

		lineMouseMoveListener = new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				if ((e.stateMask & SWT.CTRL) == 0)
				{
					if (leftDown || rightDown)
					{
						Integer leftColor = model.getLeftIndex();
						Integer rightColor = model.getRightIndex();
						// store end indices
						storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
						// clear old buffer
						tilesetPanel.clearPixelBuffer();
						// add new pixels to buffer
						Vector<InternalPixel> newPixels = DrawingLogic.drawLine(startGlobalCol, startGlobalRow,
		                        endGlobalCol, endGlobalRow, leftColor, rightColor, tltmRound_Line.getSelection(),
		                        spnSize_Line.getSelection(), tltmBorder_Line.getSelection(), leftDown);
						tilesetPanel.addBufferedPixels(newPixels);
					}
				}
			}
		};

		// mouse listeners for rectangle tool
		rectangleMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				Integer color = 0;
				if (e.button == 1)// left
				{
					leftDown = false;
					color = model.getLeftIndex();
				}
				else if (e.button == 3) // right
				{
					rightDown = false;
					color = model.getRightIndex();
				}
				// store end clicked values
				storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				// clear pixel buffer
				tilesetPanel.clearPixelBuffer();
				// get new pixels to be drawn
				Vector<InternalPixel> newPixels = DrawingLogic.drawRectangle(startGlobalCol, startGlobalRow,
		                endGlobalCol, endGlobalRow, color);
				// add them to the buffer
				tilesetPanel.addBufferedPixels(newPixels);
				// mouseup event -> obtain buffer and write it to the model
		        // obtain the current buffer
				Vector<InternalPixel> buffer = tilesetPanel.getPixelBuffer();
				// draw the buffer into the model
				ChangeCommand command = CommandFactory.create(DrawRectangleCommand.class,
		                new CommandParameter(model, buffer));
				editingDomain.getCommandStack().execute(command);
				// clear the buffer and disable its display
				tilesetPanel.clearPixelBuffer();
				tilesetPanel.setShowBufferedPixels(false);
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				Integer color = 0;
				if (e.button == 1)// left
				{
					leftDown = true;
					color = model.getLeftIndex();
				}
				else if (e.button == 3) // right
				{
					rightDown = true;
					color = model.getRightIndex();
				}
				// store start indices
				storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				// display pixel buffer
				tilesetPanel.setShowBufferedPixels(true);
				// add first pixel to buffer
				Vector<InternalPixel> newPixels = new Vector<InternalPixel>();
				newPixels.add(new InternalPixel(startGlobalCol, startGlobalRow, color));
				tilesetPanel.addBufferedPixels(newPixels);

			}
		};

		rectangleMouseMoveListener = new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				if (leftDown || rightDown)
				{
					Integer color = 0;
					if (leftDown)
					{
						color = model.getLeftIndex();
					}
					else if (rightDown)
					{
						color = model.getRightIndex();
					}
					// store end indices
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// clear old buffer
					tilesetPanel.clearPixelBuffer();
					// add new pixels to buffer
					Vector<InternalPixel> newPixels = DrawingLogic.drawRectangle(startGlobalCol, startGlobalRow,
		                    endGlobalCol, endGlobalRow, color);
					tilesetPanel.addBufferedPixels(newPixels);
				}
			}
		};

		// mouse listeners for ellipse tool
		ellipseMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				Integer color = 0;
				if (e.button == 1)// left
				{
					leftDown = false;
					color = model.getLeftIndex();
				}
				else if (e.button == 3) // right
				{
					rightDown = false;
					color = model.getRightIndex();
				}
				// store end clicked values
				storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				// clear pixel buffer
				tilesetPanel.clearPixelBuffer();
				// get new pixels to be drawn
				Vector<InternalPixel> newPixels = DrawingLogic.drawEllipse(startGlobalCol, startGlobalRow, endGlobalCol,
		                endGlobalRow, color);
				// add them to the buffer
				tilesetPanel.addBufferedPixels(newPixels);
				// mouseup event -> obtain buffer and write it to the model
		        // obtain the current buffer
				Vector<InternalPixel> buffer = tilesetPanel.getPixelBuffer();
				// draw the buffer into the model
				ChangeCommand command = CommandFactory.create(DrawEllipseCommand.class,
		                new CommandParameter(model, buffer));
				editingDomain.getCommandStack().execute(command);
				// clear the buffer and disable its display
				tilesetPanel.clearPixelBuffer();
				tilesetPanel.setShowBufferedPixels(false);
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				Integer color = 0;
				if (e.button == 1)// left
				{
					leftDown = true;
					color = model.getLeftIndex();
				}
				else if (e.button == 3) // right
				{
					rightDown = true;
					color = model.getRightIndex();
				}
				// store start indices
				storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				// display pixel buffer
				tilesetPanel.setShowBufferedPixels(true);
				// add first pixel to buffer
				Vector<InternalPixel> newPixels = new Vector<InternalPixel>();
				newPixels.add(new InternalPixel(startGlobalCol, startGlobalRow, color));
				tilesetPanel.addBufferedPixels(newPixels);

			}
		};

		ellipseMouseMoveListener = new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				if (leftDown || rightDown)
				{
					Integer color = 0;
					if (leftDown)
					{
						color = model.getLeftIndex();
					}
					else if (rightDown)
					{
						color = model.getRightIndex();
					}
					// store end indices
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// clear old buffer
					tilesetPanel.clearPixelBuffer();
					// add new pixels to buffer
					Vector<InternalPixel> newPixels = DrawingLogic.drawEllipse(startGlobalCol, startGlobalRow,
		                    endGlobalCol, endGlobalRow, color);
					tilesetPanel.addBufferedPixels(newPixels);
				}
			}
		};

		// mouse listeners for ellipse tool
		pipetteMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				int color = model.getTiles().get(startTileNumber).getPixelColumns().get(startTileCol).getPixels()
		                .get(startTileRow).getColorIndex();
				if (e.button == 1)// left
				{
					ChangeCommand command = CommandFactory.create(SetLeftSelectedColorCommand.class,
		                    new CommandParameter(model, color));
					editingDomain.getCommandStack().execute(command);
				}
				else if (e.button == 3) // right
				{
					ChangeCommand command = CommandFactory.create(SetRightSelectedColorCommand.class,
		                    new CommandParameter(model, color));
					editingDomain.getCommandStack().execute(command);
				}
			}
		};

		magnifierMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{

				if (!mouseMovedBetweenDownAndUp)
				{
					// simple click -> just zoom in/out depending on the click
		            // button
					int newZoom = model.getZoomFactor();
					if (e.button == 1)// left
					{
						newZoom++;
					}
					else if (e.button == 3) // right
					{
						newZoom--;
					}
					// set the new zoomfactor
					ChangeCommand command = CommandFactory.create(SetZoomFactorCommand.class,
		                    new CommandParameter(model, newZoom));
					editingDomain.getCommandStack().execute(command);

					// adjust scrollbar position
					int xcenter = e.x;
					int ycenter = e.y;
					int width = tilesetScroller.getClientArea().width;
					int height = tilesetScroller.getClientArea().height;
					int xorigin = xcenter - (int) ((double) width / (double) 2);
					int yorigin = ycenter - (int) ((double) height / (double) 2);
					// scroll to the new origin
					tilesetScroller.setOrigin(xorigin, yorigin);
				}
				else
				{
					// calculate new zoomfactor from the rectangle drawn
					int zoom = tilesetPanel.getZoomFactor();
					int lowerCol = startGlobalCol;
					int lowerRow = startGlobalRow;
					int higherCol = endGlobalCol;
					int higherRow = endGlobalRow;
					if (startGlobalCol > endGlobalCol)
					{
						lowerCol = endGlobalCol;
						higherCol = startGlobalCol;
					}
					if (startGlobalRow > endGlobalRow)
					{
						lowerRow = endGlobalRow;
						higherRow = startGlobalRow;
					}

					int colWidth = (higherCol - lowerCol);
					int rowHeight = (higherRow - lowerRow);
					int width = tilesetScroller.getClientArea().width;
					int height = tilesetScroller.getClientArea().height;

					int horizontalZoom = (int) Math.floor(width / colWidth);
					int verticalZoom = (int) Math.floor(height / rowHeight);

					int newZoom = horizontalZoom;
					if (verticalZoom < horizontalZoom)
					{
						newZoom = verticalZoom;
					}

					// set the new zoomfactor
					ChangeCommand command = CommandFactory.create(SetZoomFactorCommand.class,
		                    new CommandParameter(model, newZoom));
					editingDomain.getCommandStack().execute(command);

					tilesetPanel.setDrawMarkingRectangle(false);
					// scroll to the new origin
					tilesetScroller.setOrigin(lowerCol * zoom, lowerRow * zoom);
				}

				if (e.button == 1)
				{
					leftDown = false;
				}
				else if (e.button == 3)
				{
					rightDown = false;
				}
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				mouseMovedBetweenDownAndUp = false;
				storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				if (e.button == 1)// left
				{
					leftDown = true;
				}
				else if (e.button == 3) // right
				{
					rightDown = true;
				}

			}
		};

		magnifierMouseMoveListener = new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				if (leftDown || rightDown)
				{
					mouseMovedBetweenDownAndUp = true;
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					tilesetPanel.setDrawMarkingRectangle(true);
					tilesetPanel.setMarkingBounds(startGlobalCol - 1, startGlobalRow - 1, endGlobalCol - 1,
		                    endGlobalRow - 1);
				}
			}
		};

		fillMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				Integer colorToFillWith = 0;
				if (e.button == 1)// left
				{
					colorToFillWith = model.getLeftIndex();
				}
				else if (e.button == 3) // right
				{
					colorToFillWith = model.getRightIndex();
				}
				// store start clicked values
				storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				// only use valid clicks (in one of the existing tiles)
				if (startTileNumber != -2)
				{
					int colorToOverwrite = model.getTiles().get(startTileNumber).getPixelColumns().get(startTileCol)
		                    .getPixels().get(startTileRow).getColorIndex();
					int rest = (model.getTiles().size() % model.getColumns());
					if (rest != 0)
					{
						// use this variable to add one more column to the array
		                // of
		                // visitable tiles/pixels
						rest = 1;
					}
					// get new pixels to be drawn
					Vector<InternalPixel> newPixels = DrawingLogic.fillPixels(startGlobalCol, startGlobalRow, model,
		                    colorToOverwrite, colorToFillWith, new Vector<InternalPixel>(),
		                    new boolean[(model.getColumns() * 8)
		                            + 1][(((int) Math.ceil(model.getTiles().size() / model.getColumns()) + rest) * 8)
		                                    + 1]);
					// draw the buffer into the model
					ChangeCommand command = CommandFactory.create(DrawFillCommand.class,
		                    new CommandParameter(model, newPixels));
					editingDomain.getCommandStack().execute(command);
					// clear the buffer and disable its display
					tilesetPanel.clearPixelBuffer();
					tilesetPanel.setShowBufferedPixels(false);
				}
			}
		};

		markMouseAdapter = new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// step 1: gather all variables so we can decide which mode to
		        // go into --> which action will be triggered
		        // ==============================================================
		        // endIndices
		        // leftDown
		        // mouseMovedBetweenDownAndUp
				boolean permHasContent = true;
				if (model.getPermanentBuffer() == null || model.getPermanentBuffer().getBufferedPixels() == null
		                || model.getPermanentBuffer().getBufferedPixels().isEmpty())
				{
					permHasContent = false;
				}
				// rightDown
		        // startIndices
		        // tempIndices
				boolean drawMarkingRectangle = tilesetPanel.isDrawMarkingRectangle();
				// markedRectBeingMoved
				boolean showTempBuffer = tilesetPanel.isShowBufferedPixels();
				boolean showPermBuffer = tilesetPanel.isShowPermanentBuffer();
				boolean tempHidePermBuffer = tilesetPanel.isTemporarilyHidePermanentBuffer();
				int BUTTON_LEFT = 0;
				int BUTTON_RIGHT = 1;
				int BUTTON_MIDDLE = 2;
				int button = BUTTON_MIDDLE;
				if (e.button == 1)
				{
					button = BUTTON_LEFT;
				}
				else if (e.button == 3)
				{
					button = BUTTON_RIGHT;
				}
				boolean copyBG = true;
				if (tltmTranslucentBG_mark.getSelection())
				{
					copyBG = false;
				}
				boolean clickedOnPermBuffer = tilesetPanel.clickedOnPermanentBuffer(e.x, e.y);
				clickedNob = -1;
				boolean tlNobClicked = tilesetPanel.clickedOnTopLeftResizeNob(e.x, e.y);
				if (tlNobClicked) clickedNob = 0;
				boolean tmNobClicked = tilesetPanel.clickedOnTopMiddleResizeNob(e.x, e.y);
				if (tmNobClicked) clickedNob = 1;
				boolean trNobClicked = tilesetPanel.clickedOnTopRightResizeNob(e.x, e.y);
				if (trNobClicked) clickedNob = 2;
				boolean mlNobClicked = tilesetPanel.clickedOnMiddleLeftResizeNob(e.x, e.y);
				if (mlNobClicked) clickedNob = 3;
				boolean mrNobClicked = tilesetPanel.clickedOnMiddleRightResizeNob(e.x, e.y);
				if (mrNobClicked) clickedNob = 4;
				boolean blNobClicked = tilesetPanel.clickedOnBottomLeftResizeNob(e.x, e.y);
				if (blNobClicked) clickedNob = 5;
				boolean bmNobClicked = tilesetPanel.clickedOnBottomMiddleResizeNob(e.x, e.y);
				if (bmNobClicked) clickedNob = 6;
				boolean brNobClicked = tilesetPanel.clickedOnBottomRightResizeNob(e.x, e.y);
				if (brNobClicked) clickedNob = 7;
				boolean noNobClicked = false;
				if (clickedOnPermBuffer && !tlNobClicked && !tmNobClicked && !trNobClicked && !mlNobClicked
		                && !mrNobClicked && !blNobClicked && !bmNobClicked && !brNobClicked)
				{
					noNobClicked = true;
				}
				// if (!noNobClicked)
		        // {
		        // if (tmNobClicked || bmNobClicked)
		        // {
		        // horizResize = false;
		        // verticResize = true;
		        // }
		        // else if (mlNobClicked || mrNobClicked)
		        // {
		        // horizResize = true;
		        // verticResize = false;
		        // }
		        // else
		        // {
		        // horizResize = true;
		        // verticResize = true;
		        // }
		        // }

				// step 2: set mode -> decides what happens
		        // ==============================================================
				final int MODE_MARKING_FINISH = 0;
				final int MODE_MOVE_MARKEDPIXELS_FINISH = 1;
				final int MODE_RESIZE_MARKEDPIXELS_FINISH = 2;
				// set the mode
				int mode = -1;
				if (!permHasContent && mouseMovedBetweenDownAndUp)
				{
					System.out.println("MARKING_FINISH");
					mode = MODE_MARKING_FINISH;
				}
				else if (permHasContent && mouseMovedBetweenDownAndUp && markedRectBeingMoved)
				{
					System.out.println("MOVE_FINISH");
					mode = MODE_MOVE_MARKEDPIXELS_FINISH;
				}
				else if (permHasContent && mouseMovedBetweenDownAndUp && markedRectBeingResized)
				{
					System.out.println("RESIZE_FINISH");
					mode = MODE_RESIZE_MARKEDPIXELS_FINISH;
				}

				// step 3: react on mode -> trigger commands, store temp
		        // variables, etc.
		        // ==============================================================
				switch (mode)
				{
				case MODE_MARKING_FINISH:
				{
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					tilesetPanel.setDrawMarkingRectangle(false);
					ChangeCommand command = CommandFactory.create(MarkAreaCommand.class, new CommandParameter(model,
		                    model.getLeftIndex(), startGlobalCol, endGlobalCol, startGlobalRow, endGlobalRow, copyBG));
					editingDomain.getCommandStack().execute(command);
					tilesetPanel.setShowPermanentBuffer(true);
					tilesetPanel.setShowBufferedPixels(false);
					break;
				}
				case MODE_MOVE_MARKEDPIXELS_FINISH:
				{
					// get current mouse position
					storeEndIndicesAsNewTempIndices();
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// translate the temporary buffer to the current end
		            // position
					int deltaCol = endGlobalCol - tempGlobalCol;
					int deltaRow = endGlobalRow - tempGlobalRow;
					tilesetPanel.moveTempBuffer(deltaCol, deltaRow);

					// translate the persistent buffer for the whole
		            // delta
					deltaCol = endGlobalCol - startGlobalCol;
					deltaRow = endGlobalRow - startGlobalRow;
					ChangeCommand command = CommandFactory.create(MoveMarkedAreaCommand.class,
		                    new CommandParameter(model, deltaCol, deltaRow));
					editingDomain.getCommandStack().execute(command);
					// update display
		            // bindingContext.removeBinding(permanentBufferBinding);
		            // bindingContext.addBinding(permanentBufferBinding);
					permanentBufferBinding.updateModelToTarget();
					markedRectBeingMoved = false;
					tilesetPanel.setShowBufferedPixels(false);
					tilesetPanel.setTemporarilyHidePermanentBuffer(false);
					break;
				}
				case MODE_RESIZE_MARKEDPIXELS_FINISH:
				{
					// get current mouse position
					storeEndIndicesAsNewTempIndices();
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// translate the temporary buffer to the current end
		            // position
					if (horizResize)
					{
						int deltaCol = endGlobalCol - tempGlobalCol;
						totalDeltaCol += deltaCol;
					}
					if (verticResize)
					{
						int deltaRow = endGlobalRow - tempGlobalRow;
						totalDeltaRow += deltaRow;
					}
					Vector<InternalPixel> oldBuffer = tilesetPanel.getPermanentBuffer();
					Vector<InternalPixel> buffer = DrawingLogic.scaleBuffer(oldBuffer, clickedNob, totalDeltaCol,
		                    totalDeltaRow);
					tilesetPanel.clearPixelBuffer();
					tilesetPanel.addBufferedPixels(buffer);

					if (totalDeltaCol != 0 || totalDeltaRow != 0)
					{
						// create new permanent buffer out of temporary buffer
		                // via
		                // command
						ChangeCommand command = CommandFactory.create(ResizeMarkedPixelsCommand.class,
		                        new CommandParameter(model, tilesetPanel.getPixelBuffer()));
						editingDomain.getCommandStack().execute(command);
					}
					// update display
		            // bindingContext.removeBinding(permanentBufferBinding);
		            // bindingContext.addBinding(permanentBufferBinding);
					permanentBufferBinding.updateModelToTarget();
					markedRectBeingResized = false;
					tilesetPanel.setShowBufferedPixels(false);
					tilesetPanel.setTemporarilyHidePermanentBuffer(false);
					break;
				}
				default:
				{
					break;
				}
				}

				// setp 4: reset all variables we don't need in the following
		        // steps
		        // ==============================================================
				mouseMovedBetweenDownAndUp = false;
				if (button == BUTTON_LEFT)
				{
					leftDown = false;
				}
				if (button == BUTTON_RIGHT)
				{
					rightDown = false;
				}
				resetStartIndices();
				resetTempIndices();
				resetEndIndices();
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
				// step 1: gather all variables so we can decide which mode to
		        // go into --> which action will be triggered
		        // ==============================================================
		        // endIndices
		        // leftDown
		        // mouseMovedBetweenDownAndUp
				boolean permHasContent = true;
				if (model.getPermanentBuffer() == null || model.getPermanentBuffer().getBufferedPixels() == null
		                || model.getPermanentBuffer().getBufferedPixels().isEmpty())
				{
					permHasContent = false;
				}
				// rightDown
		        // startIndices
		        // tempIndices
				boolean drawMarkingRectangle = tilesetPanel.isDrawMarkingRectangle();
				// markedRectBeingMoved;
				boolean showTempBuffer = tilesetPanel.isShowBufferedPixels();
				boolean showPermBuffer = tilesetPanel.isShowPermanentBuffer();
				boolean tempHidePermBuffer = tilesetPanel.isTemporarilyHidePermanentBuffer();
				int BUTTON_LEFT = 0;
				int BUTTON_RIGHT = 1;
				int BUTTON_MIDDLE = 2;
				int button = BUTTON_MIDDLE;
				if (e.button == 1)
				{
					button = BUTTON_LEFT;
				}
				else if (e.button == 3)
				{
					button = BUTTON_RIGHT;
				}
				boolean copyBG = true;
				if (tltmTranslucentBG_mark.getSelection())
				{
					copyBG = false;
				}
				clickedOnPermBuffer = tilesetPanel.clickedOnPermanentBuffer(e.x, e.y);
				clickedNob = -1;
				tlNobClicked = tilesetPanel.clickedOnTopLeftResizeNob(e.x, e.y);
				if (tlNobClicked) clickedNob = 0;
				tmNobClicked = tilesetPanel.clickedOnTopMiddleResizeNob(e.x, e.y);
				if (tmNobClicked) clickedNob = 1;
				trNobClicked = tilesetPanel.clickedOnTopRightResizeNob(e.x, e.y);
				if (trNobClicked) clickedNob = 2;
				mlNobClicked = tilesetPanel.clickedOnMiddleLeftResizeNob(e.x, e.y);
				if (mlNobClicked) clickedNob = 3;
				mrNobClicked = tilesetPanel.clickedOnMiddleRightResizeNob(e.x, e.y);
				if (mrNobClicked) clickedNob = 4;
				blNobClicked = tilesetPanel.clickedOnBottomLeftResizeNob(e.x, e.y);
				if (blNobClicked) clickedNob = 5;
				bmNobClicked = tilesetPanel.clickedOnBottomMiddleResizeNob(e.x, e.y);
				if (bmNobClicked) clickedNob = 6;
				brNobClicked = tilesetPanel.clickedOnBottomRightResizeNob(e.x, e.y);
				if (brNobClicked) clickedNob = 7;
				noNobClicked = false;
				if (clickedOnPermBuffer && !tlNobClicked && !tmNobClicked && !trNobClicked && !mlNobClicked
		                && !mrNobClicked && !blNobClicked && !bmNobClicked && !brNobClicked)
				{
					noNobClicked = true;
				}
				if (!noNobClicked)
				{
					if (tmNobClicked || bmNobClicked)
					{
						horizResize = false;
						verticResize = true;
					}
					else if (mlNobClicked || mrNobClicked)
					{
						horizResize = true;
						verticResize = false;
					}
					else
					{
						horizResize = true;
						verticResize = true;
					}
				}

				// step 2: set mode -> decides what happens
		        // ==============================================================
				final int MODE_MARKING_START = 0;
				final int MODE_UNMARK_POTENTIAL_NEWMARKING_START = 1;
				final int MODE_UNMARK_NO_NEWMARKING = 2;
				final int MODE_MOVE_MARKEDPIXELS_START = 3;
				final int MODE_RESIZE_MARKEDPIXELS_START = 4;
				// set the modes
				int mode = -1;
				if (!permHasContent && button == BUTTON_LEFT)
				{
					System.out.println("MARKING_START");
					mode = MODE_MARKING_START;
				}
				else if (permHasContent && button == BUTTON_LEFT && !clickedOnPermBuffer)
				{
					System.out.println("UNMARK_ANDPOTENTIAL_NEWMARK_START");
					mode = MODE_UNMARK_POTENTIAL_NEWMARKING_START;
				}
				else if (permHasContent && button == BUTTON_RIGHT && !clickedOnPermBuffer)
				{
					System.out.println("UNMARK_NO_NEWMARK");
					mode = MODE_UNMARK_NO_NEWMARKING;
				}
				else if (permHasContent && button == BUTTON_LEFT && clickedOnPermBuffer && noNobClicked)
				{
					System.out.println("MOVE_START");
					mode = MODE_MOVE_MARKEDPIXELS_START;
				}
				else if (permHasContent && button == BUTTON_LEFT && clickedOnPermBuffer && !noNobClicked)
				{
					System.out.println("RESIZE_START");
					mode = MODE_RESIZE_MARKEDPIXELS_START;
				}

				// step 3: react on mode -> trigger commands, store temp
		        // variables, etc.
		        // ==============================================================
				storeStartIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				storeTempIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
				tilesetPanel.setDrawMarkingRectangle(false);
				switch (mode)
				{
				case MODE_MARKING_START:
				{
					leftDown = true;
					break;
				}
				case MODE_UNMARK_POTENTIAL_NEWMARKING_START:
				{
					// unmark via command
					ChangeCommand command = CommandFactory.create(UnmarkAreaCommand.class, new CommandParameter(model));
					editingDomain.getCommandStack().execute(command);
					// no need to show perm anymore
					tilesetPanel.setShowPermanentBuffer(false);
					leftDown = true;
					resetTempIndices();
					resetEndIndices();
					// we stored start indices but reset the other two as we
		            // registerd a left click -> this will be the start for
		            // another marking session continuing in the move listener
					break;
				}
				case MODE_UNMARK_NO_NEWMARKING:
				{
					// unmark via command
					ChangeCommand command = CommandFactory.create(UnmarkAreaCommand.class, new CommandParameter(model));
					editingDomain.getCommandStack().execute(command);
					// no need to show perm anymore
					tilesetPanel.setShowPermanentBuffer(false);
					rightDown = true;
					resetStartIndices();
					resetTempIndices();
					resetEndIndices();
					// this is kind of a dead end. it will only display the
		            // popup menu but nothing else. all other variables are
		            // reset
					break;
				}
				case MODE_MOVE_MARKEDPIXELS_START:
				{
					if (noNobClicked && clickedOnPermBuffer)
					{
						// change tilesetpanel so it displays the content of the
		                // buffer via the faster temp instead of the slower perm
		                // buffer
						tilesetPanel.copyPermIntoTemp();
						tilesetPanel.setTemporarilyHidePermanentBuffer(true);
						tilesetPanel.setShowBufferedPixels(true);
						markedRectBeingMoved = true;
						// store left click variable for proper drag handling
						leftDown = true;
					}
					break;
				}
				case MODE_RESIZE_MARKEDPIXELS_START:
				{
					// copy perm to temp
					tilesetPanel.copyPermIntoTemp();
					tilesetPanel.setTemporarilyHidePermanentBuffer(true);
					tilesetPanel.setShowBufferedPixels(true);
					markedRectBeingResized = true;
					totalDeltaCol = 0;
					totalDeltaRow = 0;
					// store left click variable for proper resize handling
					leftDown = true;
					break;
				}
				default:
				{
					break;
				}
				}

				// step 4: reset all variables we don't need in the following
		        // steps
		        // ==============================================================
			}
		};

		markMouseMoveListener = new MouseMoveListener()
		{
			@Override
			public void mouseMove(MouseEvent e)
			{
				// step 1: gather all variables so we can decide which mode to
		        // go into --> which action will be triggered
		        // ==============================================================
		        // endIndices
		        // leftDown
		        // mouseMovedBetweenDownAndUp
				boolean permHasContent = true;
				if (model.getPermanentBuffer() == null || model.getPermanentBuffer().getBufferedPixels() == null
		                || model.getPermanentBuffer().getBufferedPixels().isEmpty())
				{
					permHasContent = false;
				}
				// rightDown
		        // startIndices
		        // tempIndices
				boolean drawMarkingRectangle = tilesetPanel.isDrawMarkingRectangle();
				// markedRectBeingMoved;
				boolean showTempBuffer = tilesetPanel.isShowBufferedPixels();
				boolean showPermBuffer = tilesetPanel.isShowPermanentBuffer();
				boolean tempHidePermBuffer = tilesetPanel.isTemporarilyHidePermanentBuffer();
				int BUTTON_LEFT = 0;
				int BUTTON_RIGHT = 1;
				int BUTTON_MIDDLE = 2;
				int button = BUTTON_MIDDLE;
				if (leftDown)
				{
					button = BUTTON_LEFT;
				}
				else if (rightDown)
				{
					button = BUTTON_RIGHT;
				}
				boolean copyBG = true;
				if (tltmTranslucentBG_mark.getSelection())
				{
					copyBG = false;
				}
				boolean clickedOnPermBuffer2 = tilesetPanel.clickedOnPermanentBuffer(e.x, e.y);
				// clickedNob = -1;
				boolean tlNobClicked2 = tilesetPanel.clickedOnTopLeftResizeNob(e.x, e.y);
				// if (tlNobClicked) clickedNob = 0;
				boolean tmNobClicked2 = tilesetPanel.clickedOnTopMiddleResizeNob(e.x, e.y);
				// if (tmNobClicked) clickedNob = 1;
				boolean trNobClicked2 = tilesetPanel.clickedOnTopRightResizeNob(e.x, e.y);
				// if (trNobClicked) clickedNob = 2;
				boolean mlNobClicked2 = tilesetPanel.clickedOnMiddleLeftResizeNob(e.x, e.y);
				// if (mlNobClicked) clickedNob = 3;
				boolean mrNobClicked2 = tilesetPanel.clickedOnMiddleRightResizeNob(e.x, e.y);
				// if (mrNobClicked) clickedNob = 4;
				boolean blNobClicked2 = tilesetPanel.clickedOnBottomLeftResizeNob(e.x, e.y);
				// if (blNobClicked) clickedNob = 5;
				boolean bmNobClicked2 = tilesetPanel.clickedOnBottomMiddleResizeNob(e.x, e.y);
				// if (bmNobClicked) clickedNob = 6;
				boolean brNobClicked2 = tilesetPanel.clickedOnBottomRightResizeNob(e.x, e.y);
				// if (brNobClicked) clickedNob = 7;
		        // boolean noNobClicked = false;
		        // if (clickedOnPermBuffer && !tlNobClicked && !tmNobClicked &&
		        // !trNobClicked && !mlNobClicked
		        // && !mrNobClicked && !blNobClicked && !bmNobClicked &&
		        // !brNobClicked)
		        // {
		        // noNobClicked = true;
		        // }
		        // if (!noNobClicked)
		        // {
		        // if (tmNobClicked || bmNobClicked)
		        // {
		        // horizResize = false;
		        // verticResize = true;
		        // }
		        // else if (mlNobClicked || mrNobClicked)
		        // {
		        // horizResize = true;
		        // verticResize = false;
		        // }
		        // else
		        // {
		        // horizResize = true;
		        // verticResize = true;
		        // }
		        // }

				// step 2: set mode -> decides what happens
		        // ==============================================================
				final int MODE_MARKING_MIDDLE = 0;
				final int MODE_MOVE_MARKEDPIXELS_MIDDLE = 1;
				final int MODE_RESIZE_MARKEDPIXELS_MIDDLE = 2;
				final int MODE_SPECIALCURSORS = 50;
				final int MODE_REGULARCURSOR = 51;
				// set the modes
				int mode = -1;
				int cursorMode = -1;
				if (!permHasContent && button == BUTTON_LEFT)
				{
					System.out.println("MARKING_MIDDLE");
					mode = MODE_MARKING_MIDDLE;
				}
				else if (permHasContent && button == BUTTON_LEFT && markedRectBeingMoved)
				{
					System.out.println("MOVE_MIDDLE");
					mode = MODE_MOVE_MARKEDPIXELS_MIDDLE;
				}
				else if (permHasContent && button == BUTTON_LEFT && markedRectBeingResized)
				{
					System.out.println("RESIZE_MIDDLE");
					mode = MODE_RESIZE_MARKEDPIXELS_MIDDLE;
				}

				if (permHasContent && button != BUTTON_LEFT && clickedOnPermBuffer2)
				{
					cursorMode = MODE_SPECIALCURSORS;
				}
				else if (permHasContent && button != BUTTON_LEFT && !clickedOnPermBuffer2)
				{
					cursorMode = MODE_REGULARCURSOR;
				}

				// step 3: react on mode -> trigger commands, store temp
		        // variables, etc.
		        // ==============================================================
				switch (mode)
				{
				case MODE_MARKING_MIDDLE:
				{
					// draw marking rectangle
					mouseMovedBetweenDownAndUp = true;
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					tilesetPanel.setDrawMarkingRectangle(true);
					tilesetPanel.setMarkingBounds(startGlobalCol - 1, startGlobalRow - 1, endGlobalCol - 1,
		                    endGlobalRow - 1);
					break;
				}
				case MODE_MOVE_MARKEDPIXELS_MIDDLE:
				{
					mouseMovedBetweenDownAndUp = true;
					// track the delta step by step
					storeEndIndicesAsNewTempIndices();
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// calculate the delta for this iteration
					int deltaCol = endGlobalCol - tempGlobalCol;
					int deltaRow = endGlobalRow - tempGlobalRow;
					// translate the temp buffer accordingly
					tilesetPanel.moveTempBuffer(deltaCol, deltaRow);
					break;
				}
				case MODE_RESIZE_MARKEDPIXELS_MIDDLE:
				{
					mouseMovedBetweenDownAndUp = true;
					// track the delta step by step
					storeEndIndicesAsNewTempIndices();
					storeEndIndices(tilesetPanel.getClickedColorPosition(e.x, e.y));
					// calculate the delta for this iteration
					if (horizResize)
					{
						int deltaCol = endGlobalCol - tempGlobalCol;
						if (tlNobClicked || mlNobClicked || blNobClicked)
						{
							totalDeltaCol -= deltaCol;
						}
						else
						{
							totalDeltaCol += deltaCol;
						}
					}
					if (verticResize)
					{
						int deltaRow = endGlobalRow - tempGlobalRow;
						if (tlNobClicked || tmNobClicked || trNobClicked)
						{
							totalDeltaRow -= deltaRow;
						}
						else
						{
							totalDeltaRow += deltaRow;
						}
					}
					Vector<InternalPixel> oldBuffer = tilesetPanel.getPermanentBuffer();
					Vector<InternalPixel> buffer = DrawingLogic.scaleBuffer(oldBuffer, clickedNob, totalDeltaCol,
		                    totalDeltaRow);
					tilesetPanel.clearPixelBuffer();
					tilesetPanel.addBufferedPixels(buffer);
					break;
				}
				default:
				{
					break;
				}
				}

				// set the cursor according to current values
				switch (cursorMode)
				{
				case MODE_SPECIALCURSORS:
				{
					String img = "";
					if (tlNobClicked2 || brNobClicked2)
					{
						img = "cursor_diagDesc.png";
					}
					else if (trNobClicked2 || blNobClicked2)
					{
						img = "cursor_diagAsc.png";
					}
					else if (mlNobClicked2 || mrNobClicked2)
					{
						img = "cursor_horiz.png";
					}
					else if (tmNobClicked2 || bmNobClicked2)
					{
						img = "cursor_vertic.png";
					}
					else
					{
						img = "cursor_move.png";
					}
					Image i = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		                    "graphics/cursors/" + img);
					tilesetPanel.setCursor(new Cursor(null, i.getImageData(), 16, 16));
					break;
				}
				case MODE_REGULARCURSOR:
				{
					// regular crossfade cursor
					Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		                    "graphics/cursors/cursor_crossfade.png");
					tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 10, 10));

					break;
				}
				default:
				{
					break;
				}
				}

				// setp 4: reset all variables we don't need in the following
		        // steps
		        // ==============================================================
			}
		};

		// TODO: add more
	}

	/**
	 * stores the start indices of a click on the tileset 0 = startTileNumber, 1
	 * = startTileColumn, 2 = startTileRow, 3 = startGlobalColumn, 4 =
	 * startGlobalRow
	 * 
	 * @param position
	 */
	private void storeStartIndices(int[] position)
	{
		startTileNumber = position[0];
		startTileCol = position[1];
		startTileRow = position[2];
		startGlobalCol = position[3];
		startGlobalRow = position[4];
		startClickX = position[5];
		startClickY = position[6];
		popupClickX = position[5];
		popupClickY = position[6];
	}

	/**
	 * stores the intermediate indices of a click on the tileset 0 =
	 * tempTileNumber, 1 = tempTileColumn, 2 = tempTileRow, 3 =
	 * tempGlobalColumn, 4 = tempGlobalRow
	 * 
	 * @param position
	 */
	private void storeTempIndices(int[] position)
	{
		tempTileNumber = position[0];
		tempTileCol = position[1];
		tempTileRow = position[2];
		tempGlobalCol = position[3];
		tempGlobalRow = position[4];
		tempClickX = position[5];
		tempClickY = position[6];
	}

	/**
	 * stores the end indices of a click on the tileset 0 = endTileNumber, 1 =
	 * endTileColumn, 2 = endTileRow, 3 = endGlobalColumn, 4 = endGlobalRow
	 * 
	 * @param position
	 */
	private void storeEndIndices(int[] position)
	{
		endTileNumber = position[0];
		endTileCol = position[1];
		endTileRow = position[2];
		endGlobalCol = position[3];
		endGlobalRow = position[4];
		endClickX = position[5];
		endClickY = position[6];
	}

	/**
	 * resets the indices to 0
	 * 
	 * @param position
	 */
	private void resetStartIndices()
	{
		startTileNumber = 0;
		startTileCol = 0;
		startTileRow = 0;
		startGlobalCol = 0;
		startGlobalRow = 0;
		startClickX = 0;
		startClickY = 0;
	}

	/**
	 * resets the indices to 0
	 * 
	 * @param position
	 */
	private void resetTempIndices()
	{
		tempTileNumber = 0;
		tempTileCol = 0;
		tempTileRow = 0;
		tempGlobalCol = 0;
		tempGlobalRow = 0;
		tempClickX = 0;
		tempClickY = 0;
	}

	/**
	 * resets the indices to 0
	 * 
	 * @param position
	 */
	private void resetEndIndices()
	{
		endTileNumber = 0;
		endTileCol = 0;
		endTileRow = 0;
		endGlobalCol = 0;
		endGlobalRow = 0;
		endClickX = 0;
		endClickY = 0;
	}

	/**
	 * will assign the former start indices as the new end indices
	 * 
	 * @param position
	 */
	private void storeStartIndicesAsNewEndIndices()
	{
		endTileNumber = startTileNumber;
		endTileCol = startTileCol;
		endTileRow = startTileRow;
		endGlobalCol = startGlobalCol;
		endGlobalRow = startGlobalRow;
		endClickX = startClickX;
		endClickY = startClickY;
	}

	/**
	 * will assign the former end indices as the new temp indices
	 * 
	 * @param position
	 */
	private void storeEndIndicesAsNewTempIndices()
	{
		tempTileNumber = endTileNumber;
		tempTileCol = endTileCol;
		tempTileRow = endTileRow;
		tempGlobalCol = endGlobalCol;
		tempGlobalRow = endGlobalRow;
		tempClickX = endClickX;
		tempClickY = endClickY;
	}

	/**
	 * will assign the former end indices as the new start indices
	 * 
	 * @param position
	 */
	private void storeEndIndicesAsNewStartIndices()
	{
		startTileNumber = endTileNumber;
		startTileCol = endTileCol;
		startTileRow = endTileRow;
		startGlobalCol = endGlobalCol;
		startGlobalRow = endGlobalRow;
		startClickX = endClickX;
		startClickY = endClickY;
	}

	/**
	 * checks if any of the last end positions has changed
	 * 
	 * @param position
	 *            the current position
	 * @return true if there was a change, false otherwise
	 */
	protected boolean endIndexDiffersFrom(int[] position)
	{
		if (endTileNumber == position[0] && endTileCol == position[1] && endTileRow == position[2]
		        && endGlobalCol == position[3] && endGlobalRow == position[4])
		{
			return false;
		}
		return true;
	}

	/**
	 * Create contents of the editor part. Note: this method is not very human
	 * readable. Use windowbuilder to change the GUI so you don't have to deal
	 * with this stuff manually. Only a few minor method calls are added at the
	 * end of the method
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(final Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(6, false));
		new Label(container, SWT.NONE);

		cmpToolOptions = new Composite(container, SWT.NONE);
		layoutToolOptions = new StackLayout();
		cmpToolOptions.setLayout(layoutToolOptions);
		cmpToolOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 5, 1));

		cmpPencilOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpPencilOptions.setLayout(new GridLayout(3, false));

		Label lblShape = new Label(cmpPencilOptions, SWT.NONE);
		lblShape.setText("Shape");

		Label lblSize = new Label(cmpPencilOptions, SWT.NONE);
		lblSize.setText("Size");

		Label lblDrawingMode = new Label(cmpPencilOptions, SWT.NONE);
		lblDrawingMode.setText("Drawing Mode");

		ToolBar tbShape_Pencil = new ToolBar(cmpPencilOptions, SWT.FLAT | SWT.RIGHT);

		tltmRound_Pencil = new ToolItem(tbShape_Pencil, SWT.RADIO);
		tltmRound_Pencil.setSelection(true);
		tltmRound_Pencil.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_pencil/shape_round.png"));

		tltmRect_Pencil = new ToolItem(tbShape_Pencil, SWT.RADIO);
		tltmRect_Pencil.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_pencil/shape_square.png"));

		spnSize_Pencil = new Spinner(cmpPencilOptions, SWT.BORDER);
		spnSize_Pencil.setPageIncrement(3);
		spnSize_Pencil.setMaximum(8);
		spnSize_Pencil.setMinimum(1);
		spnSize_Pencil.setSelection(1);

		ToolBar tbDrawingMode_Pencil = new ToolBar(cmpPencilOptions, SWT.FLAT | SWT.RIGHT);

		tltmSolid_Pencil = new ToolItem(tbDrawingMode_Pencil, SWT.RADIO);
		tltmSolid_Pencil.setSelection(true);
		tltmSolid_Pencil.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_pencil/mode_solid.png"));

		tltmBorder_Pencil = new ToolItem(tbDrawingMode_Pencil, SWT.RADIO);
		tltmBorder_Pencil.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_pencil/mode_border.png"));

		cmpLineOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpLineOptions.setLayout(new GridLayout(3, false));

		Label lblShape_1 = new Label(cmpLineOptions, SWT.NONE);
		lblShape_1.setText("Shape");

		Label lblSize_1 = new Label(cmpLineOptions, SWT.NONE);
		lblSize_1.setText("Size");

		Label lblDrawingMode_1 = new Label(cmpLineOptions, SWT.NONE);
		lblDrawingMode_1.setText("Drawing Mode");

		ToolBar tbShape_Line = new ToolBar(cmpLineOptions, SWT.FLAT | SWT.RIGHT);

		tltmRound_Line = new ToolItem(tbShape_Line, SWT.RADIO);
		tltmRound_Line.setSelection(true);
		tltmRound_Line.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_line/shape_round.png"));

		tltmSquare_Line = new ToolItem(tbShape_Line, SWT.RADIO);
		tltmSquare_Line.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_line/shape_square.png"));

		spnSize_Line = new Spinner(cmpLineOptions, SWT.BORDER);
		spnSize_Line.setPageIncrement(3);
		spnSize_Line.setMaximum(8);
		spnSize_Line.setMinimum(1);
		spnSize_Line.setSelection(1);

		ToolBar tbDrawingMode_Line = new ToolBar(cmpLineOptions, SWT.FLAT | SWT.RIGHT);

		tltmSolid_Line = new ToolItem(tbDrawingMode_Line, SWT.RADIO);
		tltmSolid_Line.setSelection(true);
		tltmSolid_Line.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_line/mode_solid.png"));

		tltmBorder_Line = new ToolItem(tbDrawingMode_Line, SWT.RADIO);
		tltmBorder_Line.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_line/mode_border.png"));

		cmpMarkOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpMarkOptions.setLayout(new GridLayout(1, false));

		Label lblMarkBackground = new Label(cmpMarkOptions, SWT.NONE);
		lblMarkBackground.setText("Mark Background");

		ToolBar toolBar = new ToolBar(cmpMarkOptions, SWT.FLAT | SWT.RIGHT);

		tltmSolidBG_mark = new ToolItem(toolBar, SWT.RADIO);
		tltmSolidBG_mark.setSelection(true);
		tltmSolidBG_mark.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_mark/opt_markSolid.png"));

		tltmTranslucentBG_mark = new ToolItem(toolBar, SWT.RADIO);
		tltmTranslucentBG_mark.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_mark/opt_markTrans.png"));

		cmpWizardOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpWizardOptions.setLayout(new GridLayout(1, false));

		Label lblTileBorders = new Label(cmpWizardOptions, SWT.NONE);
		lblTileBorders.setText("Tile Borders");

		ToolBar toolBar_1 = new ToolBar(cmpWizardOptions, SWT.FLAT | SWT.RIGHT);

		tltm_multiTile_wizard = new ToolItem(toolBar_1, SWT.RADIO);
		tltm_multiTile_wizard.setSelection(true);
		tltm_multiTile_wizard.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_wizard/opt_MarkMulti.png"));

		tltm_singleTile_wizard = new ToolItem(toolBar_1, SWT.RADIO);
		tltm_singleTile_wizard.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_wizard/opt_MarkSingle.png"));

		cmpPipetteOptions = new Composite(cmpToolOptions, SWT.NONE);

		cmpMagnifierOptions = new Composite(cmpToolOptions, SWT.NONE);

		cmpRectangleOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpRectangleOptions.setLayout(new GridLayout(2, false));

		Label lblDrawingMode_2 = new Label(cmpRectangleOptions, SWT.NONE);
		lblDrawingMode_2.setText("Drawing Mode");

		Label lblTransitionDirection = new Label(cmpRectangleOptions, SWT.NONE);
		lblTransitionDirection.setText("Transition Direction");

		ToolBar toolBar_2 = new ToolBar(cmpRectangleOptions, SWT.FLAT | SWT.RIGHT);

		tltmOutline_rect = new ToolItem(toolBar_2, SWT.RADIO);
		tltmOutline_rect.setSelection(true);
		tltmOutline_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/opt_rectOutline.png"));

		tltmFilled_rect = new ToolItem(toolBar_2, SWT.RADIO);
		tltmFilled_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/opt_rectFilled.png"));

		tltmTwoColors_rect = new ToolItem(toolBar_2, SWT.RADIO);
		tltmTwoColors_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/opt_rectTwoColors.png"));

		tltmTransition_rect = new ToolItem(toolBar_2, SWT.RADIO);
		tltmTransition_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/opt_rectTwoColorTransition.png"));

		ToolBar toolBar_3 = new ToolBar(cmpRectangleOptions, SWT.FLAT | SWT.RIGHT);

		tltm_horizfade_rect = new ToolItem(toolBar_3, SWT.RADIO);
		tltm_horizfade_rect.setSelection(true);
		tltm_horizfade_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/transitionDir_horiz.png"));

		tltm_verticfade_rect = new ToolItem(toolBar_3, SWT.RADIO);
		tltm_verticfade_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/transitionDir_vertic.png"));

		tltm_diagfadeone_rect = new ToolItem(toolBar_3, SWT.RADIO);
		tltm_diagfadeone_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/transitionDir_diagOne.png"));

		tltm_diagfadetwo_rect = new ToolItem(toolBar_3, SWT.RADIO);
		tltm_diagfadetwo_rect.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_rect/transitionDir_diagTwo.png"));

		cmpEllipseOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpEllipseOptions.setLayout(new GridLayout(2, false));

		Label lblDrawingMode_3 = new Label(cmpEllipseOptions, SWT.NONE);
		lblDrawingMode_3.setText("Drawing Mode");

		Label lblTransitionDirection_1 = new Label(cmpEllipseOptions, SWT.NONE);
		lblTransitionDirection_1.setText("Transition Direction");

		ToolBar toolBar_4 = new ToolBar(cmpEllipseOptions, SWT.FLAT | SWT.RIGHT);

		tltm_outline_ellipse = new ToolItem(toolBar_4, SWT.RADIO);
		tltm_outline_ellipse.setSelection(true);
		tltm_outline_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/opt_ovalOutline.png"));

		tltm_filled_ellipse = new ToolItem(toolBar_4, SWT.RADIO);
		tltm_filled_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/opt_ovalFilled.png"));

		tltm_twoColors_ellipse = new ToolItem(toolBar_4, SWT.RADIO);
		tltm_twoColors_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/opt_ovalTwoColors.png"));

		tltm_transition_ellipse = new ToolItem(toolBar_4, SWT.RADIO);
		tltm_transition_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/opt_ovalOutlineTransition.png"));

		ToolBar toolBar_5 = new ToolBar(cmpEllipseOptions, SWT.FLAT | SWT.RIGHT);

		tltm_horizfade_ellipse = new ToolItem(toolBar_5, SWT.RADIO);
		tltm_horizfade_ellipse.setSelection(true);
		tltm_horizfade_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/transitionDir_horiz.png"));

		tltm_verticfade_ellipse = new ToolItem(toolBar_5, SWT.RADIO);
		tltm_verticfade_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/transitionDir_vertic.png"));

		tltm_diagfadeone_ellipse = new ToolItem(toolBar_5, SWT.RADIO);
		tltm_diagfadeone_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/transitionDir_diagOne.png"));

		tltm_diagfadetwo_ellipse = new ToolItem(toolBar_5, SWT.RADIO);
		tltm_diagfadetwo_ellipse.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_oval/transitionDir_diagTwo.png"));

		cmpEnumerationOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpEnumerationOptions.setLayout(new GridLayout(3, false));

		Label lblMarkingMode = new Label(cmpEnumerationOptions, SWT.NONE);
		lblMarkingMode.setText("Marking Mode");

		Label lblBase = new Label(cmpEnumerationOptions, SWT.NONE);
		lblBase.setText("Base");

		Label lblStartNumber = new Label(cmpEnumerationOptions, SWT.NONE);
		lblStartNumber.setText("Start Number");

		ToolBar toolBar_6 = new ToolBar(cmpEnumerationOptions, SWT.FLAT | SWT.RIGHT);

		tltm_markModeRectangular_Enum = new ToolItem(toolBar_6, SWT.RADIO);
		tltm_markModeRectangular_Enum.setSelection(true);
		tltm_markModeRectangular_Enum.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_enum/opt_enumRect.png"));

		tltm_markModeContinuous_Enum = new ToolItem(toolBar_6, SWT.RADIO);
		tltm_markModeContinuous_Enum.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_enum/opt_enumLine.png"));

		cmb_base_Enum = new Combo(cmpEnumerationOptions, SWT.READ_ONLY);
		cmb_base_Enum.setItems(new String[] { "10 (dec)", "16 (hex)" });
		cmb_base_Enum.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cmb_base_Enum.select(0);

		text = new Text(cmpEnumerationOptions, SWT.BORDER);
		text.setText("0");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		cmpTextOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpTextOptions.setLayout(new GridLayout(5, false));

		Label lblFontWidth = new Label(cmpTextOptions, SWT.NONE);
		lblFontWidth.setText("Font Width");

		Label lblAlignment = new Label(cmpTextOptions, SWT.NONE);
		lblAlignment.setText("Alignment");

		Label lblText = new Label(cmpTextOptions, SWT.NONE);
		lblText.setText("Text:");

		txtHereGoesSome = new Text(cmpTextOptions, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtHereGoesSome.setText("Here goes some very important text");
		txtHereGoesSome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
		new Label(cmpTextOptions, SWT.NONE);

		ToolBar toolBar_7 = new ToolBar(cmpTextOptions, SWT.FLAT | SWT.RIGHT);

		tltm_variableWidth = new ToolItem(toolBar_7, SWT.RADIO);
		tltm_variableWidth.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_text/opt_variableWidth.png"));
		tltm_variableWidth.setSelection(true);

		tltm_fixedWidth = new ToolItem(toolBar_7, SWT.RADIO);
		tltm_fixedWidth.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_text/opt_fixedWidth.png"));

		ToolBar toolBar_8 = new ToolBar(cmpTextOptions, SWT.FLAT | SWT.RIGHT);

		tltm_LeftAligned = new ToolItem(toolBar_8, SWT.RADIO);
		tltm_LeftAligned.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_text/text_left.png"));
		tltm_LeftAligned.setSelection(true);

		tltm_CenterAlignment = new ToolItem(toolBar_8, SWT.RADIO);
		tltm_CenterAlignment.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_text/text_center.png"));

		tltm_RightAligned = new ToolItem(toolBar_8, SWT.RADIO);
		tltm_RightAligned.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_text/text_right.png"));

		btnWrap = new Button(cmpTextOptions, SWT.CHECK);
		btnWrap.setText("wrap");

		Button btnOk = new Button(cmpTextOptions, SWT.NONE);
		btnOk.setImage(
		        ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor", "graphics/tool_text/ok.png"));

		cmpFillOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpFillOptions.setLayout(new GridLayout(2, false));

		Label lblFillMode = new Label(cmpFillOptions, SWT.NONE);
		lblFillMode.setText("Tile Borders");

		Label lblFillMode_1 = new Label(cmpFillOptions, SWT.NONE);
		lblFillMode_1.setText("Fill Mode");

		ToolBar toolBar_9 = new ToolBar(cmpFillOptions, SWT.FLAT | SWT.RIGHT);

		tltm_ignoreBorders_fill = new ToolItem(toolBar_9, SWT.RADIO);
		tltm_ignoreBorders_fill.setSelection(true);
		tltm_ignoreBorders_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/opt_fillMulti.png"));

		tltm_singleTile_fill = new ToolItem(toolBar_9, SWT.RADIO);
		tltm_singleTile_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/opt_fillSingle.png"));

		ToolBar toolBar_10 = new ToolBar(cmpFillOptions, SWT.FLAT | SWT.RIGHT);

		tltm_singleColor_fill = new ToolItem(toolBar_10, SWT.RADIO);
		tltm_singleColor_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/singleColor.png"));
		tltm_singleColor_fill.setSelection(true);

		tltm_horiz_fill = new ToolItem(toolBar_10, SWT.RADIO);
		tltm_horiz_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/transitionDir_horiz.png"));

		tltm_vertic_fill = new ToolItem(toolBar_10, SWT.RADIO);
		tltm_vertic_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/transitionDir_vertic.png"));

		tltm_diagone_fill = new ToolItem(toolBar_10, SWT.RADIO);
		tltm_diagone_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/transitionDir_diagOne.png"));

		tltm_diagtwo_fill = new ToolItem(toolBar_10, SWT.RADIO);
		tltm_diagtwo_fill.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_fill/transitionDir_diagTwo.png"));

		cmpPalettetoolOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpPalettetoolOptions.setLayout(new GridLayout(3, false));

		Label lblPaletteToAssociate = new Label(cmpPalettetoolOptions, SWT.NONE);
		lblPaletteToAssociate.setText("Palette to associate Tile with");
		new Label(cmpPalettetoolOptions, SWT.NONE);

		Label lblPalettePreview = new Label(cmpPalettetoolOptions, SWT.NONE);
		lblPalettePreview.setText("Palette Preview");

		txtSomeVeryImportant = new Text(cmpPalettetoolOptions, SWT.BORDER);
		txtSomeVeryImportant.setText("Some very important Palettename");
		txtSomeVeryImportant.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));

		Button button = new Button(cmpPalettetoolOptions, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		button.setText("...");

		PalettePanel palettePanel_1 = new PalettePanel(cmpPalettetoolOptions, SWT.NONE);
		palettePanel_1.setDisplayOnly();
		GridData gd_palettePanel_1 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_palettePanel_1.widthHint = 217;
		palettePanel_1.setLayoutData(gd_palettePanel_1);

		cmpSprayOptions = new Composite(cmpToolOptions, SWT.NONE);
		cmpSprayOptions.setLayout(new GridLayout(3, false));

		lblShape_2 = new Label(cmpSprayOptions, SWT.NONE);
		lblShape_2.setText("Shape");

		lblSize_2 = new Label(cmpSprayOptions, SWT.NONE);
		lblSize_2.setText("Size");

		lblDitheringMode = new Label(cmpSprayOptions, SWT.NONE);
		lblDitheringMode.setText("Dithering Mode");

		toolBar_11 = new ToolBar(cmpSprayOptions, SWT.FLAT | SWT.RIGHT);

		tltmCircle_spray = new ToolItem(toolBar_11, SWT.RADIO);
		tltmCircle_spray.setSelection(true);
		tltmCircle_spray.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_spray/shape_round.png"));

		tltmRect_spray = new ToolItem(toolBar_11, SWT.RADIO);
		tltmRect_spray.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_spray/shape_square.png"));

		spnSize_spray = new Spinner(cmpSprayOptions, SWT.BORDER);
		spnSize_spray.setPageIncrement(3);
		spnSize_spray.setMaximum(8);
		spnSize_spray.setMinimum(1);
		spnSize_spray.setSelection(8);

		ToolBar toolBar_12 = new ToolBar(cmpSprayOptions, SWT.FLAT | SWT.RIGHT);

		tltmPatternMode = new ToolItem(toolBar_12, SWT.RADIO);
		tltmPatternMode.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_spray/spray_pattern.png"));
		tltmPatternMode.setSelection(true);

		tltmRandomMode = new ToolItem(toolBar_12, SWT.RADIO);
		tltmRandomMode.setImage(ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
		        "graphics/tool_spray/spray_random.png"));

		Label label_4 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		tilesetScroller = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tilesetScroller.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 2));
		tilesetScroller.setExpandHorizontal(true);
		tilesetScroller.setExpandVertical(true);

		tilesetPanel = new TilesetPanel(tilesetScroller, SWT.NONE);
		tilesetPanel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));

		tilesetScroller.setContent(tilesetPanel);
		tilesetScroller.setMinSize(tilesetPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Composite composite_4 = new Composite(container, SWT.NONE);
		GridLayout gl_composite_4 = new GridLayout(1, false);
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 3));

		tbToolSelection = new TwoColumnToolbar(composite_4, SWT.NONE);
		tbToolSelection.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));

		Label label = new Label(composite_4, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblOverview_1 = new Label(composite_4, SWT.NONE);
		lblOverview_1.setText("Overview");

		Composite composite_2 = new Composite(composite_4, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(4, false);
		gl_composite_2.horizontalSpacing = 15;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);

		Label lblOverview = new Label(composite_2, SWT.NONE);
		lblOverview.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblOverview.setImage(
		        ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor", "graphics/temp/overview.png"));

		spnZoom = new Spinner(composite_2, SWT.BORDER);
		spnZoom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblZoomin = new Label(composite_2, SWT.NONE);
		lblZoomin.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblZoomin.setImage(
		        ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor", "graphics/temp/zoomIn.png"));

		lblZoomout = new Label(composite_2, SWT.NONE);
		lblZoomout.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblZoomout.setImage(
		        ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor", "graphics/temp/zoomOut.png"));

		lblZoomorig = new Label(composite_2, SWT.NONE);
		lblZoomorig.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblZoomorig.setImage(
		        ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor", "graphics/temp/zoomOrig.png"));

		Label label_2 = new Label(composite_4, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		btnShowGrid = new Button(composite_4, SWT.CHECK);
		btnShowGrid.setSelection(true);
		btnShowGrid.setText("Gridlines");
		
		btnCharacterData = new Button(composite_4, SWT.CHECK);
		btnCharacterData.setText("Character Data");

		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		GridLayout gl_composite_5 = new GridLayout(2, false);
		gl_composite_5.marginWidth = 0;
		gl_composite_5.marginHeight = 0;
		composite_5.setLayout(gl_composite_5);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblColumns = new Label(composite_5, SWT.NONE);
		lblColumns.setSize(57, 21);
		lblColumns.setText("Columns:");

		spnColumns = new Spinner(composite_5, SWT.BORDER);
		spnColumns.setMinimum(1);
		spnColumns.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
		label_5.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));

		Label lblColorPalette = new Label(container, SWT.NONE);
		lblColorPalette.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColorPalette.setText("Color Palette");

		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		txtPaletteName = new Text(composite, SWT.BORDER);
		txtPaletteName.setEditable(false);
		txtPaletteName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		txtPaletteName.setText("Some very important Palettename");

		btnChangePalette = new Button(composite, SWT.NONE);
		btnChangePalette.setText("...");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		selectedColors = new DoubleColorSelectionPanel(container, SWT.NONE);
		selectedColors.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		palettePanel = new PalettePanel(container, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
		gd_composite.heightHint = 98;
		gd_composite.widthHint = 272;
		palettePanel.setLayoutData(gd_composite);

		Composite composite_3 = new Composite(container, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		// init databindings
		initDataBindings();

		// init manual stuff
		initManualStuff();

		// add (non-tool related) listeners
		addListeners();
	}

	/**
	 * Initialize the manual stuff
	 */
	private void initManualStuff()
	{
		// this variable is nowhere stored within the model
		// -> initialize manually
		tilesetPanel.setShowGrid(btnShowGrid.getSelection());
		// the model predefines the tool selected. However the GUI is not yet
		// initialized with the value. --> do it now
		// 1. add the appropriate mouse listener(s) to the tilesetPanel
		updateToolListenersOnTilesetPanel(model.getTool().getValue());
		// 2. display the correct tool options panel on top of the screen
		updateToolOptionsComposite(model.getTool().getValue());
		// 3. update the proper cursor for the tilesetPanel
		updateCursorOnTileset(model.getTool().getValue());
		// 4. make sure the correct temporary drawings are used for the tool
		updateTempDrawingOnTileset(model.getTool().getValue());
	}

	/**
	 * adds the popup menu to the tilesetPanel
	 */
	private void addPopupMenu()
	{
		menu = new Menu(tilesetPanel);
		tilesetPanel.setMenu(menu);

		final MenuItem mntmCopy = new MenuItem(menu, SWT.NONE);
		mntmCopy.setText("Copy");
		mntmCopy.setAccelerator(SWT.CTRL + 'C');
		mntmCopy.setEnabled(false);
		mntmCopy.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				copyPixels();
			}
		});

		final MenuItem mntmPaste = new MenuItem(menu, SWT.NONE);
		mntmPaste.setText("Paste");
		mntmPaste.setAccelerator(SWT.CTRL + 'V');
		mntmPaste.setEnabled(false);
		mntmPaste.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				pastePixels();
			}
		});

		final MenuItem mntmCut = new MenuItem(menu, SWT.NONE);
		mntmCut.setText("Cut");
		mntmCut.setAccelerator(SWT.CTRL + 'X');
		mntmCut.setEnabled(false);
		mntmCut.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				cutPixels();
			}
		});

		final MenuItem mntmDelete = new MenuItem(menu, SWT.NONE);
		mntmDelete.setText("Delete");
		mntmDelete.setAccelerator(SWT.DEL);
		mntmDelete.setEnabled(false);
		mntmDelete.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				deletePixels();
			}
		});
		menu.addMenuListener(new MenuAdapter()
		{
			@Override
			public void menuShown(MenuEvent e)
			{
				// enable paste if a tileseteditor shared temp buffer exists
				Vector<InternalPixel> buffer = getSystemClipboardSharedBuffer();
				if (buffer != null && !buffer.isEmpty())
				{
					mntmPaste.setEnabled(true);
				}
				// enable copy, cut, delete when click occurred on marked area
				if (tilesetPanel.clickedOnPermanentBuffer(popupClickX, popupClickY))
				{
					mntmCopy.setEnabled(true);
					mntmCut.setEnabled(true);
					mntmDelete.setEnabled(true);
				}
			}
		});
	}

	/**
	 * reads the SWT clipboard data to detect either tileseteditor internally
	 * copied stuff (which is in text form) or image data from other programs
	 * 
	 * @return the shared buffer
	 */
	protected Vector<InternalPixel> getSystemClipboardSharedBuffer()
	{
		// the vector to return
		Vector<InternalPixel> buffer = null;

		Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))
		{
			try
			{
				BufferedImage img = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
				buffer = new Vector<InternalPixel>();

				// get the palette to match against
				PaletteImpl paletteModel = getUsedPaletteObject();

				int width = img.getWidth();
				int height = img.getHeight();

				// first of all we have to find out if there are more than one
				// transparency value. if so, we'll respect transparency.
				// Otherwise, we'll ignore it
				boolean ignoreTransparency = true;
				HashSet<Integer> alphas = new HashSet<Integer>();
				for (int row = 0; row < height; row++)
				{
					for (int col = 0; col < width; col++)
					{
						Color c = new Color(img.getRGB(col, row), true);
						int a = c.getAlpha();
						alphas.add(new Integer(a));
					}
				}
				if (alphas.size() > 1)
				{
					ignoreTransparency = false;
				}

				// then we go through all the pixels and create buffer pixel
				// values for them
				for (int row = 0; row < height; row++)
				{
					for (int col = 0; col < width; col++)
					{
						// get RGB values
						Color c = new Color(img.getRGB(col, row), true);
						int r = (int) Math.round((double) c.getRed() / (double) 8);
						int g = (int) Math.round((double) c.getGreen() / (double) 8);
						int b = (int) Math.round((double) c.getBlue() / (double) 8);
						// make sure they're in bounds
						if (r < 0)
						{
							r = 0;
						}
						else if (r > 31)
						{
							r = 31;
						}
						if (g < 0)
						{
							g = 0;
						}
						else if (g > 31)
						{
							g = 31;
						}
						if (b < 0)
						{
							b = 0;
						}
						else if (b > 31)
						{
							b = 31;
						}
						// get alpha value
						int a = c.getAlpha();

						// now we have the raw color values which do not have to
						// match the ones available in the palette
						// first of all we don't want any color with an alpha
						// value of more than 126 -> they'll just be omitted as
						// we don't support alpha transparency in the
						// tileeditor#
						if (ignoreTransparency || a > 126)
						{
							int nearestNeighbor = DrawingLogic.getNearestNeighbor(new NonNativeColor(r, g, b),
							        paletteModel);
							buffer.add(new InternalPixel(col, row, nearestNeighbor));
						}
					}
				}
			}
			catch (UnsupportedFlavorException e0)
			{
				// handle this as desired
				e0.printStackTrace();
			}
			catch (IOException e1)
			{
				// handle this as desired
				e1.printStackTrace();
			}
		}
		return buffer;
	}

	/**
	 * obtains the currently used palette for color matching while pasting
	 * pixels
	 * 
	 * @return
	 */
	private PaletteImpl getUsedPaletteObject()
	{
		String paletteName = model.getPaletteName();
		// string should be of format "name.pal
		// (/projectName/subfolderpath/name.pal)
		String[] parts = paletteName.split("/");
		parts = parts[0].split("\\(");
		// get project name
		String projectName = parts[1];
		// get filename
		String fileName = parts[0].substring(0, parts[0].length() - 1);
		parts = paletteName.split("\\(");
		// get id for obtaining the correct file (in case we have duplicates
		// in different folders)
		String id = parts[1].substring(0, parts[1].length() - 1);

		// get project reference
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		try
		{
			// get palette file reference
			SnesdeProjectRoot projectRoot = FlowWorkspace.getProjectRoot(project);
			IFile paletteFile = projectRoot.getFileRef(id);

			// load EMF model from file
			List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
			ComposedAdapterFactory adapterFactory1 = new ComposedAdapterFactory(
			        ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			adapterFactory1.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			adapterFactory1.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
			factories.add(adapterFactory1);
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(factories);
			BasicCommandStack commandStack = new BasicCommandStack();
			AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
			        new HashMap<Resource, Boolean>());
			URI inputUri = URI.createPlatformResourceURI(paletteFile.getFullPath().toString(), true);
			Resource res = editingDomain.getResourceSet().getResource(inputUri, true);
			res.load(null);
			PaletteImpl model = (PaletteImpl) res.getContents().get(0);
			return model;
		}
		catch (CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * sets the clipboard content to the content passed
	 * 
	 * @param permanentBuffer
	 *            the buffer to copy to the clipboard
	 */
	private void setSystemClipboardSharedBuffer(Vector<InternalPixel> permanentBuffer)
	{
		// get dimensions of the buffer
		int[] rectangle = tilesetPanel.getPermanentBufferRectangle();
		int minCol = rectangle[0];
		int minRow = rectangle[1];
		int maxCol = rectangle[2];
		int maxRow = rectangle[3];
		int width = (maxCol - minCol) + 1;
		int height = (maxRow - minRow) + 1;
		// System.out.println("Col:"+minCol+"-"+maxCol+"
		// Row:"+minRow+"-"+maxRow);
		// System.out.println("Width|Height: "+width+"|"+height);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// init image with transparent pixels
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				Color c = new java.awt.Color(0, 0, 0, 0);
				img.setRGB(col, row, c.getRGB());
			}
		}

		// set pixel data from buffer
		for (InternalPixel pix : permanentBuffer)
		{
			NonNativeColor color = palettePanel.getColors()[pix.getIndex()];
			Color c = new java.awt.Color(color.getR() * 8, color.getG() * 8, color.getB() * 8, 255);
			// System.out.println("Set pixel at " + (pix.getCol() - minCol) +
			// "|" + (pix.getRow() - minRow));
			img.setRGB(pix.getCol() - minCol, pix.getRow() - minRow, c.getRGB());
		}

		// set image to clipboard
		try
		{
			// we use the AWT clipboard implementation as the SWT one produces
			// total garbage and/or loses alpha transparency data and apparently
			// noone seems to be interested in fixing it (see
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=305314)
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			ImageTransferable selection = new ImageTransferable(img);
			clipboard.setContents(selection, null);
		}
		catch (Exception x)
		{
			x.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * removes the popup menu from the tilesetPanel
	 */
	private void removePopupMenu()
	{
		if (menu != null)
		{
			menu.dispose();
		}
	}

	/**
	 * sets the proper listeners on the tilesetPanel according to the
	 * ToolSelection. Valid IDs can be found with ToolSelection.XYZ_VALUE
	 */
	protected void updateToolListenersOnTilesetPanel(int toolId)
	{
		// remove all listeners which were previously assigned to
		// tilesetpanel (those who implement tool logic)
		removePopupMenu();
		tilesetPanel.removeMouseListener(pencilMouseAdapter);
		tilesetPanel.removeMouseMoveListener(pencilMouseMoveListener);
		tilesetPanel.removeMouseListener(lineMouseAdapter);
		tilesetPanel.removeMouseMoveListener(lineMouseMoveListener);
		tilesetPanel.removeMouseListener(rectangleMouseAdapter);
		tilesetPanel.removeMouseMoveListener(rectangleMouseMoveListener);
		tilesetPanel.removeMouseListener(ellipseMouseAdapter);
		tilesetPanel.removeMouseMoveListener(ellipseMouseMoveListener);
		tilesetPanel.removeMouseListener(pipetteMouseAdapter);
		tilesetPanel.removeMouseListener(magnifierMouseAdapter);
		tilesetPanel.removeMouseMoveListener(magnifierMouseMoveListener);
		tilesetPanel.removeMouseListener(fillMouseAdapter);
		tilesetPanel.removeMouseListener(markMouseAdapter);
		tilesetPanel.removeMouseMoveListener(markMouseMoveListener);
		// TODO: add more

		if (toolId == ToolSelection.PENCIL_VALUE)
		{
			tilesetPanel.addMouseListener(pencilMouseAdapter);
			tilesetPanel.addMouseMoveListener(pencilMouseMoveListener);
		}
		else if (toolId == ToolSelection.LINE_VALUE)
		{
			tilesetPanel.addMouseListener(lineMouseAdapter);
			tilesetPanel.addMouseMoveListener(lineMouseMoveListener);
		}
		else if (toolId == ToolSelection.ELLIPSE_VALUE)
		{
			tilesetPanel.addMouseListener(ellipseMouseAdapter);
			tilesetPanel.addMouseMoveListener(ellipseMouseMoveListener);
		}
		else if (toolId == ToolSelection.ENUMERATION_VALUE)
		{
			// TODO
		}
		else if (toolId == ToolSelection.FILL_VALUE)
		{
			tilesetPanel.addMouseListener(fillMouseAdapter);
		}
		else if (toolId == ToolSelection.MAGNIFIER_VALUE)
		{
			tilesetPanel.addMouseListener(magnifierMouseAdapter);
			tilesetPanel.addMouseMoveListener(magnifierMouseMoveListener);
		}
		else if (toolId == ToolSelection.MARK_VALUE)
		{
			tilesetPanel.addMouseListener(markMouseAdapter);
			tilesetPanel.addMouseMoveListener(markMouseMoveListener);
			addPopupMenu();
		}
		else if (toolId == ToolSelection.PALETTE_VALUE)
		{
			// TODO
		}
		else if (toolId == ToolSelection.PIPETTE_VALUE)
		{
			tilesetPanel.addMouseListener(pipetteMouseAdapter);
		}
		else if (toolId == ToolSelection.RECTANGLE_VALUE)
		{
			tilesetPanel.addMouseListener(rectangleMouseAdapter);
			tilesetPanel.addMouseMoveListener(rectangleMouseMoveListener);
		}
		else if (toolId == ToolSelection.TEXT_VALUE)
		{
			// TODO
		}
		else if (toolId == ToolSelection.WIZARD_VALUE)
		{
			// TODO
		}
		else if (toolId == ToolSelection.SPRAY_VALUE)
		{
			// TODO
		}
	}

	/**
	 * updates the tool options panel to show the ones for the tool currently
	 * selected
	 * 
	 * @param toolId
	 *            the tool id deciding which tool options to show
	 */
	protected void updateToolOptionsComposite(int toolId)
	{
		if (toolId == ToolSelection.PENCIL_VALUE)
		{
			layoutToolOptions.topControl = cmpPencilOptions;
		}
		else if (toolId == ToolSelection.LINE_VALUE)
		{
			layoutToolOptions.topControl = cmpLineOptions;
		}
		else if (toolId == ToolSelection.ELLIPSE_VALUE)
		{
			layoutToolOptions.topControl = cmpEllipseOptions;
		}
		else if (toolId == ToolSelection.ENUMERATION_VALUE)
		{
			layoutToolOptions.topControl = cmpEnumerationOptions;
		}
		else if (toolId == ToolSelection.FILL_VALUE)
		{
			layoutToolOptions.topControl = cmpFillOptions;
		}
		else if (toolId == ToolSelection.MAGNIFIER_VALUE)
		{
			layoutToolOptions.topControl = cmpMagnifierOptions;
		}
		else if (toolId == ToolSelection.MARK_VALUE)
		{
			layoutToolOptions.topControl = cmpMarkOptions;
		}
		else if (toolId == ToolSelection.PALETTE_VALUE)
		{
			layoutToolOptions.topControl = cmpPalettetoolOptions;
		}
		else if (toolId == ToolSelection.PIPETTE_VALUE)
		{
			layoutToolOptions.topControl = cmpPipetteOptions;
		}
		else if (toolId == ToolSelection.RECTANGLE_VALUE)
		{
			layoutToolOptions.topControl = cmpRectangleOptions;
		}
		else if (toolId == ToolSelection.TEXT_VALUE)
		{
			layoutToolOptions.topControl = cmpTextOptions;
		}
		else if (toolId == ToolSelection.WIZARD_VALUE)
		{
			layoutToolOptions.topControl = cmpWizardOptions;
		}
		else if (toolId == ToolSelection.SPRAY_VALUE)
		{
			layoutToolOptions.topControl = cmpSprayOptions;
		}
		cmpToolOptions.layout();
	}

	/**
	 * sets the tilesetpanels cursor according to the tool selected
	 * 
	 * @param toolInt
	 *            the tool id
	 */
	protected void updateCursorOnTileset(int toolId)
	{
		if (toolId == ToolSelection.PENCIL_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_pencil.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 0, 31));
		}
		else if (toolId == ToolSelection.LINE_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_crossfade.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 10, 10));
		}
		else if (toolId == ToolSelection.ELLIPSE_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_crossfade.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 10, 10));
		}
		else if (toolId == ToolSelection.ENUMERATION_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_number.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 0, 0));
		}
		else if (toolId == ToolSelection.FILL_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_fill.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 1, 31));
		}
		else if (toolId == ToolSelection.MAGNIFIER_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_magnifier.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 7, 7));
		}
		else if (toolId == ToolSelection.MARK_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_crossfade.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 10, 10));
		}
		else if (toolId == ToolSelection.PALETTE_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_palette.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 1, 31));
		}
		else if (toolId == ToolSelection.PIPETTE_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_pipette.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 0, 31));
		}
		else if (toolId == ToolSelection.RECTANGLE_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_crossfade.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 10, 10));
		}
		else if (toolId == ToolSelection.TEXT_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_text.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 0, 0));
		}
		else if (toolId == ToolSelection.WIZARD_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_wizard.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 1, 1));
		}
		else if (toolId == ToolSelection.SPRAY_VALUE)
		{
			Image img = ResourceManager.getPluginImage("com.flow.snesde.editors.tileeditor",
			        "graphics/cursors/cursor_spray.png");
			tilesetPanel.setCursor(new Cursor(null, img.getImageData(), 1, 1));
		}
	}

	/**
	 * activates / deactivates temporary drawing stuff on the tileset panel
	 * depending on the selected tool
	 * 
	 * @param toolId
	 *            the tool id
	 */
	protected void updateTempDrawingOnTileset(int toolId)
	{
		// deactivate all temporary drawing stuff
		tilesetPanel.setHighlightHoveringPixel(false);
		tilesetPanel.setHighlightHoveringTile(false);
		tilesetPanel.setDrawTempLineCircle(false);
		tilesetPanel.setDrawTempLineSquare(false);
		tilesetPanel.setRectangleOnTempBuffer(false);

		// reactivate based on tool & tool options selection
		if (toolId == ToolSelection.PENCIL_VALUE)
		{
			tilesetPanel.setTempLineCircleSize(spnSize_Pencil.getSelection());
			tilesetPanel.setTempLineSquareSize(spnSize_Pencil.getSelection());
			if (tltmRect_Pencil.getSelection())
			{
				tilesetPanel.setDrawTempLineSquare(true);
			}
			else if (tltmRound_Pencil.getSelection())
			{
				tilesetPanel.setDrawTempLineCircle(true);
			}
		}
		else if (toolId == ToolSelection.LINE_VALUE)
		{
			tilesetPanel.setTempLineCircleSize(spnSize_Line.getSelection());
			tilesetPanel.setTempLineSquareSize(spnSize_Line.getSelection());
			if (tltmSquare_Line.getSelection())
			{
				tilesetPanel.setDrawTempLineSquare(true);
			}
			else if (tltmRound_Line.getSelection())
			{
				tilesetPanel.setDrawTempLineCircle(true);
			}
		}
		else if (toolId == ToolSelection.ELLIPSE_VALUE)
		{
			tilesetPanel.setHighlightHoveringPixel(true);
			tilesetPanel.setRectangleOnTempBuffer(true);
		}
		else if (toolId == ToolSelection.ENUMERATION_VALUE)
		{
			tilesetPanel.setHighlightHoveringTile(true);
		}
		else if (toolId == ToolSelection.FILL_VALUE)
		{
			tilesetPanel.setHighlightHoveringPixel(true);
			if (tltm_singleTile_fill.getSelection())
			{
				tilesetPanel.setHighlightHoveringTile(true);
			}
		}
		else if (toolId == ToolSelection.MAGNIFIER_VALUE)
		{

		}
		else if (toolId == ToolSelection.MARK_VALUE)
		{
			tilesetPanel.setHighlightHoveringPixel(true);
			tilesetPanel.setRectangleOnTempBuffer(true);
		}
		else if (toolId == ToolSelection.PALETTE_VALUE)
		{
			tilesetPanel.setHighlightHoveringTile(true);
		}
		else if (toolId == ToolSelection.PIPETTE_VALUE)
		{
			tilesetPanel.setHighlightHoveringPixel(true);
		}
		else if (toolId == ToolSelection.RECTANGLE_VALUE)
		{
			tilesetPanel.setHighlightHoveringPixel(true);
			tilesetPanel.setRectangleOnTempBuffer(true);
		}
		else if (toolId == ToolSelection.TEXT_VALUE)
		{
			tilesetPanel.setHighlightHoveringTile(true);
		}
		else if (toolId == ToolSelection.WIZARD_VALUE)
		{
			if (tltm_singleTile_wizard.getSelection())
			{
				tilesetPanel.setHighlightHoveringTile(true);
			}
			tilesetPanel.setHighlightHoveringPixel(true);
			tilesetPanel.setRectangleOnTempBuffer(true);
		}
		else if (toolId == ToolSelection.SPRAY_VALUE)
		{
			tilesetPanel.setTempLineCircleSize(spnSize_spray.getSelection());
			tilesetPanel.setTempLineSquareSize(spnSize_spray.getSelection());
			if (tltmRect_spray.getSelection())
			{
				tilesetPanel.setDrawTempLineSquare(true);
			}
			else if (tltmCircle_spray.getSelection())
			{
				tilesetPanel.setDrawTempLineCircle(true);
			}
		}
	}

	/**
	 * adds non-tool-specific listeners to UI elements
	 */
	private void addListeners()
	{
		// zoom buttons
		lblZoomin.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// set the new zoomfactor
				ChangeCommand command = CommandFactory.create(SetZoomFactorCommand.class,
		                new CommandParameter(model, model.getZoomFactor() + 1));
				editingDomain.getCommandStack().execute(command);
			}
		});
		lblZoomout.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// set the new zoomfactor
				ChangeCommand command = CommandFactory.create(SetZoomFactorCommand.class,
		                new CommandParameter(model, model.getZoomFactor() - 1));
				editingDomain.getCommandStack().execute(command);
			}
		});
		lblZoomorig.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// set the new zoomfactor
				ChangeCommand command = CommandFactory.create(SetZoomFactorCommand.class,
		                new CommandParameter(model, 1));
				editingDomain.getCommandStack().execute(command);
			}
		});

		// enable/disable gridlines
		btnShowGrid.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				tilesetPanel.setShowGrid(btnShowGrid.getSelection());
			}
		});

		// select another palette
		btnChangePalette.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				PaletteSelectionDialog dlg = new PaletteSelectionDialog(
		                TileEditor.this.getSite().getWorkbenchWindow().getShell());
				initializePaletteModel();
				dlg.setOptions(availablePalettes);
				int retVal = dlg.open();
				if (retVal == IDialogConstants.OK_ID)
				{
					String selectedValue = dlg.getSelectedValue();
					if (selectedValue != null && !selectedValue.equals(""))
					{
						ChangeCommand command = CommandFactory.create(ChangePreferredPaletteCommand.class,
		                        new CommandParameter(model, selectedValue));
						editingDomain.getCommandStack().execute(command);

					}
				}
			}
		});

		// select the new color index
		palettePanel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				int clickedColorIndex = palettePanel.getClickedColorIndex(e.x, e.y);
				if (e.button == 3)
				{
					ChangeCommand command = CommandFactory.create(SetRightSelectedColorCommand.class,
		                    new CommandParameter(model, clickedColorIndex));
					editingDomain.getCommandStack().execute(command);
				}
				else
				{
					ChangeCommand command = CommandFactory.create(SetLeftSelectedColorCommand.class,
		                    new CommandParameter(model, clickedColorIndex));
					editingDomain.getCommandStack().execute(command);
				}
			}
		});

		// key listener for copy/paste/cut commands in tilesetpanel
		tilesetPanel.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				if ((e.stateMask & SWT.CTRL) == SWT.CTRL && e.keyCode == 'c')
				{
					copyPixels();
				}
				else if ((e.stateMask & SWT.CTRL) == SWT.CTRL && e.keyCode == 'v')
				{
					pastePixels();
				}
				else if ((e.stateMask & SWT.CTRL) == SWT.CTRL && e.keyCode == 'x')
				{
					cutPixels();
				}
				else if (e.keyCode == SWT.DEL)
				{
					deletePixels();
				}
			}
		});

		// this will make sure everything within the editor reacts properly on
		// tool selection.
		SelectionAdapter selectToolSelectionAdapter = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				int toolInt = 0;
				if (e.getSource().equals(tbToolSelection.getTltmEllipse()))
				{
					toolInt = TwoColumnToolbar.TOOL_ELLIPSE;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmEnumeration()))
				{
					toolInt = TwoColumnToolbar.TOOL_ENUMERATION;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmFill()))
				{
					toolInt = TwoColumnToolbar.TOOL_FILL;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmLine()))
				{
					toolInt = TwoColumnToolbar.TOOL_LINE;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmMagnifier()))
				{
					toolInt = TwoColumnToolbar.TOOL_MAGNIFIER;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmMark()))
				{
					toolInt = TwoColumnToolbar.TOOL_MARK;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmPalette()))
				{
					toolInt = TwoColumnToolbar.TOOL_PALETTE;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmPencil()))
				{
					toolInt = TwoColumnToolbar.TOOL_PENCIL;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmPipette()))
				{
					toolInt = TwoColumnToolbar.TOOL_PIPETTE;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmRectangle()))
				{
					toolInt = TwoColumnToolbar.TOOL_RECTANGLE;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmText()))
				{
					toolInt = TwoColumnToolbar.TOOL_TEXT;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmWizard()))
				{
					toolInt = TwoColumnToolbar.TOOL_WIZARD;
				}
				else if (e.getSource().equals(tbToolSelection.getTltmSpray()))
				{
					toolInt = TwoColumnToolbar.TOOL_SPRAY;
				}

				// make sure there are no tempoary artifacts showing when
		        // switching tools
				tilesetPanel.clearPixelBuffer();

				// make sure no perm buffer persists
				potentiallyUnmarkPermBuffer();

				// change tool in model
				ChangeCommand command = CommandFactory.create(ChangeSelectedToolCommand.class,
		                new CommandParameter(model, toolInt));
				editingDomain.getCommandStack().execute(command);

				// add listeners to tilesetpanel for actually
		        // dispatching change commands for pencil paint events
				updateToolListenersOnTilesetPanel(toolInt);
				// set the top composite for tool options selection
				updateToolOptionsComposite(toolInt);
				// set the cursor for the current tool
				updateCursorOnTileset(toolInt);
				// update temporary drawing stuff on tileset for current tool
				updateTempDrawingOnTileset(toolInt);
			}
		};

		// assign the listener to all tool radiobuttons
		tbToolSelection.getTltmEllipse().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmEnumeration().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmFill().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmLine().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmMagnifier().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmMark().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmPalette().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmPencil().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmPipette().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmRectangle().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmText().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmWizard().addSelectionListener(selectToolSelectionAdapter);
		tbToolSelection.getTltmSpray().addSelectionListener(selectToolSelectionAdapter);

		// this listener changes the temporary drawings on the tilesetpanel for
		// given selected tool and tool preferences
		SelectionListener updateToolTempDrawingListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				updateTempDrawingOnTileset(model.getTool().getValue());
			}
		};
		// assign it to these tool preference widgets
		tltmRound_Pencil.addSelectionListener(updateToolTempDrawingListener);
		tltmRect_Pencil.addSelectionListener(updateToolTempDrawingListener);
		spnSize_Pencil.addSelectionListener(updateToolTempDrawingListener);
		tltmRound_Line.addSelectionListener(updateToolTempDrawingListener);
		tltmSquare_Line.addSelectionListener(updateToolTempDrawingListener);
		spnSize_Line.addSelectionListener(updateToolTempDrawingListener);
		tltm_multiTile_wizard.addSelectionListener(updateToolTempDrawingListener);
		tltm_singleTile_wizard.addSelectionListener(updateToolTempDrawingListener);
		tltm_ignoreBorders_fill.addSelectionListener(updateToolTempDrawingListener);
		tltm_singleTile_fill.addSelectionListener(updateToolTempDrawingListener);
		tltmRect_spray.addSelectionListener(updateToolTempDrawingListener);
		tltmCircle_spray.addSelectionListener(updateToolTempDrawingListener);
		spnSize_spray.addSelectionListener(updateToolTempDrawingListener);

		// TODO: somehow add a selection listener to the tilesetPanel so we can
		// get proper results for ISelection for copy/paste stuff
		// tilesetPanel.addSelectionListener(new SelectionListener() {
		// public void widgetSelected(SelectionEvent e) {
		// System.out.println("hallo");
		// }
		//
		// public void widgetDefaultSelected(SelectionEvent e) {
		// System.out.println("Default");
		// }
		// });
	}

	/**
	 * copies the marked pixels of the tileset to the common editor shared
	 * buffer
	 */
	private void copyPixels()
	{
		if (model.getPermanentBuffer() != null && model.getPermanentBuffer().getBufferedPixels() != null
		        && model.getPermanentBuffer().getBufferedPixels().size() > 0)
		{
			// System.out.println("copy marked pixels");
			setSystemClipboardSharedBuffer(tilesetPanel.getPermanentBuffer());
		}
	}

	/**
	 * switches to the mark tool eventually (in case it wasn't selected before)
	 * and pastes a new selection of marked pixels created by the tileseteditors
	 * shared pixel buffer
	 */
	private void pastePixels()
	{
		if (model.getTool() != ToolSelection.MARK)
		{
			// do everything needed to switch to mark tool
			int toolInt = TwoColumnToolbar.TOOL_MARK;
			// make sure there are no tempoary artifacts showing when
			// switching tools
			tilesetPanel.clearPixelBuffer();

			// change tool in model
			ChangeCommand command = CommandFactory.create(ChangeSelectedToolCommand.class,
			        new CommandParameter(model, toolInt));
			editingDomain.getCommandStack().execute(command);

			// add listeners to tilesetpanel for actually
			// dispatching change commands for pencil paint events
			updateToolListenersOnTilesetPanel(toolInt);
			// set the top composite for tool options selection
			updateToolOptionsComposite(toolInt);
			// set the cursor for the current tool
			updateCursorOnTileset(toolInt);
			// update temporary drawing stuff on tileset for current tool
			updateTempDrawingOnTileset(toolInt);
		}
		// now mark tool is selected
		// System.out.println("paste clipboard content");
		if (model.getPermanentBuffer() != null && model.getPermanentBuffer().getBufferedPixels() != null
		        && model.getPermanentBuffer().getBufferedPixels().size() > 0)
		{
			// there is already a perm buffer -> write back to tileset and
			// create new one
			ChangeCommand command = CommandFactory.create(UnmarkAreaCommand.class, new CommandParameter(model));
			editingDomain.getCommandStack().execute(command);
		}
		// run command to create permanent buffer out of getSharedBuffer();
		ChangeCommand command = CommandFactory.create(PasteCopiedPixelsCommand.class,
		        new CommandParameter(model, getSystemClipboardSharedBuffer()));
		editingDomain.getCommandStack().execute(command);
		bindingContext.removeBinding(permanentBufferBinding);
		bindingContext.addBinding(permanentBufferBinding);
		permanentBufferBinding.updateModelToTarget();
		tilesetPanel.setShowPermanentBuffer(true);
		tilesetPanel.setTemporarilyHidePermanentBuffer(false);
	}

	/**
	 * cuts the pixels into the tileseteditors shared pixel buffer
	 */
	private void cutPixels()
	{
		if (model.getPermanentBuffer() != null && model.getPermanentBuffer().getBufferedPixels() != null
		        && model.getPermanentBuffer().getBufferedPixels().size() > 0)
		{
			// System.out.println("cut marked pixels");
			// copy perm buffer into shared buffer
			setSystemClipboardSharedBuffer(tilesetPanel.getPermanentBuffer());
			// clear perm buffer via command and unset it
			ChangeCommand command = CommandFactory.create(CutMarkedPixelsCommand.class, new CommandParameter(model));
			editingDomain.getCommandStack().execute(command);
		}
	}

	/**
	 * deletes the marked pixels from the tileset
	 */
	private void deletePixels()
	{
		if (model.getPermanentBuffer() != null && model.getPermanentBuffer().getBufferedPixels() != null
		        && model.getPermanentBuffer().getBufferedPixels().size() > 0)
		{
			// System.out.println("remove marked pixels");
			// clear perm buffer via command and unset it
			ChangeCommand command = CommandFactory.create(CutMarkedPixelsCommand.class, new CommandParameter(model));
			editingDomain.getCommandStack().execute(command);
		}
	}

	@Override
	public void setFocus()
	{
		// Set the focus
	}

	/**
	 * This is here for the listener to be able to call it.
	 */
	@Override
	protected void firePropertyChange(int action)
	{
		super.firePropertyChange(action);
	}

	/**
	 * This will be checked to decide weather or not to enable save
	 * functionality
	 */
	@Override
	public boolean isDirty()
	{
		return ((BasicCommandStack) this.editingDomain.getCommandStack()).isSaveNeeded();
	}

	@Override
	public void dispose()
	{
		try
		{
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
			// super.dispose();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * looks into the model and serializes any visible perm buffer if exists.
	 * Discards it in case it is not being displayed
	 */
	protected void potentiallyUnmarkPermBuffer()
	{
		// perm buffer should be unmarked before saving...
		if (model.getPermanentBuffer() != null && model.getPermanentBuffer().getBufferedPixels() != null
		        && !model.getPermanentBuffer().getBufferedPixels().isEmpty())
		{
			if (tilesetPanel.isShowPermanentBuffer() && !tilesetPanel.isTemporarilyHidePermanentBuffer())
			{
				ChangeCommand command = CommandFactory.create(UnmarkAreaCommand.class, new CommandParameter(model));
				editingDomain.getCommandStack().execute(command);
				tilesetPanel.setShowPermanentBuffer(false);
			}
			else
			{
				model.setPermanentBuffer(null);
			}
		}
	}

	@Override
	public void doSave(final IProgressMonitor monitor)
	{
		// we never want to serialize the perm buffer
		potentiallyUnmarkPermBuffer();
		// Do the work within an operation because this is a long running
		// activity
		// that modifies the workbench.
		try
		{
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
			{
				@Override
				public void execute(final IProgressMonitor monitor) throws ResourceException
				{
					try
					{
						// Create a resource set
						ResourceSet resourceSet = editingDomain.getResourceSet();
						// Get the URI of the palette file.
						URI fileURI = URI.createPlatformResourceURI(inputFile.getFullPath().toString(), true);
						// Create a resource for this file.
						Resource resource = resourceSet.createResource(fileURI);

						// create palette object and add to resource for saving
						if (model != null)
						{
							resource.getContents().add(model);
						}

						// Save the contents of the resource to the file system.
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING, "UTF-8");
						resource.save(options);
						inputFile.refreshLocal(IResource.DEPTH_ZERO, monitor);
						((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					catch (CoreException e)
					{
						e.printStackTrace();
					}
				}
			};
			operation.run(monitor);
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs()
	{
		// TODO: enable this
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		// TODO: enable this
		return false;
	}

	/**
	 * Executed once the resource has been changed by an external program and
	 * the editor should be notified about it At this point in time, it only
	 * reacts upon the project being closed/deleted -> editor will close too
	 * 
	 * @param event
	 *            the resource change event
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event)
	{
		IResourceDelta resourceDelta = event.getDelta();

		if (resourceDelta != null)
		{
			IResourceDelta[] removedDeltas = resourceDelta.getAffectedChildren(IResourceDelta.REMOVED);
			IResourceDelta[] preDeleteDeltas = resourceDelta.getAffectedChildren(IResourceChangeEvent.PRE_DELETE);
			ArrayList<IResourceDelta> deltaList = new ArrayList<IResourceDelta>();
			if ((removedDeltas != null) && (removedDeltas.length != 0))
			{
				deltaList.addAll(Arrays.asList(removedDeltas));
			}
			if ((preDeleteDeltas != null) && (preDeleteDeltas.length != 0))
			{
				deltaList.addAll(Arrays.asList(preDeleteDeltas));
			}

			IProject project = null;
			for (IResourceDelta delta : deltaList)
			{
				IResource resource = delta.getResource();
				IFile input = ((FileEditorInput) getEditorInput()).getFile();

				if (input != null)
				{
					project = input.getProject();
					if ((project != null) && (resource != null) && (resource instanceof IProject)
					        && (resource.getName().equals(project.getName())))
					{
						if ((delta.getKind() == IResourceDelta.REMOVED) || (!input.exists()))
						{
							// TODO: close editor
							// close(false);
							return;
						}
					}
				}
			}
		}
	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException
	{
		setSite(site);
		setInput(input);

		if (!(input instanceof IFileEditorInput) && !(input instanceof FileStoreEditorInput))
		{
			throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput");
		}
		ProgressMonitorDialog pmdialog = new ProgressMonitorDialog(site.getShell());
		try
		{
			pmdialog.run(false, false, new IRunnableWithProgress()
			{

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					monitor.beginTask("Opening editor...", IProgressMonitor.UNKNOWN);
					try
					{
						createModel(site, input);
					}
					catch (PartInitException e)
					{
						e.printStackTrace();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					finally
					{
						monitor.done();
					}
				}
			});
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// initialize palette model
		initializePaletteModel();
	}

	/**
	 * initializes the set of available color palettes for the given tileset
	 * (with the names of palettes in this project with the number of colors)
	 */
	private void initializePaletteModel()
	{
		// get preferred palette name
		preferredPaletteCombinedId = model.getPaletteName();

		// get encoding to select the proper palettes
		int colors = 2;
		Encoding encoding = model.getEncoding();
		if (encoding.equals(Encoding.ENC1BPP2COLORS))
		{
			colors = 2;
		}
		else if (encoding.equals(Encoding.ENC2BPP4COLORS))
		{
			colors = 4;
		}
		else if (encoding.equals(Encoding.ENC3BPP8COLORS))
		{
			colors = 8;
		}
		else if (encoding.equals(Encoding.ENC4BPP16COLORS))
		{
			colors = 16;
		}
		else if (encoding.equals(Encoding.ENC8BPP256COLORS))
		{
			colors = 256;
		}

		try
		{
			// collect all relevant palettes
			SnesdeProjectRoot projectRoot = FlowWorkspace.getProjectRoot(inputFile.getProject());
			HashSet<PaletteObject> palettes = projectRoot.getPalettesForColorDepth(colors);
			availablePalettes = new HashSet<String>();
			for (PaletteObject p : palettes)
			{
				availablePalettes.add(p.getName() + " (" + p.getIdentifier() + ")");
			}
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates the model of the file being processed
	 * 
	 * @param site
	 *            the editor site
	 * @param input
	 *            the IFileEditorInput to open
	 * @throws Exception
	 */
	private void createModel(final IEditorSite site, final IEditorInput input) throws Exception
	{
		// store it for future reference

		if (input instanceof IFileEditorInput)
		{
			inputFile = ((IFileEditorInput) input).getFile();
		}
		setPartName(inputFile.getName());

		URI inputUri = URI.createPlatformResourceURI(inputFile.getFullPath().toString(), true);
		Resource res = editingDomain.getResourceSet().getResource(inputUri, true);
		res.load(null);

		model = (TilesetImpl) res.getContents().get(0);

		// listen for changes on this IFile so we can close the editor if the
		// project has been closed/deleted
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	@Override
	public EditingDomain getEditingDomain()
	{
		// needed for the EditingDomainActionBarContributor enabling undo/redo
		// stuff
		return this.editingDomain;
	}

	/**
	 * initializes the databindings (mostly) updating GUI elements from the
	 * model (as model updates from the GUI are done using commands)
	 * 
	 * @return
	 */
	protected DataBindingContext initDataBindings()
	{
		bindingContext = new DataBindingContext();
		//
		IObservableValue zoomFactorTilesetPanelObserveValue = PojoProperties.value("zoomFactor").observe(tilesetPanel);
		IObservableValue modelZoomFactorObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__ZOOM_FACTOR);
		bindingContext.bindValue(zoomFactorTilesetPanelObserveValue, modelZoomFactorObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue textSpnZoomObserveValue = PojoProperties.value("selection").observe(spnZoom);
		bindingContext.bindValue(textSpnZoomObserveValue, modelZoomFactorObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue colorsPalettePanelObserveValue = PojoProperties.value("tiles").observe(tilesetPanel);
		IObservableValue modelColorsObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__TILES);
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setConverter(new EmfTilesToInternalTilesConverter());
		tilesetBinding = bindingContext.bindValue(colorsPalettePanelObserveValue, modelColorsObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy);
		//
		IObservableValue observeSelectionSpnColumnsObserveWidget = WidgetProperties.selection().observe(spnColumns);
		IObservableValue modelColumnsObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__COLUMNS);
		bindingContext.bindValue(observeSelectionSpnColumnsObserveWidget, modelColumnsObserveValue, null, null);
		//
		IObservableValue observeTilesetPanelColumnsObserveWidget = PojoProperties.value("columns")
		        .observe(tilesetPanel);
		bindingContext.bindValue(observeTilesetPanelColumnsObserveWidget, modelColumnsObserveValue, null, null);
		//
		IObservableValue observeTextTxtPaletteNameObserveWidget = WidgetProperties.text().observe(txtPaletteName);
		IObservableValue modelPaletteNameObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__PALETTE_NAME);
		UpdateValueStrategy strategy_1 = new UpdateValueStrategy();
		strategy_1.setConverter(new ComposedPaletteNameToSimplePaletteNameConverter());
		bindingContext.bindValue(observeTextTxtPaletteNameObserveWidget, modelPaletteNameObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy_1);
		//
		IObservableValue colorsPalettePanelObserveValue_1 = PojoProperties.value("colors").observe(palettePanel);
		UpdateValueStrategy strategy_2 = new UpdateValueStrategy();
		strategy_2.setConverter(new ComposedPaletteIDToNonNativeColorArrayConverter());
		bindingContext.bindValue(colorsPalettePanelObserveValue_1, modelPaletteNameObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy_2);
		//
		IObservableValue colorsTilesetPanelObserveValue = PojoProperties.value("colors").observe(tilesetPanel);
		UpdateValueStrategy strategy_3 = new UpdateValueStrategy();
		strategy_3.setConverter(new ComposedPaletteIDToNonNativeColorArrayConverter());
		bindingContext.bindValue(colorsTilesetPanelObserveValue, modelPaletteNameObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy_3);
		//
		IObservableValue colorsSelectedColorsObserveValue = PojoProperties.value("colors").observe(selectedColors);
		UpdateValueStrategy strategy_4 = new UpdateValueStrategy();
		strategy_4.setConverter(new ComposedPaletteIDToNonNativeColorArrayConverter());
		bindingContext.bindValue(colorsSelectedColorsObserveValue, modelPaletteNameObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy_4);
		//
		IObservableValue leftSelectionIndexPalettePanelObserveValue = PojoProperties.value("leftSelectionIndex")
		        .observe(palettePanel);
		IObservableValue modelLeftIndexObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__LEFT_INDEX);
		bindingContext.bindValue(leftSelectionIndexPalettePanelObserveValue, modelLeftIndexObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue rightSelectionIndexPalettePanelObserveValue = PojoProperties.value("rightSelectionIndex")
		        .observe(palettePanel);
		IObservableValue modelRightIndexObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__RIGHT_INDEX);
		bindingContext.bindValue(rightSelectionIndexPalettePanelObserveValue, modelRightIndexObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue rightSelectedSelectedColorsObserveValue = PojoProperties.value("rightSelected")
		        .observe(selectedColors);
		bindingContext.bindValue(rightSelectedSelectedColorsObserveValue, modelRightIndexObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue leftSelectedSelectedColorsObserveValue = PojoProperties.value("leftSelected")
		        .observe(selectedColors);
		bindingContext.bindValue(leftSelectedSelectedColorsObserveValue, modelLeftIndexObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
		//
		IObservableValue toolTbToolSelectionObserveValue = PojoProperties.value("tool").observe(tbToolSelection);
		IObservableValue modelToolObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__TOOL);
		UpdateValueStrategy strategy_5 = new UpdateValueStrategy();
		strategy_5.setConverter(new DrawingModeToToolSelectionBarConverter());
		bindingContext.bindValue(toolTbToolSelectionObserveValue, modelToolObserveValue,
		        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy_5);
		//
		IObservableValue permanentBufferTilesetPanelObserveValue = PojoProperties.value("permanentBuffer")
		        .observe(tilesetPanel);
		IObservableValue modelPermanentBufferObserveValue = EMFEditObservables.observeValue(editingDomain, model,
		        Literals.TILESET__PERMANENT_BUFFER);
		UpdateValueStrategy strategy_6 = new UpdateValueStrategy();
		strategy_6.setConverter(new GlobalPixelsToPermanentBufferConverter());
		permanentBufferBinding = bindingContext.bindValue(permanentBufferTilesetPanelObserveValue,
		        modelPermanentBufferObserveValue, new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
		        strategy_6);
		//
		return bindingContext;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ISelection getSelection()
	{
		if (model.getTool() == ToolSelection.MARK)
		{
			// TODO add stuff here for selection of pixels in the tilesetPanel
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelection(ISelection selection)
	{
		// TODO Auto-generated method stub

	}
}
