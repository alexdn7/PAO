package associations;

import associations.agregation.FootballPlayer;
import associations.agregation.FootballTeam;
import associations.composition.Car;
import associations.composition.Engine;
import associations.inheritance.Animal;
import associations.inheritance.Dog;
import associations.records.Cat;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        agregare => atunci cand doua obiecte pot exista unul fara celalalt

//        compozitie => has-a => atunci cand doua obiecte nu pot exista unul fara celalalt

//        mostenire => is-a => "cand vrem ca un obiect sa capete semantica altui obiect

//        caz1
        FootballPlayer footballPlayer = new FootballPlayer();
        FootballTeam footballTeam = new FootballTeam();
        List<FootballPlayer> footballPlayers = Arrays.asList(new FootballPlayer());
        footballTeam.setFootballPlayers(footballPlayers);

//        caz 2

        Engine engine = new Engine();
        Car car = new Car(engine);

        Dog dog = new Dog();
        System.out.println(dog instanceof Animal);

        Cat cat1 = new Cat("nume");
        System.out.println(cat1.name());

        List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        l1.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .forEach(System.out::println);
    }
}
