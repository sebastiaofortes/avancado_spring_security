package com.sebastiaofortes.security.security.Controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sebastiaofortes.security.security.Usuariorepository;
import com.sebastiaofortes.security.security.Model.Usuarios;



@RequestMapping(path="/main")
@Controller
public class MainController {
	
	@Autowired
	private Usuariorepository userRepository;
/*
	  @GetMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser1 (@RequestParam String name
	      , @RequestParam String email) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

	    Usuarios n = new Usuarios();
	    n.setLogin(name);
	    n.setEmail(email);
	    userRepository.save(n);
	    return "Dados salvos com sucesso!";
	  }
	  
	  */
	  
	  @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam String name
	      , @RequestParam String email) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

	    Usuarios n = new Usuarios();
	    n.setLogin(name);
	    n.setEmail(new BCryptPasswordEncoder().encode(email));
	    userRepository.save(n);
	    return "Saved";
	  }
	
	@RequestMapping("/teste-sql")
	public String index() {

	    Usuarios n = new Usuarios();
	    n.setLogin("sebastiao");
	    n.setEmail("fortes4");
	    userRepository.save(n);
		
		return "testesql";
	}
	
	@RequestMapping("/listando")
	public String listando(ModelMap model) {

		List<Usuarios> lista = (List<Usuarios>) userRepository.findAll();
		int numres = (lista.size());
		String textres = Integer.toString(numres);
		model.addAttribute("numeroresultados", textres);
		model.addAttribute("usuarios", lista);
		return "listando";
	}
	
	@RequestMapping("/listando2")
	public String listando2(ModelMap model) {

		List<Usuarios> lista = (List<Usuarios>) userRepository.findAll();
		int numres = (lista.size());
		String textres = Integer.toString(numres);
		model.addAttribute("numeroresultados", textres);
		model.addAttribute("usuarios", lista);
		return "listando2";
	}
	
	@RequestMapping("/teste-sql-get")
	public String TesteSget() {

		
		return "testesget";
	}
	
	@RequestMapping("/cadastrar")
	public String TesteSpost() {

		
		return "cadastrar";
	}
	
	@RequestMapping("/sobre")
	public String sobre() {

		return "sobre";
	}
	
	@RequestMapping("/home")
	public String home() {

		return "home";
	}
	
	@RequestMapping("/exibindo")
	public String exibindo(ModelMap model) {

		   model.addAttribute("nomeDoAtributo", "Informção vinda do código java");
		return "exibindo";
	}
	
	@RequestMapping("/recebendo")
	public String recebendo(ModelMap model, @RequestParam String valor) {

		   model.addAttribute("nomeDoAtributo", valor);
		   
		return "recebendo";
	}
	
	
	@RequestMapping("/sessoes")
	public String sessoes(HttpSession session) {
	Enumeration <String> enumeration =	session.getAttributeNames();
	
	while(enumeration.hasMoreElements()){
		String nome = enumeration.nextElement();
		System.out.println("*******Nome do atributo: "+nome);
		System.out.println("*******Conteúdo do atributo: "+session.getAttribute(nome));
	}
	
	
		return "sessoes"; 
	}
}
