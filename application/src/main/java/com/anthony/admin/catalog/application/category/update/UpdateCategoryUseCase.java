package com.anthony.admin.catalog.application.category.update;

import com.anthony.admin.catalog.application.UseCase;
import com.anthony.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
