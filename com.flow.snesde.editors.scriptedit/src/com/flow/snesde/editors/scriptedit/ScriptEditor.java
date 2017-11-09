package com.flow.snesde.editors.scriptedit;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;

import com.flow.snesde.core.model.objects.SnesdeProjectRoot;

public class ScriptEditor extends TextEditor
{
	private SnesdeProjectRoot projectRoot;

	public ScriptEditor()
	{

	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input)
			throws PartInitException
	{
		super.init(site, input);

		if (input instanceof IFileEditorInput)
		{
			IFile file = ((IFileEditorInput) input).getFile();
			IProject project = file.getProject();
			// so we get syntax highlightening, code completion, etc.
			setSourceViewerConfiguration(new ScriptEditorSourceViewerConfiguration(
					project));
		}
	}

	@Override
	protected boolean isLineNumberRulerVisible()
	{
		// always show line numbers (for now)
		return true;
	}

	@Override
	protected boolean isOverviewRulerVisible()
	{
		// always show overview ruler (for now)
		return true;
	}

	@Override
	protected boolean isTabsToSpacesConversionEnabled()
	{
		// always convert tabs to spaces
		return true;
	}
}
