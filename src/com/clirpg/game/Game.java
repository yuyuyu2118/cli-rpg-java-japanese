package src.com.clirpg.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import src.com.clirpg.characters.Player;
import src.com.utils.ConsoleColors;

public class Game {
    private Player player;
    private CharacterCreator characterCreator;
    private Gameplay gameplay;
    private boolean closeGame;

    public Game() {
        gameplay = new Gameplay();
        characterCreator = new CharacterCreator();
    }

    public void start() {
        System.out.println(ConsoleColors.GREEN_BACKGROUND + "テキストRPGゲームへようこそ！" + ConsoleColors.RESET);
        
        closeGame = false;
        while (!closeGame) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    if (player == null) {
                        player = loadCharacter();
                        if (player != null) {
                            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "セーブデータでゲームを開始します" + ConsoleColors.RESET);
                        } else {
                            player = characterCreator.createCharacter();
                        }
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "すでにアクティブなゲームがあります" + ConsoleColors.RESET);
                    }
                    gameplay.setPlayer(player);
                    gameplay.openGameWorld(player);
                    break;
                case 2:
                    if (player != null) {
                        saveCharacter(player);
                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "キャラクターの進行状況を保存しました。" + ConsoleColors.RESET);
                        player = loadCharacter();
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "保存するキャラクターがいません。先にキャラクターを作成してください。" + ConsoleColors.RESET);
                    }
                    break;
                case 3:
                    System.out.println("さようなら！");
                    closeGame = true;
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な選択です。もう一度試してください。" + ConsoleColors.RESET);
            }
        }
    }

     private void displayMainMenu() {
        System.out.println("\nメインメニュー:");
        System.out.println("1. ゲームを始める");
        System.out.println("2. ゲームを保存する"); 
        System.out.println("3. ゲームを終了する");
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

    private Player loadCharacter() {
        File characterFile = new File("savedCharacter.dat");

        if (characterFile.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(characterFile))) {
                return (Player) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    private void saveCharacter(Player player) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("savedCharacter.dat")))) {
            oos.writeObject(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}