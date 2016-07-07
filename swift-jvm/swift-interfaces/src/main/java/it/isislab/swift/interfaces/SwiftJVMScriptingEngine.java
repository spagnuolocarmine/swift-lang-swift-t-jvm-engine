package it.isislab.swift.interfaces;

import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import it.isislab.swift.scala.ScalaScriptEngine;
import it.isislab.swiftlang.swfit_clojure.ClojureScriptEngine;

public class SwiftJVMScriptingEngine {

	public static ScriptEngine engine;
	public static String engine_name;

	public static void setEngine(String engine_name_given)
	{
	
		engine_name=engine_name_given;
		try {

			switch (engine_name) {
			case SwiftJVMScriptingEngineNames.CLOJURE:
				engine = new ClojureScriptEngine();
				break;
			case SwiftJVMScriptingEngineNames.GROOVY:
				engine = new ScriptEngineManager().getEngineByName(SwiftJVMScriptingEngineNames.GROOVY);
				
				break;
			case SwiftJVMScriptingEngineNames.SCALA:
				engine = new ScalaScriptEngine();
				break;
			case SwiftJVMScriptingEngineNames.JAVASCRIPT:
				engine = new ScriptEngineManager().getEngineByName(SwiftJVMScriptingEngineNames.JAVASCRIPT);
				break;
			default:
				break;
			}
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String eval(String code)
	{
		Object output=null;
		try {
			switch (engine_name) {
			case SwiftJVMScriptingEngineNames.CLOJURE:
			    output=(engine.eval(code,engine.getContext())).toString();
				return output!=null?output.toString():"";

			case SwiftJVMScriptingEngineNames.GROOVY:
			case SwiftJVMScriptingEngineNames.JAVASCRIPT:
			
				StringWriter writer = new StringWriter();
				ScriptContext context = engine.getContext();
				context.setWriter(writer);
				engine.eval(code);
				output = writer.toString();

				return output!=null?(String)output:"";
			case SwiftJVMScriptingEngineNames.SCALA:
				output=engine.eval(code);
				return output!=null?output.toString():"";

			default:
				return null;
			}
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

	}
	public static String eval(String engine_name_given,String code)
	{
		Object output=null;
		StringWriter writer;
		ScriptContext context;
		engine_name=engine_name_given;
		try {

			switch (engine_name) {
			case SwiftJVMScriptingEngineNames.CLOJURE:
				engine = new ClojureScriptEngine();
				output=(engine.eval(code,engine.getContext())).toString();
				return output!=null?output.toString():"";
			case SwiftJVMScriptingEngineNames.GROOVY:
				engine = new ScriptEngineManager().getEngineByName(SwiftJVMScriptingEngineNames.GROOVY);
				 writer = new StringWriter();
				 context = engine.getContext();
				context.setWriter(writer);
				engine.eval(code);
				output = writer.toString();

				return output!=null?(String)output:"";
			case SwiftJVMScriptingEngineNames.SCALA:
				engine = new ScalaScriptEngine();
				output=engine.eval(code);
				return output!=null?output.toString():"";
			case SwiftJVMScriptingEngineNames.JAVASCRIPT:
				engine = new ScriptEngineManager().getEngineByName(SwiftJVMScriptingEngineNames.JAVASCRIPT);
				 writer = new StringWriter();
				 context = engine.getContext();
				context.setWriter(writer);
				engine.eval(code);
				output = writer.toString();

				return output!=null?(String)output:"";
			default:
				return null;
			}
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
