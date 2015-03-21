package com.taurenk.addressparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tauren on 3/21/15.
 */
public class AddressParser {

    private Address address;

    private String zipRegex = "(\\d{5}(-\\d{4})?)";
    private String numRegex = "(^\\d+)";

    public AddressParser(String addrString) {
        this.address = new Address(addrString);
    }

    public void parseAddress() {
        this.cleanseString(address.getAddressString());
        System.out.println("Cleansed: " + address.getAddressString());

        String addr = this.address.getAddressString();
        addr = extractZip(addr);
        System.out.println("After ZIP: <" + addr + ">");

        addr = extractNumber(addr);
        System.out.println("After Number: <" + addr + ">");
    }


    /**
     * Convert string to uppercase,
     * Strip out commas, periods,
     * hyphens in between words.
     * and remove excess white space
     */
    public void cleanseString(String addressString) {
        String s = addressString.replaceAll("[,.]", "");
        s = s.toUpperCase();
        s = s.replaceAll(" - ", "-");
        s = s.replaceAll("\\s+", " ");
        this.address.setAddressString(s);
    }

    /**
     * Use zipcode regex to find zipcode in address string.
     * if found, remove zip from the string and continue
     * @param addressString
     * @return
     */
    public String extractZip(String addressString) {

        Pattern r = Pattern.compile(this.zipRegex);
        Matcher m = r.matcher(addressString);

        if (m.find( )) {
            System.out.println("\tZip Match: <" + m.group(0) +">" );
            this.address.setZip(m.group(0));
            addressString = addressString.replace(m.group(0), "");
        } else {
            System.out.println("\tZip Match: NO MATCH");
        }
        return addressString.trim();
    }

    /**
     * Use number regex to find number in address string.
     * If found, remove number from string,
     * set number in Address. and return new string without number.
     * @param addressString
     * @return
     */
    public String extractNumber(String addressString) {
        Pattern r = Pattern.compile(this.numRegex);
        Matcher m = r.matcher(addressString);

        if (m.find( )) {
            System.out.println("\tNumber Match: <" + m.group(0) + ">");
            this.address.setNumber(m.group(0));
            addressString = addressString.replace(m.group(0), "");
        } else {
            System.out.println("\tNumber Match: NO MATCH");
        }
        return addressString.trim();
    }

}
