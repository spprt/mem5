package com.makao.memo.exception;

/**
 * 메모가 lock걸린상태에서 수정자가 아닌 다른사람이 수정하려고 할 때 발생한다.
 */
public class MemoLockException extends RuntimeException {
	private static final long serialVersionUID = 4865117888911336501L;
}
