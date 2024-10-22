package by.ustsinovich.fcadhack.repository;

import by.ustsinovich.fcadhack.entity.FilteringRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilteringRuleRepository extends JpaRepository<FilteringRuleEntity, Long> {
    List<FilteringRuleEntity> findAllByStatus(boolean status);
}
