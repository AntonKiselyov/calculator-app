package ru.akiselev.calculator.client.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import ru.akiselev.calculator.client.dto.ExpressionRequest;
import ru.akiselev.calculator.client.service.ExpressionService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/expressions")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionResource {

    private final ExpressionService expressionService;

    @POST
    public double buildExpression(final ExpressionRequest request) {
        return expressionService.evaluateExpr(request);
    }
}
