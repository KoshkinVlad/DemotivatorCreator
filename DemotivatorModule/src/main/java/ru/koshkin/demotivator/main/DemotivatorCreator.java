package ru.koshkin.demotivator.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DemotivatorCreator {

    private final int SIGN_SIZE = 20;
    private final String SIGN = "Made by Koshkin Inc.";
    private final String ORIG_FILE_PATH;
    private final String RESULT_FILE_PATH;


    public DemotivatorCreator(String ORIG_FILE_PATH, String RESULT_FILE_PATH) {
        this.ORIG_FILE_PATH = ORIG_FILE_PATH;
        this.RESULT_FILE_PATH = RESULT_FILE_PATH;
    }

    public void create(String upperText, String lowerText) throws IOException {
        // loading original image
        File origFile = new File(ORIG_FILE_PATH);
        if (!origFile.exists()) {
            throw new FileNotFoundException("File " + origFile.getAbsolutePath() + " was not found!");
        }
        Image origImage = ImageIO.read(origFile);
        // getting original image parameters
        int widthOrig = origImage.getWidth(null);
        int heightOrig = origImage.getHeight(null);
        // calculating result image parameters:
        // result image width and height
        int widthRes = widthOrig + widthOrig / 5;
        int heightRes = heightOrig + heightOrig * 2 / 5;
        // coordinates of original image begin
        int xBeginImage = widthOrig / 10;
        int yBeginImage = heightOrig / 10;
        // distance between the image and its frame
        final int DELTA_RECT = 15;
        // coordinates of the frame begin
        int xBeginRect = widthOrig / 10 - DELTA_RECT;
        int yBeginRect = heightOrig / 10 - DELTA_RECT;
        // creating result image object
        BufferedImage resultImage = new BufferedImage(widthRes, heightRes, BufferedImage.TYPE_INT_RGB);
        Graphics gc = resultImage.getGraphics();
        //drawing frame
        gc.setColor(Color.WHITE);
        gc.fillRect(xBeginRect, yBeginRect, widthOrig + DELTA_RECT * 2, heightOrig + DELTA_RECT * 2);
        gc.setColor(Color.BLACK);
        gc.fillRect(xBeginRect + DELTA_RECT / 2, yBeginRect + DELTA_RECT / 2, widthOrig + DELTA_RECT, heightOrig + DELTA_RECT);
        // text size and interline calculating (its hardcode for now)
        final int UPPER_SIZE = 70;
        final int LOWER_SIZE = 40;
        final int IN_LINEAR = 20;
        // text beginning coordinates calculation
        int yUpperTextBegin = yBeginImage + heightOrig + DELTA_RECT;
        int yLowerTextBegin = yBeginImage + heightOrig + DELTA_RECT + UPPER_SIZE + IN_LINEAR;
        // text setup
        gc.setColor(Color.WHITE);
        Font fontUpper = new Font("Arial", Font.TRUETYPE_FONT, UPPER_SIZE);
        Font fontLower = new Font("Arial", Font.TRUETYPE_FONT, LOWER_SIZE);
        final Font fontSign = new Font("Arial", Font.HANGING_BASELINE, SIGN_SIZE);
        // writing an upper text
        Rectangle upperRect = new Rectangle(0, yUpperTextBegin, widthRes, UPPER_SIZE);
        drawCenteredString(gc, upperText, upperRect, fontUpper);
        // writing a lower text
        Rectangle lowerRect = new Rectangle(0, yLowerTextBegin, widthRes, LOWER_SIZE);
        drawCenteredString(gc, lowerText, lowerRect, fontLower);
        // drawing original image
        gc.drawImage(origImage, xBeginImage, yBeginImage, null);
        // putting a watermark
        gc.setFont(fontSign);
        gc.setColor(Color.CYAN);
        gc.drawString(SIGN, 10, heightRes - SIGN_SIZE);
        // writing result image into a file
        ImageIO.write(resultImage, "jpg", new File(RESULT_FILE_PATH));
    }

    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}