package javadeveloper.uzbMapSpring.service;

import javadeveloper.uzbMapSpring.Response;
import javadeveloper.uzbMapSpring.dto.DistrictDTO;
import javadeveloper.uzbMapSpring.entity.District;
import javadeveloper.uzbMapSpring.entity.Region;
import javadeveloper.uzbMapSpring.repository.DistrictRepository;
import javadeveloper.uzbMapSpring.repository.RegionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictService {

    final DistrictRepository districtRepository;
    final RegionRepository regionRepository;

    public DistrictService(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }

    public Response getAllDistrict() {
        if (districtRepository.findAll().isEmpty())
            return new Response("empty", false);

        return new Response("success", true, districtRepository.findAll());

    }

    public Response getOneDistrict(Integer id) {
        Optional<District> district = districtRepository.findById(id);
        return district.map(value -> new Response("success", true, value)).orElseGet(() -> new Response("such district id was not found", false));

    }

    public Response addDistrict(DistrictDTO districtDTO) {
        Optional<Region> region = regionRepository.findById(districtDTO.getRegionId());
        if (!region.isPresent())
            return new Response("such region id was not found", false);

        District district = new District();
        district.setName(districtDTO.getName());
        district.setDescription(districtDTO.getDescription());
        districtRepository.save(district);
        return new Response("address added", true);
    }

    public Response addDistrict(DistrictDTO[] districtDTOS) {
        for (DistrictDTO dto : districtDTOS) {
            Optional<Region> region = regionRepository.findById(dto.getRegionId());
            if (!region.isPresent())
                return new Response("such district id was not found", false);

            District district = new District();
            district.setName(dto.getName());
            district.setDescription(dto.getDescription());
            district.setRegion(region.get());
            districtRepository.save(district);
        }
        return new Response("district added", true);
    }

    public Response updateDistrict(Integer id, DistrictDTO districtDTO) {
        Optional<Region> region = regionRepository.findById(districtDTO.getRegionId());
        if (!region.isPresent())
            return new Response("such district id was not found", false);
        Optional<District> district = districtRepository.findById(id);
        if (!district.isPresent())
            return new Response("such district id was not found", false);

        district.get().setName(districtDTO.getName());
        district.get().setDescription(districtDTO.getDescription());
        district.get().setRegion(region.get());

        return new Response("district added", true);
    }

    public Response deleteDistrict(Integer id) {
        Optional<District> district = districtRepository.findById(id);
        if (!district.isPresent())
            return new Response("such district id was not found", false);
        districtRepository.delete(district.get());
        return new Response("district deleted", true);
    }

    public Response getDistrictReq(Integer id) {

    return  new Response("success",true,districtRepository.getAllDistrictByRegionId(id));
    }

    public Response getRegionPage(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page,size);
        Page districtPage = districtRepository.findAll(pageable);

        return new Response("success",true,districtPage.toList());
    }


}
