package com.wjh.web.action.base;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wjh.domain.base.Standard;
import com.wjh.service.base.StandardService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ParentPackage("json-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {

    private Standard standard=new Standard();

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

    @Override
    public Standard getModel() {
        return standard;
    }
    @Autowired
    private StandardService standardService;

    @Action(value = "standard_save",results = {@Result(name = "success",type = "redirect",
            location = "./pages/base/standard.html")})
    public String save(){
        System.out.println("添加收派标准。。。");

        standardService.save(standard);
        return SUCCESS;
    }

    @Action(value = "standard_pageQuery", results={@Result(name = "success",type = "json")})
    public String pageQuery(){
        Pageable pageable=new PageRequest(page-1,rows);
        Page<Standard> standards = standardService.pageQuery(pageable);
        Map<String,Object> map=new HashMap<>();
        map.put("total",standards.getTotalElements());
        map.put("rows",standards.getContent());

        ActionContext.getContext().getValueStack().push(map);

        return SUCCESS;
    }


    @Action(value = "standard_findAll",results = {@Result(name = "success",type = "json")})
    public String standard_findAll(){

        List<Standard> all=standardService.findAll();

        ActionContext.getContext().getValueStack().push(all);
        return SUCCESS;
    }

}
