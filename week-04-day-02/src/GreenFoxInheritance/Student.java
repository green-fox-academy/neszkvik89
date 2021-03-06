package GreenFoxInheritance;

public class Student extends Person implements Cloneable {

    String previousOrganization = "The School of Life";
    int skippedDays = 0;

    public Student() {
    }

    public Student(String name, int age, String gender, String previousOrganization) {
        super(name, age, gender);
        this.previousOrganization = previousOrganization;
    }

    public Student clone () {
        try {
            Student johnTheClone = (Student) super.clone();
            return johnTheClone;
        } catch (CloneNotSupportedException e)
        {
            throw new Error();
        }
    }

    public void getGoal() {
        System.out.println("Be a junior software developer");
    }

    public void introduce() {
        System.out.println("Hi, I'm " + this.name + " a " + this.age + " years old " + this.gender + " from " + this.previousOrganization
                + " who skipped " + this.skippedDays + " already");
    }

    public void skipDays(int numberOfDays) {
        this.skippedDays += numberOfDays;
    }

    public static void main(String[] args) {

    }
}
