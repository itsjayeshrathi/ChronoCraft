package com.chronocraft.chronocraft.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class WatchDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl; // URL returned from Cloudinary
    private Long categoryId;
    private MultipartFile imageFile; // Image file to be uploaded
}

