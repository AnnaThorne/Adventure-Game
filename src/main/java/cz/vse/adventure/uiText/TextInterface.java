package cz.vse.adventure.uiText;


import java.util.Scanner;
import cz.vse.adventure.logic.IGame;

public class TextInterface {
    private IGame game;


    public TextInterface(IGame hra) {
        this.game = hra;
    }

    public void play() {
        System.out.println(game.returnGreeting());
        while (!game.endGame()) {
            String radek = readString();
            System.out.println(game.executeCommand(radek));
        }
        if(game.getGamePlan().isVictorious()) {
            System.out.println(game.returnEpilogue());
        }
    }

    private String readString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
