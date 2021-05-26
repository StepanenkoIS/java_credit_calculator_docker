**Инструкция по запуску java_credit_calculator_docker:**

1. Перейти в каталог исходного приложения.

2. Выполнить команду для создания докер образа:

$ docker build --tag calculator:1.0 .

3. Проверить созданный образ:

$ docker images

4. Создать каталог например '/data-for-calculation' внутри создать два каталога: 

- parameters  (сюда помещать параметры кредита, данная папка будет слушаться приложением);

- result (сюда будут помещаться результаты вычислений);

5. Для запуска выполнить следующую команду:

$ docker run --name calculator -v /{$directory-path}/data-for-calculation/:/resources -ti calculator:1.0 /resources

вместо {$directory-path} - указать путь к каталогу

6. После завершения, для удаления контейнера выполнить следующие команды 

$ docker rm calculator

$ docker rmi calculator:1.0