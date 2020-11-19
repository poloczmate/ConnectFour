import java.util.Scanner;

public class Game {
    private Board b;
    private Player p1;
    private Player p2;
    private int counter = 0;

    public Game(Board b, Player p1, Player p2) {
        this.b = b;
        this.p1 = p1;
        this.p2 = p2;
    }

    public int checkWin() {
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 6; j++){
                if (i < 5){
                    if (b.getData(i,j) == 'X' && b.getData(i+1,j) == 'X' && b.getData(i+2,j) == 'X' && b.getData(i+3,j) == 'X') return 1;
                    if (b.getData(i,j) == 'O' && b.getData(i+1,j) == 'O' && b.getData(i+2,j) == 'O' && b.getData(i+3,j) == 'O') return 2;
                }
                if (j < 4){
                    if (b.getData(i,j) == 'X' && b.getData(i,j+1) == 'X' && b.getData(i,j+2) == 'X' && b.getData(i,j+3) == 'X') return 1;
                    if (b.getData(i,j) == 'O' && b.getData(i,j+1) == 'O' && b.getData(i,j+2) == 'O' && b.getData(i,j+3) == 'O') return 2;
                }
                if (j < 4 && i < 5){
                    if (b.getData(i,j) == 'X' && b.getData(i+1,j+1) == 'X' && b.getData(i+2,j+2) == 'X' && b.getData(i+3,j+3) == 'X') return 1;
                    if (b.getData(i,j) == 'O' && b.getData(i+1,j+1) == 'O' && b.getData(i+2,j+2) == 'O' && b.getData(i+3,j+3) == 'O') return 2;
                }
                if (i < 5 && 2 < j){
                    if (b.getData(i,j) == 'X' && b.getData(i+1,j-1) == 'X' && b.getData(i+2,j-2) == 'X' && b.getData(i+3,j-3) == 'X') return 1;
                    if (b.getData(i,j) == 'O' && b.getData(i+1,j-1) == 'O' && b.getData(i+2,j-2) == 'O' && b.getData(i+3,j-3) == 'O') return 2;
                }
            }
        }
        return 0;
    }

    public void Step(){
        int col;
        int step;
        Scanner scan = new Scanner(System.in);
        if (counter % 2 == 0){
            System.out.println("Player " + p1.getMark() + " please choose a column: ");
            col = scan.nextInt();
            step = b.step(p1,col);
        }else{
            System.out.println("Player " + p2.getMark() + " please choose a column: ");
            col = scan.nextInt();
            step = b.step(p2,col);
        }
        if (step == 1) {
            counter++;
        }else{
            System.out.println("Bad input or Column is full!");
        }

    }

    public void print(){
        b.print();
    }
}
