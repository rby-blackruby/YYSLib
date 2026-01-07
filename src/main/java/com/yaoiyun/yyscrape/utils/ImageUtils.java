package com.yaoiyun.yyscrape.utils;

import com.yaoiyun.yyscrape.scraper.exception.ImageProcessException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ImageUtils {
    private static final Logger LOGGER = Logger.getLogger(ImageUtils.class.getName());

    private ImageUtils() {}

    public static List<byte[]> splitLargeImage(BufferedImage largeImage, String imageExtension) {
        // TODO: if this solves the jpeg -> webp conversion issues, make it so that instead of 2 images
        // it splits the large image imageHeigh / IMAGE_SPLIT_THRESHOLD_PX number of images.
        int topHeight = largeImage.getHeight() / 2;
        int bottomHeight = largeImage.getHeight() - topHeight;

        LOGGER.info("Creating split images...");
        BufferedImage topImage = largeImage.getSubimage(0, 0, largeImage.getWidth(), topHeight);
        BufferedImage bottomImage = largeImage.getSubimage(0, topHeight, largeImage.getWidth(), bottomHeight);

        LOGGER.info("Writing images to baos...");
        ByteArrayOutputStream topBaos = new ByteArrayOutputStream();
        try {
            if(!ImageIO.write(topImage, imageExtension, topBaos))
                throw new ImageProcessException("Failed to write topImage to baos, ImageIO.write returned false!!");
        } catch(IOException e) {
            throw new ImageProcessException("Failed to write topImage to baos.", e);
        }

        ByteArrayOutputStream bottomBaos = new ByteArrayOutputStream();
        try {
            if(!ImageIO.write(bottomImage, imageExtension, bottomBaos))
                throw new ImageProcessException("Failed to write bottomImage to baos, ImageIO.write returned false!!");
        } catch(IOException e) {
            throw new ImageProcessException("Failed to write bottomImage to baos.", e);
        }

        LOGGER.info("Done.");
        return List.of(topBaos.toByteArray(), bottomBaos.toByteArray());
    }
}
