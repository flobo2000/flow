package com.flow.snesde.tileeditor.ui;

import java.util.HashSet;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class PaletteSelectionDialog extends Dialog
{

	private HashSet<String> Palettes;
	private List list;
	String selected = "";

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public PaletteSelectionDialog(Shell parentShell)
	{
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));

		Label lblColorPalettes = new Label(container, SWT.NONE);
		lblColorPalettes.setText("Select a Color Palette");

		ScrolledComposite scrolledComposite = new ScrolledComposite(container,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		list = new List(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(list);
		scrolledComposite
				.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		list.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] selectedItems = list.getSelection();
				if (selectedItems == null)
				{
					selected = null;
				}
				else
				{
					selected = selectedItems[0];
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub

			}
		});

		internalSetOptions();
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(450, 300);
	}

	public void setOptions(HashSet<String> availablePalettes)
	{
		this.Palettes = availablePalettes;
		internalSetOptions();
	}

	private void internalSetOptions()
	{
		if (list != null && Palettes != null)
		{
			list.removeAll();
			for (String s : Palettes)
			{
				list.add(s);
			}
		}
	}

	public String getSelectedValue()
	{
		return this.selected;
	}

}
