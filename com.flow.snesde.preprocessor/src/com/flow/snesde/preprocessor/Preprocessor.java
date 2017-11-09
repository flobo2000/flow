package com.flow.snesde.preprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.console.MessageConsoleStream;

import projectmeta.impl.MetadataImpl;

import com.flow.snesde.core.model.objects.SnesdeProjectRoot;
import com.flow.snesde.core.model.util.DuplicateFileException;
import com.flow.snesde.core.model.util.FlowWorkspace;
import com.flow.snesde.core.model.util.UnknownTypeException;
import com.flow.snesde.core.util.ResourceUtil;
import com.flow.snesde.preprocessor.metadatamapping.CarttypeMapping;
import com.flow.snesde.preprocessor.metadatamapping.CountrycodeMapping;
import com.flow.snesde.preprocessor.metadatamapping.LicenseeMapping;
import com.flow.snesde.preprocessor.metadatamapping.RamsizeMapping;
import com.flow.snesde.preprocessor.metadatamapping.RomsizeMapping;

public class Preprocessor
{
	public void preprocess(IProject project, IProgressMonitor monitor,
			MessageConsoleStream out, MessageConsoleStream err,
			MetadataImpl staticData) throws CoreException,
			DuplicateFileException, UnknownTypeException,
			PreprocessorException, NumberFormatException, IOException
	{
		// get projectRoot
		SnesdeProjectRoot projectRoot = FlowWorkspace.getProjectRoot(project);

		// make sure Output folder exists and is empty
		IFolder outfolder = project.getFolder("Output");
		if (!outfolder.exists())
		{
			outfolder.create(true, true, monitor);
		}
		else
		{
			clearFolder(outfolder, monitor);
		}

		LinkedList<String> outlines = new LinkedList<String>();

		// get main.dscipt reference
		IFile mainFile = projectRoot.getFileRef(
				SnesdeProjectRoot.FILETYPE_MAINSCRIPT, "main.dscript");

		outlines = preprocessScript(mainFile, outlines, project, out, err,
				staticData);
		BufferedWriter asmFile = null;
		try
		{
			asmFile = ResourceUtil.createBufferedWriter(project
					.getFile("Output/"
							+ staticData.getGamename().replaceAll("\\s+", "")
							+ ".asm"));
			for (String line : outlines)
			{
				asmFile.write(line + "\n");
			}
			asmFile.flush();
			asmFile.close();
		}
		catch (Exception e)
		{
			throw new PreprocessorException(
					"An error occurred trying to create preprocessed assembly file "
							+ staticData.getGamename() + ".");
		}
	}

