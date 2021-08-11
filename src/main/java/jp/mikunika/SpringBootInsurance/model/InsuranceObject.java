package jp.mikunika.SpringBootInsurance.model;

import jp.mikunika.SpringBootInsurance.dto.InsuranceObjectDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "insurance_object")
@Getter
@Setter
public class InsuranceObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToMany
    @JoinTable(name = "insurance_object_option",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private Set<InsuranceOption> insuranceOptionList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "object_type_fk")
    private InsuranceObjectType insuranceObjectType;

    @ManyToOne
    @JoinColumn(name = "insurance_policy_fk")
    private InsurancePolicy insurancePolicy;

    public static InsuranceObject from(InsuranceObjectDto objectDto) {
        InsuranceObject object = new InsuranceObject();
        object.setName(objectDto.getName());
        return object;
    }
}
