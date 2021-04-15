package com.kuro4king.crud.repository;

import com.kuro4king.crud.model.Region;

import java.util.List;

public interface RegionRepository extends GenericRepository<Region, Long> {

    Region save(Region region);

    List<Region> getAll();

    Region getById(Long id);

    Region update(Region region);

    void delete(Long id);

    Region getByName(String name);
}
