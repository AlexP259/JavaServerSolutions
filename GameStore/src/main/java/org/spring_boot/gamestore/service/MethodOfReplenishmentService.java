package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.MethodOfReplenishment;
import org.spring_boot.gamestore.repository.MethodOfReplenishmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodOfReplenishmentService implements IMethodOfReplenishmentService {

    @Autowired
    MethodOfReplenishmentRepo methodOfReplenishmentRepo;

    @Override
    public boolean deleteMethodOfReplenishment(int id) {

        MethodOfReplenishment methodOfReplenishment = methodOfReplenishmentRepo.findById(id).orElse(null);

        if(methodOfReplenishment != null){
            methodOfReplenishmentRepo.delete(methodOfReplenishment);
            return true;
        }

        return false;
    }
}
