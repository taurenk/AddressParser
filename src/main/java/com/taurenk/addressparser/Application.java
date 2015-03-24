package com.taurenk.addressparser;

import com.taurenk.addressparser.library.StandardsLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tauren on 3/21/15.
 */
public class Application {

    public static void main(String[] args) {
        List<String> test_address = new ArrayList<String>();
        test_address.add("6 Caputo Dr., Manorville NY  11949");
        test_address.add("100 White Plains Road, White Plains NY 10604");

        StandardsLibrary lib =  new StandardsLibrary();

        for (String addr : test_address) {
            System.out.println("New Address String:" + addr);
            AddressParser parser = new AddressParser(addr, lib);
            parser.preParseAddress();
            System.out.println("\n");
        }

    }
}
