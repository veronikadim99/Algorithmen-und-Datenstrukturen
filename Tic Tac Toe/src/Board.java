import java.util.*;

public class Board {
    private int n;

    private int[][] gameboard;
    private int o = -1;
    private int x = 1;
    private int empty_field = 0;
    private int vertical;
    private int horizontal;
    private int occupied_fields;
    private int winner; //who is the winner

    public int getN() { return n;}
    public Board(int n) {
        //initialize the board and throw exception if the parameters are out of bounds
        //the board here is initialized with zeros
        this.n = n;
        if (n < 1 || n > 10) {
            throw new InputMismatchException();
        }

       this.gameboard = new int[n][n];

        for (horizontal = 0; horizontal < n; horizontal++) {
            for (vertical = 0; vertical < n; vertical++) {
                gameboard[horizontal][vertical] = empty_field;
            }
        }


    }

    public int nFreeFields() {
        // TODO
        //get the number of the free positions on the board
        int all_positions = n*n;
        int free_positions;

        free_positions = all_positions - occupied_fields;
        return free_positions;

    }

    public int getField(Position pos) throws InputMismatchException {
        // TODO

        //get the value on the certain position


        if(pos.x > n || pos.y > n || pos.x < 0 || pos.y < 0) {
            throw new InputMismatchException("Position doesn't found");
        }else{
            return gameboard[pos.y][pos.x];
        }
    }

    public void setField (Position pos,int token) throws InputMismatchException{


            // TODO
            //set field und the board and ++ or -- free fields

            if(token == this.o || token == this.x || token == this.empty_field) {

                    if(token == this.o || token == this.x){
                        occupied_fields++;
                    }
                    if(this.gameboard[pos.y][pos.x] != empty_field){
                        occupied_fields--;
                    }
                    this.gameboard[pos.y][pos.x] = token;

            }else {
                throw new InputMismatchException("Token not valid");
            }



    }


        public void doMove (Position pos,int player)
        {
            // TODO
            //do move and -- the number of free fields
            getField(pos);
           if(player != 1 && player != -1) {
                throw new InputMismatchException("This place is not free.");

            }

            if(player == 1 || player == -1){

                    occupied_fields++;
                    nFreeFields();


                if(this.gameboard[pos.y][pos.x] != empty_field){

                    occupied_fields--;
                    nFreeFields();
                }
                this.gameboard[pos.y][pos.x] = player;
            }




        }

        public void undoMove (Position pos) {
            // TODO
            //undo move and ++ the number of free fields
           setField(pos,empty_field);

        }



        public boolean isGameWon () {
            //horizontals
            for(horizontal = 0; horizontal < n; horizontal++) {
                double average = Arrays.stream(gameboard[horizontal]).average().getAsDouble();
                if (average == 1 || average == -1){
                    return true;
                }
            }
            //verticals
            int countO = 0,countX = 0;
            for(vertical = 0; vertical < n; vertical++){
                countX = 0; countO = 0;
                for(horizontal = 0; horizontal<n; horizontal++){
                    if (gameboard[horizontal][vertical] == 1) {
                        countX++;
                    }
                    if (gameboard[horizontal][vertical] == -1) {
                        countO++;
                    }
                }
            }
            //diagonals
            if (countO == n || countX == n) { return true; }
            horizontal = 0; countX = 0; countO = 0;
            for(vertical = n-1; vertical >= 0; vertical--, horizontal++){
                if (gameboard[horizontal][vertical] == 1) {
                    countX++;
                }
                if (gameboard[horizontal][vertical] == -1) {
                    countO++;
                }

            }
            if (countO == n || countX == n) { return true; }
            vertical = 0; countX = 0; countO = 0;
            for(horizontal = 0; horizontal < n; horizontal++, vertical++){
                if (gameboard[horizontal][vertical] == 1) {
                    countX++;
                }
                if (gameboard[horizontal][vertical] == -1) {
                    countO++;
                }

            }
            if (countO == n || countX == n) { return true; }

            return false;

        }


        public Iterable<Position> validMoves () {
            //get all teh valid moves, and add those who are empty to a new linked list
            List<Position> valid_moves = new LinkedList<Position>();


            for (horizontal = 0; horizontal < n; horizontal++) {
                for (vertical = 0; vertical < n; vertical++) {
                    if (gameboard[horizontal][vertical] == empty_field) {
                        Position pos= new Position(vertical, horizontal);
                        valid_moves.add(pos);
                    }
                }
            }
           return valid_moves;
        }

        //printing the board
        public void print ()
        {
            for (horizontal = 0; horizontal < n; horizontal++) {

                for(vertical = 0; vertical < n; vertical++) {

                    System.out.print(this.gameboard[horizontal][vertical] + "   ");
                }

                System.out.println("");
                }
            // TODO
        }

        public int TheWinnerIs(){
        return this.winner;   //function for winner
        }

}

