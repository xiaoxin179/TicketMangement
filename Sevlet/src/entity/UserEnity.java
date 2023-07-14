package entity;

public class UserEnity {
    private int id;
    private String name;
    private int age;
    private String statue;
    private String gender;
    private String phone;
    private String password;

    public UserEnity(int id,String name, int age, String statue, String gender, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.statue = statue;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEnity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", statue='" + statue + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
