package sample;

import com.sun.javafx.collections.MappingChange;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public void start(Stage primaryStage){
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
        //add new Button to restart the game
        Button button = new Button("Restart");
        button.setMinSize(75,50);
        button.setFont(Font.font(15));
        gp.addRow(8,button);
        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ConnectFour");
        primaryStage.show();
        //OnAction(Restart) -> all Cells free
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 6; i++){
                    for (int j = 0; j < 7; j++) Map[i][j].setText("");
                }
                nextPlayer = "X";
                msgLabel.setText(nextPlayer + "'s turn!");
            }
        });
    }

    private class MapElement extends StackPane{
        private Text text = new Text("");
        public MapElement(double i, double j, double textSize, double col){
            Rectangle r = new Rectangle(i ,j);
            r.setFill(null);
            r.setStroke(Color.BLACK);
            text.setFont(Font.font(textSize));
            setText("");
            getChildren().addAll(r,text);
            setOnMouseClicked(event->{
                if (!nextPlayer.equals("")){ // if "" the game has ended
                    if (checkColumn(col) != 10){ //if 10 the column is full
                        if (!hasWinner()){ //check winner
                            if (!isDraw()){ //check table full
                                Map[(int) checkColumn(col)][(int) col].setText(nextPlayer); //do the step
                                if (hasWinner()){
                                    printMsg(nextPlayer+ " has won! Start a new game with restart button!");
                                    nextPlayer = "";
                                }else{
                                    if (nextPlayer.equals("X")) nextPlayer="O";
                                    else if(nextPlayer.equals("O")) nextPlayer = "X";
                                    printMsg(nextPlayer+"'s turn!");
                                }
                            }else{
                                printMsg("The game is a draw, start a new game with the restart button!");
                            }
                        }else {
                            printMsg(nextPlayer+ " has won! Start a new game with restart button!");
                            nextPlayer = "";
                        }
                    }else printMsg("The column is already full! " + nextPlayer +"'s turn!");
                }else printMsg("The game is already over!");
            });
        }

        public void setText(String s){
            text.setText(s);
        }
        public String getText(){
            return text.getText();
        }
        public void printMsg(String s){ //set message at the top of the window
            msgLabel.setText(s);
        }
        public boolean isDraw(){ // check the table is full
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 7; j++){
                    if (Map[i][j].getText().equals("")) return false;
                }
            }
            return true;
        }

        public boolean hasWinner(){ // check winner
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 7; j++){
                    if (i < 3){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i+1][j].getText().equals(nextPlayer)
                                && Map[i+2][j].getText().equals(nextPlayer)
                                && Map[i+3][j].getText().equals(nextPlayer)){
                            return true;
                        }
                    }
                    if (j < 4){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i][j+1].getText().equals(nextPlayer)
                                && Map[i][j+2].getText().equals(nextPlayer)
                                && Map[i][j+3].getText().equals(nextPlayer)) return true;
                    }
                    if (j < 4 && i < 3){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i+1][j+1].getText().equals(nextPlayer)
                                && Map[i+2][j+2].getText().equals(nextPlayer)
                                && Map[i+3][j+3].getText().equals(nextPlayer)) return true;
                    }
                    if (j < 4 && i > 2){
                        if (Map[i][j].getText().equals(nextPlayer)
                                && Map[i-1][j+1].getText().equals(nextPlayer)
                                && Map[i-2][j+2].getText().equals(nextPlayer)
                                && Map[i-3][j+3].getText().equals(nextPlayer)) return true;
                    }
                }
            }
            return false;
        }

        public double checkColumn(double col){ //check column
            for (int i = 5; i >= 0;i--){
                if (Map[i][(int)col].getText().equals("")) return i;
            }
            return 10;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
