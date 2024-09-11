import java.util.Scanner;

public class Game extends Deck {
	private Scanner scanner = new Scanner(System.in);

	public enum GameResult {
		BOTH_OUT_OF_RANGE,
		PLAYER_OUT_OF_RANGE,
		ENEMY_OUT_OF_RANGE,
		DRAW,
		ENEMY_WINS,
		PLAYER_WINS,
		PLAYER_QUIT
	}

	public Game() {

	}

	// private GameResult verifyWinner(int playerHandValue, int enemyHandValue) {
	// 	if (playerHandValue > 21 && enemyHandValue > 21) {
	// 		return GameResult.BOTH_OUT_OF_RANGE;
	// 	} else if (playerHandValue > 21) {
	// 		return GameResult.PLAYER_OUT_OF_RANGE;
	// 	} else if (enemyHandValue > 21) {
	// 		return GameResult.ENEMY_OUT_OF_RANGE;
	// 	} else if (playerHandValue == enemyHandValue) {
	// 		return GameResult.DRAW;
	// 	} else {
	// 		return (playerHandValue > enemyHandValue) ? GameResult.PLAYER_WINS : GameResult.ENEMY_WINS;
	// 	}
	// }

	// public Hand buildHand(Player player) {
	// 	Hand hand = new Hand(player.getName());
	// 	hand.addCard(giveCard());
	// 	hand.addCard(giveCard());
	// 	return hand;
	// }

	// public Player.GameAction playerTurn(Player player, Hand playerHand) {
	// 	while (true) {
	// 		System.out.println("Sua mão: ");
	// 		playerHand.printCards();
	// 		System.out.println("Total: " + playerHand.getHandValue());

	// 		System.out.println("O que deseja fazer? \n1 - Comprar carta\n2 - Passar a vez\n3 - Desistir");
	// 		int action = Integer.parseInt(scanner.nextLine());

	// 		if (action == 1) {
	// 			playerHand.addCard(giveCard());
	// 		} else if (action == 2) {
	// 			return Player.GameAction.PASS;
	// 		} else if (action == 3) {
	// 			return Player.GameAction.QUIT;
	// 		}
	// 	}
	// }

	// public void enemyTurn(Enemy enemy, Hand enemyHand) {
	// 	while (true) {
	// 		Enemy.GameAction decision = enemy.decisionMaking(enemyHand.getHandValue());

	// 		if (decision == Enemy.GameAction.BUY) {
	// 			enemyHand.addCard(giveCard());
	// 		} else if (decision == Enemy.GameAction.PASS) {
	// 			break;
	// 		}
	// 	}
	// }

	// public GameResult runRound(Player player, Enemy enemy) {
	// 	buildDeck();
	// 	shuffleDeck();

	// 	Hand playerHand = buildHand(player);
	// 	Hand enemyHand = buildHand(enemy);

	// 	System.out.println("Você enfrentará " + enemy.getName() + "!");

	// 	Player.GameAction action = playerTurn(player, playerHand);

	// 	if (action == Player.GameAction.QUIT) {
	// 		return GameResult.PLAYER_QUIT;
	// 	}

	// 	enemyTurn(enemy, enemyHand);
	// 	System.out.println("Mão do oponente: ");
	// 	enemyHand.printCards();
	// 	System.out.println("Total: " + enemyHand.getHandValue());

	// 	return verifyWinner(playerHand.getHandValue(), enemyHand.getHandValue());
	// }

	// public void startGame() {
	// 	System.out.println("Bem-vindo ao jogo de Blackjack!");
	// 	System.out.println("Informe o seu nome: ");
	// 	String name = scanner.nextLine();
	// 	Player player = new Player(name);

	// 	for (int round = 0; round < this.enemies.size(); round++) {
	// 		GameResult result = runRound(player, this.enemies.get(round));

	// 		if (result == GameResult.BOTH_OUT_OF_RANGE) {
	// 			System.out.println("Ambos estouraram.");
	// 			round--;
	// 		} else if (result == GameResult.DRAW) {
	// 			System.out.println("Empate.");
	// 			round--;
	// 		} else if (result == GameResult.ENEMY_OUT_OF_RANGE) {
	// 			player.addWin();
	// 			System.out.println("Oponente estourou.");
	// 		} else if (result == GameResult.PLAYER_OUT_OF_RANGE) {
	// 			System.out.println("Você estourou.");
	// 			break;
	// 		} else if (result == GameResult.ENEMY_WINS) {
	// 			System.out.println("Oponente venceu.");
	// 			break;
	// 		} else if (result == GameResult.PLAYER_WINS) {
	// 			player.addWin();
	// 			System.out.println("Você venceu!");
	// 		} else if (result == GameResult.PLAYER_QUIT) {
	// 			System.out.println("Você desistiu.");
	// 			break;
	// 		}
	// 	}
	// }
}
