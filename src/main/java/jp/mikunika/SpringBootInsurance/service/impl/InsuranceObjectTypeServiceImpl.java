package jp.mikunika.SpringBootInsurance.service.impl;

import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceObjectType;
import jp.mikunika.SpringBootInsurance.repository.InsuranceObjectTypeRepository;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectService;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceObjectTypeServiceImpl implements InsuranceObjectTypeService {

    private final InsuranceObjectTypeRepository objectTypeRepository;
    private final InsuranceObjectService objectService;

    @Autowired
    InsuranceObjectTypeServiceImpl(InsuranceObjectTypeRepository objectTypeRepository,
                                   InsuranceObjectService objectService) {
        this.objectTypeRepository = objectTypeRepository;
        this.objectService = objectService;
    }

    @Override
    public List<InsuranceObjectType> getAll() {
        return objectTypeRepository.findAll();
    }

    @Override
    public InsuranceObjectType getOne(Long id) {
        return objectTypeRepository.getById(id);
    }

    @Override
    public InsuranceObjectType save(InsuranceObjectType entity) {
        return objectTypeRepository.save(entity);
    }

    @Override
    public InsuranceObjectType update(Long id, InsuranceObjectType entityNew) {
        InsuranceObjectType objectType = getOne(id);
        BeanUtils.copyProperties(entityNew, objectType, "id");
        return objectTypeRepository.save(objectType);
    }

    @Override
    public void delete(Long id) {
        objectTypeRepository.deleteById(id);
    }

    @Override
    public List<InsuranceObject> getTypeObjects(Long typeId) {
        InsuranceObjectType objectType = getOne(typeId);
        return List.copyOf(objectType.getInsuranceObjectList());
    }

    @Override
    public InsuranceObjectType addObjectToType(Long typeId, InsuranceObject object) {
        InsuranceObjectType objectType = getOne(typeId);
        objectService.save(object);
        object.setInsuranceObjectType(objectType);
        return save(objectType);
    }

    @Override
    public InsuranceObjectType setObjectToType(Long typeId, Long objectId) {
        InsuranceObjectType objectType = getOne(typeId);
        InsuranceObject object = objectService.getOne(objectId);
        object.setInsuranceObjectType(objectType);
        return save(objectType);
    }
}
