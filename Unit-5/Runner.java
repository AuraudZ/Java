class Runner {
    public static void main(String[] args) {

        Student harry = new Student("Harry", 13157);
        Student ron = new Student("Ron");
        Student hermione = new Student();
        Student g = new Student("Ginny", 13157);
        Student f = new Student("Fred");
        System.out.println("** Printing values after objects are created **");
        System.out.println("I'm " + harry.name + ", id " + harry.studentID);
        System.out.println("I'm " + ron.name + ", id " + ron.studentID);
        System.out.println("I'm " + hermione.name + ", id " + hermione.studentID);
        System.out.println("I'm " + g.name + ", id " + g.studentID);
        System.out.println("I'm " + f.name + ", id " + f.studentID);

        // Assign values to object attributes
        // harry.name = "Harry Potter";
        // harry.studentID = 13157;
        // ron.name = "Ron Weasley";
        // hermione.studentID = 17971;

        System.out.println("\n** Printing values after assigning values to objects **");
        System.out.println("I'm " + harry.name + ", id " + harry.studentID);
        System.out.println("I'm " + ron.name + ", id " + ron.studentID);
        System.out.println("I'm " + hermione.name + ", id " + hermione.studentID);

        // Java has a feature called "garbage collection" where
        // objects that have nothing pointing to them get automatically
        // deleted (b/c there's no way to access them)
        ron = hermione; // Object ron used to point to is now inaccessible, will be garbage
                        // collected
        ron.name = "Ronald";

        System.out.println(
                "\n** Printing values after re-assigning ron to point at hermione object **");
        System.out.println("I'm " + harry.name + ", id " + harry.studentID);
        System.out.println("I'm " + ron.name + ", id " + ron.studentID);
        System.out.println("I'm " + hermione.name + ", id " + hermione.studentID);

        System.out.println("\n### Testing static variables (all objects share one copy) ###");
        System.out.println(Student.schoolName); // this is the better way to do it (associated
                                                // w/class)
        System.out.println(Student.schoolName);
        System.out.println(Student.schoolName);
        System.out.println(Student.schoolName); // all share same static variable

        // overwrite the name assigned initially, test again
        Student.schoolName = "LA Eagles";
        System.out.println(
                "\n### Testing static variables (after re-assigning it, all objects reflect this update) ###");
        System.out.println(Student.schoolName); // this is the better way to do it (associated
                                                // w/class)
        System.out.println(harry.schoolName);
        System.out.println(ron.schoolName);
        System.out.println(hermione.schoolName); // all share same static variable
    }

}
