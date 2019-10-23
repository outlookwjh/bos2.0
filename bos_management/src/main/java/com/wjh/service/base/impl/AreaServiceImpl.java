package com.wjh.service.base.impl;

import com.wjh.dao.base.AreaRespository;
import com.wjh.domain.base.Area;
import com.wjh.service.base.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRespository respository;
    @Override
    public void saveFile(List<Area> list) {
        respository.save(list);
    }

    @Override
    public Page<Area> pageQuery(Specification<Area> spec, Pageable pageable) {
        return respository.findAll(spec,pageable);
    }
}
