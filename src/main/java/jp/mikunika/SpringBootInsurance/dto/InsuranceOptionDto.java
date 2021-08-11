package jp.mikunika.SpringBootInsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InsuranceOptionDto {
    private Long id;
    private String name;

    @JsonIgnoreProperties("insuranceOptionList")
    List<InsuranceObject> insuranceObjectList = new ArrayList<>();

    public static InsuranceOptionDto from(InsuranceOption option) {
        InsuranceOptionDto insuranceOptionDto = new InsuranceOptionDto();
        insuranceOptionDto.setId(option.getId());
        insuranceOptionDto.setName(option.getName());
        insuranceOptionDto.setInsuranceObjectList(List.copyOf(option.getInsuranceObjectList()));
        return insuranceOptionDto;
    }
}
