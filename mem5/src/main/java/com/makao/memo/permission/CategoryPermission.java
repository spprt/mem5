package com.makao.memo.permission;

import javax.servlet.http.HttpSession;

import com.makao.memo.entity.Category;
import com.makao.memo.exception.PermissionDeniedException;
import com.makao.memo.util.AuthInfo;

/**
 * 카테고리관련 권한체크 모음
 */
public class CategoryPermission {

	/**
	 * 카테고리 작성자인지 여부
	 * @param ctgr
	 * @param session
	 * @return
	 */
	public static boolean isRegUser(Category ctgr,  HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		return ctgr.getUserId() == authInfo.getId();
	}
	
	/**
	 * 카테고리 작성자인지 체크한다.
	 * @param ctgr
	 * @param session
	 */
	public static void checkRegUser(Category ctgr,  HttpSession session) {
		if(!isRegUser(ctgr, session)) {
			throw new PermissionDeniedException();
		}
	}
}
