package org.lucky.addressbook.controller;

import java.util.*;
import org.lucky.addressbook.model.AddressBook;

public class OptionManager 
{
    private Map<Integer, Option> options;
    protected AddressBook addressBook;

    //CONSTRUCTOR
    public OptionManager(AddressBook addressBook)
    {
        options = new HashMap<>();
        this.addressBook = addressBook;
    }

    /**
     * Adds an option to the HashMap
     * @param ref References a specific menu option number
     * @param option Specific option dependent on ref value
     */
    public void addOption(int ref, Option option)
    {
        options.put(ref, option);
    }

    /**
     * 
     * @param ref References a specific option selected by user
     * @param input Value to be found
     * @return
     */
    public String doOption(int ref, String input)
    {
        String result;

        result = options.get(ref).doOption(input);

        return result;
    }
}
