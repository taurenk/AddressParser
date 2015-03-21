package com.taurenk.addressparser;

/**
 * Created by tauren on 3/21/15.
 */
public class Application {

    public static void main(String[] args) {
        String addrString = "6 Caputo Dr., Manorville NY  11949";
        System.out.println("New Address String:" + addrString);

        AddressParser parser = new AddressParser(addrString);
        parser.parseAddress();
    }
}
