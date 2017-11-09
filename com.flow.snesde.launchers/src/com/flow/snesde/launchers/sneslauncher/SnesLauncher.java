/**
 * 
 */
package com.flow.snesde.launchers.sneslauncher;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import projectmeta.impl.MetadataImpl;

import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.preprocessor.Preprocessor;

/**
 * @author flo
 * 
 */
public class SnesLauncher extends LaunchConfigurationDelegate
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 * eclipse .debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.debug.core.ILaunch,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(final ILaunchConfiguration configuration,
			final String mode, final ILaunch launch,
			final IProgressMonitor monitor) throws CoreException
	{
		final String projectName = configuration
				.getAttribute("projectName", "");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IProject project = root.getProject(projectName);

		SnesdeProjectRoot projectRoot1 = FlowWorkspace.getProjectRoot(project);
		MetadataImpl staticData = null;

		IFile metafile;
		String metareaderror = null;
		try
		{
			metafile = projectRoot1.getFileRef(
					SnesdeProjectRoot.FILETYPE_PROJECTMETA, "project.meta");
			URI inputUri = URI.createPlatformResourceURI(metafile.getFullPath()
					.toString(), true);
			ResourceSet resSet = new ResourceSetImpl();
			Resource res = resSet.getResource(inputUri, true);
			res.load(null);
			staticData = (MetadataImpl) res.getContents().get(0);
		}
		catch (Exception e)
		{
			metareaderror = e.getMessage();
		}

		final MetadataImpl staticData2 = staticData;
		final String metareaderror2 = metareaderror;

		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				// get the console
				MessageConsole console = getConsole(configuration.getName(),
						projectName);

				MessageConsoleStream out = console.newMessageStream();
				MessageConsoleStream err = console.newMessageStream();
				err.setColor(new Color(null, 255, 0, 0));
				MessageConsoleStream ok = console.newMessageStream();
				ok.setColor(new Color(null, 0, 255, 0));
				out.println("Compiling project \"" + projectName + "\"");

				out.println("\nStarting flow preprocessor...");

				if (metareaderror2 != null)
				{
					err.println(metareaderror2);
					return;
				}
				String gameName = (staticData2.getGamename()).replaceAll(
						"\\s+", "");
				// use snesde preprocessor
				Preprocessor prepro = new Preprocessor();
				MetadataImpl staticData2 = null;
				try
				{
					SnesdeProjectRoot projectRoot = FlowWorkspace
							.getProjectRoot(project);

					IFile metafile = projectRoot.getFileRef(
							SnesdeProjectRoot.FILETYPE_PROJECTMETA,
							"project.meta");
					URI inputUri = URI.createPlatformResourceURI(metafile
							.getFullPath().toString(), true);
					ResourceSet resSet = new ResourceSetImpl();
					Resource res = resSet.getResource(inputUri, true);
					res.load(null);
					staticData2 = (MetadataImpl) res.getContents().get(0);
					prepro.preprocess(project, monitor, out, err, staticData2);
				}
				catch (Exception e)
				{
					err.println("ERROR: " + e.getMessage());
					return;
				}

				// get output folder path
				IFolder outfolder = project.getFolder("Output");
				String outfolderPath = outfolder.getLocation().toFile()
						.getAbsolutePath();
				outfolderPath = outfolderPath + File.separator;

				// use wla compiler
				out.println("\nAssembling...");
				String asmpath = Platform.getPreferencesService().getString(
						"com.flow.snesde.core", "ASMPATH", "", null);
				executeCommandAndCaptureToConsole(asmpath + " -vo "
						+ outfolderPath + gameName + ".asm " + outfolderPath
						+ gameName + ".obj", out, err, ok);

				// TODO: use SPC compiler

				// create link file
				// TODO: add SPC parts to linker file
				out.println("\nCreating link file...");
				addLinkerFileToProject(project, gameName, out, err,
						outfolderPath);
				// use wla linker
				out.println("Linking...");
				String linkpath = Platform.getPreferencesService().getString(
						"com.flow.snesde.core", "LINKPATH", "", null);
				executeCommandAndCaptureToConsole(linkpath + " -vr "
						+ outfolderPath + gameName + ".link " + outfolderPath
						+ gameName + ".sfc", out, err, ok);

				// refresh output folder in tree viewer
				try
				{
					outfolder.refreshLocal(1, monitor);
				}
				catch (CoreException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// execute emulator with new rom
				out.println("\nStarting Emulator...");
				String emupath = Platform.getPreferencesService().getString(
						"com.flow.snesde.core", "EMUPATH", "", null);
				executeCommandAndCaptureToConsole(emupath + " " + outfolderPath
						+ gameName + ".sfc", out, err, ok);
			}
		});
	}

	/**
	 * 
	 * @param project
	 * @param err
	 * @param out
	 * @param outfolderPath
	 * @param linkerFilePath
	 */
	private void addLinkerFileToProject(IProject project, String gameName,
			MessageConsoleStream out, MessageConsoleStream err,
			String outfolderPath)
	{
		String linkFilename = gameName + ".link";
		// create files in a subdirectory of the project
		IFolder folder = project.getFolder("Output");
		IFile newFile = folder.getFile(linkFilename);
		try
		{
			createLinkerFile(newFile, linkFilename, gameName, outfolderPath);
		}
		catch (CoreException e)
		{
			err.println("An error occurred trying to create the link file");
			err.println(e.getMessage());
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (StackTraceElement s : stackTrace)
			{
				err.println(s.toString());
			}
		}
	}

	/**
	 * creates one folder within the project
	 * 
	 * @param folder
	 *            the new IFolder to be created
	 * @throws CoreException
	 *             can occur if the IFolder couldn't be created
	 */
	private void createFolder(final IFolder folder) throws CoreException
	{
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder)
		{
			createFolder((IFolder) parent);
		}
		if (!folder.exists())
		{
			folder.create(false, true, null);
		}
	}

	/**
	 * creates the Linkerfile iFile
	 * 
	 * @param file
	 *            the IFile to be created
	 * @param filename
	 *            the filename to be used
	 * @param gameName
	 *            the name of the game
	 * @param outfolderPath
	 * @throws CoreException
	 *             thrown if no file was created
	 */
	private void createLinkerFile(final IFile file, final String filename,
			String gameName, String outfolderPath) throws CoreException
	{
		if (!file.exists())
		{
			String stringToWrite = "[objects]\n" + outfolderPath
					+ File.separator + gameName + ".obj";
			byte[] bytes = stringToWrite.getBytes();
			InputStream source = new ByteArrayInputStream(bytes);
			file.create(source, IResource.NONE, null);
			file.refreshLocal(0, null);
		}
	}

	/**
	 * Returns a new console object to interface with and displays the console
	 * view
	 * 
	 * @return the MessageConsole
	 */
	private MessageConsole getConsole(String launchConfigName,
			String projectName)
	{
		String datetime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
				.format(new Date());
		String name = launchConfigName + " [SNES Project]: " + projectName
				+ " (" + datetime + ")";
		// Create and focus on the view named SNES Console
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
			{
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				String id = IConsoleConstants.ID_CONSOLE_VIEW;
				IConsoleView view = null;
				try
				{
					view = (IConsoleView) page.showView(id);
				}
				catch (PartInitException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.display(existing[i]);
				((MessageConsole) existing[i]).clearConsole();
				return (MessageConsole) existing[i];
			}
		// no console found, so create a new one
		MessageConsole console = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { console });

		// show the console view with this console
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view = null;
		try
		{
			view = (IConsoleView) page.showView(id);
		}
		catch (PartInitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.display(console);
		console.clearConsole();
		return console;
	}

	/**
	 * Executes the passed command and displays the output on the eclipse
	 * console
	 * 
	 * @param command
	 *            the command to be executed
	 * @param out
	 *            the MessageConsoleStream to display the output on
	 * @param err
	 *            the MessageConsoleStream to display errors through
	 * @param ok
	 */
	private void executeCommandAndCaptureToConsole(String command,
			MessageConsoleStream out, MessageConsoleStream err,
			MessageConsoleStream ok)
	{
		try
		{
			String line;
			out.println(command);
			// System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);

			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((line = input.readLine()) != null)
			{
				out.println(line);
				// System.out.println(line);
			}
			input.close();
			BufferedReader error = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			while ((line = error.readLine()) != null)
			{
				err.println(line);
				// System.out.println(line);
			}
		}
		catch (IOException e)
		{
			err.println("An error occurred trying to execute " + command);
			err.println(e.getMessage());
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (StackTraceElement s : stackTrace)
			{
				err.println(s.toString());
			}
		}

	}
}
