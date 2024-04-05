# DentalProfitApp

DentalProfitApp jest aplikacją służącą do zbierania danych na temat dziennego zarobku lekarza w przeliczeniu na ilość przepracowanych godzin. 
Aplikacja pozwala na wizualizację danych z każdego miesiąca oraz prezentuje lekarza z największym utargiem na wykresie. 
Dzięki tej aplikacji możemy analizować, którzy lekarze przynoszą nam największy profit, zarówno miesiąc po miesiącu, jak i w podsumowaniu rocznym.

## Technologie

- Java
- Spring Boot
- MySQL
- Maven
- Docker
- Lombok
- REST API

## Funkcje

- Zbieranie danych o dziennym zarobku lekarza.
- Przeliczanie zarobku na ilość przepracowanych godzin.
- Prezentacja danych z każdego miesiąca.
- Wyświetlanie lekarza z największym utargiem na wykresie.
- Analiza lekarzy przynoszących największy profit.

## Wymagania

- Java 8+
- Spring Boot
- MySQL
- Maven
- Docker
- Lombok
- Biblioteka do interfejsu użytkownika (opcjonalnie)

## Instalacja i Uruchomienie

1. Sklonuj repozytorium:

git clone https://github.com/javne/DentalProfitApp.git

2. Przejdź do katalogu z projektem:

cd DentalProfitApp

3. Uruchom aplikację za pomocą Maven:
   
mvn spring-boot:run


4. (Opcjonalnie) Możesz zbudować obraz Dockera i uruchomić aplikację w kontenerze:

docker build -t dental-profit-app .
docker run -p 8080:8080 dental-profit-app


## Kontrybucje

Jeśli chcesz wnieść swoje poprawki lub funkcjonalności do aplikacji, zapraszamy do kontrybucji. Prosimy o przestrzeganie standardów kodowania i wyraźne opisanie zmian, aby ułatwić proces przeglądu kodu.

1. Rozpocznij od zforkowania repozytorium.
2. Stwórz nowy branch (`git checkout -b nowy-branch`).
3. Wprowadź swoje zmiany.
4. Zrób commit (`git commit -am 'Dodano nową funkcjonalność'`).
5. Wypchnij zmiany do brancha (`git push origin nowy-branch`).
6. Stwórz nowego Pull Requesta.

## Autorzy

- Ewelina Borkowska - [Javne](https://github.com/Javne)

## Licencja

Ten projekt jest objęty licencją [DentalProfitAPP.licence]. Zobacz plik [LICENSE.md](LICENSE.md) dla szczegółów.



