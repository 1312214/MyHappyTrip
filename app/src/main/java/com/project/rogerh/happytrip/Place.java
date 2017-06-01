package com.project.rogerh.happytrip;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by rogerh on 6/1/2017.
 */

// because this class like a database, so we puts attribue is public modifier.
public class Place {
    public  String name;
    public List<Integer> images;
    public String address;
    public Integer rating;
    public String intro;
    public List<String> comments;
    public LatLng xy;
    public Integer thumnial;


    public Place(String _name, List<Integer> _imgs, String _address, Integer _rating,
                 String _intro, List<String> _comments, LatLng _xy, Integer _thumnial){
        this.name = _name;
        this.address = _address;
        this.images = _imgs;
        this.rating = _rating;
        this.intro = _intro;
        this.comments = _comments;
        this.xy = _xy;
        this.thumnial = _thumnial;
    }

}
