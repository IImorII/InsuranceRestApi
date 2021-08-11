package jp.mikunika.SpringBootInsurance.model;

import jp.mikunika.SpringBootInsurance.dto.InsuranceObjectTypeDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "object_type")
@Getter
@Setter
public class InsuranceObjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "insuranceObjectType")
    Set<InsuranceObject> insuranceObjectList = new HashSet<>();

    public static InsuranceObjectType from(InsuranceObjectTypeDto objectTypeDto) {
        InsuranceObjectType objectType = new InsuranceObjectType();
        objectType.setName(objectTypeDto.getName());
        return objectType;
    }
}
