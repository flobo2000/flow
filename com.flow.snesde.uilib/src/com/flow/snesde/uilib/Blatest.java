package com.flow.snesde.uilib;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Blatest
{
	public static void main(String[] args)
	{
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);
		shell.setText("StackOverflow");
		shell.setLayout(new FillLayout());

		final Tree tree = new Tree(shell, SWT.NONE);

		for (int i = 0; i < 10; i++)
		{
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText("Parent " + i);

			for (int j = 0; j < 3; j++)
			{
				TreeItem child = new TreeItem(item, SWT.NONE);
				child.setText("Child " + i + " " + j);
			}
		}

		final Menu menu = new Menu(tree);
		tree.setMenu(menu);
		menu.addMenuListener(new MenuAdapter()
		{
			@Override
			public void menuShown(MenuEvent e)
			{
				MenuItem[] items = menu.getItems();
				for (int i = 0; i < items.length; i++)
				{
					items[i].dispose();
				}
				MenuItem newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText("Menu for " + tree.getSelection()[0].getText());
			}
		});

		shell.pack();
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
