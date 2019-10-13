package com.wjh.service.base;

import com.wjh.domain.base.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StandardService {

    void save(Standard standard);

    List<Standard> findAll();


    Page<Standard> pageQuery(Pageable pageable);
}
