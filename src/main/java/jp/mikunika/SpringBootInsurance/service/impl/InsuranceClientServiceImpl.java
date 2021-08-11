package jp.mikunika.SpringBootInsurance.service.impl;

import jp.mikunika.SpringBootInsurance.model.InsuranceClient;
import jp.mikunika.SpringBootInsurance.model.InsurancePolicy;
import jp.mikunika.SpringBootInsurance.repository.ClientRepository;
import jp.mikunika.SpringBootInsurance.service.InsuranceClientService;
import jp.mikunika.SpringBootInsurance.service.InsurancePolicyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InsuranceClientServiceImpl implements InsuranceClientService {

    private final ClientRepository clientRepository;
    private final InsurancePolicyService policyService;

    @Autowired
    InsuranceClientServiceImpl(ClientRepository clientRepository,
                               InsurancePolicyService policyService) {
        this.clientRepository = clientRepository;
        this.policyService = policyService;
    }

    @Override
    public List<InsuranceClient> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public InsuranceClient getOne(Long id) {
        return clientRepository.getById(id);
    }

    @Override
    public InsuranceClient save(InsuranceClient entity) {
        return clientRepository.save(entity);
    }

    @Override
    public InsuranceClient update(Long id, InsuranceClient entityNew) {
        InsuranceClient client = getOne(id);
        BeanUtils.copyProperties(entityNew, client, "id");
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<InsurancePolicy> getClientPolicyList(Long clientId) {
        InsuranceClient client = getOne(clientId);
        return List.copyOf(client.getInsurancePolicyList());
    }

    @Override
    public InsuranceClient addPolicyToClient(Long clientId, InsurancePolicy policy) {
        InsuranceClient client = getOne(clientId);
        policyService.save(policy);
        policy.setClient(client);
        return save(client);
    }

    @Override
    public InsuranceClient setPolicyToClient(Long clientId, Long policyId) {
        InsuranceClient client = getOne(clientId);
        InsurancePolicy policy = policyService.getOne(policyId);
        policy.setClient(client);
        return save(client);
    }

}
