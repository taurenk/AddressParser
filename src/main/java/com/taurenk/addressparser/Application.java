package com.taurenk.addressparser;



import com.taurenk.addressparser.library.AddressUtility;

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

        AddressUtility addressUtility = new AddressUtility();

        for (String addr : test_address) {
            System.out.println("New Address String:" + addr);
            AddressParser parser = new AddressParser(addr, addressUtility);
            parser.preParseAddress();
            System.out.println("\n");
        }

    }
}
