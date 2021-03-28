package org.lucky.addressbook.model;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Lakshan Martin 
 */
public class Entry 
{
    //CLASS FIELDS
    private String name;
    private List<String> emails; 

    //CONSTRUCTOR
    public Entry(String name, List<String> emails)
    {
        this.name = name;
        this.emails = emails;
    }

    //ACCESSOR
    public String getName()
    {
        return name;
    }

    public List<String> getEmails()
    {
        return emails;
    } 
}
