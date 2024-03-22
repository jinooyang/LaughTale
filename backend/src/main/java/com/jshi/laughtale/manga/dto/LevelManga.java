package com.jshi.laughtale.manga.dto;

import lombok.Builder;
import lombok.Getter;

public class LevelManga {
	@Getter
	@Builder
	public static class Request {

	}

	@Getter
	@Builder
	public static class Response {
		private String thumbnail;
		private String title;
		private Integer level;
	}
}
