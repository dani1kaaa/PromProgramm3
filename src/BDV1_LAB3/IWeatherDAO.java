package BDV1_LAB3;

import java.util.List;
import javax.sql.DataSource;

public interface IWeatherDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(Weather customer); // Вставка новой записи
    void append(String nameCity, String Temperatyrel); // Добавление новой записи
    void append(String nameCity);
    void deleteByTemperatyre(String Temperatyre); // Удаление записи по фамилии
    void delete(String nameCity, String Temperatyre); // Удаление записи с указанными названием и цветом
    void delete(String nameCity);
    void deleteAll(); // Удаление всех запией
    void update(String oldTemperatyre, String newTemperatyre); // Изменение записей в таблице
    List<Weather> findByNameCity(String nameCity); // Получение записей с заданным названием 
    List<Weather> select(String nameCity, String Temperatyre); // Получение записей с заданными названием и цветом
    List<Weather> selectAll(); // Получение всех записей
}
