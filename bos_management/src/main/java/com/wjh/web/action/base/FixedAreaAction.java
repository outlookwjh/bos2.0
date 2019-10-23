package com.wjh.web.action.base;

import com.wjh.domain.base.FixedArea;
import com.wjh.service.base.FixedAreaService;
import com.wjh.web.action.common.CommonAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ParentPackage(value = "json-default")
@Namespace("/")
@Controller
@Scope("prototype")
@Actions
public class FixedAreaAction extends CommonAction<FixedArea>{

    @Autowired
    private FixedAreaService areaService;

    @Action(value = "fixedArea_save",results = {@Result(name = "success",
            type = "redirect",location = "./pages/base/fixed_Area.html")})
    public String insertFixedArea(){

        areaService.insertFixedArea(model);



        return SUCCESS;
    }


}
