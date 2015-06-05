public class Question8_4{
}

class Identifiable{
	private final String uniqueId;
	//equals, hashcode
}
class Registration extends Identifiable{
	private final Vehicle vehicle;
	private final Row row;

	public void freeSlot(){
		row.freeSlot(vehicle);
	}
}
class ParkingLot{
	private List<Storey> storeys;
	private Map<Vehicle, Registration> storedVehicles;

	public void storeVehicle(Vehicle vehicle) throws ParkingLotFullException {
		if (storedVehicles.containsKey(vehicle)){
			return;
		}
		for(Storey storey : storeys){
			Optional<Registration> registration = storey.tryStore(vehicle);
			if (registration.isPresent()){
				storedVehicles.put(vehicle, registration);
				return;
			}
		}
		throw new ParkingLotFullException();
	}
	public void restoreVehicle(Vehivle vehicle){
		if (!storedVehicles.containsKey(vehicle)){
			return;
		}
		Registration registration = storedVehicles.get(vehicle);
		registration.freeSlot();
		storedVehicles.remove(vehicle);
	}
}
class Storey extends Capacity{
	private List<Row> rows;
	public Optional<Registration> tryStore(Vehicle vehicle){
		for(Row row: rows){
			Optional<Registration> registration = row.tryStore(vehicle);
			if (registration.isPresent()){	
				return registration;
			}
		}
		return Optional.<Registration>absent();
	}
}
class Row extends Capacity{
	LinkedList<Vehicle> vehicles;

	public Optional<Registration> tryStore(Vehicle vehicle){
		if (remainingCapacity >= vehicle.size){
			remaininCapacity -= vehicle.size;
			vehicles.add(vehicle);
			return new Optional(new Registration(vehicle, this));
		}
		return Optional.<Registration>absent();
	}
	public void freeSlot(Vehicle vehicle){
		if (!vehicles.contains(vehicle)) throw new IllegalArgumentException(String.format("Parking place[%s] does not contain vehicle[%d]", this, vehicle));
		vehicles.remove(vehicle);
		remaininCapacity += vehicle.size();
	}
}
abstract class Capacity{
	final int capacity;

	public Capacity(int capacity){
		this.capacity = capacity;
	}
	
}
abstract class Vehicle extends Identifiable{
	final int size;
	public Vehicle(int size){
		this.size = size;
	}
}
class Car extends Vehicle{
	public Car(){
		super(2);
	}
}
class MotorCycle extends Vehicle{
	public MotorCycle(){
		super(1);
	}
}
class Bus extends Vehicle{
	public Bus(){
		super(5);
	}
}
