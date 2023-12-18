package src.com.clirpg.game;

import src.com.clirpg.characters.Player;
import src.com.clirpg.locations.*;
import src.com.utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gameplay {
    private Player player;
    public void setPlayer(Player player) {
        this.player = player;
    }

    private Arena arena;
    private Shop shop;
    private Village village;
    private boolean gameOngoing;
    private Quest quest;

    public Gameplay() {
        arena = new Arena();
        shop = new Shop();
        quest = new Quest();
        village = new Village(quest);  
    }

    public void openGameWorld(Player player) {
        gameOngoing = true;
        quest.setPlayer(player);
        System.out.println("\n広大なゲームの世界に自分を見つけます。");

        while (gameOngoing) {
            displayExplorationMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    arena.setPlayer(player);
                    arena.visit();
                    break;
                case 2:
                    shop.setPlayer(player);
                    shop.visit();
                    break;
                case 3:
                    village.setPlayer(player);
                    village.visit();
                    break;
                case 4:
                    displayCharacterStatus();
                    break;
                case 5:
                    System.out.println(ConsoleColors.RED + "メインメニューに戻ります。" + ConsoleColors.RESET);
                    gameOngoing = false;
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な選択です。もう一度試してください。" + ConsoleColors.RESET);
            }
        }
    }

    private int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("選択肢を入力してください: ");
            return scanner.nextInt(); 
        } catch (InputMismatchException e) {
            return 0; // 0を入力してデフォルトのスイッチケースをトリガーする
        }
    }

    private void displayExplorationMenu() {
        System.out.println("\n何をしますか？");
        System.out.println("1. アリーナを訪れる");
        System.out.println("2. 店に行く");
        System.out.println("3. 近くの村を探検する");
        System.out.println("4. キャラクターの状態を表示する");
        System.out.println("5. メインメニューに戻る");
    }

    private void displayCharacterStatus() {
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nキャラクターの状態:" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "名前: " + player.name + ConsoleColors.RESET) ;
        System.out.println(ConsoleColors.BLUE + "体力: " + player.health + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "攻撃力: " + player.attackStrength + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "命中率: " + player.hitProbability + ConsoleColors.RESET);
    }
}