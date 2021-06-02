package javadeveloper.uzbMapSpring.repository;

import javadeveloper.uzbMapSpring.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {

    @Query("select d from District d where d.region.id = ?1")
    List<District> getAllDistrictByRegionId(Integer id);

}
