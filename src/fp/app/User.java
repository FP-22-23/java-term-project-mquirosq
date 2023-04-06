package fp.app;

import fp.utils.Checkers;

public record User(Gender gender, Integer age, String nationality) {
	
	public User{
		Checkers.check("Incorrect age",age>=0);
	}

	@Override
	public String toString() {
		return "User [gender=" + gender + ", age=" + age + ", nationality=" + nationality + "]";
	}
	
}
