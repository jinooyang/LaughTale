import React, { useEffect, useRef, useState } from 'react';
import * as d3 from 'd3';
import client from "../../apis";

const Chart = () => {
    const svgRef = useRef(null);
    const [viewRange, setViewRange] = useState('all');

    const [dataPoints, setDataPoints] = useState([]);
    const [showTooltip, setShowTooltip] = useState(false); // 툴팁 표시 상태

    // 툴팁 토글 함수

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await client.get('/wordhistory/data'); // 실제 API 엔드포인트 URL로 교체

                // API로부터 받은 데이터를 사용하여 dataPoints 상태를 업데이트
                const formattedData = response.data.historyList.map(item => ({
                    level: item.level,
                    repeatCnt: item.repeatCnt,
                    date: new Date(item.time),
                    title: item.word,
                }));
                setDataPoints(formattedData);
            } catch (error) {
                console.error('Failed to fetch data:', error);
            }
        };
        fetchData();
    }, []); // 빈 배열을 전달하여 컴포넌트가 마운트될 때만 실행되도록 함

    // 함수 배열
    const functions = [
        (x :number) => 184 / (Math.pow(Math.log(x), 1.25) + 1.84), //1레벨 1회 학습
        (x :number) => 368 / (Math.pow(Math.log(x), 1.25) + 3.68), //1레벨 2회 학습
        (x :number) => 736 / (Math.pow(Math.log(x), 1.25) + 7.36), //1레벨 3회 학습
        (x :number) => 1472 / (Math.pow(Math.log(x), 1.25) + 14.72), //1레벨 4회 학습
        (x :number) => 2944 / (Math.pow(Math.log(x), 1.25) + 29.44), //1레벨 5회 학습
        (x :number) => 2*184 / (Math.pow(Math.log(x), 1.25) + 1.84), //2레벨 1회 학습
        (x :number) => 2*368 / (Math.pow(Math.log(x), 1.25) + 3.68), //2레벨 2회 학습
        (x :number) => 2*736 / (Math.pow(Math.log(x), 1.25) + 7.36), //2레벨 3회 학습
        (x :number) => 2*1472 / (Math.pow(Math.log(x), 1.25) + 14.72), //2레벨 4회 학습
        (x :number) => 2*2944 / (Math.pow(Math.log(x), 1.25) + 29.44), //2레벨 5회 학습
        (x :number) => 3*184 / (Math.pow(Math.log(x), 1.25) + 1.84), //3레벨 1회 학습
        (x :number) => 3*368 / (Math.pow(Math.log(x), 1.25) + 3.68), //3레벨 2회 학습
        (x :number) => 3*736 / (Math.pow(Math.log(x), 1.25) + 7.36), //3레벨 3회 학습
        (x :number) => 3*1472 / (Math.pow(Math.log(x), 1.25) + 14.72), //3레벨 4회 학습
        (x :number) => 3*2944 / (Math.pow(Math.log(x), 1.25) + 29.44), //3레벨 5회 학습
        (x :number) => 4*184 / (Math.pow(Math.log(x), 1.25) + 1.84), //4레벨 1회 학습
        (x :number) => 4*368 / (Math.pow(Math.log(x), 1.25) + 3.68), //4레벨 2회 학습
        (x :number) => 4*736 / (Math.pow(Math.log(x), 1.25) + 7.36), //4레벨 3회 학습
        (x :number) => 4*1472 / (Math.pow(Math.log(x), 1.25) + 14.72), //4레벨 4회 학습
        (x :number) => 4*2944 / (Math.pow(Math.log(x), 1.25) + 29.44), //4레벨 5회 학습
        (x :number) => 5*184 / (Math.pow(Math.log(x), 1.25) + 1.84), //5레벨 1회 학습
        (x :number) => 5*368 / (Math.pow(Math.log(x), 1.25) + 3.68), //5레벨 2회 학습
        (x :number) => 5*736 / (Math.pow(Math.log(x), 1.25) + 7.36), //5레벨 3회 학습
        (x :number) => 5*1472 / (Math.pow(Math.log(x), 1.25) + 14.72), //5레벨 4회 학습
        (x :number) => 5*2944 / (Math.pow(Math.log(x), 1.25) + 29.44), //5레벨 5회 학습
    ];

    // 레벨별 색상을 정의합니다.
    const levelColors = ['#58CE7E', '#E8974D', '#DAEE66', '#67B9E2', '#E970D6'];

    // eslint-disable-next-line react-hooks/exhaustive-deps
    useEffect(() => {
        const svg = d3.select(svgRef.current);
        svg.selectAll("*").remove(); // 기존 그래프를 지웁니다.

        const width = 640;
        const height = 550;
        const margin = { top: 20, right: 20, bottom: 50, left: 50 };
        const innerWidth = width - margin.left - margin.right;
        const innerHeight = height - margin.top - margin.bottom;

        // 스케일 설정
        const xScale = d3.scaleLinear()
            .domain([1, 500])
            .range([0, innerWidth]); // xScale의 시작점을 0으로 설정합니다.

        const yScale = d3.scaleLinear()
            .domain([0, 500])
            .range([innerHeight, 0]); // yScale의 시작점을 innerHeight로 설정하여 아래쪽에서 시작하도록 합니다.

        const g = svg.append('g').attr('transform', `translate(${margin.left},${margin.top})`);

        g.append('g')
            .call(d3.axisLeft(yScale).ticks(5).tickFormat(d => `${+d / 100}`))
            .append("text")
            .attr("fill", "#000")
            .attr("transform", "rotate(-90)")
            .attr("x", -210)
            .attr("y", -30)
            .attr("dy", "0.71em")
            .attr("text-anchor", "end")
            .text("예측 기억량");

        g.append('g')
            .call(d3.axisBottom(xScale))
            .attr('transform', `translate(0,${innerHeight})`)
            .append("text")
            .attr("fill", "#000")
            .attr("y", 40)
            .attr("x", innerWidth / 2)
            .attr("text-anchor", "end")
            .text("학습경과시간(분)");

        // line 생성기 설정
        const line = d3.line<[number, number]>() // 타입 어설션 추가
            .x(d => xScale(d[0]))
            .y(d => yScale(d[1]))
            .curve(d3.curveMonotoneX);


        // 필터링 로직을 추가하여 현재 선택된 범위에 따라 표시할 함수를 결정합니다.
        const filteredFunctions = viewRange === 'all'
            ? functions
            : functions.slice(parseInt(viewRange.split('-')[0]), parseInt(viewRange.split('-')[1]) + 1);

        // 각 레벨별로 그래프를 그립니다.
        filteredFunctions.forEach((func, index) => {
            const data: [number, number][] = Array.from({ length: 500 }, (_, i) => [i + 1, func(i + 1)]);
            let colorIndex;
            if (viewRange === 'all') {
                colorIndex = Math.floor(index / 5) % levelColors.length; // 전체보기일 때는 기존 로직을 사용
            } else {
                // 특정 레벨 보기일 때는 viewRange에서 레벨을 계산
                const level = parseInt(viewRange.split('-')[0]) / 5 + 1;
                colorIndex = Math.floor(level) - 1; // viewRange가 0 시작이므로 -1
            }
            g.append('path')
                .datum(data)
                .attr('fill', 'none')
                .attr('stroke', levelColors[colorIndex])
                .attr('d', line);
        });

        // 데이터 포인트를 차트에 표시하는 로직
        dataPoints.forEach(dataPoint => {
            // level과 repeatCnt를 통해 함수 배열의 인덱스를 계산
            const funcIndex = (dataPoint.level - 1) * 5 + dataPoint.repeatCnt - 1;
            const selectedFunction = functions[funcIndex];

            // 현재 시간과 데이터의 date 차이를 분 단위로 계산
            const diffInMinutes = Math.floor((new Date().getTime() - new Date(dataPoint.date).getTime()) / (1000 * 60)) + 1;
            // 함수를 사용하여 y 값을 계산
            const yValue = selectedFunction(diffInMinutes);

            // 레벨에 따른 색상을 가져옵니다.
            const color = levelColors[dataPoint.level - 1] || 'black';

            // 점을 차트에 추가
            const group = d3.select(svgRef.current).append('g');
            group.append('circle')
                .attr('cx', xScale(diffInMinutes)+50)
                .attr('cy', yScale(yValue)+20)
                .attr('r', 3)
                .attr('fill', color)
                // .attr('stroke', 'black') //테두리
                .attr('stroke-width', 1)
                .append('title')
                .text(`${dataPoint.title}\nLevel : ${dataPoint.level}\n학습횟수 : ${dataPoint.repeatCnt}\n경과시간 : ${diffInMinutes}분\n획득포인트 :${yValue/100}`);
        });

    }, [viewRange, dataPoints]);


    //레벨별 버튼 생성
    const renderLevelButtons = () => {
        const buttons = [];
        // 'all'은 특별한 경우이므로 별도로 처리합니다.
        buttons.push(
            <button
                key="all"
                onClick={() => setViewRange('all')}
                className="bg-gray-200 text-gray-800 hover:bg-gray-300 py-2 px-4 rounded transition duration-200 ease-in-out"
            >
                전체보기
            </button>
        );

        // 레벨별 색상 클래스
        const colorClasses = [
            'bg-custom-bronze ',
            'bg-custom-silver ',
            'bg-custom-gold ',
            'bg-custom-platinum ',
            'bg-custom-diamond',
        ];

        // 레벨별 버튼을 생성
        colorClasses.forEach((colorClass, index) => {
            const level = index + 1; // 레벨은 1부터 시작합니다.
            buttons.push(
                <button
                    key={level}
                    onClick={() => setViewRange(`${(level - 1) * 5}-${level * 5 - 1}`)}
                    className={`py-2 px-4 rounded transition duration-200 ease-in-out ${colorClass} text-white brightness-100 hover:brightness-125`}
                >
                    {level}레벨
                </button>
            );
        });
        return buttons;
    }; //레벨 버튼 생성


    return (
        <div className="relative">
            <svg ref={svgRef} width={650} height={550}/>

            {/* 툴팁 토글 버튼 */}
            <button
                onMouseEnter={() => setShowTooltip(true)}
                onMouseLeave={() => setShowTooltip(false)}
                className="absolute top-5 right-10 w-8 h-8 bg-gray-300 rounded-full flex items-center justify-center text-lg font-bold cursor-pointer"
                aria-label="정보"
            >
                ?
            </button>

            {/* 툴팁 내용 */}
            {showTooltip && (
                <div
                    className="absolute top-10 right-10 w-300px p-4 bg-white rounded shadow-lg z-10" // w-64 대신 w-300px 를 사용하거나 적절한 값을 설정하세요.
                    style={{ right: '50px', top: '50px', width: '500px' }} // 인라인 스타일에서 width 값을 설정합니다.
                >
                    <div>
                        <div className="mt-5 ml-9 mb-2 mr-9 ">
                            <div className="font-bold mb-5 text-4xl">에빙하우스 망각곡선</div>
                            망각곡선 가설은 시간이 지남에 따라 기억이 남아있는 감소의 정도를 말하는 가설로,
                            이 곡선은 기억을 유지하려는 시도가 없을 때 정보가 시간이 지남에 따라 손실되는 정도를 보여줍니다.<br/>
                            곡선을 살펴보면 시간이 지남에 따라 잊어버리는 속도가 줄어듦을 알 수 있습니다.
                        </div>

                        <div className="mt-5 ml-9 mb-2 mr-9 ">
                            이러한 망각곡선 주기에 착안하여 적절한 시점에 퀴즈를 통한 반복학습을 통해
                            장기기억으로 유도하고 있습니다.<br/><br/>
                            ※ 해당 그래프는 학습 단어 망각곡선에는 최근에 퀴즈에서 푼 단어의 예측 기억량을
                            시각화한 것입니다.
                        </div>
                    </div>
                </div>
            )}

            <div className="flex flex-wrap gap-2 justify-center">
                {renderLevelButtons()}
            </div>
        </div>
    );
};


export default Chart;