package com.anthony.admin.catalog.application.category.create;

import com.anthony.admin.catalog.domain.category.Category;
import com.anthony.admin.catalog.domain.category.CategoryGateway;
import com.anthony.admin.catalog.domain.validation.handler.Notification;
import com.anthony.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Right;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var name = aCommand.name();
        final var description = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();
        final var aCategory = Category.newCategory(name, description, isActive);
        aCategory.validate(notification);

        return notification.hasError() ? Left(notification) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(Category aCategory) {
        return API.Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
