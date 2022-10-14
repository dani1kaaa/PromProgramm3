package BDV1_LAB3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class weatherDAO implements IWeatherDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Weather customer) { // Реализация вставки новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO BDV1_LAB3 (NAME_CITY, TEMPERATYRE) VALUES(?,?)",
                new Object[]{customer.getNameCity(), customer.getTemperature()});
    }

    @Override
    public void append(String nameCity, String Temperatyre) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO BDV1_LAB3 (NAME_CITY, TEMPERATYRE) VALUES(?,?)", new Object[]{nameCity, Temperatyre});
    }

    @Override
    public void deleteByTemperatyre(String temperatyre) {  // Реализация удалени
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM BDV1_LAB3 WHERE temperatyre LIKE ?", new Object[]{'%' + temperatyre + '%'});
    }

    @Override
    public void delete(final String nameCity, final String temperatyre) {  // Реализация удаления записей с указанными названием и цветом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from BDV1_LAB3 where NAME_CITY = ? AND TEMPERATYRE = ?", new Object[]{nameCity, temperatyre});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override

    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from BDV1_LAB3");
    }

    @Override
    public void update(String oldTemperatyre, String newtemperatyre) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE BDV1_LAB3 SET temperatyre = ? WHERE temperatyre = ?", new Object[]{newtemperatyre, oldTemperatyre});
    }

    @Override
    public List<Weather> findByNameCity(String nameCity) {  // Реализация поиска записей по названию
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM BDV1_LAB3 WHERE NAME_CITY LIKE ?";
        List<Weather> city = select.query(sql, new Object[]{'%' + nameCity + '%'}, new CityRowMapper());
        return city;
    }

    @Override
    public List<Weather> select(String nameCity, String temperatyre) {  // Реализация получения записей с заданными названием и цветом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from BDV1_LAB3 where NAME_CITY = ? AND TEMPERATYRE= ?",
                new Object[]{nameCity, temperatyre}, new CityRowMapper());
    }

    @Override
    public List<Weather> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from BDV1_LAB3", new CityRowMapper());
    }

    @Override
    public void append (String nameCity) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        String temperatyre = "+25";
        insert.update("INSERT INTO BDV1_LAB3 (NAME_CITY, temperatyre) VALUES(?,?)", new Object[]{nameCity, temperatyre});
    }
    
   @Override
    public void delete(final String nameCity) {  // Реализация удаления записей с указанными названием и цветом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from BDV1_LAB3 where NAME_CITY = ?", new Object[]{nameCity});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
}