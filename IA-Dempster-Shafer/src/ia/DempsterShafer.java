package ia;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class DempsterShafer {

    public static Map<EnumSet<Hypothesis>, Double> combine(
            Map<EnumSet<Hypothesis>, Double> m1,
            Map<EnumSet<Hypothesis>, Double> m2) {

        Map<EnumSet<Hypothesis>, Double> result = new HashMap<>();
        double conflict = 0.0;

        for (EnumSet<Hypothesis> a : m1.keySet()) {

            for (EnumSet<Hypothesis> b : m2.keySet()) {

                EnumSet<Hypothesis> intersection = EnumSet.copyOf(a);
                intersection.retainAll(b);

                double value = m1.get(a) * m2.get(b);

                if (intersection.isEmpty()) {
                    conflict += value;
                } else {
                    if (!result.containsKey(intersection)) {
                        result.put(intersection, value);
                    } else {
                        result.put(intersection, result.get(intersection) + value);
                    }
                }
            }
        }

        double normalizer = 1 - conflict;

        Map<EnumSet<Hypothesis>, Double> normalized = new HashMap<>();
        for (EnumSet<Hypothesis> key : result.keySet()) {
            normalized.put(key, result.get(key) / normalizer);
        }

        return normalized;
    }
}

