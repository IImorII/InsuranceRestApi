package jp.mikunika.SpringBootInsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.mikunika.SpringBootInsurance.model.InsuranceClient;
import jp.mikunika.SpringBootInsurance.model.InsurancePolicy;
import lombok.Data;

@Data
public class InsurancePolicyDto {
    private Long id;
    private String name;

    @JsonIgnoreProperties("insurancePolicyList")
    private InsuranceClient client;

    public static InsurancePolicyDto from(InsurancePolicy policy) {
        InsurancePolicyDto insurancePolicyDto = new InsurancePolicyDto();
        insurancePolicyDto.setId(policy.getId());
        insurancePolicyDto.setName(policy.getName());
        if (policy.getClient() != null) {
            insurancePolicyDto.setClient(policy.getClient());
        }
        return insurancePolicyDto;
    }
}
