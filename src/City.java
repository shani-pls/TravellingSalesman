
public class City {
	private int xCoordinate;
	private int yCoordinate;
	
	public City(int x, int y) {
		setxCoordinate(x);
		setyCoordinate(y);
	}
	
	public City() {
		int x = (int) Math.round(Math.random()*1000);//The size of the 2D space is 1000x1000
		int y = (int) Math.round(Math.random()*1000);//The size of the 2D space is 1000x1000
		setxCoordinate(x);
		setyCoordinate(y);
	}
	
	public double distanceTo(City other) {
		double xDist = this.getxCoordinate() - other.getxCoordinate();
		double yDist = this.getyCoordinate() - other.getyCoordinate();
		return(Math.sqrt((xDist*xDist) + (yDist*yDist)));
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
