package javadeveloper.uzbMapSpring.service;

import javadeveloper.uzbMapSpring.Response;
import javadeveloper.uzbMapSpring.dto.AddressDTO;
import javadeveloper.uzbMapSpring.entity.Address;
import javadeveloper.uzbMapSpring.entity.District;
import javadeveloper.uzbMapSpring.repository.AddressRepository;
import javadeveloper.uzbMapSpring.repository.DistrictRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    final AddressRepository addressRepository;
    final DistrictRepository districtRepository;

    public AddressService(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }


    public Response getAllAddresses() {
        if (addressRepository.findAll().isEmpty())
            return new Response("not success", false);

        return new Response("success", true, addressRepository.findAll());

    }

    public Response getOneAddresses(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(value -> new Response("success", true, value)).orElseGet(() -> new Response("such address was not found", false));

    }

    public Response addAddress(AddressDTO addressDTO) {
        Optional<District> district = districtRepository.findById(addressDTO.getDistrictId());
        if (!district.isPresent())
            return new Response("such district id was not found",false);

        Address address = new Address();
        address.setName(addressDTO.getName());
        address.setDescription(addressDTO.getDescription());
        address.setDistrict(district.get());
        addressRepository.save(address);
        return new Response("address added",true);
    }

    public Response addAddress(AddressDTO[] addressDTO) {
        for (AddressDTO dto : addressDTO) {
            Optional<District> district = districtRepository.findById(dto.getDistrictId());
            if (!district.isPresent())
                return new Response("such district id was not found",false);

            Address address = new Address();
            address.setName(dto.getName());
            address.setDescription(dto.getDescription());
            address.setDistrict(district.get());
            addressRepository.save(address);
        }
        return new Response("address added",true);
    }

    public Response updateAddress(Integer id, AddressDTO addressDTO) {
        Optional<District> district = districtRepository.findById(addressDTO.getDistrictId());
        if (!district.isPresent())
            return new Response("such district id was not found",false);
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent())
            return new Response("such address id was not found",false);
        Address address1 = address.get();
        address1.setName(addressDTO.getName());
        address1.setDescription(addressDTO.getDescription());
        address1.setDistrict(district.get());


        return new Response("address added",true);


    }

    public Response deleteAddress(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent())
            return new Response("such address id was not found",false);
        addressRepository.delete(address.get());
        return new Response("address deleted",true);
    }

    public Response getRegionPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page addressPage = addressRepository.findAll(pageable);

        return new Response("success",true,addressPage.toList());

    }

    public Response getAddressReq(Integer id) {
        return  new Response("success",true,addressRepository.getAllDistrictByDistrictId(id));

    }
}