	private LinkedList<String> preprocessScript(IFile file,
			LinkedList<String> outlines, IProject project,
			MessageConsoleStream out, MessageConsoleStream err,
			MetadataImpl staticData) throws PreprocessorException,
			CoreException, NumberFormatException, IOException,
			DuplicateFileException, UnknownTypeException
	{
		out.println("Preprocessing script " + file.getName());
		if (!file.exists())
		{
			throw new FileNotFoundException("Script " + file.getName()
					+ " doesn't exist.");
		}

		// PreprocessScript
		String filename = file.getName();
		String line;
		int lineNumber = 0;

		BufferedReader in = ResourceUtil.createBufferedReader(file);
		SnesdeProjectRoot projectRoot = FlowWorkspace.getProjectRoot(project);
		while ((line = in.readLine()) != null)
		{
			lineNumber++;
			// get comment for later appending
			String comment = getCommentForLine(line);
			line = line.split(";")[0];

			// handle flow include directives
			if (line.contains("flow.include("))
			{
				// remove all whitespace
				line = line.replaceAll("\\s+", "");
				if (line.contains("flow.include(script,"))
				{
					String scriptName = ((line
							.split("flow.include\\(script,"))[1])
							.split("\\)")[0];
					IFile script = projectRoot.getFileRef(
							SnesdeProjectRoot.FILETYPE_SCRIPT, scriptName
									+ ".dscript");
					outlines.add(";content of script '" + scriptName + "'");
					outlines = preprocessScript(script, outlines, project, out,
							err, staticData);
				}
				else if (line.contains("flow.include(palette,"))
				{
					// get palette file
					String paletteName = ((line
							.split("flow.include\\(palette,"))[1])
							.split("\\)")[0];
					out.println("Preprocessing palette " + paletteName);
					// add db commands for asm processing of this file
					outlines.add(";content of palette '" + paletteName + "'");
					IFile palette = projectRoot.getFileRef(
							SnesdeProjectRoot.FILETYPE_PALETTE, paletteName
									+ ".pal");
					outlines.add(PreprocessUtil.parseEmfModelFromIFile(palette));
				}
				else if (line.contains("flow.include(tileset,"))
				{
					// get tileset file
					String tilesetName = ((line
							.split("flow.include\\(tileset,"))[1])
							.split("\\)")[0];
					out.println("Preprocessing tileset " + tilesetName);
					// add db commands for asm processing of this file
					outlines.add(";content of tileset '" + tilesetName + "'");
					IFile tileset = projectRoot.getFileRef(
							SnesdeProjectRoot.FILETYPE_TILESET, tilesetName
									+ ".tile");
					outlines.add(PreprocessUtil.parseEmfModelFromIFile(tileset));
				}
				// TODO: add more include directives as the corresponding
				// objects are there
			}
			else if (line.contains("flow.staticdata("))
			{
				// remove all whitespace
				line = line.replaceAll("\\s+", "");
				String staticKey = (line.split("flow.staticdata\\(")[1])
						.split("\\)")[0];
				line = line.split("flow.staticdata\\(")[0] + " ";
				if (staticKey.equals("rombanksize"))
				{
					line = line + "$8000";
				}
				else if (staticKey.equals("numberofbanks"))
				{
					String romsize = staticData.getRomSize().split(" ")[0];
					int size = (Integer.parseInt(romsize)) * 4;
					line = line + size;
				}
				else if (staticKey.equals("shortname"))
				{
					line = line + "\"" + staticData.getShortname() + "\"";
				}
				else if (staticKey.equals("longname"))
				{
					line = line + "\"" + staticData.getGamename() + "\"";
				}
				else if (staticKey.equals("timing"))
				{
					String timing = staticData.getTiming();
					if (timing.contains("FASTROM"))
					{
						line = "FASTROM";
					}
					else
					{
						line = "SLOWROM";
					}
				}
				else if (staticKey.equals("adressing"))
				{
					String adressing = staticData.getAdressing();
					if (adressing.equalsIgnoreCase("lorom"))
					{
						line = "LOROM";
					}
					else
					{
						line = "HIROM";
					}
				}
				else if (staticKey.equals("carttype"))
				{
					// carttype from mapping document
					line = line
							+ "$"
							+ new CarttypeMapping().getCodeForLabel(staticData
									.getCartridgeType());
				}
				else if (staticKey.equals("romsize"))
				{
					// romsize from mapping document
					line = line
							+ "$"
							+ new RomsizeMapping().getCodeForLabel(staticData
									.getRomSize());
				}
				else if (staticKey.equals("ramsize"))
				{
					// ramsize from mapping document
					line = line
							+ "$"
							+ new RamsizeMapping().getCodeForLabel(staticData
									.getRamSize());
				}
				else if (staticKey.equals("countrycode"))
				{
					// countrycode from mapping document
					line = line
							+ "$"
							+ new CountrycodeMapping()
									.getCodeForLabel(staticData.getCountry());
				}
				else if (staticKey.equals("licensee"))
				{
					// licenseecode from mapping document
					line = line
							+ "$"
							+ new LicenseeMapping().getCodeForLabel(staticData
									.getLicensee());
				}
				else if (staticKey.equals("version"))
				{
					// convert integer from staticdata to $XX
					Integer i = new Integer(staticData.getVersion());
					line = line + "$" + i.toHexString(i);
				}
				// append comment
				line = appendCommentToLine(line, comment);
				outlines.add(line);
			}
			else
			{
				// regular line. add to outlist
				// append comment
				line = appendCommentToLine(line, comment);
				outlines.add(line);
			}
		}
		in.close();

		return outlines;
	}

	/**
	 * looks for semicolon (;) and cuts off everything before the first
	 * occurrance. returns the rest (which is effectively the comment of the
	 * passed line)
	 * 
	 * @param line
	 *            the line to parse
	 * @return the comment (if any). Empty string otherwise
	 */
	private String getCommentForLine(String line)
	{
		String[] parts = line.split(";");
		if (parts.length == 0)
		{
			return "";
		}
		else if (parts.length == 1 && line.startsWith(";"))
		{
			return line;
		}
		else if (parts.length == 1 && line.endsWith(";"))
		{
			return "";
		}
		else if (parts.length == 2)
		{
			return parts[1];
		}
		else if (parts.length > 2)
		{
			// concatenate the comment parts and return
			String comment = "";
			for (int i = 1; i < parts.length; i++)
			{
				comment = comment + ";" + parts[i];
			}
			return comment;
		}
		return "";
	}

	/**
	 * Appends the passed comment to the line if it contains anything
	 * 
	 * @param line
	 *            the line passed
	 * @param comment
	 *            the comment to append
	 * @return the line + the comment
	 */
	private String appendCommentToLine(String line, String comment)
	{
		if (comment == null || comment.equals(""))
		{
			return line;
		}
		else
		{
			return line + ";" + comment;
		}
	}

	/**
	 * Removes all files and folders of this IFolder recursively
	 * 
	 * @param folder
	 *            the IFolder to remove content from
	 * @param monitor
	 *            the job monitor showing current activity
	 * @throws CoreException
	 *             thrown if removal was unsuccessful
	 */
	private void clearFolder(IFolder folder, IProgressMonitor monitor)
			throws CoreException
	{
		IResource[] members = folder.members();
		for (IResource r : members)
		{
			if (r instanceof IFolder)
			{
				clearFolder((IFolder) r, monitor);
			}
			r.delete(true, monitor);
		}
	}
}
