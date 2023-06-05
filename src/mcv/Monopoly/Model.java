package mcv.Monopoly;

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
    private int whoseMove;
    private boolean gameOver;
    private Card[][] board;
    private Player[] list;

    private void newGame() {
        // patern is: cost of property, cost of house, no house, 1 house, 2 house, 3 house, hotel
        Card MA = new Card(60, 50, 2, 4, 10, 30, 90, 160, 250, "Mediterranean Avenue");
        board[10][9] = MA;
        Card BA = new Card(60, 50, 3, 8, 20, 60, 180, 320, 450, "Baltic Avenue");
        board[10][7] = BA;
        Card OA = new Card(100, 50, 6, 12, 30, 90, 270, 400, 550, "Oriental Avenue");
        board[10][4] = OA;
        Card VA = new Card(100, 50, 6, 12, 30, 90, 270, 400, 550, "Vermont Avenue");
        board[10][2] = VA;
        Card CA = new Card(120, 50, 8, 16, 40, 100, 300, 450, 600, "Connecticut Avenue");
        board[10][1] = CA;
        Card SCP = new Card(140, 100, 10, 20, 50, 150, 450, 625, 750, "St. Charles Place");
        board[9][0] = SCP;
        Card SA = new Card(140, 100, 10, 20, 50, 150, 450, 625, 750, "States Avenue");
        board[7][0] = SA;
        Card ViA = new Card(160, 100, 12, 24, 60, 180, 500, 700, 900, "Virginia Avenue");
        board[6][0] = ViA;
        Card SJP = new Card(180, 100, 14, 28, 70, 200, 550, 750, 950, "St. James Place");
        board[4][0] = SJP;
        Card TA = new Card(180, 100, 14, 28, 70, 200, 550, 750, 950, "Tennessee Avenue");
        board[2][0] = TA;
        Card NYA = new Card(180, 100, 16, 32, 80, 220, 600, 800, 1000, "New York Avenue");
        board[1][0] = NYA;
        Card KA = new Card(220, 150, 18, 36, 90, 250, 700, 875, 1050, "Kentucky Avenue");
        board[0][1] = KA;
        Card IA = new Card(220, 150, 18, 36, 90, 250, 700, 875, 1050, " Indiana Avenue");
        board[0][3] = IA;
        Card ILA = new Card(240, 150, 20, 40, 100, 300, 750, 925, 1100, "Illinois Avenue");
        board[0][4] = ILA;
        Card AA = new Card(260, 150, 22, 44, 110, 330, 800, 975, 1150, "Atlantic Avenue");
        board[0][6] = AA;
        Card VEA = new Card(260, 150, 22, 44, 110, 330, 800, 975, 1150, "Ventor Avenue");
        board[0][7] = VEA;
        Card MG = new Card(280, 150, 24, 48, 120, 360, 850, 1025, 1200, "Marving Gardens");
        board[0][9] = MG;
        Card PA = new Card(300, 200, 26, 52, 130, 390, 900, 1100, 1275, "Pacific Avenue");
        board[1][10] = PA;
        Card NCA = new Card(300, 200, 26, 52, 130, 390, 900, 1100, 1275, "North Carolina Avenue");
        board[2][10] = NCA;
        Card PEA = new Card(320, 200, 28, 56, 150, 450, 1000, 1200, 1400, "Pennsylvania Avenue");
        board[4][10] = PEA;
        Card PP = new Card(350, 200, 35, 70, 175, 500, 1100, 1300, 1500, "Park Place");
        board[7][10] = PP;
        Card B = new Card(400, 200, 50, 100, 200, 600, 1400, 1700, 2000, "Boardwalk");
        board[9][10] = B;
        whoseMove = 1;
        Player a = new Player(1500, 10, 10);
        Player b = new Player(1500, 10, 10);
        Player c = new Player(1500, 10, 10);
        Player d = new Player(1500, 10, 10);
        this.list = new Player[4];
        list[0] = a;
        list[1] = b;
        list[2] = c;
        list[3] = d;
        this.mvcMessaging.notify("turn", this.whoseMove);
        this.mvcMessaging.notify("money", a.getMoney());
    }

    public Model(Messenger messages) {
        mvcMessaging = messages;
    }

    /**
     * Initialize the model here and subscribe to any required messages
     */
    public void init() {
        this.board = new Card[11][11];
        this.newGame();
        this.mvcMessaging.subscribe("playerMove", this);
        this.mvcMessaging.subscribe("newGame", this);
        this.mvcMessaging.subscribe("roll", this);
        this.mvcMessaging.subscribe("special", this);
        this.mvcMessaging.subscribe("owned", this);
        this.mvcMessaging.subscribe("notOwned", this);
        this.mvcMessaging.subscribe("end turn", this);
        this.mvcMessaging.subscribe("yes", this);
        this.mvcMessaging.subscribe("no", this);
        this.mvcMessaging.subscribe("loan", this);
        this.mvcMessaging.subscribe("Go", this);
        this.mvcMessaging.subscribe("Dividend", this);
        this.mvcMessaging.subscribe("Speeding", this);
        this.mvcMessaging.subscribe("boardWalk", this);

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
        switch (messageName) {
            case "playerMove":
                // Get the position string and convert to row and col
                System.out.print("test2");
                String position = (String) messagePayload;
                Integer row = Integer.valueOf(position.substring(0, 1));
                Integer col = Integer.valueOf(position.substring(1, 2));
                // If square is blank...
                System.out.print("test4");
                // newGame message handler
                break;
            case "newGame":
                // Reset the app state

                this.newGame();
                // Send the boardChange message along with the new board
                this.mvcMessaging.notify("boardChange", this.board);
                break;
            case "roll": {
                Player a = list[this.whoseMove - 1];
                if (!a.getBankrupt()) {
                    for (int i = ((Integer) messagePayload); i > 0; i--) {
                        if (a.getXPosition() == 10 && a.getYPosition() != 0) {
                            a.setYPosition(a.getYPosition() - 1);
                        } else if (a.getYPosition() == 0 && a.getXPosition() != 0) {
                            a.setXPosition(a.getXPosition() - 1);
                        } else if (a.getXPosition() == 0 && a.getYPosition() != 10) {
                            a.setYPosition(a.getYPosition() + 1);
                        } else if (a.getYPosition() == 10 && a.getXPosition() != 10) {
                            a.setXPosition(a.getXPosition() + 1);
                        }
                    }
                    Cords b = new Cords(a.getXPosition(), a.getYPosition(), this.whoseMove, board, list);
                    this.mvcMessaging.notify("Moved", b);
                } else {
                    this.mvcMessaging.notify("bankrupt", 0);
                }
                break;
            }
            case "special": {
                Player a = list[whoseMove - 1];
                if ((a.getXPosition() == 10 && a.getYPosition() == 6) || (a.getXPosition() == 6 && a.getYPosition() == 0) || (a.getXPosition() == 0 && a.getYPosition() == 6) || (a.getXPosition() == 6 && a.getYPosition() == 10) ) {
                    this.mvcMessaging.notify("Railroad", 50);
                } else if ((a.getXPosition() == 0 && a.getYPosition() == 2)|| (a.getXPosition() == 10 && a.getYPosition() == 3)){
                    this.mvcMessaging.notify("chance", 50);
                } else {
                a.subMoney(150);
                this.mvcMessaging.notify("null", 150);
                break;
                }
            }
            case "end turn": {
                if (this.whoseMove == 4) {
                    this.whoseMove = 1;
                } else {
                    this.whoseMove += 1;
                }
                Player a = list[whoseMove - 1];
                int m = a.getMoney();
                this.mvcMessaging.notify("money", m);
                this.mvcMessaging.notify("turn", this.whoseMove);
                this.mvcMessaging.notify("reset", this.whoseMove);
                break;
            }
            case "owned": {
                Player a = list[whoseMove - 1];
                Card c = board[a.getXPosition()][a.getYPosition()];
                if (c.getOwner() == whoseMove) {
                    this.mvcMessaging.notify("House", c.getHouseCost());
                } else {
                    int m = 0;
                    switch (c.getHouses()) {
                        case 0: {
                            m = c.getOGvalue();
                            break;
                        }
                        case 1: {
                            m = c.getHouse1();
                            break;
                        }
                        case 2: {
                            m = c.getHouse2();
                            break;
                        }
                        case 3: {
                            m = c.getHouse3();
                            break;
                        }
                        case 4: {
                            m = c.getHouse4();
                            break;
                        }
                        case 5: {
                            m = c.getHotel();
                            break;
                        }
                        default:
                            break;
                    }
                    a.subMoney(m);
                    int g = c.getOwner() - 1;
                    Player h = list[g];
                    h.addMoney(m);
                }

                break;
            }
            case "notOwned": {
                Player a = list[this.whoseMove - 1];
                Card b = board[a.getXPosition()][a.getYPosition()];
                this.mvcMessaging.notify("Buy", b.getPropertyCost());
                break;
            }
            case "yes": {
                Player a = list[this.whoseMove - 1];
                Card b = board[a.getXPosition()][a.getYPosition()];
                int c = b.getPropertyCost();
                a.subMoney(c);
                b.setOwner(this.whoseMove - 1);
                this.mvcMessaging.notify("reset", b.getPropertyCost());
                break;
            }
            case "Hyes": {
                Player a = list[this.whoseMove - 1];
                Card b = board[a.getXPosition()][a.getYPosition()];
                int c = b.getHouseCost();
                a.subMoney(c);
                b.setHouses(b.getHouses() + 1);
                this.mvcMessaging.notify("reset", b.getPropertyCost());
                break;
            }
            case "loan": {
                Player a = list[this.whoseMove - 1];
                a.addMoney(150);
            }
            case "Go": {
                Player a = list[this.whoseMove - 1];
                a.addMoney(200);
                a.setXPosition(10);
                a.setYPosition(10);
            }
            case "Speeding": {
                Player a = list[this.whoseMove - 1];
                a.subMoney(15);
            }
            case "boardwalk": {
                Player a = list[this.whoseMove - 1];
                a.setXPosition(9);
                a.setYPosition(10);
            }
            case "Dividend": {
                Player a = list[this.whoseMove - 1];
                a.addMoney(50);
            }
            default:
                break;
        }
    }

}
