public class DateDecoration extends MetadataDecorator 
{
    private String date;

    public DateDecoration(ImageRecord img, String date)
    {
        super(img);
        this.date = date;
    }    

    @Override
    public String getCaption()
    {
        return super.getCaption() + addDateCaption();
    }

    private String addDateCaption()
    {
        return " | Date: " + date; 
    }
}
