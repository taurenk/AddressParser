package com.taurenk.addressparser.regexlibrary;

import sun.org.mozilla.javascript.tools.jsc.Main;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by tauren on 3/22/15.
 */
public class RegexLibrary {

    private Pattern us_states_regex;
    private HashMap us_states_map;

    /**
     * Initialize US State Abrv Hashmap
     */
    public RegexLibrary() {
        us_states_map = new HashMap();
        import_states_data();

    }


    /**
     * Read in US state file and create:
     * 1. State Abrv Hashmap for quick state lookup
     * 2. State Regex for searching for states.
     * TODO: Need to rethink on what i acutally need here...
     */
    private void import_states_data() {
        System.out.println(System.getProperty("java.class.path"));
        try {
            // This was a PAIN-make sure the target file IS IN THE TARGET CLASSES!
            BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                            RegexLibrary.class.getResourceAsStream("US_States.txt")
                                        ));
            //System.out.println("Loaded.");

            List<String> statePattern = new ArrayList<String>();
            String line;
            String[] lineItems;
            while((line = reader.readLine()) != null) {
                lineItems = line.split(":");

                // Think about this....
                if (lineItems[0].length() == 2) {
                    us_states_map.put(lineItems[0], lineItems[1]);
                    //us_states_map.put(lineItems[1], lineItems[0]);
                }
                // Create state regex for searching
                statePattern.add(lineItems[0].trim());
                statePattern.add(lineItems[1].trim());
            }

            StringBuilder sb = new StringBuilder();
            for(int index=0; index < statePattern.size(); index++) {
                sb.append( statePattern.get(index) );
                if ( (index+1) < statePattern.size()) {
                    sb.append("|");
                }
            }

            us_states_regex = Pattern.compile(
                                    "(" + sb.toString() + ")"
                                );

            System.out.println("Regex:" + sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
