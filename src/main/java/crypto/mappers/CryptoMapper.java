package crypto.mappers;

import crypto.models.HistoCrypto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CryptoMapper {

    String INSERT_DATA = "INSERT INTO `Crypto`.`exchange_summary`(`id`,`fromCurrency`,`toCurrency`,`time`,`open`,`high`,`low`,`close`)\n" +
            "VALUES(#{id},#{fromCurrency},#{toCurrency},#{time},#{open},#{high},#{low},#{close});";
    String GET_ALL_DATA = "SELECT * FROM `Crypto`.`exchange_summary`;";
    String GET_DATA_BY_FSYM = "SELECT * FROM `Crypto`.`exchange_summary` WHERE `fromCurrency` = #{fromCurrency};";



    @Insert(INSERT_DATA)
    public void saveCryptoData(HistoCrypto obj);

    @Select(GET_ALL_DATA)
    public HistoCrypto[] getAllData();

    @Select(GET_DATA_BY_FSYM)
    public HistoCrypto getDataByFsym(String fsym);




}
