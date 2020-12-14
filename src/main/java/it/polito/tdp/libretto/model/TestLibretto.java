package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {
	
	Libretto lib;

	private void run() {
		this.lib = new Libretto(); //crea un libretto vuoto
		
		// 1. Inserire alcuni voti
		Voto v1 = new Voto("Tecniche di Programmazione", 30, LocalDate.of(2020, 06, 15));
		Voto v2 = new Voto("Analisi II", 28, LocalDate.of(2020, 06, 28));
		
		lib.add(v1);
		lib.add(v2);
		if(lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14))) == false){
			System.err.println("Errore nell'inserimento del voto");
		}
		
		System.out.println(this.lib);
		
		// 2. Stampa tutti i voti uguali a 28
		System.out.println(this.lib.stampaVotiUguali(28));
		
		System.out.println(this.lib.estraiVotiUguali(28));
		
		// 3. Cerca un esame superato, conoscendo il nome del corso
		String nomeCorso = "Analisi II";
		Voto votoAnalisi = lib.cercaNomeCorso(nomeCorso);
		System.out.println("Il voto di "+nomeCorso+" è "+votoAnalisi.getVoto());
		/*Voto votoMancante = lib.cercaNomeCorso("Fisica I");
		System.out.println("Il voto di Fisica I è "+votoMancante.getVoto());*/
		
		// 4. 5. Verifica voti duplicati o voti in conflitto
		Voto economia2 = new Voto("Economia", 24, LocalDate.now());
		Voto economia3 = new Voto("Economia", 21, LocalDate.now());
		System.out.println("Economia con 24 è duplicato : "+
		lib.isDuplicato(economia2)+"/conflitto: "+lib.isConflitto(economia2));
		System.out.println("Economia con 21 è duplicato : "+
		lib.isDuplicato(economia3)+"/conflitto: "+lib.isConflitto(economia3));
		
		// 6. Migliora il libretto
		Libretto migliorato = lib.creaLibrettoMigliorato();
		System.out.println("Miglioramento del libretto");
		System.out.println(lib);
		System.out.println(migliorato);
		
		// 7. Stampa in ordine alfabetico
		Libretto alfabetico = new Libretto(lib);
		alfabetico.ordinaPerCorso();
		System.out.println(alfabetico);
		// 7. Stampa in ordine alfabetico
		Libretto votidecrescenti = new Libretto(lib);
		votidecrescenti.ordinaPerVoto();
		System.out.println(votidecrescenti);
		
		// 8. Elimina voti bassi
		lib.add(new Voto("Chimica", 19, LocalDate.now()));
		lib.ordinaPerCorso();
		System.out.println(lib);
		lib.cancellaVotiScarsi();
		System.out.println(lib);
	}
	
	public static void main(String[] args) {
		// static : posso richiamare solamente altri metodi static 
		// di altre classi non si lavora all'interno del main, si 
		// crea un'oggetto e si lavora all'interno di esso
		TestLibretto test = new TestLibretto();
		test.run();
	}

}
