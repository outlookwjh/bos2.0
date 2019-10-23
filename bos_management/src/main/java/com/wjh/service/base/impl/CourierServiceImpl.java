package com.wjh.service.base.impl;

import com.wjh.dao.base.CourierRespository;
import com.wjh.domain.base.Courier;
import com.wjh.service.base.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierRespository respository;
    @Override
    public void courier_save(Courier courier) {
        respository.save(courier);
    }

    @Override
    public Page<Courier> pageQuery(Specification<Courier> spec, Pageable pageable) {
        return respository.findAll(spec,pageable);
    }

    @Override
    @Transactional
    public void updateStatus(String ids) {
        String[] idsarray = ids.split(",");

        for (String id : idsarray) {

            respository.updateStatus(Integer.valueOf(id));
        }


    }

    @Override
    @Transactional
    public void recoveryCourier(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {

            respository.recoveryCourier(Integer.parseInt(s));
        }

    }


}
