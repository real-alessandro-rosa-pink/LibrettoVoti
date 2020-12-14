package it.polito.tdp.libretto.model;

// import java.util.Date;
// "La libreria ha un milione di problemi" cit. Fulvio Corno
// Introdotto dalla vesione Java 7.0
import java.time.LocalDate;


/**
 * Classe Voto, contiene le informazioni su un esame superato.
 * @author aless
 *
 */

public class Voto implements Comparable<Voto> {
	
	private String corso;		//ES, "Tecniche di Programmazione"
	private int voto;			//ES, 28
	private LocalDate data;		//ES, 15/06/2020
	
	//Sintassi per la JavaDOC
	
	/**
	 * Costruisce un nuovo Voto.
	 * 
	 * @param corso nome del corso superato
	 * @param voto valore del voto acquisito
	 * @param data data di superamento dell'esame
	 */
	
	public Voto(String corso, int voto, LocalDate data) {
		super();
		this.corso = corso;
		this.voto = voto;
		this.data = data;
	}
	
	//1° METODO per copiare un oggetto
	/**
	 * Copy constructor di {@link Voto}: crea un nuovo {@link Voto}, 
	 * il contenuto del parametro {@code v}
	 * @param v il voto da copiare
	 */
	public Voto(Voto v) {
		// v.getCorso(), sto condividendo il riferimento, immutabile.
		// Utilizzando new String(v.corso) sto creando una nuova stringa, 
		// quindi il problema della copia non si pone, 
		// in quando le stringhe in Java non sono modificabili.
		this.corso = new String(v.corso);
		this.data = v.data;
		// E' un tipo primitivo, quindi viene sempre copiato il valore.
		this.voto = v.voto;
	}
	
	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return corso + ": " + voto + " " + data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}

	// Questa funzione lavora con il principio di delega
	// Criterio di uguaglianza tra due oggetti di tipo Voto
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}
	
	//2° METODO per copiare un oggetto
	/**
	 * Crea una copia (clone) dell'oggetto presente (this), come nuovo oggetto.
	 */
	public Voto clone() {
		Voto v = new Voto(this.corso, this.voto, this.data);
		return v;
	}
	
	@Override
	public int compareTo(Voto other) {
		/*
		 * <0 se this < other
		 * == 0 se this == other
		 * >0 se this > other
		 */
		// Delego il confronto tra gli oggetti voto a gli oggetti di tipo stringa
		return this.getCorso().compareTo(other.getCorso());
		
	}

}