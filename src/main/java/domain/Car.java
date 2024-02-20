package domain;

import java.util.Objects;

public class Car {
    public static final int MINIMUM_LENGTH = 5;
    public String name;
    public int location;

    public Car(String name) {
        validator(name);
        this.name = removeBlank(name);
        location = 0;
    }

    private void validator(String name) {
        Objects.requireNonNull(name, ErrorMessage.NULL_NAME.getMessage());

        if (name.length() > MINIMUM_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.LENGTH_EXCEEDED.getMessage());
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_NAME.getMessage());
        }
    }

    private String removeBlank(String name) {
        return name.replaceAll("\\s+", "");
    }

    public void incLocation() {
        location += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getCarName() {
        return name;
    }

    public int getLocation() {
        return location;
    }
}
