package org.some.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/db-info")
public class DatabaseInfoController {

    @Autowired
    private DataSource dataSource;

    @GetMapping
    public String getDatabaseUrl() throws SQLException {
        return "Connected to: " + dataSource.getConnection().getMetaData().getURL();
    }
}