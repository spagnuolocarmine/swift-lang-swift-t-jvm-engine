package it.isislab.swift.scala;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import scala.collection.immutable.Nil;
import scala.tools.nsc.Settings;
import scala.tools.nsc.interpreter.IMain;

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
	public void testHello()
	{
		try {

			ScalaScriptEngine engine=new ScalaScriptEngine();

			String code = ""
					+"object HelloWorld {\n"
					+"def main(args: Array[String]) = println(\"Hello Scala!\")\n"
					+"}\n"
					+"";
			System.out.println("Result? " + (engine.eval(code)).getClass().getName());
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
		assertTrue( true );
	}
	/**
	 * Rigourous Test :-)
	 */
	public void testHello2()
	{
		try {

			Settings settings = new Settings();
			settings.usejavacp().tryToSetFromPropertyValue("true");
			settings.stopAfter().tryToSetColon(Nil.$colon$colon("dce")); // same as "dce" :: Nil in scala
			settings.debug().tryToSetFromPropertyValue("true");

			IMain main = new IMain(settings);


			String code = ""
					+"object HelloWorld {\n"
					+"def main(args: Array[String]) = println(\"Hello Scala!\")\n"
					+"}\n"
					+"";
			System.out.println("Result? " + (main.eval(code)).getClass().getName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
		assertTrue( true );
	}
}
