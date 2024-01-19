package it.itsrizzoli.ProgettoEsameSpringBoot.Model;

import jakarta.persistence.*;
@Entity
public class UtenteLibro {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Integer id;


        @ManyToOne
        @JoinColumn(name = "utente_id")
        Utente utente;

        @ManyToOne
        @JoinColumn(name = "libro_id")
        Libro libro;

        public UtenteLibro() {
        }

        public UtenteLibro( Utente utente, Libro libro) {

                this.utente = utente;
                this.libro = libro;
        }
}
