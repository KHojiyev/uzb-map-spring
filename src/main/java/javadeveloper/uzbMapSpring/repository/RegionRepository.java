package javadeveloper.uzbMapSpring.repository;

import javadeveloper.uzbMapSpring.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {


}
