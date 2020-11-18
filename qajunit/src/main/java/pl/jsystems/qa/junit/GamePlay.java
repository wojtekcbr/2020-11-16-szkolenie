
package pl.jsystems.qa.junit;

public class GamePlay {

    public String play(int number) {
        if(number == 0) throw new IllegalArgumentException("NUmber can not be " + number);
        if(number % 3 == 0) return "ok";
        if(number % 5 == 0) return "Not ok";
        return String.valueOf(number);
    }
}