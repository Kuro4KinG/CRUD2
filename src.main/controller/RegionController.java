package controller;

import model.Region;
import Implementation.RegionRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class RegionController {
    private RegionRepositoryImpl regionRepositoryImpl = new RegionRepositoryImpl();

    public Region createRegion(String name) throws IOException {
        Region region = new Region(null, name);
        return regionRepositoryImpl.save(region);
    }

    public List<Region> getAll() throws IOException {
        return regionRepositoryImpl.getAll();
    }

    public void deleteRegion(Long id) throws IOException {
        regionRepositoryImpl.delete(id);
    }

    public Region getRegionById(Long id) throws IOException {
        return regionRepositoryImpl.getById(id);
    }
    public Region updateRegion(Long id, String name)throws IOException{
        Region updatedRegion= new Region(id,name);
        return regionRepositoryImpl.update(updatedRegion);
    }
}
