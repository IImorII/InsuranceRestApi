package jp.mikunika.SpringBootInsurance.repository;

import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceObjectRepository extends JpaRepository<InsuranceObject, Long> {
}
