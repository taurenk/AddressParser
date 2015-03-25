package com.taurenk.addressparser.library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by tauren on 3/25/15.
 */
public class AddressUtility {

    // Regex
    private Pattern zipRegex = Pattern.compile("(\\d{5}(-\\d{4})?)");
    private Pattern numRegex = Pattern.compile("(^\\d+)");
    // Average length of City in US is one word - test this out.
    private Pattern cityRegex = Pattern.compile("\\s(\\w+)$");
    private Pattern stateRegex;

    // US Address Standards Map
    private HashMap state_map;
    private HashMap directional_map;


    /**
     * Load in data from files/static input.
     */
    public AddressUtility() {
        DataLoader addressData = new DataLoader();
        this.stateRegex = Pattern.compile(addressData.getSTATE_REGEX());
        this.state_map = addressData.getSTATE_MAP();
        this.directional_map = addressData.getDIRECTIONAL_MAP();
    }

    public Pattern getZipRegex() {
        return zipRegex;
    }

    public Pattern getNumRegex() {
        return numRegex;
    }

    public Pattern getCityRegex() {
        return cityRegex;
    }

    public Pattern getStateRegex() {
        return stateRegex;
    }

    public HashMap getState_map() {
        return state_map;
    }

    public HashMap getDirectional_map() {
        return directional_map;
    }
}
