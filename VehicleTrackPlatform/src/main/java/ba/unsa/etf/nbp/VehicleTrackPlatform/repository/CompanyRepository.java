package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Company;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.CompanyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class CompanyRowMapper implements RowMapper<Company> {
        @Override
        @NonNull
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            Company company = new Company();
            company.setId(rs.getLong("ID"));
            company.setName(rs.getString("NAME"));
            company.setAddress(rs.getString("ADDRESS"));
            company.setEmail(rs.getString("EMAIL"));
            return company;
        }
    }

    public List<Company> findAll(CompanyType companyType) {
        return jdbcTemplate.query("SELECT * FROM COMPANY WHERE COMPANY_TYPE=?", new CompanyRowMapper(), companyType.getCode());
    }

    public Optional<Company> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM COMPANY WHERE ID = ?",
                new CompanyRowMapper(), id).stream().findFirst();
    }

    public Company save(Company company) {
        if (company.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO COMPANY (NAME, ADDRESS, CITY, POSTAL_CODE, PHONE_NUMBER, EMAIL) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    Long.class,
                    company.getName(),
                    company.getAddress(),
                    company.getEmail());
            company.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE COMPANY SET NAME = ?, ADDRESS = ?, CITY = ?, " +
                            "POSTAL_CODE = ?, PHONE_NUMBER = ?, EMAIL = ? WHERE ID = ?",
                    company.getName(),
                    company.getAddress(),
                    company.getEmail(),
                    company.getId());
        }
        return company;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM COMPANY WHERE ID = ?", id);
    }
}
