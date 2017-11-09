package com.flow.snesde.filecomputil.views;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import com.flow.snesde.filecomputil.pojos.ColorBytePojo;
import com.flow.snesde.filecomputil.uielements.ComparisonMatrix;
import com.flow.snesde.uilib.animatedGifLabel.AnimatedGifLabel;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class FileCompUtilView extends ViewPart
{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.flow.snesde.filecomputil.views.FileCompUtilView";
	private Action action1;
	private Action action2;
	private Text txtFolderToScan;
	private Text txtExtensionFilterCsv;
	private Label lblNumberoffilesfound;
	private Label lblNumberoffilteredfilesfound;
	private final HashSet<String> fileNames = new HashSet<String>();
	private final HashSet<String> filteredFileNames = new HashSet<String>();
	private final HashSet<String> fileExtensions = new HashSet<String>();
	private ComparisonMatrix compMatrix;
	private AnimatedGifLabel lblLoading;
	private Button btnSelectFolder;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	class ViewContentProvider implements IStructuredContentProvider
	{
		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput)
		{
		}

		@Override
		public void dispose()
		{
		}

		@Override
		public Object[] getElements(Object parent)
		{
			return new String[] { "One", "Two", "Three" };
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider
	{
		@Override
		public String getColumnText(Object obj, int index)
		{
			return getText(obj);
		}

		@Override
		public Image getColumnImage(Object obj, int index)
		{
			return getImage(obj);
		}

		@Override
		public Image getImage(Object obj)
		{
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	class NameSorter extends ViewerSorter
	{
	}

	/**
	 * The constructor.
	 */
	public FileCompUtilView()
	{
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent)
	{
		parent.setLayout(new GridLayout(8, false));

		Label lblFolderToCompare = new Label(parent, SWT.NONE);
		lblFolderToCompare.setText("Folder to compare:");

		lblNumberoffilesfound = new Label(parent, SWT.NONE);
		lblNumberoffilesfound.setAlignment(SWT.RIGHT);
		lblNumberoffilesfound.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 7, 1));

		txtFolderToScan = new Text(parent, SWT.BORDER);
		txtFolderToScan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 7, 1));

		btnSelectFolder = new Button(parent, SWT.NONE);
		btnSelectFolder.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		btnSelectFolder.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				DirectoryDialog fd = new DirectoryDialog(parent.getShell(),
						SWT.OPEN);
				fd.setText("Open directory...");
				String selected = fd.open();
				txtFolderToScan.setText(selected);
				File folder = new File(selected);
				File[] listOfFiles = folder.listFiles();

				fileNames.clear();
				for (int i = 0; i < listOfFiles.length; i++)
				{
					if (listOfFiles[i].isFile())
					{
						fileNames.add(listOfFiles[i].getName());
					}
				}

				updateLabelsAccordingToFilterList();
			}
		});
		btnSelectFolder.setText("...");

		Label lblFileExtensionscsv = new Label(parent, SWT.NONE);
		lblFileExtensionscsv.setText("File extensions (CSV):");

		lblNumberoffilteredfilesfound = new Label(parent, SWT.NONE);
		lblNumberoffilteredfilesfound.setAlignment(SWT.RIGHT);
		lblNumberoffilteredfilesfound.setLayoutData(new GridData(SWT.FILL,
				SWT.CENTER, false, false, 7, 1));

		txtExtensionFilterCsv = new Text(parent, SWT.BORDER);
		txtExtensionFilterCsv.setText("spc");
		txtExtensionFilterCsv.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(ModifyEvent e)
			{
				updateLabelsAccordingToFilterList();
			}
		});
		txtExtensionFilterCsv.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 8, 1));

		Label lblFileSimilarity = new Label(parent, SWT.NONE);
		lblFileSimilarity.setText("File Similarity:");

		Label lblSame = new Label(parent, SWT.NONE);
		lblSame.setText("different");

		Label lblColorfade = new Label(parent, SWT.NONE);
		lblColorfade.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
				false, 1, 1));
		lblColorfade.setImage(ResourceManager.getPluginImage(
				"com.flow.snesde.filecomputil", "icons/colorfade.png"));

		Label lblDifferent = new Label(parent, SWT.NONE);
		lblDifferent.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
		lblDifferent.setText("same");
		new Label(parent, SWT.NONE);

		lblLoading = new AnimatedGifLabel(parent, SWT.NONE);
		lblLoading.setOrientation(SWT.RIGHT_TO_LEFT);
		lblLoading
				.setGifImageFromPluginResource("com.flow.snesde.filecomputil/icons/loading.gif");
		lblLoading.setVisible(false);

		final Button btnAnalyze = new Button(parent, SWT.NONE);
		btnAnalyze.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				final String folderToScan = txtFolderToScan.getText();
				lblLoading.setVisible(true);
				btnSelectFolder.setEnabled(false);
				btnAnalyze.setEnabled(false);
				txtExtensionFilterCsv.setEnabled(false);
				txtFolderToScan.setEnabled(false);
				Job job = new Job("Comparing files...")
				{
					@Override
					protected IStatus run(IProgressMonitor monitor)
					{
						try
						{
							monitor.beginTask("Comparing files",
									filteredFileNames.size() + 2);
							monitor.subTask("Getting maximum filesize...");
							monitor.worked(1);
							long maxFileSize = 0;
							for (String filename : filteredFileNames)
							{
								// hand over the maximum filesize to the UI
								// component
								File file = new File(folderToScan
										+ File.separator + filename);
								if (file.length() > maxFileSize)
								{
									if (monitor.isCanceled())
										return Status.CANCEL_STATUS;
									maxFileSize = file.length();
								}
							}

							// calculate byte/pixel counters
							// create Hashmap for byte occurrance counting and
							// initialize with
							// proper size
							final ColorBytePojo byteCounter = new ColorBytePojo(
									maxFileSize, filteredFileNames.size());

							// fill Hashmap with byte occurrances for all
							// filtered files
							monitor.subTask("Filling counter object with byte counters...");
							for (String filename : filteredFileNames)
							{
								monitor.subTask("Processing " + filename
										+ "...");
								monitor.worked(1);
								int counter = 0;
								DataInputStream in = null;
								try
								{
									if (monitor.isCanceled())
										return Status.CANCEL_STATUS;
									File file = new File(folderToScan
											+ File.separator + filename);
									in = new DataInputStream(
											new FileInputStream(file));
									while (true)
									{
										byte byt = in.readByte();
										byteCounter.registerByteAtPosition(byt,
												counter);
										counter++;
									}
								}
								catch (EOFException e1)
								{
									try
									{
										in.close();
									}
									catch (IOException e2)
									{
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
								}
								catch (IOException e2)
								{
									// TODO Auto-generated catch block
									e2.printStackTrace();
									try
									{
										in.close();
										monitor.worked(10);
									}
									catch (IOException e1)
									{
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							monitor.subTask("Updating UI...");
							monitor.worked(1);
							Display.getDefault().asyncExec(new Runnable()
							{
								@Override
								public void run()
								{
									// Update UI here
									compMatrix.setBytesToDisplay(byteCounter);
									compMatrix.redraw();
									lblLoading.setVisible(false);
									btnSelectFolder.setEnabled(true);
									btnAnalyze.setEnabled(true);
									txtExtensionFilterCsv.setEnabled(true);
									txtFolderToScan.setEnabled(true);
								}
							});
						}
						finally
						{
						}
						return Status.OK_STATUS;
					}
				};
				job.addJobChangeListener(new JobChangeAdapter()
				{
					@Override
					public void done(IJobChangeEvent event)
					{
						Display.getDefault().asyncExec(new Runnable()
						{
							@Override
							public void run()
							{
								// reset loading animation
								lblLoading.setVisible(false);
								btnSelectFolder.setEnabled(true);
								btnAnalyze.setEnabled(true);
								txtExtensionFilterCsv.setEnabled(true);
								txtFolderToScan.setEnabled(true);
							}
						});
					}
				});
				job.setPriority(Job.SHORT);
				job.schedule(); // start as soon as possible

			}
		});

		btnAnalyze.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 2, 1));
		btnAnalyze.setText("Analyze!");

		compMatrix = new ComparisonMatrix(parent, SWT.NONE);
		compMatrix.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				8, 1));
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu()
	{
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener()
		{
			@Override
			public void menuAboutToShow(IMenuManager manager)
			{
				FileCompUtilView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars()
	{
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager)
	{
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager)
	{
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager)
	{
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions()
	{
		action1 = new Action()
		{
			@Override
			public void run()
			{
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action()
		{
			@Override
			public void run()
			{
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	private void hookDoubleClickAction()
	{
	}

	private void showMessage(String message)
	{
		// TODO do something maybe
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus()
	{
		// TODO do something probably
	}

	private void updateLabelsAccordingToFilterList()
	{
		fileExtensions.clear();
		filteredFileNames.clear();
		if (!txtExtensionFilterCsv.getText().equals(""))
		{
			String[] extensions = txtExtensionFilterCsv.getText().split(",");
			if (extensions.length > 0)
			{
				for (int i = 0; i < extensions.length; i++)
				{
					fileExtensions.add(extensions[i].trim().toLowerCase());
				}
			}
			for (String ext : fileExtensions)
			{
				for (String origFile : fileNames)
				{
					String file = origFile.toLowerCase();
					if (file.endsWith("." + ext))
					{
						filteredFileNames.add(origFile);
					}
				}
			}
		}
		else
		{
			for (String file : fileNames)
			{
				filteredFileNames.add(file);
			}
		}

		lblNumberoffilesfound.setText(fileNames.size() + " files in Folder");
		if (fileExtensions.size() == 0)
		{
			lblNumberoffilteredfilesfound.setText("No filter set.");
		}
		else
		{
			lblNumberoffilteredfilesfound.setText(filteredFileNames.size()
					+ " files with the correct extensions.");
		}
		lblNumberoffilesfound.redraw();
		lblNumberoffilteredfilesfound.redraw();
	}
}
