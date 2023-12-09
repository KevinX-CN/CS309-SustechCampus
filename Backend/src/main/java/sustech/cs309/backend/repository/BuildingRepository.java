package sustech.cs309.backend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.cs309.backend.model.Building;

public interface BuildingRepository extends JpaRepository<Building, UUID> {

}