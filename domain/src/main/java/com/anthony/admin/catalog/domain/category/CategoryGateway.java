package com.anthony.admin.catalog.domain.category;

import com.anthony.admin.catalog.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID aCategoryID);

    Optional<Category> findById(CategoryID aCategoryID);

    Category update(Category aCategory);

    Pagination<Category> findAll(CategorySearchQuery aQuery);

}
