
import java.util.*;

public class HorseManager {
    private final Set<Horse> horses;

    public HorseManager() {
        this.horses = new HashSet<>();
    }

    public void addHorse(int id, String name) {
        Horse horse = new Horse(id, name);
        if (horses.contains(horse)) {
            throw new IllegalArgumentException("Horse with id " + id + " already exists");
        }
        horses.add(horse);
    }

    public Set<Horse> getHorses() {
        return horses;
    }

    public Horse getHorseById(int horseId) {
        for (Horse horse : horses) {
            if (horse.getId() == horseId) {
                return horse;
            }
        }
        return null;
    }

    public Horse selectRandomPlace() {
        List<Horse> currentHorses = new ArrayList<>(horses);
        Random random = new Random(1);
        int randomIndex = random.nextInt(currentHorses.size());
        return currentHorses.get(randomIndex);

    }

//    public Horse selectRandomLoser() {
//        List<Horse> currentHorses = new ArrayList<>(horses);
//        Random random = new Random();
//        int randomIndex = random.nextInt(currentHorses.size());
//        return currentHorses.get(randomIndex);
//    }
}
