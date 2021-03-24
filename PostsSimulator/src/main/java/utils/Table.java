package utils;

public class Table {
    private boolean[] table = new boolean[26];

    public Table() {
        for (int i = 0; i < 26; i++) {
            table[i] = false;
        }
    }

    public void setValue(char c, boolean value) {
        table[c - 'a'] = value;
    }

    public boolean getValue(char c) {
        return table[c - 'a'];
    }
}
