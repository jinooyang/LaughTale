import {MangaAnalyze} from "../../../types";
import React, {useEffect, useState} from "react";
import Chart from "react-apexcharts";

export default function ChartResult( props: MangaAnalyze ) {
    const [wordLevels, setWordLevels] = useState([0, 0, 0, 0, 0]); // 각 레벨별 단어 개수를 저장할 배열
    console.log(props);

    useEffect(() => {
        // 단어 레벨별 개수를 계산
        const levelCounts = [0, 0, 0, 0, 0];
        props.chapter.forEach(chapter => {
            chapter.cuts.forEach(cut => {
                cut.sentence.forEach(sentence => {
                    sentence.words.forEach(word => {
                        if (word.level >= 1 && word.level <= 5) {
                            levelCounts[word.level - 1] += 1;
                        }
                    });
                });
            });
        });
        setWordLevels(levelCounts);
    }, [props]);

    return (
        <Chart
            type="donut"
            series={wordLevels}

            options={{
                labels: ["Level 1 단어", "Level 2 단어", "Level 3 단어", "Level 4 단어", "Level 5 단어"],
                chart: {
                    type: 'donut',
                    width: 480,
                },

            }}
        />

    );
}