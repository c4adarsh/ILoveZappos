package com.zapoos.ilovezapoos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 2/6/2017.
 */

public class Suggestions {

    private final List<String> results;

    public Suggestions(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }
}
