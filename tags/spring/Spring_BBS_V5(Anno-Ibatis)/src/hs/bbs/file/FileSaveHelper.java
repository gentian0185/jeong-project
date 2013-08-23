package hs.bbs.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSaveHelper {
	public static String save(String directory, InputStream is)
			throws IOException {
		// long currentTime = System.currentTimeMillis();
		// int randomValue = random.nextInt(50);
		// String fileName = Long.toString(currentTime) + "_"
		// + Integer.toString(randomValue);
		System.out.println(directory + " is directory");

		File file = new File(directory);
		System.out.println(file.getAbsolutePath() + "      fsafas");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);

			int temp = -1;
			while ((temp = is.read()) != -1) {
				os.write(temp);
			}
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
				}
		}
		return file.getAbsolutePath();
	}
}
