package crypto.controllers;

import crypto.models.CryptoRoot;
import crypto.models.HistoCrypto;
import crypto.services.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")

public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    //making 3rd party API call from CryptoCompare, persist is optional
    @RequestMapping("/histominute")
    public CryptoRoot search (@RequestParam(value = "fsym", required = true) String fsym,
                              @RequestParam(value = "tsym", required = true) String tsym,
                              @RequestParam(value = "persist", defaultValue = "false") boolean persist){
        return cryptoService.search(fsym, tsym, persist);
    }

    //get crypto data by "from" currency
    @RequestMapping(method= RequestMethod.GET, value = "/fsym")
    public HistoCrypto[] findByFsym(@RequestParam(value = "fsym", required = true) String fsym){
        return cryptoService.getDataByFsym(fsym);
    }

    //get crypto data by "to" currency
    @RequestMapping(method= RequestMethod.GET, value = "/tsym")
    public HistoCrypto[] findByTsym(@RequestParam(value = "tsym", required = true) String tsym){
        return cryptoService.getDataByTsym(tsym);
    }

    //get crypto data by "id"
    @GetMapping("/{id}")
    public HistoCrypto getDataById(@PathVariable(value = "id") int id){
        return cryptoService.getDataById(id);
    }

    //get all crypto data from the database
    @GetMapping("/")
    public HistoCrypto[] findAll(){
        return cryptoService.getAllData();
    }

    //post new crypto data to DB
    @PostMapping("/")
    public String add(@RequestBody HistoCrypto data){
        return cryptoService.addData(data);
    }

    //edit data in DB by ID
    @PatchMapping("/")
    public HistoCrypto update(@RequestBody HistoCrypto data) {
        return cryptoService.update(data);
    }

    //delete data in DB by ID
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(value = "id") int id){
        return cryptoService.deleteDataById(id);
    }

}
