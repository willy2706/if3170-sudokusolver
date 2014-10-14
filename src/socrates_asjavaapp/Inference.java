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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class Inference implements Runnable {
	public Inference() {
	
	}
	public Integer[][] result;
	private Integer[][] GetMatrix(Iterator it) {
		String pattern = "\\(MAIN::possible \\(row [0-9]{1}\\) \\(column [0-9]{1}\\) \\(diagonal [0-9]{1,2}\\) \\(value [0-9]{1,2}\\) \\(group [0-9]{1}\\) \\(id [0-9]{1,2}\\)\\)";
		String pattern_row = "row [0-9]";
		String pattern_column = "column [0-9]";
		String pattern_value = "value [0-9]{1,2}";
		Pattern p = Pattern.compile(pattern);
		Pattern prow = Pattern.compile(pattern_row);
		Pattern pcol = Pattern.compile(pattern_column);
		Pattern pval = Pattern.compile(pattern_value);

		Integer[][] cell = new Integer[6][6];
		int i = -1; //row
		int j = -1; //column
		Integer v = null;
		String str = new String();

		while(it.hasNext()){
			Object element = it.next();
			String el = element.toString();
			Matcher m = p.matcher(el);

			if(m.matches()){
				Matcher mrow = prow.matcher(el);
				Matcher mcol = pcol.matcher(el);
				Matcher mval = pval.matcher(el);
				int row_end = -1;
				int col_end = -1;
				int val_end = -1;
				while(mrow.find()){
					row_end = mrow.end();
				}
				str = el.substring(row_end-1,row_end);
				try{
					i = Integer.parseInt(str);
				}
				catch(NumberFormatException e){
					e.printStackTrace();
				}

				while(mcol.find()){
					col_end = mcol.end();
				}
				str = el.substring(col_end-1,col_end);
				try{
					j = Integer.parseInt(str);
				}
				catch(NumberFormatException e){
				   e.printStackTrace();
				}

				while(mval.find()){
					val_end = mval.end();
				}
				str = el.substring(val_end-1,val_end);
				try{
					v = Integer.parseInt(str);
				}
				catch(NumberFormatException e){
					//System.out.println();
				}
				cell[i-1][j-1] = v;
			}
		}
		return cell;
	}
	
	private Integer[][] inferenceFact() {
		Integer[][] cell = null;
		try {
			Rete env = new Rete();
			env.batch("file\\sudoku.clp");
			env.batch("file\\solve.clp");
			env.batch("file\\output-frills.clp");
			env.batch("file\\tc.clp");
			env.reset();
			env.eval("(run)");
			Iterator it = env.listFacts();
			cell = this.GetMatrix(it);
		} catch (JessException e) {
			e.printStackTrace();
		}
		
		this.result = cell; //ini paling penting
		return cell;
	}

	@Override
	public void run() {
		this.inferenceFact();
	}
	
//	public void start () {
//		if (t == null) {
//		   t = new Thread (this);
//		   t.start();
//		}
//	}
}

