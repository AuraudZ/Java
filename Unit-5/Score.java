public abstract class Score {
    private String name;
    protected int value;
    private boolean isUsed;
    YahtzeeDice dice dice.getValues

    abstract int calculateScore(int[] values);

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }


}
