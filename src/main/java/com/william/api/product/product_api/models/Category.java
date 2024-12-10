package com.william.api.product.product_api.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    @JsonProperty("id") 
    private String id;
    @NotBlank(message = "O nome é obrigatório")
    private String name;
}
