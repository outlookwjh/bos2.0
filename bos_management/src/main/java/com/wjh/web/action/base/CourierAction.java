package com.wjh.web.action.base;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wjh.domain.base.Courier;
import com.wjh.service.base.CourierService;
import javassist.runtime.Inner;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring data 分页条件查询的使用
 */
@ParentPackage("json-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {

    Courier courier = new Courier();

    @Override
    public Courier getModel() {
        return courier;
    }

    private int page;
    private int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Autowired
    private CourierService courierService;

    @Action(value = "courier_save", results = {@Result(name = "success", type = "redirect"
            , location = "./pages/base/courier.html"),})
    public String courier_save() {

        courierService.courier_save(courier);
        return SUCCESS;
    }

    Specification<Courier> spec = new Specification<Courier>() {
        @Override
        public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<>(10);
            if (null != courier.getCompany() && !"".equals(courier.getCompany())) {

                Predicate p1 = cb.like(root.get("company").as(String.class), "%" +
                        courier.getCompany() + "%");
                list.add(p1);
            }
            if (null != courier.getCourierNum() && !"".equals(courier.getCourierNum())) {
                Predicate p2 = cb.equal(root.get("courierNum"), courier.getCourierNum());
                list.add(p2);
            }
            if (null != courier.getType() && !"".equals(courier.getType())) {
                Predicate p3 = cb.equal(root.get("type").as(String.class), courier.getType());
                list.add(p3);
            }
            //多表
            if (null != courier.getStandard() && !"".equals(courier.getStandard().getName()) ) {
                Join<Object, Object> standardRoot = root.join("standard", JoinType.INNER);
                Predicate p4 = cb.like(standardRoot.<String>get("name"), "%" + courier.getStandard().getName() + "%");
                list.add(p4);
            }


            return cb.and(list.toArray(new Predicate[0]));
        }
    };

    @Action(value = "courier_pageQuery", results = {@Result(name = "success", type = "json")})
    public String PageQuery() {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Courier> couriers = courierService.pageQuery(spec, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", couriers.getContent());
        map.put("total", couriers.getTotalElements());

        ActionContext.getContext().getValueStack().push(map);
        return SUCCESS;
    }


    @Action(value = "courier_delBatch",results = {@Result(name = "success",type = "redirect",
            location = "./pages/base/courier.html")})
    public String updateCourier(){
        courierService.updateStatus(ids);
        return  SUCCESS;
    }

    @Action(value = "courier_recoveryBatch",results = {@Result(name = "success",
            type = "redirect",location = "./pages/base/courier.html")})
    public String recoveryCourier(){
        courierService.recoveryCourier(ids);
        return SUCCESS;
    }
}
