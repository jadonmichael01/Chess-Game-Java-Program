// STUDENT: JADONMICHAEL DE JESUS
// PANTHER ID: 6176454

package main;

import game.*;
import java.util.*;

public class Main {

    public static void main(String[] args){
        Game game = null;           // this initializes a game instance
        String player1 = null;      // this initializes the player1 string
        String player2 = null;      // this initializes the player 2 string
        while(true) {
            Scanner keyboard = new Scanner(System.in);  // this is our scanner
            String command = "";                        // this allows spaces within the same line
            command = keyboard.next();
            switch(command){
                case "new":     // if the user inputs "new game" followed by two names for player1 and player 2
                    String gameWord = keyboard.next();      // it will create a new game instance according to their inputs for
                    if(gameWord.equals("game")){            // player1 and player2
                        player1 = keyboard.next();
                        player2 = keyboard.next();
                        game = new Game(player1, player2);
                        game.showBoard(System.out);         // this prints out the current board at the starting position
                    }
                    break;
                case "mv":      // if the user inputs "mv" followed by a specific move set
                    String move = keyboard.next();  // it will move a piece according to the user's input
                    if(!move.matches("^[a-h][1-8][a-h][1-8]$")){    // this checks that the mv input is between a-h and 1-8 like d2d4
                        System.out.println("Illegal command! Try again");  // if the user fails to input these parameters in the regex it will print illegal command and wait for another input
                        break;
                    }
                    if(game.move(new Move(move), command)){ // this calls the move method in the game class and creates a new move
                        if(!game.isWhiteTurn()){ // if the piece is white it will print player1's move
                            System.out.println(player1 + " moves " + move + "\n");
                        } else { // if the piece is not white, which is black it will print player2's move
                            System.out.println(player2 + " moves " + move + "\n");
                        }
                        game.showBoard(System.out); // prints out the new board along with the new positions after the move
                    } else {
                        System.out.println("Illegal command! Try again");
                    }
                    break;
                case "cp": // if the user inputs "cp" followed by a specific move set it will execute a capture for a piece
                    String capture = keyboard.next();
                    if(!capture.matches("^[a-h][1-8][a-h][1-8]$")){ // similar to "mv" command it checks if it matches the regex so its a moveset according to our board
                        System.out.println("Illegal command! Try again");
                        break;
                    }
                    if(game.move(new Move(capture), command)) { // this calls the game method in the game class
                        if(!game.isWhiteTurn()) { // if the piece is white it means player1 captured a piece
                            System.out.println(player1 + " captures " + capture + "\n");
                        } else { // if the piece is black it prints out player2 capturing a piece
                            System.out.println(player2 + " captures " + capture + "\n");
                        }
                        game.showBoard(System.out); // prints out the gameboard
                    } else {
                        System.out.println("Illegal command! Try again");
                    }
                    break;
                case "print": // prints out the game board and the list of moves
                    String secondToken = keyboard.next();
                    if(secondToken.equals("status")){
                        game.showBoard(System.out);
                        System.out.println("List of moves:" + game);
                    } else {
                        System.out.println("Illegal command! Try again");
                    }
                    break;
                case "undo": // if the user inputs undo it will undo the previous move
                    if(game.getMoves().size() == 0){ // if no moves have been made, nothing will happen because there is nothing go back to
                        System.out.println("No moves have been made.");
                        continue;
                    }
                    Game alternative = new Game(game.getPlayer1(), game.getPlayer2()); // we initialize a new game instance with the current player1 and player2
                    ArrayList<Move> moves = game.getMoves(); // we initialize an arraylist of moves
                    moves.remove(moves.size() -1); // moves.size() - 1 will go back to the move before to "undo" a move
                    for(Move m : moves) {
                        alternative.move(m, "m"); // this creates the set of moves with .size() -1
                    }
                    game = alternative; // we then create the alternative game making it the new game
                    game.showBoard(System.out); // we print out the game board
                    break;
                default: // if the user inputs an invalid command that is not according to the command phrases it will default to printing this:
                    System.out.println("Invalid command! try again!");

            }


        }

    }
}
