// package com.jshi.laughtale;
//
// import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.StringTokenizer;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import com.jshi.laughtale.dictionary.domain.Dictionary;
// import com.jshi.laughtale.dictionary.repository.DictionaryRepository;
//
// @SpringBootTest
// class InsertWordDefinitionTest {
// 	@Autowired
// 	DictionaryRepository dictionaryRepository;
//
// 	@Test
// 	void contextLoads() throws IOException {
// 		class JapWord {
// 			List<String> japaneseWord;
// 			String level;
// 			String definition;
//
// 			public JapWord(List<String> japaneseWord, String level, String definition) {
// 				this.japaneseWord = japaneseWord;
// 				this.level = level;
// 				this.definition = definition;
// 			}
//
// 			@Override
// 			public String toString() {
// 				return "Word{" +
// 					"japanseseWord='" + japaneseWord + '\'' +
// 					", level='" + level + '\'' +
// 					", definition=" + definition +
// 					'}';
// 			}
// 		}
// 		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
// 			"C:\\Users\\SSAFY\\Desktop\\laughtale\\S10P22A705\\backend\\src\\test\\java\\com\\jshi\\laughtale\\japaneseWordDefinition.txt"),
// 			"UTF-8"))) {
// 			StringTokenizer st = null;
//
// 			List<JapWord> wordList = new ArrayList<>();
// 			String line;
// 			while ((line = br.readLine()) != null) {
// 				st = new StringTokenizer(line);
// 				if (st.hasMoreTokens()) {
// 					String japaneseWord = st.nextToken();
// 					StringTokenizer jt = new StringTokenizer(japaneseWord, "[/]");
// 					List<String> japaneseWordString = new ArrayList<>();
// 					while (jt.hasMoreTokens()) {
// 						japaneseWordString.add(jt.nextToken());
// 					}
// 					String level = st.nextToken();
// 					StringBuilder def = new StringBuilder();
// 					while (st.hasMoreTokens()) {
// 						def.append(st.nextToken());
// 					}
// 					JapWord word = new JapWord(japaneseWordString, level, def.toString());
// 					wordList.add(word);
// 				}
// 			}
// 			for (int i = 0; i < wordList.size(); i++) {
// 				// System.out.println(i + "번 : " + wordList.get(i).toString());
// 				for (int j = 0; j < wordList.get(i).japaneseWord.size(); j++) {
// 					dictionaryRepository.save(Dictionary.builder()
// 						.japWord(wordList.get(i).japaneseWord.get(j))
// 						.definition(wordList.get(i).definition)
// 						.build());
// 				}
// 			}
// 			// System.out.println("fin");
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
//
// 	}
//
// }
