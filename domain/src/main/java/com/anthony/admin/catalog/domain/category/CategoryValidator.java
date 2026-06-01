package com.anthony.admin.catalog.domain.category;

import com.anthony.admin.catalog.domain.validation.Error;
import com.anthony.admin.catalog.domain.validation.ValidationHandler;
import com.anthony.admin.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int MAX_NAME_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler handler) {
        super(handler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraint();
    }

    private void checkNameConstraint() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var nameLength = name.trim().length();
        if (nameLength > MAX_NAME_LENGTH || nameLength < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
