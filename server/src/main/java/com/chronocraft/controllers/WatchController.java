package com.chronocraft.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chronocraft.dto.WatchDTO;
import com.chronocraft.entities.Watch;
import com.chronocraft.repositories.WatchRepository;
import com.chronocraft.services.WatchService;
import com.chronocraft.utility.StorageService;

@RestController
@RequestMapping("/watches")
@CrossOrigin(origins = "http://localhost:3000")
public class WatchController {

    @Autowired
    private WatchService watchService;
    
    @Autowired
    private WatchRepository watchrepository;
    
    @Autowired
    private StorageService storageService;
    
//    
//    @PostMapping("/addwatch")
//    public ResponseEntity<?> addWatch(
//            @RequestParam("name") String name,
//            @RequestParam("description") String description,
//            @RequestParam("price") Double price,
//            @RequestParam("categoryId") Integer categoryId,
//            @RequestParam("imageFile") MultipartFile imageFile) {
//    	
//    	try {
//			String imagePath = watchService.saveImageFile(imageFile);
//			
//			WatchDTO watchDTO = new WatchDTO();
//            watchDTO.setTitle(name);
//            watchDTO.setDescription(description);
//            watchDTO.setPrice(price);
//            watchDTO.setCategoryId(categoryId);
//            
//            Watch watch = watchService.addWatch(watchDTO, imagePath);
//            return ResponseEntity.ok(watch);
//			
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//			return ResponseEntity.status(500).body("Error uploading file");
//		}
//    }
    
    @PostMapping("/addwatch")
    	public ResponseEntity<?> createWatch(@RequestBody WatchDTO watchDTO) {
    	System.out.println("recieved request for ADD PRODUCT");
    	//Watch watch = WatchDTO.toEntity(watchDTO);

    	//String imageName = saveFile(image);

    	Watch watchResult = null;
    	try {
    		watchResult = watchService.addWatch(watchDTO);
    		} catch (FileNotFoundException e) {	
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
		}
    	//Watch watchResult=watchService.addWatch(watch, watchDTO.getImage());

        return ResponseEntity.ok(watchResult);
    }
    
    @GetMapping("/sorted-by-price")
    public ResponseEntity<List<Watch>> getWatchesSortedByPrice(
            @RequestParam("ascending") boolean ascending) {
        List<Watch> watches = watchService.getWatchesSortedByPrice(ascending);
        return ResponseEntity.ok(watches);
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllWatches() {
    	System.out.println("request came for getting all watches");
        List<Watch> watches = watchService.getAllWatches();
        return ResponseEntity.ok(watches);
    }
    
    @GetMapping("/id")
    public ResponseEntity<?> getWatchById(@RequestParam("watchId") int watchId) {
        Watch watch= watchService.getWatchById(watchId);
        return ResponseEntity.ok(watch);
    }
    
    @GetMapping("categoryid")
	public ResponseEntity<?> getWatchesByCategories(@RequestParam("categoryId") int categoryId) {
		
		System.out.println("request came for getting all watches by category");
		
		List<Watch> watches = new ArrayList<Watch>();
		
		watches = watchrepository.findByCategoryId(categoryId);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(watches);
		
	}

	@GetMapping(value="/{watchImageName}", produces = "image/*")
	public void fetchWatchImage(@PathVariable("watchImageName") String watchImageName, HttpServletResponse resp) {
		System.out.println("request came for fetching product pic");
		System.out.println("Loading file: " + watchImageName);
		Resource resource = storageService.load(watchImageName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("response sent!");
	}
    
    
    
    
//    @PutMapping("/{id}")
//    public ResponseEntity<WatchDTO> updateWatch(@PathVariable Long id, @RequestBody WatchDTO watchDTO) {
//        WatchDTO updatedWatch = watchService.updateWatch(id, watchDTO);
//        return new ResponseEntity<>(updatedWatch, HttpStatus.OK);
//    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatch(@PathVariable int id) {
        watchService.deleteWatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Watch>> searchWatches(@RequestParam("brand") String brand) {
        List<Watch> watches = watchService.searchWatchesByName(brand);
        return ResponseEntity.ok(watches);
    }
  
}
