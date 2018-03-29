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
    @RequestMapping(method = RequestMethod.GET, value = "/{fsym}")
    public HistoCrypto find(@PathVariable(value = "fsym") String fsym){
        return cryptoService.getDataByFsym(fsym);
    }



}
