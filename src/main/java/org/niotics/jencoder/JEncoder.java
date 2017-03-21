/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 */

/**
 * This class is responsible for encoding UTF-8 characters.
 * 
 * @author RKR8
 */

package org.niotics.jencoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JEncoder {

    private String inputFilename;
    private String outputFilename;

    public JEncoder(String inputFilename, String outputFilename) {
	this.inputFilename = inputFilename;
	this.outputFilename = outputFilename;
    }

    public String getInputFilename() {
	return inputFilename;
    }

    public void setInputFilename(String inputFilename) {
	this.inputFilename = inputFilename;
    }

    public String getOutputFilename() {
	return outputFilename;
    }

    public void setOutputFilename(String outputFilename) {
	this.outputFilename = outputFilename;
    }

    protected String getEncodedCharacter(char c) {
	// check, if character is ascii
	if (c > 127)
	    return String.format("\\u%04x", (int) c);
	return Character.toString(c);
    }

    protected String obfuscateString(String s) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < s.length(); i++)
	    sb.append(getEncodedCharacter(s.charAt(i)));
	return sb.toString();
    }

    public void obfuscateFile() throws IOException {
	// replace BOM with ""
	String inputText = new String(Files.readAllBytes(Paths.get(inputFilename)), StandardCharsets.UTF_8)
		.replaceAll("\ufeff", "");
	String outputText = obfuscateString(inputText);
	Files.write(Paths.get(outputFilename), outputText.getBytes(), StandardOpenOption.WRITE);
    }

    public static void main(String[] args) {
	if (args.length > 0)
	    try {
		new JEncoder(args[0], args[0]).obfuscateFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	else
	    System.out.println("Usage: java -jar JEncoder-0.1.1.jar yourSourceFile.java");
    }

}
