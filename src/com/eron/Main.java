package com.eron;

import java.util.*;

public class Main {
    static ArrayList<Integer> playerPostions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPostions = new ArrayList<Integer>();

    public static void main(String[] args) {
	// write your code here

        //Gameboard
        char [] [] gameBoard = {{' ','|',' ','|',' '},
                                {'-','+','-','+','-'},
                                {' ','|',' ','|',' '},
                                {'-','+','-','+','-'},
                                {' ','|',' ','|',' '}};

        printGameBoard(gameBoard);

        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your move(1-9)");
            int playerPos = scan.nextInt();

            while (playerPostions.contains(playerPos) || cpuPostions.contains(playerPostions)){
                System.out.println("position take! Enter another position");
                playerPos = scan.nextInt();
            }

            //Player
            placePiece(gameBoard, playerPos, "player");

            String gameResultMessage = checkWinner();

            if(gameResultMessage.length()>0){
                System.out.println(gameResultMessage);
                break;
            }

            //CPU
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            while (playerPostions.contains(cpuPos) || cpuPostions.contains(cpuPos)){
                System.out.println("position take! Enter another position");
                playerPos = scan.nextInt();
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            gameResultMessage = checkWinner();
            if(gameResultMessage.length()>0){
                System.out.println(gameResultMessage);
                break;
            }
            System.out.println(gameResultMessage);
        }
    }

    public static void printGameBoard(char [] [] gameBoard){
        for(char [] row: gameBoard ){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char [][] gameBoard, int pos, String user){
        char symbol = ' ';

        if (user.equals("player")){
            symbol = 'X';
            playerPostions.add(pos);
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuPostions.add(pos);
        }

        //change the position of the gameBoard
        switch(pos){
            case 1:
                gameBoard [0] [0] = symbol;
                break;
            case 2:
                gameBoard [0] [2] = symbol;
                break;
            case 3:
                gameBoard [0] [4] = symbol;
                break;
            case 4:
                gameBoard [2] [0] = symbol;
                break;
            case 5:
                gameBoard [2] [2] = symbol;
                break;
            case 6:
                gameBoard [2] [4] = symbol;
                break;
            case 7:
                gameBoard [4] [0] = symbol;
                break;
            case 8:
                gameBoard [4] [2] = symbol;
                break;
            case 9:
                gameBoard [4] [4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(){

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning){
            if(playerPostions.containsAll(l)){
                return "Congrats you won!";
            } else if (cpuPostions.contains(l)){
                return "CPU wins! Try next time";
            } else if (playerPostions.size() + cpuPostions.size() == 9 ){
                return "CAT!";
            }
        }


        return "";
    }
}
