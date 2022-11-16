package com.rmmcosta.deliveringflowers;

import com.rmmcosta.deliveringflowers.data.Candy;
import com.rmmcosta.deliveringflowers.data.CandyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
public class CandyDAOImpl implements CandyDAO {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_CANDIES =
            "select * from Candy c";
    private static final String SELECT_ALL_CANDIES_BY_DELIVERY_ID =
            "select c.* " +
                    "from Candy c " +
                    "where exists ( select 1 " +
                    "from candy_delivery cd " +
                    "where cd.delivery_id = :deliveryId " +
                    "and cd.candy_id = c.id" +
                    ")";

    @Override
    public List<Candy> getAllCandy() {
        List<Candy> candies = jdbcTemplate.query(SELECT_ALL_CANDIES, new HashMap<>(), new BeanPropertyRowMapper<>(Candy.class));
        return candies;
    }

    @Override
    public void addCandyToDelivery(Long candyId, Long deliveryId) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("candy_delivery");
        HashMap<String, Long> params = new HashMap<>();
        params.put("candy_id", candyId);
        params.put("delivery_id", deliveryId);
        jdbcInsert.execute(params);
    }

    @Override
    public List<Candy> getAllCandyByDeliveryId(Long deliveryId) {
        HashMap<String, Long> params = new HashMap<>();
        params.put("deliveryId", deliveryId);
        List<Candy> candies = jdbcTemplate.query(SELECT_ALL_CANDIES_BY_DELIVERY_ID, params, new BeanPropertyRowMapper<>(Candy.class));
        return candies;
    }

    @Override
    public void saveCandy(Candy candy) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("candy");
        jdbcInsert
                .execute(new BeanPropertySqlParameterSource(candy));
    }
}
