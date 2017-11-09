package com.flow.snesde.uilib;

import java.awt.event.MouseAdapter;
import java.math.BigInteger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
//import org.eclipse.swt.graphics.RGBA;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.flow.snesde.uilib.tilesetPanel.TilesetPanel;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class TestShell
{

	protected Shell shell;
	private Display display;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			TestShell window = new TestShell();
			window.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setLayout(new GridLayout(2, false));
		
		Button btnSetclipboard = new Button(shell, SWT.NONE);
		btnSetclipboard.setText("setClipboard");
		btnSetclipboard.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				setClipboardContent();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnGetclipboard = new Button(shell, SWT.NONE);
		btnGetclipboard.setText("getClipboard");
		btnGetclipboard.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				getClipboardContent();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		// PalettePanel pal = new PalettePanel(shell, SWT.NONE);
		// pal.setDisplayOnly();

		// ScrolledComposite scrolledComposite = new ScrolledComposite(shell,
		// SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		// scrolledComposite.setExpandHorizontal(true);
		// scrolledComposite.setExpandVertical(true);

		// TilesetPanel composite = new TilesetPanel(scrolledComposite,
		// SWT.NONE);
		// scrolledComposite.setContent(composite);
		// scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT,
		// SWT.DEFAULT));

		//final TilesetPanel panel = new TilesetPanel(shell, SWT.NONE);
		
	}
	
	private void getClipboardContent() 
	{
		System.out.println("\nafter copy");
		final Clipboard clipboard = new Clipboard(display);
		
		ImageData imageData = (ImageData)clipboard.getContents(ImageTransfer.getInstance());
		
		if (imageData != null) 
		{
			int height = imageData.height;
			int width = imageData.width;
			for (int row = 0; row < height; row++)
			{
				for (int col = 0; col < width; col++)
				{
					String binString = Integer
							.toBinaryString(imageData.getPixel(col, row));
					while (binString.length() != 32)
					{
						binString = "0" + binString;
					}
					int b = Integer.parseInt(
							(binString.substring(
									binString.length() - 8,
									binString.length())), 2);
					int g = Integer.parseInt((binString.substring(
							binString.length() - 16,
							binString.length() - 8)), 2);
					int r = Integer.parseInt((binString.substring(
							binString.length() - 24,
							binString.length() - 16)), 2);
					int a = imageData.getAlpha(col, row);
					boolean transparent = false;
					if (a < 127)
					{
						transparent = true;
					}
					System.out.println("#" + col + "|" + row + ": rgb "
							+ r + "|" + g + "|" + b + " alpha: " + a);
				}
			}
		}
	}
	
	private void setClipboardContent()
	{
		//create ImageData
		
				int width = 8;
				int height = 8;
				//rgb colorspace of the SNES
				RGB[] rgbArray = new RGB[32768];
				int i=0;
				for (int r=0; r<=31; r++)
				{
					for (int g=0; g<=31; g++)
					{
						for (int b=0; b<=31; b++)
						{
							rgbArray[i] = new RGB(r*8, g*8, b*8);
							i++;
						}
					}
				}
				PaletteData pal = new PaletteData(rgbArray);
				ImageData img = new ImageData(width, height, 32, pal);
				for (int row = 0; row < height; row++)
				{
					for (int col = 0; col < width; col++)
					{
						if (row==0 && col==0)
						{
							img.setPixel(col, row, new RGB(0,0,0).hashCode());
						}
						else if (row==0 && col==1)
						{
							img.setPixel(col, row, new RGB(255,0,0).hashCode());
						}
						else if (row==0 && col==2)
						{
							img.setPixel(col, row, new RGB(0,255,0).hashCode());
						}
						else if (row==0 && col==3)
						{
							img.setPixel(col, row, new RGB(0,0,255).hashCode());
						}
//						else if (row==0 && col==4)
//						{
//							img.setPixel(col, row, new RGBA(0,0,0,0).hashCode());
//						}
//						else if (row==0 && col==5)
//						{
//							img.setPixel(col, row, new RGBA(255,0,0,127).hashCode());
//						}
//						else if (row==0 && col==6)
//						{
//							img.setPixel(col, row, new RGBA(0,255,0,127).hashCode());
//						}
//						else if (row==0 && col==7)
//						{
//							img.setPixel(col, row, new RGBA(0,0,255,127).hashCode());
//						}
					}
				}
				
				//set Image
				ImageTransfer imageTransfer = ImageTransfer.getInstance();

			    //set clipboard
				final Clipboard clipboard = new Clipboard(display);
				clipboard.setContents(new Object[]{img},
						new Transfer[]{imageTransfer});
				
				
				System.out.println("before copy");
				for (int row = 0; row < height; row++)
				{
					for (int col = 0; col < width; col++)
					{
						String binString = Integer
								.toBinaryString(img.getPixel(col, row));
						while (binString.length() != 32)
						{
							binString = "0" + binString;
						}
						int b = Integer.parseInt(
								(binString.substring(
										binString.length() - 8,
										binString.length())), 2);
						int g = Integer.parseInt((binString.substring(
								binString.length() - 16,
								binString.length() - 8)), 2);
						int r = Integer.parseInt((binString.substring(
								binString.length() - 24,
								binString.length() - 16)), 2);
						int a = img.getAlpha(col, row);
						boolean transparent = false;
						if (a < 127)
						{
							transparent = true;
						}
						System.out.println("#" + col + "|" + row + ": rgb "
								+ r + "|" + g + "|" + b + " alpha: " + a);
					}
				}
				
				getClipboardContent();
	}
}
