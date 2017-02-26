package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.eviadorEmail.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;
import br.com.alura.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoService cs;

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/listaconvidados")
	public String listaConvidados(Model model){
		
		Iterable<Convidado> convidados = cs.listarConvidados();
		model.addAttribute("convidados",convidados);
		
		return "listaconvidados";
	}
	
	@RequestMapping(value= "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
	                   @RequestParam("telefone") String telefone, Model model ){

	    Convidado novoConvidado = new Convidado(nome, email, telefone);
	    
	    cs.salvar(novoConvidado);
	    
	    cs.enviarEmail("Thiago", "thinindo2@gmail.com");

	    Iterable<Convidado> convidados = cs.listarConvidados();
	    model.addAttribute("convidados", convidados);

	    return "listaconvidados";
	}
}
