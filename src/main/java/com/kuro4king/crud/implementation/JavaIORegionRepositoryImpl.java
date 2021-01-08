package com.kuro4king.crud.implementation;

import com.kuro4king.crud.repository.RegionRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import com.kuro4king.crud.model.Region;

public class JavaIORegionRepositoryImpl implements RegionRepository {
    final String regions = "src/main/resources/files/regions.txt";

    @Override
    public List<Region> getAll() throws IOException {
        return getLines(regions).
                stream()
                .map(this::regionFromLine)
                .collect(Collectors.toList());
    }

    @Override
    public Region getById(Long id) throws IOException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    public Region getByName(String name) throws IOException {
        return getAll().stream().filter(line -> line.getName().equals(name))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Region save(Region newRegion) throws IOException {
        List<Region> list = getAll();
        newRegion = new Region(generateID(list), newRegion.getName());
        list.add(newRegion);
        writeLines(regions, list);
        return newRegion;
    }

    @Override
    public Region update(Region region) throws IOException {
        List<Region> list = getAll();
        Region updatedRegion = list.stream()
                .filter(el -> el.getId().equals(region.getId()))
                .findFirst().get();
        updatedRegion.setName(region.getName());
        writeLines(regions, list);
        return updatedRegion;
    }

    @Override
    public void delete(Long id) throws IOException {
        List<Region> list = getAll();
        list.remove(list.stream().
                filter(el -> el.getId().equals(id))
                .collect(Collectors.toList()).get(0));
        writeLines(regions, list);
    }

    private List<String> getLines(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.toList());
    }

    private void writeLines(String path, List<Region> list) throws IOException {
        List newList = list.stream()
                .map(Region::toString)
                .collect(Collectors.toList());
        Files.write(Paths.get(path), newList);
    }

    private Region regionFromLine(String line) {
        String[] splitLine = line.split(". ");
        return new Region(Long.parseLong(splitLine[0]), splitLine[1]);
    }

    private Long generateID(List<Region> list) {
        return list.stream().map(Region::getId).max(Long::compare).get() + 1;
    }
}

