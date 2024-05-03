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
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;


public class ReadData {
//Variables:
public String fileName = "cereal.csv";
public int NumberofDatapoints = 77;
public int YColumnIndex = 10;
public int XColumnIndex = 16;



















    public ReadData(){
      new SwingWrapper<CategoryChart>(stickChart()).displayChart();
      scatter();
      plotLSRL(linear_regression(getRating(), getSugar()));
      covariance(getRating(), getSugar());
    }

  
    public double[][] format(){
      double[][] SvR = new double[NumberofDatapoints][2];
      int rowNum = 0;
      String temp = "";

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              temp = data;
              for(int i = 0; i<YColumnIndex-1; i++){
                temp = temp.substring(temp.indexOf(",")+1);
              }
             
          
             SvR[rowNum][0]=Double.parseDouble(temp.substring(0,temp.indexOf(",")));


             
              temp = data;
              for(int i = 0; i<XColumnIndex-1; i++){
                temp = temp.substring(temp.indexOf(",")+1);
              }
             SvR[rowNum][1]=Double.parseDouble(temp);

          
             
            rowNum++;
            
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
public void run(){
      new SwingWrapper<CategoryChart>(stickChart()).displayChart();
      scatter();
      plotLSRL(linear_regression(getRating(), getSugar()));
      covariance(getRating(), getSugar());
       

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
    
    chart.addSeries("Sugar Vs Rating", xData, yData);
    new SwingWrapper<XYChart>(chart).displayChart();

  



  
}

public void plotLines(){
  double[] xData = getRating();
    double[] yData = getSugar();
    XYChart chart = QuickChart.getChart("Sugar vs Rating", "Rating", "Sugar", "y(x)", xData, yData);
    new SwingWrapper(chart).displayChart();

}

public void plotLSRL(double[][] Data){
  XYChart chart = new XYChartBuilder().width(800).height(600).build();

    // Customize Chart
    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
    chart.getStyler().setChartTitleVisible(false);
    chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
    
double[] yData = new double[Data.length];
double[] xData = new double[Data.length];
for (int i = 0; i < Data.length; i++) {
  xData[i] = Data[i][0];
  yData[i] = Data[i][1];
  
}


XYSeries scatterSeries = chart.addSeries("Sugar vs Rating", getRating(), getSugar());
scatterSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Scatter);

XYSeries lineSeries = chart.addSeries("LSRL",xData, yData);
lineSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Line);
new SwingWrapper<XYChart>(chart).displayChart();
chart.getStyler().setMarkerSize(15);

}

    public double avgData(double[] data){
      double temp=0;
      
      for (int i = 0; i < data.length; i++) {
        temp+=data[i];

      }  
    

      return temp/data.length;
    }

  
    public double covariance(double[] xData, double[] yData){
      double summation=0;
       for (int i = 0; i < yData.length; i++) {
        summation+=((xData[i]-avgData(xData))*(yData[i]-avgData(yData)));}
      
      System.out.println("Covariance: " + summation/(xData.length-1));
      return summation/(xData.length-1);
    }
    
    
    public double getMin(double[] data){
        double min = data[0];
    for (int i = 0; i < data.length; i++) {
          if (data[i]<min){
            min = data[i];
          }
        }
    
    return min;
    }

    public double getMax(double[] data){
      double max = data[0];
      for (int i = 0; i < data.length; i++) {
            if (data[i]>max){
              max = data[i];
            }
          }
      
      return max;
      }
    
   

    public double stdDev(double[] data){
      double value = 0.0;
      for (int i = 0; i < data.length; i++) {
        value+=(data[i]-avgData(data));
      }
        return Math.sqrt((Math.pow(value, 2))/data.length);
    }


    public double correlation(double[] xData, double[] yData){
      double numorator = 0.0;
      double denom1 = 0.0;
      double denom2 = 0.0;

      for (int i = 0; i < xData.length; i++) {
          numorator+=((xData[i]-avgData(xData))*(yData[i]-avgData(yData)));
      }
      for (int i = 0; i < xData.length; i++) {
        denom1+=Math.pow(xData[i]-avgData(xData),2);  
      }
      for (int i = 0; i < yData.length; i++) {
        denom2+=Math.pow(yData[i]-avgData(yData),2);  
      }

      
      System.out.println("Correlation: "+(numorator)/(Math.sqrt((denom1*denom2))));
      return (numorator)/(Math.sqrt((denom1*denom2)));

    }


 


    public double[][] linear_regression(double[] xData, double[] yData){
      double[][] xytemp;
      
      xytemp = new double[(int)(getMax(xData)-getMin(xData))][2];
   
      double B = correlation(xData, yData)*(stdDev(yData)/stdDev(xData));
        double A = avgData(yData)-(B*avgData(xData));
        // System.out.println(A);
        // System.out.println(B);
        
      for (int i = (int)getMin(xData); i < (int)getMax(xData); i++) {
        xytemp[i-(int)getMin(xData)][1] = (B * i) + A;
        xytemp[i-(int)getMin(xData)][0]=i;
      }
      
      
      
        return xytemp;
    }

  public CategoryChart stickChart(){
    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Stick").build();
    chart.getStyler().setChartTitleVisible(true);
    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
    chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Stick);

       double[] xData = getRating();
    double[] yData = getSugar();
    
    
    chart.addSeries("Sugar vs Rating", xData, yData);
    return chart;


  }

public void print(double[][] SvR){{
  for (int row = 0; row < SvR.length; row++) {
   
      System.out.println(SvR[row][0] + ", " + SvR[row][1]);
    
  }

}

}
    public static void main(String[] args) {
        ReadData r = new ReadData();
       
        // r.scatter();
        // r.stickChart();
        // r.print(r.linear_regression(r.getRating(), r.getSugar()));
        // r.plotLSRL(r.linear_regression(r.getRating(), r.getSugar()));
        // new SwingWrapper<CategoryChart>(r.stickChart()).displayChart();
      

    }
}
