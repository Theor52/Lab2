import java.util.*;

// абстрактний клас вагону
abstract class RailCar {
    // змінні private для того щоб була інкапсуляція
    private int passengerCapacity;
    private int baggageCapacity;
    private int comfortLevel;

    // конструктор
    public RailCar(int passengerCapacity, int baggageCapacity, int comfortLevel) {
        this.passengerCapacity = passengerCapacity;
        this.baggageCapacity = baggageCapacity;
        this.comfortLevel = comfortLevel;
    }

    // функції щоб отримати приватні змінні
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getBaggageCapacity() {
        return baggageCapacity;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    // абстрактний метод для виведення інформації про вагон
    public abstract void displayInfo();
}

// вагон першого класу
class FirstClassCar extends RailCar {
    public FirstClassCar(int passengerCapacity, int baggageCapacity) {
        super(passengerCapacity, baggageCapacity, 1);
    }

    @Override
    public void displayInfo() {
        System.out.println("First Class Car - Passengers: " + this.getPassengerCapacity() + ", Baggage: " + this.getBaggageCapacity() + ", Comfort: " + this.getComfortLevel());
    }
}

// вагон другого класу
class SecondClassCar extends RailCar {
    public SecondClassCar(int passengerCapacity, int baggageCapacity) {
        super(passengerCapacity, baggageCapacity, 2);
    }

    @Override
    public void displayInfo() {
        System.out.println("Second Class Car - Passengers: " + this.getPassengerCapacity() + ", Baggage: " + this.getBaggageCapacity() + ", Comfort: " + this.getComfortLevel());
    }
}

// плацкартний вагон
class ThirdClassCar extends RailCar {
    public ThirdClassCar(int passengerCapacity, int baggageCapacity) {
        super(passengerCapacity, baggageCapacity, 3);
    }

    @Override
    public void displayInfo() {
        System.out.println("Third Class Car - Passengers: " + this.getPassengerCapacity() + ", Baggage: " + this.getBaggageCapacity());
    }
}

// клас для представлення потяга
class Train {
    // вагони як список обʼєктів
    private List<RailCar> cars = new ArrayList<>();

    public void addCar(RailCar car) {
        cars.add(car);
    }

    // функція яка просумовує вмістимість пасажирів по вагонах
    public int calculateTotalPassengers() {
        int totalPassengers = 0;
        for (RailCar car : cars) {
            totalPassengers += car.getPassengerCapacity();
        }
        return totalPassengers;
    }

    // функція яка просумовує вмістимість багажу
    public int calculateTotalBaggage() {
        int totalBaggage = 0;
        for (RailCar car : cars) {
            totalBaggage += car.getBaggageCapacity();
        }
        return totalBaggage;
    }

    // функція що просто сортує по полю comfortLevel
    public void sortCarsByComfort() {
        cars.sort(Comparator.comparingInt(RailCar::getComfortLevel));
    }

    // функція що повертає вагони де вмістимість лежить на проміжку від min до max
    public List<RailCar> findCarsByPassengerRange(int min, int max) {
        List<RailCar> result = new ArrayList<>();
        for (RailCar car : cars) {
            if (car.getPassengerCapacity() >= min && car.getPassengerCapacity() <= max) {
                result.add(car);
            }
        }
        return result;
    }

    // функція що просто проходиться по вагонам і показує їхню інформацію
    public void displayTrain() {
        for (RailCar car : cars) {
            car.displayInfo();
        }
    }
}

// головний клас де виконується програма
public class Main {
    public static void main(String[] args) {
        // створюємо потяг та причеплюємо до нього вагони
        Train train = new Train();
        train.addCar(new FirstClassCar(20, 100));
        train.addCar(new SecondClassCar(40, 150));
        train.addCar(new ThirdClassCar(60, 200));
        train.addCar(new SecondClassCar(45, 160));

        // виводимо інформацію про початковий потяг
        System.out.println("Initial Train Composition:");
        train.displayTrain();
        System.out.println("\nTotal Passengers: " + train.calculateTotalPassengers());
        System.out.println("Total Baggage: " + train.calculateTotalBaggage());

        // сортуємо за комфортом і виводимо інформацію
        train.sortCarsByComfort();
        System.out.println("\nTrain Sorted by Comfort Level:");
        train.displayTrain();

        // фільтруємо вагони де вмістимість між 30 та 50 і виводимо інформацію
        System.out.println("\nCars with passenger capacity between 30 and 50:");
        List<RailCar> filteredCars = train.findCarsByPassengerRange(30, 50);
        for (RailCar car : filteredCars) {
            car.displayInfo();
        }
    }
}
