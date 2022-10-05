/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.adventure.logic;






public interface IGame
{
    public String returnGreeting();
    

    public String returnEpilogue();
    

     public boolean endGame();
     

     public String executeCommand(String radek);
   

     public GamePlan getGamePlan();
}
