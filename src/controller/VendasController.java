package controller;

import java.util.concurrent.Semaphore;
import controller.ThreadEntity;

public class VendasController extends Thread {

	private int maxTickets = 4;
	private int minTickets = 1;
	private static int tickets = 100;
	private int idThread;
	private Semaphore semaforo;
	ThreadEntity pessoa = new ThreadEntity(idThread);

	public VendasController(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		Login();
	}

	private void Login() {
		double timeLogin = ((Math.random() * 1.1) + 0.5);
		// System.out.println(timeLogin + "login");
		if (timeLogin > 1) {
			System.err.println("A thread " + idThread + " levou timeout \n");
		} else {
			System.out.println("A thread " + idThread + " logou \n");
			comprarTicket();
		}
	}

	private void comprarTicket() {
		int timeCompra = (int) ((Math.random() * 1001) + 2001);
			if (timeCompra >= 2500) {
				System.err.println("A pessoa " + idThread + " não conseguiu efetuar a compra por estourar o tempo de sessão \n");
			} else {
				try {
					sleep(timeCompra);
					Validacao();
				} catch (Exception e) {
					e.getStackTrace();
				}
				
			}
	}
	
	private void Validacao() {
		try {
			semaforo.acquire();
			int comprar = (int) ((Math.random() * maxTickets) + minTickets);
			if (tickets > 0 && comprar <= tickets) {
				pessoa.setTickets(pessoa.getTickets() + comprar);
				tickets -= comprar;
				System.out.println("A pessoa " + idThread + " comprou " + pessoa.getTickets() + " ingressos. \n");
				System.out.println(tickets + " ingressos restantes \n");
			} else {
				System.err.println("Ingressos esgotados para tentativa de compra da Thread " + idThread + " \n");
			}
		} catch (Exception e) {
			
		} finally {
			semaforo.release();
		}
	}

}
