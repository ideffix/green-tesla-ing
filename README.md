# Cel projektu
Repozytorium zostało stworzone na potrzeby [konkursu](https://www.ing.pl/pionteching) "Zielona Tesla za zielony kod" organizowanego przez firmę ING. 

## Technologie
- Java 17
- Maven
- Jackson
- JUnit
- codeql (statyczna analiza kodu)

## Podejście do problemu
Ideą konkursu jest to, aby napisany przez programistę kod był przyjazny ekologii. Stąd też oprócz efektywnego wykonania logiki zadań, zdecydowałem się wprowadzić poniższe usprawnienia:
### Minimalizacja classpath
Zdecydowałem się korzystać z jak najmniejszej ilości zewnętrznych bibliotek tak, aby zminimalizować liczbę klas, która będzie dostępna w wynikowym pliku JAR. Jedyna biblioteka, która jest dostępna w wynikowym pliku JAR to Jackson, który służy do parsowania JSONów. Można byłoby się pokusić na bezpośrednie operacje na stringach tak, aby unikać przemapowywania String -> DTO, a potem DTO -> String, ale uznałem, że ten sposób by zaczernił kod, a zysk byłby niewielki.

Biblioteka, która jest użyta do testów, nie jest pakowana do pliku wynikowego JAR, więc jest ona widoczna tylko w trakcie testów.

### Framework
Spring czy też inny Framework Javy jest świetnym narzędziem do tworzenia aplikacji webowych, ale niestety nic za darmo. Za cenę dużej wygody, musimy zapłacić wielkością paczki wynikowej czy też dodatkowym kodem wykonywanym w trakcie działania programu. Aby maksymalnie zminimalizować czas działania programu, w swoim kodzie zdecydowałem się na korzystanie wyłącznie z klas dostępnych w JDK. Pozwoliło mi to zredukować kilka warstw aplikacji, którą domyślnie dostarcza Spring.

### Testy wydajnościowe
Oprócz badania złożoności obliczeniowej zdecydowałem się również napisać kod, który wykonuje testy wydajnościowe. Przydało się to podczas porównywania czasu wykonania programu po wprowadzaniu zmian, które nie wpływają na złożoność obliczeniową.

Dodatkowo napisałem klasy generujące losowe dane testowe, tak aby można było wykonywać testy dla większej liczby danych wejściowych.

### Kodowanie
Starałem się pisać możliwie jak najprostszy kod, bez streamów, programowania funkcyjnego oraz z zastosowaniem klasycznych pętli. Zauważyłem, że przyczyniło się to do zmniejszenia czasu wykonania programu.

## ATM
W zadaniu wykonuję następujące kroki:
1. Sortuję listę wejściową po regionie, a następnie po priorytecie. O(n*log(n))
2. Tworzę listę wynikową oraz set, w którym przetrzymuję bankomaty, które zostały już dodane do trasy.
3. Iteruję po posortowanej liście i dodaję do listy wynikowej bankomat, który nie został jeszcze odwiedzony. Stosując `record` nie muszę martwić się o poprawną implementację `hashCode`. O(n)

Złożoność czasowa: O(n*log(n))

Złożoność pamięciowa: O(n)

gdzie **n** to ilość zadań (`Task`)

## Transactions
W zadaniu użyłem 2 struktur danych:
1. Mapy, w której kluczem jest numer konta (`String`) a wartością obiekt, który przetrzymuje aktualny stan konta.
2. Kolejka priorytetowa, w której przetrzymywane są posortowane konta.

Algorytm jest bardzo prosty:
1. Iteruję się po wszystkich transakcjach i jeżeli w mapie nie istnieją konta z/do których kierowana jest transakcja, to je tworzę.
2. Wykonuję operację transferu środków z jednego konta na drugie.
3. Na końcu przemapowuję obiekty kont na poprawny typ wynikowy.

Do przechowywania stanu konta użyłem klasy `BigDecimal` aby utrzymać precyzję.

Zamiast kolejki priorytetowej, próbowałem również użyć `TreeMap` oraz posortowanie numerów kont na samym końcu algorytmu, ale żadna z tych opcji nie poprawiła czasu wykonania programu.

## Online Game
Jest to problem znany pod nazwą [Bin packing problem](https://en.wikipedia.org/wiki/Bin_packing_problem). Jako że wszystkie dane są od razu dostępne, możemy to rozwiązać za pomocą algorytmów offlinowych. Aby respektować zasadę, że pierwsi wchodzą najlepsi gracze, posortowałem dane wejściowe, a następnie użyłem podejścia [First Fit](https://en.wikipedia.org/wiki/First-fit_bin_packing).

Problem ten w pesymistycznym wariancie działa w złożoności czasowej O(n^2). W pesymistycznym przypadku algorytm musi sprawdzić wszystkie bieżące pojemniki, zanim umieści przedmiot, a liczba pojemników może sięgać nawet do n w skrajnych przypadkach.