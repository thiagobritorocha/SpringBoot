package br.com.alura.listavip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.eviadorEmail.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

@Service
public class ConvidadoService {
	
	@Autowired
	private ConvidadoRepository cr;

	public Iterable<Convidado> listarConvidados(){
		
		return cr.findAll();
	}
	
	public void salvar(Convidado novoConvidado){
		cr.save(novoConvidado);
	}
	
	public void enviarEmail(String nome, String email){
		new EmailService().enviar(nome, email);
	}
}
