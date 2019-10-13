package com.wjh.service.base.impl;

import com.wjh.dao.base.StandardRepository;
import com.wjh.domain.base.Standard;
import com.wjh.service.base.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardRepository standardRepository;

    @Override
    public void save(Standard standard) {

        standardRepository.save(standard);

    }

    @Override
    public List<Standard> findAll() {
       return standardRepository.findAll();
    }

    @Override
    public Page<Standard> pageQuery(Pageable pageable) {

        Page<Standard> page = standardRepository.findAll(pageable);
        return  page;
    }
}
