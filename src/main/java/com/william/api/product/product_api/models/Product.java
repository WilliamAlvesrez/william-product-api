package com.william.api.product.product_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "products")
public class Product {
    @Id

     @JsonProperty("productIdentifier")
    private String product_identifier;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("preco")
    private double price;

    @JsonProperty("category_id")
    private String category_id;

    private Category category;
    
    
    
}
