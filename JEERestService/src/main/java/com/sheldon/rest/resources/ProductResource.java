package com.sheldon.rest.resources;

import com.sheldon.rest.common.domain.Product;
import com.sheldon.rest.common.representation.ProductRepresentation;
import com.sheldon.rest.common.validation.groups.ProductGroups;
import com.sheldon.rest.ejb.ProductEJB;
import com.sheldon.rest.common.mapper.ProductMapper;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
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
    private Request request;
    @Context
    private UriInfo uriInfo;
    @Context
    private HttpServletRequest httpServletRequest;

    @GET
    @Path("/getAllProducts")
    public Response getAllProducts() {
        List<Product> products = productEJB.getAllProducts();
        EntityTag etag = new EntityTag(Integer.toString(products.hashCode()));

        Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(etag);

        if (null == responseBuilder) {
            responseBuilder = Response
                    .ok(ProductMapper.mapToProductRepresentations(products, uriInfo, ProductResource.class), MediaType.APPLICATION_JSON)
                    .tag(etag);
        } else {
            responseBuilder.status(Response.Status.NOT_MODIFIED);
        }

        return responseBuilder.build();
    }

    @GET
    @Path("/getProduct/{sku}")
    public Response getProduct(@NotNull @PathParam("sku") String sku, @HeaderParam("Cache-Control")CacheControl cacheControl) {
        Product product = productEJB.getProduct(sku);
        EntityTag entityTag = new EntityTag(Integer.toString(product.hashCode()));

        Response.ResponseBuilder evaluationResultBuilder = request.evaluatePreconditions(entityTag);

        if (evaluationResultBuilder == null) {
            evaluationResultBuilder = Response.ok(ProductMapper.mapToProductRepresentation(product, uriInfo, ProductResource.class), MediaType.APPLICATION_JSON).tag(entityTag);
        } else {
            evaluationResultBuilder.status(304);
        }

        if (null == cacheControl) {
            cacheControl = new CacheControl();
            cacheControl.setPrivate(true);
            cacheControl.setMaxAge(120);
//        cacheControl.setMustRevalidate(true);
//        cacheControl.setNoStore(true);
        }
        evaluationResultBuilder.cacheControl(cacheControl);

        return evaluationResultBuilder.build();
    }

    @POST
    @Path("/getProduct")
    public Response getProductBySku(@QueryParam("sku") String sku) {
        return Response.ok().entity(ProductMapper.mapToProductRepresentation(productEJB.getProduct(sku), uriInfo, ProductResource.class)).build();
    }

    @POST
    @Path("/addProduct")
    public Response addProduct(@Valid @ConvertGroup(from = Default.class, to = ProductGroups.AddProductGroup.class) ProductRepresentation productRepresentation) {
        productRepresentation.setSku(UUID.randomUUID().toString());
        return Response.status(201).entity(ProductMapper.mapToProductRepresentation(productEJB.addProduct(ProductMapper.mapToProduct(productRepresentation)), uriInfo, ProductResource.class)).build();
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
