package com.kuro4king.crud.controller;


import com.kuro4king.crud.repository.implementation.jsonimpl.JsonRegionRepositoryImpl;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.repository.implementation.ioimpl.JavaIORegionRepositoryImpl;
import com.kuro4king.crud.repository.RegionRepository;

import java.io.IOException;
import java.util.List;

public class RegionController {
    private RegionRepository regionRepository;

    public RegionController(String format) {
        if (format.equals("json")) {
            regionRepository = new JsonRegionRepositoryImpl();
        } else if (format.equals("txt")) {
            regionRepository = new JavaIORegionRepositoryImpl();
        }
    }


    public Region createRegion(String name) {
        Region region = new Region(null, name);
        return regionRepository.save(region);
    }

    public List<Region> getAll() throws IOException {
        return regionRepository.getAll();
    }

    public void deleteRegion(Long id) {
        regionRepository.delete(id);
    }

    public Region getRegionById(Long id) {
        return regionRepository.getById(id);
    }

    public Region getRegionByName(String name) throws IOException {
        return regionRepository.getByName(name);
    }

    public Region updateRegion(Long id, String name) {
        Region updatedRegion = new Region(id, name);
        return regionRepository.update(updatedRegion);
    }
}
