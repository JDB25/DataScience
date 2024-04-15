import java.util.concurrent.TimeUnit;

public class Counting {
    private String counter = "1";
    private int num;
    public Counting(){
        
        for (int i = 0; i < 166; i++) {
            counter= counter + "0";
            
            System.out.println(counter);
            for (int j = 0; j < 5000000; j++) {
                num++;
            }
           
                    
                
                
            
        
        
    }}
    public static void main(String[] args) {
        new Counting();
    }
}
