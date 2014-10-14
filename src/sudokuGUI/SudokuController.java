/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuGUI;

import InputOutput.Reader;
import InputOutput.Writer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import socrates_asjavaapp.Inference;

public class SudokuController {

    JLabel leftLabel, rightLabel;
    JFormattedTextField[][] leftSudoku, rightSudoku;
    static JButton goButton, loadButton;
	static String fullPath = null;
	
    public SudokuController() {
        leftSudoku = new JFormattedTextField[6][6]; // standard sudoku size
        rightSudoku = new JFormattedTextField[6][6];
    }

    void bindLeftLabel(JLabel label) {
        leftLabel = label;
    }

    void bindRightLabel(JLabel label) {
        rightLabel = label;
    }

    void bindLeftSudokuCell(final int row, final int column, JFormattedTextField field) {
//        field.addPropertyChangeListener("value", new PropertyChangeListener() {
//
//            // if user edits field than You could do something about it here
//            public void propertyChange(PropertyChangeEvent evt) {
//                if (evt.getNewValue() != null) {
//                    String newValue = (String) evt.getNewValue();
//                    userEditedValueAt(row, column, Integer.valueOf(newValue));
//                }
//            }
//        });
        leftSudoku[row][column] = field;
    }

    void bindRightSudokuCell(int row, int column, JFormattedTextField field) {
        rightSudoku[row][column] = field;
    }

    private String getPrettyPrinted(JFormattedTextField[][] sudoku) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append("|");
            for (int j = 0; j < 6; j++) {
                if (sudoku[i][j] != null) {
                    sb.append(sudoku[i][j].getText());
                } else {
                    sb.append("-");
                }
                sb.append(" ");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
	
	void bindLoadButton(JButton loadButton) {
        this.loadButton = loadButton;
        loadButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                loadButtonPressed();
				goButton.setEnabled(true);
            }
        });
    }
	
    void bindCenterButton(JButton goButton) {
        this.goButton = goButton;
		this.goButton.setEnabled(false);
        goButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                goButtonPressed();
            }
        });
    }
    SudokuImplementation listener;

    public void setListener(SudokuImplementation listener) {
        this.listener = listener;
    }
    Thread backGroundThread;

	//begitu tekan load, maka load ke layar dan save ke file
	private void loadButtonPressed() {
		if (listener != null) {
            if (backGroundThread == null || (backGroundThread != null && !backGroundThread.isAlive())) {
                backGroundThread = new Thread() {
                    @Override
                    public void run() {
						
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle("Browse File");
						chooser.setAcceptAllFileFilterUsed(false);
						String fullPathLocation = chooser.getCurrentDirectory().toString();

						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							System.out.println("getCurrentDirectory(): " + fullPathLocation);
							System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
							SudokuController.fullPath = chooser.getSelectedFile().toString();
							Character[][] tmp = new Reader(SudokuController.fullPath).GetContainer();
							new Writer().WriteToFile(tmp);

							Integer[][] leftValues = new Integer[6][6];
							for (int i = 0; i < 6; ++i) {
								for (int j = 0; j < 6; ++j) {
									leftValues[i][j] = tmp[i+1][j+1] == '*' ? null : Character.getNumericValue(tmp[i+1][j+1]);
								}
							}
							listener.loadButtonPressed(leftValues, SudokuController.this);
							leftLabel.setText("");
						} else {
							System.out.println("No Selection ");
						}
                    }
                };
                backGroundThread.start();
            }
        }
	}
	
    private void goButtonPressed() {
        if (listener != null) {
            if (backGroundThread == null || (backGroundThread != null && !backGroundThread.isAlive())) {
                backGroundThread = new Thread() {

                    @Override
                    public void run() {
						Character[][] tmp = new Reader(SudokuController.fullPath).GetContainer();
						Integer[][] leftValues = new Integer[6][6];
						for (int i = 0; i < 6; ++i) {
							for (int j = 0; j < 6; ++j) {
								leftValues[i][j] = tmp[i+1][j+1] == '*' ? null : Character.getNumericValue(tmp[i+1][j+1]);
							}
						}
                        listener.goButtonPressed(leftValues, SudokuController.this);
                    }
                };
                backGroundThread.start();
            }
        }
    }
	
	public void setSudokuLeft(final Integer[][] result) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
						leftSudoku[i][j].setValue(String.valueOf(result[i][j]));
                    }
                }
            }
        });
	}
	
    public void setSudokuResult(final Integer[][] result) {
        // Any GUI interaction must be done on EDT
        // We don't want to block computation so we choose invokeLater
        // as opposed to invokeAndWait.
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        rightSudoku[i][j].setValue(String.valueOf(result[i][j]));
                    }
                }
            }
        });
    }

    public void setSudokuTime(final String time) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                leftLabel.setText("<html>Running time: <b>" + time);
            }
        });
    }

    public void setSudokuCompleted(final boolean completed) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                //rightLabel.setText("<html>Completely Solved: <b>" + completed);
                if (completed) {
					goButton.setEnabled(false);
                }
            }
        });
    }
}
