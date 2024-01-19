package it.itsrizzoli.ProgettoEsameSpringBoot;

import it.itsrizzoli.ProgettoEsameSpringBoot.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProgettoEsameSpringBootApplication implements CommandLineRunner {
	@Autowired
	UtenteRepository utenteRepository;
	@Autowired
	LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProgettoEsameSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Libro libro = new Libro("Harry Potter", "J.K. Rowling", "Harry Potter è una serie di romanzi fantasy scritta da J. K. Rowling, incentrata sulle avventure del giovane mago Harry Potter e dei suoi migliori amici Ron Weasley ed Hermione Granger, studenti della Scuola di Magia e Stregoneria di Hogwarts. ", 20.0);
		libroRepository.save(libro);

		Utente utente = new Utente("Cristina", "Gruppo", "CriGru", "Cristina");

		// Creo un UtenteLibro associato all'utente e al libro
		UtenteLibro utenteLibro = new UtenteLibro();
		utenteLibro.setUtente(utente);
		utenteLibro.setLibro(libro);

		// Creo un set di UtenteLibro e lo assegno all'utente
		Set<UtenteLibro> utenteLibri = new HashSet<>();
		utenteLibri.add(utenteLibro);
		utente.setUtente_libro(utenteLibri);

		utenteRepository.save(utente);
	}

}
