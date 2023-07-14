package entity;

/**
 * @author:XIAOXIN
 * @date:2023/07/10
 **/
public class ManAnWan {
    public int man;
    public int woman;

    public int getMan() {
        return man;
    }

    public void setMan(int man) {
        this.man = man;
    }

    public int getWoman() {
        return woman;
    }

    public void setWoman(int woman) {
        this.woman = woman;
    }

    public ManAnWan(int man, int woman) {
        this.man = man;
        this.woman = woman;
    }

    @Override
    public String toString() {
        return "ManAnWan{" +
                "man=" + man +
                ", woman=" + woman +
                '}';
    }
}
