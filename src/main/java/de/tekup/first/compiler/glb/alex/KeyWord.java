package de.tekup.first.compiler.glb.alex;

import java.util.Arrays;
import java.util.List;

public class KeyWord {
	
	private static List<String> keywords;
	
	static {
		keywords = Arrays.asList("var", "integer","float","if");
	}
	
	public static boolean in(String s) {
		return keywords.contains(s);
	}

}
