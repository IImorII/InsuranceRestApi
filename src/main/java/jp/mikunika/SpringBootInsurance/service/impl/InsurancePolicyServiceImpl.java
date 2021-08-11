package jp.mikunika.SpringBootInsurance.service.impl;

import jp.mikunika.SpringBootInsurance.model.InsuranceClient;
import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsurancePolicy;
import jp.mikunika.SpringBootInsurance.repository.InsurancePolicyRepository;
import jp.mikunika.SpringBootInsurance.service.InsuranceClientService;
import jp.mikunika.SpringBootInsurance.service.InsuranceObjectService;
import jp.mikunika.SpringBootInsurance.service.InsurancePolicyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    private final InsurancePolicyRepository policyRepository;
    private final InsuranceObjectService objectService;
    private final InsuranceClientService clientService;

    @Autowired
    InsurancePolicyServiceImpl(InsurancePolicyRepository policyRepository,
                               InsuranceObjectService objectService,
                               @Lazy InsuranceClientService clientService) {
        this.policyRepository = policyRepository;
        this.objectService = objectService;
        this.clientService = clientService;
    }

    @Override
    public List<InsurancePolicy> getAll() {
        return policyRepository.findAll();
    }

    @Override
    public InsurancePolicy getOne(Long id) {
        return policyRepository.getById(id);
    }

    @Override
    public InsurancePolicy save(InsurancePolicy entity) {
        return policyRepository.save(entity);
    }

    @Override
    public InsurancePolicy update(Long id, InsurancePolicy entityNew) {
        InsurancePolicy policy = getOne(id);
        BeanUtils.copyProperties(entityNew, policy, "id");
        return policyRepository.save(policy);
    }

    @Override
    public void delete(Long id) {
        policyRepository.deleteById(id);
    }


    @Override
    public InsuranceClient getPolicyClient(Long policyId) {
        InsurancePolicy policy = getOne(policyId);
        return policy.getClient();
    }

    @Override
    public List<InsuranceObject> getPolicyObjects(Long policyId) {
        InsurancePolicy policy = getOne(policyId);
        return List.copyOf(policy.getInsuranceObjectList());
    }

    @Override
    public InsurancePolicy addObjectToPolicy(Long policyId, InsuranceObject object) {
        InsurancePolicy policy = getOne(policyId);
        objectService.save(object);
        object.setInsurancePolicy(policy);
        return save(policy);
    }

    @Override
    public InsurancePolicy addClientToPolicy(Long policyId, InsuranceClient client) {
        InsurancePolicy policy = getOne(policyId);
        clientService.save(client);
        policy.setClient(client);
        return save(policy);
    }

    @Override
    public InsurancePolicy setObjectToPolicy(Long policyId, Long objectId) {
        InsurancePolicy policy = getOne(policyId);
        InsuranceObject object = objectService.getOne(objectId);
        object.setInsurancePolicy(policy);
        return save(policy);
    }

    @Override
    public InsurancePolicy setClientToPolicy(Long policyId, Long clientId) {
        InsurancePolicy policy = getOne(policyId);
        InsuranceClient client = clientService.getOne(clientId);
        policy.setClient(client);
        return save(policy);
    }
}
