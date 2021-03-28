/**
 * Represents an image in an album.
 */
public class ConcreteImageRecord implements ImageRecord
{
    private String filename;
    private String caption;
    
    public ConcreteImageRecord(String newFilename, String newCaption)
    {
        filename = newFilename;
        caption = newCaption;
    }
    
    @Override
    public String getFilename()
    {
        return filename;
    }
    
    @Override
    public String getCaption()
    {
        return "Caption: " + caption;
    }
}

