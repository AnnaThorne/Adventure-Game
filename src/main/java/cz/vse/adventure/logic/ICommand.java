package cz.vse.adventure.logic;

interface ICommand {

    public String executeCommand(String... params);

	public String getName();
	
}
