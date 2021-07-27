package com.tooner.web.exception;

public class DuplicatedUserNameException extends RuntimeException {
    public DuplicatedUserNameException(String userName) {
        super(userName + " 이메일은 이미 가입되어 있습니다.");
    }
}
