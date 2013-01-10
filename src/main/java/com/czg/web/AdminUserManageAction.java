package com.czg.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czg.bean.AdminUser;
import com.czg.common.DaoCriteria;
import com.czg.common.HttpUtility;
import com.czg.service.impl.AdminUserService;

/** 员工管理 */
@Controller
public class AdminUserManageAction
{
	
	private AdminUser entity = new AdminUser();
	private List<AdminUser> userList = new ArrayList<AdminUser>();
	@Autowired
	private AdminUserService adminUserService;
	//@Autowired
	//private Page page;
	
	//private String action = HttpUtility.getQueryString("ac");
	//private String uid = HttpUtility.getQueryString("uid");
	
	public AdminUser getEntity() {
		return entity;
	}

	public List<AdminUser> getUserList() {
		return userList;
	}

	public void setUserList(List<AdminUser> userList) {
		this.userList = userList;
	}

	public void setEntity(AdminUser entity) {
		this.entity = entity;
	}


	public AdminUser getModel()
	{
		return entity;
	}
	
	
//--------访问方法开始--------------------------------------------------------
	
	@RequestMapping("/test")
	public String testDo(HttpServletRequest request,HttpServletResponse response){
		System.out.println("hello!");
		System.out.println(HttpUtility.getQueryString("a"));
		System.out.println(request.getParameter("a"));
		return "test";
	}
	/**后台入口页面*/
	public String login()
	{
		if(HttpUtility.getAdminUser()!=null){
			return "mainUI";
		}
		if(true){
			
			if(adminUserService.userShipVerification(entity))
			{
				return "mainUI";
			}
		}
		return "loginUI";
	}
	
	/** 进入后台主页面*/
	public String main(){
		//if(HttpUtility.getAdminUser(request)!=null){
			
			return "mainUI";
		
	}
	
	public String loginOut(){
		//HttpUtility.getCurrentSession().clear();
		return "loginUI";
	}
	
	public String listUser(){
		userList = adminUserService.selectByExample(new DaoCriteria());
		return "listUI";
	}
	
/*	public String listEmp(){
		HttpUtility.setNameSpaceToSession();
		page = sysEmployeeService.selectEmployeeByPageCondition(page);
		return "listUI";
	}
	
	public String editEmp(){
		
		if(entity.getId()!= null && entity.getId() > 0){
			
				entity = sysEmployeeService.selectByPrimaryKey(entity.getId());
				
				action = "update";
				return "updateUI";
			}
		else{
				action = "insert";
				return "insertUI";
		}
	}
	
	@Validations(  
	        requiredStrings={   //字符串不能为空
	            @RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="entity.realName",message="姓名不能为空！"),
	            @RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="entity.mobilePhone",message="手机不能为空！"),
	            @RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="entity.email",message="EMAIL不能为空！")
	        },
	        emails={@EmailValidator(fieldName="entity.email",message="EMAIL格式不对")},
	        regexFields={@RegexFieldValidator(fieldName="entity.mobilePhone",expression="^(\\+86|0|1)\\d{10,11}$",message="电话号码不正确！")}
			)
	public String updateEmp() throws IOException{
		int i = 0;
		i = sysEmployeeService.updateByPrimaryKeySelective(entity);
		
			HttpUtility.pageRender("操作成功！","",i == 1?"success":"error");
		
		return null;
	}
	

	public String insertEmp() throws IOException{
		
		
		int i = 0;
		i = sysEmployeeService.insertEmployee(entity);
		
		HttpUtility.pageRender("操作成功！","",i == 1?"success":"error");
		
		return null;
	}
	
	public String delEmps(){
		
		if(eid.trim().length() > 0)
			sysEmployeeService.deleteMultiByIds(eid);
			
		return listEmp();
	}*/
	
}
