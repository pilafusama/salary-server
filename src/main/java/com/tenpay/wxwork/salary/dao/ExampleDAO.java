package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/21
 */
@Mapper
public interface ExampleDAO  {
    @Select("select * from test")
    public List<Example> getTestData(Example example);
}
