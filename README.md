# battleships

# Uruchomienie
Aby odpalić projekt należy:
1. Sklonować repo komendą `git clone git@github.com:lsereda/battleships.git`
2. Odpalić taska `fatJar` komendą `gradle fatJar`
3. Uruchomić zbudowany program komendą `java -jar build/libs/battleships-1.0-SNAPSHOT.jar` wraz z dodanymi parametrami uruchomieniowymi.

Parametry uruchomieniowe:
- mode [client|server] - wskazuje tryb działania (jako serwer - przyjmuje połączenie, jako klient - nawiązuje połączenie z serwerem)
- port - wskazuje port, na którym aplikacja ma się komunikować
- mapPath - wskazuje ścieżkę z mapą w formacie `.txt`
- host (dotyczy tylko klienta) - wskazuje adres serwera.

Przykładowa komenda uruchamiająca wraz z parametrami `java -jar build/libs/battleships-1.0-SNAPSHOT.jar -mode server -mapPath map.txt -port 1234`.

# Użyte wzorce projektowe wraz z uzasadnieniem:
- `Singleton` (klasy Client, Server) - zapewnienie globalnego dostępu do tych klas oraz zapewnienie, że będzie istnieć co najwyżej jedna instancja każdej z nich
- `Dependency Injection` (konstruktor klasy ScannerCommandReceiver) - usunięcie bezpośredniej zależności między między klasami ScannerCommandReceiver oraz Scanner
- `Factory` (klasy View, ViewFactory, ViewType) - ukrycie zaimplementowanej logiki i sprawienie, żeby użytkownik skupił się na używaniu, a nie tworzeniu nowych obiektów
- `Null object` (klasy EmptyView, EmptyTurn) - ukrycie absencji (referencji do nulla) obiektów poszczególnych typów, ułatwienie procesu tworzenia oraz testowania aplikacji
- `Iterator` (klasy Iterator, MapIterator) - umozliwienie przeglądania pól określonego typu w Mapie bez wnikania w jej implementację
- `Monad` (klasy Maybe, Just, Nothing) - przeniesienie pewnych zachowań do abstrakcji (mogłem uzyć klasy Optional z biblioteki standardowej, jdnak wolałem w celach treningowych napisać własną implementację)
- `Builder` (klasa Session wraz z wewnętrzną klasą SessionBuilder) - rozdzielenie sposobu tworzenia Session od jej implementacji
- `State` (klasa State oraz jej klasy pochodne) - zmiana zachowania wewnętrznego klasy poprzez zmianę jej stanu
