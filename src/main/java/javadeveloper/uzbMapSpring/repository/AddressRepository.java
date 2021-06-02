package javadeveloper.uzbMapSpring.repository;

import javadeveloper.uzbMapSpring.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select a from Address a where a.district.id = ?1")
    List<Address> getAllDistrictByDistrictId(Integer id);
}
