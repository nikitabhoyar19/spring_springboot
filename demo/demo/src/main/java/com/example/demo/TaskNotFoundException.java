package com.example.demo;

public class TaskNotFoundException extends Exception {
	public String errmsg;
	
     public TaskNotFoundException(String msg) {
    	 this.errmsg = msg;
     }
}
