package com.flow.snesde.uilib;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

public class TestAWTClipboard
{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					TestAWTClipboard window = new TestAWTClipboard();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestAWTClipboard()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][]", "[]"));

		JButton btnSetClipboard = new JButton("setClipboard");
		btnSetClipboard.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				setClipboardContent();
			}
		});
		frame.getContentPane().add(btnSetClipboard, "cell 0 0");

		JButton btnGetclipboard = new JButton("getClipboard");
		btnGetclipboard.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				getClipboardContent();
			}
		});
		frame.getContentPane().add(btnGetclipboard, "cell 1 0");
	}

	protected void setClipboardContent()
	{
		// create ImageData

		int width = 8;
		int height = 8;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				if (row == 0 && col == 0)
				{
					Color c = new Color(0, 0, 0, 255);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 1)
				{
					Color c = new Color(255, 0, 0, 255);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 2)
				{
					Color c = new Color(0, 255, 0, 255);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 3)
				{
					Color c = new Color(0, 0, 255, 255);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 4)
				{
					Color c = new Color(0, 0, 0, 0);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 5)
				{
					Color c = new Color(255, 0, 0, 127);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 6)
				{
					Color c = new Color(0, 255, 0, 127);
					img.setRGB(col, row, c.getRGB());
				}
				else if (row == 0 && col == 7)
				{
					Color c = new Color(0, 0, 255, 127);
					img.setRGB(col, row, c.getRGB());
				}
			}
		}

		// set image to clipboard
		try
		{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			ImageTransferable selection = new ImageTransferable(img);
			clipboard.setContents(selection, null);
		}
		catch (Exception x)
		{
			x.printStackTrace();
			System.exit(1);
		}

		System.out.println("before copy");
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				Color c = new Color(img.getRGB(col, row));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				int a = c.getAlpha();
				System.out.println("#" + col + "|" + row + ": rgb " + r + "|" + g + "|" + b + " alpha: " + a);
			}
		}

		getClipboardContent();
	}

	protected void getClipboardContent()
	{
		System.out.println("\nafter copy:");
		Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))
		{
			try
			{
				BufferedImage img = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
				int width = img.getWidth();
				int height = img.getHeight();
				for (int row = 0; row < height; row++)
				{
					for (int col = 0; col < width; col++)
					{
						Color c = new Color(img.getRGB(col, row), true);
						int r = c.getRed();
						int g = c.getGreen();
						int b = c.getBlue();
						int a = c.getAlpha();
						System.out.println("#" + col + "|" + row + ": rgb " + r + "|" + g + "|" + b + " alpha: " + a);
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
		else
		{
			System.err.println("getImageFromClipboard: That wasn't an image!");
		}
	}

	/**
	 * This class is a wrapper for the data transfer of image objects.
	 */
	class ImageTransferable implements Transferable
	{
		/**
		 * Constructs the selection.
		 * 
		 * @param image
		 *            an image
		 */
		public ImageTransferable(Image image)
		{
			theImage = image;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors()
		{
			return new DataFlavor[] { DataFlavor.imageFlavor };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor)
		{
			return flavor.equals(DataFlavor.imageFlavor);
		}

		@Override
		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
		{
			if (flavor.equals(DataFlavor.imageFlavor))
			{
				return theImage;
			}
			else
			{
				throw new UnsupportedFlavorException(flavor);
			}
		}

		private Image theImage;
	}
}
