package Algorithm.test;

import java.util.HashMap;
import java.util.Objects;

public class Main {

    static public class Identifier {
        private long A;
        private long B;
        private long C;

        public Identifier(long A, long B, long C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Identifier other = (Identifier) o;

            return other.A == A && other.B == B && other.C == C;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(A, B, C); // import java.util.Objects; 가 필요함
        }
    }

    public static void main(String[] args) {
        HashMap<Identifier, Integer> m = new HashMap<Identifier, Integer>();

        m.put(new Identifier(1, 2, 3), 0);
        m.put(new Identifier(1, 3, 3), 1);
        m.put(new Identifier(1, 3, 3), 2);

        // System.out.println(m.get(new Identifier(1, 3, 3)));

        for (Identifier e : m.keySet()) {
            System.out.println(m.get(e));
        }

    }
}
