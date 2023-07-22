package sorting;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(final String[] args) {
        String sortingType = "natural";
        String dataType = "word";
        String inputFile = null;
        String outputFile = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-dataType")) {
                if (i + 1 < args.length) {
                    switch (args[i + 1]) {
                        case "long":
                        case "line":
                        case "word":
                            dataType = args[i + 1];
                            break;
                        default:
                            System.out.println("No data type defined!");
                            return;
                    }
                } else {
                    System.out.println("No data type defined!");
                    return;
                }
            } else if (args[i].equals("-sortingType")) {
                if (i + 1 < args.length) {
                    switch (args[i + 1]) {
                        case "byCount":
                        case "natural":
                            sortingType = args[i + 1];
                            break;
                        default:
                            System.out.println("No sorting type defined!");
                            return;
                    }
                } else {
                    System.out.println("No sorting type defined!");
                    return;
                }
            } else if (args[i].equals("-inputFile")) {
                if (i + 1 < args.length) {
                    inputFile = args[i + 1];
                    i++;
                } else {
                    System.out.println("No input file name defined!");
                    return;
                }
            } else if (args[i].equals("-outputFile")) {
                if (i + 1 < args.length) {
                    outputFile = args[i + 1];
                    i++;
                } else {
                    System.out.println("No output file name defined!");
                    return;
                }
            } else if (args[i].startsWith("-")) {
                System.out.println("\"" + args[i] + "\" is not a valid parameter. It will be skipped.");
            }
        }

        if (sortingType.isEmpty()) {
            System.out.println("No sorting type defined!");
            return;
        }

        if (dataType.isEmpty()) {
            System.out.println("No data type defined!");
            return;
        }

        try (Scanner scanner = (inputFile != null) ? new Scanner(new FileInputStream(inputFile)) : new Scanner(System.in)) {
            UserInterface ui = new UserInterface(scanner, dataType, sortingType);

            if (outputFile != null) {
                try (PrintStream printStream = new PrintStream(new FileOutputStream(outputFile))) {
                    ui.setOutputStream(printStream);
                    ui.boot();
                }
            } else {
                ui.boot();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
