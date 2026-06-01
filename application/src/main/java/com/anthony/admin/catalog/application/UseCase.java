package com.anthony.admin.catalog.application;

import com.anthony.admin.catalog.domain.category.Category;

public class UseCase {

    public Category execute(){
        return Category.newCategory("", "", true);
    }
}