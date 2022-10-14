package BDV1_LAB3;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class CityRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Weather city = new Weather();
            city.setId(rs.getInt("id"));
            city.setNameCity(rs.getString("name_City"));
            city.setTemperature(rs.getString("Temperatyre"));
            return city;
        }
    }
}
