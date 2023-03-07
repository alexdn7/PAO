public class CarService {
    private Car[] cars = new Car[0];
    private CarService () {}

    private static class SingletonHolder {
        public static final CarService INSTANCE = new CarService();
    }

    public static CarService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void listAllCars() {
        for(Car c : cars) {
            System.out.println("Car details:\nName: " + c.getName() + ", color: " + c.getColor() + "\nReviews:" + c.getReviews() + "\n");
        }
    }

    public void AddCar(Car c) {
        Car[] newArr = new Car[cars.length + 1];

        System.arraycopy(cars, 0, newArr, 0, cars.length);

        newArr[newArr.length - 1] = c;
        cars = newArr;
    }

    public void AddReview(String carName, String review) {
        for(var c: cars) {
            if(c.getName().equalsIgnoreCase(carName))
                c.addReview(review);
        }
    }
}
