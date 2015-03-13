package br.com.democracy.helper;

import java.io.File;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.messages.Messages;
import br.com.democracy.service.impl.SchedulingServiceImpl;

/**
 * Provides auxiliar methods for manipulating files
 */
public class FileHelper {

	private static final String TEMPORARY_DIRECTORY = "Temp";
	private static final String DOWNLOADS_DIRECTORY = "Downloads";

	/**
	 * @return the Path of the temporary directory on the file system. This
	 *         directory should NOT be visible to the users
	 */
	public static String getTempDir() {
		return getDirectoryPath(TEMPORARY_DIRECTORY);
	}

	/**
	 * @return the Path of the downloads directory on the file system. This
	 *         directory is visible to the users.
	 */
	public static String getDownloadsDir() {
		return getDirectoryPath(DOWNLOADS_DIRECTORY);
	}

	/**
	 * @return the visible URL of the downloads directory
	 */
	public static String getDownloadsDirURL() {
		return "http://" + EmailHelper.getInstance().getServerName() + "/"
				+ DOWNLOADS_DIRECTORY + "/";
	}

	/**
	 * Returns a directory inside the server, or creates it if it does not
	 * exist.
	 * 
	 * @param directoryName
	 *            the desired name for the directory
	 * @return the path of the directory.
	 */
	private static String getDirectoryPath(String directoryName) {
		java.security.ProtectionDomain pd = SchedulingServiceImpl.class
				.getProtectionDomain();
		if (pd == null)
			return null;
		java.security.CodeSource cs = pd.getCodeSource();
		if (cs == null)
			return null;
		java.net.URL url = cs.getLocation();
		if (url == null)
			return null;
		java.io.File f = new File(url.getFile());

		String currentDir = f.getAbsolutePath();
		String separator = System.getProperty("file.separator");
		currentDir = currentDir.split("WEB-INF")[0] + directoryName + separator;

		// if the directory does not exist, creates it
		File folder = new File(currentDir);
		if (!folder.exists()) {
			folder.mkdir();
		}

		return currentDir;
	}

	/**
	 * Removes recursively a file or directory and all files it contains
	 * 
	 * @param file
	 */
	public static void remove(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				remove(f);
			}
		}
		file.delete();
	}

	/**
	 * Removes all files and directories the given directory contains, but
	 * keeping the directory itself, now empty.
	 * 
	 * @param directory
	 *            the path of the directory
	 * @throws ServiceException
	 */
	public static void removeDirectoryFiles(String directory)
			throws ValidationException {
		File dir = new File(directory);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				remove(f);
			}
		} else {
			throw new ValidationException(Messages.INVALID_FILE_TYPE);
		}
	}

}
