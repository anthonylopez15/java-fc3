package com.anthony.admin.catalog.application;

import com.anthony.admin.catalog.domain.category.Category;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN aInput);
}