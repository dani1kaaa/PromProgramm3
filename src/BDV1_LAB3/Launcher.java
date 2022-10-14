package BDV1_LAB3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            weatherDAO CityDAO = (weatherDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            CityDAO.deleteAll(); // Удаление

            Weather city = new Weather("Павлодар", "+25"); // Создание
            CityDAO.insert(city);

            CityDAO.insert(new Weather("Павлодар", "+15")); // Добавление
            CityDAO.insert(new Weather("Аксу", "+26"));
            
            /////////////////////
            CityDAO.append("Семей");
            /////////////////////
            
            CityDAO.deleteByTemperatyre("+26"); // Удаление
            CityDAO.delete("Павлодар", "+15");
            
            //////////////////////
            CityDAO.delete("Семей");
            /////////////////////
            
            List<Weather> citys = CityDAO.findByNameCity("па");
            System.out.println(citys != null ? citys : "Нет данных");

            CityDAO.append("Караганда", "+20");
            CityDAO.append("Астана", "+15");
            CityDAO.append("Шымкент", "+30");
            CityDAO.append("Тараз", "+25");

            CityDAO.update("+15", "+25");

            System.out.println("Прогноз погоды на сегодня:");

            List<Weather> list = CityDAO.selectAll();
            for (Weather myCity : list) {
                System.out.println(myCity.getNameCity() + " " + myCity.getTemperature());
            }

            System.out.println();
            System.out.println("Вывод записей с Павлодаром и температурой +25:");

            list = CityDAO.select("Павлодар", "+25");
            for (Weather myCity : list) {
                System.out.println(myCity.getNameCity() + " " + myCity.getTemperature());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }

}
