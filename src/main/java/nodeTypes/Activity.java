package nodeTypes;

import java.time.LocalDateTime;

public class Activity {
		private String id, user;
		private LocalDateTime date;
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
		 * @return the user
		 */
		public String getUser() {
			return user;
		}
		/**
		 * @param user the user to set
		 */
		public void setUser(String user) {
			this.user = user;
		}
		/**
		 * @return the date
		 */
		public LocalDateTime getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		
		
}
