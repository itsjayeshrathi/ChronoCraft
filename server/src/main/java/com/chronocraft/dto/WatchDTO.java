package com.chronocraft.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chronocraft.entities.Watch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WatchDTO {
    private int id;
    private String title;
	private String description;
	private int quantity;
    private double price;
    private int categoryId;
    //private MultipartFile imageFile;  
    private String imageURL;
    
    public static Watch toEntity(WatchDTO dto) {
		Watch watch=new Watch();
		BeanUtils.copyProperties(dto, watch, "image", "categoryId");		
		return watch;
	}
}
