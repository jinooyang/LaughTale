package com.jshi.laughtale.quiz.controller;

import com.jshi.laughtale.quiz.dto.QuizResult;
import com.jshi.laughtale.quiz.dto.QuizWord;
import com.jshi.laughtale.quiz.service.QuizService;
import com.jshi.laughtale.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/new/{chapterId}")
    public ResponseEntity<List<QuizWord>> makeNewQuiz(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                      @PathVariable int chapterId) {
        //회원 아이디 조회
        Long memberId = customUserDetails.getId();
        //해당 사용자가 퀴즈를 이미 만들었다면 삭제하고 진행한다 -> ex) 새로고침 상황
        quizService.deleteMemberQuiz(memberId);
        //새로운 퀴즈를 생성한다
        List<QuizWord> quiz = quizService.makeNewQuiz(memberId, chapterId);
        //생성된 퀴즐르 DB에 저장한다
        quizService.saveNewQuiz(quiz, memberId);

        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/solve")
    public ResponseEntity<List<Integer>> quizResult(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                    @RequestBody QuizResult.Request request) {
        Long memberId = customUserDetails.getId();
        // TODO : 퀴즈 결과가 왔는데 퀴즈가 없던경우(예외처리)
        //퀴즈 결과를 받아온다
        List<Integer> result = quizService.saveQuizResult(memberId, request.getAnswer());

        //저장된 퀴즈를 삭제한다
        quizService.deleteMemberQuiz(memberId);
        return ResponseEntity.ok(result);
    }

}
