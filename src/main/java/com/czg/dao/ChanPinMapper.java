package com.czg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.czg.bean.ChanPin;
import com.czg.common.DaoCriteria;

public interface ChanPinMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(DaoCriteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(DaoCriteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ChanPin record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ChanPin record);

    /**
     * 根据条件查询记录集
     */
    List<ChanPin> selectByExample(DaoCriteria example);

    /**
     * 根据主键查询记录
     */
    ChanPin selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") ChanPin record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") ChanPin record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ChanPin record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ChanPin record);
}