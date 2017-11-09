package com.flow.snesde.core.preferences.pages;

import java.io.FileNotFoundException;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.flow.snesde.core.Activator;
import com.flow.snesde.core.util.GraphicsFactory;

public class TilesetEditorPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage
{
	// TODO: see
	// http://www.eclipse.org/articles/Article-Field-Editors/field_editors.html

	/**
	 * the standard constructor
	 */
	public TilesetEditorPreferencePage()
	{
		super(FieldEditorPreferencePage.GRID);
	}

	/**
	 * The id of this page for reference in plugin.xml
	 */
	public static String id = "com.flow.snesde.core.preferences.pages.page1";

	@Override
	public void init(final IWorkbench workbench)
	{
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Tileset Editor preferences");
		ImageDescriptor desc;
		try
		{
			desc = GraphicsFactory.getIconAsImageDescriptor("snesde.png",
					GraphicsFactory.ICONSIZE_16x16);
			setImageDescriptor(desc);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void createFieldEditors()
	{
		// FileFieldEditor asmPath = new FileFieldEditor("ASMPATH",
		// "&Assembler executable:", true, getFieldEditorParent());
		// FileFieldEditor linkPath = new FileFieldEditor("LINKPATH",
		// "&Linker executable:", true, getFieldEditorParent());
		// FileFieldEditor emuPath = new FileFieldEditor("EMUPATH",
		// "&Emulator executable:", true, getFieldEditorParent());
		// addField(asmPath);
		// addField(linkPath);
		// addField(emuPath);
	}

}
