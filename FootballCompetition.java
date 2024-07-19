package football;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootballCompetition {

    public static void main(String[] args) {
        System.out.println("Current working directory: " + Paths.get("").toAbsolutePath().toString());

        String inputFile = "input.txt"; // Adjusted path based on your setup
        String outputFile = "output.txt"; // Adjusted path based on your setup
        Map<String, Team> teams = new HashMap<>();

        System.out.println("Input file path: " + inputFile);
        System.out.println("Output file path: " + outputFile);

        File file = new File(inputFile);
        if (!file.exists()) {
            System.err.println("Input file does not exist: " + inputFile);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    System.err.println("Invalid match format: " + line);
                    continue;
                }

                String teamA = parts[0].trim();
                String teamB = parts[1].trim();
                String outcome = parts[2].trim();

                teams.computeIfAbsent(teamA, Team::new);
                teams.computeIfAbsent(teamB, Team::new);

                switch (outcome) {
                    case "win":
                        teams.get(teamA).win();
                        teams.get(teamB).lose();
                        break;
                    case "loss":
                        teams.get(teamA).lose();
                        teams.get(teamB).win();
                        break;
                    default:
                        System.err.println("Unknown match outcome: " + outcome);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Team> sortedTeams = new ArrayList<>(teams.values());
        Collections.sort(sortedTeams);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Team\tMP\tW\tL\tP\n");
            for (Team team : sortedTeams) {
                bw.write(String.format("%s\t%d\t%d\t%d\t%d\n", team.name, team.matchesPlayed, team.wins, team.losses, team.points));
            }
            System.out.println("Output file written successfully: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}