package it.isislab.swift.interfaces;

import javax.script.ScriptException;

public interface  SwiftJVMScriptingInterface {

	 public void init() throws ScriptException;

	 public Object eval(String code) throws ScriptException;
	


}