# Sweater

```docker run -m 6GiB --rm --name pg -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v vol_1:/var/lib/postgresql/data postgres:15.3```

Запустить оболочку:

```docker exec -it pg bash```