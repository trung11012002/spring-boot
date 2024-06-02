package com.shop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    private String image;
    @Column(name = "description" ,columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;

    public Product() {
        // TODO Auto-generated constructor stub
    }

    public Product(Integer id, String productName, Double price, String image, String description, Category category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}