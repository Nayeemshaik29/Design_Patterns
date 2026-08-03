package InterviewProblems.ParkingLot.domain;

import java.util.UUID;

public class ParkingSlot {
    private UUID id;
    private Vehicle.VehicleType slotType;
    private Boolean isOccupied;
    private int floorNumber;

    public ParkingSlot(Vehicle.VehicleType slotType,int floorNumber)
    {
        this.slotType = slotType;
        this.floorNumber = floorNumber;
        this.id = UUID.randomUUID();
        this.isOccupied = false;
    }

    public UUID getId() {
        return id;
    }

    public Vehicle.VehicleType getSlotType() {
        return slotType;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSlotType(Vehicle.VehicleType slotType) {
        this.slotType = slotType;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotType=" + slotType +
                ", isOccupied=" + isOccupied +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
