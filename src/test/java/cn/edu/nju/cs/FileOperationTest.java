package cn.edu.nju.cs;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOperationTest {

    @Test
    public void outputTest() {
        int i = 0, j = 1;
        String temp = "hello";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        //System.out.println("record/" + df.format(new Date()) + ".txt");
        File file = new File("record/" + df.format(new Date()) + ".txt");
        try {
            File dir = new File(file.getParent());
            dir.mkdirs();
            file.createNewFile();

            PrintWriter output = new PrintWriter(file);
            output.print(i + " ");
            output.print(j + " ");
            output.println(temp);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inputTest() {
        try {
            //File file = new File("record1.txt");
            InputStreamReader reader = new InputStreamReader(new FileInputStream("record/2018-01-03_22-24-06.txt"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null)
                System.out.println(lineTxt);
            reader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
