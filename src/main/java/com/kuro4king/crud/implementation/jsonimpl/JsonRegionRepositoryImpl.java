package com.kuro4king.crud.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.model.User;
import com.kuro4king.crud.repository.RegionRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonRegionRepositoryImpl implements RegionRepository {
    final String regionPath = "src/main/resources/files/json/regions.json";
    Gson gson = new Gson();

    @Override
    public List<Region> getAll() throws IOException {
        return gson.fromJson(new FileReader(regionPath), new TypeToken<ArrayList<Region>>() {
        }.getType());
    }

    @Override
    public Region getById(Long id) throws IOException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Region getByName(String name) throws IOException {
        return getAll().stream().filter(line -> line.getName().equals(name))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Region save(Region newRegion) throws IOException {
        List<Region> regions = getAll();
        newRegion = new Region(generateID(regions), newRegion.getName());
        regions.add(newRegion);
        writeLines(regionPath, regions);
        return newRegion;
    }


    @Override
    public Region update(Region region) throws IOException {
        List<Region> regions = getAll();
        Region updatedRegion = regions.stream()
                .filter(el -> el.getId().equals(region.getId()))
                .findFirst().get();
        updatedRegion.setName(region.getName());
        writeLines(regionPath, regions);
        return region;
    }

    @Override
    public void delete(Long id) throws IOException {
        List<Region> regions = getAll();
        regions.remove(regions.stream().
                filter(el -> el.getId().equals(id))
                .collect(Collectors.toList()).get(0));
        writeLines(regionPath, regions);
    }


    private void writeLines(String regionPath, List<Region> regions) throws IOException {
        FileWriter writer = new FileWriter(regionPath);
        gson.toJson(regions, writer);
        writer.close();
    }
    private Long generateID(List<Region> list) {
        return list.stream().map(Region::getId).max(Long::compare).get() + 1;
    }
}
