package javadeveloper.uzbMapSpring.controller;
import javadeveloper.uzbMapSpring.Response;
import javadeveloper.uzbMapSpring.dto.DistrictDTO;
import javadeveloper.uzbMapSpring.service.DistrictService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/district")
public class DistrictController {

    final DistrictService districtService;


    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/page")
    public Response getDistrictPage(@RequestParam Integer page,@RequestParam Integer size){
        return  districtService.getRegionPage(page,size);
    }

    @GetMapping
    public Response getAllDistrict() {
        return districtService.getAllDistrict();
    }

    @GetMapping("/request")
    public Response getDistrictRequest(@RequestParam Integer id){
        return districtService.getDistrictReq(id);
    }

    @GetMapping("/{id}")
    public Response getAllDistricts(@PathVariable Integer id) {
        return districtService.getOneDistrict(id);
    }

    @PostMapping("/one")
    public Response addDistrict(@RequestBody DistrictDTO districtDTO) {
        return districtService.addDistrict(districtDTO);
    }

    @PostMapping
    public Response addDistrict(@RequestBody DistrictDTO[] districtDTO) {
        return districtService.addDistrict(districtDTO);
    }

    @PutMapping("/{id}")
    public Response updateDistrict(@PathVariable Integer id,@RequestBody DistrictDTO districtDTO){
        return districtService.updateDistrict(id,districtDTO);
    }
    @DeleteMapping("/{id}")
    public Response deleteDistrict(@PathVariable Integer id){
        return districtService.deleteDistrict(id);
    }



}
