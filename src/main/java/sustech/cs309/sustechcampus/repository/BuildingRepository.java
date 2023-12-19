package sustech.cs309.sustechcampus.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.cs309.sustechcampus.model.Building;

public interface BuildingRepository extends JpaRepository<Building, UUID> {
  List<Building> findAllByBuildingName(String buildingName);
}