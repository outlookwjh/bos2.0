package com.wjh.web.action.base;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wjh.domain.base.Area;
import com.wjh.service.base.AreaService;
import com.wjh.web.action.common.CommonAction;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ParentPackage("json-default")
@Actions
@Namespace("/")
@Controller
@Scope("prototype")
public class AreaAction extends CommonAction<Area>{

    /*Area area=new Area();
    @Override
    public Area getModel() {
        return area;
    }
*/
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Autowired
    private AreaService areaService;

    @Action(value = "area_upload" ,results = {@Result(name = "success",type = "json")})
    public String upLoadFile() throws IOException {

        List<Area> list=new ArrayList<>();
        HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (Row cells : sheet) {
            if (cells.getRowNum()==0){
                continue;
            }
            if (cells.getCell(0)==null){
                continue;
            }
            Area area = new Area();
            area.setId(cells.getCell(0).getStringCellValue());
            area.setCity(cells.getCell(2).getStringCellValue());
            area.setProvince(cells.getCell(1).getStringCellValue());
            area.setDistrict(cells.getCell(3).getStringCellValue());
            area.setPostcode(cells.getCell(4).getStringCellValue());
            list.add(area);
        }
        areaService.saveFile(list );
        return SUCCESS;
    }

    private Integer rows;
    private Integer page;

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Action(value = "area_pageQuery",results = {@Result(name = "success",type = "json")})
    public String area_pageQuery(){

        Specification<Area> spec=new Specification<Area>() {
            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> lst=new ArrayList<>();
                if (null!=model.getProvince() && !"".equals(model.getProvince())){
                   Predicate p1= cb.like(root.<String>get("province").as(String.class),
                           "%"+model.getProvince()+"%");
                   lst.add(p1);
                }
                if (null!=model.getCity() && !"".equals(model.getCity())){
                    Predicate p2=cb.like(root.<String>get("city").as(String.class),"%"+model.getCity()+"%");
                    lst.add(p2);
                }
                if (null!=model.getDistrict() && !"".equals(model.getDistrict())){
                    Predicate p3=cb.like(root.<String>get("district").as(String.class),"%"+model.getDistrict()+"%");
                    lst.add(p3);
                }
                return cb.and(lst.toArray(new Predicate[0]));
            }
        };

        Pageable pageable=new PageRequest(page-1,rows);
        Page<Area> results=areaService.pageQuery(spec,pageable);

        pushStack(results);

      /*  Map<String,Object> map=new HashMap<>();
        map.put("rows",results.getContent());
        map.put("total",results.getTotalElements());

        ActionContext.getContext().getValueStack().push(map);*/
        return SUCCESS;

    }

}
