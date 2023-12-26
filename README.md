# **Дипломный проект по профессии «Тестировщик»**  
Поставленная задача - автоматизация тестирования веб-сервиса для покупки тура по определенной цене. Данный веб-сервис позволяет производить оплату двумя способами: обычная оплата по дебетовой карте или уникальная технология - выдача кредита по данным банковской карты. 
## **Необходимые инструменты**  
Для успешного запуска автотестов потребуется установка следующих программ: 
- Git   
- IntelliJ IDEA  
- Docker Desktop    
- веб-браузер (рекомендуемые: Google Chrome, Mozilla Firefox)  
 ## **Запуск автотестов**  
1. Клонировать данный проект в локальный репозиторий с помощью команды git clone 
2. Открыть проект с помощью IntelliJ IDEA  
3. Открыть Docker Desktop  
*Следующие шаги осуществляются через терминал IntelliJ IDEA:* 
4. Запустить контейнеры командой docker-compose up
5. Запустить SUT командой для выбранной СУБД:  
для MySQL ввести java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar  
для PostgreSQL ввести java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar  
6. Запустить автотесты командой для выбранной СУБД:  
для MySQL ввести ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"  
для PostgreSQL ввести ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
7. Для остановки SUT использовать сочетание клавиш Ctrl+C
8. Для остановки контейнеров использовать команду docker-compose down 
