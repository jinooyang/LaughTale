import React, {useState, useEffect} from 'react';
import {Swiper, SwiperSlide} from 'swiper/react';
import SwiperCore, {EffectCoverflow, Pagination, Navigation} from 'swiper';

import '../../../node_modules/swiper/swiper.min.css'
import client from "../../apis";
import {useNavigate} from "react-router-dom";

SwiperCore.use([EffectCoverflow, Pagination, Navigation]);

function RecentWord() {

    const [swiperRef, setSwiperRef] = useState(null); // 여기에서 swiperRef 상태를 정의합니다.
    // 슬라이드 정보를 저장하는 상태
    const [slideInfo, setSlideInfo] = useState({title: ''});

    const [slidesData, setSlidesData] = useState([]);

    useEffect(() => {
        const fetchSlidesData = async () => {
            try {
                const response = await client.get('/manga/recent');
                console.log("캐러셀 : ", response)
                const slides = response.data.map(item => ({
                    id: item.id,
                    title: item.title,
                    imageUrl: item.thumbnail,
                }));
                setSlidesData(slides);
            } catch (error) {
                console.error("Failed to fetch slides data", error);
            }
        };
        fetchSlidesData();
    }, []);
    const navigate = useNavigate();


    // 슬라이드 클릭 핸들러 함수
    const handleSlideClick = (index) => {
        // 클릭한 슬라이드가 중앙에 위치하지 않았을 경우, 해당 슬라이드로 이동
        if (swiperRef && swiperRef.realIndex !== index) {
            swiperRef.slideTo(index);
        } else {
            // 여기에 중앙 슬라이드를 클릭했을 때의 페이지 이동 로직을 추가합니다.
            console.log(`Navigating to: /cartoon/${slidesData[index].id}`);
            console.log(slidesData, index)
            navigate(`/cartoon/${slidesData[index].id}`)
            // 예: window.location.href = `/comic/${slidesData[index].id}`;
        }
    };

    // slidesData가 비어 있으면 아무것도 렌더링하지 않음
    if (slidesData.length === 0) {
        return <div>최근 본 만화가 없습니다</div>;
    }


    return (
        <div className="container scale-[80%]" style={{maxWidth: '700px'}}>
            {/*<h1 className="heading">최근 본 만화목록</h1>*/}
            <Swiper
                effect={'coverflow'}
                grabCursor={true}
                centeredSlides={true}
                loop={false}
                slidesPerView={'auto'}
                coverflowEffect={{
                    rotate: 5,
                    stretch: 0,
                    depth: 190,
                    modifier: 2.5,
                }}

                pagination={{el: '.swiper-pagination', clickable: true}}
                navigation={{
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                }}
                onSwiper={setSwiperRef} // Swiper 인스턴스 설정
                onSlideChange={(swiper) => {
                    // 현재 중앙 슬라이드의 정보를 업데이트합니다.
                    const currentSlideData = slidesData[swiper.realIndex];
                    setSlideInfo(currentSlideData);
                }}
                onInit={(swiper) => {
                    // Swiper 초기화 시, 첫 번째 슬라이드 정보를 설정합니다.
                    const currentSlideData = slidesData[swiper.realIndex];
                    setSlideInfo(currentSlideData);
                }}
                className="swiper_container"
            >
                {slidesData.map((slide, index) => (
                    <SwiperSlide className="fixslidewidth" key={slide.id} onClick={() => handleSlideClick(index)}>
                        <img src={slide.imageUrl} alt={`slide_image_${index}`}/>
                    </SwiperSlide>
                ))}

            </Swiper>
            <div className="slide-info">
                <div className='truncate '>{slideInfo.title}</div>
            </div>

        </div>
    );
}

export default RecentWord;