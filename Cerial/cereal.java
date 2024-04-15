import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;



public class cereal{
    private String name;
    private String mfr;
    private boolean type;
    private int cals;
    private int protein;
    private int fat;
    private double carbo;
    private int sugar;
    private int potass;
    private int vitamins;
    private int shelf;
    private double weight;
    private double cups;
    private double rating;
    private int sodium;
    private double fiber;

    public cereal(String name, String mfr, boolean type, int cals, int protein, int fat, double carbo, int sugar,
            int potass, int vitamins, int shelf, double weight, double cups, double rating, int sodium, double fiber) {
        this.name = name;
        this.mfr = mfr;
        this.type = type;
        this.cals = cals;
        this.protein = protein;
        this.fat = fat;
        this.carbo = carbo;
        this.sugar = sugar;
        this.potass = potass;
        this.vitamins = vitamins;
        this.shelf = shelf;
        this.weight = weight;
        this.cups = cups;
        this.rating = rating;
        this.sodium = sodium;
        this.fiber = fiber;
    }
    

    public static void main(String[] args) throws Exception {
   
        double[] xData = new double[] { 0.0, 1.0, 2.0 };
        double[] yData = new double[] { 2.0, 1.0, 0.0 };
     
        // Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
     
        // Show it
        new SwingWrapper(chart).displayChart();
     
      }
    



}