package com.jshi.laughtale.chapter.dto;

import com.jshi.laughtale.chapter.domain.Chapter;

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
		private Long id;
		private Integer chapterNo;

	}
}
