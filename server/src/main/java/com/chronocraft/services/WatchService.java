package com.chronocraft.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chronocraft.dto.WatchDTO;
import com.chronocraft.entities.Watch;

public interface WatchService {
	
	public Watch addWatch(WatchDTO watchDTO) throws FileNotFoundException, IOException;
    void deleteWatch(int id);
    public Watch getWatchById(int id);
    public List<Watch> getAllWatches();
	String saveImageFile(MultipartFile imageFile) throws IOException;
	List<Watch> getWatchesSortedByPrice(boolean ascending);
	List<Watch> searchWatchesByName(String title);
}
