import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    // Insert your fields, constructors and methods here.

    //CLASS FIELDS
    private List<ImageRecord> album;

    //CONSTRUCTOR
    public Album()
    {
        album = new ArrayList<>();
    }

    //ACCESSORS
    public List<ImageRecord> getAlbum()
    {
        return album;
    }

    //METHODS -----------------------------------------------------------------

    public void addImage(ImageRecord newImage)
    {
        album.add(newImage);
    }
}
