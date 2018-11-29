package nodeTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity {
	
		public enum type{
			LOGON, PENDRIVE, HTTP
		}
		
		private String id, userIdentifier, pc;
		private LocalDateTime date;
		private Boolean active;
		private String url;
		private type activityType;
		
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		
		/**
		 * @return the userIdentifier
		 */
		public String getUserIdentifier() {
			return userIdentifier;
		}
		/**
		 * @param userIdentifier the userIdentifier to set
		 */
		public void setUserIdentifier(String userIdentifier) {
			this.userIdentifier = userIdentifier;
		}
		
		/**
		 * @return the date
		 */
		public LocalDateTime getDate() {
			return date;
		}
		/**
		 * @return the date string formatted to dd/MM/yyyy hh:mm:ss
		 */
		public String getDateString() {
			return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		/**
		 * @return the active
		 */
		public Boolean getActive() {
			return active;
		}
		/**
		 * @param active the active to set
		 */
		public void setActive(Boolean active) {
			this.active = active;
		}
		
		/**
		 * @return the activityType
		 */
		public type getActivityType() {
			return activityType;
		}
		/**
		 * @param activityType the activityType to set
		 */
		public void setActivityType(type activityType) {
			this.activityType = activityType;
		}
		
		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}
		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/**
		 * @return the pc
		 */
		public String getPc() {
			return pc;
		}
		/**
		 * @param pc the pc to set
		 */
		public void setPc(String pc) {
			this.pc = pc;
		}
		
		
}
