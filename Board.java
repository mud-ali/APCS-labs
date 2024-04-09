package connectfour;

/** Implements a ConnectFour board */
public class Board {
  static final int ROWS = 6; // The number of rows on the board.
  static final int COLUMNS = 7; // The number of columns on the board.

  // board is the instance variable that stores the actual board, a 2-array of chars.
  // The possible characters stored in the board include:
  //  ' ' - Empty Space
  //  'R' - Red checker
  //  'Y' - Yellow checker
  private char[][] board;

  /** Constructor of a Board object.
    *
    * Instantiates the 'board' variable and initializes all of the cells to ' '.
    */
  public Board() {
    board = new char[ROWS][];
    for (int i=0;i<ROWS;i++) {
      board[i] = new char[COLUMNS];
      for (int j=0;j<COLUMNS;j++) {
        board[i][j] = ' ';
      }
    }
  }

  /** Returns a String representation of the current board. The String will be printed to System.out by ConnectFour.java.
   * See the format in the assignment PDF. 
   * */
  public String toString() {
    String out = "";
    for (int i=0;i<board.length;i++) {
      out+='|';
      for (int j=0;j<board[i].length;j++) {
        out += board[i][j] + "|";
      }
      out += "\n";
    }
    
    for (int i=0;i<COLUMNS*2+1;i++) {
      out += '-';
    }
    return out;
  }

  /** Attempts to drop the specified checker in the specified column.  If the checker is
    * successfully dropped, the method adds the checker to the board variable and returns true.
    * If the specified column is full, the method returns false.
    */
  public boolean dropChecker(int column, char checker) {
    if (column >= COLUMNS || board[0][column]!=' ') return false;
    
    if (checker != 'R' && checker != 'Y')
      throw new IllegalArgumentException("Checker must be either R or Y");
    int row = ROWS-1;
    while (board[row][column] != ' ') --row;
    board[row][column] = checker;

    return true;
  }

  /** Determines if the game has a winner.  The method returns true if either user
    * has four in a row in any direction (including along a diagonal!).
    **/
  public boolean checkWinner() {
    for (int rowNum=0;rowNum<ROWS;rowNum++) {
      char[] row = board[rowNum];
      for (int col=0;col<COLUMNS-3;col++) {
        if (
          //horizontal
          (row[col]==row[col+1] &&
           row[col+2]==row[col+3] &&
           row[col+1]==row[col+2] &&
           row[col] != ' '
          ) || //check the \ diagonal
          (rowNum < ROWS-3 &&
           row[col]==board[rowNum+1][col+1] &&
           board[rowNum+2][col+2] == board[rowNum+3][col+3] &&
           board[rowNum+2][col+2]==row[col] &&
           row[col] != ' '
          )
        ) return true;
      }
    }

    for (int rowNum=3;rowNum<ROWS;rowNum++) {
      for (int col=0;col<COLUMNS;col++) {
        if (
          // check the vertical
          (board[rowNum][col] == board[rowNum-1][col] &&
            board[rowNum-2][col] == board[rowNum-3][col] &&
            board[rowNum-2][col] == board[rowNum][col]  &&
            board[rowNum][col] != ' '
          ) || ( // check the / diagonal
            col < COLUMNS-4 &&
            board[rowNum][col] == board[rowNum-1][col+1] &&
            board[rowNum-2][col+2] == board[rowNum-3][col+3] &&
            board[rowNum][col] == board[rowNum-3][col+3] &&
            board[rowNum][col] != ' '
          )
        )
          return true;
      }
    }
    
    return false;
  }

  /** Determines if the game is a draw.
    * This method assumes the user already checked if there is a winner via a call to
    * checkWinner. This method returns true if there are no more blank squares. */
  public boolean checkDraw() {
    for (int i=0;i<board.length;i++) {
      for (int j=0;j<board[i].length;j++) {
        if (board[i][j]==' ') return false;
      }
    }
    return true;
  }

}
