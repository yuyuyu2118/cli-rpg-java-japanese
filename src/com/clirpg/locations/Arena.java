package src.com.clirpg.locations;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.com.clirpg.characters.Civillian;
import src.com.clirpg.characters.Player;
import src.com.clirpg.game.Round;
import src.com.utils.ConsoleColors;

public class Arena implements Visit {

  private Civillian trainer;
  private Player player;

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Arena() {
    trainer = new Civillian("Bertrand", "トレーナー");
  }

  public void visit() {
    System.out.println("\n");
    System.out.println(
      ConsoleColors.PURPLE_BOLD +
      trainer.toString() +
      "：アリーナへようこそ！" +
      ConsoleColors.RESET
    );

    System.out.println(
      ConsoleColors.PURPLE +
      trainer.toString() +
      "：何レベルから始めますか？" +
      ConsoleColors.RESET
    );

    int levelArena = getUserChoice();

    // パワーレベル：levelArenaで戦いを開始

    Round round1 = new Round(levelArena, player);
    if (round1.startRound() == 0) {
      return;
    }
    String contFight = "y";

    Scanner contScan = new Scanner(System.in);
    while (contFight.equals("y")) {
      System.out.println(
        "\n" +
        ConsoleColors.PURPLE +
        trainer.toString() +
        "：いい戦いでした！続けて戦いますか？" +
        ConsoleColors.RESET
      );
      System.out.println(
        "続ける場合はyを、そうでない場合はnを入力してください"
      );
      contFight = contScan.nextLine();

      switch (contFight) {
        case "y":
          levelArena++;
          System.out.println(
            ConsoleColors.PURPLE +
            trainer.toString() +
            "：アリーナレベル" +
            levelArena +
            "で戦いが続きます" +
            ConsoleColors.RESET
          );
          round1 = new Round(levelArena, player);
          if (round1.startRound() == 0) {
            return;
          }
          break;
        case "n":
          contFight = "n";
          System.out.println(
            ConsoleColors.PURPLE_BOLD +
            trainer.toString() +
            "：素晴らしい戦いでした。またアリーナでお会いしましょう。" +
            ConsoleColors.RESET
          );
          if (levelArena > player.maxLevelArena) {
            player.maxLevelArena = levelArena;
          }
          break;
        default:
          System.out.println(
            ConsoleColors.RED_BOLD_BRIGHT +
            "有効な選択肢ではありません。もう一度試してください。" +
            ConsoleColors.RESET
          );
          contFight = "y";
      }
    }
    return;
  }

  private int getUserChoice() {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.print("選択肢を入力してください: ");
      return scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println(
        ConsoleColors.RED_BOLD_BRIGHT +
        "有効な選択肢ではありません。レベル0から開始します。" +
        ConsoleColors.RESET
      );
      return 0; // デフォルトのスイッチケースをトリガーするために0を返す
    }
  }
}
