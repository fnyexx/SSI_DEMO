import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.czg.bean.AdminUser;
import com.czg.bean.ChanPin;
import com.czg.common.CrptsUtil;
import com.czg.common.DaoCriteria;
import com.czg.common.VerifyCodeUtil;
import com.czg.dao.ChanPinMapper;
import com.czg.service.AdminUserServicexx;


public class test1 {
	String[] spring = { "spring/applicationContext.xml"};
	BeanFactory factory = new ClassPathXmlApplicationContext(spring);
	private Object getDaoOrService(String clazz)
	{
		return factory.getBean(clazz);
	}

	@Test
	public void addEmeber()
	{
		ChanPinMapper cpm = (ChanPinMapper)getDaoOrService("chanPinMapper");
		//System.out.println(cpm.countByExample(new DaoCriteria()));
		ChanPin cp = new ChanPin();
		cp.setCid(1);
		cp.setCname("asdf");
		cp.setCnum(1);
		cp.setDateLine(new Date());
		cp.setExtendImg("");
		cp.setHot(1);
		cp.setMaterials("");
		cp.setMaxImg("");
		cp.setMinImg("");
		cp.setMoney((short) 0);
		cp.setNote("");
		cp.setSeasoning("");
		cp.setStatus(1);
		cpm.insert(cp);
	}
	
	@Test
	public void addAdminUser()
	{
		AdminUserServicexx cpm = (AdminUserServicexx)getDaoOrService("adminUserService");
		//System.out.println(cpm.countByExample(new DaoCriteria()));
		AdminUser au = new AdminUser();
		String salt = VerifyCodeUtil.CreateAuthStr(6);
			au.setPassword(CrptsUtil.md5Encrypt("as01141012",salt));
			au.setRealName("xm");
			au.setSalt(salt);
			au.setStatus(1);
			au.setUserName("fnyexx");
			cpm.insert(au);
	}
}
