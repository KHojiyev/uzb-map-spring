package javadeveloper.uzbMapSpring.controller;

import javadeveloper.uzbMapSpring.Response;
import javadeveloper.uzbMapSpring.entity.Region;
import javadeveloper.uzbMapSpring.service.RegionService;
import org.omg.CORBA.INTERNAL;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/region")
public class RegionController {


    final RegionService regionService;


    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/page")
    public Response getRegionPage(@RequestParam Integer page,@RequestParam Integer size){
        return  regionService.getRegionPage(page,size);
    }

    @GetMapping
    public Response getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{id}")
    public Response getOneRegion(@PathVariable Integer id) {
        return regionService.getOneRegion(id);
    }

    @PostMapping("/one")
    public Response addRegion(@RequestBody Region region) {
        return regionService.addRegion(region);
    }

    @PostMapping
    public Response addRegion(@RequestBody Region[] region) {
        return regionService.addRegion(region);
    }

    @PutMapping("/{id}")
    public Response updateRegion(@PathVariable Integer id, @RequestBody Region region){
        return regionService.updateRegion(id,region);
    }

    @DeleteMapping("{id}")
    public Response deleteRegion(@PathVariable Integer id){
        return regionService.deleteRegion(id);
    }


}
