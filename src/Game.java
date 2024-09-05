package src;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Deck {
	public Game(){
		
	}

	public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

	private String verifyWinner (int player_hand_value, int enemy_hand_value) {
		if (player_hand_value > 21 && enemy_hand_value > 21) {
			return "both_out_of_range";
		} else {
			if (player_hand_value > 21) {
				return "player_out_of_range";
			} else if (enemy_hand_value > 21) {
				return "enemy_out_of_range";
			} else if (player_hand_value == enemy_hand_value) {
				return "draw";
			} else if (player_hand_value < enemy_hand_value) {
				return "enemy_wins";
			} else {
				return "player_wins";
			}
		}
	}

	public void startGame(){
		int[] rounds = new int[]{ 1, 2, 3, 4, 5 };
		String[] enemies = new String[]{ "Dev Júnior", "Scrum Master", "Gustavo Guanabara", "Uncle Bob",  "Berry" };

		Boolean quit = false;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Bem vindo ao Blackberry! ");
			System.out.println("Informe o seu nome: ");
			String name = scanner.nextLine();
			Player player = new Player(name);
			clearScreen();

			while (true){
				Integer action;

				for (int i = 0; i < rounds.length; i++) {
					// Thread.sleep(1000);
					System.out.println("\n\nROUND "+(i+1));

					buildDeck();
					shuffleDeck();

					ArrayList<Card> cards_given = new ArrayList<>();
					cards_given.add(giveCard());
					cards_given.add(giveCard());
					Hand player_hand = new Hand(cards_given, player.getName());
					
					Enemy enemy = new Enemy(enemies[i]);
					ArrayList<Card> cards_enemy = new ArrayList<>();
					cards_enemy.add(giveCard());
					cards_enemy.add(giveCard());
					Hand enemy_hand = new Hand(cards_enemy, enemy.getName());

					System.out.println("Você enfrentará "+enemy.getName()+ "!");
					action = 0;
					// Thread.sleep(1000);
					
					while (true) {
						System.out.println("Sua mão: ");
						player_hand.printCards();
						System.out.println("Total: " + player_hand.getHandValue());

						String output = verifyWinner(player_hand.getHandValue(), enemy_hand.getHandValue());
						if ("both_out_of_range".equals(output)) {
							System.out.println("Mão de "+enemy.getName()+": ");
							enemy_hand.printCards();
							System.out.println("Total: " + enemy_hand.getHandValue());

							System.out.println("Vocês dois estouraram!");
							i -= 1;
							break;
						} 
						if ("player_out_of_range".equals(output)){
							System.out.println("Você estourou!");
							i = -1;
							enemy.addWin();
							break;
						}
						if ("enemy_out_of_range".equals(output)){
							System.out.println("Mão de "+enemy.getName()+": ");
							enemy_hand.printCards();
							System.out.println("Total: " + enemy_hand.getHandValue());

							System.out.println("O seu adversário estourou!");
							player.addWin();
							break;
						}

						if (action == 2){
							System.out.println("Mão de "+enemy.getName()+": ");
							enemy_hand.printCards();
							System.out.println("Total: " + enemy_hand.getHandValue());

							if ("draw".equals(output)) {
								System.out.println("Vocês empataram!");
								i -= 1;
							}
							if ("enemy_wins".equals(output)) {
								System.out.println("O adversário venceu!");
								i = -1;
								enemy.addWin();
							}
							if ("player_wins".equals(output)){
								System.out.println("Você venceu!");
								player.addWin();
							}

							break;
						}					
					
						System.out.println("O que deseja fazer? \n1 - Comprar carta\n2 - Passar a vez\n3 - Desistir");
						action = Integer.valueOf(scanner.nextLine());

						if (action == 1) {
							player_hand.addCard(giveCard());
							// Thread.sleep(1000);
						}
						if (action == 2) {
							while (true) {
								// Thread.sleep(1000);
								int enemy_action = enemy.decisionMaking(enemy_hand.getHandValue());
								System.out.println(enemy_action);
								if (enemy_action == 1) {
									System.out.println("Adversário comprou!");
									enemy_hand.addCard(giveCard());
								} else {
									System.out.println("Adversário passou a vez!");
									break;
								}
							}
						}
						if (action == 3) {
							quit = true;
							enemy.addWin();
							break;
						}
					}

					if (action == 3) {
						break;
					}
				}

				if (quit){
					break;
				} else {
					System.out.println("Parabéns, você zerou o jogo!");
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		System.out.println("Out of while");
		System.exit(0);
	}
}