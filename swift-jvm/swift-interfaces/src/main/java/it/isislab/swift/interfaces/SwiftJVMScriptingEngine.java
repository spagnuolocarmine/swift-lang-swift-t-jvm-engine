package it.isislab.swift.interfaces;

import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

				engine = new ScriptEngineManager().getEngineByName("groovy");
				break;
			case SwiftJVMScriptingEngineNames.SCALA:

				break;
			case SwiftJVMScriptingEngineNames.JAVASCRIPT:
				engine = new ScriptEngineManager().getEngineByName("javascript");
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
		try {
			switch (engine_name) {
			case SwiftJVMScriptingEngineNames.CLOJURE:

				return (engine.eval(code,engine.getContext())).toString();

			case SwiftJVMScriptingEngineNames.GROOVY:
			case SwiftJVMScriptingEngineNames.JAVASCRIPT:
				StringWriter writer = new StringWriter();

				ScriptContext context = engine.getContext();

				context.setWriter(writer);

				engine.eval(code);

				String output = writer.toString();

				return output;
			case SwiftJVMScriptingEngineNames.SCALA:

				break;

			default:
				return null;
			}
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;

	}

}