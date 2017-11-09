/**
 * 
 */
package com.flow.snesde.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import com.flow.snesde.core.Activator;

/**
 * @author flo
 * 
 */
@SuppressWarnings("restriction")
public class ResourceUtil
{
	/**
	 * @param f
	 *          the IFile to get the java.io.file for
	 * @return the java file corresponding to the IFile
	 * @throws ResourceException
	 *           may occur if the rousrce couldn't be opened
	 */
	public static File getFileForIFile(final IFile f) throws ResourceException
	{
		IPath location = f.getLocation();
		if (location == null)
		{
			throw new ResourceException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID, "No location for IFile " + f.getName()
							+ " found."));
		}
		File file = location.toFile();
		if (file == null)
		{
			throw new ResourceException(new Status(IStatus.ERROR,
					Activator.PLUGIN_ID, "File for IFile " + f.getName() + " was null."));
		}
		return file;
	}
	
	/**
	 * @param f
	 *          the file to get the IFile for
	 * @return the IFile returned
	 */
	public static IFile getIFileForFile(final File f)
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath location = Path.fromOSString(f.getAbsolutePath());
		IFile ifile = workspace.getRoot().getFile(location);
		return ifile;
	}
	
	/**
	 * @param path
	 *          the path to create the BufferedWriter to
	 * @return the BufferedWriter created
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 * @throws UnsupportedEncodingException
	 *           if UTF8 isn't supported
	 */
	public static BufferedWriter createBufferedWriter(final String path)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		return new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), "UTF8"));
	}
	
	/**
	 * @param file
	 *          the file to open the BufferedWriter for
	 * @return the BufferedWriter
	 * @throws UnsupportedEncodingException
	 *           if UTF8 isn't supported
	 * @throws FileNotFoundException
	 *           if the file couldn't be found
	 */
	public static BufferedWriter createBufferedWriter(final File file)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		return new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF8"));
	}
	
	/**
	 * @param file
	 *          the file to open the BufferedWriter for
	 * @return the BufferedWriter
	 * @throws UnsupportedEncodingException
	 *           if UTF8 isn't supported
	 * @throws FileNotFoundException
	 *           if the file couldn't be found
	 * @throws ResourceException
	 *           if the Resource wasn't found
	 */
	public static BufferedWriter createBufferedWriter(final IFile file)
			throws UnsupportedEncodingException, FileNotFoundException,
			ResourceException
	{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				getFileForIFile(file)), "UTF8"));
	}
	
	/**
	 * @param path
	 *          the path to create the BufferedReader from
	 * @return the BufferedReader
	 * @throws UnsupportedEncodingException
	 *           if UTF8 isn't suported
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 */
	public static BufferedReader createBufferedReader(final String path)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		return new BufferedReader(new InputStreamReader(new FileInputStream(path),
				"UTF8"));
	}
	
	/**
	 * @param file
	 *          the file to create the BufferedReader for
	 * @return the BufferedReader returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 * @throws UnsupportedEncodingException
	 *           if UTF8 isn't supported
	 */
	public static BufferedReader createBufferedReader(final File file)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		return new BufferedReader(new InputStreamReader(new FileInputStream(file),
				"UTF8"));
	}
	
	/**
	 * @param file
	 *          the file to create the BufferedReader for
	 * @return the BufferedReader returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 * @throws UnsupportedEncodingException
	 *           if UTF8 isn't supported
	 * @throws ResourceException
	 *           if the resource wasn't found
	 */
	public static BufferedReader createBufferedReader(final IFile file)
			throws UnsupportedEncodingException, FileNotFoundException,
			ResourceException
	{
		return new BufferedReader(new InputStreamReader(new FileInputStream(
				getFileForIFile(file)), "UTF8"));
	}
	
	/**
	 * @param path
	 *          the path to create the output stream for
	 * @return the output stream returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 */
	public static FileOutputStream createFileOutputStream(final String path)
			throws FileNotFoundException
	{
		return new FileOutputStream(path);
	}
	
	/**
	 * @param file
	 *          the file to create the output stream for
	 * @return the output stream returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 */
	public static FileOutputStream createFileOutputStream(final File file)
			throws FileNotFoundException
	{
		return new FileOutputStream(file);
	}
	
	/**
	 * @param file
	 *          the ifile to create the output stream for
	 * @return the output stream returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 * @throws ResourceException
	 *           if the resource wasn't found
	 */
	public static FileOutputStream createFileOutputStream(final IFile file)
			throws FileNotFoundException, ResourceException
	{
		return new FileOutputStream(getFileForIFile(file));
	}
	
	/**
	 * @param path
	 *          the path to create the input stream from
	 * @return the input stream returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 */
	public static FileInputStream createFileInputStream(final String path)
			throws FileNotFoundException
	{
		return new FileInputStream(path);
	}
	
	/**
	 * @param file
	 *          the file to create the input stream from
	 * @return the input stream returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 */
	public static FileInputStream createFileInputStream(final File file)
			throws FileNotFoundException
	{
		return new FileInputStream(file);
	}
	
	/**
	 * @param file
	 *          the Ifile to create the input stream from
	 * @return the input stream returned
	 * @throws FileNotFoundException
	 *           if the file wasn't found
	 * @throws ResourceException
	 *           if the resource wasn't found
	 */
	public static FileInputStream createFileInputStream(final IFile file)
			throws FileNotFoundException, ResourceException
	{
		return new FileInputStream(getFileForIFile(file));
	}
}
