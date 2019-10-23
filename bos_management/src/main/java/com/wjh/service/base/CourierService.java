package com.wjh.service.base;

import com.wjh.domain.base.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CourierService {
    void courier_save(Courier courier);

    Page<Courier> pageQuery(Specification<Courier> spec, Pageable pageable);

    void updateStatus(String ids);

    void recoveryCourier(String ids);
}
