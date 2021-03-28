public class SearchByName implements Option
{
    private AddressBook addressBook;

    protected SearchByName(AddressBook addressBook)
    {
        this.addressBook = addressBook;
    }

    @Override 
    public String doOption(String s)
    {
        return addressBook.findByName(s);
    }

    @Override
    public boolean requiresText() 
    {
        return true;
    }
}
