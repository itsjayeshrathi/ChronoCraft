package com.chronocraft.chronocraft.service.coudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {
    Map<String,Object> uploadImage(MultipartFile file) throws IOException;
}
