public class Circle {
    // Main Method
    public static void main(String[] args) {

        // Takes inpput via TextIO and uses subrotines to calculate the area and circumference of a
        // circle
        System.out.println("What is the raidus of the circle ");
        double radius = TextIO.getDouble();
        System.out.println("The area is " + area(radius));
        System.out.println("The circumference is " + circumference(radius));
    }


    public static double area(double radius) {
        // Calculates the area of a circle
        // PI * r^2
        return Math.PI * Math.pow(radius, 2);
    }

    public static double circumference(double radius) {
        // Calculates the circumference of a circle
        // 2*PI*r
        return Math.PI * 2 * radius;
    }

}
