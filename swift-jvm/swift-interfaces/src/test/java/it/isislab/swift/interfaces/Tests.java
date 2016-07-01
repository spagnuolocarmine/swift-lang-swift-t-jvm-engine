package it.isislab.swift.interfaces;

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
		String script_hello_clojure=""
			+"	(ns clojure.examples.hello                          \n"
			+"			   (:gen-class))                            \n"
            +"                                                      \n"
			+"			(defn hello-world [username]                \n"
			+"			   (str (format \"Hello, %s\" username))) \n"
            +"                                                      \n"
			+"			(hello-world \"Red\")                       \n"
			+"";
    	System.out.println(SwiftJVMScriptingEngine.eval(script_hello_clojure));
        
    }
    /**
     */
    public void testGroovy()
    {
    	
    	SwiftJVMScriptingEngine.setEngine(SwiftJVMScriptingEngineNames.GROOVY);
    	
    	String script_fac_groovy= ""
				+"import java.security.MessageDigest "
				+ " \n"
				+ "def hash(text){"
				+ " MessageDigest"
				+ ".getInstance(\"SHA-512\")"
						+ ".digest(text.getBytes(\"UTF-8\"))"
							+".encodeBase64().toString()"
							+"} \n"
							+ "\n println hash(UUID.randomUUID().toString())"
				+" ";
    	System.out.println(SwiftJVMScriptingEngine.eval(script_fac_groovy));
    	
    
    	
        
    }
    //TODO ADD RETURN VALUE STRING
    public void testScala()
    {
    	
    	SwiftJVMScriptingEngine.setEngine(SwiftJVMScriptingEngineNames.SCALA);
    	
    	String script_scala = ""
				+"object HelloWorld {\n"
				+"def main(args: Array[String]) = println(\"Hello Scala!\")\n"
				+"}\n"
				+"";
    	System.out.println(SwiftJVMScriptingEngine.eval(script_scala));
    	
        
    }
    //TODO ADD RETURN VALUE STRING
    public void testJavaScript()
    {
    	
    	SwiftJVMScriptingEngine.setEngine(SwiftJVMScriptingEngineNames.JAVASCRIPT);
    	
    	String script_js = ""
    		+"	function test() {                                                                                                  \n"            
    		+"  var x = 2, y = 4;                                                                                                  \n"
    		+"  return eval(\"x + y\");  // Direct call, uses local scope, result is 6                                         \n"
    		+"}    test();  ";
    	
    	System.out.println(SwiftJVMScriptingEngine.eval(script_js));
    	
        
    }
}
