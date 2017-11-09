/**
 * 
 */
package com.flow.snesde.tileeditor.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

/**
 * @author flo This class was necessary due to a bug in SWT in GTK/Linux. The
 *         problem is that wrapping of toolbar items is not properly possible.
 *         Therefore having two columns of toolbar items next to each other is
 *         not possible by default. This works around the problem by creating
 *         two separate toolbars next to each other which are automatically
 *         linked so that only one toolitem from either of the toolbars can be
 *         selected.
 */
public class TwoColumnToolbar extends Composite
{
	public static final int TOOL_PENCIL = 0;
	public static final int TOOL_LINE = 1;
	public static final int TOOL_PIPETTE = 2;
	public static final int TOOL_MAGNIFIER = 3;
	public static final int TOOL_FILL = 4;
	public static final int TOOL_MARK = 5;
	public static final int TOOL_RECTANGLE = 6;
	public static final int TOOL_ELLIPSE = 7;
	public static final int TOOL_ENUMERATION = 8;
	public static final int TOOL_TEXT = 9;
	public static final int TOOL_PALETTE = 10;
	public static final int TOOL_WIZARD = 11;
	public static final int TOOL_SPRAY = 12;

	private final ToolBar rightToolbar;
	private final ToolBar leftToolbar;
	private final ToolItem tltmPencil;
	private final ToolItem tltmPipette;
	private final ToolItem tltmFill;
	private final ToolItem tltmRectangle;
	private final ToolItem tltmPalette;
	private final ToolItem tltmEnumeration;
	private final ToolItem tltmLine;
	private final ToolItem tltmMagnifier;
	private final ToolItem tltmMark;
	private final ToolItem tltmEllipse;
	private final ToolItem tltmText;
	private final ToolItem tltmWizard;
	private final ToolItem tltmSpray;

