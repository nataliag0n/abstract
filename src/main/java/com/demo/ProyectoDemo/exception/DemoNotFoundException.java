package com.demo.ProyectoDemo.exception;

import com.demo.ProyectoDemo.util.Constant;

@SuppressWarnings("serial")
public class DemoNotFoundException extends RuntimeException {
	
	public DemoNotFoundException(Integer id) {
		super(Constant.NOT_FOUND + id);
	}
	
}