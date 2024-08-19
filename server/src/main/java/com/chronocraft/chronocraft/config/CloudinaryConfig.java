package com.chronocraft.chronocraft.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map map = new HashMap<>();
        map.put("cloud_name", "chronocraft");
        map.put("api_key", "997427917946654");
        map.put("api_secret", "wH7-I4gzN4-vS5I3Vl1VSPOPRV0");
        return new Cloudinary(map);
    }
}
