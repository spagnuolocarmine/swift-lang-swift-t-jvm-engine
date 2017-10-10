package it.isislab.swiftlang.java_jshell;

import java.io.Reader;
import java.util.List;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;


 
import jdk.jshell.*;

public class JShellScriptEngine extends AbstractScriptEngine
{
	private JShell js;
	public JShellScriptEngine()
	{
	   js = JShell.create();
	}
	
	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		// TODO Auto-generated method stub
		
		List<SnippetEvent> snippetsList=js.eval(script);
		return js.eval(script);
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
