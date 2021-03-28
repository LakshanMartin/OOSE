public class RatingDecoration extends MetadataDecorator
{
    private String rating;

    public RatingDecoration(ImageRecord img, String rating)
    {
        super(img);
        this.rating = rating;
    }

    @Override
    public String getCaption()
    {
        return super.getCaption() + addRatingCaption();
    }

    private String addRatingCaption()
    {
        return " | Rating: " + rating;
    }
}
