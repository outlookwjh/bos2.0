package com.wjh.service.base;

import com.wjh.domain.base.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AreaService {
    void saveFile(List<Area> list);

    Page<Area> pageQuery(Specification<Area> spec, Pageable pageable);
}
