import java.util.Arrays;

/**
 * Represents a list of int values that can grow and shrink.
 */
public class DynamicArrayOfInts {
    // "Under the hood" we will use an array as our data structure.
    // However since it is private, 'encapsulated', the outside world
    // that is using our DynamicArrayOfInt class will only see the dynamic
    // behavior we provide through the public methods
    private int[] items = new int[8];
    private int itemCt; // default is 0

    /**
     * Return the item at a given index in the array. Throws ArrayIndexOutOfBoundsException if the
     * index is not valid.
     */
    public int get(int index) {
        if (index < 0 || index >= itemCt) {
            throw new ArrayIndexOutOfBoundsException("Illegal index, " + index);
        }
        return items[index];

    }

    /**
     * Set the value of the array element at a given index. Note this overwrites previous
     * information. It DOES NOT allow you to add new items Throws ArrayIndexOutOfBoundsException if
     * the index is not valid.
     */
    public void set(int index, int item) {
        if (index < 0 || index >= itemCt)
            throw new ArrayIndexOutOfBoundsException("Illegal index, " + index);
        items[index] = item;
    }

    /**
     * Returns the number of items currently in the array.
     */
    public int size() {
        return itemCt;
    }

    /**
     * Adds a new item to the end of the array. The size increases by one.
     */
    public void add(int item) {
        if (itemCt == items.length) {
            items = Arrays.copyOf(items, 2 * items.length);
        }
        items[itemCt++] = item;
    }

    /**
     * Adds a new item to the list at the specified index. The size increases by one. Throws
     * ArrayIndexOutOfBoundsException if the index is not valid.
     */
    public void add(int index, int item) {
        if (index < 0 || index >= itemCt) {
            throw new ArrayIndexOutOfBoundsException("Ilegal index, " + index);
        }
        if (itemCt == items.length) {
            items = Arrays.copyOf(items, 2 * items.length);
        }
        for (int i = itemCt; i > index; i--) {
            items[i + 1] = items[i];
        }
        items[index] = item;
        itemCt++;
    }


    /**
     * Removes the item at a given index in the array. The size of the array decreases by one. Items
     * following the removed item are moved down one space in the array. Throws
     * ArrayIndexOutOfBoundsException if the index is not valid.
     */
    public void remove(int index) {
        if (index < 0 || index >= itemCt)
            throw new ArrayIndexOutOfBoundsException("Illegal index, " + index);
        for (int i = index + 1; i < itemCt; i++) {
            items[i - 1] = items[i];
        }
        itemCt--;
    }


    public String toString() {
        // return java.util.Arrays.toString(items);
        String s = "[";
        for (int i = 0; i < items.length; i++) {
            if (i == itemCt && i != items.length - 1)
                s += "**" + items[i] + "**, ";
            else if (i == itemCt && i == items.length - 1)
                s += "**" + items[items.length - 1] + "**]";
            else if (i == items.length - 1)
                s += items[items.length - 1] + "]";
            else
                s += items[i] + ", ";
        }
        return s;
    }

}
