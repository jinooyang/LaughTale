package com.jshi.laughtale.chapter.dto;

import lombok.Builder;
import lombok.Getter;

public class ChapterListDto {



	@Getter
	@Builder
	public static class Request {

	}

	@Getter
	@Builder
	public static class Response {
		private Long chapterId;
		private Integer chapterNo;
		private String thumbnail;
		private Integer level;
	}
}
