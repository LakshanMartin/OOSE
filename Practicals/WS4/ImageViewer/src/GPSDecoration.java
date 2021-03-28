public class GPSDecoration extends MetadataDecorator 
{
    private int lat;
    private int longi;
    private int elev;

    public GPSDecoration(ImageRecord img, String[] coords)
    {
        super(img);
        lat = Integer.parseInt(coords[0]);
        longi = Integer.parseInt(coords[1]);
        elev = Integer.parseInt(coords[2]);
    }   
    
    @Override
    public String getCaption()
    {
        return super.getCaption() + addGPSCaption();
    }
    
    private String addGPSCaption()
    {
        return " | GPS Coords: " + lat + ", " + longi + ", " + elev;
    }
}
