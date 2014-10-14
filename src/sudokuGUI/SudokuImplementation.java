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
public interface SudokuImplementation {
    void goButtonPressed(Integer[][] leftSudokuValues, SudokuController resultAcceptor);
	void loadButtonPressed(Integer[][] leftSudokuValues, SudokuController resultAcceptor);
}
