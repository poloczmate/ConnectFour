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
    private MapElement msgLabel = new MapElement(700,50,30,8);
    private MapElement[][] Map = new MapElement[6][7];

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gp = new GridPane();
        //set and add Message Box
        msgLabel.setText("X's turn!");
        msgLabel.setAlignment(Pos.CENTER);
        gp.add(msgLabel,0,0,7,1);
        //create and add MapElements
        for (int i = 0; i < 6; i++){
            MapElement m1 = new MapElement(100,100,72,0);
            MapElement m2 = new MapElement(100,100,72,1);
            MapElement m3 = new MapElement(100,100,72,2);
            MapElement m4 = new MapElement(100,100,72,3);
            MapElement m5 = new MapElement(100,100,72,4);
            MapElement m6 = new MapElement(100,100,72,5);
            MapElement m7 = new MapElement(100,100,72,6);
            gp.addRow(i+2,m1,m2,m3,m4,m5,m6,m7);
            Map[i][0] = m1;
            Map[i][1] = m2;
            Map[i][2] = m3;
            Map[i][3] = m4;
            Map[i][4] = m5;
            Map[i][5] = m6;
            Map[i][6] = m7;
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
        public MapElement(double i, double j, double textSize, double col){
            Rectangle r = new Rectangle(i ,j);
            r.setFill(null);
            r.setStroke(Color.BLACK);
            text.setFont(Font.font(textSize));
            setText("");
            getChildren().addAll(r,text);
            setOnMouseClicked(event->{
                //todo
                if (!nextPlayer.equals("")){
                    if (!hasWinner()){

                    }else printMsg(nextPlayer + " has won!");
                }else printMsg("The game is already over!");
            });
        }
        public void setText(String s){
            text.setText(s);
        }
        public String getText(){
            return text.getText();
        }
        public void printMsg(String s){
            msgLabel.setText(s);
        }
        public boolean isDraw(){
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 7; j++){
                    if (Map[i][j].getText().equals("")) return false;
                }
            }
            return true;
        }

        public boolean hasWinner(){
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 7; j++){
                    if (i < 2){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i+1][j].getText().equals(nextPlayer)
                                && Map[i+2][j].getText().equals(nextPlayer)
                                && Map[i+3][j].getText().equals(nextPlayer)) return true;
                    }
                    if (j < 3){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i][j+1].getText().equals(nextPlayer)
                                && Map[i][j+2].getText().equals(nextPlayer)
                                && Map[i][j+3].getText().equals(nextPlayer)) return true;
                    }
                    if (j < 3 && i < 5){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i+1][j+1].getText().equals(nextPlayer)
                                && Map[i+2][j+2].getText().equals(nextPlayer)
                                && Map[i+3][j+3].getText().equals(nextPlayer)) return true;
                    }
                    if (j < 3 && i > 2){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i-1][j+1].getText().equals(nextPlayer)
                                && Map[i-2][j+2].getText().equals(nextPlayer)
                                && Map[i-3][j+3].getText().equals(nextPlayer)) return true;
                    }
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
