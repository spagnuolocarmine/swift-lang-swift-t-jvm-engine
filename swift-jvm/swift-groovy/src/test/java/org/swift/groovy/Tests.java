package org.swift.groovy;

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
public class Tests extends TestCase
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
    		
    		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
    		ScriptContext context = scriptEngine.getContext();
    		context.setWriter(writer);
    		
    		scriptEngine.eval("print \"Hello World\"");
    		
    		String output = writer.toString();

    		System.out.println("Script output: " + output);
    		
    		assertEquals("Hello World", output);
			
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
        assertTrue( true );
    }
    
    
    public void testHashing()
    {
    	
    	try {
    		StringWriter writer = new StringWriter();
    		
    		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
    		ScriptContext context = scriptEngine.getContext();
    		context.setWriter(writer);
    		
    		String script_fac= ""
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
    		scriptEngine.eval(script_fac);
    		
    		String output = writer.toString();

    		System.out.println("Script output: " + output);
    		
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
        assertTrue( true );
    }
    public void testMap()
    {
    	
    	try {
    		StringWriter writer = new StringWriter();
    		
    		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
    		ScriptContext context = scriptEngine.getContext();
    		context.setWriter(writer);
    		
    		String script_fac= "import java.io.*\n"
    				+"def myMap = [:]					   \n"
					+"myMap[\"key1\"] = 1                  \n"
					+"myMap[\"key2\"] = true               \n"
					+"myMap[\"key3\"] = \"myMap Third\"    \n"
					+"print myMap                          \n"
    				+" ";
    		scriptEngine.eval(script_fac);
    		
    		String output = writer.toString();

    		System.out.println("Script output: " + output);
    		
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
        assertTrue( true );
    }
    public void testGameOfLife()
    {
    	
    	try {
    		StringWriter writer = new StringWriter();
    		
    		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
    		ScriptContext context = scriptEngine.getContext();
    		context.setWriter(writer);

    		String script_game_og_life="" 
    		+"	class GameOfLife {                                                                  \n"    
            +"                                                                                      \n"
    		+"	int generations                                                                     \n"
    		+"	int dimensions                                                                      \n"
    		+"	def board                                                                           \n"
    		+"	                                                                                    \n"
    		+"	GameOfLife(generations = 5, dimensions = 5) {                                       \n"
    		+"		this.generations = generations                                                  \n"
    		+"		this.dimensions = dimensions                                                    \n"
    		+"		this.board = createBlinkerBoard()                                               \n"
    		+"	}                                                                                   \n"
            +"                                                                                      \n"
    		+"	static def createBlinkerBoard() {                                                   \n"
    		+"		[                                                                               \n"
    		+"			[].withDefault{0},                                                          \n"
    		+"			[0,0,1].withDefault{0},                                                     \n"
    		+"			[0,0,1].withDefault{0},                                                     \n"
    		+"			[0,0,1].withDefault{0}                                                      \n"
    		+"		].withDefault{[]}                                                               \n"
    		+"	}                                                                                   \n"
            +"                                                                                      \n"
    		+"	static def createGliderBoard() {                                                    \n"
    		+"		[                                                                               \n"
    		+"			[].withDefault{0},                                                          \n"
    		+"			[0,0,1].withDefault{0},                                                     \n"
    		+"			[0,0,0,1].withDefault{0},                                                   \n"
    		+"			[0,1,1,1].withDefault{0}                                                    \n"
    		+"		].withDefault{[]}                                                               \n"
    		+"	}                                                                                   \n"
            +"                                                                                      \n"
    		+"	static def getValue(board, point) {                                                 \n"
    		+"		def x,y                                                                         \n"
    		+"		(x,y) = point                                                                   \n"
    		+"		if(x < 0 || y < 0) {                                                            \n"
    		+"			return 0                                                                    \n"
    		+"		}                                                                               \n"
    		+"		board[x][y] ? 1 : 0                                                             \n"
    		+"	}                                                                                   \n"
    		+"	                                                                                    \n"
    		+"	static def countNeighbors(board, point) {                                           \n"
    		+"		def x,y                                                                         \n"
    		+"		(x,y) = point                                                                   \n"
    		+"		def neighbors = 0                                                               \n"
    		+"		neighbors += getValue(board, [x-1,y-1])                                         \n"
    		+"		neighbors += getValue(board, [x-1,y])                                           \n"
    		+"		neighbors += getValue(board, [x-1,y+1])                                         \n"
    		+"		neighbors += getValue(board, [x,y-1])                                           \n"
    		+"		neighbors += getValue(board, [x,y+1])                                           \n"
    		+"		neighbors += getValue(board, [x+1,y-1])                                         \n"
    		+"		neighbors += getValue(board, [x+1,y])                                           \n"
    		+"		neighbors += getValue(board, [x+1,y+1])                                         \n"
    		+"		neighbors                                                                       \n"
    		+"	}                                                                                   \n"
            +"                                                                                      \n"
    		+"	static def conwaysRule(currentValue, neighbors) {                                   \n"
    		+"		def newValue = 0                                                                \n"
    		+"		if(neighbors == 3 || (currentValue && neighbors == 2)) {                        \n"
    		+"			newValue = 1                                                                \n"
    		+"		}                                                                               \n"
    		+"		newValue                                                                        \n"
    		+"	}                                                                                   \n"
    		+"	                                                                                    \n"
    		+"	static def createNextGeneration(currentBoard, dimensions) {                         \n"
    		+"		def newBoard = [].withDefault{[].withDefault{0}}                                \n"
    		+"		(0..(dimensions-1)).each { row ->                                               \n"
    		+"			(0..(dimensions-1)).each { column ->                                        \n"
    		+"				def point = [row, column]                                               \n"
    		+"				def currentValue = getValue(currentBoard, point)                        \n"
    		+"				def neighbors = countNeighbors(currentBoard, point)                     \n"
    		+"				newBoard[row][column] = conwaysRule(currentValue, neighbors)            \n"
    		+"			}                                                                           \n"
    		+"		}                                                                               \n"
    		+"		newBoard                                                                        \n"
    		+"	}                                                                                   \n"
            +"                                                                                      \n"
    		+"	static def printBoard(generationCount, board, dimensions) {                         \n"
    		+"		println \"Generation ${generationCount}\"                                       \n"
    		+"		println '*' * 80                                                                \n"
    		+"		(0..(dimensions-1)).each { row ->                                               \n"
    		+"			(0..(dimensions-1)).each { column ->                                        \n"
    		+"				print board[row][column] ? 'X' : '.'                                    \n"
    		+"			}                                                                           \n"
    		+"			print System.getProperty('line.separator')                                  \n"
    		+"		}                                                                               \n"
    		+"		println ''                                                                      \n"
    		+"	}                                                                                   \n"
    		+"	                                                                                    \n"
    		+"	def start() {                                                                       \n"
    		+"		(1..generations).each { generation ->                                           \n"
    		+"			printBoard(generation, this.board, this.dimensions)                         \n"
    		+"			this.board = createNextGeneration(this.board, this.dimensions)              \n"
    		+"		}                                                                               \n"
    		+"	}                                                                                   \n"
    		+"	                                                                                    \n"
    		+"}                                                                                     \n"
            +"                                                                                      \n"
    		+"// Blinker                                                                            \n"
    		+"def game = new GameOfLife()                                                           \n"
    		+"game.start()                                                                          \n"
            +"                                                                                      \n"
    		+"// Glider                                                                             \n"
    		+"game = new GameOfLife(10, 10)                                                         \n"
    		+"game.board = game.createGliderBoard()                                                 \n"
    		+"game.start()                                                                          \n"
            +"";                                                                                        
    		scriptEngine.eval(script_game_og_life);
    		
    		String output = writer.toString();

    		System.out.println("Script output: " + output);
    		
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
        assertTrue( true );
    }
}
