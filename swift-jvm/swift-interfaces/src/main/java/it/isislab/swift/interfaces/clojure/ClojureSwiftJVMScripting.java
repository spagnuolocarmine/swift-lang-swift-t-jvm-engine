package it.isislab.swift.interfaces.clojure;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import it.isislab.swift.interfaces.SwiftJVMScriptingInterface;
import it.isislab.swiftlang.swfit_clojure.ClojureScriptEngine;

public class ClojureSwiftJVMScripting implements SwiftJVMScriptingInterface{
	
	
	ScriptEngine scriptEngine;
	@Override
	public void init() throws ScriptException {
		 scriptEngine = new ClojureScriptEngine();
	}

	@Override
	public Object eval(String code) throws ScriptException {
		
		return scriptEngine.eval("(def hello (fn []\"Hello world\"))",scriptEngine.getContext());
	}


}
