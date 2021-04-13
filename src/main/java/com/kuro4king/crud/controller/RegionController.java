package com.kuro4king.crud.controller;

import com.kuro4king.crud.implementation.jsonimpl.JsonRegionRepositoryImpl;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.implementation.ioimpl.JavaIORegionRepositoryImpl;
import com.kuro4king.crud.repository.RegionRepository;

import java.io.IOException;
import java.util.List;

public class RegionController {
    private RegionRepository regionRepository = new JsonRegionRepositoryImpl();

    public Region createRegion(String name) throws IOException {
        Region region = new Region(null, name);
        return regionRepository.save(region);
    }

    public List<Region> getAll() throws IOException {
        return regionRepository.getAll();
    }

    public void deleteRegion(Long id) throws IOException {
        regionRepository.delete(id);
    }

    public Region getRegionById(Long id) throws IOException {
        return regionRepository.getById(id);
    }

    public Region getRegionByName(String name) throws IOException {
        return regionRepository.getByName(name);
    }

    public Region updateRegion(Long id, String name) throws IOException {
        Region updatedRegion = new Region(id, name);
        return regionRepository.update(updatedRegion);
    }
}
