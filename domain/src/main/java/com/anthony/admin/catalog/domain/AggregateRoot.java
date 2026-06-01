package com.anthony.admin.catalog.domain;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }
}
