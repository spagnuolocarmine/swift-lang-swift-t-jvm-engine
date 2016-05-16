package it.isislab.swiftlang.swfit_clojure;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class Tests 
extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public Tests( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( Tests.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testHelloWorld()
	{
		try {


			ScriptEngine scriptEngine = new ClojureScriptEngine();

			Object result=scriptEngine.eval("(def hello (fn []\"Hello world\"))",scriptEngine.getContext());


			System.out.println("Script output: " + result);

			//assertEquals("Hello World", result.toString());
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		} 
		assertTrue( true );
	}
}
