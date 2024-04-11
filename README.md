# Zadanie-Rekrutacyjne-ITRun
Zadanie zrealizowane w ramach pierwszego wtapu rekrutacji to firmy ITRun

System Zarządzania Pracownikami

Projekt jest prostym systemem zarządzania pracownikami zaimplementowanym przy użyciu Spring Boot. Zapewnia podstawowe operacje CRUD (Create, Read, Update, Delete) do zarządzania pracownikami.
Spis Treści

    Wstęp
    Wykorzystane Technologie
    Konfiguracja
    Endpointy
    Repozytorium
    Modele

Wstęp

System Zarządzania Pracownikami umożliwia użytkownikom wykonywanie różnych operacji na danych pracowników, takich jak dodawanie nowych pracowników, pobieranie istniejących pracowników, aktualizowanie informacji o pracownikach oraz usuwanie pracowników.
Wykorzystane Technologie

    Java
    Spring Boot
    XML (do przechowywania danych)

Konfiguracja

    Sklonuj repozytorium na swój lokalny komputer.
    Zaimportuj projekt do preferowanego środowiska programistycznego (IDE).
    Zbuduj i uruchom projekt.

Endpointy

    GET /employees: Pobierz wszystkich pracowników.
    GET /employees/find: Znajdź pracownika, podając jego typ, imię, nazwisko oraz numer telefonu.
    POST /employees: Dodaj nowego pracownika.
    DELETE /employees/{personId}: Usuń pracownika na podstawie jego unikalnego ID.
    PUT /employees: Zaktualizuj informacje o istniejącym pracowniku.

Repozytorium

Klasa XmlEmployeeRepository odpowiada za zarządzanie przechowywaniem i pobieraniem danych pracowników. Wykorzystuje pliki XML (internal_employees.xml i external_employees.xml) do przechowywania rekordów pracowników.
Modele

Klasa Person reprezentuje encję pracownika, zawierającą atrybuty takie jak ID pracownika, imię, nazwisko, numer telefonu, e-mail, PESEL (Numer Identyfikacji Osobistej) oraz typ (wewnętrzny lub zewnętrzny).