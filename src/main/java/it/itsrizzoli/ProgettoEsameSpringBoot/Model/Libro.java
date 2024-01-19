package it.itsrizzoli.ProgettoEsameSpringBoot.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    String titolo;

    @NotNull
    @Size(min=2, max=50)
    String autore;

    @Size(min=0, max=3000)
    String descrizione;

    @NotNull
    @Min(0)
    double prezzo;

    @OneToMany(mappedBy = "libro")
    Set<UtenteLibro> utente_libro;


    public Libro() {
    }

    public Libro( String titolo, String autore, String descrizione, double prezzo) {

        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
        this.prezzo = prezzo;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Set<UtenteLibro> getUtente_libro() {
        return utente_libro;
    }

    public void setUtente_libro(Set<UtenteLibro> utente_libro) {
        this.utente_libro = utente_libro;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", utente_libro=" + utente_libro +
                '}';
    }
}
