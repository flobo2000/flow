/**
 * 
 */
package com.flow.snesde.core.perspectives;

import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;

import com.flow.snesde.core.util.GraphicsFactory;

/**
 * @author flo
 * 
 */
public class WelcomePage implements IIntroPart
{
	
	// VITAL : you must implement
	@Override
	public void createPartControl(final Composite container)
	{
		Composite outerContainer = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		outerContainer.setLayout(gridLayout);
		outerContainer.setBackground(outerContainer.getDisplay().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		Label label = new Label(outerContainer, SWT.CENTER);
		label.setText("WELCOME TO ECLIPSE");
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalAlignment = GridData.CENTER;
		gd.verticalAlignment = GridData.CENTER;
		label.setLayoutData(gd);
		label.setBackground(outerContainer.getDisplay().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
	}
	
	// VITAL : you must implement
	@Override
	public String getTitle()
	{
		return "My Title";
	}
	
	// VITAL : you must implement
	@Override
	public Image getTitleImage()
	{
		try
		{
			return GraphicsFactory.getImage("icons/32/snesde.png");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// return new Image(Display.getCurrent(), this.getClass()
		// .getResourceAsStream("splash.bmp"));
	}
	
	@Override
	public void addPropertyListener(final IPropertyListener listener)
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public void dispose()
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public IIntroSite getIntroSite()
	{
		// NON-VITAL : implement accordingly to your needs
		return null;
	}
	
	@Override
	public void init(final IIntroSite site, final IMemento memento)
			throws PartInitException
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public void removePropertyListener(final IPropertyListener listener)
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public void saveState(final IMemento memento)
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public void setFocus()
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public void standbyStateChanged(final boolean standby)
	{
		// NON-VITAL : implement accordingly to your needs
	}
	
	@Override
	public Object getAdapter(final Class adapter)
	{
		// NON-VITAL : implement accordingly to your needs
		return null;
	}
}
