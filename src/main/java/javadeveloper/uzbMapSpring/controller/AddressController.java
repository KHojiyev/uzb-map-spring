package javadeveloper.uzbMapSpring.controller;

import javadeveloper.uzbMapSpring.Response;
import javadeveloper.uzbMapSpring.dto.AddressDTO;
import javadeveloper.uzbMapSpring.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/request")
    public Response getDistrictRequest(@RequestParam Integer id){
        return addressService.getAddressReq(id);
    }

    @GetMapping("/page")
    public Response getDistrictPage(@RequestParam Integer page,@RequestParam Integer size){
        return  addressService.getRegionPage(page,size);
    }

    @GetMapping
    public Response getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Response getAllAddresses(@PathVariable Integer id) {
        return addressService.getOneAddresses(id);
    }

    @PostMapping("/one")
    public Response addAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    @PostMapping
    public Response addAddress(@RequestBody AddressDTO[] addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    @PutMapping("/{id}")
    public Response updateAddress(@PathVariable Integer id,@RequestBody AddressDTO addressDTO){
        return addressService.updateAddress(id,addressDTO);
    }

    @DeleteMapping("/{id}")
    public Response deleteAddress(@PathVariable Integer id){
        return addressService.deleteAddress(id);
    }
}
