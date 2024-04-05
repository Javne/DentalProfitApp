package com.javne.dentalprofitapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Doctor> getAll() {
        return jdbcTemplate.query("SELECT id, date, name, amount, hours FROM doctor",
                BeanPropertyRowMapper.newInstance(Doctor.class));
    }

    public Optional<Doctor> getByName(String name) {
        try {
            Doctor doctor = jdbcTemplate.queryForObject("SELECT id, date, name, amount, hours FROM doctor WHERE name = ?",
                    BeanPropertyRowMapper.newInstance(Doctor.class), name);
            return Optional.ofNullable(doctor);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Doctor> getById(int id) {
        try {
            Doctor doctor = jdbcTemplate.queryForObject("SELECT id, date, name, amount, hours FROM doctor WHERE id = ?",
                    BeanPropertyRowMapper.newInstance(Doctor.class), id);
            return Optional.ofNullable(doctor);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void save(Doctor doctor) {
        jdbcTemplate.update("INSERT INTO doctor (id, date, name, amount, hours) VALUES (?, ?, ?, ?, ?)",
                doctor.getId(), doctor.getDate(), doctor.getName(), doctor.getAmount(), doctor.getHours());
    }

    public void update(Doctor doctor) {
        jdbcTemplate.update("UPDATE doctor SET date = ?, name = ?, amount = ?, hours = ? WHERE id = ?",
                doctor.getDate(), doctor.getName(), doctor.getAmount(), doctor.getHours(), doctor.getId());
    }

    public void delete(Doctor doctor) {
        jdbcTemplate.update("DELETE FROM doctor WHERE name=?", doctor.getName());
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM doctor WHERE id=?", id);
    }

}
