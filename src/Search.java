/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class Search {

    DatabaseManager dbm = new DatabaseManager();

    public String Suggestions(String table, String col, String word) {
        String Sg = null;
        String keyword;
        int i = 0;
        int hits = 0;
        int lenght = word.length();

        int a = dbm.getStringArray(table, col).length;

        while (i < a - 1) {

            if (lenght < dbm.getStringArray(table, col)[i + 1].length()) {
                keyword = dbm.getStringArray(table, col)[i + 1].substring(0, lenght);
                 
                if (keyword.equals(word)) {
                hits++;                                                      //get number of hits
                Sg = dbm.getStringArray(table, col)[i + 1];
               
            }
            } else {
                keyword = word;
            }
            
            i++;
        }
        if (hits == 1) {
            return Sg;                                                        // return only hit
        } else {
            return word;
        }                                                         // more than one hit. return input
    }
    public String[] Search_para(String para){
   String[] ASK = new String[2];
   
        para=":\\";
        ASK[0]=para;
        para = "\\";
        ASK[1]= para;
        
    
    return ASK;
    }

}
