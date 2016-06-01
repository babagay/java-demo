package ru.babagay.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import ru.babagay.entity.Account;

import javax.xml.crypto.Data;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;


@org.springframework.stereotype.Repository("accountRespitory")
public class AccountRepositoryImpl implements AccountRepository<Account> {

    @Autowired
    protected JdbcOperations jdbcOperations;


    //TODO
    public void persist(Account object) {

//        Object[] params = new Object[] { object.getId(), object.getDescription() };
        Object[] params = new Object[]{ object.getId(), object.getFirst_name() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

        jdbcOperations.update("INSERT INTO yourapp_data(\n" +
                "            data_id, data_description)\n" +
                "    VALUES (cast(? as UUID), ?);", params, types);
    }

    // TODO
    public void delete(Data object) {
//        jdbcOperations.update("DELETE FROM yourapp_data\n" +
//                " WHERE data_id = '" + object.getId().toString() + "';");
    }



    @Override
    public void delete(Account object) {

    }

    @Override
    public Set<String> getRandomData() {
        Set<String> result = new HashSet<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT id FROM account a ORDER BY RANDOM() LIMIT 50;");
        while (rowSet.next()) {
            result.add(rowSet.getString("id"));
        }
        return result;
    }


}
