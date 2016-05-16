package it.isislab.swift.interfaces.clojure;

import it.isislab.swift.interfaces.SwiftJVMScriptingEngine;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwiftJVMScriptingEngine.setEngine("clojure");
    	System.out.println(SwiftJVMScriptingEngine.eval("(def hello (fn []\"Hello world\"))"));
	}

}
