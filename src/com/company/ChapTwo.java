package com.company;

public class ChapTwo {
    public ChapTwo() {
        System.out.println("BEGIN EXERCISES CHAP 2 ");
        System.out.println("EXERCISES 5");
        PointImmu pointImmu = new PointImmu(4.0f, 3.0f);
        PointImmu newPointImu = pointImmu.translate(1.0f, 1.0f).scale(0.5f);
        System.out.println(pointImmu.toString() + "|" + newPointImu.toString());
        System.out.println("EXERCISES 6,7,8");
        PointMutator pointMutator = new PointMutator(4.0f, 3.0f);
        PointMutator newPointMutator = pointMutator.translate(1.0f, 1.0f).scale(0.5f);
        System.out.println(pointMutator.toString() + "|" + newPointMutator.toString());
        System.out.println("EXERCISES 9");
        Car car = new Car(0.5f);
        car.travelBy(100.0f);
        System.out.println("Travel by:" + car.getTravelDistanceMiles() + ", spend fuel:" + car.getFuelSpend());
        car.travelBy(400.0f);
        System.out.println("Travel by:" + car.getTravelDistanceMiles() + ", spend fuel:" + car.getFuelSpend());
    }

    public static class PointImmu {
        private Float x;
        private Float y;
        public PointImmu(Float x, Float y) {
            this.x = x;
            this.y = y;
        }
        public PointImmu translate(Float x, Float y) {
            return new PointImmu(getX() + x, getY() + y);
        }
        public PointImmu scale(Float scale) {
            return new PointImmu(getX() * scale, getY() * scale);
        }

        public Float getX() {
            return x;
        }

        public void setX(Float x) {
            this.x = x;
        }

        public Float getY() {
            return y;
        }

        public void setY(Float y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "PointImmu{" +
                    "x=" + x +
                    ", y=" + y +
                    '}' + super.hashCode();
        }
    }
    public static class PointMutator {
        private Float x;
        private Float y;
        public PointMutator(Float x, Float y) {
            this.x = x;
            this.y = y;
        }
        public PointMutator translate(Float x, Float y) {
            setX(getX() + x);
            setY(getY() + y);
            return this;
        }
        public PointMutator scale(Float scale) {
            setY(getY() * scale);
            setX(getX() * scale);
            return this;
        }

        public Float getX() {
            return x;
        }

        public void setX(Float x) {
            this.x = x;
        }

        public Float getY() {
            return y;
        }

        public void setY(Float y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "PointMutator{" +
                    "x=" + x +
                    ", y=" + y +
                    '}' + super.hashCode();
        }
    }
    public static class Car {
        private Float fuelRate;
        private Float fuelSpend = 0.0f;
        protected Float travelDistanceMiles = 0.0f;

        /**
         * @param fuelRate the miles / gallons
         */
        public Car(Float fuelRate) {
            this.fuelRate = fuelRate;
        }
        public void travelBy(Float midlesDistance) {
            if (midlesDistance > 0) {
                this.travelDistanceMiles += midlesDistance;
                this.fuelSpend += midlesDistance * (1 / fuelRate);
            }
        }
        public Float getTravelDistanceMiles() {
            return travelDistanceMiles;
        }
        public Float getFuelSpend() {
            return fuelSpend;
        }
    }
}
