package football;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FootballCompetitionTest {
	private File tempDir;
    private File inputFile;
    private File outputFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary directory
        tempDir = Files.createTempDirectory("test-dir").toFile();

        // Create input and output files in the temporary directory
        inputFile = new File(tempDir, "test_input.txt");
        outputFile = new File(tempDir, "test_output.txt");

        // Write content to the input file (example data)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write("Sample input data");
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up: delete temporary directory and files
        deleteRecursive(tempDir);
    }

    private void deleteRecursive(File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            for (File child : fileOrDir.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDir.delete();
    }

    @Test
    public void testFootballCompetition() throws IOException {
        // Perform the action that generates the output file
        // For example, simulate generating output based on input file
        generateOutputFile(inputFile, outputFile);

        // Verify that the output file is created and has expected content
        assertTrue(outputFile.exists(), "Output file should exist");

        // Read and assert content from the output file
        String actualOutput = readContentFromFile(outputFile);
        assertEquals("Sample input data", actualOutput.trim(), "Output content should match expected");
    }

    private void generateOutputFile(File inputFile, File outputFile) throws IOException {
        // Example: Simple copy of input file to output file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private String readContentFromFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}