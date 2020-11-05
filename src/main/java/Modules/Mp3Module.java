package Modules;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Mp3Module implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("mp3");
    }

    public void displayFunction() {
        System.out.println("1 - Get the name of track");
        System.out.println("2 - Get the duration of track");
        System.out.println("3 - Get the genre of track");
    }

    public void doFunction(File file, int numberFunc) throws Exception {
        Mp3Module.class.getMethod("doFunction" + numberFunc, File.class).invoke(this, file);
    }

    //Get the name of track
    public void doFunction1(File file) throws IOException, ImageProcessingException {
        Metadata metadata = Mp4MetadataReader.readMetadata(file);

        for (Directory directory : metadata.getDirectories()) {
            if (directory.getName().equals("File")) {
                System.out.println("Track name: " + directory.getDescription(1));
            }
        }
    }

    //Get the duration of track
    public void doFunction2(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = fileFormat.properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000);
            System.out.println("Duration = " + sec + " seconds");
        }
    }

    //Get the genre of track
    public void doFunction3(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = fileFormat.properties();
            String key = "mp3.id3tag.genre";
            String genre = (String) properties.get(key);
            System.out.println("Genre of track " + genre);
        }
    }
}
