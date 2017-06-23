package com.sheldon.rest.resources;

import com.sheldon.rest.common.representation.ProductRepresentation;
import com.sheldon.rest.common.validation.groups.ProductGroups;
import com.sheldon.rest.ejb.ProductEJB;
import com.sheldon.rest.mapper.ProductMapper;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

/**
 * Created by Sheld on 5/26/2017.
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    @EJB
    private ProductEJB productEJB;
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/getAllProducts")
    public Response getAllProducts() {
        return Response.ok().entity(ProductMapper.mapToProductRepresentations(productEJB.getAllProducts(), uriInfo)).build();
    }

    @GET
    @Path("/getProduct/{sku}")
    public Response getProduct(@NotNull @PathParam("sku") String sku) {
        return Response.ok().entity(ProductMapper.mapToProductRepresentation(productEJB.getProduct(sku), uriInfo)).build();
    }

    @POST
    @Path("/getProduct")
    public Response getProductBySku(@QueryParam("sku") String sku) {
        return Response.ok().entity(ProductMapper.mapToProductRepresentation(productEJB.getProduct(sku), uriInfo)).build();
    }

    @POST
    @Path("/addProduct")
    public Response addProduct(@Valid @ConvertGroup(from = Default.class, to = ProductGroups.AddProductGroup.class) ProductRepresentation productRepresentation) {
        productRepresentation.setSku(UUID.randomUUID().toString());
        return Response.status(201).entity(ProductMapper.mapToProductRepresentation(productEJB.addProduct(ProductMapper.mapToProduct(productRepresentation)), uriInfo)).build();
    }

    @DELETE
    @Path("removeProduct")
    public Response removeProduct(@Valid @ConvertGroup(from = Default.class, to = ProductGroups.DeleteProductGroup.class) ProductRepresentation productRepresentation) {
        return removeProductBySku(productRepresentation.getSku());
    }

    @DELETE
    @Path("removeProduct/{sku}")
    public Response removeProductBySku(@NotNull @PathParam("sku") String sku) {
        if (productEJB.removeProduct(sku)) {
            return Response.status(204).build();
        }
        return Response.status(410).build();
    }
}
