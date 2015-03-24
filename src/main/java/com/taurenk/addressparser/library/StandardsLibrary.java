package com.taurenk.addressparser.library;

import java.io.*;

import java.util.*;

import java.util.regex.Pattern;

/**
 * Created by tauren on 3/22/15.
 */
public class StandardsLibrary {

    private Pattern us_states_regex;
    private HashMap us_states_map;
    private HashMap directional_map;


    /**
     * Initialize US State Abrv Hashmap
     */
    public StandardsLibrary() {
        us_states_map = new HashMap();
        this.import_states_data();
        this.loadDirectionalMap();
    }

    /**
     * Return US State Regex Pattern
     * @return Pattern
     */
    public Pattern getUs_states_regex() {
        return us_states_regex;
    }

    /**
     * Retrieve State Standards Mapping. Example:
     *      NY : NY
     *      NEW YORk : NY
     * This is used to set a State Standard within Geocoder.
     * @return HashMap
     */
    public HashMap getUs_states_map() {
        return us_states_map;
    }

    /**
     * Load Standard Directions into HashMap
     */
    private void loadDirectionalMap() {
        directional_map.put("NORTH", "N");
        directional_map.put("NORTHEAST", "NE");
        directional_map.put("EAST", "E");
        directional_map.put("SOUTHEAST", "SE");
        directional_map.put("SOUTH", "S");
        directional_map.put("SOUTHWEST", "SW");
        directional_map.put("WEST", "W");
        directional_map.put("NORTHWEST", "NW");
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
                                            StandardsLibrary.class.getResourceAsStream("US_States.txt")
                                        ));


            String line;
            String[] lineItems;
            while((line = reader.readLine()) != null) {
                lineItems = line.split(":");

                // Think about this....
                if (lineItems[0].length() == 2) {
                    this.us_states_map.put(lineItems[0], lineItems[0]);
                    us_states_map.put(lineItems[1], lineItems[0]);
                }

            }

            StringBuilder sb = new StringBuilder();
            Iterator it = us_states_map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                sb.append(pair.getKey());
                if (it.hasNext()) {sb.append("|");}
                it.remove(); // avoids a ConcurrentModificationException
            }

            this.us_states_regex = Pattern.compile(
                                    "\\s(" + sb.toString() + ")$"
                                );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
