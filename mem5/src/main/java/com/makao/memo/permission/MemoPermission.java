package com.makao.memo.permission;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import com.makao.memo.entity.Memo;
import com.makao.memo.entity.MemoShare;
import com.makao.memo.exception.MemoLockException;
import com.makao.memo.exception.PermissionDeniedException;
import com.makao.memo.util.AuthInfo;

/**
 * 메모관련 권한체크 모음
 */
public class MemoPermission {

	/**
	 * 메모 공유자에 포함되어있는지 여부
	 * 
	 * @param memo
	 * @param session
	 * @return
	 */
	public static boolean isShared(Memo memo, HttpSession session) {
		boolean isShared = false;
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		Long authId = authInfo.getId();
		Long memoId = memo.getId();
		List<MemoShare> shares = memo.getShares().stream()
				.filter(s -> s.getUserId() == authId && s.getMemo().getId() == memoId).collect(Collectors.toList());
		if (shares.size() > 0) {
			isShared = true;
		}

		return isShared;
	}

	/**
	 * 메모 공유자인지 체크한다
	 * 
	 * @param memo
	 * @param session
	 */
	public static void checkShared(Memo memo, HttpSession session) {
		boolean isShared = isShared(memo, session);
		if (!isShared) {
			throw new PermissionDeniedException();
		}
	}

	/**
	 * 메모수정이 가능한지 체크한다.
	 * 
	 * @param memo
	 * @param session
	 */
	public static void checkEdit(Memo memo, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		boolean isShared = isShared(memo, session);
		if (memo.isLock()) {
			if (memo.getModUserId() != authInfo.getId()) {
				throw new MemoLockException();
			}
		} else if (!isShared) {
			throw new PermissionDeniedException();
		}
	}

	/**
	 * 작성자인지 여부
	 * @param memo
	 * @param session
	 * @return
	 */
	public static boolean isRegUser(Memo memo, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		return memo.getRegUserId() == authInfo.getId();
	}

	/**
	 * 작성자인지 체크한다.
	 * 
	 * @param memo
	 * @param session
	 */
	public static void checkRegUser(Memo memo, HttpSession session) {
		if (!isRegUser(memo, session)) {
			throw new PermissionDeniedException();
		}
	}
}
