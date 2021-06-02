package javadeveloper.uzbMapSpring.dto;

import javadeveloper.uzbMapSpring.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {


    private String name;
    private String description;
    private Integer regionId;
}
