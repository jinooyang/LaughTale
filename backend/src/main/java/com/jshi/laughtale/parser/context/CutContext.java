package com.jshi.laughtale.parser.context;

import com.jshi.laughtale.cut.domain.Cut;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class CutContext {
    private Cut cut;
    @Setter
    private List<SpeechContext> speechContexts;
}
