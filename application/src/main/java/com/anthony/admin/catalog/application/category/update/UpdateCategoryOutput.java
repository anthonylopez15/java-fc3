package com.anthony.admin.catalog.application.category.update;

import com.anthony.admin.catalog.domain.category.Category;
import com.anthony.admin.catalog.domain.category.CategoryID;

public record UpdateCategoryOutput(CategoryID id) {

    public static UpdateCategoryOutput from(final Category category) {
        return new UpdateCategoryOutput(category.getId());
    }
}
