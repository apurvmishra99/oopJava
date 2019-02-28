public class Field {
    public int value;
    public final boolean initial;

    public Field() {
        value = 0;
        initial = false;
    }

    public Field(int value, boolean state) {
        this.value = value;
        this.initial = state;
    }

    public Field(Field otherField) {
    	this.value = otherField.value;
    	this.initial = otherField.initial;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        this.value = val;
    }

    public boolean isInitial() {
        return initial;
    }

    public String toString() {
        return Integer.toString(value);
    }

}