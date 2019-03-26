package at.gv.brz.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class DebugPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		System.out.println("Nach Phase: " + event.getPhaseId());
	}

	public void beforePhase(PhaseEvent event) {
		System.out.println("Vor Phase: " + event.getPhaseId());

	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}