package jp.mikunika.SpringBootInsurance.repository;

import jp.mikunika.SpringBootInsurance.model.InsuranceObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceObjectTypeRepository extends JpaRepository<InsuranceObjectType, Long> {
}
