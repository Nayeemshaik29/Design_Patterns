package InterviewProblems.ParkingLot.domain;

import java.util.UUID;

public class PricingRule {
    private UUID id;
    private Vehicle.VehicleType vehicleType;
    private double ratePerHour;
    private double flatRate;
    public PricingRule(Vehicle.VehicleType vehicleType, double ratePerHour, double flatRate) {
        this.id = UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
    }
    public void upddateRates(double ratePerHour, double flatRate) {
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
    }
    public void updaterateFlatRate(double flatRate) {
        this.flatRate = flatRate;
    }
    public void updateHourlyRate(double hourRate) {
        this.ratePerHour = hourRate;
    }
    public UUID getId() {
        return id;
    }

    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public double getFlatRate() {
        return flatRate;
    }

    @Override
    public String toString() {
        return "PricingRule{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
                ", ratePerHour=" + ratePerHour +
                ", flatRate=" + flatRate +
                '}';
    }

}
