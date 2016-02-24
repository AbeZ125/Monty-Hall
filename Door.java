import java.util.Random;

/**
 * Created by Home on 2016-01-10.
 */
public class Door {

    int prize;
    public Door(int prize){

        this.prize=prize;
    }

    public int getPrize() {
        return prize;
    }

    public String toString(){
        return "" + prize;
    }
}
