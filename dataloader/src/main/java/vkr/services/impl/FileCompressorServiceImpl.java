package vkr.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import vkr.configurations.DownloadConfigurationProperties;
import vkr.services.FileCompressorService;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Service
public class FileCompressorServiceImpl implements FileCompressorService {
    private final DownloadConfigurationProperties downloadConfigurationProperties;

    private static final Logger logger = LogManager.getLogger(FileCompressorServiceImpl.class);

    public FileCompressorServiceImpl(DownloadConfigurationProperties downloadConfigurationProperties) {
        this.downloadConfigurationProperties = downloadConfigurationProperties;
    }

    @Override
    public String getCompressedImageFile(String imageUrl) {
        String extractionFilenameFromUrl = extractFilenameFromUrl(imageUrl);

        new File(downloadConfigurationProperties.getImageFilePath()).mkdirs();
        File imageFile = new File(String.format("%s/%s",downloadConfigurationProperties.getImageFilePath(), extractionFilenameFromUrl));

        if(!imageFile.exists()){
            try (FileOutputStream fileOutputStream = new FileOutputStream(imageFile)) {
                BufferedImage image = ImageIO.read(new URL(imageUrl));
                if(image != null){
                    String fileFormat = extractionFilenameFromUrl.split("\\.")[1].toLowerCase();
                    compressImage(fileOutputStream, image, fileFormat);
                } else {
                    return imageUrl;
                }
            } catch (IOException ex) {
                logger.error(ex);
                return imageUrl;
            } catch (Exception ex){
                logger.error(ex);
                return imageUrl;
            }
        }

        return extractionFilenameFromUrl;
    }

    private String extractFilenameFromUrl(String imageUrl){
        String[] splitUrl = imageUrl.split("/");
        return splitUrl[splitUrl.length - 1];
    }

    private void compressImage(FileOutputStream fileOutputStream, BufferedImage image, String fileFormat) throws IOException {
        try {
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(fileFormat);

            ImageWriter writer = writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(fileOutputStream);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

            image = resizeImage(image,
                    image.getWidth() > 1600 ? image.getWidth() / 4 : image.getWidth() / 2,
                    image.getHeight() > 1600 ? image.getHeight() / 4 : image.getHeight() / 2);

            param.setCompressionQuality(downloadConfigurationProperties.getCompressionRatio());  // Измените значение качества
            writer.write(null, new IIOImage(image, null, null), param);

            ios.close();
            writer.dispose();
        } catch (NoSuchElementException ex){
            if (!ImageIO.write(image, "jpg", fileOutputStream)) {
                System.err.println("Could not write JPEG format"); // Should never happen
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight) {
        Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(tmp, 0, 0, null);
        graphics2D.dispose();

        return resizedImage;
    }
}
