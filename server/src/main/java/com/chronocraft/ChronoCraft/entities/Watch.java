package com.chronocraft.ChronoCraft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "watches")
@NoArgsConstructor
@AllArgsConstructor
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    private String name;
    @NotBlank
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    private String description;
    private Double price;
    private Integer quantity;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
