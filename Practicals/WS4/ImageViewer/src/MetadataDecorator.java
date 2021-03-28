public abstract class MetadataDecorator implements ImageRecord
{
    private ImageRecord img;

    public MetadataDecorator(ImageRecord img)
    {
        this.img = img;
    }

    @Override
    public String getFilename()
    {
        return img.getFilename();
    }

    @Override
    public String getCaption()
    {
        return img.getCaption();
    }
}
