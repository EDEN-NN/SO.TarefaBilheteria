package view;

import controller.VendasController;

import java.util.concurrent.Semaphore;

import controller.ThreadEntity;

public class Bilheteria {

	public static void main(String[] args) {
		final int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		
		for (int idThread = 1; idThread < 301; idThread++) { 
			Thread t = new VendasController(idThread, semaforo);
			t.start();
		}

	}

}
