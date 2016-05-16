package it.isislab.swift.scala;

import java.io.StringWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import scala.tools.nsc.interpreter.IMain;
import scala.tools.nsc.settings.MutableSettings.BooleanSetting;

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
    public void testApp()
    {
    	try {
    		
    		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("scala");
    		
    		((BooleanSetting)(((IMain)scriptEngine)
    	               .settings().usejavacp()))
    	                    .value_$eq(true);
    	 
//    		final StringWriter writer = new StringWriter();
//
//    		scriptEngine.put("writer", writer);
//
//    		final String script = "writer.write(\"3\");";
//    		scriptEngine.eval(script);
//    		
//    		assertEquals("3", writer.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
        assertTrue( true );
    }
}
