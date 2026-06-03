package com.anthony.admin.catalog.application.category.update;

import com.anthony.admin.catalog.domain.category.Category;
import com.anthony.admin.catalog.domain.category.CategoryGateway;
import com.anthony.admin.catalog.domain.category.CategoryID;
import com.anthony.admin.catalog.domain.exceptions.DomainException;
import com.anthony.admin.catalog.domain.validation.Error;
import com.anthony.admin.catalog.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var id = CategoryID.from(aCommand.id());
        final var name = aCommand.name();
        final var description = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(id)
                .orElseThrow(notFound(id));

        final var notification = Notification.create();

        aCategory.update(name, description, isActive).validate(notification);

        return notification.hasError() ? API.Left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return API.Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anId.getValue()))
        );
    }
}
