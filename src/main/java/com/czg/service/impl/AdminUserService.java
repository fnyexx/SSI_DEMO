package com.czg.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czg.bean.AdminUser;
import com.czg.common.CrptsUtil;
import com.czg.common.DaoCriteria;
import com.czg.common.HttpUtility;
import com.czg.dao.AdminUserMapper;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    private static final Logger logger = Logger.getLogger(AdminUserService.class);
    
    private DaoCriteria daoCriteria = new DaoCriteria();
    
    private AdminUser entity = new AdminUser();
    
    public int countByExample(DaoCriteria example) {
        int count = this.adminUserMapper.countByExample(example);
        logger.debug("count: {}");
        return count;
    }

    public AdminUser selectByPrimaryKey(Integer id) {
        return this.adminUserMapper.selectByPrimaryKey(id);
    }

    public List<AdminUser> selectByExample(DaoCriteria example) {
        return this.adminUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.adminUserMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(AdminUser record) {
        return this.adminUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AdminUser record) {
        return this.adminUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(DaoCriteria example) {
        return this.adminUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(AdminUser record, DaoCriteria example) {
        return this.adminUserMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(AdminUser record, DaoCriteria example) {
        return this.adminUserMapper.updateByExample(record, example.getCondition());
    }

    public int insert(AdminUser record) {
        return this.adminUserMapper.insert(record);
    }

    public int insertSelective(AdminUser record) {
        return this.adminUserMapper.insertSelective(record);
    }
    
    public boolean userShipVerification(AdminUser au)
	{	
		daoCriteria.clear();
		daoCriteria.put("userName", au.getUserName());
		daoCriteria.put("status", 1);
		boolean compare = false;
		List<AdminUser> EMP_LIST = adminUserMapper.selectByExample(daoCriteria);
			
			if(EMP_LIST.size() == 1)
				entity = EMP_LIST.get(0);
			
			compare = CrptsUtil.md5Compare(au.getPassword(), entity.getSalt(), entity.getPassword());
			
			if(compare){
				logger.info("用户登陆检验成功！");
				HttpUtility.getCurrentSession().setAttribute("adminUser", entity);
			}
			else
			{
				logger.info("用户不存在或此用户异常！");
			}
			
			return compare;
		
	}
}