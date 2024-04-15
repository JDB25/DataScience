import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

 // Import the Scanner class to read text files
public class ReadData {
    public ReadData(){}

  
    public double[][] format(){
      double[][] SvR = new double[77][2];
      int rowNum = 0;
      String temp = "";

        try {
            File myObj = new File("cereal.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              temp = data;
              for(int i = 0; i<9; i++){
                temp = temp.substring(temp.indexOf(",")+1);
              }
              // System.out.println(temp.substring(0, temp.indexOf(",")));
          
             SvR[rowNum][0]=Double.parseDouble(temp.substring(0,temp.indexOf(",")));


             
              temp = data;
              for(int i = 0; i<15; i++){
                temp = temp.substring(temp.indexOf(",")+1);
              }
             SvR[rowNum][1]=Double.parseDouble(temp);

             //i\from index of 9th comma to index opfr 10th comma
             
            rowNum++;
             
              //System.out.println(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        //-read in a text file
        //-move through text file one
        //line at a time
        // make an array--each line split by comma
        //read the array and make an object
        //store all your objercts Arraylist
        //
        return SvR;
        }
        public double[] getSugar(){
          double[] sugar = new double[77];
          double[][] main = format();
          
          for (int index = 0; index < main.length; index++) {
            sugar[index] = main[index][0];
          }
          return sugar;

        }
        public double[] getRating(){
          double[] rating = new double[77];
          double[][] main = format();
          
          for (int index = 0; index < main.length; index++) {
            rating[index] = main[index][1];
          }
          return rating;

        }
public void scatter(){
  XYChart chart = new XYChartBuilder().width(800).height(600).build();

    // Customize Chart
    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
    chart.getStyler().setChartTitleVisible(false);
    chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
    chart.getStyler().setMarkerSize(16);

    // Series

    double[] xData = getRating();
    double[] yData = getSugar();
    
    chart.addSeries("Rating Vs Sugar", yData, xData);
    new SwingWrapper<XYChart>(chart).displayChart();

    







  
}





public void print(double[][] SvR){{
  for (int row = 0; row < SvR.length; row++) {
   
      System.out.println(SvR[row][0] + " sugar and " + SvR[row][1] + " rating");
    
    
  }

}

}
    public static void main(String[] args) {
        ReadData r = new ReadData();
        r.scatter();
    }
}
