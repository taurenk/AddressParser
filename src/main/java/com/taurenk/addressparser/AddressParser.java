package com.taurenk.addressparser;

import com.taurenk.addressparser.library.AddressUtility;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * AddressParser
 * Main address parsing engine.
 * Currently, We really on "pre-parse": get number, state and zipcode.
 * Created by tauren on 3/21/15.
 */
public class AddressParser {

    private Address address;
    private AddressUtility addressUtilities;

    /**
     * Set standardsLibrary [only load it once, during startup as file IO is involved]
     * @param addrString
     */
    public AddressParser(String addrString, AddressUtility addressUtilities) {
        this.address = new Address(addrString);
        this.addressUtilities = addressUtilities;
    }

    /**
     * Pre parse address, trying to identify
     * The number, state and zipcode.
     * Other address elements [city and street] are a bit
     * more difficult to do with regex...
     */
    public void preParseAddress() {

        this.cleanseString(address.getAddressString());
        System.out.println("Cleansed: " + address.getAddressString());

        String addr = this.address.getAddressString();
        addr = extractZip(addr);
        System.out.println("After ZIP: <" + addr + ">");

        addr = extractNumber(addr);
        System.out.println("After Number: <" + addr + ">");

        addr = extractState(addr);
        System.out.println("After State: <" + addr + ">");
        
        // Extracting City is a bit more difficult

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

        Matcher m = addressUtilities.getZipRegex().matcher(addressString);

        if (m.find( )) {
            System.out.println("\tZip Match: <" + m.group(0) +">" );
            this.address.setZip(m.group(0).trim());
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
        Matcher m = addressUtilities.getNumRegex().matcher(addressString);
        if (m.find( )) {
            System.out.println("\tNumber Match: <" + m.group(0) + ">");
            this.address.setNumber(m.group(0).trim());
            addressString = addressString.replace(m.group(0), "");
        } else {
            System.out.println("\tNumber Match: NO MATCH");
        }
        return addressString.trim();
    }

    /**
     *
     * TODO: Make this a bit stronger...perhaps use string distance or metaphones?
     *      New York == Naw York?
     * @param addressString
     * @return
     */
    public String extractState(String addressString) {
        Matcher m = addressUtilities.getStateRegex().matcher(addressString);
        if (m.find( )) {
            System.out.println("\tStates Match: <" + m.group(0) + ">");
            this.address.setState(m.group(0).trim());
            addressString = addressString.replace(m.group(0), "");
        } else {
            System.out.println("\tStates Match: NO MATCH");
        }
        return addressString.trim();
    }
}
