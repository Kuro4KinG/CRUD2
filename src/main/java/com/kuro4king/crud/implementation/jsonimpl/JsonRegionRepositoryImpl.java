package com.kuro4king.crud.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.repository.RegionRepository;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonRegionRepositoryImpl implements RegionRepository {
    final String regionPath = "src/main/resources/files/json/regions.json";
    Gson gson = new Gson();
    @Override
    public Region save(Region region) throws IOException {
        List<Region> regions = getAll();
        regions.add(region);
        writeLines(regionPath, regions);
        return region;
    }

    @Override
    public List<Region> getAll() throws IOException {
        FileReader fileReader = new FileReader(regionPath);
        List<Region> regions = gson.fromJson(fileReader, new TypeToken<ArrayList<Region>>() {
        }.getType());
        fileReader.close();
        return regions;
    }

    @Override
    public Region getById(Long id) throws IOException {
        List<Region> regions = getAll();
        for (Region region :
                regions) {
            if (region.getId().equals(id))
                return region;
        }
        return null;
    }

    @Override
    public Region update(Region region) throws IOException {
        List<Region> regions = getAll();
        for (Region reg :
                regions) {
            if (reg.getId().equals(region.getId()))
                reg.setName(region.getName());
        }
        writeLines(regionPath, regions);
        return region;
    }

    @Override
    public void delete(Long id) throws IOException {
        List<Region> regions = getAll();
        Region regionToDelete = null;
        for (Region reg :
                regions) {
            if (reg.getId().equals(id))
                regionToDelete = reg;
        }
        regions.remove(regionToDelete);
        writeLines(regionPath, regions);
    }

    @Override
    public Region getByName(String name) throws IOException {
        List<Region> regions = getAll();
        for (Region region :
                regions) {
            if (region.getName().equals(name))
                return region;
        }
        return null;
    }

    private void writeLines(String regionPath, List<Region> regions) throws IOException {
        FileWriter writer = new FileWriter(regionPath);
        gson.toJson(regions, writer);
        writer.close();
    }
}
