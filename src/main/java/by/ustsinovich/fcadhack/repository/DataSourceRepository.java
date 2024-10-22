package by.ustsinovich.fcadhack.repository;

import by.ustsinovich.fcadhack.entity.DataSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSourceEntity, Long> {
}
