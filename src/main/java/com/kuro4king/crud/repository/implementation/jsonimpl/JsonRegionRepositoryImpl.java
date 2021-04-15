package com.kuro4king.crud.repository.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.repository.RegionRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonRegionRepositoryImpl implements RegionRepository {
    private final String regionPath = "src/main/resources/files/json/regions.json";
    Gson gson = new Gson();

    @Override
    public List<Region> getAll() {
        try {
            return gson.fromJson(new FileReader(regionPath), new TypeToken<ArrayList<Region>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Region getById(Long id) {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Region getByName(String name) {
        return getAll().stream().filter(line -> line.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Region save(Region newRegion) {
        List<Region> regions = getAll();
        newRegion = new Region(generateID(regions), newRegion.getName());
        regions.add(newRegion);
        writeLines(regions);
        return newRegion;
    }


    @Override
    public Region update(Region region) {
        List<Region> regions = getAll();
        regions.stream()
                .filter(el -> el.getId().equals(region.getId()))
                .findFirst().ifPresent(updatedRegion -> updatedRegion.setName(region.getName()));
        writeLines(regions);
        return region;
    }

    @Override
    public void delete(Long id){
        List<Region> regions = getAll();
        regions.remove(regions.stream().
                filter(el -> el.getId().equals(id))
                .findFirst().orElse(null));
        writeLines(regions);
    }


    private void writeLines(List<Region> regions) {
        try (FileWriter writer = new FileWriter(regionPath)) {
            gson.toJson(regions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateID(List<Region> list) {
        return list.stream().map(Region::getId).max(Long::compare).orElse(null) + 1;
    }
}
