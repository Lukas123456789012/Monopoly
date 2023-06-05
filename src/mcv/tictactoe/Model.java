package mcv.tictactoe;

import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 *
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

    // Messaging system for the MVC
    private final Messenger mvcMessaging;

    // Model's data variables
    private boolean whoseMove;
    private boolean gameOver;
    private String[][] board;
    private int[] cord = {0, 0};

    private void newGame() {
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                this.board[row][col] = "";
            }
        }
        this.board[3][4] = "blue";
        this.board[4][3] = "blue";
        this.board[3][3] = "red";
        this.board[4][4] = "red";
        this.whoseMove = true;
        this.gameOver = false;
        this.mvcMessaging.notify("boardChange", this.board);
    }

    public Model(Messenger messages) {
        mvcMessaging = messages;
    }

    /**
     * Initialize the model here and subscribe to any required messages
     */
    public void init() {
        this.board = new String[8][8];
        this.newGame();
        this.mvcMessaging.subscribe("playerMove", this);
        this.mvcMessaging.subscribe("newGame", this);

    }

    @Override
    public void messageHandler(String messageName, Object messagePayload) {
        // Display the message to the console for debugging
        if (messagePayload != null) {
            System.out.println("MSG: received by model: " + messageName + " | " + messagePayload.toString());
        } else {
            System.out.println("MSG: received by model: " + messageName + " | No data sent");
        }
        if (messageName.equals("newGame")) {
            newGame();
        }
        // playerMove message handler
        if (messageName.equals("playerMove")) {
            // Get the position string and convert to row and col
            System.out.print("test2");
            String position = (String) messagePayload;
            Integer row = Integer.valueOf(position.substring(0, 1));
            Integer col = Integer.valueOf(position.substring(1, 2));
            // If square is blank...
            if ("".equals(board[row][col])) {
                if (this.whoseMove) {
                    this.redMove(row, col);
                    
                } else { 
                    this.blueMove(row, col);
                }
                System.out.print("test3");
                this.mvcMessaging.notify("boardChange", this.board);
            }
System.out.print("test4");
            // newGame message handler
        } else if (messageName.equals("newGame")) {
            // Reset the app state
            
            this.newGame();
            // Send the boardChange message along with the new board 
            this.mvcMessaging.notify("boardChange", this.board);
        }
    }

    public void redMove(int row, int col) {
        System.out.print("test5");
        if (row > 2) { // up 
            int g =0;
            boolean f = false;
            for (int i = 0; row - i > -1; i++) {
                if ("blue".equals(board[row-i][col])) {
                    g++;
                } else if ("red".equals(board[row-i][col])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row-i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col] = "red";
                }
            }
        }
        if (row < 5) { // down
            int g =0;
            boolean f = false;
            for (int i = 0; row + i < 8; i++) {
                if ("blue".equals(board[row+i][col])) {
                    g++;
                } else if ("red".equals(board[row+i][col])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row+i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col] = "red";
                }
            }
        }
        if (col < 5) { //left
            int g =0;
            boolean f = false;
            for (int i = 0; col + i < 8; i++) {
                if ("blue".equals(board[row][col+i])) {
                    g++;
                } else if ("red".equals(board[row][col+i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col+j] = "red";
                }
            }
        }
        if (col > 2) { //right
            int g =0;
            boolean f = false;
            for (int i = 0; col - i > -1; i++) {
                if ("blue".equals(board[row][col-i])) {
                    g++;
                } else if ("red".equals(board[row][col-i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            } else if ("red".equals(board[row][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col-j] = "red";
                }
            }
        }
        if (col > 2 && row > 2) { //top left
            int g =0;
            boolean f = false;
            for (int i = 0; col - i > -1 && row - i > -1 ; i++) {
                if ("blue".equals(board[row-i][col-i])) {
                    g++;
                } else if ("red".equals(board[row-i][col-i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row-i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col-j] = "red";
                }
            }
        } 
        if (col < 5 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 0; col + i < 8 && row + i < 8 ; i++) {
                if ("blue".equals(board[row+i][col+i])) {
                    g++;
                } else if ("red".equals(board[row+i][col+i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row+i][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col+j] = "red";
                }
            }
        } 
        if (col > 2 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 0; col - i > -1 && row + i < 8 ; i++) {
                if ("blue".equals(board[row+i][col-i])) {
                    g++;
                } else if ("red".equals(board[row+i][col-i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row+i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col-j] = "red";
                }
            }
        }
        if (col < 5 && row > 2) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 0; col + i < 8 && row - i > -1 ; i++) {
                if ("blue".equals(board[row-i][col+i])) {
                    g++;
                } else if ("red".equals(board[row-i][col+i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row-i][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col+j] = "red";
                }
            }
        } 
         
    }
    public void blueMove(int row, int col) {
        System.out.print("test5");
        if (row > 2) { // up 
            int g =0;
            boolean f = false;
            for (int i = 0; row - i > -1; i++) {
                if ("red".equals(board[row-i][col])) {
                    g++;
                } else if ("blue".equals(board[row-i][col])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            } else if ("blue".equals(board[row-i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col] = "blue";
                }
            }
        }
        if (row < 5) { // down
            int g =0;
            boolean f = false;
            for (int i = 0; row + i < 8; i++) {
                if ("red".equals(board[row+i][col])) {
                    g++;
                } else if ("blue".equals(board[row+i][col])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            } else if ("blue".equals(board[row+i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col] = "blue";
                }
            }
        }
        if (col < 5) { //left
            int g =0;
            boolean f = false;
            for (int i = 0; col + i < 8; i++) {
                if ("red".equals(board[row][col+i])) {
                    g++;
                } else if ("blue".equals(board[row][col+i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            } else if ("blue".equals(board[row][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col+j] = "blue";
                }
            }
        }
        if (col > 2) { //right
            int g =0;
            boolean f = false;
            for (int i = 0; col - i > -1; i++) {
                if ("red".equals(board[row][col-i])) {
                    g++;
                } else if ("blue".equals(board[row][col-i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col-j] = "blue";
                }
            }
        }
        if (col > 2 && row > 2) { //top left
            int g =0;
            boolean f = false;
            for (int i = 0; col - i > -1 && row - i > -1 ; i++) {
                if ("red".equals(board[row-i][col-i])) {
                    g++;
                } else if ("blue".equals(board[row-i][col-i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row-i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col-j] = "blue";
                }
            }
        } 
        if (col < 5 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 0; col + i < 8 && row + i < 8 ; i++) {
                if ("red".equals(board[row+i][col+i])) {
                    g++;
                } else if ("blue".equals(board[row+i][col+i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row+i][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col+j] = "blue";
                }
            }
        } 
        if (col > 2 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 0; col - i > -1 && row + i < 8 ; i++) {
                if ("red".equals(board[row+i][col-i])) {
                    g++;
                } else if ("blue".equals(board[row+i][col-i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row+i][col-1])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col-j] = "blue";
                }
            }
        }
        if (col < 5 && row > 2) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 0; col + i < 8 && row - i > -1 ; i++) {
                if ("red".equals(board[row-i][col+i])) {
                    g++;
                } else if ("blue".equals(board[row-i][col+i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row-i][col+1])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col+j] = "blue";
                }
            }
        } 
        
         
    }
    
}
