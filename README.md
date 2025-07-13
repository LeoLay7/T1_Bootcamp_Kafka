# Kafka Weather Data Producer/Consumer

Проект для демонстрации работы с Apache Kafka через Producer и Consumer для обработки данных о погоде.

## Запуск через Docker Compose

### Предварительные требования
- Docker
- Docker Compose

### Запуск
```bash
# Сборка и запуск всех сервисов
docker compose up --build

# Запуск в фоновом режиме
docker compose up --build -d

# Остановка
docker compose down
```

### Архитектура
- **Kafka**: Apache Kafka брокер (порт 9092)
- **Producer**: Генерирует данные о погоде каждые 2 секунды
- **Consumer**: Обрабатывает и анализирует данные о погоде
- **kafka-init**: Создает топик `weather-report`

### Переменные окружения
- `KAFKA_SERVERS`: Адрес Kafka сервера (по умолчанию: kafka:19092)
- `KAFKA_TOPIC`: Название топика (по умолчанию: weather-report)
- `TOPIC_TOPIC_PARTITIONS`: Партиции для Consumer (по умолчанию: 0)

### Локальный запуск
Для локального запуска с Kafka на localhost:9092 используйте стандартные Java команды:
```bash
cd Producer && mvn clean package && java -cp "target/classes:target/dependency/*" org.homework.Main

cd Consumer && mvn clean package && java -jar target/Consumer-1.0-SNAPSHOT.jar
```


### Признание
СЛЁЗНО прошу прощения, к сожалению, из-за недостатка времени, не успел сделать тесты...
Каюсь, грешен, обещаю впредь такого не допускать и всегда делать тесты.
Кроме того, знаю про проблему с валидацией данных, но в моем случае, где данные фиксированные, для экономии времени всемозможные проверки подаваемых consumerу данных были пропущены

НО справедливости ради, оно проработало 2 часа к ряду в при локальном запуске и не упало. На основе этого, могу сказать, что работает хорошо, хоть тестами и подтвердить не могу...