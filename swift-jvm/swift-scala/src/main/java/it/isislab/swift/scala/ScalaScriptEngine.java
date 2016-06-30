package it.isislab.swift.scala;

import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

import scala.collection.immutable.Nil;
import scala.tools.nsc.Settings;
import scala.tools.nsc.interpreter.IMain;


public class ScalaScriptEngine extends AbstractScriptEngine
{
	private IMain main;
	public ScalaScriptEngine()
	{
		Settings settings = new Settings();
		settings.usejavacp().tryToSetFromPropertyValue("true");
		settings.stopAfter().tryToSetColon(Nil.$colon$colon("dce")); // same as "dce" :: Nil in scala
		settings.debug().tryToSetFromPropertyValue("true");

		 main = new IMain(settings);
	}
	


	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		// TODO Auto-generated method stub
		return main.eval(script);
	}

	@Override
	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bindings createBindings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptEngineFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
