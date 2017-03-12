package com.muletut.search;

import java.io.File;
import java.io.FileFilter;

public class TextFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		System.out.println("PATH"+pathname.getName());
		return pathname.getName().toLowerCase().endsWith(".html");
	}
}