package com.anthony.admin.catalog;

import com.anthony.admin.catalog.application.UseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UseCaseTest {

    @Test
    public void testCreateUseCase(){
        Assertions.assertNotNull(new UseCase());
        Assertions.assertNotNull(new UseCase().execute());
    }

}
