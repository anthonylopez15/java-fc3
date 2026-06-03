package com.anthony.admin.catalog.domain.category;

import com.anthony.admin.catalog.domain.AggregateRoot;
import com.anthony.admin.catalog.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {
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
        final var deleteAt = aActive ? null : now;
        return new Category(id, aName, aDescription, aActive, now, now, deleteAt);
    }

    public static Category with(final Category aCategory) {
        return new Category(
                aCategory.getId(),
                aCategory.name,
                aCategory.description,
                aCategory.isActive(),
                aCategory.createAt,
                aCategory.updateAt,
                aCategory.deleteAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category activate() {
        this.deleteAt = null;
        this.active = true;
        this.updateAt = Instant.now();
        return this;
    }

    public Category deactivate() {
        if (getDeleteAt() == null) {
            this.deleteAt = Instant.now();
        }
        this.active = false;
        this.updateAt = Instant.now();
        return this;
    }

    public Category update(final String aName, final String aDescription, final boolean isActive) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        this.name = aName;
        this.description = aDescription;
        this.updateAt = Instant.now();
        return this;
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

    @Override
    public Category clone() {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}