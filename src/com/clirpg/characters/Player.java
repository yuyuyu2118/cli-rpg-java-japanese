package src.com.clirpg.characters;

import java.util.Random;

public class Player extends Entity implements Combat {
    public int attackStrength;
    public int hitProbability;
    public int money;
    public int maxLevelArena;

    final boolean friendly = true;
    
    public Player(String name, CharacterClass characterClass)
    {
        super(name);
        money = 100;
        maxLevelArena = 0;
        this.health = characterClass.health;
        this.attackStrength = characterClass.attackStrength;
        this.hitProbability = characterClass.hitProbability;   
    }

    @Override
    public String toString() {
        return "名前: " + this.name + ", 体力: " + health + ", 攻撃力: " + attackStrength + ", 命中確率: " + hitProbability + ", お金: " + money;
    }

    public int combat()
    {
        int randomNumber;
        Random random = new Random();
        randomNumber = random.nextInt(100);
        //System.out.println("乱数: " + randomNumber);
        if(randomNumber < this.hitProbability)
        {
            System.out.println("攻撃がヒットし、" + this.attackStrength + "のダメージを与えた");
            return this.attackStrength;
        }
        else
        {
            System.out.println("攻撃が失敗した");
        }
        return 0;
    }
}