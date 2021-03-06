package br.pucrs.adapter.controller;

import java.util.List;
import java.util.Map;

import br.pucrs.adapter.dto.SimulacaoVendaDTO;
import br.pucrs.application.exception.InvalidAddressException;
import br.pucrs.application.exception.LimitExceedOnSaleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("/confirmacao")
    public boolean confirm(@RequestBody VendaDTO dto) {
        try {
            vendaService.confirm(dto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/simular")
    public SimulacaoVendaDTO simulate(@RequestBody VendaDTO dto) {
        return this.vendaService.simulate(dto);
    }

    @GetMapping()
    public List<Venda> history() {
        return this.vendaService.history();
    }


    @ExceptionHandler(LimitExceedOnSaleException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(LimitExceedOnSaleException ex) {
        Map<String, Object> body = Map.of("Message", ex.getMessage(), "status", HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<Map<String, Object>> handleBadAddressRequest(InvalidAddressException ex) {
        Map<String, Object> body = Map.of("Message", ex.getMessage(), "status", HttpStatus.BAD_REQUEST.name());
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

}
