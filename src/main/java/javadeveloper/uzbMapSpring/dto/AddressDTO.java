package javadeveloper.uzbMapSpring.dto;

import javadeveloper.uzbMapSpring.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String name;
    private String description;
    private Integer districtId;

}
