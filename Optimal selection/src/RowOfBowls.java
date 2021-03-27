import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RowOfBowls {
    private int player1 = 1;
    private  LinkedList<Integer> allBows = new LinkedList<>();
    private  LinkedList<Integer> allBows1 = new LinkedList<>();
    private int scoreOne;
    private int scoreMinusOne;
    private int finalScore;
    private int player = player1;
    private LinkedList<Integer> forOne = new LinkedList<>();
    private LinkedList<Integer> forMinusOne = new LinkedList<>();
    private LinkedList<Integer> fake = new LinkedList<>();
    private int matrixGain[][];
    private int var1,var2,var3;
    private List<Integer> sequences = new LinkedList<Integer>();
    private int var4,var5;





    public RowOfBowls() {}


    public int maxGain(int[] values)
    {

        matrixGain = new int[values.length][values.length];  //make nxn matrix, where n is length of the given array
        for(int i = 0; i < values.length; i++) {
            allBows1.add(values[i]);
        }
        int i,j;

        for(int y = 0; y < values.length; y++){
            for(i =0,j = y;j < values.length ; j++, i++){
                //initializing variables for the recursive formula later
               if((i + 2) <= j) {
                   var1 = matrixGain[i+2][j];

               }else{
                   var1 = 0;

               }
               if((i+1) <= (j-1)){

                   var2 = matrixGain[i+1][j-1];
               }else{

                   var2 = 0;
               }
               if(i <= (j-2)){

                   var3 = matrixGain[i][j-2];

               }else{
                   var3 = 0;
               }
                //in the first iteration 2>=0 and 1>=0 so the variables are equals to 0
               var4 = Math.min(var1,var2);
               var5 = Math.min(var2,var3);

                //in the first iteration of the loop there is 0+4, 0+4 and the maximum is 4
               matrixGain[i][j] = Math.max(var4 + values[i], var5 + values[j]);

            }
        }

        int finalScore2 = matrixGain[0][values.length-1] + matrixGain[0][values.length-1] - Arrays.stream(values).sum();
        return finalScore2;
    }

    public int maxGainRecursive(int[] values)
    {
        // TODO
        for(int i = 0; i < values.length; i++) {
            allBows.add(values[i]);
        }

        return maxGainRecursiveMethod();
    }
    public int maxGainScore(){
        //sum of the score
        if(allBows.isEmpty()) {
            //sum for player one, take elements from the array for player1
            for (int i = 0; i < forOne.size(); i++) {
                int var = forOne.get(i);
                this.scoreOne = scoreOne + var;

            }
            //sum for player2, take elements from the array for player2
            for (int i = 0; i < forMinusOne.size(); i++) {
                int var = forMinusOne.get(i);
                this.scoreMinusOne = scoreMinusOne + var;
            }
            if(scoreOne > scoreMinusOne) {
                finalScore = scoreOne - scoreMinusOne;
            }else{
                finalScore = scoreMinusOne-scoreOne;
            }
        }
        return finalScore;

    }
    public int maxGainRecursiveMethod() {

        if (allBows.isEmpty()) {
            return maxGainScore();
        }
        if (player == player1) {
            fake = forOne;
        } else {
            fake = forMinusOne;
        }


        if (allBows.size() == 1) {
            //if there is one element left add it to the score of the player
            fake.add(allBows.getFirst());
            allBows.removeFirst();

            return maxGainRecursiveMethod();
        }
        //if there are only 2 elements left, get the max
        if (allBows.size() == 2) {
            if (allBows.getLast() > allBows.getFirst()) {
                fake.add(allBows.getLast());
                allBows.removeLast();
            } else {
                fake.add(allBows.getFirst());
                allBows.removeFirst();
            }
            player = -player;
            return maxGainRecursiveMethod();
        }




        if (Math.max(allBows.get(0), allBows.get(allBows.size() - 1)) > (Math.max(allBows.get(1), allBows.get(allBows.size() - 2))))
        {
            if (Math.max(allBows.get(0), allBows.get(allBows.size() - 1)) == allBows.get(0)) {
                fake.add(allBows.get(0));
                allBows.remove(allBows.get(0));
            } else if (allBows.get(0) == allBows.size() - 1) {
                if ((Math.max(allBows.get(1), allBows.get(allBows.size() - 2)) == allBows.get(1))) {
                    fake.add(allBows.get(allBows.size() - 1));
                    allBows.remove(allBows.size() - 1);
                } else {
                    fake.add(allBows.get(0));
                    allBows.remove(allBows.get(0));
                }
            } else {
                fake.add(allBows.get(allBows.size() - 1));
                allBows.remove(allBows.size() - 1);
            }
        }else {
            if ((Math.max(allBows.get(1), allBows.get(allBows.size() - 2)) == allBows.get(1))) {
                fake.add(allBows.get(allBows.size() - 1));
                allBows.remove(allBows.size() - 1);
            } else {
                fake.add(allBows.get(0));
                allBows.remove(allBows.get(0));

            }
        }
        player = -player;
        return maxGainRecursiveMethod();


    }


    public Iterable<Integer> optimalSequence() {
        // TODO
        int j = 0;
        int n = allBows1.size()-1;
        int i = 0;
        while(i < allBows1.size()-1){
            int choice = (matrixGain[j+1][n] >= matrixGain[j][n-1]) ? n : j; //compare values in the matrix
            sequences.add(choice);  //add index in the list
            if(choice == n){
                n--;
            }else{
                j++;
            }
            i++;
        }
        sequences.add(j);

        return sequences;
    }



    public static void main(String[] args)
    {

    }




}

