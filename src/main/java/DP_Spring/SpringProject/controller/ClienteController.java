package DP_Spring.SpringProject.controller;

import DP_Spring.SpringProject.model.Cliente;
import DP_Spring.SpringProject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @GetMapping("/")
    @ResponseBody
    public String hello(){
        Cliente cli = new Cliente("Erico", "Rua JJ Doutado");
        repo.save(cli);
        return "Cliente criado e salvo: " + cli.getNome();
    }
}
