package com.chronocraft.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chronocraft.custom_exceptions.ResourceNotFoundException;
import com.chronocraft.dto.WatchDTO;
import com.chronocraft.entities.Category;
import com.chronocraft.entities.Watch;
import com.chronocraft.repositories.CategoryRepository;
import com.chronocraft.repositories.WatchRepository;
import com.chronocraft.utility.StorageService;


@Service
public class WatchServiceImpl implements WatchService {

    @Autowired
    private WatchRepository watchRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private StorageService storageService;
    
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
	public Watch addWatch(WatchDTO watchDTO) throws IOException {

		Watch watch= new Watch();
		watch.setBrand(watchDTO.getTitle());
		Category category = categoryRepository.findById(watchDTO.getCategoryId())
	                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + watchDTO.getCategoryId()));
		watch.setCategory(category);
		watch.setDescription_name(watchDTO.getDescription());
		watch.setId(watchDTO.getId());
		watch.setPrice(watchDTO.getPrice());
		watch.setQuantity(watchDTO.getQuantity());
		watch.setImagePath(watchDTO.getImageURL());
		watchRepository.save(watch);
		return watch;
	}
//    @Override
//    public Watch addWatch(WatchDTO watchDTO, String imagePath) {
//        Watch watch = new Watch();
//        watch.setBrand(watchDTO.getTitle());
//        watch.setDescription_name(watchDTO.getDescription());
//        watch.setPrice(watchDTO.getPrice());
//        watch.setCategory(categoryRepository.getById(watchDTO.getCategoryId()));
//        watch.setImagePath(imagePath);  // Store the image path or file name
//        return watchRepository.save(watch);
//    }
//    
    
    @Override
    public String saveImageFile(MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Generate a unique file name
            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            // Save the file to the specified directory
            File file = new File(uploadDir + File.separator + fileName);
            imageFile.transferTo(file);
            return fileName;  // Return the file name or path
        }
        return null;
    }
//    @Override
//	public Watch addWatch(Watch watch, MultipartFile productImmage) {
//		String watchImageName = storageService.store(productImmage);
//		watch.setImageName(watchImageName);
//		watchRepository.save(watch);
//		return watch;
//	}
    
    @Override
    public List<Watch> getWatchesSortedByPrice(boolean ascending) {
        if (ascending) {
            return watchRepository.findAllByOrderByPriceAsc();
        } else {
            return watchRepository.findAllByOrderByPriceDesc();
        }
    }
    


    
    @Override
    public void deleteWatch(int id) {
        Watch watch = watchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Watch not found"));
        watchRepository.delete(watch);
    }

    @Override
    public Watch getWatchById(int id) {
        Watch watch = watchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Watch not found"));
        return watch;
    }

    @Override
    public List<Watch> getAllWatches() {
    	List<Watch> watches = new ArrayList<Watch>();
		
    	watches = watchRepository.findAll();
    	return watches;
    }

	@Override
	public List<Watch> searchWatchesByName(String brand) {
		return watchRepository.findByTitleContainingIgnoreCase(brand);
	}

	
}
