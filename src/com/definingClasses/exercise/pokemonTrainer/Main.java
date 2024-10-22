package com.definingClasses.exercise.pokemonTrainer;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Tournament tournament = new Tournament();

        addTrainersAndPokemon(input, tournament, scanner);

        input = scanner.nextLine();

        while (!"End".equals(input)) {
            String element = input;
            tournament.getTrainerList().forEach(trainer -> trainer.compete(element));

            input = scanner.nextLine();
        }

        tournament.getTrainerList()
                .stream()
                .sorted(Comparator.comparingInt(Trainer::getBadges).reversed())
                .forEach(System.out::println);
    }

    private static void addTrainersAndPokemon(String input, Tournament tournament, Scanner scanner) {
        while (!"Tournament".equals(input)) {
            String[] info = input.split(" ");
            Trainer trainer = new Trainer(info[0]);

            if (tournament.isInTournament(trainer)) {
                trainer = tournament.getTrainer(trainer.getName());
            } else {
                tournament.addTrainer(trainer);
            }

            trainer.addPokemon(getPokemon(info));

            input = scanner.nextLine();
        }
    }

    private static Pokemon getPokemon(String[] info) {
        return new Pokemon(info[1], info[2], Integer.parseInt(info[3]));
    }
}
