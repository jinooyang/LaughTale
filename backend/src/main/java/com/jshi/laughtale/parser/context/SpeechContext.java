package com.jshi.laughtale.parser.context;

import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.worddata.domain.WordData;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class SpeechContext {
    private Speech speech;
    @Setter
    private List<WordData> wordDataList;
}
