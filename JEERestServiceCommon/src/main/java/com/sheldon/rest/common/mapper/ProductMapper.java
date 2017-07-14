package com.sheldon.rest.common.mapper;

import com.sheldon.rest.common.constants.Constants;
import com.sheldon.rest.common.domain.Product;
import com.sheldon.rest.common.representation.ProductRepresentation;
import org.jboss.resteasy.plugins.providers.atom.Link;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sheld on 6/20/2017.
 */
public class ProductMapper {
    public static ProductRepresentation mapToProductRepresentation(Product product, UriInfo uriInfo, Class clazz) {
        ProductRepresentation productRepresentation = new ProductRepresentation();

        productRepresentation.setId(product.getId());
        productRepresentation.setSku(product.getSku());
        productRepresentation.setName(product.getName());
        productRepresentation.setSize(product.getSize());
        productRepresentation.setCost(product.getCost());
        productRepresentation.setImgUrl(product.getImgUrl());

        Link link = getProductLink(product, uriInfo, clazz);

        List<Link> links = new ArrayList<>();
        links.add(link);

        productRepresentation.setLinks(links);

        return productRepresentation;
    }

    public static Product mapToProduct(ProductRepresentation productRepresentation) {
        Product product = new Product();

        product.setId(productRepresentation.getId());
        product.setSku(productRepresentation.getSku());
        product.setName(productRepresentation.getName());
        product.setSize(productRepresentation.getSize());
        product.setCost(productRepresentation.getCost());
        product.setImgUrl(productRepresentation.getImgUrl());

        return product;
    }

    public static List<ProductRepresentation> mapToProductRepresentations(List<Product> products, UriInfo uriInfo, Class clazz) {
        List<ProductRepresentation> productRepresentations = new ArrayList<>();

        for (Product product : products) {
            productRepresentations.add(mapToProductRepresentation(product, uriInfo, clazz));
        }

        return productRepresentations;
    }

    private static Link getProductLink(Product product, UriInfo uriInfo, Class clazz) {
        Link link = new Link();
        link.setRel("self");
        link.setHref(uriInfo.getBaseUriBuilder().path(clazz).path(clazz, Constants.GET_PRODUCT).build(product.getSku()));

        return link;
    }
}
