package com.flow.snesde.launchers.sneslauncher;

import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

public class SnesShortcutLauncher implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		//get Project the selected resource is contained in
		//verify if project is an SNESDE project
		//get default configured parameters from prefereces
		//execute common launcher with these parameters
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub

	}

}
