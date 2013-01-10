package com.czg.service;

import java.util.List;

import com.czg.bean.AdminUser;
import com.czg.common.DaoCriteria;

public interface AdminUserServicexx {
    int countByExample(DaoCriteria example);

    AdminUser selectByPrimaryKey(Integer id);

    List<AdminUser> selectByExample(DaoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    int deleteByExample(DaoCriteria example);

    int updateByExampleSelective(AdminUser record, DaoCriteria example);

    int updateByExample(AdminUser record, DaoCriteria example);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);
    
    public boolean userShipVerification(AdminUser au);
}