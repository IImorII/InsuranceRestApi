package jp.mikunika.SpringBootInsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.mikunika.SpringBootInsurance.model.InsuranceClient;
import jp.mikunika.SpringBootInsurance.model.InsurancePolicy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InsuranceClientDto {
    private Long id;
    private String name;
    private Integer birth;

    @JsonIgnoreProperties("client")
    private List<InsurancePolicy> insurancePolicyList = new ArrayList<>();

    public static InsuranceClientDto from(InsuranceClient client) {
        InsuranceClientDto clientDto = new InsuranceClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setBirth(client.getBirth());
        clientDto.setInsurancePolicyList(List.copyOf(client.getInsurancePolicyList()));
        return clientDto;
    }
}
