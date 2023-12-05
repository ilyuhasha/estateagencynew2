package com.example.estateAgency.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.convert.DataSizeUnit;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "products6")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "discription")
    private String description;

    private String form;

    private int price;

    private int telefone;

    private String city;

    private String author;
}