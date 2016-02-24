/**
 * Created by Home on 2016-01-10.
 */
public class Player extends Human{

    private int playerScore;

    public Player(String str) {
        super(str);
    }

    public void setScore(int playerScore){
        this.playerScore += playerScore;

    }

    public int getPlayerScore() {
        return playerScore;
    }
}
