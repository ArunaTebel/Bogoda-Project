
import java.io.*;
import java.sql.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.MemoryCacheImageInputStream;
//import javax.swing.text.Document;
//import com.itextpdf.text.DocumentException;

public class PDF_Handling {

    public void Print_Database_Without_Filtering(String databasetable, String arr[][],String title) throws DocumentException {
        try {

            Document document = new Document();

            try {
                PdfWriter.getInstance(document, new FileOutputStream("E:/data.pdf"));
                //PdfWriter.getInstance(document, new MemoryCacheImageInputStream() );
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PDF_Handling.class.getName()).log(Level.SEVERE, null, ex);
            }

            int num_of_columns = arr.length;

            document.open();

            document.add(addTitle(title,15));

            PdfPTable table = new PdfPTable(num_of_columns);
            for (int i = 0; i < num_of_columns; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(arr[0][i]));
                cell.setHorizontalAlignment(1);
                cell.setBorder(0);
                table.addCell(cell);

                //table.addCell(null);
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bogoda", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + databasetable + "");
            while (rs.next()) {

                for (int i = 0; i < num_of_columns; i++) {
                    // table.addCell(add(rs.getString(arr[1][i])));
                     PdfPCell cell = new PdfPCell(new Phrase(rs.getString(arr[1][i])));
                    cell.setHorizontalAlignment(1);
                    cell.setBorder(0);
                    table.addCell(cell);
                    
                }
            }
            document.add(table);
            document.close();
            // TODO code application logic here

        } catch (Exception ex) {
            Logger.getLogger(PDF_Handling.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "E:/data.pdf");
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null,"error");
        }

    }

    public static Paragraph addTitle(String title, int font_size) {
        Font font = FontFactory.getFont("Times-Roman", font_size, Font.BOLD | Font.UNDERLINE);
        Paragraph p = new Paragraph(title, font);
        p.setSpacingAfter(20);
        p.setAlignment(1); // Center
        return p;
    }

    public static Phrase add(String s) {
        Font fontbold = FontFactory.getFont("Times-Roman", 15, Font.BOLD);
        Phrase p = new Phrase(s, fontbold);
        return p;
    }

    public static PdfPTable AddTable() {
        PdfPTable table = new PdfPTable(2);

        return table;
    }

}
