package jp.mikunika.SpringBootInsurance.controller;

import jp.mikunika.SpringBootInsurance.model.InsuranceClient;
import jp.mikunika.SpringBootInsurance.model.InsuranceObjectType;
import jp.mikunika.SpringBootInsurance.repository.InsuranceObjectTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("types")
public class InsuranceObjectTypeController {
    private final InsuranceObjectTypeRepository typeRepository;

    @Autowired
    public InsuranceObjectTypeController(InsuranceObjectTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public List<InsuranceObjectType> getTypes() {
        return typeRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public InsuranceObjectType getOneType(@PathVariable("id") InsuranceObjectType type) {
        return type;
    }

    @PostMapping
    public InsuranceObjectType create(@RequestBody InsuranceObjectType type) {
        return typeRepository.save(type);
    }

    @PutMapping(value = "{id}")
    public InsuranceObjectType update(@PathVariable("id") InsuranceObjectType type, @RequestBody InsuranceClient typeNew) {
        BeanUtils.copyProperties(typeNew, type, "id");
        return typeRepository.save(type);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") InsuranceObjectType type) {
        typeRepository.delete(type);
    }
}
