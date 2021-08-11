package jp.mikunika.SpringBootInsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceObjectType;
import jp.mikunika.SpringBootInsurance.model.InsuranceOption;
import jp.mikunika.SpringBootInsurance.model.InsurancePolicy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InsuranceObjectDto {
    private Long id;
    private String name;
    private Double price;

    @JsonIgnoreProperties("insuranceObjectList")
    private List<InsuranceOption> insuranceOptionList = new ArrayList<>();

    @JsonIgnoreProperties("insuranceObjectList")
    private InsuranceObjectType insuranceObjectType;

    @JsonIgnoreProperties("insuranceObjectList")
    private InsurancePolicy insurancePolicy;

    public static InsuranceObjectDto from(InsuranceObject object) {
        InsuranceObjectDto insuranceObjectDto = new InsuranceObjectDto();
        insuranceObjectDto.setId(object.getId());
        insuranceObjectDto.setName(object.getName());
        insuranceObjectDto.setInsuranceOptionList(List.copyOf(object.getInsuranceOptionList()));
        insuranceObjectDto.setInsuranceObjectType(object.getInsuranceObjectType());
        insuranceObjectDto.setInsurancePolicy(object.getInsurancePolicy());
        return insuranceObjectDto;
    }
}
