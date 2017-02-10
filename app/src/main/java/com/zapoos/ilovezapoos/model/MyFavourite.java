package com.zapoos.ilovezapoos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 2/10/2017.
 */

public class MyFavourite {

    List<Favourite> favourites = new ArrayList<Favourite>();

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }
}
