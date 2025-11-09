package demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SimpleEditor {
    public static String processArrayList(String ops) {
        List<Character> buf = new ArrayList<>();
        int cursor = 0; // index where next insert happens

        for (char ch : ops.toCharArray()) {
            switch (ch) {
                case '<':
                    // you fill in here
                    break;
                case '>':
                    // you fill in here
                    break;
                case '-':
                    // you fill in here
                    break;
                default:
                    buf.add(cursor, ch); // insert with O(n) shift
                    cursor++;
            }
        }
        StringBuilder sb = new StringBuilder(buf.size());
        for (char c : buf)
            sb.append(c);
        return sb.toString();
    }

    public static String processLinkedList(String ops) {
        List<Character> buf = new LinkedList<>();
        ListIterator<Character> it = buf.listIterator(); // iterator is the cursor

        for (char ch : ops.toCharArray()) {
            switch (ch) {
                case '<':
                    // you fill in here
                    break;
                case '>':
                    // you fill in here
                    break;
                case '-':
                    // you fill in here
                    break;
                default:
                    it.add(ch); // O(1) insert at iterator
            }
        }
        StringBuilder sb = new StringBuilder(buf.size());
        for (char c : buf)
            sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(processArrayList("ab<c->d>"));
        System.out.println(processLinkedList("ab<c->d>"));

        // more tests
        String[] inputs = {
                "abc-", // one backspaces
                "abc--", // two backspaces
                "ab<c->d>", // typical cursor + backspace
                "A<B<C<D<->->", // many cursor moves & backspaces
                "-a-bc", // backspace at start has no effect
                "abc>>d<<z", // moving right past end has no effect
                "abc<<<x", // insert at very start
                "ab<cd<-<e>", // interleaved edits
                "abc---", // delete all
                "a<b>c<d->e<f>->g" // mix them all
        };

        String[] expected = {
                "ab",
                "a",
                "abd",
                "CBA",
                "bc",
                "abzcd",
                "xabc",
                "eadb",
                "",
                "bacfg"
        };

        for (int i = 0; i < inputs.length; i++) {
            String got = processArrayList(inputs[i]);
            if (!got.equals(expected[i])) {
                System.out.printf(
                        "LinkedList FAIL  input=\"%s\"  expected=\"%s\"  got=\"%s\"%n",
                        inputs[i], expected[i], got);
            } else {
                System.out.printf(
                        "LinkedList OK    input=\"%s\"  ->  %s%n",
                        inputs[i], got);
            }
        }

        for (int i = 0; i < inputs.length; i++) {
            String got = processLinkedList(inputs[i]);
            if (!got.equals(expected[i])) {
                System.out.printf(
                        "LinkedList FAIL  input=\"%s\"  expected=\"%s\"  got=\"%s\"%n",
                        inputs[i], expected[i], got);
            } else {
                System.out.printf(
                        "LinkedList OK    input=\"%s\"  ->  %s%n",
                        inputs[i], got);
            }
        }
    }
}
