package com.lj.gkzy.service.converter;

import com.lj.gkzy.domain.dto.ObtainingScoreDataDO;
import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import org.springframework.stereotype.Service;

/**
 * @author liujing
 * @version $Id: ObtainingScoreDataModelConverter.java, v 0.1 2021-04-03 14:05:07 liujing Exp $$
 */
@Service
public class ObtainingScoreDataModelConverter {

    /**
     * Convert ObtainingScoreDataDO to ObtainingScoreDataModel
     *
     * @param obtainingScoreDataDO
     * @return
     */
    public static ObtainingScoreDataModel convertToObtainingScoreDataModel(ObtainingScoreDataDO obtainingScoreDataDO) {
        if (obtainingScoreDataDO == null) {
            return null;
        }
        ObtainingScoreDataModel obtainingScoreDataModel = new ObtainingScoreDataModel();

        obtainingScoreDataModel.setId(obtainingScoreDataDO.getId());
        obtainingScoreDataModel.setYear(obtainingScoreDataDO.getYear());
        obtainingScoreDataModel.setProvinces(obtainingScoreDataDO.getProvinces());
        obtainingScoreDataModel.setAdmissionCategory(obtainingScoreDataDO.getAdmissionCategory());
        obtainingScoreDataModel.setDivisionOfClass(obtainingScoreDataDO.getDivisionOfClass());
        obtainingScoreDataModel.setBatch(obtainingScoreDataDO.getBatch());
        obtainingScoreDataModel.setTheNameOfTheProfessional(obtainingScoreDataDO.getTheNameOfTheProfessional());
        obtainingScoreDataModel.setProfessionalDescription(obtainingScoreDataDO.getProfessionalDescription());
        obtainingScoreDataModel.setEnrollment(obtainingScoreDataDO.getEnrollment());
        obtainingScoreDataModel.setHighestScore(obtainingScoreDataDO.getHighestScore());
        obtainingScoreDataModel.setLowestScore(obtainingScoreDataDO.getLowestScore());
        obtainingScoreDataModel.setAverageScore(obtainingScoreDataDO.getAverageScore());
        obtainingScoreDataModel.setControlScore(obtainingScoreDataDO.getControlScore());
        obtainingScoreDataModel.setTheMinimumGap(obtainingScoreDataDO.getTheMinimumGap());
        obtainingScoreDataModel.setDifferenceOfAverage(obtainingScoreDataDO.getDifferenceOfAverage());
        obtainingScoreDataModel.setCreated(obtainingScoreDataDO.getCreated());
        obtainingScoreDataModel.setUpdated(obtainingScoreDataDO.getUpdated());

        return obtainingScoreDataModel;
    }

    /**
     * Convert ObtainingScoreDataModel to ObtainingScoreDataDO
     *
     * @param obtainingScoreDataModel
     * @return
     */
    public static ObtainingScoreDataDO convertToObtainingScoreDataDO(ObtainingScoreDataModel obtainingScoreDataModel) {
        if (obtainingScoreDataModel == null) {
            return null;
        }
        ObtainingScoreDataDO obtainingScoreDataDO = new ObtainingScoreDataDO();

        obtainingScoreDataDO.setId(obtainingScoreDataModel.getId());
        obtainingScoreDataDO.setYear(obtainingScoreDataModel.getYear());
        obtainingScoreDataDO.setProvinces(obtainingScoreDataModel.getProvinces());
        obtainingScoreDataDO.setAdmissionCategory(obtainingScoreDataModel.getAdmissionCategory());
        obtainingScoreDataDO.setDivisionOfClass(obtainingScoreDataModel.getDivisionOfClass());
        obtainingScoreDataDO.setBatch(obtainingScoreDataModel.getBatch());
        obtainingScoreDataDO.setTheNameOfTheProfessional(obtainingScoreDataModel.getTheNameOfTheProfessional());
        obtainingScoreDataDO.setProfessionalDescription(obtainingScoreDataModel.getProfessionalDescription());
        obtainingScoreDataDO.setEnrollment(obtainingScoreDataModel.getEnrollment());
        obtainingScoreDataDO.setHighestScore(obtainingScoreDataModel.getHighestScore());
        obtainingScoreDataDO.setLowestScore(obtainingScoreDataModel.getLowestScore());
        obtainingScoreDataDO.setAverageScore(obtainingScoreDataModel.getAverageScore());
        obtainingScoreDataDO.setControlScore(obtainingScoreDataModel.getControlScore());
        obtainingScoreDataDO.setTheMinimumGap(obtainingScoreDataModel.getTheMinimumGap());
        obtainingScoreDataDO.setDifferenceOfAverage(obtainingScoreDataModel.getDifferenceOfAverage());
        obtainingScoreDataDO.setCreated(obtainingScoreDataModel.getCreated());
        obtainingScoreDataDO.setUpdated(obtainingScoreDataModel.getUpdated());

        return obtainingScoreDataDO;
    }
}
