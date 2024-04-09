// CartoonGrid.tsx
import React from 'react';
import CartoonCard from './CartoonCard'; // 경로는 실제 구조에 맞게 조정해야 합니다.

interface Cartoon {
    imageUrl: string;
    title: string;
    authors: string;
    mangaId: string;
}

interface CartoonGridProps {
    cartoons: Cartoon[];
    level:number;
}

const LevelCartoonGrid: React.FC<CartoonGridProps> = ({ cartoons, level }) => {
        console.log(cartoons)
    return (

        <div className="mx-auto comonent_wrap" style={{ maxWidth: '600px' }}>
            <div className="flex items-center justify-between mb-4 ComponentHead">
                <div className="text-2xl font-bold ComponentHead_title">Level {level}</div>

            </div>
            <div className="p-2 mb-8">
                <div className="mx-auto" style={{maxWidth: '600px'}}>
                    <ul className="grid grid-cols-1 gap-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
                        {cartoons.map((webtoon, index) => (
                            <CartoonCard
                                key={index}
                            imageUrl={webtoon.imageUrl}
                            title={webtoon.title}
                                mangaId={+webtoon.mangaId}
                        />
                    ))}
                    </ul>
             </div>
            </div>
        </div>
    );
};

export default LevelCartoonGrid;
