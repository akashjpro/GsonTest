package com.example.tmha.gsontest;

import java.util.List;

/**
 * Created by Aka on 8/24/2017.
 */

public class Blog {
    public String status;
    public List<Post> posts;

    public Blog() {
    }

    public Blog(String status, List<Post> posts) {
        this.status = status;
        this.posts = posts;
    }
}
