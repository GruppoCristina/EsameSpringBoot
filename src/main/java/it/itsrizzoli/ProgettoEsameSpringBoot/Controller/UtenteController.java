package it.itsrizzoli.ProgettoEsameSpringBoot.Controller;

import it.itsrizzoli.ProgettoEsameSpringBoot.Model.LibroRepository;
import it.itsrizzoli.ProgettoEsameSpringBoot.Model.Utente;
import it.itsrizzoli.ProgettoEsameSpringBoot.Model.UtenteRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private LibroRepository libroRepository;
    @GetMapping("/")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/registrazione")
    public String showRegister(PersonaForm personaForm){
        return "registrazioneuser";
    }

    @PostMapping("/postRegistrazione")
    public String  postRegistrazione(@Valid PersonaForm personaForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registrazioneuser";
        }
        utenteRepository.save(new Utente(personaForm.nome,personaForm.cognome,personaForm.username,personaForm.password));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
        return "loginuser";
    }

    @PostMapping("/postLogin")
    public String postLogin(LoginForm loginForm, HttpSession session) {
        Utente user = utenteRepository.login(loginForm.username, loginForm.password);

        if(user != null){
            session.setAttribute("utente", user);
            System.out.println(session.getAttribute("utente"));
            return "redirect:/home";
        }else{
            return "loginuser";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("utente", null);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String showHome(Model m, HttpSession session) {

        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Utente utente = (Utente) session.getAttribute("utente");

        m.addAttribute("libri",libroRepository.findAll());

        m.addAttribute("preferiti",libroRepository.findLibroByUtenteLibri(utente.getId()));

        return "home";
    }

    @GetMapping("/profilo")
    public String showProfilo(HttpSession session, Model model){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);

        return "profilo";
    }

    @GetMapping("/modificaUtente")
    public String mostraModificaForm(Model model, HttpSession session) {
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
        Utente utente = (Utente) session.getAttribute("utente");

        model.addAttribute("utente", utente);
        return "modificaProfilo";
    }

    @PostMapping("/modificaPostUtente")
    public String postModificaForm(Utente utente, HttpSession session) {
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
        Utente utente1 = (Utente) session.getAttribute("utente");
        utente1.setNome(utente1.getNome());
        utente1.setCognome(utente.getCognome());
        utente1.setUsername(utente.getUsername());
        utente1.setPassword(utente.getPassword());

        utenteRepository.save(utente1);

        return "redirect:/profilo";
    }

    @GetMapping("/delete")
    public String removeUser(HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
            Utente utente = (Utente) session.getAttribute("utente");
        utenteRepository.delete(utente);

        session.setAttribute("utente",null);

        return "redirect:/registrazione";
    }
}
