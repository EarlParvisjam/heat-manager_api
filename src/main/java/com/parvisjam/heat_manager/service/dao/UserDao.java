package com.parvisjam.heat_manager.service.dao;

import com.parvisjam.heat_manager.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Repository
public class UserDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void readUsersFromDatabase() {
        // Code to read users from the database
        System.out.println("Reading users from the database...");
        jdbcTemplate.queryForList("SELECT * FROM users").forEach(row -> {
            System.out.println("User: " + row.get("name") + ", Email: " + row.get("email") + ", Age: " + row.get("age"));
        });
    }

    public CustomUser getUserByName(String userName) throws UsernameNotFoundException {
        // Code to get a user by name from the database
        String sql = "SELECT * FROM users WHERE name = ?";
        
        CustomUser user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new CustomUser(
            rs.getString("name"),
            rs.getString("email"),
            rs.getInt("age"),
            rs.getString("password")
        ), userName
        );

        if (user == null) {
            throw new UsernameNotFoundException("User with name " + userName + " not found");
        }

        return user;
    }
}
