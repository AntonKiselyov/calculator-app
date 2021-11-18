package ru.akiselev.calculator.service.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import ru.akiselev.calculator.service.dto.Operand;
import ru.akiselev.calculator.service.service.ExpressionService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/expressions")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionResource {

    private final ExpressionService expressionService;

    @GET
    @Path("/{expression}")
    public Operand buildExpression(final @PathParam("expression") String expression) {
        return expressionService.buildExpression(expression);
    }
}
