package com.chronocraft.chronocraft.service.admin;

import com.chronocraft.chronocraft.dto.WatchDTO;

import java.util.List;

public interface WatchService {
    WatchDTO addWatch(WatchDTO watchDTO);

    List<WatchDTO> getAllWatches();

    List<WatchDTO> getWatchesByName(String name);
    boolean deleteWatch(Long id);
     WatchDTO getWatchById(Long id);
     WatchDTO updateWatch(Long id, WatchDTO watchDTO);

}
