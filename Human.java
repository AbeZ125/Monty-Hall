public class Human {

    private String name;

    public Human(String str) {
        setName(str);
    }

    private void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}