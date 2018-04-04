package crypto.controllers;

import crypto.models.CryptoRoot;
import crypto.models.HistoCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import crypto.services.CryptoService;

@RestController
@RequestMapping("/data")

public class CryptoController {

    @Autowired
    CryptoService cryptoService;

//    @RequestMapping("/histominute")
//    public CryptoRoot search (@RequestParam(value = "fsym", required = true) String fsym,
//                              @RequestParam(value = "tsym", required = true) String tsym){
//        return cryptoService.search(fsym, tsym);
//    }


    @RequestMapping("/histominute")
    public CryptoRoot search (@RequestParam(value = "fsym", required = true) String fsym,
                              @RequestParam(value = "tsym", required = true) String tsym,
                              @RequestParam(value = "persist", defaultValue = "false") boolean persist){
        return cryptoService.search(fsym, tsym, persist);
    }

    //get crypto data by "from" currency
    @GetMapping("/{fsym}")
    public HistoCrypto findByFsym(@PathVariable(value = "fsym") String fsym){
        return cryptoService.getDataByFsym(fsym);
    }

    //get crypto data by "to" currency
    @GetMapping("/{tsym}")
    public HistoCrypto findByTsym(@PathVariable(value = "tsym") String tsym){
        return cryptoService.getDataByTsym(tsym);
    }

    //get all crypto data from the database
    @GetMapping("/")
    public HistoCrypto[] findAll(){
        return cryptoService.getAllData();
    }

    @PostMapping("/")
    public String add(@RequestBody HistoCrypto data){
        return cryptoService.addData(data);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") int id){
        return cryptoService.deleteData(id);
    }




}
