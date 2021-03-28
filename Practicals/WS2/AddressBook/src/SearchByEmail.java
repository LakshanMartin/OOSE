public class SearchByEmail implements Option
{
    //CLASS FIELD
    private AddressBook addressBook;

    //CONSTRUCTOR
    protected SearchByEmail(AddressBook addressBook)
    {
        this.addressBook = addressBook;
    }

    @Override
    public String doOption(String s) 
    {
        return addressBook.findByEmail(s);
    }

    @Override
    public boolean requiresText() 
    {
        return true;
    }
}
