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
    

    public Round(int level)
    {
        this.level = level;
        this.roundSoldierArray = new Soldier [1000];
        this.roundMonsterArray = new Monster [1000];
    }

    public Round(int level, Player player)
    {
        this.level = level;
        this.player = player;
        this.roundSoldierArray = new Soldier [1000];
        this.roundMonsterArray = new Monster [1000];
    }

    public int getLevel()
    {
        return this.level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int startRound()
    {
        player.health = 100;
        Random random = new Random();
        //System.out.println("started round at level: " + this.getLevel());
        // Get enemies
        int soldierNumber = 0;
        int monsterNumber = 0;
        // Generate a random double between 0 (inclusive) and 1 (exclusive)
        for (int i = this.getLevel(); i > 0; i--) {
            //System.out.println(i);
            // chane do disable monsters
            int randomNumber = random.nextInt(2);
            //System.out.println(randomNumber);
            switch (randomNumber) {
                case 0:
                    soldierNumber++;
                    break;
                case 1:
                    monsterNumber++;
                    i-=2;
                    break;
            }
        }
        //System.out.println("remainder = " + (this.getLevel() - (soldierNumber + (monsterNumber * 3))));
        //System.out.println("soldier number = " + soldierNumber);
        //System.out.println("monster number = " + monsterNumber);

        for (int i = soldierNumber; i > 0; i--) {
            //System.out.println(i);
            int randomNumber = random.nextInt(10);
            //System.out.println(randomNumber);
            switch (randomNumber) {
                case 0:
                    appendSoldier(new Soldier("Billy"));
                    break;
                case 1:
                    appendSoldier(new Soldier("Brian"));
                    break;
                case 2:
                    appendSoldier(new Soldier("Luc"));
                    break;
                case 3:
                    appendSoldier(new Soldier("Matthew"));
                    break;
                case 4:
                    appendSoldier(new Soldier("Mary"));
                    break;
                case 5:
                    appendSoldier(new Soldier("Antoinette"));
                    break;
                case 6:
                    appendSoldier(new Soldier("James"));
                    break;
                case 7:
                    appendSoldier(new Soldier("Ryan"));
                    break;
                case 8:
                    appendSoldier(new Soldier("Robert"));
                    break;
                case 9:
                    appendSoldier(new Soldier("Edward"));
                    break;
            }
        }

        for (int i = monsterNumber; i > 0; i--) {
            //System.out.println(i);
            int randomNumber = random.nextInt(10);
            //System.out.println(randomNumber);
            switch (randomNumber) {
                case 0:
                    appendMonster(new Monster("Balrog"));
                    break;
                case 1:
                    appendMonster(new Monster("Banshee"));
                    break;
                case 2:
                    appendMonster(new Monster("Lockjaw"));
                    break;
                case 3:
                    appendMonster(new Monster("DiamondHead"));
                    break;
                case 4:
                    appendMonster(new Monster("Ogre"));
                    break;
                case 5:
                    appendMonster(new Monster("Witch"));
                    break;
                case 6:
                    appendMonster(new Monster("ManEater"));
                    break;
                case 7:
                    appendMonster(new Monster("Basilisk"));
                    break;
                case 8:
                    appendMonster(new Monster("Zombie"));
                    break;
                case 9:
                    appendMonster(new Monster("Warg"));
                    break;                                                            
            }
        }
        this.roundLoop();
        if(player.health >= 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void roundLoop() {
        Scanner choiceReader = new Scanner(System.in);
        int enemiesRemain = 1;
        int playerAlive = 1;

        while ((enemiesRemain == 1) && (playerAlive == 1))
        {
            System.out.println(ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET);
            System.out.println(this.toString());
            System.out.println("「1」を入力して兵士に攻撃、「2」を入力してモンスターに攻撃");
            int choice = choiceReader.nextInt();
    
            // 'choice' の値を確認し、それに応じて行動を実行
            switch (choice) {
                case 1:
                    if(currentSoldierNumber <= 0)
                    {
                        break;
                    }
                    // 兵士に攻撃するコード
                    // 例：player.attackSoldier();
                    //System.out.println("プレイヤーは兵士を選択");
                    System.out.println(ConsoleColors.YELLOW_UNDERLINED + "0-" + (currentSoldierNumber - 1) + "の範囲で兵士を選んでください" + ConsoleColors.RESET);
                    choice = choiceReader.nextInt();
                    while ((choice > (currentSoldierNumber - 1)) || (choice < 0))
                    {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な数値です。もう一度選んでください" + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.YELLOW_UNDERLINED + "0-" + (currentSoldierNumber - 1) + "の範囲で兵士を選んでください" + ConsoleColors.RESET);
                        choice = choiceReader.nextInt();
                    }
                    System.out.println(ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET);
                    System.out.println("選択された兵士: " + roundSoldierArray[choice].toString());
                    roundSoldierArray[choice].setHealth(player.combat());
                    break;
                case 2:
                    if(currentMonsterNumber <= 0)
                    {
                        break;
                    }
                    // モンスターに攻撃するコード
                    // 例：player.attackMonster();
                    //System.out.println("プレイヤーはモンスターを選択");
                    System.out.println(ConsoleColors.YELLOW_UNDERLINED + "0-" + (currentMonsterNumber - 1) + "の範囲でモンスターを選んでください" + ConsoleColors.RESET);
                    choice = choiceReader.nextInt();
                    while ((choice > (currentMonsterNumber - 1)) || (choice < 0))
                    {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な数値です。もう一度選んでください" + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.YELLOW_UNDERLINED + "0-" + (currentMonsterNumber - 1) + "の範囲でモンスターを選んでください" + ConsoleColors.RESET);
                        choice = choiceReader.nextInt();
                    }
                    System.out.println(ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET);
                    System.out.println("選択されたモンスター: " + roundMonsterArray[choice].toString());
                    roundMonsterArray[choice].setHealth(player.combat());
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "無効な選択です。もう一度試してください。" + ConsoleColors.RESET);
                    break;
            }
            enemiesRemain = checkAndUpdate();
            if (enemiesRemain == 1)
            {
                Random random = new Random();
                if (currentSoldierNumber > 0)
                {
                    System.out.println(ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET);
                    int randomNumber = random.nextInt(currentSoldierNumber);
                    Soldier selectedSoldier = roundSoldierArray[randomNumber];
                    player.health -= selectedSoldier.combat();
                }
                if (currentMonsterNumber > 0)
                {
                    System.out.println(ConsoleColors.RED + "~~~~~~~~~~~~" + ConsoleColors.RESET);
                    int randomNumber = random.nextInt(currentMonsterNumber);
                    Monster selectedMonster = roundMonsterArray[randomNumber];
                    player.health -= selectedMonster.combat();
                }
            }
            if (player.health < 0)
            {
                System.out.println(ConsoleColors.RED_BACKGROUND + "あなたは死亡しました！" + ConsoleColors.RESET);
                System.exit(0);
            }
        }
    }
    
    public int checkAndUpdate()
    {
        if (currentSoldierNumber > 0)
        {
            for (int i = 0; i < currentSoldierNumber; i++)
            {
                if (roundSoldierArray[i] != null)
                {
                    if (roundSoldierArray[i].health <= 0)
                    {
                        System.out.println(ConsoleColors.RED_BACKGROUND + roundSoldierArray[i].name + "が死亡しました" + ConsoleColors.RESET);
                        removeSoldier(i);
                    }
                }
                else
                {
                    System.out.println("兵士が見失われました。エラー位置：" + i);
                    return 1;
                }
            }
        }

        if (currentMonsterNumber > 0)
        {
            for (int i = 0; i < currentMonsterNumber; i++)
            {
                if (roundMonsterArray[i] != null)
                {
                    if (roundMonsterArray[i].health <= 0)
                    {

                        System.out.println(ConsoleColors.RED_BACKGROUND + roundMonsterArray[i].name + "が死亡しました" + ConsoleColors.RESET);

                        removeMonster(i);
                    }
                }
                else
                {
                    System.out.println("モンスターが見失われました。エラー位置：" + i);
                    return 1;
                }
            }
        }
        if ((currentMonsterNumber + currentSoldierNumber) == 0)
        {
            return 0;
        }
        return 1;
    }

    public void removeSoldier(int toRemove)
    {
        Soldier[] tmpRoundSoldierArray = roundSoldierArray;
        int tmpSoldierNumber = currentSoldierNumber;
        //System.out.println("target to remove " + tmpRoundSoldierArray[toRemove]);
        this.roundSoldierArray = new Soldier [1000];
        this.currentSoldierNumber = 0;
        for (int i = 0; (i < tmpSoldierNumber); i++)
        {
            //System.out.println("adding: " + tmpRoundSoldierArray[i]);
            if(i != toRemove)
            {
                appendSoldier(tmpRoundSoldierArray[i]);
            }
        }
    }

    public void removeMonster(int toRemove)
    {
        Monster[] tmpRoundMonsterArray = roundMonsterArray;
        int tmpMonsterNumber = currentMonsterNumber;
        //System.out.println("target to remove " + tmpRoundMonsterArray[toRemove]);
        this.roundMonsterArray = new Monster [1000];
        this.currentMonsterNumber = 0;
        for (int i = 0; (i < tmpMonsterNumber); i++)
        {
            //System.out.println("adding: " + tmpRoundMonsterArray[i]);
            if(i != toRemove)
            {
                appendMonster(tmpRoundMonsterArray[i]);
            }
        }
    }

    public void appendMonster(Monster newMonster) {
        if (currentMonsterNumber < roundMonsterArray.length) {
            roundMonsterArray[currentMonsterNumber] = newMonster;
            currentMonsterNumber++; // Increment the count of Monsters in the array
        } else {
            // Handle the case where the array is full and cannot append more Monsters
            System.out.println("配列がいっぱいです。モンスターを追加できません。");
        }
    }

    public void appendSoldier(Soldier newSoldier) {
        if (currentSoldierNumber < roundSoldierArray.length) {
            roundSoldierArray[currentSoldierNumber] = newSoldier;
            currentSoldierNumber++; // Increment the count of soldiers in the array
        } else {
            // Handle the case where the array is full and cannot append more soldiers
            System.out.println("配列がいっぱいです。兵士を追加できません。");
        }
    }

    public String toString() {
        String toPrint = (ConsoleColors.CYAN + this.player.toString() + "\n" + ConsoleColors.RESET);
        toPrint += (ConsoleColors.GREEN_BACKGROUND + "兵士: " + ConsoleColors.RESET);
        for (int i = 0; i < currentSoldierNumber; i++) {
            if (roundSoldierArray[i] != null) {
                toPrint = toPrint + "[" + i + "]:" + roundSoldierArray[i].toString() + ", ";
            }
            else
            {
                //System.out.println("soldier lost");
            }
        }
        toPrint += "\n";
        toPrint += (ConsoleColors.GREEN_BACKGROUND + "モンスター: " + ConsoleColors.RESET);
        for (int i = 0; i < currentMonsterNumber; i++) {
            if (roundMonsterArray[i] != null) {
                toPrint = toPrint + "[" + i + "]:" + roundMonsterArray[i].toString() + ", ";
            }
            else
            {
                //System.out.println("monster lost");
            }
        }
        return toPrint;
    }
    
}