package src.com.clirpg.game;

import java.util.Scanner;
import src.com.clirpg.characters.CharacterClass;
import src.com.clirpg.characters.Player;
import src.com.utils.ConsoleColors;

public class CharacterCreator {

   public Player createCharacter() {
        System.out.println("キャラクター作成:");

        Scanner scanner = new Scanner(System.in);

        System.out.print("キャラクターの名前を入力してください: ");
        String playerName = scanner.nextLine();

        CharacterClass characterClass = chooseCharacterClass();
        Player player = new Player(playerName, characterClass);

        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + characterClass + "というクラスの" + playerName + "を作成しました" + ConsoleColors.RESET);

        return player;
    }

    private CharacterClass chooseCharacterClass() {
        System.out.println("キャラクタークラスを選択してください:");

        for (CharacterClass characterClass : CharacterClass.values()) {
            var optionNumber = characterClass.ordinal() + 1;
            System.out.println (ConsoleColors.GREEN_BACKGROUND + optionNumber+ ". " + characterClass + ConsoleColors.RESET);
            System.out.println("   体力: " + characterClass.health);
            System.out.println("   攻撃力: " + characterClass.attackStrength);
            System.out.println("   命中率: " + characterClass.hitProbability);
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("選んだクラスの番号を入力してください: ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= CharacterClass.values().length) {
                    return CharacterClass.values()[choice - 1];
                } else {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な選択です。有効なクラス番号を入力してください。" + ConsoleColors.RESET);
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な入力です。数値を入力してください。" + ConsoleColors.RESET);
                scanner.next(); 
            }
        }
    }
}