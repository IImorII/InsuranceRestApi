package jp.mikunika.SpringBootInsurance.repository;

import jp.mikunika.SpringBootInsurance.model.InsuranceOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceOptionRepository extends JpaRepository<InsuranceOption, Long> {
}
