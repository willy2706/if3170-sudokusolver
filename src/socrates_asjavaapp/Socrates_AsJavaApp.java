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
    try {
    Rete env = new Rete();
    env.batch("E:\\PEERSEMESTEREMPAT\\AI\\tubes1\\SocratesKnowledgeBase\\SocratesKnowledgeBase.clp");
    env.reset();
    env.run();
    } catch (JessException e) {
    e.printStackTrace();
    }
  }
}

