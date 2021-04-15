package com.kuro4king.crud.repository.implementation.ioimpl;

import com.kuro4king.crud.repository.RegionRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import com.kuro4king.crud.model.Region;

public class JavaIORegionRepositoryImpl implements RegionRepository {
    private final String regionPath = "src/main/resources/files/txt/regions.txt";

    @Override
    public List<Region> getAll() {
        return Objects.requireNonNull(getLines()).
                stream()
                .map(this::regionFromLine)
                .collect(Collectors.toList());
    }

    @Override
    public Region getById(Long id) {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .findFirst().orElse(null);
    }

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
        Region updatedRegion = regions.stream()
                .filter(el -> el.getId().equals(region.getId()))
                .findFirst().orElse(null);
        if (updatedRegion != null) {
            updatedRegion.setName(region.getName());
        }
        writeLines(regions);
        return updatedRegion;
    }

    @Override
    public void delete(Long id) {
        List<Region> regions = getAll();
        regions.remove(regions.stream().
                filter(el -> el.getId().equals(id))
                .findFirst().orElse(null));
        writeLines(regions);
    }

    private List<String> getLines() {
        try {
            return Files.lines(Paths.get(regionPath)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeLines(List<Region> list) {
        List<String> newList = list.stream()
                .map(Region::toString)
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(regionPath), newList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Region regionFromLine(String line) {
        String[] splitLine = line.split(". ");
        return new Region(Long.parseLong(splitLine[0]), splitLine[1]);
    }

    private Long generateID(List<Region> list) {
        return list.stream().map(Region::getId).max(Long::compare).orElse(null) + 1;
    }
}

