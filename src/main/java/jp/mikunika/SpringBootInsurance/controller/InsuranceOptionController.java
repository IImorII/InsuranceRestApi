package jp.mikunika.SpringBootInsurance.controller;

import jp.mikunika.SpringBootInsurance.dto.InsuranceOptionDto;
import jp.mikunika.SpringBootInsurance.model.InsuranceOption;
import jp.mikunika.SpringBootInsurance.service.InsuranceOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("options")
public class InsuranceOptionController {
    private final InsuranceOptionService optionService;

    @Autowired
    public InsuranceOptionController(InsuranceOptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<List<InsuranceOptionDto>> getObjects() {
        List<InsuranceOption> options = optionService.getAll();
        List<InsuranceOptionDto> optionsDtos = options.stream().map(InsuranceOptionDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(optionsDtos);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<InsuranceOptionDto> getOneObject(@PathVariable final Long id) {
        InsuranceOption option = optionService.getOne(id);
        return ResponseEntity.ok(InsuranceOptionDto.from(option));
    }

    @PostMapping
    public ResponseEntity<InsuranceOptionDto> create(@RequestBody InsuranceOptionDto optionDto) {
        InsuranceOption option = optionService.save(InsuranceOption.from(optionDto));
        return ResponseEntity.ok(InsuranceOptionDto.from(option));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<InsuranceOptionDto> update(@PathVariable final Long id, @RequestBody InsuranceOptionDto optionNewDto) {
        InsuranceOption option = optionService.update(id, InsuranceOption.from(optionNewDto));
        return ResponseEntity.ok(InsuranceOptionDto.from(option));
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        optionService.delete(id);
    }
}
