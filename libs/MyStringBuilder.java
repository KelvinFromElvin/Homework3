package libs;

public class MyStringBuilder {
    private static final int INITIAL_LENGTH = 16;
    private static final int INITIAL_INDEX = 0;

    private int size;
    private int idx;
    private char[] allText;

    // Constractors
    public MyStringBuilder() {
        this.size = INITIAL_LENGTH;
        this.idx = INITIAL_INDEX;
        this.allText = new char[this.size];
    }

    // Actions
    private void ensureSize(int sizeToAdd) {
        // O(n)
        if (this.idx + sizeToAdd < this.size) {
            return;
        }
        this.growSize(sizeToAdd);
        this.updateAllTextToSize();
    }

    private void growSize(int sizeToAdd) {
        // O(k)
        sizeToAdd = Math.abs(sizeToAdd);
        int newSize = this.size;
        int minCapacity = this.idx + sizeToAdd;

        while (newSize < minCapacity) {
            newSize = newSize * 2 + 2;
        }

        this.size = newSize;
    }

    private void updateAllTextToSize() {
        // O(n)
        char[] newAllText = new char[this.size];

        for (int i = 0; i < this.idx && i < newAllText.length; i++) {
            newAllText[i] = this.allText[i];
        }

        this.allText = newAllText;
    }

    public MyStringBuilder append(char data) {
        // O(k)
        this.ensureSize(1);
        this.allText[this.idx++] = data;
        return this;
    }

    public MyStringBuilder append(String data) {
        // O(n)
        if (data == null) {
            data = "null";
        }

        int dataLen = data.length();

        this.ensureSize(dataLen);

        for (int i = 0; i < dataLen; i++) {
            this.allText[this.idx++] = data.charAt(i);
        }

        return this;
    }

    @Override
    public String toString() {
        // O(k)
        return new String(this.allText, 0, this.idx);
    }
}
