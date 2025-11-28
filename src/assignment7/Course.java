package assignment7;

public class Course {

    private String name;
    private int credits;
    private int capacity;
    private int studentsEnrolled;
    private Student[] enrolledStudents;   // NEW

    public Course(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolledStudents = new Student[capacity];   // allocate array
        this.studentsEnrolled = 0;
    }

    public String getName() { return name; }
    public int getCredits() { return credits; }
    public int getCapacity() { return capacity; }
    public int getStudentsEnrolled() { return studentsEnrolled; }

    public int getSeatsRemaining() {
        return capacity - studentsEnrolled;
    }

    public boolean addStudent(Student s) {
    // reject if full
    if (studentsEnrolled >= capacity) {
        return false;
    }

    // reject duplicates (identity check, null-safe)
    for (int i = 0; i < studentsEnrolled; i++) {
        if (enrolledStudents[i] == s) {
            return false;
        }
    }

    // add student
    enrolledStudents[studentsEnrolled] = s;
    studentsEnrolled++;
    return true;
}


    public Student getStudentAt(int index) {
        return enrolledStudents[index];
    }

    public String generateRoster() {
        String roster = "";

        for (int i = 0; i < studentsEnrolled; i++) {
            Student s = enrolledStudents[i];

        if (s != null) {
            roster += s.getFullName();
            if (i < studentsEnrolled - 1) {
                roster += "\n";
            }
        }
        }
    return roster;
    }


    public double calculateAverageGPA() {
    if (studentsEnrolled == 0) {
        return Double.NaN;   // avoid division by zero
    }

    double total = 0.0;

    // only loop through actual enrolled students, not the whole array
    for (int i = 0; i < studentsEnrolled; i++) {
        Student s = enrolledStudents[i];
        total += s.calculateGradePointAverage();
    }
    return total / studentsEnrolled;
    }

    @Override
        public String toString() {
        return name + " (" + credits + " credits)";
        }


}
