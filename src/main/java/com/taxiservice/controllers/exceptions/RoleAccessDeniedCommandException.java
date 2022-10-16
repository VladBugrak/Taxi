package com.taxiservice.controllers.exceptions;

public class RoleAccessDeniedCommandException extends RuntimeException{

    public RoleAccessDeniedCommandException(){
        super();
    }
    public RoleAccessDeniedCommandException(String command){
        super(command);
    }
}
