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
}
