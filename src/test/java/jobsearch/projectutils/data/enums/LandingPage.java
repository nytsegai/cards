package jobsearch.projectutils.data.enums;

public enum LandingPage {
	ANNOUNCEMENT ("PVM > Announcement"),
	LOGBOOK ("PVM > Log Book");
	
	private final String title;
	LandingPage(String title) {
		this.title = title;
	}
	
	public String title() {
		return this.title;
	}
}
