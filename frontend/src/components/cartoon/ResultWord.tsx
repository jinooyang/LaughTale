import Icon from "@mdi/react";
import {mdiVolumeHigh} from "@mdi/js";
import axios from "axios";
import React, {useState} from "react";
import SpeechButton from "../common/SpeechButton.tsx";

function DefinitionModal({ isOpen, onClose, definition  }) {
    if (!isOpen) return null;

    return (
        <div className="z-1000 fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="bg-[#ffffff] p-4 rounded-lg">
                <div className="flex justify-between items-center w-full p-3">
                    <div className=" laughtale-font text-black flex-1 text-center">
                        단어 해석
                    </div>
                    <button
                        onClick={onClose}
                        className=" laughtale-font text-black rounded-full bg-grey-500 hover:bg-grey-700">
                        X
                    </button>
                </div>
                <div className="flex justify-items-center">
                    {/*단어의 뜻이 클 수 있으므로 오른쪽 영역을 차지하도록 한다*/}
                    <div className="flex justify-center text-black p-12">
                        <div className="rounded-xl bg-[#73ABE5] p-12">
                            <div>
                                <h2 className=" laughtale-font text-2xl text-black font-bold"
                                    dangerouslySetInnerHTML={{__html: definition}}></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

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

// const handleIconClick = (word) => {
//     playTTS(word);
// };

const ResultWord=({slides,selectedAnswers})=>{
    const [isModalOpenW, setIsModalOpenW] = useState(false);
    const closeModalW = () => setIsModalOpenW(false);
    const [definition, setDefinition] = useState('');
    const openModalWithDefinition = (slideone) => {
        setDefinition(slideone.definition);
        setIsModalOpenW(true);
    };

    console.log("데이터 출력" ,slides);
    console.log("여기지금새로출력" ,selectedAnswers);

    return(
        <div className=" laughtale-font w-[1050px] text-white font-bold flex flex-wrap text-4xl">
        {slides.map((slide, index) =>{
            const isCorrect = selectedAnswers[index]?.answerId === selectedAnswers[index].id+1;
            return (
            <div
                className="group p-6 flex justify-center items-center transform hover:scale-110 transition duration-300 relative" style={{ color: isCorrect ? '#19895D' : '#BD2B2B' }}>
                <div
                    key={index}
                    className="laughtale-font rounded-xl overflow-hidden w-[180px] h-[100px] flex justify-center items-center shadow-sm  bg-[#73ABE5] group-hover:bg-gradient-to-b from-[#4EDBDE] to-[#8675DA] hover:border-transparent hover:text-black transition-all duration-300"
                >
                    <div className="flex flex-col items-center space-y-2">
                    <div className="flex justify-center space-x-2 p-3">
                        <div className=" laughtale-font font-semibold hover:text-black">
                            {slide.option[slide.answerNo - 1]}
                        </div>
                        <div
                            // onClick={() => handleIconClick(slide.option[slide.answerNo - 1])}
                        >
                            <SpeechButton sentence={slide.option[slide.answerNo - 1]} style={{width:"3rem", display:"inline", marginLeft:'1rem'}}/>
                        </div>
                    </div>
                    <div className="flex justify-items-center">
                        <button
                            className=" laughtale-font p-3 text-2xl hover:text-white"
                            onClick={() => openModalWithDefinition(slide)}>
                            단어해석
                        </button>
                    </div>
                </div>
            </div>
            </div>
            )})}
<DefinitionModal
    isOpen={isModalOpenW}
    onClose={closeModalW}
    definition={definition}
/>
</div>
)
;
}
export default ResultWord;