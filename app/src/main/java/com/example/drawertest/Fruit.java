package com.example.drawertest;

import org.litepal.crud.DataSupport;

class Fruit extends DataSupport {
    private String name;
    private int imageId;

    Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    String getName() {
        return name;
    }

    int getImageId() {
        return imageId;
    }
}
