package it.itsrizzoli.ProgettoEsameSpringBoot.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends CrudRepository<Libro, Integer> {
    @Query("select l from Libro l inner join UtenteLibro ul on l.id=ul.libro.id and ul.utente.id=:utenteId")
    List<Libro> findLibroByUtenteLibri(@Param("utenteId") Integer utenteId);
    Optional<Libro> findByTitolo(String titolo);
}
