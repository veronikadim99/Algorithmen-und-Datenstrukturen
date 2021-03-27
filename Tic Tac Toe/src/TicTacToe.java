

public class TicTacToe {


    public static int alphaBeta(Board board, int player) {
        // TODO
        //if game is won, dont go to alpha beta pruning
        if(board.isGameWon()) {
            int variableP;
            if (1 == player ) {

                variableP = (board.nFreeFields()+1);
                return variableP;

            } else{
                variableP = -(board.nFreeFields() +1);
                return variableP;

            }


        }else{
            return MinMax(board, player, Integer.MIN_VALUE, Integer.MAX_VALUE, 0)*player;

        }



    }

    private static int MinMax(Board board, int player, int alpha, int beta,int depth) {

        if(board.isGameWon()) {
            int variableP;
            if (1 == player ) {

                variableP = (board.nFreeFields()*-1 -1);
                return variableP;

            } else{
                variableP = (board.nFreeFields() +1);
                return variableP;

            }


        }
        if (board.nFreeFields() == 0) {
            return 0;
        }
        int getTurn = 1;


        //if player is on turn maxA
        if (getTurn == player) {

            for (Position pos : board.validMoves()) {
                board.doMove(pos, player);


                int scoreOfPlayer = MinMax(board, -player, alpha, beta, depth);
                board.undoMove(pos);

                if (scoreOfPlayer > alpha) {
                    alpha = scoreOfPlayer;
                    if (alpha >= beta) {

                        break;
                    }

                }

            }
            return alpha;

            //else if it is not on turn minB
        } else {

            for (Position pos : board.validMoves()) {
                board.doMove(pos, player);
                int scoreOfPlayer = MinMax(board, -player, alpha, beta, depth);
                board.undoMove(pos);
                if (scoreOfPlayer < beta) {
                    beta = scoreOfPlayer;
                    if (alpha >= beta) {

                        break;
                    }

                }

            }
            return beta;
        }


    }

    public static void evaluatePossibleMoves(Board board, int player) {
        // TODO
        XO(player);
        int n;
        if (player == 1) {
            n = 1;
        } else {
            n = -1;
        }

        int horizontal = 0;

        while(horizontal < board.getN()) {
            if(horizontal > 0){ System.out.print("\n"); }
            int vertical = 0;
            while( vertical < board.getN()) {
                //get board for already set values


                Position position = new Position(vertical,horizontal);
                if (board.getField(position) == 1) {

                    System.out.print("  x ");

                }
                if (board.getField(position) == -1) {
                    System.out.print("  o");

                    //get board if pos is empty
                } if(board.getField(position) == 0){

                    //evaluate
                    board.doMove(position,n);
                    if(-alphaBeta(board,-n) >= 0){

                        System.out.print(" ");    //format
                    }

                    System.out.print(" ");
                    System.out.print(-alphaBeta(board,-n));

                    board.undoMove(position);

                }
                vertical++;
            }
            horizontal++;
        }

    }

    public static void XO(int player){
        char x = 0;
        char o = 0;
        char n;
        if(player == 1) {
            n = 'x';
        }else{
            n = 'o';
        }
        System.out.println("Evaluation for player '" +n +"':");



    }


    public static void main(String[] args)
    {

    }
}

