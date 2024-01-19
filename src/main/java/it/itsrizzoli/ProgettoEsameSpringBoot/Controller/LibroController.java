package it.itsrizzoli.ProgettoEsameSpringBoot.Controller;

import it.itsrizzoli.ProgettoEsameSpringBoot.Model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Controller
public class LibroController {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    UtenteLibroRepository utenteLibroRepository;
    @GetMapping("/createBook")
    public String createBook(Libro libro, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
        return "createbook";
    }

    @PostMapping("/postStoreBook")
    public String storeBook(@Valid Libro libro, BindingResult bindingResult, Model model, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        if(bindingResult.hasErrors()){
            return "createbook";
        }
        libroRepository.save(libro);
        return "redirect:/home";
    }

    @GetMapping("/dettaglio")
    public String dettaglioBook(@RequestParam("libroId") Integer libroId, Model m, HttpSession session){

        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Optional<Libro> libroDettaglio = libroRepository.findById(libroId);
        Libro libro = null;

        if (libroDettaglio.isPresent()) {
            libro = libroDettaglio.get();
        }



        m.addAttribute("libro",libro);
        return "dettagliobook";
    }

    @GetMapping("/modifica")
    public String mostraModificaForm(@RequestParam("libroId") int libroId, Model model, HttpSession session) {
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Optional<Libro> libroOptional = libroRepository.findById(libroId);

        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            model.addAttribute("libro", libro);
        }


        return "modificaForm";
    }

    @PostMapping("modificaPost")
    public String mostraModificaPost(@Valid Libro libro,BindingResult bindingResult, HttpSession session) {

        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        if(bindingResult.hasErrors()){
            return "modificaForm";
        }
        Optional<Libro> libroOptional = libroRepository.findById(libro.getId());

        if (libroOptional.isPresent()) {
            Libro libro1 = libroOptional.get();
            libro1.setTitolo(libro.getTitolo());
            libro1.setAutore(libro.getAutore());
            libro1.setDescrizione(libro.getDescrizione());
            libro1.setPrezzo(libro.getPrezzo());
            System.out.printf(libro.toString());
            libroRepository.save(libro1);
        }
        //html anche il prezzo

        return "redirect:/home";
    }

    @RequestMapping("/preferiti")
    public String bookUser(@RequestParam("libroId") Integer libroId, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Utente utente = (Utente) session.getAttribute("utente");
        Optional<Libro> libroDettaglio = libroRepository.findById(libroId);
        Libro libro = null;

        if (libroDettaglio.isPresent()) {
            libro = libroDettaglio.get();
        }
        UtenteLibro utenteLibro = new UtenteLibro(utente, libro);
        utenteLibroRepository.save(utenteLibro);
        return "redirect:/home";

    }

    @GetMapping("/remove")
    public String removeBook(@RequestParam("libroId") Integer libroId, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Optional<Libro> libroToRimuovi = libroRepository.findById(libroId);

        if (libroToRimuovi.isPresent()) {
            Libro libro = libroToRimuovi.get();
            libroRepository.delete(libro);
        }

        return "redirect:/home";
    }
}
