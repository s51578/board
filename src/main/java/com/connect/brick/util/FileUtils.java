package com.connect.brick.util;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {

	public static Set<Path> MachingFileExtension(String folderPath, String patternExtension) {

		System.out.println(patternExtension);
		System.out.println(folderPath);
		Path startDir = Paths.get(folderPath);
		String pattern = "*.{" + patternExtension + "}";

		FileSystem fs = FileSystems.getDefault();
		final PathMatcher matcher = fs.getPathMatcher("glob:" + pattern);
		// File file[] = new File[4];
		final Set<Path> results = new HashSet<Path>();

		FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
				Path name = file.getFileName();
				if (matcher.matches(name)) {

//		        	 if (file.toString().endsWith(".txt")) {
//		                 System.out.println(file.getFileName());
//		             }
					results.add(file);

					// System.out.print(String.format("Found matched file: '%s'.%n", file));

				}
				return FileVisitResult.CONTINUE;
			}
		};

		try {
			Files.walkFileTree(startDir, matcherVisitor);

			/*
			 * for( Path next : results){
			 * //System.out.print(String.format("[Here] Found matched file: '%s'.%n",
			 * next)); }
			 */

		} catch (NoSuchFileException fe) { // pspark
			System.out.println("File not found! :" + fe);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return results;
	}

}
