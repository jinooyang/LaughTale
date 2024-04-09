import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { useState } from "react";
import Slider from "react-slick";

const comics = [
  {
    id: 1,
    title: "만화1",
    episode: "3화",
    imageUrl: "/src/assets/samples/e2-1.jpg",
  },
  {
    id: 2,
    title: "만화2",
    episode: "3화",
    imageUrl: "/src/assets/samples/e3-1.jpg",
  },
  {
    id: 3,
    title: "만화3",
    episode: "3화",
    imageUrl: "/src/assets/samples/e4-2.jpg",
  },
  {
    id: 4,
    title: "만화4",
    episode: "3화",
    imageUrl: "/src/assets/samples/e3-4.jpg",
  },
  {
    id: 5,
    title: "만화5",
    episode: "3화",
    imageUrl: "/src/assets/samples/e1-3.jpg",
  },
  {
    id: 5,
    title: "만화6",
    episode: "7화",
    imageUrl: "/src/assets/samples/e3-3.jpg",
  },
];

function Recent() {
  const initialIndex = 0;
  const [selected, setSelected] = useState(initialIndex);

  const settings = {
    dots: false,
    infinite: true,
    centerMode: true,
    centerPadding: "0px", // 중앙 이미지 강조를 위한 패딩 조정
    slidesToShow: 4.3, // 기본으로 노출되는 이미지 수 조정
    speed: 150, // 포커스 속도 조정
    cssEase: "ease-in-out", // 애니메이션 효과를 더 자연스럽게
    focusOnSelect: true,
    initialSlide: 0, // 수정된 초기 슬라이드 인덱스
    afterChange: (current) => setSelected(current),
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          centerPadding: "40px",
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2,
          centerPadding: "80px",
        },
      },
    ],
  };

  // 만화 객체가 하나도 없을 경우 아무것도 렌더링하지 않음
  if (comics.length === 0) return null;



  return (
      <div className="container mx-auto px-20 py-50 mt-10 pt-10 myslickclass">
        <Slider {...settings}>
          {comics.map((comic, index) => (
              <div
                  key={comic.id}
                  className={`relative p-4 transition-all duration-200 ease-in-out transform ${
                      selected === index ? "scale-150 z-30" : "scale-100 z-10"
                  }`}
                  style={{
                    marginRight: index === comics.length - 1 ? "0px" : "-50px",
                  }}
              >
                <img
                    src={comic.imageUrl}
                    alt={comic.title}
                    className={`w-full h-full object-cover rounded-lg transition-all duration-300 ease-in-out ${
                        selected === index ? "opacity-100" : "opacity-50"
                    }`}
                />
                {selected === index && (
                    <div className="text-center mt-2 text-black">
                      <h3 className="text-lg font-bold">{comic.title}</h3>
                      <p>{comic.episode}</p>
                    </div>
                )}
              </div>
          ))}
        </Slider>
      </div>
  );
}

export default Recent;