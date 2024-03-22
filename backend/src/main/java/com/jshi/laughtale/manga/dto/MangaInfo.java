package com.jshi.laughtale.manga.dto;

import lombok.Builder;
import lombok.Getter;

public class MangaInfo {
	@Getter
	@Builder
	public static class Request {

	}

	@Getter
	@Builder
	public static class Response {
		Long id;
		String title;
		String category;
		String author;
		String summary;
		String thumbnail;
		Integer level;

	}
}
