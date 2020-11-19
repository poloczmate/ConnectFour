public class Board {
    private char array[][];

    public Board() {
        array = new char[7][6];
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 6; j++){
                array[i][j] = ' ';
            }
        }
    }

    public void print(){
        for (int i = 0; i < 7; i++) System.out.print(i + " | ");
        System.out.println();
        for (int j = 6; 0 < j; j--){
            for (int i = 0;i < 7; i++){
                if (i != 6) System.out.print(array[i][j-1] + " | ");
                if (i ==6 ) System.out.println(array[i][j-1]);
            }
            System.out.println("-------------------------");
        }
    }

    public int step(Player p, int col){
        if (col < 0 || 6 < col) return 0;
        int i = 0;
        while (i < 6 && array[col][i] != ' ') i++;
        if (i < 6){
            array[col][i] = p.getMark();
            return 1;
        }else{
            return 0;
        }
    }

    public char getData(int row, int column){
        return array[row][column];
    }
}
