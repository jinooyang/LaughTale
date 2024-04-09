// CartoonGrid.tsx
import React from 'react';
import CartoonCard from './CartoonCard';
import {Link} from "react-router-dom"; // 경로는 실제 구조에 맞게 조정해야 합니다.

interface Cartoon {
    thumbnail: string;
    title: string;
    id: number;
}

interface CartoonGridProps {
    cartoons: Cartoon[];
    level:number;
}

const CartoonGrid: React.FC<CartoonGridProps> = ({ cartoons, level }) => {

    // const allCartoonsLink = `/cartoons/level/${level}`;
    return (

        <div className="mx-auto comonent_wrap " >
            <div className="flex items-center justify-between mb-4 ComponentHead">
                {/*<div className="text-2xl font-bold ComponentHead_title">Level {level}</div>*/}
                {/*<Link to={allCartoonsLink} className="text-lg text-black hover:text-black-800">*/}
                {/*    Level {level} 만화 더보기*/}
                {/*</Link>*/}
            </div>
            <div className="p-6">
                <div className="mx-auto" style={{maxWidth: '1350px'}}>
                    <ul className="grid grid-cols-1 gap-24 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5">
                        {cartoons.map((webtoon, index) => (
                            <CartoonCard
                                key={index}
                                imageUrl={webtoon.thumbnail}
                                title={webtoon.title}
                                mangaId={webtoon.id}
                            />
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default CartoonGrid;
