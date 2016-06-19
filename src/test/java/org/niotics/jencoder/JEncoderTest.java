/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 */

/**
 * This class is responsible for testing the JEncoder class.
 * 
 * @author RKR8
 */

package org.niotics.jencoder;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Test;

public class JEncoderTest {

    String inputFilename;
    String outputFilename;
    JEncoder jEncoder;

    /**
     * This method is called before every test and resets every field.
     * 
     * @author RKR8
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
	// JEncoder constructor needs inputFilename and outputFilename
	inputFilename = "testFile.java";
	outputFilename = "testFile.java";
	jEncoder = new JEncoder(inputFilename, outputFilename);
    }

    /**
     * This method tests the getInputFilename() method of the JEncoder class.
     * 
     * @author RKR8
     */
    @Test
    public void testGetInputFilename() {
	assertSame("inputFilename should be the same as jEncoder.getInputFilename()", inputFilename,
		jEncoder.getInputFilename());
    }

    /**
     * This method tests the setInputFilename() method of the JEncoder class.
     * 
     * @author RKR8
     */
    @Test
    public void testSetInputFilename() {
	// set inputFilename to a new object
	jEncoder.setInputFilename(new String(inputFilename));
	assertNotSame("inputFilename should not be the same as jEncoder.getInputFilename()", inputFilename,
		jEncoder.getInputFilename());
    }

    /**
     * This method tests the getOutputFilename() method of the JEncoder class.
     * 
     * @author RKR8
     */
    @Test
    public void testGetOutputFilename() {
	assertSame("outputFilename should be the same as jEncoder.getOutputFilename()", outputFilename,
		jEncoder.getOutputFilename());
    }

    /**
     * This method tests the setOutputFilename() method of the JEncoder class.
     * 
     * @author RKR8
     */
    @Test
    public void testSetOutputFilename() {
	// set outputFilename to a new object
	jEncoder.setOutputFilename(new String(outputFilename));
	assertNotSame("outputFilename should not be the same as jEncoder.getOutputFilename()", outputFilename,
		jEncoder.getOutputFilename());
    }

    /**
     * This method tests the getEncodedCharacter() method of the JEncoder class.
     * 
     * @author RKR8
     */
    @Test
    public void testGetEncodedCharacter() {
	// no character replacement
	assertEquals("\'\\0\' should be equal to jEncoder.getEncodedCharacter(\'\\0\')", "\0",
		jEncoder.getEncodedCharacter('\0'));
	assertEquals("\'\\n\' should be equal to jEncoder.getEncodedCharacter(\'\\n\')", "\n",
		jEncoder.getEncodedCharacter('\n'));
	assertEquals("\'\\b\' should be equal to jEncoder.getEncodedCharacter(\'\\b\')", "\b",
		jEncoder.getEncodedCharacter('\b'));
	assertEquals("\'0\' should be equal to jEncoder.getEncodedCharacter(\'0\')", "0",
		jEncoder.getEncodedCharacter('0'));
	assertEquals("\'a\' should be equal to jEncoder.getEncodedCharacter(\'a\')", "a",
		jEncoder.getEncodedCharacter('a'));
	// character replacement
	assertEquals("\'©\' should be equal to jEncoder.getEncodedCharacter(\'©\')", "\\u00a9",
		jEncoder.getEncodedCharacter('©'));
	assertEquals("\'đ\' should be equal to jEncoder.getEncodedCharacter(\'đ\')", "\\u0111",
		jEncoder.getEncodedCharacter('đ'));
    }

    /**
     * This method tests the obfuscateString() method of the JEncoder class.
     * 
     * @author RKR8
     */
    @Test
    public void testObfuscateString() {
	// character replacement
	assertEquals("\"\\u00df\\u0178\" should be equal to jEncoder.obfuscateString(\"ßŸ\")", "\\u00df\\u0178",
		jEncoder.obfuscateString("ßŸ"));
	// no character replacement
	assertEquals("\"test\" should be equal to jEncoder.obfuscateString(\"test\")", "test",
		jEncoder.obfuscateString("test"));
	assertEquals("\"\" should be equal to jEncoder.obfuscateString(\"\")", "", jEncoder.obfuscateString(""));
    }

    /**
     * This method is not yet fully implemented.
     */
    /*
     * @Test public void testObfuscateFile() { try { // create test file
     * Files.write(Paths.get(inputFilename), "\ufeffáúÏdfg".getBytes(),
     * StandardOpenOption.CREATE_NEW); // obfuscate test file
     * jEncoder.obfuscateFile(); // character replacement assertEquals(
     * "\"\\u00e1\\u00fa\\u00cfdfg\" should be equal to new String(Files.readAllBytes(Paths.get(outputFilename)), StandardCharsets.UTF_8)"
     * , "\\u00e1\\u00fa\\u00cfdfg", new
     * String(Files.readAllBytes(Paths.get(outputFilename)),
     * StandardCharsets.UTF_8)); // delete test files
     * Files.delete(Paths.get(inputFilename));
     * Files.delete(Paths.get(outputFilename)); } catch (Exception e) {
     * e.printStackTrace(); } }
     */

}
