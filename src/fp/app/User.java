package fp.app;

import fp.utils.Checkers;

public record User (Gender gender, Integer age, String nationality) implements Comparable<User>  {
	
	public User{
		Checkers.check("Incorrect age",age>=0);
	}

	@Override
	public String toString() {
		return "User [gender=" + gender + ", age=" + age + ", nationality=" + nationality + "]";
	}

	@Override
	public int compareTo(User u) {
		int out = age.compareTo(u.age());
		if (out==0){
			out = nationality.compareTo(u.nationality());
			if (out==0){
				out = gender.compareTo(u.gender());
			}
		}
		return out;
	}
	
	
}
