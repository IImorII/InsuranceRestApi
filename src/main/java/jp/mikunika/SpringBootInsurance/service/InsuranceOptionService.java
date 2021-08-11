package jp.mikunika.SpringBootInsurance.service;

import jp.mikunika.SpringBootInsurance.model.InsuranceObject;
import jp.mikunika.SpringBootInsurance.model.InsuranceOption;

import java.util.List;

public interface InsuranceOptionService extends BaseService<InsuranceOption> {

    /** GET - retrieve related objects */
    List<InsuranceObject> getOptionObjects(Long optionId);

    /** POST without id - create a new object and connect it with current object */
    InsuranceOption addObjectToOption(Long optionId, InsuranceObject object);

    /** POST with id - create relationship between the two existing objects */
    InsuranceOption setObjectToOption(Long optionId, Long objectId);
}