	/**
	 * @param parent
	 * @param style
	 */
	public TwoColumnToolbar(final Composite parent, final int style)
	{
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		this.leftToolbar = new ToolBar(this, SWT.FLAT | SWT.RIGHT
				| SWT.VERTICAL);

		// pencil tool
		tltmPencil = new ToolItem(this.leftToolbar, SWT.RADIO);
		tltmPencil.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor",
				"graphics/tool_pencil.png"));
		tltmPencil.setToolTipText("draw freeform lines");
		tltmPencil.setSelection(true);
		tltmPencil.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// pipette tool
		tltmPipette = new ToolItem(this.leftToolbar, SWT.RADIO);
		tltmPipette.setImage(ResourceManager
				.getPluginImage("com.flow.snesde.editors.tileeditor",
						"graphics/tool_slurp.png"));
		tltmPipette.setToolTipText("select a color from another pixel");
		tltmPipette.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// marking tool
		tltmMark = new ToolItem(leftToolbar, SWT.RADIO);
		tltmMark.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor", "graphics/tool_mark.png"));
		tltmMark.setToolTipText("mark pixels for copying/moving/removing");
		tltmMark.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// ellipse tool
		tltmEllipse = new ToolItem(leftToolbar, SWT.RADIO);
		tltmEllipse
				.setImage(ResourceManager.getPluginImage(
						"com.flow.snesde.editors.tileeditor",
						"graphics/tool_oval.png"));
		tltmEllipse.setToolTipText("create an ellipse");
		tltmEllipse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// fill tool
		tltmFill = new ToolItem(this.leftToolbar, SWT.RADIO);
		tltmFill.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor", "graphics/tool_fill.png"));
		tltmFill.setToolTipText("fill an area with a given color");
		tltmFill.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// enumeration tool
		tltmEnumeration = new ToolItem(this.leftToolbar, SWT.RADIO);
		tltmEnumeration.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor",
				"graphics/tool_number.png"));
		tltmEnumeration.setToolTipText("add enumeration tiles");
		tltmEnumeration.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// magnifier tool
		tltmMagnifier = new ToolItem(leftToolbar, SWT.RADIO);
		tltmMagnifier
				.setImage(ResourceManager.getPluginImage(
						"com.flow.snesde.editors.tileeditor",
						"graphics/tool_zoom.png"));
		tltmMagnifier.setToolTipText("zoom in/out");
		tltmMagnifier.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		this.rightToolbar = new ToolBar(this, SWT.FLAT | SWT.RIGHT
				| SWT.VERTICAL);

		// line tool
		tltmLine = new ToolItem(rightToolbar, SWT.RADIO);
		tltmLine.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor", "graphics/tool_line.png"));
		tltmLine.setToolTipText("draw straight lines");
		tltmLine.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		tltmSpray = new ToolItem(rightToolbar, SWT.RADIO);
		tltmSpray.setImage(ResourceManager
				.getPluginImage("com.flow.snesde.editors.tileeditor",
						"graphics/tool_spray.png"));
		tltmSpray.setToolTipText("use spray can to paint");
		tltmSpray.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// wizard tool
		tltmWizard = new ToolItem(rightToolbar, SWT.RADIO);
		tltmWizard.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor",
				"graphics/tool_wizard.png"));
		tltmWizard.setToolTipText("mark areas of equal color");
		tltmWizard.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// rectangle tool
		tltmRectangle = new ToolItem(rightToolbar, SWT.RADIO);
		tltmRectangle
				.setImage(ResourceManager.getPluginImage(
						"com.flow.snesde.editors.tileeditor",
						"graphics/tool_rect.png"));
		tltmRectangle.setToolTipText("create a rectangle");
		tltmRectangle.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// palette tool
		tltmPalette = new ToolItem(rightToolbar, SWT.RADIO);
		tltmPalette.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor",
				"graphics/tool_palette.png"));
		tltmPalette.setToolTipText("set preferred palettes per tile");
		tltmPalette.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

		// text tool
		tltmText = new ToolItem(this.rightToolbar, SWT.RADIO);
		tltmText.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.editors.tileeditor", "graphics/tool_text.png"));
		tltmText.setToolTipText("add text");
		tltmText.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				setSelection((ToolItem) e.getSource());
			}
		});

	}

	/**
	 * If a new toolitem was selected, the old selection needs to be deactivated
	 * 
	 * @param t
	 *            the toolitem which was selected by the user
	 */
	private void setSelection(final ToolItem t)
	{
		// if there was any selection from the other toolbar (not the one the
		// currently selected element resides in), we have to deactivate it
		// manually
		ToolItem[] items = null;
		// make everything unselected
		items = this.rightToolbar.getItems();
		for (ToolItem i : items)
		{
			i.setSelection(false);
		}
		items = this.leftToolbar.getItems();
		for (ToolItem i : items)
		{
			i.setSelection(false);
		}
		t.setSelection(true);
	}

	/**
	 * sets the selection of the tool programmatically. Uses the literals
	 * TwoColumnToolbar.TOOL_XYZ. These are to be kept in sync with the ones
	 * inside the tileset ecore model so we don't need to convert anything in
	 * case we want to sync them
	 * 
	 * @param selectedTool
	 *            the selected toolItem
	 */
	public void setTool(int selectedTool)
	{
		switch (selectedTool)
		{
			case TOOL_ELLIPSE:
			{
				setSelection(tltmEllipse);
				break;
			}
			case TOOL_ENUMERATION:
			{
				setSelection(tltmEnumeration);
				break;
			}
			case TOOL_FILL:
			{
				setSelection(tltmFill);
				break;
			}
			case TOOL_LINE:
			{
				setSelection(tltmLine);
				break;
			}
			case TOOL_MAGNIFIER:
			{
				setSelection(tltmMagnifier);
				break;
			}
			case TOOL_MARK:
			{
				setSelection(tltmMark);
				break;
			}
			case TOOL_PALETTE:
			{
				setSelection(tltmPalette);
				break;
			}
			case TOOL_PENCIL:
			{
				setSelection(tltmPencil);
				break;
			}
			case TOOL_PIPETTE:
			{
				setSelection(tltmPipette);
				break;
			}
			case TOOL_RECTANGLE:
			{
				setSelection(tltmRectangle);
				break;
			}
			case TOOL_TEXT:
			{
				setSelection(tltmText);
				break;
			}
			case TOOL_WIZARD:
			{
				setSelection(tltmWizard);
				break;
			}
			case TOOL_SPRAY:
			{
				setSelection(tltmSpray);
				break;
			}
			default:
				break;
		}
	}

	/**
	 * returns the tool representing integer (note that these integers have been
	 * kept in sync with the ecore model enumeration for tilesets so we don't
	 * have to convert values).
	 * 
	 * @return the tool selection integer
	 */
	public int getTool()
	{
		if (tltmEllipse.getSelection())
		{
			return TOOL_ELLIPSE;
		}
		if (tltmEnumeration.getSelection())
		{
			return TOOL_ENUMERATION;
		}
		if (tltmFill.getSelection())
		{
			return TOOL_FILL;
		}
		if (tltmLine.getSelection())
		{
			return TOOL_LINE;
		}
		if (tltmMagnifier.getSelection())
		{
			return TOOL_MAGNIFIER;
		}
		if (tltmMark.getSelection())
		{
			return TOOL_MARK;
		}
		if (tltmPalette.getSelection())
		{
			return TOOL_PALETTE;
		}
		if (tltmPencil.getSelection())
		{
			return TOOL_PENCIL;
		}
		if (tltmPipette.getSelection())
		{
			return TOOL_PIPETTE;
		}
		if (tltmRectangle.getSelection())
		{
			return TOOL_RECTANGLE;
		}
		if (tltmWizard.getSelection())
		{
			return TOOL_WIZARD;
		}
		if (tltmSpray.getSelection())
		{
			return TOOL_SPRAY;
		}
		return TOOL_TEXT;
	}

	public ToolItem getTltmPencil()
	{
		return tltmPencil;
	}

	public ToolItem getTltmPipette()
	{
		return tltmPipette;
	}

	public ToolItem getTltmFill()
	{
		return tltmFill;
	}

	public ToolItem getTltmRectangle()
	{
		return tltmRectangle;
	}

	public ToolItem getTltmPalette()
	{
		return tltmPalette;
	}

	public ToolItem getTltmEnumeration()
	{
		return tltmEnumeration;
	}

	public ToolItem getTltmLine()
	{
		return tltmLine;
	}

	public ToolItem getTltmMagnifier()
	{
		return tltmMagnifier;
	}

	public ToolItem getTltmMark()
	{
		return tltmMark;
	}

	public ToolItem getTltmEllipse()
	{
		return tltmEllipse;
	}

	public ToolItem getTltmText()
	{
		return tltmText;
	}

	public ToolItem getTltmWizard()
	{
		return tltmWizard;
	}

	public ToolItem getTltmSpray()
	{
		return tltmSpray;
	}
}
