package it.polito.tdp.libretto.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Memorizza e gestisce un insieme di voti superati
 * 
 * @author aless
 * 
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>();
	
	/**
	 * Crea un libretto nuovo e vuoto, non ha bisogno di parametri
	 */
	public Libretto() {
		super(); //E' sempre bene delegare alla superclasse
		//this.voti = new ArrayList<Voto>();
	}
	
	/**
	 * Copy Constructor
	 * "Shallow" (copia superficiale), fa una copia dell'oggetto corrente, ma non degli 
	 * oggetti contenuti.
	 * @param lib
	 */
	// Non dovendo modificare gli oggetti voto, ma ordinarli e basta, è sufficente un copy 
	// constructor "Shallow"
	// Esegue una copia dell'oggetto corrente, gli oggetti Voto sono condivisi tra i due libretti
	public Libretto(Libretto lib) {
		this.voti.addAll(lib.voti);
	}
	
	//La migliore implementazione è quella add(Voto v), è più mantenibile
	//E' un'operazione di delega, in quanto l'operazione di aggiunta è delegata all'ArrayList
	/**
	 * Aggiunge un nuovo noto al libretto
	 * @param v Voto da aggiungere
	 * @return {@code true} se ha inserito il voto, {@code false} se non l'ha inserito
	 * perché era in conflitto oppure era duplicato.
	 */
	public boolean add(Voto v) { // usare sempre oggetti e mai singoli dati sparsi
		if(this.isDuplicato(v) == true || this.isConflitto(v) == true) {
			return false; // non inserire il voto
		} else {
			this.voti.add(v);	// di fatto è un operazione di delega ad Collection.add()
								// inserisce il voto
								// voti.add(v) è analogo, con this. diventa esplicito
			return true;
		}
	}
	
	public String toString() {
		String s="";
		for(Voto v : this.voti) {
			s += v.toString()+"\n";
		}
		return s;
	}
	/**
	 * Dato un Libretto, restituisce una stringa nella quale 
	 * vi sono solamente i voti pari al valore specificato
	 * @param voto
	 * @return
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for(Voto v : this.voti) {
			if(v.getVoto() == voto) {
				s += v.toString()+"\n";
			}
		}
		return s;
	}
	
	/**
	 * Genera un nuovo libretto, a partire da quello esistente,
	 * che conterrà esclusivamente i voti con votazione pari a quella specificata.
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) { //stampa selettiva
		Libretto nuovo = new Libretto();
		for(Voto v: this.voti) {
			if(v.getVoto() == voto) {
				nuovo.add(v);
			}
		}
		return nuovo;
	}
	
	/**
	 * Data il nome di un corso, ricerca se quell'esame esiste nel libretto, 
	 * ed in caso affermativo restituisce l'oggetto {@link Voto} corrispondente.
	 * Se l'esame non esiste, restituisce {@ null}.
	 * 
	 * @param nomeCorso nome esame da cercare
	 * @return il Voto corrispondente, oppure null se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*
		Iterazione dell'array
		for(Voto v : this.voti) {
			if(nomeCorso.equals(v.getCorso())) {
				return v;
			}
		}
		return null;
		*/
		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));
		if(pos != -1) {
			return this.voti.get(pos);
		} else {
			return null;
		}
	}
	/**
	 * Ritorna {@code true} se il corso specificato da {@code v}
	 * esiste nel libretto, con la stessa valutazione.
	 * Se non esiste, o se la stessa valutazione è diversa, ritorna {@code false}.
	 * 
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if(esiste == null)
			return false;
		/*
		if(esiste.getVoto() == v.getVoto())
			return true;
		*/
		else
			return ( esiste.getVoto() == v.getVoto() );
	}
	/**
	 * Determina se esiste un voto con lo stesso nome corso ma valutazione diversa.
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if(esiste==null)
			return false;
		return ( esiste.getVoto() != v.getVoto() );
	}
	/*public void add(String corso, int voto, LocalDate data) {
		voti.
	}*/
	
	/**
	 * Restituisce un NUOVO libretto, migliorando i voti del libretto attuale
	 * 
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();
		for(Voto v : this.voti) {
			//Voto v2 = v; // Non va bene, in quanto questa operazione non crea un'oggetto nuovo, 
						   // ma crea un riferimento all'oggetto già creato del primo ArrayList
			// Come posso clonare l'oggetto contenuto in v (v2)? 
			// 1. METODO : definire un copy constructor
			// 2. METODO : utilizzo (implementandolo) il metodo clone
			// Voto v2 = new Voto(v);
			// Voto v2 = v.clone();
			
			//Voto v3 = new Voto(v.getCorso(), v.getVoto(), v.getData()); NON CI PIACE, in quanto l'oggetto di tipo Voto
			//perderebbe la sua genericità.
			
			Voto v2 = v.clone();
			
			if(v2.getVoto() >= 24) {
				v2.setVoto(v2.getVoto()+2);
				if(v2.getVoto()>30)
					v2.setVoto(30);
			} else if(v2.getVoto() >= 18) {
				v2.setVoto(v2.getVoto()+1);
			}
			nuovo.add(v2);
		}
		return nuovo;
	}
	
	/**
	 * Riordina i voti presenti nel libretto corrente 
	 * alfabeticamente per corso
	 */
	// Posso ordinare secondo il criterio più naturale oppure con dei parametri di ordinamento più articolati.
	public void ordinaPerCorso() {
		Collections.sort(this.voti); // Introdotta da Java 8
	}
	
	// PER VERIFICARE L'UGUAGLIANZA TRA DUE OGGETTI bisogna preferenzialmente utilizzare equals 
	// 
	
	public void ordinaPerVoto() {
		// NOTA : ConfrontaVotiPerValutazione() è una classe, quindi deve essere 
		// istanziata con new per essere utilizzata come oggetto.
		Collections.sort(this.voti, new ConfrontaVotiPerValutazione()); // Si utilizza un Comparator
	}
	
	/**
	 * Elimina dal libretto tutti i voti <24
	 */
	
	// PERCHE' VIENE MOSTRATO ConcurrentModificationException??
	// Se tento di modificare una lista nel mentre sto iterando 
	// sulla lista genero un'accesso concorrente
	public void cancellaVotiScarsi() {
		List<Voto> daRimuovere = new ArrayList<>();
		for(Voto v : this.voti) {
			if( v.getVoto() < 24 ) {
				daRimuovere.add(v);
			}
		}
		
		this.voti.removeAll(daRimuovere);
		/*
		for(Voto v : daRimuovere) {
			this.voti.remove(v);
		}
		*/
	}
}
