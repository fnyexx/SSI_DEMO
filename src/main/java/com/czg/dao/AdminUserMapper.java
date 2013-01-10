package com.czg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.czg.bean.AdminUser;
import com.czg.common.DaoCriteria;

public interface AdminUserMapper {
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
    int insert(AdminUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(AdminUser record);

    /**
     * 根据条件查询记录集
     */
    List<AdminUser> selectByExample(DaoCriteria example);

    /**
     * 根据主键查询记录
     */
    AdminUser selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") AdminUser record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") AdminUser record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(AdminUser record);
}