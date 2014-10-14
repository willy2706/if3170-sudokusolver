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
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
/**
 * View which constructs every component and creates it's own controller.
 */
public class SudokuView extends JFrame {

    SudokuController controller;

    public void setSudokuImplementation(SudokuImplementation listener) {
        controller.setListener(listener);
    }

    /** Creates new form NewJFrame */
    public SudokuView() {
        controller = new SudokuController();
        setTitle("Sudoku Solver 1.0");
        getContentPane().add(createCenterPanel(), BorderLayout.CENTER);
        getContentPane().add(createBottomPanel(), BorderLayout.SOUTH);
        setMinimumSize(new Dimension(600, 300));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        JLabel leftLabel = createLabel("");
        JLabel rightLabel = createLabel("");

        controller.bindLeftLabel(leftLabel);
        controller.bindRightLabel(rightLabel);

        bottomPanel.add(leftLabel, getWholeCellConstraints());
        bottomPanel.add(new JSeparator(JSeparator.VERTICAL));
        bottomPanel.add(rightLabel, getWholeCellConstraints());

        bottomPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        return bottomPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(createLeftPanel(), getWholeCellConstraints());
		centerPanel.add(createLoadButton(), getPreferredSizeConstraint());
        centerPanel.add(createCenterButton(), getPreferredSizeConstraint());
        centerPanel.add(createRightPanel(), getWholeCellConstraints());
        return centerPanel;
    }

    private GridBagConstraints getPreferredSizeConstraint() {
        // default will do
        return new GridBagConstraints();
    }

	private JButton createLoadButton() {
        JButton goButton = new JButton("Load");
        controller.bindLoadButton(goButton);
        return goButton;
    }
	
    private JButton createCenterButton() {
        JButton goButton = new JButton("Solve >");
        controller.bindCenterButton(goButton);
        return goButton;
    }
	
    private static final Insets sixPixelInset = new Insets(6, 6, 6, 6);

    private JPanel createRightPanel() {
        JPanel rightPanel = create3x2Panel(6);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                JPanel panel2 = create2x3Panel(2);
                fillRightPanelWithNonEditable(panel2, i, j);
                rightPanel.add(panel2);
            }
        }
        rightPanel.setBorder(new EmptyBorder(sixPixelInset));
        return rightPanel;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = create3x2Panel(6);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                JPanel panel2 = create2x3Panel(2);
                fillLeftPanelWithNonEditable(panel2, i, j);
                leftPanel.add(panel2);
            }
        }
        leftPanel.setBorder(new EmptyBorder(sixPixelInset));
        return leftPanel;
    }

    private GridBagConstraints getWholeCellConstraints() {
        GridBagConstraints wholePanelCnstr = getPreferredSizeConstraint();
        wholePanelCnstr.fill = java.awt.GridBagConstraints.BOTH;
        wholePanelCnstr.weightx = 1.0;
        wholePanelCnstr.weighty = 1.0;
        return wholePanelCnstr;
    }

//    private void fillPanelWithEditable(JPanel panel, int majorRow, int majorColumn) {
//        for (int minorRow = 0; minorRow < 1; minorRow++) {
//            for (int minorColumn = 0; minorColumn < 1; minorColumn++) {
//                final JFormattedTextField editableField = createEditableField();
//                int column = majorColumn * 3 + minorColumn;
//                int row = majorRow * 3 + minorRow;
//                controller.bindLeftSudokuCell(row, column, editableField);
//                panel.add(editableField);
//            }
//        }
//    }

    private void fillLeftPanelWithNonEditable(JPanel panel, int majorRow, int majorColumn) {
        for (int minorRow = 0; minorRow < 2; minorRow++) {
            for (int minorColumn = 0; minorColumn < 3; minorColumn++) {
                final JFormattedTextField editableField = createNonEditableField();
				//final JFormattedTextField editableField1 = createNonEditableField();
                int column = majorColumn * 3 + minorColumn;
                int row = majorRow * 2 + minorRow;
                //controller.bindRightSudokuCell(row, column, editableField);
				controller.bindLeftSudokuCell(row, column, editableField);
                panel.add(editableField);
            }
        }
    }
	
	    private void fillRightPanelWithNonEditable(JPanel panel, int majorRow, int majorColumn) {
        for (int minorRow = 0; minorRow < 2; minorRow++) {
            for (int minorColumn = 0; minorColumn < 3; minorColumn++) {
                final JFormattedTextField editableField = createNonEditableField();
				//final JFormattedTextField editableField1 = createNonEditableField();
                int column = majorColumn * 3 + minorColumn;
                int row = majorRow * 2 + minorRow;
                //controller.bindRightSudokuCell(row, column, editableField);
				controller.bindRightSudokuCell(row, column, editableField);
                panel.add(editableField);
            }
        }
    }

	private JPanel create3x2Panel(int gap) {
        final GridLayout gridLayout = new GridLayout(3, 2, 1, 1);
        gridLayout.setHgap(gap);
        gridLayout.setVgap(gap);
        JPanel panel = new JPanel(gridLayout);
        return panel;
    }
	
    private JPanel create2x3Panel(int gap) {
        final GridLayout gridLayout = new GridLayout(2, 3, 1, 1);
        gridLayout.setHgap(gap);
        gridLayout.setVgap(gap);
        JPanel panel = new JPanel(gridLayout);
        return panel;
    }

    private JFormattedTextField createNonEditableField() {
        JFormattedTextField field = createEditableField();
        field.setEditable(false);
        field.setBackground(Color.WHITE); // otherwise non-editable gets gray
        return field;
    }

    private JFormattedTextField createEditableField() {
        JFormattedTextField field = new JFormattedTextField();
        // accept only one digit and nothing else
        try {
            field.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#")));
        } catch (java.text.ParseException ex) {
        }
        field.setPreferredSize(new Dimension(16, 30));
        field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        field.setText(" ");
        field.setBorder(null);
        return field;
    }
}
