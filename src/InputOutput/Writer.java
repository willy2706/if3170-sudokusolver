/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author WILLY
 */
public class Writer {
	public Writer() {
		
	}
	
	public void WriteToFile(Character[][] param) {
//		for (int i = 1; i < param.length; ++i) {
//			for (int j = 1; j < param[i].length; ++j) {
//				System.out.print(param[i][j]);
//			}
//			System.out.println();
//		}
		try {
			File file = new File("file\\tc.clp");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("(defrule grid-values\n");
			bw.write("	?f <- (phase grid-values)\n");
			bw.write("	=>\n");
			bw.write("	(retract ?f)\n");
			bw.write("	(assert (phase expand-any))\n");
			bw.write("	(assert (size 3))\n");
			
			bw.write("	(assert (possible (row 1) (column 1) (value " + (param[1][1] == '*' ? "any" : param[1][1].toString()) + ") (diagonal 1)(group 1) (id 1)))\n");
			bw.write("	(assert (possible (row 1) (column 2) (value " + (param[1][2] == '*' ? "any" : param[1][2].toString()) + ") (diagonal 3)(group 1) (id 2)))\n");
			bw.write("	(assert (possible (row 1) (column 3) (value " + (param[1][3] == '*' ? "any" : param[1][3].toString()) + ") (diagonal 4)(group 1) (id 3)))\n");
			bw.write("	(assert (possible (row 2) (column 1) (value " + (param[2][1] == '*' ? "any" : param[2][1].toString()) + ") (diagonal 5)(group 1) (id 4)))\n");
			bw.write("	(assert (possible (row 2) (column 2) (value " + (param[2][2] == '*' ? "any" : param[2][2].toString()) + ") (diagonal 1)(group 1) (id 5)))\n");
			bw.write("	(assert (possible (row 2) (column 3) (value " + (param[2][3] == '*' ? "any" : param[2][3].toString()) + ") (diagonal 6)(group 1) (id 6)))\n");   

			bw.write("	(assert (possible (row 1) (column 4) (value " + (param[1][4] == '*' ? "any" : param[1][4].toString()) + ") (diagonal 7)(group 2) (id 7)))\n");
			bw.write("	(assert (possible (row 1) (column 5) (value " + (param[1][5] == '*' ? "any" : param[1][5].toString()) + ") (diagonal 8)(group 2) (id 8)))\n");
			bw.write("	(assert (possible (row 1) (column 6) (value " + (param[1][6] == '*' ? "any" : param[1][6].toString()) + ") (diagonal 2)(group 2) (id 9)))\n");
			bw.write("	(assert (possible (row 2) (column 4) (value " + (param[2][4] == '*' ? "any" : param[2][4].toString()) + ") (diagonal 9)(group 2) (id 10)))\n");
			bw.write("	(assert (possible (row 2) (column 5) (value " + (param[2][5] == '*' ? "any" : param[2][5].toString()) + ") (diagonal 2)(group 2) (id 11)))\n");
			bw.write("	(assert (possible (row 2) (column 6) (value " + (param[2][6] == '*' ? "any" : param[2][6].toString()) + ") (diagonal 10)(group 2) (id 12)))\n");

			bw.write("	(assert (possible (row 3) (column 1) (value " + (param[3][1] == '*' ? "any" : param[3][1].toString()) + ") (diagonal 11)(group 3) (id 13)))\n");
			bw.write("	(assert (possible (row 3) (column 2) (value " + (param[3][2] == '*' ? "any" : param[3][2].toString()) + ") (diagonal 12)(group 3) (id 14)))\n");
			bw.write("	(assert (possible (row 3) (column 3) (value " + (param[3][3] == '*' ? "any" : param[3][3].toString()) + ") (diagonal 1)(group 3) (id 15)))\n");
			bw.write("	(assert (possible (row 4) (column 1) (value " + (param[4][1] == '*' ? "any" : param[4][1].toString()) + ") (diagonal 13)(group 3) (id 16)))\n");
			bw.write("	(assert (possible (row 4) (column 2) (value " + (param[4][2] == '*' ? "any" : param[4][2].toString()) + ") (diagonal 14)(group 3) (id 17)))\n");
			bw.write("	(assert (possible (row 4) (column 3) (value " + (param[4][3] == '*' ? "any" : param[4][3].toString()) + ") (diagonal 2)(group 3) (id 18)))\n");  

			bw.write("	(assert (possible (row 3) (column 4) (value " + (param[3][4] == '*' ? "any" : param[3][4].toString()) + ") (diagonal 2)(group 4) (id 19)))\n");
			bw.write("	(assert (possible (row 3) (column 5) (value " + (param[3][5] == '*' ? "any" : param[3][5].toString()) + ") (diagonal 15)(group 4) (id 20)))\n");
			bw.write("	(assert (possible (row 3) (column 6) (value " + (param[3][6] == '*' ? "any" : param[3][6].toString()) + ") (diagonal 16)(group 4) (id 21)))\n");
			bw.write("	(assert (possible (row 4) (column 4) (value " + (param[4][4] == '*' ? "any" : param[4][4].toString()) + ") (diagonal 1)(group 4) (id 22)))\n");
			bw.write("	(assert (possible (row 4) (column 5) (value " + (param[4][5] == '*' ? "any" : param[4][5].toString()) + ") (diagonal 17)(group 4) (id 23)))\n");
			bw.write("	(assert (possible (row 4) (column 6) (value " + (param[4][6] == '*' ? "any" : param[4][6].toString()) + ") (diagonal 18)(group 4) (id 24)))\n");
   
			bw.write("	(assert (possible (row 5) (column 1) (value " + (param[5][1] == '*' ? "any" : param[5][1].toString()) + ") (diagonal 19)(group 5) (id 25)))\n");
			bw.write("	(assert (possible (row 5) (column 2) (value " + (param[5][2] == '*' ? "any" : param[5][2].toString()) + ") (diagonal 2)(group 5) (id 26)))\n");
			bw.write("	(assert (possible (row 5) (column 3) (value " + (param[5][3] == '*' ? "any" : param[5][3].toString()) + ") (diagonal 20)(group 5) (id 27)))\n");
			bw.write("	(assert (possible (row 6) (column 1) (value " + (param[6][1] == '*' ? "any" : param[6][1].toString()) + ") (diagonal 2)(group 5) (id 28)))\n");
			bw.write("	(assert (possible (row 6) (column 2) (value " + (param[6][2] == '*' ? "any" : param[6][2].toString()) + ") (diagonal 21)(group 5) (id 29)))\n");
			bw.write("	(assert (possible (row 6) (column 3) (value " + (param[6][3] == '*' ? "any" : param[6][3].toString()) + ") (diagonal 22)(group 5) (id 30)))\n");

			bw.write("	(assert (possible (row 5) (column 4) (value " + (param[5][4] == '*' ? "any" : param[5][4].toString()) + ") (diagonal 23)(group 6) (id 31)))\n");
			bw.write("	(assert (possible (row 5) (column 5) (value " + (param[5][5] == '*' ? "any" : param[5][5].toString()) + ") (diagonal 1)(group 6) (id 32)))\n");
			bw.write("	(assert (possible (row 5) (column 6) (value " + (param[5][6] == '*' ? "any" : param[5][6].toString()) + ") (diagonal 24)(group 6) (id 33)))\n");
			bw.write("	(assert (possible (row 6) (column 4) (value " + (param[6][4] == '*' ? "any" : param[6][4].toString()) + ") (diagonal 25)(group 6) (id 34)))\n");
			bw.write("	(assert (possible (row 6) (column 5) (value " + (param[6][5] == '*' ? "any" : param[6][5].toString()) + ") (diagonal 26)(group 6) (id 35)))\n");
			bw.write("	(assert (possible (row 6) (column 6) (value " + (param[6][6] == '*' ? "any" : param[6][6].toString()) + ") (diagonal 1)(group 6) (id 36))))\n");
			
			bw.close();
		} catch (IOException e) {
			
		}
		
	}
}
