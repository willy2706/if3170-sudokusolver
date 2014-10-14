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

import java.awt.EventQueue;
import javax.swing.JFileChooser;
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
//        JFileChooser chooser = new JFileChooser();
//		chooser.setCurrentDirectory(new java.io.File("."));
//		chooser.setDialogTitle("Browse File");
//		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		chooser.setAcceptAllFileFilterUsed(false);
//		String fullPathLocation = chooser.getCurrentDirectory().toString();
//
//		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//		  System.out.println("getCurrentDirectory(): " + fullPathLocation);
//		  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
//		} else {
//		  System.out.println("No Selection ");
//		}

		
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