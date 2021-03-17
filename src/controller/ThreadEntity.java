package controller;

public class ThreadEntity extends Thread{
	
	private int id;
	private int tickets;
	
	public ThreadEntity(int id) {
		this.id = id;
	}
	
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	
	public int getTickets () {
		return this.tickets;
	}
}
