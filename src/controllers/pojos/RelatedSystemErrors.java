package controllers.pojos;

import java.util.ArrayList;

public class RelatedSystemErrors {

	String system;

	int tenMinuteCount;
	int thirtyMinuteCount;
	int oneHourCount;
	int eightHourCount;
	int twentyFourHourCount;
	int sevenDayCount;
	int twentyEightDayCount;

	double tenMinuteTrend;
	double thirtyMinuteTrend;
	double oneHourTrend;
	double eightHourTrend;
	double sevenDayTrend;
	double twentyFourHourTrend;

	boolean warning;
	boolean danger;

	ArrayList<ErrorAuditDetails> errorAuditDetails;

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public int getTenMinuteCount() {
		return tenMinuteCount;
	}

	public void setTenMinuteCount(int tenMinuteCount) {
		this.tenMinuteCount = tenMinuteCount;
	}

	public int getThirtyMinuteCount() {
		return thirtyMinuteCount;
	}

	public void setThirtyMinuteCount(int thirtyMinuteCount) {
		this.thirtyMinuteCount = thirtyMinuteCount;
	}

	public int getOneHourCount() {
		return oneHourCount;
	}

	public void setOneHourCount(int oneHourCount) {
		this.oneHourCount = oneHourCount;
	}

	public int getEightHourCount() {
		return eightHourCount;
	}

	public void setEightHourCount(int eightHourCount) {
		this.eightHourCount = eightHourCount;
	}

	public int getTwentyFourHourCount() {
		return twentyFourHourCount;
	}

	public void setTwentyFourHourCount(int twentyFourHourCount) {
		this.twentyFourHourCount = twentyFourHourCount;
	}

	public int getSevenDayCount() {
		return sevenDayCount;
	}

	public void setSevenDayCount(int sevenDayCount) {
		this.sevenDayCount = sevenDayCount;
	}

	public int getTwentyEightDayCount() {
		return twentyEightDayCount;
	}

	public void setTwentyEightDayCount(int twentyEightDayCount) {
		this.twentyEightDayCount = twentyEightDayCount;
	}

	public double getTenMinuteTrend() {
		return tenMinuteTrend;
	}

	public void setTenMinuteTrend(double tenMinuteTrend) {
		this.tenMinuteTrend = tenMinuteTrend;
	}

	public double getThirtyMinuteTrend() {
		return thirtyMinuteTrend;
	}

	public void setThirtyMinuteTrend(double thirtyMinuteTrend) {
		this.thirtyMinuteTrend = thirtyMinuteTrend;
	}

	public double getOneHourTrend() {
		return oneHourTrend;
	}

	public void setOneHourTrend(double oneHourTrend) {
		this.oneHourTrend = oneHourTrend;
	}

	public double getEightHourTrend() {
		return eightHourTrend;
	}

	public void setEightHourTrend(double eightHourTrend) {
		this.eightHourTrend = eightHourTrend;
	}

	public double getSevenDayTrend() {
		return sevenDayTrend;
	}

	public void setSevenDayTrend(double sevenDayTrend) {
		this.sevenDayTrend = sevenDayTrend;
	}

	public double getTwentyFourHourTrend() {
		return twentyFourHourTrend;
	}

	public void setTwentyFourHourTrend(double twentyFourHourTrend) {
		this.twentyFourHourTrend = twentyFourHourTrend;
	}

	public boolean isWarning() {
		return warning;
	}

	public void setWarning(boolean warning) {
		this.warning = warning;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}

	public ArrayList<ErrorAuditDetails> getErrorAuditDetails() {
		return errorAuditDetails;
	}

	public void setErrorAuditDetails(ArrayList<ErrorAuditDetails> errorAuditDetails) {
		this.errorAuditDetails = errorAuditDetails;
	}

}
