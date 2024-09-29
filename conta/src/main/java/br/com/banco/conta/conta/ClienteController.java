package br.com.banco.conta.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Controller
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Collection<Cliente>> findAll() {
        var clientes = service.findAll();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable UUID id) {
        var cliente = service.findById(id);
        return ResponseEntity.ok(cliente);
    }
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        cliente =  service.save(cliente);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> Update(@PathVariable UUID id, @RequestBody Cliente cliente){
        cliente = service.update(id, cliente);
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
