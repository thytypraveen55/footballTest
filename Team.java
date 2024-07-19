package football;

class Team implements Comparable<Team> {
	String name;
	int matchesPlayed;
	int wins;
	int losses;
	int points;

	Team(String name) {
		this.name = name;
	}

	void win() {
		wins++;
		points += 3;
		matchesPlayed++;
	}

	void lose() {
		losses++;
		matchesPlayed++;
	}

	@Override
	public int compareTo(Team other) {
		// Sort by points descending, then by name alphabetically
		if (this.points != other.points) {
			return other.points - this.points;
		} else {
			return this.name.compareTo(other.name);
		}
	}
}