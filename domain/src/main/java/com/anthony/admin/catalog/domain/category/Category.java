package com.anthony.admin.catalog.domain.category;

import com.anthony.admin.catalog.domain.AggregateRoot;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private boolean active;
    private Instant createAt;
    private Instant updateAt;
    private Instant deleteAt;

    private Category(
            final CategoryID aId,
            final String aName,
            final String aDescription,
            final boolean aActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate) {
        super(aId);
        this.name = aName;
        this.description = aDescription;
        this.active = aActive;
        this.createAt = aCreationDate;
        this.updateAt = aUpdateDate;
        this.deleteAt = aDeleteDate;
    }

    public static Category newCategory(
            final String aName,
            final String aDescription,
            final boolean aActive) {

        final var id = CategoryID.unique();
        final var now = Instant.now();

        return new Category(id, aName, aDescription, aActive, now, now, null);
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public Instant getDeleteAt() {
        return deleteAt;
    }
}