# shop-project

**Для запуска модуля shop необходимо:**
1. В файле shop/src/main/resources/application.properties и flyway.conf установить логин и пароль для подключения к вашему PostgresQL
2. Создать базу данных с именем shop_db.
3. Вход на главную стираницу сайта localhost:8080
4. Логин/пароль администратора на сайте 000000/admin
5. Для полноценной проверки функционеальности необходимо создать один или несколько продуктов в admin panel. Походящие картинки для продуктов в product images
6. *Для работы модуля через eureka сервер, необходимо закоментировать @EnableDiscoveryClient(autoRegister = false) в классе ShopApplication.

**Модуль shop-eureka**
1. Является eureka сервером для обмена запросасми между shop и editProducts

**Модуль EditProducts**
1. Работает через Eureka, поэтому перед его запуском нужно запустить модули shop-eureka и shop*
2. Модуль EditProducts может получать и редактировать **категории продуктов** из базы через eureka
3. Вход на главную страницу сайта localhost:8083
