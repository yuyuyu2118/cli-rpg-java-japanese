package src.com.clirpg.game;

import src.com.clirpg.characters.Civillian;
import src.com.clirpg.characters.Player;
import src.com.utils.ConsoleColors;

public class Quest {

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
        if(healthQuest < 60 ){
            healthQuest = player.health + 20;
        }
        if(strengthQuest < 10){
            strengthQuest = player.attackStrength + 10;
        }
    }

    private int levelArenaQuest;
    private int healthQuest;
    private int strengthQuest;
    private Civillian mayor;

    public Quest() {
        levelArenaQuest = 5;
        healthQuest = 0;
        strengthQuest = 0;
        mayor = new Civillian("Thomas", "市長");

    }
    
    public void showQuests(){

        checkQuests();
        printQuests();
        System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：これらのクエストのいずれかを終えたら、次回また来てください\n" + ConsoleColors.RESET);
        

    }

    private void printQuests(){
        System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：これがあなたの一般的なクエストです：" + ConsoleColors.RESET);
        System.out.println("\nアリーナレベル " + levelArenaQuest + " に到達する");
        System.out.println("\n体力を " + healthQuest + " までアップグレードする");
        System.out.println("\n攻撃力を " + strengthQuest + " までアップグレードする");
        System.out.println("\nクエストを達成すると20コインを獲得します。");
        System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：クエストを終えたら、報酬を受け取りに戻ってきてください。" + ConsoleColors.RESET);
    }

    private void checkQuests(){

        if(player.maxLevelArena >= levelArenaQuest){
            System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：おめでとうございます！アリーナレベル " + levelArenaQuest + " 達成のクエストを完了しました" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + mayor.toString() + "：こちらがあなたの報酬です！" + ConsoleColors.RESET);
            player.money += 20;
            System.out.println("お金: " + player.money);
            levelArenaQuest += 5;
            System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：次の目標アリーナレベルは " + levelArenaQuest + " です" + ConsoleColors.RESET);
        }
        if(player.health >= healthQuest){
            System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：おめでとうございます！体力 " + healthQuest + " アップグレードのクエストを完了しました" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + mayor.toString() + "：こちらがあなたの報酬です：" + ConsoleColors.RESET);
            player.money += 20;
            System.out.println("お金: " + player.money);
            healthQuest += 20;
            System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：次のタスクのための必要体力は " + healthQuest + " です" + ConsoleColors.RESET);
        }
        if(player.attackStrength >= strengthQuest){
            System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：おめでとうございます！攻撃力 " + strengthQuest + " アップグレードのクエストを完了しました" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + mayor.toString() + "：こちらがあなたの報酬です：" + ConsoleColors.RESET);
            player.money += 20;
            System.out.println("お金: " + player.money);
            strengthQuest += 20;
            System.out.println(ConsoleColors.GREEN + "\n" + mayor.toString() + "：次のタスクのための必要攻撃力は " + strengthQuest + " です" + ConsoleColors.RESET);
       
        }


    }
}
