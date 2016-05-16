package it.isislab.swift.interfaces;

import it.isislab.swift.interfaces.SwiftJVMScriptingEngine;
import it.isislab.swift.interfaces.SwiftJVMScriptingEngineNames;
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
     */
    public void testClojure()
    {
    	
    	SwiftJVMScriptingEngine.setEngine(SwiftJVMScriptingEngineNames.CLOJURE);
    	System.out.println(SwiftJVMScriptingEngine.eval("(def hello (fn []\"Hello world\"))"));
        //TODO add assert condition
    	assertTrue( true );
    }
}
