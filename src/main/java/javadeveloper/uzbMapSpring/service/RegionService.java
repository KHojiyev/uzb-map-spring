package javadeveloper.uzbMapSpring.service;

import javadeveloper.uzbMapSpring.Response;
import javadeveloper.uzbMapSpring.entity.Region;
import javadeveloper.uzbMapSpring.repository.RegionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {

    final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Response getAllRegions() {
        if (regionRepository.findAll().isEmpty())
            return new Response("empty", false);

        return new Response("success", true, regionRepository.findAll());

    }

    public Response getOneRegion(Integer id) {
        Optional<Region> region = regionRepository.findById(id);
        return region.map(value -> new Response("success", true, value)).orElseGet(() -> new Response("such region id was not found", false));

    }

    public Response addRegion(Region[] region) {
        for (Region region2 : region) {
            Region region1 = new Region();
            region1.setName(region2.getName());
            region1.setDescription(region2.getDescription());
            regionRepository.save(region1);
        }
        return new Response("region added", true);

    }

    public Response addRegion(Region region) {

        Region region1 = new Region();
        region1.setName(region.getName());
        region1.setDescription(region.getDescription());
        regionRepository.save(region1);

        return new Response("region added", true);

    }

    public Response updateRegion(Integer id, Region region) {
        Optional<Region> byId = regionRepository.findById(id);
        if (!byId.isPresent())
            return new Response("such region id was not found", false);
        Region region1 = byId.get();
        region1.setDescription(region.getDescription());
        region1.setName(region.getName());

        return new Response("region updated", true);
    }

    public Response deleteRegion(Integer id) {
        Optional<Region> byId = regionRepository.findById(id);
        if (!byId.isPresent())
            return new Response("such region id was not found", false);
        regionRepository.delete(byId.get());
        return new Response("region deleted", true);

    }

    public Response getRegionPage(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page,size);
        Page regionPage = regionRepository.findAll(pageable);

        return new Response("success",true,regionPage.toList());

    }

}
