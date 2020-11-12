package org.kpagan.clash.clashserver.util;

public final class ClashUtils {

	public static final String getTag(String tag) {
		if (tag == null) {
			return "";
		}
		tag = tag.trim();
		if (tag.startsWith("#")) {
			return tag;
		} else {
			return "#".concat(tag);
		}
	}
	
	public static final int nullSafeAdd(Integer i1, Integer i2) {
		int ret = 0;
		if (i1 != null) {
			ret += i1;
		}
		if (i2 != null) {
			ret += i2;
		}
		return ret;
	}
}
