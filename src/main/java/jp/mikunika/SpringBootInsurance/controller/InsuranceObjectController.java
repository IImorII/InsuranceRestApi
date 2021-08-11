package jp.mikunika.SpringBootInsurance.controller;

import jp.mikunika.SpringBootInsurance.dto.InsuranceObjectDto;
import jp.mikunika.SpringBootInsurance.dto.InsuranceOptionDto;
import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceOption;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("objects")
public class InsuranceObjectController {

    private final InsuranceObjectService objectService;

    @Autowired
    public InsuranceObjectController(InsuranceObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping
    public ResponseEntity<List<InsuranceObjectDto>> getObjects() {
        List<InsuranceObject> objects = objectService.getAll();
        List<InsuranceObjectDto> objectsDtos = objects.stream().map(InsuranceObjectDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(objectsDtos);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<InsuranceObjectDto> getOneObject(@PathVariable final Long id) {
        InsuranceObject object = objectService.getOne(id);
        return ResponseEntity.ok(InsuranceObjectDto.from(object));
    }

    @PostMapping
    public ResponseEntity<InsuranceObjectDto> create(@RequestBody InsuranceObjectDto objectDto) {
        InsuranceObject object = objectService.save(InsuranceObject.from(objectDto));
        return ResponseEntity.ok(InsuranceObjectDto.from(object));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<InsuranceObjectDto> update(@PathVariable final Long id, @RequestBody InsuranceObjectDto objectNewDto) {
        InsuranceObject object = objectService.update(id, InsuranceObject.from(objectNewDto));
        return ResponseEntity.ok(InsuranceObjectDto.from(object));
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        objectService.delete(id);
    }

    @PostMapping(value = "{objectId}/options")
    public  ResponseEntity<InsuranceObjectDto> addOptionToObject(@PathVariable final Long objectId, @RequestBody InsuranceOptionDto optionDto) {
        InsuranceObject object = objectService.addOptionToObject(objectId, InsuranceOption.from(optionDto));
        return ResponseEntity.ok(InsuranceObjectDto.from(object));
    }
}
