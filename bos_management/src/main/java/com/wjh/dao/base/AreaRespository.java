package com.wjh.dao.base;

import com.wjh.domain.base.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AreaRespository extends JpaRepository<Area,String> ,JpaSpecificationExecutor<Area> {
}
