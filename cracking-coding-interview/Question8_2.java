public class Question8_2{
}

enum Rank{
	responder, manager, director;

	public Rank higher(){
		if (this == responder) return manager;
		if (this == manager) return director;
		if (this == director) return director;
	}
}

class CallHandler{
	private final Map<Rank, NavigableSet<Employee>> availableEmployeesByRank;
	private final Map<Rank, Queue<Call>> callQueue;

	public void dispatchCall(Caller caller){
		dispatchCall(new Call(caller));
	}	
	public void dispatchCall(Call call){
		Employee availableEmployee = availableEmployeesByRank.get(call.getRank()).pollFirst();
		if (availableEmployee != null){
			call.setHandler(availableEmployee);
			availableEmployee.handleCall(call);
		} else {
			call.reply("waiting for avaiable epmloyee");
			callQueue.get(call.getRank()).add(call);
		}
	}
	public void assignCall(Employee employee){
		Call call = callQueue.get(employee.getRank()).pollFirst();
		if (call != null){
			call.setHandler(employee);
			employee.handleCall(call);
		}
	}
	public void callEnded(Call call){
		Employee employee = call.getHandler();
		unassignEmployee(employee);
	}
	public void escalate(Call call){
		Employee employee = call.getHandler();
		call.setRank(employee.getRank().higher());
		unassignEmployee(employee);
		dispatchCall(call);
	}
	public void unassignEmployee(Employee employee){
		assignCall(employee);
		if (!isAssigned(employee){
                	availableEmployeesByRank.get(employee.getRank()).add(employee);
		}
	}
}

class Call{
	private final Caller caller;
	private Rank rank;
	private Employee handler;
	public Call(Caller caller){
		this(caller, Rank.responder);
	}
	public Call(Caller caller, Rank rank){
		this.caller = checkNotNull(caller);
		this.rank = checkNotNull(rank);
	}
	
}
class Employee{
	private final Rank rank;
	public void handleCall(Call call);
}
