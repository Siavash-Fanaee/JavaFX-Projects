//Superclass for all types of players.
//abstract means that this class can not be instantiated.
public abstract class X_Player {

	private int number;
	private int ID = 0;
	private String playerType;
	
	
	
	private String name;
	
	//Constructors. Default. Empty. Does nothing in particular.
	public X_Player() {
		
	}//constructor()
	
	//Initialises a player with an ID.
	public X_Player(int playerID) {
		ID = playerID;
		
	}//constructor()
	
	//Methods. Usually declared public or protected in order to be useful.
	//Subclasses inherit these methods.
	
	public String kickBall() {
		
		String kickReport = "Just kicked the ball.";
		
		return kickReport;
				
	}//kickBall()
	
	
	public String passBall() {
		
		String passReport = "Just passed the ball.";
		
		return passReport;
		
	}//passBall()
	
	
	//Simple setters.
	public void setNumber(int n) {number = n;}
	public void setName(String nm) {name = nm;}
	public void setType(String tp) {playerType = tp;}
	
	
	//Getters.
	public String getType() { return playerType;}
	public int getID() { return ID;}
	public String getName() {return name;}
	public int getNumber() {return number;}
	
	public String toStringDescr() {
		
		String stringDescr = "\n" + "Type:" + getType() + "\n"
				+ "ID:" + getID() + "\n"
				+ "Name:" + getName() + "\n"
				+ "Number:" + getNumber() + "\n";
		
		
		return stringDescr;
		
	}//toStringDescr()
	
}//Player
