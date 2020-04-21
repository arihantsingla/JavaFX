import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
public class RollDice extends Application {

    public static void main(String[] args) 
    {
        launch(args);
    }
   private int dice1 = 4;
   private int dice2 = 3;
   private Canvas canvas;
   private int frameNum; 
   public void start(Stage stg) {
      
       canvas = new Canvas(200,200);
       drawTheFrame();
       
       canvas.setOnMouseClicked( e -> roll() );
       
       BorderPane parent = new BorderPane();
       parent.setCenter(canvas);
     
       BorderPane root = new BorderPane();
       root.setCenter(canvas);
       Scene createScene = new Scene(root);
       stg.setScene(createScene);
       stg.setTitle("Dice!");
       stg.setResizable(false);
       stg.show();
             
   }

   private AnimationTimer createTimer = new AnimationTimer() {
       public void handle( long time ) {
           dice1 = (int)(Math.random()*6) + 1;
           dice2 = (int)(Math.random()*6) + 1;
           drawTheFrame();
           frameNum++;
           if (frameNum == 60) {
               createTimer.stop();
               
           }
       }
   };
   private void roll() {
       frameNum = 0;
       createTimer.start(); 
   }
   private void drawDiceInFrame(GraphicsContext graphicContext, int val, int x, int y) {
       int r=174,g=245,b=234;
       graphicContext.setFill(Color.rgb(r,g,b));
       graphicContext.fillRect(x, y, 35, 35);
       graphicContext.setStroke(Color.BLACK);
       graphicContext.strokeRect(x+0.5, y+0.5, 34, 34);
       graphicContext.setFill(Color.BLACK);
       if (val > 1)  // upper left dot
           graphicContext.fillOval(x+3, y+3, 9, 9);
       if (val > 3)  // upper right dot
           graphicContext.fillOval(x+23, y+3, 9, 9);
       if (val == 6) // middle left dot
           graphicContext.fillOval(x+3, y+13, 9, 9);
       if (val % 2 == 1) // middle dot (for odd-numbered val's)
           graphicContext.fillOval(x+13, y+13, 9, 9);
       if (val == 6) // middle right dot
           graphicContext.fillOval(x+23, y+13, 9, 9);
       if (val > 3)  // bottom left dot
           graphicContext.fillOval(x+3, y+23, 9, 9);
       if (val > 1)  // bottom right dot
           graphicContext.fillOval(x+23, y+23, 9,9);
   }

   private void drawTheFrame() {
       GraphicsContext graphicContext = canvas.getGraphicsContext2D();
       graphicContext.setFill(Color.rgb(200,200,255));
       graphicContext.fillRect(0,0,100,100);
       graphicContext.setStroke( Color.BLUE );
       graphicContext.strokeRect(1,1,98,98);
       drawDiceInFrame(graphicContext, dice1, 10, 10);
       drawDiceInFrame(graphicContext, dice2, 55, 55);
   }
   
}
