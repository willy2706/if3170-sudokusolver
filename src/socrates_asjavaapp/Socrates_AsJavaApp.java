/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socrates_asjavaapp;

/**
 *
 * @author Arina Listyarini DA
 */


import InputOutput.Reader;
import InputOutput.Writer;
import java.util.Iterator;
import jess.JessException;
import jess.Rete;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arina Listyarini DA
 */

public class Socrates_AsJavaApp{
public static void main(String[] args) {
	Character[][] tmp = new Reader().GetContainer();
    try {
		new Writer().WriteToFile(tmp);
		
		Rete env = new Rete();
		env.batch("file\\sudoku.clp");
		env.batch("file\\solve.clp");
		env.batch("file\\output-frills.clp");
		env.batch("file\\tc1.clp");
		env.reset();
		env.eval("(run)");
		Iterator it = env.listFacts();
    } catch (JessException e) {
		e.printStackTrace();
    }
  }
}

