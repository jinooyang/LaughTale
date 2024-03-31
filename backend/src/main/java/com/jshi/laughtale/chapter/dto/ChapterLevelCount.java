package com.jshi.laughtale.chapter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ChapterLevelCount {
	@Getter
	@Builder
	@Setter
	public static class Response {
		private int level;
		private long count;
	}
}
