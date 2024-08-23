package com.chronocraft.chronocraft.service.coudinary;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public Map<String, Object> uploadImage(MultipartFile file) {
        try {
            // Upload the image to Cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "images"));

            // Check if upload result contains the URL and handle other potential fields if necessary
            if (!uploadResult.containsKey("url")) {
                throw new RuntimeException("Image URL not found in upload result");
            }

            // Return the entire upload result map
            return uploadResult;
        } catch (IOException e) {
            throw new RuntimeException("Image uploading failed", e);
        }
    }

}
