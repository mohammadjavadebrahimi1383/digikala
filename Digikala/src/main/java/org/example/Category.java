package org.example;

import java.io.Serializable;

public class Category implements Serializable {
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
