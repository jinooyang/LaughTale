import Slider from "react-slick";
import React, {useEffect, useState} from 'react';
import Icon from '@mdi/react';
import { mdiVolumeHigh } from '@mdi/js';
import ModalCarousel from "./ModalCarousel.tsx";
import client from "../../apis";
import getWordExample from "./getWordExample.tsx";
import { useQueryClient } from '@tanstack/react-query';
import SpeechButton from "../../components/common/SpeechButton.tsx";

const settings = {
    dots: false, // 점으로 페이지 위치 표시
    infinite: false, // 무한 슬라이딩 비활성화
    speed: 500,
    slidesToShow: 1, // 한 번에 보여질 슬라이드 페이지 수
    slidesToScroll: 1 // 스크롤할 때마다 넘어갈 슬라이드 페이지 수
};

function DefinitionModal({ isOpen, onClose, definition  }) {
    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="bg-[#ffffff] p-4 rounded-lg">
                <div className="flex justify-between items-center w-full p-3">
                    <div className="text-black flex-1 text-center">
                        단어 해석
                    </div>
                    <button
                        onClick={onClose}
                        className="text-black rounded-full bg-grey-500 hover:bg-grey-700">
                        X
                    </button>
                </div>
                <div className="flex justify-items-center">
                    {/*단어의 뜻이 클 수 있으므로 오른쪽 영역을 차지하도록 한다*/}
                    <div className="flex justify-center text-white p-12">
                        <div className="rounded-xl bg-[#73ABE5] p-12">
                            <div>
                                <h2 className="text-2xl text-black font-bold"
                                    dangerouslySetInnerHTML={{__html: definition}}></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};



function ExampleModal({isOpen, onClose, example}) {
    const [selectedSentenceIndex, setSelectedSentenceIndex] = useState(0);
    const handleSentenceClick = (index) => {
        setSelectedSentenceIndex(index);
    };

    if (!isOpen) return null;

        return (
            <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
                <div className="bg-white p-4 rounded-lg">
                    <div className="flex justify-between items-center w-full p-3">
                        <div className="text-black flex-1 text-center">
                            예문 보기
                        </div>
                        <button
                            onClick={onClose}
                            className="text-black rounded-full bg-grey-500 hover:bg-grey-700">
                            X
                        </button>
                    </div>
                    <hr className="mt-6 mb-3 border-1 border-black"/>
                    <div className="grid grid-cols-5 gap-4 p-3 mb-[15px]">
                        <div className="flex col-span-2 justify-end">
                            <ModalCarousel example={example} selectedIndex={selectedSentenceIndex}/>
                        </div>

                        <div className="flex flex-col col-span-3 justify-items-center ">
                            <div className="flex flex-col justify-center  h-[500px] text-black p-12 ">
                                <div className="overflow-y-scroll scrollbar-hide ">
                                    {example.speeches.map((item, index) => (
                                        <div key={index}
                                             className="scale-90 rounded-xl bg-[#73ABE5] mb-6 p-6 hover:text-black hover:scale-95 transform transition-transform duration-300 hover:bg-gradient-to-r from-[#4EDBDE] from-5% to-[#8675DA]"
                                             onClick={() => handleSentenceClick(index)}>
                                            <div className="flex items-center justify-start ">
                                                <div className="mr-3">
                                                    <h2>{item.sentence}<SpeechButton sentence={item.sentence} style={{color:'black', width:"24px", height:'24px'}} /></h2>
                                                </div>
                                            </div>
                                        </div>

                                    ))}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
};

//tts
import axios from 'axios';
import {useQuery} from "@tanstack/react-query";
import getWordInfo from "./getWordInfo.tsx";

async function playTTS(text) {
    const clientId = 'slivj7sa6g'; // 클라이언트 ID
    const clientSecret = 'tJF92nSeETcCmlFMeMwXRJ0HKote1gKECwGVjGX2'; // 클라이언트 시크릿
    try {
        const response = await axios.post('https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts', `speaker=shinji&volume=0&speed=0&pitch=0&format=mp3&text=${text}`, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-NCP-APIGW-API-KEY-ID': clientId,
                'X-NCP-APIGW-API-KEY': clientSecret,
            },
            responseType: 'arraybuffer' // 바이너리 데이터를 위한 설정
        });
        const audioUrl = URL.createObjectURL(new Blob([response.data], {type: 'audio/mp3'}));
        const audio = new Audio(audioUrl);
        audio.play();
    } catch (error) {
        console.error('TTS play error:', error);
    }
}

