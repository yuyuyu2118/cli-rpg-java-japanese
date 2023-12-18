package src.com.clirpg.characters;

import src.com.utils.ConsoleColors;

public class Shopkeeper extends Entity implements Talk{
    final static boolean friendly = true;
    String job = "店主";

    public Shopkeeper(String name)
    {
        super(name);
        this.health = 100;
    }

    public void talk()
    {
        System.out.println(ConsoleColors.CYAN_BOLD + "\n" + toString() + "：いらっしゃいませ！何をお探しですか？\n" + ConsoleColors.RESET);
    }

    public String toString()
    {
        return this.job + " " + this.name; 
    }

    public void talkEnd()
    {
        System.out.println(ConsoleColors.CYAN_BOLD + "\n" + toString() + "：ご来店ありがとうございます。良い旅を！" + ConsoleColors.RESET);
    }

    public void talkThanks()
    {
        System.out.println(ConsoleColors.CYAN + "\n" + toString() + "：ご購入ありがとうございます。他にも何かお探しですか？" + ConsoleColors.RESET);
    }
}