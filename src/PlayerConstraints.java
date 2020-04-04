
public class PlayerConstraints {

	public final static int speed = 20;
	public final static int upperBound = 380 - speed;
	public final static int lowerBound = 0;

	public static boolean checkLowerBound(double y) {
		if (lowerBound < y) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkUpperBound(double y) {
		if (y < upperBound) {
			return true;
		} else {
			return false;
		}
	}

}
