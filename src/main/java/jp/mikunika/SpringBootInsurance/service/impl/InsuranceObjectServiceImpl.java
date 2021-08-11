package jp.mikunika.SpringBootInsurance.service.impl;

import jp.mikunika.SpringBootInsurance.model.*;
import jp.mikunika.SpringBootInsurance.repository.InsuranceObjectRepository;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectService;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectTypeService;
import jp.mikunika.SpringBootInsurance.service.InsuranceOptionService;
import jp.mikunika.SpringBootInsurance.service.InsurancePolicyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceObjectServiceImpl implements InsuranceObjectService {

    private final InsuranceObjectRepository objectRepository;
    private final InsurancePolicyService policyService;
    private final InsuranceOptionService optionService;
    private final InsuranceObjectTypeService objectTypeService;

    @Autowired
    InsuranceObjectServiceImpl(InsuranceObjectRepository objectRepository,
                               @Lazy InsurancePolicyService policyService,
                               @Lazy InsuranceOptionService optionService,
                               @Lazy InsuranceObjectTypeService objectTypeService) {
        this.objectRepository = objectRepository;
        this.policyService = policyService;
        this.optionService = optionService;
        this.objectTypeService = objectTypeService;
    }

    @Override
    public List<InsuranceObject> getAll() {
        return objectRepository.findAll();
    }

    @Override
    public InsuranceObject getOne(Long id) {
        return objectRepository.getById(id);
    }

    @Override
    public InsuranceObject save(InsuranceObject entity) {
        return objectRepository.save(entity);
    }

    @Override
    public InsuranceObject update(Long id, InsuranceObject entityNew) {
        InsuranceObject object = getOne(id);
        BeanUtils.copyProperties(entityNew, object, "id");
        return objectRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        objectRepository.deleteById(id);
    }

    @Override
    public List<InsuranceOption> getObjectOptions(Long objectId) {
        InsuranceObject object = getOne(objectId);
        return List.copyOf(object.getInsuranceOptionList());
    }

    @Override
    public InsuranceObjectType getObjectType(Long objectId) {
        InsuranceObject object = getOne(objectId);
        return object.getInsuranceObjectType();
    }

    @Override
    public InsurancePolicy getObjectPolicy(Long objectId) {
        InsuranceObject object = getOne(objectId);
        return object.getInsurancePolicy();
    }

    @Override
    public InsuranceObject addOptionToObject(Long objectId, InsuranceOption option) {
        InsuranceObject object = getOne(objectId);
        optionService.save(option);
        object.getInsuranceOptionList().add(option);
        return save(object);
    }

    @Override
    public InsuranceObject addPolicyToObject(Long objectId, InsurancePolicy policy) {
        InsuranceObject object = getOne(objectId);
        policyService.save(policy);
        object.setInsurancePolicy(policy);
        return save(object);
    }

    @Override
    public InsuranceObject addTypeToObject(Long objectId, InsuranceObjectType objectType) {
        InsuranceObject object = getOne(objectId);
        objectTypeService.save(objectType);
        object.setInsuranceObjectType(objectType);
        return save(object);
    }

    @Override
    public InsuranceObject setOptionToObject(Long objectId, Long optionId) {
        InsuranceObject object = getOne(objectId);
        InsuranceOption option = optionService.getOne(optionId);
        object.getInsuranceOptionList().add(option);
        return save(object);
    }

    @Override
    public InsuranceObject setPolicyToObject(Long objectId, Long policyId) {
        InsuranceObject object = getOne(objectId);
        InsurancePolicy policy = policyService.getOne(policyId);
        object.setInsurancePolicy(policy);
        return save(object);
    }

    @Override
    public InsuranceObject setTypeToObject(Long objectId, Long objectTypeId) {
        InsuranceObject object = getOne(objectId);
        InsuranceObjectType objectType = objectTypeService.getOne(objectTypeId);
        object.setInsuranceObjectType(objectType);
        return save(object);
    }
}
