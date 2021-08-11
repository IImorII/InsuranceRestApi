package jp.mikunika.SpringBootInsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceObjectType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InsuranceObjectTypeDto {
    private Long id;
    private String name;

    @JsonIgnoreProperties("insuranceObjectType")
    private List<InsuranceObject> insuranceObjectList = new ArrayList<>();

    public static InsuranceObjectTypeDto from(InsuranceObjectType objectType) {
        InsuranceObjectTypeDto objectTypeDto = new InsuranceObjectTypeDto();
        objectTypeDto.setId(objectType.getId());
        objectTypeDto.setName(objectType.getName());
        objectTypeDto.setInsuranceObjectList(List.copyOf(objectType.getInsuranceObjectList()));
        return objectTypeDto;
    }
}
