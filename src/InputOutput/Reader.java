/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author WILLY
 */
public class Reader {
	public Reader() {
		
	}
	public Character[][] GetContainer() {
		BufferedReader br = null;
		String[] tmp = new String[7];
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("file\\input.txt"));
			
			int i = 0;
			int line = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				++i;
				if ((i%3) != 0) {
					++line;
					tmp[line] = sCurrentLine;
				}
				//System.out.println(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return ProcessStringToCharacter(tmp);
	}
	
	private Character[][] ProcessStringToCharacter(String[] param) {
		if (param == null) return null;
		Character[][] retval = new Character[7][7];
		for (int i = 1; i <= 6; ++i) {
			for (int j = 1; j <= 6; ++j) {
				if (j < 4) retval[i][j] = param[i].charAt(j-1);
				else retval[i][j] = param[i].charAt(j);
			}
		}
		return retval;
	}
}