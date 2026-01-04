package ia;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        EnumSet<Risk> THETA = EnumSet.allOf(Risk.class);

        Map<EnumSet<Risk>, Double> weather = new HashMap<>();
        weather.put(EnumSet.of(Risk.RISC_RIDICAT), 0.55);
        weather.put(EnumSet.of(Risk.RISC_MEDIU), 0.25);
        weather.put(THETA, 0.20);

        Map<EnumSet<Risk>, Double> traffic = new HashMap<>();
        traffic.put(EnumSet.of(Risk.RISC_RIDICAT), 0.40);
        traffic.put(EnumSet.of(Risk.RISC_MEDIU), 0.40);
        traffic.put(THETA, 0.20);

        Map<EnumSet<Risk>, Double> road = new HashMap<>();
        road.put(EnumSet.of(Risk.RISC_MEDIU), 0.30);
        road.put(EnumSet.of(Risk.RISC_SCAZUT), 0.20);
        road.put(EnumSet.of(Risk.RISC_RIDICAT), 0.20);
        road.put(THETA, 0.30);

        System.out.println("Evidenta 1 (Vreme)");
        DempsterShafer.print(weather);

        System.out.println("\nEvidenta 2 (Trafic) ");
        DempsterShafer.print(traffic);

        System.out.println("\nAgregare 1: Vreme & Trafic ");
        Map<EnumSet<Risk>, Double> combined12 = DempsterShafer.combine(weather, traffic);
        DempsterShafer.print(combined12);

        System.out.println("\nEvidenta 3 (Drum) ");
        DempsterShafer.print(road);

        System.out.println("\nAgregare 2: (Vreme & Trafic) & Drum ");
        Map<EnumSet<Risk>, Double> combined123 = DempsterShafer.combine(combined12, road);
        DempsterShafer.print(combined123);

        EnumSet<Risk> best = null;
        double bestVal = -1;

        for (EnumSet<Risk> key : combined123.keySet()) {
            double v = combined123.get(key);
            if (v > bestVal) {
                bestVal = v;
                best = key;
            }
        }

        System.out.println("\nConcluzie: cel mai probabil = " + best + " (valoare " + bestVal + ")");
    }
}
