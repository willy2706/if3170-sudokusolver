/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudokuGUI;

/**
 *
 * @author WILLY
 */
import InputOutput.Writer;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jess.JessException;
import jess.Rete;
import socrates_asjavaapp.Inference;

/**
 * Simulates Sudoku solver. Demonstrates how to update GUI. The whole
 * implementation is constructed so GUI never freezes.
 */
class CLISPSudokuImplementation implements SudokuImplementation {

    public CLISPSudokuImplementation() {
    }
	
    public void goButtonPressed(Integer[][] leftSudokuValues, SudokuController resultAcceptor) {		
		Inference inf = new Inference();
		Thread th = new Thread(inf);
		th.start();
		
		for (int i = 0; inf.result == null ; i++) {
            resultAcceptor.setSudokuCompleted(false);
            resultAcceptor.setSudokuTime(String.valueOf(i * 50) + "ms");
            resultAcceptor.setSudokuResult(getRandomResult());
            waitSomeTime();
        }
		
        resultAcceptor.setSudokuResult(inf.result);
        resultAcceptor.setSudokuCompleted(true);
        waitSomeTime();
    }

    private void waitSomeTime() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ex) {
        }
    }

    private Integer[][] getRandomResult() {
        Integer[][] randomResult = new Integer[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                randomResult[i][j] = (int) (Math.random() * 9);
            }
        }
        return randomResult;
    }

	@Override
	public void loadButtonPressed(Integer[][] leftSudokuValues, SudokuController resultAcceptor) {
		resultAcceptor.setSudokuResult(new Integer[6][6]);
		resultAcceptor.setSudokuLeft(leftSudokuValues);
	}
}