package repository;

import model.Region;

import java.io.IOException;
import java.util.List;

public interface RegionRepository extends GenericRepository<Region, Long> {


    Region save(Region region) throws IOException;

    List<Region> getAll() throws IOException;

    Region getById(Long id) throws IOException;

    Region update(Region region) throws IOException;

    void delete(Long id) throws IOException;
}
