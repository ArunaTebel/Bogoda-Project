/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class Table_handler {

    public void clear_table(javax.swing.JTable table, int col, int row) {
        int k = 0;
        int j = 0;

        while (k <= row-1) {
            j = 0;
            while (j < col) {

                table.setValueAt(null, k, j);
                j++;
            }
            k++;
        }
    }

}
