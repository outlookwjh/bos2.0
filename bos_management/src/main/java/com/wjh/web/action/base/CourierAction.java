package com.wjh.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wjh.domain.base.Courier;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ParentPackage("json-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {

    Courier courier=new Courier();

    @Override
    public Courier getModel() {
        return courier;
    }

    @Action(value = "courier_save",results = {@Result(name = "success",type = "redirect"),})
    public String courier_save(){


        return SUCCESS;
    }
}
