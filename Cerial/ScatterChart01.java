import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner; 
public class ScatterChart01 implements ExampleChart<XYChart> {
 
    public static void main(String[] args) {
   
      ExampleChart<XYChart> exampleChart = new ScatterChart01();
      XYChart chart = exampleChart.getChart();
      new SwingWrapper<XYChart>(chart).displayChart();
    }
   
    @Override
    public XYChart getChart() {
   
      // Create Chart
      XYChart chart = new XYChartBuilder().width(800).height(600).build();
   
      // Customize Chart
      chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
      chart.getStyler().setChartTitleVisible(false);
      chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
      chart.getStyler().setMarkerSize(16);
   
      // Series
      List<Double> xData = new LinkedList<Double>();
      List<Double> yData = new LinkedList<Double>();
      Random random = new Random();
      int size = 1000;
      for (int i = 0; i < size; i++) {
        xData.add(random.nextGaussian() / 1000);
        yData.add(-1000000 + random.nextGaussian());
      }
      chart.addSeries("Gaussian Blob", xData, yData);
   
      return chart;
    }
   
  }