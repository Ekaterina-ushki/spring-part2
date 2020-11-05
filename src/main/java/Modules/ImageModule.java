package Modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageModule implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("jpg");
    }

    public void displayFunction() {
        System.out.println("1 - Get to the size of image");
        System.out.println("2 - Get exif information");
        System.out.println("3 - Get color depth of image");
    }

    @Override
    public void doFunction(File file, int numberFunc) throws Exception {
        ImageModule.class.getMethod("doFunction" + numberFunc, File.class).invoke(this, file);
    }

    //Get to the size of image
    public void doFunction1(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        System.out.println("Image size: " + width + "x" + height + "px");
    }

    //Get exif information
    public void doFunction2(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        System.out.println("EXIF information:");
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }

    //Get color depth
    public void doFunction3(File file) throws ImageProcessingException, IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        int depth = bufferedImage.getColorModel().getPixelSize();
        System.out.println("Image color depth = " + depth);
    }
}
