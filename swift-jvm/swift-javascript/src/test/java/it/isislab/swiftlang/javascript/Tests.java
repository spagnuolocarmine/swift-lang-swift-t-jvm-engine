package it.isislab.swiftlang.javascript;

import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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
    public void testApp()
    {
    	try {
    		StringWriter writer = new StringWriter();
    		
    		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
    		ScriptContext context = scriptEngine.getContext();
    		context.setWriter(writer);
    		
    		scriptEngine.eval("print(\"Hello World\");");
    		
    		String output = writer.toString();

    		System.out.println("Script output: " + output);
    		
    		//assertEquals("Hello World\n", output);
			
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
        assertTrue( true );
    }
}
