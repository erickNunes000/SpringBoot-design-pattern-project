package DP_Spring.SpringProject.controller;

import DP_Spring.SpringProject.model.Cliente;
import DP_Spring.SpringProject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {//@ResquestMapping("clientes")

    @Autowired
    private ClienteRepository repo;

    @GetMapping("/")
    @ResponseBody //remover ResponseBody e deixar o ResponseEntity
    public ResponseEntity<String>hello(){
        Cliente cli = new Cliente("Erico", "Rua JJ Doutado");
        Cliente cli2 = new Cliente("maria", "Rua 04");
        repo.save(cli);
        repo.save(cli2);
        return ResponseEntity.ok("Cliente criado e salvo: " + repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente>adicionar(@RequestBody Cliente cliente){ //requestbody deve ser usado a objetos
       // Cliente cli = new Cliente(nome, endereco);
        Cliente cli = repo.save(cliente);
        return ResponseEntity.ok(cli);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Optional<Cliente> cli = repo.findById(id);
        if(cli.isPresent()){
            return ResponseEntity.ok(cli.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente>atualizar(@PathVariable Long id ,@RequestBody Cliente cliente){
        Optional<Cliente> cli = repo.findById(id);
        if(cli.isPresent()){
            cli.get().setNome(cliente.getNome());
            cli.get().setEndereco(cliente.getEndereco());
            Cliente atualizado =  repo.save(cli.get());
            return ResponseEntity.ok(atualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String>deletar(@PathVariable Long id){
        Optional<Cliente>cli = repo.findById(id);
        if(cli.isPresent()){
            repo.delete(cli.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("\"cliente deletado com sucesso\"");
    }
}
