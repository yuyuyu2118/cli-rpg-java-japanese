package src.com.clirpg.locations;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.com.clirpg.characters.*;
import src.com.utils.ConsoleColors;

public class Shop implements Visit {

  private Shopkeeper shopkeeper;
  private Player player;

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Shop() {
    shopkeeper = new Shopkeeper("Jack");
  }

  public void visit() {
    shopkeeper.talk();

    Scanner shopScan = new Scanner(System.in);
    boolean shopBool = true;

    while (shopBool) {
      printShop();
      System.out.println("所持金: " + player.money);
      System.out.println("購入したいアップグレードの番号を入力してください");
      int choice = getUserChoice();

      switch (choice) {
        case 1:
          if (player.money >= 10) {
            player.attackStrength += 1;
            player.money -= 10;
            System.out.println("現在の攻撃力: " + player.attackStrength);
          } else {
            System.out.println(
              ConsoleColors.CYAN +
              "\n" +
              shopkeeper.toString() +
              ": 申し訳ありませんが、お金が足りないためこれを買うことができません。" +
              ConsoleColors.RESET
            );
          }
          break;
        case 2:
          if (player.money >= 10) {
            player.hitProbability += 5;
            player.money -= 15;
            System.out.println("現在の命中率: " + player.hitProbability);
          } else {
            System.out.println(
              ConsoleColors.CYAN +
              "\n" +
              shopkeeper.toString() +
              ": 申し訳ありませんが、お金が足りないためこれを買うことができません。" +
              ConsoleColors.RESET
            );
          }
          break;
        case 3:
          if (player.money >= 20) {
            player.health += 5;
            player.money -= 20;
            System.out.println("現在の体力: " + player.health);
          } else {
            System.out.println(
              ConsoleColors.CYAN +
              "\n" +
              shopkeeper.toString() +
              ": 申し訳ありませんが、お金が足りないためこれを買うことができません。" +
              ConsoleColors.RESET
            );
          }
          break;
        case 4:
          shopBool = false;
          break;
        default:
          System.out.println(
            ConsoleColors.RED_BOLD_BRIGHT +
            "無効な選択です。もう一度試してください。" +
            ConsoleColors.RESET
          );
      }

      shopkeeper.talkThanks();
    }

    shopkeeper.talkEnd();
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

  private void printShop() {
    System.out.println(
      ConsoleColors.CYAN +
      "\n" +
      shopkeeper.toString() +
      ": 選択できるオプションはこちらです:" +
      ConsoleColors.RESET
    );
    System.out.println("1. 攻撃力 +1、コスト 10");
    System.out.println("2. 命中確率 +5%、コスト 15");
    System.out.println("3. 体力 +5、コスト 20");
    System.out.println("4. 退出");
    return;
  }
}
