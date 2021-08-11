package jp.mikunika.SpringBootInsurance.controller;

import jp.mikunika.SpringBootInsurance.dto.InsurancePolicyDto;
import jp.mikunika.SpringBootInsurance.model.InsurancePolicy;
import jp.mikunika.SpringBootInsurance.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("policies")
public class InsurancePolicyController {
    private final InsurancePolicyService policyService;

    @Autowired
    public InsurancePolicyController(InsurancePolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity<List<InsurancePolicyDto>> getPolicies() {
        List<InsurancePolicy> policyList = policyService.getAll();
        List<InsurancePolicyDto> policyDtoList = policyList.stream().map(InsurancePolicyDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(policyDtoList);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<InsurancePolicyDto> getOnePolicy(@PathVariable final Long id) {
        InsurancePolicy policy = policyService.getOne(id);
        return ResponseEntity.ok(InsurancePolicyDto.from(policy));
    }

    @PostMapping
    public ResponseEntity<InsurancePolicyDto> create(@RequestBody InsurancePolicyDto policyDto) {
        InsurancePolicy policy = policyService.save(InsurancePolicy.from(policyDto));
        return ResponseEntity.ok(InsurancePolicyDto.from(policy));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<InsurancePolicyDto> update(@PathVariable final Long id, @RequestBody InsurancePolicyDto policyNewDto) {
        InsurancePolicy policy = policyService.update(id, InsurancePolicy.from(policyNewDto));
        return ResponseEntity.ok(InsurancePolicyDto.from(policy));
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        policyService.delete(id);
    }
}
