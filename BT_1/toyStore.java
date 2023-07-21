package BT_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class toyStore {
    private ArrayList<toy> toys;
    private ArrayList<toy> prizeToys;
    private String prizeFilePath;

    public toyStore() {
        toys = new ArrayList<toy>();
        prizeToys = new ArrayList<toy>();
        prizeFilePath = "prize_toys.txt";
    }

    public void addToy(toy toy) {
        toys.add(toy);
    }

    public void changeToyFrequency(int toyId, double newFrequency) {
        for (toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setFrequency(newFrequency);
            }
        }
    }

    public void organizeRaffle() {
        prizeToys.clear();

        for (toy toy : toys) {
            double random = Math.random() * 100;
            if (random < toy.getFrequency()) {
                prizeToys.add(toy);
            }
        }
    }
    public toy getPrizeToy() {
        if (!prizeToys.isEmpty()) {
            toy prizeToy = prizeToys.remove(0);
            prizeToy.setQuantity(prizeToy.getQuantity() - 1);

            try {
                FileWriter writer = new FileWriter(prizeFilePath, true);
                writer.write(prizeToy.getName() + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл игрушки");
            }

            return prizeToy;
        } else {
            System.out.println("Все игрушки кончились");
            return null;
        }
    }
}
