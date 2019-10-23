package com.wjh.service.base.impl;

import com.wjh.dao.base.FixedAreaRespository;
import com.wjh.domain.base.FixedArea;
import com.wjh.service.base.FixedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService{

    @Autowired
    private FixedAreaRespository respository;
    @Override
    public void insertFixedArea(FixedArea model) {
        respository.save(model);
    }
}
