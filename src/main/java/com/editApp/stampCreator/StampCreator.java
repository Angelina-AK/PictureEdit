package com.editApp.stampCreator;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

public class StampCreator {

    private StampParams m_params;

    public void setParams(StampParams m_params) {
        this.m_params = m_params;
    }

    public BufferedImage CreateStampV1(){

        int height = 1000; // crutch, after will need to make a primary calculation of the height
        int width = m_params.getWidth();
        int topOffset = 20;
        int botOffset = 20;
        int leftOffset = 20;
        int rightOffset = 20;

        int currentHeight = topOffset + 10;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();

        g2d.setStroke(new BasicStroke(1));

        // some stuff for nice look
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font basicFont = m_params.getBasicFont();
        Font lowFont = m_params.getLowFont();

        g2d.setFont(basicFont);
        g2d.setColor(new Color(35, 175, 75));
        FontMetrics metrics = g2d.getFontMetrics(basicFont);
        FontMetrics metricsLow = g2d.getFontMetrics(lowFont);

        int headerHeight;
        headerHeight = DrawText(g2d, m_params.getHeaderText(), currentHeight, leftOffset * 2, width - (leftOffset * 2) - (rightOffset * 2), true);

        currentHeight += (metrics.getHeight() + headerHeight);

        int a1 = leftOffset * 2;
        int a2 = a1 + metrics.stringWidth("Подписант") + width / 10;
        int a3 = width - (rightOffset * 2 + metrics.stringWidth("Дата и время подписания"));

        // TODO move constant values to StampCreatorSettings
        g2d.drawString("Подписант", a1, currentHeight);
        g2d.drawString("Сертификат", a2, currentHeight);
        g2d.drawString("Дата и время подписания", a3, currentHeight);

        g2d.setFont(lowFont);
        currentHeight += metricsLow.getHeight();
        g2d.drawString("(Должность, ФИО)", a1, currentHeight);
        g2d.drawString("(тип, кем выдан, идентификатор, период действия)", a2, currentHeight);

        g2d.setFont(basicFont);

        for (StampSignature signature : m_params.getSignatures()){

            currentHeight += metricsLow.getHeight();
            g2d.drawLine((int) (leftOffset * 1.5), currentHeight, (int) (width - rightOffset * 1.5), currentHeight + 1);

            int textHeight1;
            int textHeight2;
            int textHeight3;
            textHeight1 = DrawText(g2d, signature.getSignatureName(), currentHeight, a1, a2 - a1, false);
            textHeight2 = DrawText(g2d, signature.getSignature(), currentHeight, a2, a3 - a2, false);
            textHeight3 = DrawText(g2d, signature.getSignatureTime(), currentHeight, a3, width - a3, false);

            currentHeight += Stream.of(textHeight1, textHeight2, textHeight3).max(Integer::compare).orElseThrow();
        }


        g2d.drawRect(leftOffset, topOffset, width - leftOffset - rightOffset, currentHeight);

        g2d.dispose();


        return cropImage(image, currentHeight + botOffset * 2);
    }

    private int DrawText(@NotNull Graphics2D g2d, @NotNull String text, int topOffset, int leftOffset, int width, boolean centerAlign){

        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());

        int y = topOffset + metrics.getHeight();

        // split string to substrings
        String[] strings = text.split("\n");
        for (int i = 0; i < strings.length; i++){

            String[] words = strings[i].split(" ");
            StringBuilder currentLine = new StringBuilder();

            for (String word : words) {

                String testLine = currentLine.toString() + word + " ";
                int lineWidth = metrics.stringWidth(testLine);

                if (lineWidth > width) {

                    int textXStart = centerAlign ? leftOffset + (width - metrics.stringWidth(currentLine.toString())) / 2 : leftOffset;
                    g2d.drawString(currentLine.toString(), textXStart, y);
                    currentLine = new StringBuilder(word + " ");
                    y += metrics.getHeight();

                } else {
                    currentLine.append(word).append(" ");
                }
            }

            if (!currentLine.isEmpty()) {
                int textXStart = centerAlign ? leftOffset + (width - metrics.stringWidth(currentLine.toString())) / 2 : leftOffset;
                g2d.drawString(currentLine.toString(), textXStart, y);
            }

            if (i != strings.length - 1) {
                y += metrics.getHeight();
            }

        }

        return y - topOffset;
    }

    // another crutch
    private @NotNull BufferedImage cropImage(@NotNull BufferedImage originalImage, int newHeight) {
        int originalWidth = originalImage.getWidth();

        BufferedImage croppedImage = new BufferedImage(originalWidth, newHeight, originalImage.getType());

        Graphics2D g = croppedImage.createGraphics();

        g.drawImage(originalImage, 0, 0, originalWidth, newHeight, 0, 0, originalWidth, newHeight, null);

        g.dispose();

        return croppedImage;
    }
}
