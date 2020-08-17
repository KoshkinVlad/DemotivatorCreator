package ru.koshkin.demotivator.main;

import javax.swing.*;
import java.io.*;

public class Main extends JFrame {

    private static final String ORIG_FILE_PATH = "original/orig.jpg";
    private static final String RESULT_FILE_PATH = "result.jpg";

    private static final String upperText = "Гигант мысли";
    private static final String lowerText = "Его идеи будут актуальны всегда";

    public static void main(String[] args) throws IOException {
        DemotivatorCreator creator = new DemotivatorCreator(ORIG_FILE_PATH, RESULT_FILE_PATH);
        creator.create(upperText, lowerText);
    }
}