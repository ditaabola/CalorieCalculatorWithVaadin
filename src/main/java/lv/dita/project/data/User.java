package lv.dita.project.data;

public class User {
    private int height;
    private double weight;
    private int age;
    private PersonsGender gender;
    private DailyActivityLevel activityLevel;

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public PersonsGender getGender() {
        return gender;
    }

    public DailyActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public WeightGoal getGoal() {
        return goal;
    }

    private WeightGoal goal;

}
