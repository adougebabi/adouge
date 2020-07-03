package com.adouge.secure.client;

import com.adouge.secure.constant.SecureConstant;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author : Vinson
 * @date : 2020/6/15 5:05 下午
 */
@AllArgsConstructor
public class ClientDetailsServiceImpl implements IClientDetailsService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public IClientDetails loadClientByClientId(String clientId) {
        try {
            return jdbcTemplate.queryForObject(SecureConstant.DEFAULT_SELECT_STATEMENT, new String[]{clientId}, new BeanPropertyRowMapper<>(ClientDetails.class));
        } catch (Exception e) {
            return null;
        }

    }
}
