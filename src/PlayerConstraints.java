
public class PlayerConstraints {

	public final static int upperBound = 400 - PlayerParameters.radiusY;
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
