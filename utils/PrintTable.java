package utils;

public class PrintTable {
    public static final int DEFAULT_PADDING_COUNT = 2;
    public static final char PADDING_CHAR = ' ';

    private static final String TABLE_LINES_COLOR = "_§cyan_";
    private static final String TABLE_HEADER_COLOR = "_§red_";
    private static final String TABLE_BODY_COLOR = "_§blue_";

    public static final String TABLE_DATA_WALL = "│";
    public static final String TABLE_VERTICAL_SAPARATOR = "─";

    public static final String TABLE_CELL_TOP_LEFT = "┌";
    public static final String TABLE_CELL_TOP_CENTER = "┬";
    public static final String TABLE_CELL_TOP_RIGHT = "┐";

    public static final String TABLE_CELL_BOTTOM_LEFT = "└";
    public static final String TABLE_CELL_BOTTOM_CENTER = "┴";
    public static final String TABLE_CELL_BOTTOM_RIGHT = "┘";

    public static final String TABLE_CELL_MIDDLE_LEFT = "├";
    public static final String TABLE_CELL_MIDDLE_CENTER = "┼";
    public static final String TABLE_CELL_MIDDLE_RIGHT = "┤";
    // │ ─ ┌ ┐ └ ┘ ├ ┤ ┬ ┴ ┼

    private String[][] body;
    private int paddingCount;
    private String padding;
    private int[] colMaxLen;

    public PrintTable() {
        this.body = new String[0][0];
        this.setPaddingCount(DEFAULT_PADDING_COUNT);
        this.colMaxLen = new int[0];
    }

    // Setters
    public void setPaddingCount(int paddingCount) {
        // O(k)
        if (paddingCount < 0) {
            return;
        }

        this.paddingCount = paddingCount;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.paddingCount; i++) {
            sb.append(PADDING_CHAR);
        }

        this.padding = sb.toString();
    }

    public void addRow() {
        // O(n)
        String[][] newBody = new String[this.body.length + 1][0];

        for (int i = 0; i < this.body.length; i++) {
            newBody[i] = this.body[i];
        }

        newBody[newBody.length - 1] = new String[0];

        this.body = newBody;
    }

    private void addColMaxLen() {
        // O(n)
        int[] newColMaxLen = new int[this.colMaxLen.length + 1];

        for (int i = 0; i < this.colMaxLen.length; i++) {
            newColMaxLen[i] = this.colMaxLen[i];
        }

        newColMaxLen[newColMaxLen.length - 1] = 0;

        this.colMaxLen = newColMaxLen;
    }

    public void addData(String data) {
        // O(n)
        String[] currentRow = this.body[this.body.length - 1];

        String[] newRow = new String[currentRow.length + 1];

        for (int i = 0; i < currentRow.length; i++) {
            newRow[i] = currentRow[i];
        }

        newRow[newRow.length - 1] = data;

        if (newRow.length > this.colMaxLen.length) {
            this.addColMaxLen();
        }

        if (this.colMaxLen[newRow.length - 1] < data.length()) {
            this.colMaxLen[newRow.length - 1] = data.length();
        }

        this.body[this.body.length - 1] = newRow;
    }

    public void printTable() {
        // O(n1 * n2 * n3 * n4)
        int longestTextLen;

        String colors = TABLE_HEADER_COLOR;
        String saparateLineTemplate = this.prepareSaparateLineTemplate();

        this.printRowLine(saparateLineTemplate, -1);// O(n * k)

        for (int row = 0; row < this.body.length; row++) {
            if (row != 0) {
                colors = TABLE_BODY_COLOR;
            }
            for (int col = 0; col < this.body[row].length; col++) {
                longestTextLen = this.colMaxLen[col];

                this.printTextWithPadding(this.body[row][col], longestTextLen, colors, TABLE_LINES_COLOR);
            }
            Prints.printfln(TABLE_DATA_WALL);
            this.printRowLine(saparateLineTemplate, row);
        }
    }

    private String prepareSaparateLineTemplate() {
        // O(n)
        StringBuilder sb = new StringBuilder();

        sb.append(TABLE_LINES_COLOR);

        for (int i = 0; i < this.colMaxLen.length; i++) {
            sb.append("%s");

            for (int j = 0; j < this.colMaxLen[i] + 4; j++) {
                sb.append(TABLE_VERTICAL_SAPARATOR);
            }
        }

        sb.append("%s");

        return sb.toString();
    }

    private void printRowLine(String saparateLineTemplate, int row) {
        // O(n * k)
        String start, middle, end;

        String[] args = new String[this.colMaxLen.length + 1];

        if (row == -1) {
            // first row
            start = TABLE_CELL_TOP_LEFT;
            middle = TABLE_CELL_TOP_CENTER;
            end = TABLE_CELL_TOP_RIGHT;
        } else if (row == this.body.length - 1) {
            // last row
            start = TABLE_CELL_BOTTOM_LEFT;
            middle = TABLE_CELL_BOTTOM_CENTER;
            end = TABLE_CELL_BOTTOM_RIGHT;
        } else {
            // somewhere in the middle
            start = TABLE_CELL_MIDDLE_LEFT;
            middle = TABLE_CELL_MIDDLE_CENTER;
            end = TABLE_CELL_MIDDLE_RIGHT;
        }

        for (int i = 1; i < args.length; i++) {
            args[i] = middle;
        }

        args[0] = start;
        args[args.length - 1] = end;

        Prints.printfln(saparateLineTemplate, (Object[]) args);
    }

    private void printTextWithPadding(String text, int maxTextLen, String textColor, String saparatorColor) {
        // O(n * k)
        int textLen = text.length();
        int padding = (maxTextLen - textLen) / 2;
        int prefPadding = padding;
        int postPadding = padding;

        StringBuilder sb = new StringBuilder();

        sb.append(saparatorColor);

        sb.append(TABLE_DATA_WALL);

        if (prefPadding + postPadding + textLen != maxTextLen) {
            postPadding++;
        }

        sb.append(textColor);
        for (int i = 0; i < prefPadding; i++) {
            sb.append(PADDING_CHAR);
        }

        sb.append(this.padding);
        sb.append(text);
        sb.append(this.padding);

        sb.append(saparatorColor);
        for (int i = 0; i < postPadding; i++) {
            sb.append(PADDING_CHAR);
        }

        Prints.printf(sb.toString());
    }
}
