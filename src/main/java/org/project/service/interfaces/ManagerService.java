package org.project.service.interfaces;

import org.project.entity.BaseClass;
import org.project.entity.Expert;


import java.util.List;

public interface ManagerService {


    void ChangePriceService(Double startPrice,Double endPrice);
    void ChangePriceSpecial(Double startPrice,Double endPrice);
    List<BaseClass> showAllService();
    void insertService(String name);
    void insertSpecial(String name);
    Boolean isServiceExists();
    Boolean isSpecialExists();
    List<Expert> search(Expert specialist);

}
