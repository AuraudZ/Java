public class Assignment {
    private final String name;
    private final String dateAssigned;
    private final int pointsPossible;
    private final int pointsEarned;

    public Assignment(String name, String dateAssigned, int pointsPossible, int pointsEarned){
        this.name = name;
        this.dateAssigned = dateAssigned;
        this.pointsPossible = pointsPossible;
        this.pointsEarned = pointsEarned;
    }

    public void grade() {
        System.out.println("Graded.");
    }

    public String getName() {
        return name;
    }



    public String getDateAssigned() {
        return dateAssigned;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

}
