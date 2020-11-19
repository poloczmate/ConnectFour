
public class Main {
    public static void main(String[] args){
        Player p1 = new Player('X');
        Player p2 = new Player('O');
        Board b = new Board();
        Game g = new Game(b,p1,p2);

        do {
            g.print();
            g.Step();
        }while (g.checkWin() == 0);
        g.print();
        if (g.checkWin() == 1){
            System.out.println("Player 1 WIN!");
        }else{
            System.out.println("Player 2 WIN!");
        }
    }
}
