package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */

/*Annotation que define o controller*/
@RestController
public class GreetingsController {
    
	/*injecao de dependencia para repositorio*/
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	/*método generico para testar o controller da api*/
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }    
    
    @PostMapping(value="salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }    
    
    @GetMapping(value = "listaTodos")
    /*retorna os dados para o corpo da resposta*/
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuarios(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	/*retorna a lista de usuarios em JSON*/
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PutMapping(value="atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("O id não foi informado para atualização!", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }   
    
    @DeleteMapping(value="deletar")
    @ResponseBody
    public ResponseEntity<String> deletar (@RequestParam Long id){
    	usuarioRepository.deleteById(id);
    	return new ResponseEntity<String>("Usuário deletado!", HttpStatus.OK);
    }    
    
    @GetMapping(value="buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name="id") Long id){
    	Usuario user = usuarioRepository.findById(id).get();
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping(value="buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name="name") String name){
    	List<Usuario> user = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }
    
    
}
