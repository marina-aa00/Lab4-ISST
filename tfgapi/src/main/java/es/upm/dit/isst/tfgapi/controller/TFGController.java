package es.upm.dit.isst.tfgapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  es.upm.dit.isst.tfgapi.model.*;
import  es.upm.dit.isst.tfgapi.repository.*;

import java.net.*;
import java.util.*;
import org.slf4j.*;






@RestController

public class TFGController {

    private final TFGRepository tfgRepository;

    public static final Logger log = LoggerFactory.getLogger(TFGController.class);

    public TFGController(TFGRepository t) {

        this.tfgRepository = t;

    }

    @GetMapping("/tfgs")

    List<TFG> readAll() {

      return (List<TFG>) tfgRepository.findAll();

    }

 

    @PostMapping("/tfgs")

    ResponseEntity<TFG> create(@RequestBody TFG newTFG) throws URISyntaxException {

      TFG result = tfgRepository.save(newTFG);

      return ResponseEntity.created(new URI("/tfgs/" + result.getEmail())).body(result);

    }

 

    @GetMapping("/tfgs/{id}")

    ResponseEntity<TFG> read(@PathVariable String id) {

      return tfgRepository.findById(id).map(tfg ->

         ResponseEntity.ok().body(tfg)

      ).orElse(new ResponseEntity<TFG>(HttpStatus.NOT_FOUND));

    }


    @PutMapping("/tfgs/{id}")

    ResponseEntity<TFG> update(@RequestBody TFG newTFG, @PathVariable String id) {

      return tfgRepository.findById(id).map(tfg -> {

        tfg.setNombre(newTFG.getNombre());

        tfg.setTitulo(newTFG.getTitulo());

        tfg.setTutor(newTFG.getTutor());

        tfg.setStatus(newTFG.getStatus());

        tfg.setNota(newTFG.getNota());

        tfg.setMemoria(newTFG.getMemoria());

        tfgRepository.save(tfg);

        return ResponseEntity.ok().body(tfg);

      }).orElse(new ResponseEntity<TFG>(HttpStatus.NOT_FOUND));

    }


    @DeleteMapping("tfgs/{id}")

    ResponseEntity<TFG> delete(@PathVariable String id) {

      tfgRepository.deleteById(id);

      return new ResponseEntity<TFG>(HttpStatus.NOT_FOUND);

    }


    @GetMapping("/tfgs/profesor/{id}")

    List<TFG> readTutor(@PathVariable String id) {

      return (List<TFG>) tfgRepository.findByTutor(id);

    }


    @PostMapping("/tfgs/{id}/incrementa")

    ResponseEntity<TFG> incrementa(@PathVariable String id) {

      return tfgRepository.findById(id).map(tfg -> {

        tfg.setStatus(tfg.getStatus() + 1);

        tfgRepository.save(tfg);

        return ResponseEntity.ok().body(tfg);

      }).orElse(new ResponseEntity<TFG>(HttpStatus.NOT_FOUND));  

    }

}