package platformer.world;

public enum Planet {

	MERCURY("mercury"),
	VENUS("venus"),
	EARTH("earth"),
	MOON("moon"),
	MARS("mars"),
	JUPITER("jupiter"),
	SATURN("saturn"),
	URANUS("uranus"),
	NEPTUNE("neptune"),
	PLUTO("pluto")
	
	
	;
	String name;
	Planet(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
