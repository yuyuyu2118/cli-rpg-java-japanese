package src.com.clirpg.characters;

public class Soldier extends Entity implements Combat, Talk{
    final static boolean friendly = false;
    String job = "敵の兵士";
    int damage = 10;

    public Soldier(String name)
    {
        super(name);
        this.health = 15;
    }

    public Soldier(String name, int health)
    {
        super(name, health);
    }

    public int combat()
    {   
        System.out.println(this.name + "が" + this.damage + "のダメージで攻撃します");
        return this.damage;
    }

    public void talk()
    {
        System.out.println("今すぐ降伏しなさい、さもないと！");
    }

    public String toString()
    {
        return this.name + " HP: " + this.health; 
    }
}