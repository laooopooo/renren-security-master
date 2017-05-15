package io.renren.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 多租户工具类
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017年5月15日
 */
public class TenantUtils {
	public static final String TENANTID = "tenantId";

	public static void setTenantId(int tenantId, HttpSession session) {
		session.setAttribute(TENANTID, tenantId);
	}
	
	public static void setTenantId(int tenantId) {
		getSession().setAttribute(TENANTID, tenantId);
	}

	public static int getTenantId(HttpSession session) {
		return (int) session.getAttribute(TENANTID);
	}
	
	public static int getTenantId() {
		return (int) getSession().getAttribute(TENANTID);
	}
	
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}

}
