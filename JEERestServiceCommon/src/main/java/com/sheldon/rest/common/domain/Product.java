package com.sheldon.rest.common.domain;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Sheld on 5/28/2017.
 */
@Entity
@Table(schema = "coffee_shop", name = "products")
@NamedQueries({
        @NamedQuery(name = "products.getAllProducts", query="SELECT p FROM Product p"),
        @NamedQuery(name = "products.getProductBySku", query = "SELECT p FROM Product p where p.sku = :sku"),
        @NamedQuery(name = "products.deleteProductBySku", query = "DELETE FROM Product p where p.sku = :sku")
})
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "sku_id")
    private String sku;

    @Column(name= "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "\"imgUrl\"")
    private String imgUrl;

    public Product() {
        super();
    }

    public Product(BigInteger id, String sku, String name, String size, Double cost, String imgUrl) {
        super();
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.imgUrl = imgUrl;
    }

    public Product(Product product) {
        super();
        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.size = product.getSize();
        this.cost = product.getCost();
        this.imgUrl = product.getImgUrl();
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

    public double getCost() {
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
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (sku != null ? !sku.equals(product.sku) : product.sku != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (size != null ? !size.equals(product.size) : product.size != null) return false;
        if (cost != null ? !cost.equals(product.cost) : product.cost != null) return false;
        return imgUrl != null ? imgUrl.equals(product.imgUrl) : product.imgUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", product_name='" + name + '\'' +
                ", product_size='" + size + '\'' +
                ", cost=" + cost +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
