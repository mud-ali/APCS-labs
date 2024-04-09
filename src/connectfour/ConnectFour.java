package connectfour;

import java.util.Scanner;

public class ConnectFour {
  // Player 0 is Red; Player 1 is Yellow
  static String colorLabelCaps[] = { "Red", "Yellow" };
  static String colorLabel[] = { "red", "yellow" };
  static char colorChar[] = { 'R', 'Y' };
  
  public static void main(String[] args) {
    int player = 0;  // Player 0 is red; Player 1 is yellow.  
    
    Board board = new Board();
    Scanner input = new Scanner(System.in);
    
    while (!board.checkWinner() && !board.checkDraw()) {
      System.out.printf("Drop a %s disk at column (0-6): ", colorLabel[player]);
      int col = input.nextInt();
      System.out.println();
      while (!board.dropChecker(col, colorChar[player])) {
        System.out.print("That column is full or does not exist. Please try another column: ");
        col = input.nextInt();
        System.out.println();
      }
      System.out.println(board);

      player = (player+1) % 2;
    }
    
    if (board.checkDraw()) {
      System.out.println("The game has been drawed! Thanks for playing");
    } else {
      System.out.printf("%s wins!\n", colorLabelCaps[(player+1) % 2]);
    }

    input.close();
  }
}
