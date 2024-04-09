// package com.jshi.laughtale;
//
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import com.jshi.laughtale.chapter.domain.Chapter;
// import com.jshi.laughtale.chapter.repository.ChapterRepository;
// import com.jshi.laughtale.cut.domain.Cut;
// import com.jshi.laughtale.cut.repository.CutRepository;
// import com.jshi.laughtale.speech.domain.Speech;
// import com.jshi.laughtale.speech.repository.SpeechRepository;
// import com.jshi.laughtale.worddata.domain.WordData;
// import com.jshi.laughtale.worddata.repository.WordDataRepository;
// import com.jshi.laughtale.wordlist.domain.WordList;
// import com.jshi.laughtale.wordlist.repository.WordListRepository;
// import com.jshi.laughtale.wordlist.service.WordListService;
//
// import jakarta.persistence.Tuple;
// import lombok.RequiredArgsConstructor;
//
// @SpringBootTest
//
// public class ChapterLevelTest {
// 	@Autowired
// 	private ChapterRepository chapterRepository;
// 	@Autowired
// 	private WordListRepository wordListRepository;
//
// 	// public class Point {
// 	// 	int cnt;
// 	// 	int sum;
// 	//
// 	// 	public Point(int cnt, int sum) {
// 	// 		this.cnt = cnt;
// 	// 		this.sum = sum;
// 	// 	}
// 	// }
//
// 	// public class ChapterWords {
// 	// 	Long chapterId;
// 	// 	Long level;
// 	// 	Long levelCnt;
// 	//
// 	// 	public ChapterWords(Long chapterId, Long level, Long levelCnt) {
// 	// 		this.chapterId = chapterId;
// 	// 		this.level = level;
// 	// 		this.levelCnt = levelCnt;
// 	// 	}
// 	// }
//
// 	@Test
// 	public void calculateChapterLevel() {
//
// 		// List<ChapterWords> cwList = new ArrayList<>();
// 		long chapterId = 182;
// 		List<Tuple> tupleList = wordListRepository.findCalculatedChapterLevel(chapterId);
// 		long totalSum = 0;
// 		long totalCnt =0;
// 		for (Tuple tuple : tupleList) {
// 			int level = tuple.get("level", Integer.class);
// 			// ChapterWords chapterWords
// 			// 	= new ChapterWords(
// 			// 	tuple.get("chapterId", Long.class),
// 			// 	level,
// 			// 	tuple.get("levelcnt", Long.class)
// 			// );
// 			// cwList.add(chapterWords);
// 			long cnt = tuple.get("levelcnt", Long.class);
// 			totalSum += level * cnt;
// 			totalCnt +=  cnt;
// 		}
// 		double avg = (double)totalSum/totalCnt;
// 		System.out.println(getLevel(avg));
// 		// Map<Long, Point> map = new HashMap<>();
// 		// for (ChapterWords cw : cwList) {
// 		// 	long id = cw.chapterId;
// 		// 	long level = cw.level;
// 		// 	long cnt = cw.levelCnt;
// 		// 	if (!map.containsKey(id))
// 		// 		map.put(id, new Point(0, 0));
// 		// 	Point p = map.get(id);
// 		// 	p.cnt += cnt;
// 		// 	p.sum += level * cnt;
// 		// 	map.put(id, p);
// 		// 	//1.40 ~ 1.78    (669개)
// 		//
// 		// 	//1랩 : ~ 1.67 (223 개)
// 		//
// 		// 	//2랩 :1.65~ 1.73(377번까지 154개)
// 		//
// 		// 	//3랩 : ~1.8 (520번까지 143개)
// 		//
// 		// 	//4랩  : ~1.9 626까지 (106개)
// 		//
// 		// 	//5랩 : 1.9 +@  43개
// 		//
// 		// 	// System.out.println("id : " + id + ", level : " + level + " , cnt : " + cnt);
// 		// }
// 		// StringBuilder sb = new StringBuilder();
// 		// // for(long i = 7;i<=770;i++){
// 		// // 	if(!map.containsKey(i))sb.append(i + ", ");
// 		// // }
// 		// // System.out.println(sb.toString());
// 		// // for(Long id  : map.keySet()){
// 		// // 	Point  point = map.get(id);
// 		// // 	System.out.println(id + " : " +"sum : " + point.sum + "cnt : " + point.cnt  + "avg : " + (double)point.sum/point.cnt);
// 		// //
// 		// // }
// 		// List<Chapter> chapterList = chapterRepository.findAll();
// 		// // System.out.println("!!! : " + chapterList.size());
// 		//
// 		// for (Chapter chapter : chapterList) {
// 		// 	if(!map.containsKey(chapter.getId())){
// 		// 		// System.out.println();
// 		// 		sb.append("챕터에 정보가 누락되었음 : ").append(chapter.getId()).append("\n");
// 		// 		chapter.setLevel(5);
// 		// 		chapterRepository.save(chapter);
// 		// 		System.out.println(chapter.getId() + "의 레밸은 : " + 5);
// 		// 		continue;
// 		// 	}
// 		//
// 		//
// 		//
// 		// 	Point po = map.get(chapter.getId());
// 		// 	double avg = (double)po.sum / po.cnt;
// 		// 	chapter.setLevel(getLevel(avg));
// 		// 	chapterRepository.save(chapter);
// 		// 	System.out.println(chapter.getId() + "의 레밸은 : " + chapter.getLevel());
// 		// }
// 		// System.out.println(sb);
// 	}
//
// 	public int getLevel(double avg) {
//
// 		//1랩 : ~ 1.67 (223 개)
//
// 		//2랩 :1.65~ 1.73(377번까지 154개)
//
// 		//3랩 : ~1.8 (520번까지 143개)
//
// 		//4랩  : ~1.9 626까지 (106개)
//
// 		//5랩 : 1.9 +@  43개
//
// 		if (avg <= 1.67)
// 			return 1;
// 		if (avg <= 1.73)
// 			return 2;
// 		if (avg <= 1.8)
// 			return 3;
// 		if (avg <= 1.9)
// 			return 4;
// 		return 5;
//
// 	}
//
// }
