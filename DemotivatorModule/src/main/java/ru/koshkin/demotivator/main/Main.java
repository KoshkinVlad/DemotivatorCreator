package ru.koshkin.demotivator.main;

import java.io.*;

public class Main {

    private static final String ORIG_FILE_PATH = "original/orig.jpg";
    private static final String RESULT_FILE_PATH = "result.jpg";

    private static final String upperText = "Верхний текст";
    private static final String lowerText = "Нижний текст";

    public static void main(String[] args) throws IOException {
        DemotivatorCreator creator = new DemotivatorCreator(ORIG_FILE_PATH, RESULT_FILE_PATH);
        creator.create(upperText, lowerText);
    }
}