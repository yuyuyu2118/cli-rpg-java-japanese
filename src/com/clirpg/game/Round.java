package src.com.clirpg.game;

import java.util.Random;
import java.util.Scanner;
import src.com.clirpg.characters.*;
import src.com.utils.ConsoleColors;

public class Round {

  private int level;
  private Player player;
  private Soldier roundSoldierArray[];
  private int currentSoldierNumber = 0;
  private Monster roundMonsterArray[];
  private int currentMonsterNumber = 0;

  public Round(int level) {
    this.level = level;
    this.roundSoldierArray = new Soldier[1000];
    this.roundMonsterArray = new Monster[1000];
  }

  public Round(int level, Player player) {
    this.level = level;
    this.player = player;
    this.roundSoldierArray = new Soldier[1000];
    this.roundMonsterArray = new Monster[1000];
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int startRound() {
    player.health = 100;
    Random random = new Random();
    //System.out.println("レベル " + this.getLevel() + " でラウンドを開始しました");
    // 敵を取得
    int soldierNumber = 0;
    int monsterNumber = 0;
    // 0（含む）から1（含まない）の間のランダムな数値を生成
    for (int i = this.getLevel(); i > 0; i--) {
      //System.out.println(i);
      // モンスターを無効にする変更
      int randomNumber = random.nextInt(2);
      //System.out.println(randomNumber);
      switch (randomNumber) {
        case 0:
          soldierNumber++;
          break;
        case 1:
          monsterNumber++;
          i -= 2;
          break;
      }
    }
    //System.out.println("残り = " + (this.getLevel() - (soldierNumber + (monsterNumber * 3))));
    //System.out.println("兵士の数 = " + soldierNumber);
    //System.out.println("モンスターの数 = " + monsterNumber);

    for (int i = soldierNumber; i > 0; i--) {
      //System.out.println(i);
      int randomNumber = random.nextInt(10);
      //System.out.println(randomNumber);
      switch (randomNumber) {
        case 0:
          appendSoldier(new Soldier("ビリー"));
          break;
        case 1:
          appendSoldier(new Soldier("ブライアン"));
          break;
        case 2:
          appendSoldier(new Soldier("ルク"));
          break;
        case 3:
          appendSoldier(new Soldier("マシュー"));
          break;
        case 4:
          appendSoldier(new Soldier("メアリー"));
          break;
        case 5:
          appendSoldier(new Soldier("アントワネット"));
          break;
        case 6:
          appendSoldier(new Soldier("ジェームズ"));
          break;
        case 7:
          appendSoldier(new Soldier("ライアン"));
          break;
        case 8:
          appendSoldier(new Soldier("ロバート"));
          break;
        case 9:
          appendSoldier(new Soldier("エドワード"));
          break;
      }
    }

    for (int i = monsterNumber; i > 0; i--) {
      //System.out.println(i);
      int randomNumber = random.nextInt(10);
      //System.out.println(randomNumber);
      switch (randomNumber) {
        case 0:
          appendMonster(new Monster("バルログ"));
          break;
        case 1:
          appendMonster(new Monster("バンシー"));
          break;
        case 2:
          appendMonster(new Monster("ロックジョー"));
          break;
        case 3:
          appendMonster(new Monster("ダイアモンドヘッド"));
          break;
        case 4:
          appendMonster(new Monster("オーガ"));
          break;
        case 5:
          appendMonster(new Monster("魔女"));
          break;
        case 6:
          appendMonster(new Monster("マンイーター"));
          break;
        case 7:
          appendMonster(new Monster("バジリスク"));
          break;
        case 8:
          appendMonster(new Monster("ゾンビ"));
          break;
        case 9:
          appendMonster(new Monster("ワーグ"));
          break;
      }
    }
    this.roundLoop();
    if (player.health >= 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public void roundLoop() {
    Scanner choiceReader = new Scanner(System.in);
    int enemiesRemain = 1;
    int playerAlive = 1;

    while ((enemiesRemain == 1) && (playerAlive == 1)) {
      System.out.println(
        ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET
      );
      System.out.println(this.toString());
      System.out.println(
        "「1」を入力して兵士を攻撃、または「2」を入力してモンスターを攻撃してください"
      );
      int choice = choiceReader.nextInt();

      // Check the value of 'choice' and perform actions accordingly
      switch (choice) {
        case 1:
          if (currentSoldierNumber <= 0) {
            break;
          }
          // Code to attack a soldier
          // For example: player.attackSoldier();
          //System.out.println("player chooses soldier");
          System.out.println(
            ConsoleColors.YELLOW_UNDERLINED +
            "0-" +
            (currentSoldierNumber - 1) +
            "の範囲で兵士を選んでください" +
            ConsoleColors.RESET
          );
          choice = choiceReader.nextInt();
          while ((choice > (currentSoldierNumber - 1)) || (choice < 0)) {
            System.out.println(
              ConsoleColors.RED_BOLD_BRIGHT +
              "無効な数値です。もう一度選んでください" +
              ConsoleColors.RESET
            );
            System.out.println(
              ConsoleColors.YELLOW_UNDERLINED +
              "0-" +
              (currentSoldierNumber - 1) +
              "の範囲で兵士を選んでください" +
              ConsoleColors.RESET
            );
            choice = choiceReader.nextInt();
          }
          System.out.println(
            ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET
          );
          System.out.println(
            "選択した兵士: " + roundSoldierArray[choice].toString()
          );
          roundSoldierArray[choice].setHealth(player.combat());
          break;
        case 2:
          if (currentMonsterNumber <= 0) {
            break;
          }
          // Code to attack a monster
          // For example: player.attackMonster();
          //System.out.println("player chooses Monster");
          System.out.println(
            ConsoleColors.YELLOW_UNDERLINED +
            "0-" +
            (currentMonsterNumber - 1) +
            "の範囲でモンスターを選んでください" +
            ConsoleColors.RESET
          );
          choice = choiceReader.nextInt();
          while ((choice > (currentMonsterNumber - 1)) || (choice < 0)) {
            System.out.println(
              ConsoleColors.RED_BOLD_BRIGHT +
              "無効な数値です。もう一度選んでください" +
              ConsoleColors.RESET
            );
            System.out.println(
              ConsoleColors.YELLOW_UNDERLINED +
              "0-" +
              (currentMonsterNumber - 1) +
              "の範囲でモンスターを選んでください" +
              ConsoleColors.RESET
            );
            choice = choiceReader.nextInt();
          }
          System.out.println(
            ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET
          );
          System.out.println(
            "選択したモンスター: " + roundMonsterArray[choice].toString()
          );
          roundMonsterArray[choice].setHealth(player.combat());
          break;
        default:
          System.out.println(
            ConsoleColors.RED_BOLD_BRIGHT +
            "無効な選択です。もう一度試してください。" +
            ConsoleColors.RESET
          );
          break;
      }
      enemiesRemain = checkAndUpdate();
      if (enemiesRemain == 1) {
        Random random = new Random();
        if (currentSoldierNumber > 0) {
          System.out.println(
            ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET
          );
          int randomNumber = random.nextInt(currentSoldierNumber);
          Soldier selectedSoldier = roundSoldierArray[randomNumber];
          player.health -= selectedSoldier.combat();
        }
        if (currentMonsterNumber > 0) {
          System.out.println(
            ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET
          );
          int randomNumber = random.nextInt(currentMonsterNumber);
          Monster selectedMonster = roundMonsterArray[randomNumber];
          player.health -= selectedMonster.combat();
        }
      }
      if (player.health < 0) {
        System.out.println(
          ConsoleColors.RED_BACKGROUND +
          "あなたは死にました！" +
          ConsoleColors.RESET
        );
        System.exit(0);
      }
    }
  }

  public int checkAndUpdate() {
    if (currentSoldierNumber > 0) {
      for (int i = 0; i < currentSoldierNumber; i++) {
        if (roundSoldierArray[i] != null) {
          if (roundSoldierArray[i].health <= 0) {
            System.out.println(
              ConsoleColors.RED_BACKGROUND +
              roundSoldierArray[i].name +
              "が死亡しました" +
              ConsoleColors.RESET
            );
            removeSoldier(i);
          }
        } else {
          System.out.println("兵士が失われました。エラー位置：" + i);
          return 1;
        }
      }
    }

package src.com.clirpg.game;

import java.util.Random;
import java.util.Scanner;
import src.com.clirpg.characters.*;
import src.com.utils.ConsoleColors;

public class Round {

  // ... (他のコード部分は省略)

  public int checkAndUpdate() {
    if (currentMonsterNumber > 0) {
      for (int i = 0; i < currentMonsterNumber; i++) {
        if (roundMonsterArray[i] != null) {
          if (roundMonsterArray[i].health <= 0) {
            System.out.println(
              ConsoleColors.RED_BACKGROUND +
              roundMonsterArray[i].name +
              "が死亡しました" +
              ConsoleColors.RESET
            );
            removeMonster(i);
          }
        } else {
          System.out.println("モンスターが失われました。エラー位置：" + i);
          return 1;
        }
      }
    }
    if ((currentMonsterNumber + currentSoldierNumber) == 0) {
      return 0;
    }
    return 1;
  }

  // ... (他のメソッドは省略)

  public void appendMonster(Monster newMonster) {
    if (currentMonsterNumber < roundMonsterArray.length) {
      roundMonsterArray[currentMonsterNumber] = newMonster;
      currentMonsterNumber++; // Increment the count of Monsters in the array
    } else {
      // Handle the case where the array is full and cannot append more Monsters
      System.out.println("配列がいっぱいです。これ以上モンスターを追加できません。");
    }
  }

  public void appendSoldier(Soldier newSoldier) {
    if (currentSoldierNumber < roundSoldierArray.length) {
      roundSoldierArray[currentSoldierNumber] = newSoldier;
      currentSoldierNumber++; // Increment the count of soldiers in the array
    } else {
      // Handle the case where the array is full and cannot append more soldiers
      System.out.println("配列がいっぱいです。これ以上兵士を追加できません。");
    }
  }

  public String toString() {
    String toPrint =
      (
        ConsoleColors.CYAN + this.player.toString() + "\n" + ConsoleColors.RESET
      );
    toPrint +=
      (ConsoleColors.GREEN_BACKGROUND + "兵士: " + ConsoleColors.RESET);
    for (int i = 0; i < currentSoldierNumber; i++) {
      if (roundSoldierArray[i] != null) {
        toPrint =
          toPrint + "[" + i + "]:" + roundSoldierArray[i].toString() + ", ";
      } else {
        //System.out.println("兵士が失われました");
      }
    }
    toPrint += "\n";
    toPrint +=
      (ConsoleColors.GREEN_BACKGROUND + "モンスター: " + ConsoleColors.RESET);
    for (int i = 0; i < currentMonsterNumber; i++) {
      if (roundMonsterArray[i] != null) {
        toPrint =
          toPrint + "[" + i + "]:" + roundMonsterArray[i].toString() + ", ";
      } else {
        //System.out.println("モンスターが失われました");
      }
    }
    return toPrint;
  }
}