const   CustomSlider = ({level,page,size}) => {
    // 레벨별 단어 목록 데이터
    const { data: wordData, isLoading, error } = useQuery({
        queryKey: ['wordBook', level, page, size],
        queryFn: () => getWordInfo(level, page, size)
    });

    useEffect(() => {
        console.log('단어장에 대한 데이터:', wordData);
    }, [wordData, isLoading]);


    const [isModalOpenW, setIsModalOpenW] = useState(false);
    const closeModalW = () => setIsModalOpenW(false);
    // 단어 해석 보기
    const [definition, setDefinition] = useState('');
    const openModalWithDefinition = (slideone) => {
        setDefinition(slideone.definition);
        setIsModalOpenW(true);
    };

    const [exampleId, setExampleId] = useState(null);
    const [isModalOpenE, setIsModalOpenE] = useState(false);
    const closeModalE = () => {
        setIsModalOpenE(false);
        setExampleId(null); // 모달을 닫을 때 exampleId 초기화
    };

    const { data: example } = useQuery({
        queryKey: ['example', exampleId],
        queryFn: () => getWordExample(exampleId),
        enabled: !!exampleId,
    });

    useEffect(() => {
        if (!isLoading && example && !error && exampleId) {
            console.log("3순서실행");
            setIsModalOpenE(true);
        }
    }, [isLoading, example, error, exampleId]);

    const openModalWithExample = (id) => {
        setExampleId(id);
        console.log("1순서실행",id);
        // setIsModalOpenE(true);
    };

    const queryClient = useQueryClient();

    async function handleRemoveClick(id) {
        try {
            await client.delete(`/word-book/${id}`);
            console.log("단어 삭제완료");
            // 단어 삭제 후 업데이트
            // @ts-ignore
            queryClient.invalidateQueries(['wordBook']);
        } catch (error) {
            console.error("There was an error!", error);
        }
    }

    // tts 실행함수
    const handleIconClick = (word) => {
        playTTS(word);
    };

    // const bgColorClass = level === 1 ? 'bg-[#56cd7c]' :
    //                                                         level === 2 ? 'bg-[#e99648]' :
    //                                                         level === 3 ? 'bg-[#f54242]' : 'bg-[#56cd7c]'; // 기본값
    // const borderColorClass = level === 1 ? 'border-[#90F880]' :
    //                                                             level === 2 ? 'border-[#8caef5]' :
    //                                                             level === 3 ? 'border-[#f56666]' : 'border-[#90F880]'; // 기본값
    //
    // const gradientFromClass = level === 1 ? 'from-[#83E893]' :
    //                                                             level === 2 ? 'from-[#77a7f7]' :
    //                                                                 level === 3 ? 'from-[#f57575]' : 'from-[#83E893]'; // 기본값
    //
    // const gradientToClass = level === 1 ? 'to-[#059C54]' :
    //                                                             level === 2 ? 'to-[#3761d5]' :
    //                                                                 level === 3 ? 'to-[#d52828]' : 'to-[#059C54]'; // 기본값

    return (
            <div className="w-[1120px] text-white font-bold flex flex-wrap text-4xl">
                    {/*<Slider {...settings}>*/}
                    {/*    {slides.map(slide => (*/}
                    {/*        <div className="flex items-center rounded-xl overflow-hidden w-[500px] h-[480px]">*/}
                    {/*            <div className="flex flex-wrap justify-center items-center">*/}
                    {wordData && wordData.content.map(slideone => (
                        <div key={slideone.id}>
                            <div
                                className="group p-6 flex justify-center items-center transform hover:scale-110 transition duration-300 relative">
                                <div
                                    className="absolute top-0 right-0 transform translate-y-10 -translate-x-12 text-red-600 w-6 h-6 flex justify-center items-center opacity-0 group-hover:opacity-100 transition-opacity duration-300 pointer-events-auto"
                                    onClick={()=>handleRemoveClick(slideone.id)}>
                                    &ndash;
                                </div>
                                <div
                                className={"bg-[#73ABE5] text-white rounded-xl overflow-hidden w-[250px] h-[120px] flex justify-center items-center shadow-sm border-2 border-[#73ABE5] group-hover:bg-gradient-to-b from-[#4EDBDE] to-[#8675DA] hover:border-transparent hover:text-black transition-all duration-300"}
                                >
                                    <div>
                                        <div className="flex justify-items-center space-x-2 p-3">
                                            <div className="text-black font-semibold hover:text-black">
                                                {slideone.word}
                                            </div>
                                            <SpeechButton sentence={slideone.word} style={{width:"3rem", display:"inline", marginLeft:'1rem'}}/>
                                            {/*<div onClick={() => handleIconClick(slideone.word)}>*/}
                                            {/*    <Icon className="hoverIcon" path={mdiVolumeHigh} size={1.5}/>*/}
                                            {/*</div>*/}
                                        </div>
                                        <div className="flex justify-items-center">
                                            <button
                                                className="text-black p-3 text-2xl hover:text-white"
                                                onClick={() => openModalWithDefinition(slideone)}>
                                                단어해석
                                            </button>
                                            <button
                                                className="hover:text-white p-3 text-2xl text-black"
                                                onClick={() => openModalWithExample(slideone.id)}>
                                                예문보기
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    ))}
                    {/*</div>*/}
                    {/*</div>*/}
                    {/*))}*/}
                    {/*</Slider>*/}
                    {/*<Modal isOpen={isModalOpen} onClose={closeModal} handleIconClick={handleIconClick}/>*/}
                    <DefinitionModal
                        isOpen={isModalOpenW}
                        onClose={closeModalW}
                        definition={definition}
                    />
                    <ExampleModal
                        isOpen={isModalOpenE}
                        onClose={closeModalE}
                        example={example}
                    />
            </div>
    );
};

export default CustomSlider;
