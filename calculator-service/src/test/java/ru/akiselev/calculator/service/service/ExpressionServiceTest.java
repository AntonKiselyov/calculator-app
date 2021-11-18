package ru.akiselev.calculator.service.service;

import org.junit.jupiter.api.Test;

class ExpressionServiceTest {

    @Test
    void buildExpression() {
        ExpressionService expressionService = new ExpressionService();

        expressionService.buildExpression("      2 * (a + b) - 5  ");
    }
}