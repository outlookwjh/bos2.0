package com.wjh.dao.base;

import com.wjh.domain.base.FixedArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FixedAreaRespository extends JpaRepository<FixedArea,Integer> ,JpaSpecificationExecutor<FixedArea> {
}
