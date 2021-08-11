package jp.mikunika.SpringBootInsurance.service.impl;

import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceOption;
import jp.mikunika.SpringBootInsurance.repository.InsuranceOptionRepository;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectService;
import jp.mikunika.SpringBootInsurance.service.InsuranceOptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceOptionServiceImpl implements InsuranceOptionService {

    private final InsuranceOptionRepository optionRepository;
    private final InsuranceObjectService objectService;

    InsuranceOptionServiceImpl(InsuranceOptionRepository optionRepository,
                               InsuranceObjectService objectService) {
        this.optionRepository = optionRepository;
        this.objectService = objectService;
    }

    @Override
    public List<InsuranceOption> getAll() {
        return optionRepository.findAll();
    }

    @Override
    public InsuranceOption getOne(Long id) {
        return optionRepository.getById(id);
    }

    @Override
    public InsuranceOption save(InsuranceOption entity) {
        return optionRepository.save(entity);
    }

    @Override
    public InsuranceOption update(Long id, InsuranceOption entityNew) {
        InsuranceOption option = getOne(id);
        BeanUtils.copyProperties(entityNew, option, "id");
        return optionRepository.save(option);
    }

    @Override
    public void delete(Long id) {
        optionRepository.deleteById(id);
    }

    @Override
    public List<InsuranceObject> getOptionObjects(Long optionId) {
        InsuranceOption option = getOne(optionId);
        return List.copyOf(option.getInsuranceObjectList());
    }

    @Override
    public InsuranceOption addObjectToOption(Long optionId, InsuranceObject object) {
        InsuranceOption option = getOne(optionId);
        objectService.save(object);
        object.getInsuranceOptionList().add(option);
        return save(option);
    }

    @Override
    public InsuranceOption setObjectToOption(Long optionId, Long objectId) {
        InsuranceOption option = getOne(optionId);
        InsuranceObject object = objectService.getOne(objectId);
        object.getInsuranceOptionList().add(option);
        return save(option);
    }
}
