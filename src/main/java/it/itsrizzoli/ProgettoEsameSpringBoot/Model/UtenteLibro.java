package it.itsrizzoli.ProgettoEsameSpringBoot.Model;

import jakarta.persistence.*;
@Entity
public class UtenteLibro {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Integer id;


        @ManyToOne
        @JoinColumn
        Utente utente;

        @ManyToOne
        @JoinColumn
        Libro libro;

        public UtenteLibro() {
        }

        public UtenteLibro( Utente utente, Libro libro) {

                this.utente = utente;
                this.libro = libro;
        }


        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Utente getUtente() {
                return utente;
        }

        public void setUtente(Utente utente) {
                this.utente = utente;
        }

        public Libro getLibro() {
                return libro;
        }

        public void setLibro(Libro libro) {
                this.libro = libro;
        }
}
