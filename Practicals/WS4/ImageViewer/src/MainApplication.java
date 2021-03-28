import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
//import java.util.Scanner;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }
    
    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage)
    {
        // Construct an album object.
        Album album = new Album();
        
        // Make a new window.
        MainWindow window = new MainWindow(album, stage);        
        
        // Choose which album to load.
        File albumFile = window.chooseAlbumFile();
        
        if(albumFile == null)
        {
            Platform.exit(); // Otherwise JavaFX keeps the program open, doing nothing.
        }
        else
        {
            try
            {
                // Attempt to read an album file.
                readAlbumFile(albumFile, album);
                
                // Display the window.
                window.show();
            }
            catch(IOException e)
            {
                System.err.println("Error while reading " + albumFile);
                Platform.exit();
            }
            catch(UnsupportedOperationException e)
            {
                System.err.println(e.getMessage());
                Platform.exit();
            }
        }
    }
    
    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFile The file storing the list of image filenames and their captions.
     * @param album An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(File albumFile, Album album) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(albumFile));
        String line = reader.readLine();

        ImageRecord img;
        String[] lineParts, metaDataParts, gpsParts;
        String imageFilename, imageCaption;

        while(line != null)
        {
            if(line.trim().length() > 0) // Ignore blank lines
            {
                lineParts = line.split(":");
                
                imageFilename = albumFile.getParent() + File.separatorChar + lineParts[0];
                imageCaption = "";

                if(lineParts.length > 1)
                {
                    imageCaption = lineParts[1];
                }

                //Create concrete base (without decorations) 
                img = new ConcreteImageRecord(imageFilename, imageCaption);

                //Skips for-loop if no decorations to be added
                for(int i = 2; i < lineParts.length; i++)
                {
                    //Isolate metadata from line
                    metaDataParts = lineParts[i].split("=");

                    //Identify metadata type
                    switch(metaDataParts[0])
                    {
                        case "date":
                            img = new DateDecoration(img, metaDataParts[1]);
                        break;
                        
                        case "gps":
                            gpsParts = metaDataParts[1].split(" ");
                            img = new GPSDecoration(img, gpsParts);
                        break;

                        case "rating":
                            img = new RatingDecoration(img, metaDataParts[1]);
                        break;

                        //Not sure if this is the correct exception to use.
                        //Just wanted some practice with Exception handling
                        default:
                            throw new UnsupportedOperationException(
                                "ERROR: No such decoration for \"" 
                                + metaDataParts[0] + "\" implemented!");
                    }
                }
                
                // Insert your code here to add a new image to the album.
                album.addImage(img);
            }
                        
            line = reader.readLine();
        }
        reader.close();
    }

}
