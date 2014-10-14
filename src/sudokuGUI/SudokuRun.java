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
import java.awt.EventQueue;
import javax.swing.UIManager;

import java.awt.EventQueue;
import javax.swing.UIManager;

public class SudokuRun implements Runnable {

    public void run() {
        // ******************** here You can swap Your true implementation
        SudokuImplementation sudokuImplementation = new CLISPSudokuImplementation();
        // ***************************** *************** ********* **** ** *


        SudokuView sudokuView = new SudokuView();
        sudokuView.setSudokuImplementation(sudokuImplementation);
        sudokuView.setVisible(true);
    }

    public static void main(String args[]) {
        tryToSetSystemLookAndFeel();
        EventQueue.invokeLater(new SudokuRun());
    }

    private static void tryToSetSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Couldn't set LAF");
        }
    }
}