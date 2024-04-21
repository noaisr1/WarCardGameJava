
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/*
 *  Main class starts the game and manages the game process.
 */
public class Main extends Application {
	DeckOfCards myDeckOfCards = new DeckOfCards();
    private final ArrayList<Card> player1Cards = new ArrayList<>();
    private final ArrayList<Card> player2Cards = new ArrayList<>();
    private int rounds = 0;
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * The start function opens the dialog box and setting the elements in it and its actions. 
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("War Game");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            startGame();
            primaryStage.close();
        });

        root.getChildren().addAll(new Label("Welcome to the game of War!"), startButton);

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
	}
	
	/*
	 * startGame shuffle the cards, and gives cards to each player to start the game.
	 * after all, it calls playRound()
	 */

	private void startGame() {
		rounds = 0;

	    // Reset decks
	    player1Cards.clear();
	    player2Cards.clear();
	    myDeckOfCards.shuffle();

	    // Distribute cards to players
	    int totalCards = myDeckOfCards.getSize();
	    int cardsPerPlayer = totalCards / 2;
	    for (int i = 0; i < cardsPerPlayer; i++) {
	        player1Cards.add(myDeckOfCards.dealCard());
	        player2Cards.add(myDeckOfCards.dealCard());
	    }

	    playRound();
	}
	
	/*
	 * playRound is controlling each round of the game, calculates and announce the winner of each round.
	 */
	
	private void playRound() {
		rounds++;

	    if (player1Cards.isEmpty() || player2Cards.isEmpty()) {
	        showGameOverDialog();
	        return; // Exit the method if one player has no cards left
	    }

	    Card player1Card = player1Cards.remove(0);
	    Card player2Card = player2Cards.remove(0);

	    String roundMessage = "Round " + rounds + "\n" +
	            "Player 1 plays: " + player1Card.toString() + "\n" +
	            "Player 2 plays: " + player2Card.toString();

	    // Compare cards
	    int comparison = compareCards(player1Card, player2Card);
	    String resultMessage;
	    if (comparison > 0) {
	        resultMessage = "Player 1 wins the round!";
	        player1Cards.add(player1Card);
	        player1Cards.add(player2Card);
	    } else if (comparison < 0) {
	        resultMessage = "Player 2 wins the round!";
	        player2Cards.add(player1Card);
	        player2Cards.add(player2Card);
	    } else {
	        resultMessage = "It's a tie!";
	        player1Cards.add(player1Card);
	        player2Cards.add(player2Card);
	    }

	    boolean gameIsOver = player1Cards.isEmpty() || player2Cards.isEmpty();
	    showDialog("Round Result", roundMessage + "\n" + resultMessage, gameIsOver);

	    // Check if the game is over
	    if (gameIsOver) {
	        showGameOverDialog();
	    } else {
	        playRound(); // Proceed to the next round automatically
	    }
	}
	
	private void showGameOverDialog() {
	    showDialog("Game Over", "Game over!", true);
	}
	
	/*
	 * showDialog is a generic functions to show messages on dialog
	 */
	private void showDialog(String title, String message, boolean isGameOver) {
		Stage dialogStage = new Stage();
	    dialogStage.initModality(Modality.APPLICATION_MODAL);
	    dialogStage.setTitle(title);
	    Label label = new Label(message);
	    Button closeButton = new Button("Next");
	    closeButton.setOnAction(e -> dialogStage.close());
	    VBox vbox = new VBox(new Text(message), closeButton);
	    if (!isGameOver) {
	        Button exitButton = new Button("Exit Game");
	        exitButton.setOnAction(e -> {
	            dialogStage.close();
	            // Exit the game here
	            System.exit(0);
	        });
	        vbox.getChildren().add(exitButton);
	    }
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(10);
	    Scene scene = new Scene(vbox, 300, 200);
	    dialogStage.setScene(scene);
	    dialogStage.showAndWait();
	}
	
	/*
	 * compareCards gets 2 cards and returns who wins
	 */
	private int compareCards(Card card1, Card card2) {
		// Compare cards based on face
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int face1 = java.util.Arrays.asList(faces).indexOf(card1.getFace());
        int face2 = java.util.Arrays.asList(faces).indexOf(card2.getFace());
        return Integer.compare(face1, face2);
    }
}
