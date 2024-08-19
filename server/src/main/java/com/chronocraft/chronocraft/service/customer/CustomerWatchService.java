package com.chronocraft.chronocraft.service.customer;

import com.chronocraft.chronocraft.dto.WatchDTO;

import java.util.List;

public interface CustomerWatchService {
    List<WatchDTO> getAllWatches();

    List<WatchDTO> getWatchesByName(String name);
}
