import React, { useState, useEffect } from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import getWordInfo from "../mypage/getWordInfo.tsx";

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const WordLevelChart = () => {
    const [chartData, setChartData] = useState({
        labels: [],
        datasets: []
    });
    const [hasData, setHasData] = useState(false); // 데이터 유무 상태 추가

    useEffect(() => {
        const fetchData = async () => {
            const levels = [1, 2, 3, 4, 5];
            const wordCounts = await Promise.all(levels.map(level => getWordInfo(level, 0, 1000))); // 페이지 0, size 1000으로 설정
            const data = wordCounts.map(result => result.totalElements); // totalElements는 각 레벨별 단어의 총 개수를 나타냄
            const labels = levels.map(level => `Level ${level}`);

            const dataExist = data.some(count => count > 0); // 어떤 레벨이라도 단어 수가 0보다 큰지 확인
            setHasData(dataExist); // 데이터 유무 상태 업데이트

            setChartData({
                labels: labels,
                datasets: [
                    {
                        label: '단어 수',
                        data: data,
                        borderColor: 'rgb(255, 99, 132)',
                        backgroundColor: [
                            '#58CE7E', '#E8974D', '#DAEE66', '#67B9E2', '#E970D6'
                        ],
                    },
                ],
            });
        };

        fetchData();
    }, []);

    const options = {
        maintainAspectRatio: false,
        indexAxis: 'y' as const,
        elements: {
            bar: {
                borderWidth: 0,
            },
        },
        responsive: true,
        plugins: {
            legend: {
                display: false,
            },
            title: {
                display: false,
            },
        },
        scales: {
            x: {
                ticks: {
                    display: false,
                    color: '#000000',
                    font: {
                        size: 10,
                    },
                },
                grid: {
                    display: false,
                },
            },
            y: {
                ticks: {
                    color: '#000000',
                    font: {
                        size: 8,
                    },
                },
                grid: {
                    display: false,
                },
                barThickness: 24,
                categoryPercentage: 0.8,
                barPercentage: 0.7,
            }
        },
    };

    // 데이터 유무에 따라 차트 또는 메시지 표시
    return hasData ? (
        <Bar data={chartData} options={options} />
    ) : (
        <p>단어장에 단어가 없습니다.</p> // 데이터가 없는 경우 메시지 표시
    );
};

export default WordLevelChart;
