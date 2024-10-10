package br.edu.atitus.paradigma.cambioservice.controller;

import br.edu.atitus.paradigma.cambioservice.entity.CambioEntity;
import br.edu.atitus.paradigma.cambioservice.repository.CambioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cambio-service") 
public class CambioController {

    private final CambioRepository cambioRepository;

    public CambioController(CambioRepository cambioRepository) {
        this.cambioRepository = cambioRepository;
    }

    @GetMapping("/{valor}/{origem}/{destino}")
    public ResponseEntity<CambioEntity> getCambio(@PathVariable double valor, 
                                                  @PathVariable String origem, 
                                                  @PathVariable String destino) {
        
        Optional<CambioEntity> cambio = cambioRepository.findByOrigemAndDestino(origem, destino);
     
        if (cambio.isPresent()) {
            CambioEntity cambioEntity = cambio.get();
            
         
            double valorConvertido = valor * cambioEntity.getFator();
            cambioEntity.setValorConvertido(valorConvertido);
            cambioEntity.setAmbiente("Produção"); 

           
            return new ResponseEntity<>(cambioEntity, HttpStatus.OK);
        }
        
      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
