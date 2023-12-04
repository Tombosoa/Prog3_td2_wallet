package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.Devise;
import com.example.td2wallet.Operation.DeviseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DeviseController {
    private final DeviseOperation deviseOperation;

    @Autowired
    public DeviseController(DeviseOperation deviseOperation) {
        this.deviseOperation = deviseOperation;
    }

    @GetMapping("/devises")
    public List<Devise> getDevises(){
        return deviseOperation.findAll();
    }

    @PostMapping("/saveAllDevises")
    public List<Devise> saveAllDevises(@RequestBody List<Devise> devises){
        return deviseOperation.saveAll(devises);
    }

    @PostMapping("/devise")
    public Devise newDevise(@RequestBody Devise devise){
        return deviseOperation.save(devise);
    }

    @PutMapping("/devise")
    public Devise updateDevise(@RequestBody Devise devise){
        return deviseOperation.update(devise);
    }

    @DeleteMapping("/devise/{uid}")
    public void deleteDevise(@PathVariable("uid") int id){
        deviseOperation.deleteDevise(id);
    }

    @GetMapping("/devise/{id}")
    public Devise getOneDevise(@PathVariable("id") int id){
        return deviseOperation.getOne(id);
    }
}
