package com.sheldon.rest.common.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sheldon.rest.common.validation.annotations.Currency;
import com.sheldon.rest.common.validation.annotations.StringNotNullOrEmpty;
import com.sheldon.rest.common.validation.groups.ProductGroups;
import org.jboss.resteasy.plugins.providers.atom.Link;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Sheld on 6/20/2017.
 */
public class ProductRepresentation extends BaseRepresentation {
    @JsonIgnore
    private BigInteger id;
    @StringNotNullOrEmpty(groups = ProductGroups.DeleteProductGroup.class)
    private String sku;
    @StringNotNullOrEmpty(groups = ProductGroups.AddProductGroup.class)
    private String name;
    @StringNotNullOrEmpty(groups = ProductGroups.AddProductGroup.class)
    private String size;
    @Currency(groups = ProductGroups.AddProductGroup.class)
    private Double cost;
    @StringNotNullOrEmpty(groups = ProductGroups.AddProductGroup.class)
    private String imgUrl;

    public ProductRepresentation() {
        super();
    }

    public ProductRepresentation(BigInteger id, String sku, String name, String size, Double cost, String imgUrl, List<Link> links) {
        super();
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.imgUrl = imgUrl;
        this.links = links;
    }

    public ProductRepresentation(ProductRepresentation productRepresentation) {
        super();
        this.id = productRepresentation.getId();
        this.sku = productRepresentation.getSku();
        this.name = productRepresentation.getName();
        this.size = productRepresentation.getSize();
        this.cost = productRepresentation.getCost();
        this.imgUrl = productRepresentation.getImgUrl();
        this.links = productRepresentation.getLinks();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRepresentation)) return false;
        if (!super.equals(o)) return false;

        ProductRepresentation that = (ProductRepresentation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sku != null ? !sku.equals(that.sku) : that.sku != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        return imgUrl != null ? imgUrl.equals(that.imgUrl) : that.imgUrl == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductRepresentation{" +
                "links=" + links +
                ", id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", cost=" + cost +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
