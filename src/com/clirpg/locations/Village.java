package src.com.clirpg.locations;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import src.com.clirpg.characters.*;
import src.com.clirpg.game.Quest;
import src.com.clirpg.game.Round;
import src.com.utils.ConsoleColors;

public class Village implements Visit {

  private Civillian civillian;
  private Civillian mayor;
  private Quest quest;
  private Player player;

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Village(Quest quest) {
    mayor = new Civillian("Thomas", "村長");
    this.quest = quest;
  }

  public void visit() {
    System.out.println(
      ConsoleColors.GREEN_BOLD +
      "\n" +
      mayor.toString() +
      "：村へようこそ！" +
      ConsoleColors.RESET
    );

    boolean villageBool = true;
    Scanner villageScan = new Scanner(System.in);

    while (villageBool) {
      printMayor();
      System.out.println("選びたい番号を入力してください");
      int choice = getUserChoice();

      switch (choice) {
        case 1:
          quest.showQuests();
          break;
        case 2:
          fightingQuest();
          break;
        case 3:
          villageBool = false;
          break;
        default:
          System.out.println(
            ConsoleColors.RED_BOLD_BRIGHT +
            "無効な選択です。もう一度試してください。" +
            ConsoleColors.RESET
          );
      }
    }

    System.out.println(
      ConsoleColors.GREEN_BOLD +
      "\n" +
      mayor.toString() +
      "：ご来訪ありがとうございました。またのお越しをお待ちしております。" +
      ConsoleColors.RESET
    );
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

  private String getCivillianName() {
    String name;

    int random = (int) (Math.random() * 5);

    switch (random) {
      case 0:
        name = "William";
        break;
      case 1:
        name = "Arthur";
        break;
      case 2:
        name = "Lillian";
        break;
      case 3:
        name = "Alfred";
        break;
      case 4:
        name = "Audrey";
        break;
      case 5:
        name = "Edward";
        break;
      default:
        name = "Edith";
    }

    return name;
  }

  private String getCivillianJob() {
    String job;

    int random = (int) (Math.random() * 5);

    switch (random) {
      case 0:
        job = "農夫";
        break;
      case 1:
        job = "肉屋";
        break;
      case 2:
        job = "大工";
        break;
      case 3:
        job = "パン屋";
        break;
      case 4:
        job = "農夫";
        break;
      case 5:
        job = "織物職人";
        break;
      default:
        job = "鎧職人";
    }

    return job;
  }

  private void fightingQuest() {
    civillian = new Civillian(getCivillianName(), getCivillianJob());
    String villageFight = "y";

    while (villageFight.equals("y")) {
      System.out.println(
        ConsoleColors.GREEN +
        "\n" +
        mayor.toString() +
        "：戦闘のクエストを持っている村人を見つけました。" +
        ConsoleColors.RESET
      );

      civillian.talk();

      Scanner villageScan = new Scanner(System.in);

      System.out.println(
        "続ける場合はyを、そうでない場合はnを入力してください"
      );
      villageFight = villageScan.nextLine();

      switch (villageFight) {
        case "y":
          System.out.println("戦闘が始まります");
          Round round1 = new Round(getEnemyLevel(), player);
          if (round1.startRound() == 0) {
            return;
          }
          player.money += 10;
          civillian.talkEnd();
          System.out.println("所持金: " + player.money);
          civillian = new Civillian(getCivillianName(), getCivillianJob());
          civillian = new Civillian(getCivillianName(), getCivillianJob());
          break;
        case "n":
          villageFight = "n";
          break;
        default:
          System.out.println(
            ConsoleColors.RED_BOLD_BRIGHT +
            "有効な選択肢ではありません。もう一度試してください。" +
            ConsoleColors.RESET
          );
          villageFight = "y";
      }
    }
  }

  private void printMayor() {
    System.out.println(
      ConsoleColors.GREEN +
      "\n" +
      mayor.toString() +
      "：この村では一般的なクエストと戦闘クエストを用意しています。" +
      ConsoleColors.RESET
    );
    System.out.println(
      ConsoleColors.GREEN +
      "\n" +
      mayor.toString() +
      "：選択できるオプションはこちらです：" +
      ConsoleColors.RESET
    );
    System.out.println("1. 一般的なクエスト");
    System.out.println("2. 戦闘クエスト");
    System.out.println("3. 退出");
  }

  private int getEnemyLevel() {
    Random random = new Random();
    if (player.maxLevelArena < 3) {
      return 1;
    } else if (player.maxLevelArena < 5) {
      int randomNumber = random.nextInt(2);
      return player.maxLevelArena - randomNumber;
    } else {
      int randomNumber = random.nextInt(5);
      return player.maxLevelArena - randomNumber;
    }
  }
}
