package ia;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class DempsterShafer {

    public static Map<EnumSet<Risk>, Double> combine(Map<EnumSet<Risk>, Double> m1,
                                                     Map<EnumSet<Risk>, Double> m2) {

        Map<EnumSet<Risk>, Double> temp = new HashMap<>();
        double conflict = 0.0;

        for (EnumSet<Risk> a : m1.keySet()) {
            for (EnumSet<Risk> b : m2.keySet()) {

                EnumSet<Risk> inter = EnumSet.copyOf(a);
                inter.retainAll(b);

                double value = m1.get(a) * m2.get(b);

                if (inter.isEmpty()) {
                    conflict += value;
                } else {
                    if (!temp.containsKey(inter)) {
                        temp.put(inter, value);
                    } else {
                        temp.put(inter, temp.get(inter) + value);
                    }
                }
            }
        }

        double normalizer = 1.0 - conflict;
        if (normalizer <= 0.000001) {
            throw new RuntimeException("Conflict prea mare (K=" + conflict + ").");
        }

        Map<EnumSet<Risk>, Double> result = new HashMap<>();
        for (EnumSet<Risk> key : temp.keySet()) {
            result.put(key, temp.get(key) / normalizer);
        }

        return result;
    }

    public static void print(Map<EnumSet<Risk>, Double> m) {
        for (EnumSet<Risk> key : m.keySet()) {
            System.out.println(key + " -> " + m.get(key));
        }
    }
}
