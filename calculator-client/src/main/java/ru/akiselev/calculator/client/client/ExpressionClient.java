package ru.akiselev.calculator.client.client;

import ru.akiselev.calculator.client.client.dto.Operand;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/expressions")
@Produces(MediaType.APPLICATION_JSON)
public interface ExpressionClient {

    @GET
    @Path("/{expression}")
    Operand buildExpression(final @PathParam("expression") String expression);
}
