package com.timete.neo4j.constants;

import org.apache.log4j.Logger;

public class Contants {

	private static Logger log = Logger.getLogger(Contants.class.getName());

	public enum USER_TYPE {
		NORMAL(0), VERIFIED(1);

		public int value;

		private USER_TYPE(int value) {
			this.value = value;
		}

		public static USER_TYPE getFormValue(int value) {
			if (value == 0) {
				return NORMAL;
			} else if (value == 1) {
				return VERIFIED;
			}
			return NORMAL;
		}

		public static USER_TYPE getFormValue(Object obj) {
			int value=0;
			try {
				value=Integer.parseInt(obj.toString());
			} catch (Exception e) {
				log.error("Error", e);
				value=0;
			}
			if (value == 0) {
				return NORMAL;
			} else if (value == 1) {
				return VERIFIED;
			}
			return NORMAL;
		}
	}
}
