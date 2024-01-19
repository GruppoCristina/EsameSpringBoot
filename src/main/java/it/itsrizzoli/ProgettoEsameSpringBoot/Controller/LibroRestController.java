package it.itsrizzoli.ProgettoEsameSpringBoot.Controller;

import it.itsrizzoli.ProgettoEsameSpringBoot.Model.Libro;
import it.itsrizzoli.ProgettoEsameSpringBoot.Model.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/libri")
public class LibroRestController {
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public Iterable<Libro> getAllLibri() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Integer id) {
        return libroRepository.findById(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/titolo/{titolo}")
    public ResponseEntity<Libro> getLibroByTitolo(@PathVariable String titolo) {
        return libroRepository.findByTitolo(titolo)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
