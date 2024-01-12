package com.demo.ProyectoDemo.exception;

import com.demo.ProyectoDemo.util.Constant;

@SuppressWarnings("serial")
public class DemoUnSupportedFieldPatchException extends RuntimeException{

	public DemoUnSupportedFieldPatchException(String key) {
		super(Constant.NOT_ALLOWED.replace(Constant.ID, key));
	}
	
}