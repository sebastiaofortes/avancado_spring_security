package com.sebastiaofortes.security.security.Controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

import com.sebastiaofortes.security.security.Rolerepository;
import com.sebastiaofortes.security.security.Usuariorepository;
import com.sebastiaofortes.security.security.Model.Role;
import com.sebastiaofortes.security.security.Model.Usuarios;



@RequestMapping(path="/main")
@Controller
public class MainController {
	
	@Autowired
	private Usuariorepository userRepository;
	
	@Autowired
	private Rolerepository roleRepository;

	  @GetMapping(path="/dell") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser1 (@RequestParam Integer id) {
	 
		  userRepository.deleteById(id);
		    return "Dados deletados com sucesso!";
	 
	   
	  }
	  
@Transactional
	  @GetMapping(path="/dell_role") // Map ONLY POST Requests
	  public @ResponseBody String dellRole(@RequestParam String nomeRole) {

	roleRepository.deleteUR(nomeRole);
	System.out.println("Relacionamentos excluídos ");
	  
	  
		  roleRepository.deleteByNomeRole(nomeRole);
		    return "Dados deletados com sucesso!";
	 
	  
	  }
	  
	 
	  
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
	  
	  @PostMapping(path="/add_role") // Map ONLY POST Requests
	  public @ResponseBody String addRole(@RequestParam String role) {
	

	    Role r = new Role();
	    r.setNomeRole(role);

	    roleRepository.save(r);	
	    return "Saved";
	  }	  
	  
	  @Transactional
	  @PostMapping(path="/add_per") // Map ONLY POST Requests
	  public @ResponseBody String addPer(@RequestParam String usuario, @RequestParam String role) {
	
		  roleRepository.insertUR(usuario, role);
	   
		  
	    return "Saved";
	  }	  
	  
	  
	  @Transactional
	  @PostMapping(path="/dell_per") // Map ONLY POST Requests
	  public @ResponseBody String delPer(@RequestParam String usuario, @RequestParam String role) {
	
		  roleRepository.deleteOneUR(usuario, role);
	   
		  
	    return "Saved";
	  }	   
	  
	@RequestMapping("/cadastrar_relacionamentos")
	public String index(ModelMap model) {
		List<Usuarios> listaU = (List<Usuarios>) userRepository.findAll();
		model.addAttribute("usuarios", listaU);
		List<Role> listaR = (List<Role>) roleRepository.findAll();
		model.addAttribute("roles", listaR);
		
		List<String[]> objectList = roleRepository.selectUR();
		model.addAttribute("rels", objectList);
		
		for (String[] obj: objectList) {
			for(String valor: obj) {
				System.out.println(valor);
			}
		}
		
		return "cadastrar_per";
	}
	
	
	@RequestMapping("/cadastrar_usu")
	public String TesteSpost(ModelMap model) {

	
		List<Usuarios> lista = (List<Usuarios>) userRepository.findAll();
		int numres = (lista.size());
		String textres = Integer.toString(numres);
		model.addAttribute("numeroresultados", textres);
		model.addAttribute("usuarios", lista);	
		
		return "cadastrar_usu";
	}
	
	
	@RequestMapping("/cadastrar_roles")
	public String CadastrarRoles(ModelMap model) {

	
		List<Role> lista = (List<Role>) roleRepository.findAll();
		int numres = (lista.size());
		String textres = Integer.toString(numres);
		model.addAttribute("numeroresultados", textres);
		model.addAttribute("roles", lista);	
		
		return "cadastrar_roles";
	}
	
	@RequestMapping("/sobre")
	public String sobre() {

		return "sobre";
	}
	
	@RequestMapping("/home")
	public String home() {

		return "home";
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
