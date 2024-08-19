package com.chronocraft.chronocraft.service.admin;

import com.chronocraft.chronocraft.dto.WatchDTO;
import com.chronocraft.chronocraft.entity.CategoryEntity;
import com.chronocraft.chronocraft.entity.WatchEntity;
import com.chronocraft.chronocraft.repository.CategoryRepository;
import com.chronocraft.chronocraft.repository.WatchRepository;
import com.chronocraft.chronocraft.service.coudinary.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;
    private final CategoryRepository categoryRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = Logger.getLogger(WatchServiceImpl.class.getName());

    @Autowired
    public WatchServiceImpl(WatchRepository watchRepository, CategoryRepository categoryRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.watchRepository = watchRepository;
        this.categoryRepository = categoryRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public WatchDTO addWatch(WatchDTO watchDTO) {
        String imageUrl = uploadImageAndGetUrl(watchDTO.getImageFile());
        WatchEntity watchEntity = modelMapper.map(watchDTO, WatchEntity.class);
        watchEntity.setImageUrl(imageUrl);

        CategoryEntity categoryEntity = categoryRepository.findById(watchDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        watchEntity.setCategoryEntity(categoryEntity);

        WatchEntity savedWatchEntity = watchRepository.save(watchEntity);
        return modelMapper.map(savedWatchEntity, WatchDTO.class);
    }

    @Override
    public List<WatchDTO> getAllWatches() {
        return watchRepository.findAll().stream()
                .map(watch -> modelMapper.map(watch, WatchDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WatchDTO> getWatchesByName(String name) {
        return watchRepository.findAllByNameContaining(name).stream()
                .map(watch -> modelMapper.map(watch, WatchDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteWatch(Long id) {
        if (watchRepository.existsById(id)) {
            watchRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public WatchDTO getWatchById(Long id) {
        return watchRepository.findById(id)
                .map(watch -> modelMapper.map(watch, WatchDTO.class))
                .orElse(null);
    }

    @Override
    public WatchDTO updateWatch(Long id, WatchDTO watchDTO) {
        WatchEntity watchEntity = watchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watch not found"));

        CategoryEntity categoryEntity = categoryRepository.findById(watchDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        watchEntity.setName(watchDTO.getName());
        watchEntity.setDescription(watchDTO.getDescription());
        watchEntity.setPrice(watchDTO.getPrice());
        watchEntity.setCategoryEntity(categoryEntity);

        if (watchDTO.getImageFile() != null && !watchDTO.getImageFile().isEmpty()) {
            String imageUrl = uploadImageAndGetUrl(watchDTO.getImageFile());
            watchEntity.setImageUrl(imageUrl);
        }

        WatchEntity updatedWatchEntity = watchRepository.save(watchEntity);
        return modelMapper.map(updatedWatchEntity, WatchDTO.class);
    }

    private String uploadImageAndGetUrl(MultipartFile imageFile) {
        try {
            Map<String, Object> uploadResult = cloudinaryService.uploadImage(imageFile);
            String imageUrl = (String) uploadResult.get("url");
            if (imageUrl == null) {
                throw new RuntimeException("Image URL not returned from Cloudinary");
            }
            return imageUrl;
        } catch (IOException e) {
            LOGGER.severe("Image upload failed: " + e.getMessage());
            throw new RuntimeException("Failed to upload image", e);
        }
    }

}
