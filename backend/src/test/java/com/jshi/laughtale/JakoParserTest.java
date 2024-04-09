// package com.jshi.laughtale;
//
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;
// import java.util.TreeMap;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import com.jshi.laughtale.jako.domain.JaKo;
// import com.jshi.laughtale.jako.repository.JaKoRepository;
//
// @SpringBootTest
// public class JakoParserTest {
// 	@Autowired
// 	private JaKoRepository jaKoRepository;
// 	static String breaker = "<br />";
//
// 	static Set<String> set = new HashSet<>();
// 	private String[] partsOfSpeech = {"명사·형용동사·サ변격 활용 자동사",
// 		"상1단 활용 자동사",
// 		"보조동사",
// 		"격조사",
// 		"보조동사·サ변격 활용 자동사",
// 		"형용사ク활용",
// 		"종(終)조사",
// 		"계(係)조사",
// 		"구절",
// 		"5단 활용 자동사·보조동사",
// 		"접미어",
// 		"부사·형용동사",
// 		"조수사",
// 		"명사·대명사",
// 		"4단 활용 자동사",
// 		"サ 行 활용 자·타동사",
// 		"명사·トタル형 활용의 형용동사",
// 		"명사·ニナ형 활용·トタル형 활용의 형용동사",
// 		"감동사·형용동사",
// 		"접속사",
// 		"감동사",
// 		"부사·サ변격 활용 자동사",
// 		"하2단 활용 자동사",
// 		"조사",
// 		"접속조사·종(終)조사",
// 		"부사·トタル형 활용의 형용동사",
// 		"접두어",
// 		"カ 行 활용 자동사",
// 		"명사·トタル형 활용의 형용동사·サ변격 활용 타동사",
// 		"상1단 활용 타동사",
// 		"ナリ활용의 형용동사",
// 		"명사·トタル형 활용의 형용동사·サ변격 활용 자타동사",
// 		"5단 활용 타동사",
// 		"하1단 활용 타동사",
// 		"하1단 활용 자타동사",
// 		"명사·형용동사·부사",
// 		"トタル형 활용의 형용동사·부사",
// 		"접속조사",
// 		"부사·형용동사·サ변격 활용 자타동사",
// 		"부사",
// 		"명사·ナリ활용의 형용동사",
// 		"부사· タリ활용의 형용동사",
// 		"형용사",
// 		"명사·トタル형 활용의 형용동사·サ변격 활용 자동사",
// 		"タリ활용의 형용동사",
// 		"サ 行 활용 자동사",
// 		"상2단 활용 자동사",
// 		"4단 활용 자동사·보조동사",
// 		"명사·サ변격 활용 타동사",
// 		"5단 활용 자동사",
// 		"명사",
// 		"부사·형용동사 ·サ변격 활용 자타동사",
// 		"명사·부사 ·サ변격 활용 자동사",
// 		"ナ 行 활용 자동사",
// 		"サ 行 활용 타동사",
// 		"병렬조사",
// 		"명사·형식 명사",
// 		"명사·サ변격 활용 자동사",
// 		"연어",
// 		"부조사",
// 		"トタル형 활용의 형용동사",
// 		"하1단 활용 자동사",
// 		"ニナ형 활용·トタル형 활용의 형용동사",
// 		"상1단 활용 자·타동사",
// 		"명사·サ변격 활용 자·타동사",
// 		"5단 활용 자·타동사",
// 		"조동사",
// 		"연체사",
// 		"ラ 行 활용 자동사",
// 		"명사·부사",
// 		"부사·접속사",
// 		"형식명사",
// 		"대명사",
// 		"대명사·부사",
// 		"명사·형용동사",
// 		"4단 활용 자동사·상2단 활용 자동사",
// 		"형용동사·サ변격 활용 자동사",
// 		"형용사シク활용",
// 		"형용동사",
// 		"형용동사·부사",
// 		"명사·형용동사·サ변격 활용 타동사",
// 		"4단 활용 타동사",
// 		"명사·조수사",
// 		"하2단 활용 타동사",
// 		"명사·접미사"};
//
// 	@Test
// 	public void jakoParser() {
// 		set.addAll(Arrays.asList(partsOfSpeech));
// 		List<JaKo> jaKoList = jaKoRepository.findAll();
// 		System.out.println(jaKoList.size());
// 		int idx = 0;
// 		for (JaKo jaKo : jaKoList) {
// 			String word = jaKo.getLangFrom();
// 			String langTo = jaKo.getLangTo();
// 			Map<String, String> map = new TreeMap<>();
// 			parseString(langTo, map);
// 			//파싱했는데 맵이 비어있는 경우
// 			createDefinitionForEmptyMap(map, langTo);
// 			StringBuilder newDef = new StringBuilder();
// 			for (String key : map.keySet()) {
// 				newDef.append("<div class=\"word\"><span class=\"pos\">");
// 				newDef.append(key);
// 				newDef.append("</span> : <span class=\"def\">");
// 				newDef.append(map.get(key));
// 				newDef.append("</span></div>");
// 			}
// 			// System.out.println(newDef);
// 			jaKo.setParsedDef(newDef.toString());
// 			jaKoRepository.save(jaKo);
// 			//<div class="word"><span class = "pos">명사</span> : <span class = "def">뜻</span></div>
//
// 		}
//
// 	}
//
// 	private static void createDefinitionForEmptyMap(Map<String, String> map, String definition) {
// 		if (map.isEmpty()) {
// 			// definition = definition.replaceAll("<strong>", ",").replaceAll("</strong>", ",");
// 			if (definition.startsWith(","))
// 				definition = definition.substring(1);
// 			String[] tokens = definition.split(breaker);
// 			StringBuilder sb = new StringBuilder();
// 			for (int t = 1; t < tokens.length; t++) {
// 				sb.append(tokens[t]);
// 			}
// 			definition = sb.toString();
// 			if (definition.startsWith("⇒")) {
// 				definition = definition.substring(1);
// 			}
// 			definition = definition.trim();
// 			map.put("뜻", definition);
// 		}
// 	}
//
// 	private void parseString(String input, Map<String, String> map) {
// 		String[] tokens = input.split(breaker);
// 		for (String now : tokens) {
// 			boolean defi = false;
// 			String key = null;
// 			int startIndex = 0;
// 			for (int i = 0; i < Math.min(100, now.length()); i++) {
// 				//                if(now.charAt(i)=='[') System.out.println(now.charAt(i+1) + " " + isKorean(now.charAt(i+1)));
//
// 				if (now.charAt(i) == '['
// 					//                        && isKorean(now.charAt(i + 1))
// 				) {
// 					defi = true;
// 					startIndex = i;
// 				}
// 			}
// 			int idx = startIndex + 1;
// 			if (defi) {
// 				StringBuilder pos = new StringBuilder();
// 				while (idx < now.length() && now.charAt(idx) != ']') {
// 					pos.append(now.charAt(idx));
// 					idx++;
// 				}
// 				key = pos.toString();
// 				String value = now.substring(idx + 1).trim()
// 					// .replaceAll("<strong>", ", ").replaceAll("</strong>", ", ")
// 					;
// 				if (!value.isEmpty() && !isNotADefinition(value) && !map.containsKey(key) && set.contains(key)) {
// 					map.put(key, value);
// 				}
// 			}
//
// 		}
//
// 	}
//
// 	private boolean isNotADefinition(String value) {
// 		return ((value.startsWith("『문어』")) && value.length() == 4)
// 			|| value.startsWith("⇒")
// 			|| (value.charAt(0) == '《' && value.charAt(value.length() - 1) == '》');
// 	}
// }
