import Slider from 'react-slick';
import React, { useRef, useEffect } from 'react';
import image1 from '../../assets/samples/e1-1.jpg';
import image2 from '../../assets/samples/e1-3.jpg';
import image3 from '../../assets/samples/e1-2.jpg';

function error(message: string): never {
    throw new Error(message);
}
const ModalCarousel = ({example,selectedIndex}) => {
    const carouselRef = useRef();

    useEffect(() => {
        if (carouselRef.current) {
            // @ts-ignore
            carouselRef.current.slickGoTo(selectedIndex); // 선택된 인덱스의 이미지로 이동
        }
    }, [selectedIndex]);

    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        className: "slider variable-width",
        variableWidth: true,
        adaptiveHeight: true,
    };

    return (<div className="flex justify-center">
            <div className="w-[400px]">
                <Slider ref={carouselRef} {...settings}>
                    {example.speeches.map((image, index) => (
                        <div className="flex justify-center items-center">
                            <div key={index} className="p-3">
                                <img src={image.imageUrl} style={{ width: "400px", height: "500px" }} />
                            </div>
                        </div>
                    ))}
                </Slider>
            </div>
        </div>
    );
}

export default ModalCarousel;
