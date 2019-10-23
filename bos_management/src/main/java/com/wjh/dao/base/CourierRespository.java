package com.wjh.dao.base;

import com.wjh.domain.base.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourierRespository extends JpaRepository<Courier,Integer> ,
        JpaSpecificationExecutor<Courier> {


    @Query(value = "update Courier set deltag='1' where id = ? ")
    @Modifying
    public void updateStatus( Integer id);


    @Query(value = "update Courier set deltag=null where id=?")
    @Modifying
    void recoveryCourier(int i);
}
