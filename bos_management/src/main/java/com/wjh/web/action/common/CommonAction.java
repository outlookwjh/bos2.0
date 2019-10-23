package com.wjh.web.action.common;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wjh.domain.base.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过定义一个泛型的基类，创建构造方法获取一个泛型的实列，供子类使用
 * @param <T>
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T>{

    protected T model;
    @Override
    public T getModel() {
        return model;
    }

    protected int page;

    protected  int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public CommonAction(){

        //构造子类对象获取父类对象的泛型
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType= (ParameterizedType) type;
        Class<T> modelClass =(Class<T>) parameterizedType.getActualTypeArguments()[0];
        try {
             model = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("模型构造失败！");
        }
    }

    protected void pushStack(Page<T> results){

        Map<String,Object> map=new HashMap<>();
        map.put("rows",results.getContent());
        map.put("total",results.getTotalElements());

        ActionContext.getContext().getValueStack().push(map);
    }


}
