package ia;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        EnumSet<Hypothesis> THETA = EnumSet.allOf(Hypothesis.class);

        Map<EnumSet<Hypothesis>, Double> symptoms = new HashMap<>();
        symptoms.put(EnumSet.of(Hypothesis.GRIPA), 0.6);
        symptoms.put(EnumSet.of(Hypothesis.COVID), 0.2);
        symptoms.put(EnumSet.of(Hypothesis.GRIPA, Hypothesis.COVID), 0.2);

        Map<EnumSet<Hypothesis>, Double> test = new HashMap<>();
        test.put(EnumSet.of(Hypothesis.COVID), 0.7);
        test.put(EnumSet.of(Hypothesis.GRIPA), 0.1);
        test.put(THETA, 0.2);

        Map<EnumSet<Hypothesis>, Double> doctor = new HashMap<>();
        doctor.put(EnumSet.of(Hypothesis.GRIPA), 0.4);
        doctor.put(EnumSet.of(Hypothesis.COVID), 0.3);
        doctor.put(THETA, 0.3);

        System.out.println("Agregare 1: simptome + test");
        Map<EnumSet<Hypothesis>, Double> first =
                DempsterShafer.combine(symptoms, test);
        print(first);

        System.out.println("\nAgregare 2: rezultat + evaluare medic");
        Map<EnumSet<Hypothesis>, Double> second =
                DempsterShafer.combine(first, doctor);
        print(second);
    }

    static void print(Map<EnumSet<Hypothesis>, Double> m) {
        for (EnumSet<Hypothesis> key : m.keySet()) {
            System.out.println(key + " -> " + m.get(key));
        }
    }
}

