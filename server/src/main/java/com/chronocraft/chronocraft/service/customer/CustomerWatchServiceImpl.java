package com.chronocraft.chronocraft.service.customer;

import com.chronocraft.chronocraft.dto.WatchDTO;
import com.chronocraft.chronocraft.entity.WatchEntity;
import com.chronocraft.chronocraft.repository.WatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerWatchServiceImpl implements CustomerWatchService {
    private final WatchRepository watchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerWatchServiceImpl(WatchRepository watchRepository, ModelMapper modelMapper) {
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WatchDTO> getAllWatches() {
        List<WatchEntity> watchEntities = watchRepository.findAll();
        return watchEntities.stream().map(watch -> modelMapper.map(watch, WatchDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<WatchDTO> getWatchesByName(String name) {
        List<WatchEntity> watchEntities = watchRepository.findAllByNameContaining(name);
        return watchEntities.stream().map(watch -> modelMapper.map(watch, WatchDTO.class)).collect(Collectors.toList());
    }
}
