package org.example;

import java.io.Serializable;

public class SubCategory extends Category implements Serializable {
    private String subCategoryName;

    public SubCategory(String categoryName, String subCategoryName) {
        super(categoryName);
        this.subCategoryName = subCategoryName;
    }
}
