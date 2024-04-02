package com.jshi.laughtale.worddata.dto;

import lombok.Builder;
import lombok.Getter;

public class WordCloudData {

	@Getter
	@Builder
	public static class Response {
		private String text;
		private Integer value;
	}
}
