package crypto.services;

import crypto.mappers.CryptoMapper;
import crypto.models.CryptoRoot;
import crypto.models.HistoCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CryptoMapper cryptoMapper;


    public CryptoRoot search(String fsym, String tsym, boolean persist) {
        String fquery = "https://min-api.cryptocompare.com/data/histominute?fsym="+fsym+"&tsym="+tsym;

        CryptoRoot data = restTemplate.getForObject(fquery, CryptoRoot.class);

        if (persist){
            saveCryptoData(data, fsym, tsym);
        }
        return data;
    }

    private void saveCryptoData(CryptoRoot data, String fsym, String tsym) {

        HistoCrypto obj = new HistoCrypto();

        obj.setFromCurrency(fsym);
        obj.setToCurrency(tsym);
        obj.setTime(data.getData()[0].getTime());
        obj.setOpen(data.getData()[0].getOpen());
        obj.setHigh(data.getData()[0].getHigh());
        obj.setLow(data.getData()[0].getLow());
        obj.setClose(data.getData()[0].getClose());

        cryptoMapper.saveCryptoData(obj);
    }

    public HistoCrypto[] getAllData(){
        return cryptoMapper.getAllData();
    }

    public HistoCrypto getDataByFsym(String fsym){
        return cryptoMapper.getDataByFsym(fsym);
    }


}
