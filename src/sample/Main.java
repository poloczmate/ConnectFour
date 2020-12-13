package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Map;


public class Main extends Application {
    private String nextPlayer = "X";
    //initalize with bigger row,col than other MapElements
    private MapElement msgLabel = new MapElement(700,50,30,8,8);

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gp = new GridPane();
        //set and add Message Box
        msgLabel.setText("X's turn!");
        msgLabel.setAlignment(Pos.CENTER);
        gp.add(msgLabel,0,0,7,1);
        //create and add MapElements
        for (int i = 0; i < 6; i++){
            MapElement m1 = new MapElement(100,100,72,i,0);
            MapElement m2 = new MapElement(100,100,72,i,1);
            MapElement m3 = new MapElement(100,100,72,i,2);
            MapElement m4 = new MapElement(100,100,72,i,3);
            MapElement m5 = new MapElement(100,100,72,i,4);
            MapElement m6 = new MapElement(100,100,72,i,5);
            MapElement m7 = new MapElement(100,100,72,i,6);
            gp.addRow(i+2,m1,m2,m3,m4,m5,m6,m7);
        }

        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ConnectFour");
        primaryStage.show();
    }

    private class MapElement extends StackPane{
        private Text text = new Text("");
        private double row;
        private double col;
        public MapElement(double i, double j, double textSize, double row, double col){
            Rectangle r = new Rectangle(i ,j);
            r.setFill(null);
            r.setStroke(Color.BLACK);
            text.setFont(Font.font(textSize));
            setText("");
            getChildren().addAll(r,text);
        }
        public void setText(String s){
            text.setText(s);
        }
        public String getText(){
            return text.getText();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
